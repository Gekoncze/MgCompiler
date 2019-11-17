package cz.mg.compiler.tasks.builder.utilities;

import cz.mg.collections.array.Array;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;


public class Pattern {
    private final Filter[] filters;

    public Pattern(Filter... filters) {
        this.filters = filters;
    }

    public Filter[] getFilters() {
        return filters;
    }

    @Override
    public String toString() {
        return "[" + new Array<>(filters).toString("][") + "]";
    }

    public boolean match(Block block){
        if(filters.length != block.getParts().count()) return false;
        for(int i = 0; i < filters.length; i++){
            Filter filter = filters[i];
            Part part = block.getParts().get(i);
            if(!filter.match(part)) return false;
        }
        return true;
    }

    public static Pattern create(Block block){
        Filter[] filters = new Filter[block.getParts().count()];
        for(int i = 0; i < filters.length; i++){
            filters[i] = Filter.create(block.getParts().get(i));
        }
        return new Pattern(filters);
    }
}
