package cz.mg.collections.readers;


public abstract class Reader<T> {
    public Reader(){
        setMark();
    }
    
    public abstract T read();
    public abstract T readOptional();
    public abstract T readPrevious();
    public abstract T readPreviousOptional();
    public abstract T readNext();
    public abstract T readNextOptional();
    public abstract T moveNext();
    public abstract T moveNextOptional();
    public abstract T movePrevious();
    public abstract T movePreviousOptional();
    public abstract boolean hasNext();
    public abstract boolean hasPrevious();
    public abstract void setMark();
    public abstract void returnToMark();
    protected abstract RuntimeException outOfBoundsException();
}
