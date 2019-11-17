package cz.mg.compiler.tasks.builder.language;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.calls.*;
import cz.mg.compiler.entities.logical.language.links.*;
import cz.mg.compiler.entities.logical.language.other.*;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.*;
import cz.mg.compiler.entities.structured.parts.Void;
import cz.mg.compiler.entities.structured.parts.chains.Path;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.BuildTask;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class BuildCallTask extends BuildTask {
    private final Part part;
    private Context context;
    private Call call = null;
    private final ChainList<Variable> declaredVariables = new CachedChainList<>();

    public BuildCallTask(Task parentTask, Part part, Context context) {
        super(parentTask);
        this.part = part;
        this.context = context;
    }

    public Call getCall() {
        return call;
    }

    public ChainList<Variable> getDeclaredVariables() {
        return declaredVariables;
    }

    @Override
    protected void onRun() {
        call = buildCall(part);
    }

    private Call buildCall(Part part){
        check(part, Value.class, Name.class, Void.class, Null.class, Operator.class, Declaration.class, Expression.class, Path.class, Type.class);
        if(part instanceof Value) return buildValueCall((Value)part);
        if(part instanceof Name) return buildNameCall((Name) part);
        if(part instanceof Void) return buildOtherCall(part.getContent());
        if(part instanceof Null) return buildOtherCall(part.getContent());
        if(part instanceof Operator) return buildOperatorCall((Operator) part);
        if(part instanceof Declaration) return buildDeclarationCall((Declaration) part);
        if(part instanceof Expression) return buildExpression((Expression) part);
        if(part instanceof Path) return buildPathCall((Path) part);
        if(part instanceof Type) return buildDatatypeCall(part);
        throw new RuntimeException();
    }

    private Call buildValueCall(Value value){
        return new ValueCall(value.getTrace(), value.getValue());
    }

    private Call buildNameCall(Name name){
        return new NameCall(name.getTrace(), new NamedLink(context, name.getContent()));
    }

    private Call buildOtherCall(Text name) {
        return new OtherCall(name.getTrace(), new OtherLink(context, name));
    }

    private Call buildOperatorCall(Operator operator){
        return new OperatorCall(operator.getTrace(), new OperatorLink(context, operator.getContent()));
    }

    private Call buildDeclarationCall(Declaration declaration){
        Datatype datatype = buildDatatype(context, declaration.getType());
        declaredVariables.addLast(new Variable(declaration.getName().getContent(), datatype));
        return buildNameCall(declaration.getName());
    }

    private Call buildExpression(Expression expression){
        Part operation = expression.getPart();
        Call call = buildCall(operation);
        for(Part argument : expression.getParts()) call.getArguments().addLast(buildCall(argument));
        return call;
    }

    private Call buildPathCall(Path path){
        Call call = buildCall(path.getParts().get(0));
        for(int i = 1; i < path.getParts().count(); i++){
            Call previousCall = call;
            Part previousPart = path.getParts().get(i-1);
            Part currentPart = path.getParts().get(i);
            Trace trace = Text.between(previousPart.getContent(), currentPart.getContent()).getTrace();
            call = new PathCall(trace);
            call.getArguments().addLast(previousCall);
            call.getArguments().addLast(buildCall(path.getParts().get(i)));
        }
        return call;
    }

    private Call buildDatatypeCall(Part part){
        Type type = cast(part, Type.class);
        return new DatatypeCall(buildDatatype(context, type));
    }
}
