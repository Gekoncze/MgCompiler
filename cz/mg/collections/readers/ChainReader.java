package cz.mg.collections.readers;

import cz.mg.collections.chainlist.ChainList;
import cz.mg.collections.chainlist.ChainListItem;


public abstract class ChainReader<T> extends Reader<T> {
    private final ChainList<T> list;
    private ChainListItem<T> current = null;
    private ChainListItem<T> mark = null;
    
    public ChainReader(ChainList<T> list){
        this.list = list;
    }
    
    public int size(){
        return list.size();
    }
    
    @Override
    public T read() {
        if(current == null) throw outOfBoundsException();
        return current.getData();
    }

    @Override
    public T readOptional() {
        if(current == null) return null;
        return current.getData();
    }

    @Override
    public T readPrevious() {
        if(!hasPrevious()) throw outOfBoundsException();
        return current.getPrevious();
    }

    @Override
    public T readPreviousOptional() {
        if(!hasPrevious()) return null;
        return current.getPrevious();
    }

    @Override
    public T readNext() {
        if(!hasNext()) throw outOfBoundsException();
        if(current == null) return list.getFirst();
        return current.getNext();
    }

    @Override
    public T readNextOptional() {
        if(!hasNext()) return null;
        if(current == null) return list.getFirst();
        return current.getNext();
    }

    @Override
    public T moveNext() {
        if(!hasNext()) throw outOfBoundsException();
		if(current == null){
			current = list.getFirstItem();
		} else {
			current = current.getNextItem();
		}
        return current.getData();
    }

    @Override
    public T moveNextOptional() {
        if(!hasNext()) return null;
        if(current == null){
			current = list.getFirstItem();
		} else {
			current = current.getNextItem();
		}
        return current.getData();
    }

    @Override
    public T movePrevious() {
        if(!hasPrevious()) throw outOfBoundsException();
        current = current.getPreviousItem();
        return current.getData();
    }

    @Override
    public T movePreviousOptional() {
        if(!hasPrevious()) return null;
        current = current.getPreviousItem();
        return current.getData();
    }

    @Override
    public boolean hasNext() {
        if(current == null) return list.getFirstItem() != null;
        return current.getNextItem() != null;
    }

    @Override
    public boolean hasPrevious() {
        if(current == null) return false;
        return current.getPreviousItem() != null;
    }

    @Override
    public void setMark() {
        mark = current;
    }

    @Override
    public void returnToMark() {
        current = mark;
    }
}
