package cz.mg.compiler.tasks;

import cz.mg.collections.chainlist.ChainList;
import cz.mg.compiler.Location;


public class ErrorMessage {
    private final ChainList<String> message = new ChainList<>();
    private final Location location;
    
    public ErrorMessage(Location location, Object[] parts){
        this.location = location;
        ChainList list = null;
        for(int i = 0; i < parts.length; i++){
            Object t = parts[i];
            if(list != null) {
                addList(list, t);
                list = null;
            } else {
                if(t instanceof ChainList) list = (ChainList) t;
                else add(t);
            }
        }
        if(list != null) throw new RuntimeException("List without delimiter.");
    }

    public Location getLocation() {
        return location;
    }
    
    private void addList(ChainList list, Object delim){
        boolean first = true;
        for(Object o : list){
            if(!first) add(delim);
            add(o);
            first = false;
        }
    }
    
    private void add(Object t){
        if(t instanceof String) message.addLast((String) t);
		else if(t == null) message.addLast("null");
        else message.addLast(t + "");
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
		if(location != null){
            text.append("In ");
            text.append(location);
            text.append(": ");
        }
        for(String part : message){
            text.append(part);
        }
        return text.toString();
    }
}
