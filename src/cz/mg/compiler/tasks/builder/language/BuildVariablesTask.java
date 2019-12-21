package cz.mg.compiler.tasks.builder.language;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.Documentation;
import cz.mg.compiler.entities.logical.language.Stamps;
import cz.mg.compiler.entities.logical.language.other.Datatype;
import cz.mg.compiler.entities.logical.language.other.Variable;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Declaration;
import cz.mg.compiler.entities.structured.parts.Expression;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Rules;

import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.buildDatatype;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class BuildVariablesTask extends BlockBuildTask {
    private ChainList<Variable> variables = new CachedChainList<>();

    public BuildVariablesTask(Block block, Context context) {
        super(block, context);
    }

    public ChainList<Variable> getVariables() {
        return variables;
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
        for(Variable property : variables){
            property.setStamps(stamps);
            property.setDocumentation(documentation);
        }
    }

    private void build(Part part){
        check(part, List.class, Declaration.class, Expression.class);
        if(part instanceof List) buildProperties(toList(part));
        if(part instanceof Declaration) buildProperties(toList(part));
    }

    private void buildProperties(List list){
        Declaration declaration = cast(list.getParts().getFirst(), Declaration.class);
        Datatype datatype = buildDatatype(getContext(), declaration.getType());
        buildVariable(declaration.getName(), datatype);
        for(int i = 1; i < list.getParts().count(); i++){
            Part part = list.getParts().get(i);
            Name name = cast(part, Name.class);
            buildVariable(name, datatype);
        }
    }

    private void buildVariable(Name name, Datatype datatype){
        variables.addLast(new Variable(name.getContent(), datatype));
    }
}
