package cz.mg.compiler.tasks.builder.language;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.Documentation;
import cz.mg.compiler.entities.logical.language.Stamps;
import cz.mg.compiler.entities.logical.language.calls.Call;
import cz.mg.compiler.entities.logical.language.other.Datatype;
import cz.mg.compiler.entities.logical.language.other.Property;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.*;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.BuildException;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class BuildPropertiesTask extends BlockBuildTask {
    private ChainList<Property> properties = new CachedChainList<>();

    public BuildPropertiesTask(Task parentTask, Block block, Context context) {
        super(parentTask, block, context);
    }

    public ChainList<Property> getProperties() {
        return properties;
    }

    @Override
    protected Rules getRules() {
        return null;
    }

    @Override
    protected void build(Block block) {
        Stamps stamps = takeStamps();
        Documentation documentation = takeDocumentation();
        build(block.getParts().get(0));
        for(Property property : properties){
            property.setStamps(stamps);
            property.setDocumentation(documentation);
        }
    }

    private void build(Part part){
        check(part, List.class, Declaration.class, Expression.class);
        if(part instanceof List) buildProperties(toList(part));
        if(part instanceof Declaration) buildProperties(toList(part));
        if(part instanceof Expression) buildProperties((Expression) part);
    }

    private void buildProperties(Expression expression){
        Operator operator = cast(expression.getPart(), Operator.class);
        if(operator.getContent().equals("&=") || operator.getContent().equals("$=")){
            if(expression.getParts().count() != 2) throw new BuildException(expression, "Too many arguments for given operator.");
            Declaration declaration = cast(expression.getParts().get(0), Declaration.class);
            Datatype datatype = buildDatatype(getContext(), declaration.getType());
            buildProperty(declaration.getName(), datatype, buildInitializer(expression));
        } else {
            throw new BuildException(operator, "Unexpected operator. Expected \"&=\" or \"$=\", given \"", operator.getContent(), "\".");
        }
    }

    private void buildProperties(List list){
        Declaration declaration = cast(list.getParts().getFirst(), Declaration.class);
        Datatype datatype = buildDatatype(getContext(), declaration.getType());
        buildProperty(declaration.getName(), datatype, null);
        for(int i = 1; i < list.getParts().count(); i++){
            Part part = list.getParts().get(i);
            Name name = cast(part, Name.class);
            buildProperty(name, datatype, null);
        }
    }

    private void buildProperty(Name name, Datatype datatype, Call call){
        properties.addLast(new Property(name.getContent(), datatype, call));
    }

    private Call buildInitializer(Expression expression){
        BuildCallTask task = new BuildCallTask(this, expression, getContext());
        task.tryToRun();
        return task.getCall();
    }
}
