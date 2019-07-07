package cz.mg.compiler.utilities.readers;

public abstract class ArrayReader<T> extends Reader<T> {
    private int position = 0;
    private int mark;

    @Override
    public T read() {
        if(!canRead()) throw outOfBoundsException();
        return get(position++);
    }

    @Override
    public T readOptional() {
        if(!canRead()) return null;
        return get(position++);
    }

    @Override
    public void back() {
        position--;
    }

    @Override
    public boolean canRead(){
        return position >= 0 && position < count();
    }

    private boolean canRead(int position){
        return position >= 0 && position < count();
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

    public void setMark() {
        mark = getPosition();
    }

    protected abstract int count();
    protected abstract T get(int i);
}