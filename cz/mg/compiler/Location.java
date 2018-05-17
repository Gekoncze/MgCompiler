package cz.mg.compiler;


public class Location {
    private final String name;
    private final int rowBegin, rowEnd;
    private final int columnBegin, columnEnd;

    public Location(String name, int rowBegin, int rowEnd, int columnBegin, int columnEnd) {
        this.name = name;
        this.rowBegin = rowBegin;
        this.rowEnd = rowEnd;
        this.columnBegin = columnBegin;
        this.columnEnd = columnEnd;
    }

    public String getName() {
        return name;
    }

    public int getRowBegin() {
        return rowBegin;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public int getColumnBegin() {
        return columnBegin;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

    @Override
    public String toString() {
        String text = name;
        if(rowBegin >= 0 && rowEnd >= 0){
            if(rowBegin == rowEnd){
                text += " line " + rowBegin;
            } else {
                text += " lines from " + rowBegin + " to " + rowEnd;
            }
        }
        if(columnBegin >= 0 && columnEnd >= 0){
            if(columnBegin == columnEnd){
                text += " column " + columnBegin;
            } else {
                text += " columns from " + columnBegin + " to " + columnEnd;
            }
        }
        return text;
    }
}
