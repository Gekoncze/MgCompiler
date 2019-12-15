package cz.mg.compiler.entities;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.structured.Structure;
import cz.mg.compiler.utilities.debug.Trace;
import cz.mg.compiler.entities.input.Input;
import cz.mg.compiler.entities.logical.Logic;
import cz.mg.compiler.entities.output.Output;
import cz.mg.compiler.entities.text.Book;


public class Entities extends Entity {
    @Part
    private final Input input = new Input();

    @Part
    private final Book book = new Book();

    @Part
    private final Structure structure = new Structure();

    @Part
    private final Logic logic = new Logic();

    @Part
    private final Output output = new Output();

    public Input getInput() {
        return input;
    }

    public Book getBook() {
        return book;
    }

    public Structure getStructure() {
        return structure;
    }

    public Logic getLogic() {
        return logic;
    }

    public Output getOutput() {
        return output;
    }

    @Override
    public Trace getTrace() {
        return new Trace();
    }
}
