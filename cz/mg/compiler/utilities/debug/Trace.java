package cz.mg.compiler.utilities.debug;


public class Trace implements Traceable {
    private final String filename;
    private final String content;
    private final Integer begin;
    private final Integer end;
    private final Integer rowBegin;
    private final Integer rowEnd;
    private final Integer columnBegin;
    private final Integer columnEnd;

    public Trace() {
        this.filename = null;
        this.content = null;
        this.begin = null;
        this.end = null;
        this.rowBegin = null;
        this.rowEnd = null;
        this.columnBegin = null;
        this.columnEnd = null;
    }

    public Trace(String filename, String content, int begin, int end) {
        this.filename = filename;
        this.content = content;
        this.begin = begin;
        this.end = end;

        int row = 0;
        int column = 0;

        int rowBegin_ = 0;
        int rowEnd_ = 0;
        int columnBegin_ = 0;
        int columnEnd_ = 0;

        for(int i = 0; i < content.length(); i++){
            char ch = content.charAt(i);

            if(i == begin){
                rowBegin_ = row;
                columnBegin_ = column;
            }

            if(i == end){
                rowEnd_ = row;
                columnEnd_ = column;
            }

            if(ch == '\n') {
                row++;
                column = 0;
            } else {
                column++;
            }
        }

        this.rowBegin = rowBegin_;
        this.rowEnd = rowEnd_;
        this.columnBegin = columnBegin_;
        this.columnEnd = columnEnd_;
    }

    @Override
    public Trace getTrace() {
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public String getContent() {
        return content;
    }

    public Integer getRowBegin() {
        return rowBegin;
    }

    public Integer getRowEnd() {
        return rowEnd;
    }

    public Integer getColumnBegin() {
        return columnBegin;
    }

    public Integer getColumnEnd() {
        return columnEnd;
    }

    public Trace begin(){
        return new Trace(filename, content, begin, begin);
    }

    public Trace end(){
        return new Trace(filename, content, end, end);
    }
}