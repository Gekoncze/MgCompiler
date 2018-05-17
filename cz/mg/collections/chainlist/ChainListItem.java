package cz.mg.collections.chainlist;


public class ChainListItem<T> {
    private T data = null;
    private ChainListItem<T> previousItem = null;
    private ChainListItem<T> nextItem = null;
    private ChainList<T> list = null;

    public ChainListItem(T data) {
        this.data = data;
    }
    
    ChainListItem(T data, ChainList<T> list) {
        this.data = data;
        this.list = list;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    public boolean hasNext(){
        return nextItem != null;
    }
    
    public boolean hasPrevious(){
        return previousItem != null;
    }
    
    public T getPrevious(){
        if(previousItem == null) return null;
        return previousItem.data;
    }
    
    public T getNext(){
        if(nextItem == null) return null;
        return nextItem.data;
    }

    public ChainListItem getPreviousItem() {
        return previousItem;
    }

    public ChainListItem getNextItem() {
        return nextItem;
    }

    public T removePrevious() {
        if(previousItem == null) return null;
        return previousItem.remove();
    }

    public T removeNext() {
        if(nextItem == null) return null;
        return nextItem.remove();
    }
    
    public T remove() {
        if(list == null) return null;
        if(previousItem == null && nextItem == null) list.onRootItemRemoved();
        else if(previousItem == null) list.onFirstItemRemoved(nextItem);
        else if(nextItem == null) list.onLastItemRemoved(previousItem);
        else list.onItemRemoved();
        disconnectMiddle(previousItem, this, nextItem);
        return data;
    }

    public void addPrevious(T data) {
        ChainListItem<T> newItem = new ChainListItem<>(data);
        if(previousItem == null) list.onFirstItemAdded(newItem);
        else list.onItemAdded(newItem);
        connectMiddle(previousItem, newItem, this);
    }

    public void addNext(T data) {
        ChainListItem<T> newItem = new ChainListItem<>(data);
        if(nextItem == null) list.onLastItemAdded(newItem);
        else list.onItemAdded(newItem);
        connectMiddle(this, newItem, nextItem);
    }

    private void disconnectMiddle(ChainListItem left, ChainListItem middle, ChainListItem right) {
        middle.list = null;
        middle.previousItem = null;
        middle.nextItem = null;
        if(left != null) left.nextItem = right;
        if(right != null) right.previousItem = left;
    }

    private void connectMiddle(ChainListItem left, ChainListItem middle, ChainListItem right) {
        middle.list = left == null ? right.list : left.list;
        middle.previousItem = left;
        middle.nextItem = right;
        if(left != null) left.nextItem = middle;
        if(right != null) right.previousItem = middle;
    }
}