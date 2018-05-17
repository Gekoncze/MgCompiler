package cz.mg.collections.readers;

public abstract class ArrayReader<T> extends Reader<T> {
    private int position = -1;
    private int mark;
    
    @Override
    public T read() {
        if(position < 0 || position >= size()) throw outOfBoundsException();
        return get(position);
    }
    
    @Override
    public T readOptional() {
        if(position < 0 || position >= size()) return null;
        return get(position);
    }
    
    @Override
    public T readPrevious() {
        if(!hasPrevious()) throw outOfBoundsException();
        return get(position - 1);
    }
    
    @Override
    public T readPreviousOptional(){
        if(!hasPrevious()) return null;
        return get(position - 1);
    }
    
    @Override
    public T readNext() {
        if(!hasNext()) throw outOfBoundsException();
        return get(position + 1);
    }
    
    @Override
    public T readNextOptional(){
        if(!hasNext()) return null;
        return get(position + 1);
    }
    
    @Override
    public T moveNext() {
        if(!hasNext()) throw outOfBoundsException();
        position++;
        return get(position);
    }
    
    @Override
    public T moveNextOptional() {
        if(!hasNext()) return null;
        position++;
        return get(position);
    }
    
    @Override
    public T movePrevious() {
        if(!hasPrevious()) throw outOfBoundsException();
        position--;
        return get(position);
    }
    
    @Override
    public T movePreviousOptional() {
        if(!hasPrevious()) return null;
        position--;
        return get(position);
    }
    
    @Override
    public boolean hasNext(){
        return (position + 1) < size();
    }
    
    @Override
    public boolean hasPrevious(){
        return (position - 1) >= 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMark() {
        return mark;
    }
    
    @Override
    public void setMark() {
        mark = getPosition();
    }
    
    @Override
    public void returnToMark(){
        position = mark;
    }
    
    protected abstract int size();
    protected abstract T get(int i);
}
