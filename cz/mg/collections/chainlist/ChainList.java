package cz.mg.collections.chainlist;

import java.util.Iterator;


public class ChainList<T> implements Iterable<T> {
    private int itemCount = 0;
	private ChainListItem<T> firstItem = null;
	private ChainListItem<T> lastItem = null;

	public ChainList() {
	}
	
	public ChainList(T data) {
		addLast(data);
	}
    
    public void addFirst(T data) {
        if(firstItem == null){
            ChainListItem<T> newItem = new ChainListItem<>(data, this);
            firstItem = newItem;
            lastItem = newItem;
			itemCount = 1;
        } else {
            firstItem.addPrevious(data);
        }
    }
    
    public void addLast(T data) {
        if(lastItem == null){
            ChainListItem<T> newItem = new ChainListItem<>(data, this);
            firstItem = newItem;
            lastItem = newItem;
			itemCount = 1;
        } else {
            lastItem.addNext(data);
        }
    }
	
	public void clear(){
		while(!isEmpty()) removeFirst();
	}
    
    public T removeFirst(){
        if(firstItem == null) return null;
        return firstItem.remove();
    }
    
    public T removeLast(){
        if(lastItem == null) return null;
        return lastItem.remove();
    }

    public T getFirst() {
		if(firstItem == null) return null;
        return firstItem.getData();
    }

    public T getLast() {
		if(lastItem == null) return null;
        return lastItem.getData();
    }
    
    public T get(int i) {
        return getItem(i).getData();
    }

    public ChainListItem<T> getFirstItem() {
        return firstItem;
    }

    public ChainListItem<T> getLastItem() {
        return lastItem;
    }
    
    public ChainListItem<T> getItem(int id) {
        if(id < 0 || id >= itemCount) return null;
        ChainListItem<T> currentItem = firstItem;
        for(int i = 0; i < id; i++){
            currentItem = currentItem.getNextItem();
        }
        return currentItem;
    }
    
    public boolean isEmpty(){
        return itemCount <= 0;
    }
    
    public int size(){
        return itemCount;
    }
    
    void onItemRemoved(){
        itemCount--;
    }
    
    void onFirstItemRemoved(ChainListItem<T> newFirstItem){
        firstItem = newFirstItem;
        itemCount--;
    }
    
    void onLastItemRemoved(ChainListItem<T> newLastItem){
        lastItem = newLastItem;
        itemCount--;
    }
    
    void onRootItemRemoved(){
        firstItem = null;
        lastItem = null;
        itemCount = 0;
    }
    
    void onItemAdded(ChainListItem<T> newItem) {
        onSizeRaised(newItem); // must be called first to ensure integrity
        itemCount++;
    }
    
    void onFirstItemAdded(ChainListItem<T> newItem) {
        onSizeRaised(newItem); // must be called first to ensure integrity
        firstItem = newItem;
        itemCount++;
    }
    
    void onLastItemAdded(ChainListItem<T> newItem) {
        onSizeRaised(newItem); // must be called first to ensure integrity
        lastItem = newItem;
        itemCount++;
    }
	
	private void onSizeRaised(ChainListItem<T> newItem) {
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private ChainListItem<T> current = firstItem;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T rval = current.getData();
                current = current.getNextItem();
                return rval;
            }
        };
    }
	
	public static <T> ChainList<T> unionAll(ChainList<T> a, ChainList<T> b){
        ChainList<T> result = new ChainList<>();
        for(T aa : a) result.addLast(aa);
        for(T bb : b) result.addLast(bb);
        return result;
    }
    
    public static <T> ChainList<T> unionAll(T a, ChainList<T> b){
        ChainList<T> result = new ChainList<>();
        result.addLast(a);
        for(T bb : b) result.addLast(bb);
        return result;
    }
    
    public static <T> ChainList<T> unionAll(ChainList<T> a, T b){
        ChainList<T> result = new ChainList<>();
        for(T aa : a) result.addLast(aa);
        result.addLast(b);
        return result;
    }
	
	public String toString(String delim){
        StringBuilder s = new StringBuilder();
        boolean first = true;
        for(T t : this){
            if(!first) s.append(delim);
            s.append(t);
            first = false;
        }
        return s.toString();
    }
}
