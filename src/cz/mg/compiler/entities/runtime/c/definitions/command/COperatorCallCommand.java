package cz.mg.compiler.entities.runtime.c.definitions.command;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.CEntity;
import cz.mg.compiler.entities.runtime.c.definitions.CVariable;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class COperatorCallCommand extends CEntity {
    @Part
    private final CVariable leftOperand;

    @Part
    private final CVariable rightOperand;

    @Part
    private final CVariable output;

    public COperatorCallCommand(Trace trace, CVariable leftOperand, CVariable rightOperand, CVariable output) {
        super(trace);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.output = output;
    }

    public CVariable getLeftOperand() {
        return leftOperand;
    }

    public CVariable getRightOperand() {
        return rightOperand;
    }

    public CVariable getOutput() {
        return output;
    }
}
