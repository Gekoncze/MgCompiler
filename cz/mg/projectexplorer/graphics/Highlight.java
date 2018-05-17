package cz.mg.projectexplorer.graphics;

import java.awt.Color;


public class Highlight {
    private final int row;
    private final int columnBegin;
    private final int columnEnd;
    private final Color color;

    public Highlight(int row, int columnBegin, int columnEnd, Color color) {
        this.row = row;
        this.columnBegin = columnBegin;
        this.columnEnd = columnEnd;
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public int getColumnBegin() {
        return columnBegin;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

    public Color getColor() {
        return color;
    }
}
