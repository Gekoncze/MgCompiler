package cz.mg.compiler.entities;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.structured.Structure;
import cz.mg.compiler.entities.logical.Logic;
import cz.mg.compiler.entities.text.Book;


public class Entities extends ToplevelEntity {
    @Part
    private final Book book = new Book();

    @Part
    private final Structure structure = new Structure();

    @Part
    private final Logic logic = new Logic();

    public Book getBook() {
        return book;
    }

    public Structure getStructure() {
        return structure;
    }

    public Logic getLogic() {
        return logic;
    }
}
