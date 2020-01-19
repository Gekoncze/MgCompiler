package cz.mg.compiler.tasks.builder.language.natives;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.natives.NativeFunctionDefinition;
import cz.mg.compiler.entities.logical.mg.other.Datatype;
import cz.mg.compiler.entities.logical.mg.other.Variable;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Declaration;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.entities.structured.parts.Operator;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import cz.mg.compiler.utilities.debug.Text;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class BuildNativeFunctionTask extends BlockBuildTask {
    @Info
    private final boolean input;

    @Info
    private final boolean output;

    @Info
    private final boolean operator;

    @Link
    private NativeFunctionDefinition functionDefinition = null;

    public BuildNativeFunctionTask(Block block, Context context, boolean input, boolean output, boolean operator) {
        super(block, context);
        this.input = input;
        this.output = output;
        this.operator = operator;
    }

    public NativeFunctionDefinition getFunctionDefinition() {
        return functionDefinition;
    }

    @Override
    protected Rules getRules() {
        return null;
    }

    @Override
    protected void build(Block block) {
        Name name = cast(block.getParts().get(2), Name.class);
        functionDefinition = new NativeFunctionDefinition(
                name.getContent(),
                buildVariables(getInputPart(block)),
                buildVariable(getOutputPart(block)),
                buildOperator(getOperatorPart(block))
        );
        functionDefinition.setStamps(takeStamps());
        functionDefinition.setDocumentation(takeDocumentation());
    }

    private Part getInputPart(Block block){
        if(input == false) return null;
        return block.getParts().get(4);
    }

    private Part getOutputPart(Block block){
        if(output == false) return null;
        if(input == false) return block.getParts().get(4);
        return block.getParts().get(6);
    }

    private Part getOperatorPart(Block block){
        if(operator == false) return null;
        if(input == false && output == false) return block.getParts().get(4);
        if(input == false && output == true) return block.getParts().get(6);
        if(input == true && output == false) return block.getParts().get(6);
        return block.getParts().get(8);
    }

    private ChainList<Variable> buildVariables(Part part){
        if(part == null) return null;
        ChainList<Variable> variables = new CachedChainList<>();
        List list = toList(part);

        Declaration declaration = cast(list.getParts().getFirst(), Declaration.class);
        Datatype datatype = buildDatatype(getContext(), declaration.getType());
        variables.addLast(new Variable(declaration.getName().getContent(), datatype));

        for(int i = 1; i < list.getParts().count(); i++){
            Part child = list.getParts().get(i);
            check(child, Declaration.class, Name.class);

            if(child instanceof Declaration){
                declaration = cast(child, Declaration.class);
                datatype = buildDatatype(getContext(), declaration.getType());
                variables.addLast(new Variable(declaration.getName().getContent(), datatype));
            }

            if(child instanceof Name){
                Name name = cast(list.getParts().get(i), Name.class);
                variables.addLast(new Variable(name.getContent(), datatype));
            }
        }

        return variables;
    }

    private Variable buildVariable(Part part){
        if(part == null) return null;
        Declaration declaration = cast(part, Declaration.class);
        Datatype datatype = buildDatatype(getContext(), declaration.getType());
        return new Variable(declaration.getName().getContent(), datatype);
    }

    private Text buildOperator(Part part){
        if(part == null) return null;
        Operator operator = cast(part, Operator.class);
        return operator.getContent();
    }
}
