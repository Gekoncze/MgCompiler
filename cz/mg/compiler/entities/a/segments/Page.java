package cz.mg.compiler.entities.a.segments;

import cz.mg.compiler.Location;


public class Page extends Segment<Book, Line> {
    private String name;
    
    public Page(Book parent, Location location) {
        super(parent, location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
