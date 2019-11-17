package cz.mg.compiler.entities;

import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.structured.StructuredBook;
import cz.mg.compiler.utilities.debug.Trace;
import cz.mg.compiler.entities.input.Input;
import cz.mg.compiler.entities.logical.Logic;
import cz.mg.compiler.entities.output.Output;
import cz.mg.compiler.entities.text.Book;


public class Entities extends Entity {
    @Child
    private final Input input = new Input();

    @Child
    private final Book book = new Book();

    @Child
    private final StructuredBook structuredBook = new StructuredBook();

    @Child
    private final Logic logic = new Logic();

    @Child
    private final Output output = new Output();

    public Input getInput() {
        return input;
    }

    public Book getBook() {
        return book;
    }

    public StructuredBook getStructuredBook() {
        return structuredBook;
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
