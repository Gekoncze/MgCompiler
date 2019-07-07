package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.entities.structured.parts.chains.Path;


public class ComposePaths implements ComposeParts {
    public ComposePaths() {
    }

    @Override
    public void compose(ChainList<Part> parts){
        ChainList<ChainList<ChainListItem<Part>>> allPathItems = new CachedChainList<>();
        ChainList<ChainListItem<Part>> currentPath = null;
        for(int i = 0; i < parts.count(); i++){
            ChainListItem<Part> previous = parts.getItem(i-1);
            ChainListItem<Part> current = parts.getItem(i);
            ChainListItem<Part> next = parts.getItem(i+1);
            if(isDotSpecial(current)){
                if(previous == null) throw new CompileException(current.getData(), "Missing part before \"", current.getData().getContent(), "\".");
                if(next == null) throw new CompileException(current.getData(), "Missing part after \"", current.getData().getContent(), "\".");
                if(isDotSpecial(next)) throw new CompileException(current.getData(), "Illegal part after \"", current.getData().getContent(), "\": \"", next.getData().getContent(), "\"");
                if(currentPath == null){
                    allPathItems.addLast(new CachedChainList<>());
                    currentPath = allPathItems.getLast();
                    currentPath.addLast(previous);
                    currentPath.addLast(current);
                    currentPath.addLast(next);
                    i++; // skip next element
                } else {
                    currentPath.addLast(current);
                    currentPath.addLast(next);
                    i++; // skip next element
                }
            } else {
                currentPath = null;
            }
        }

        for(ChainList<ChainListItem<Part>> pathItems : allPathItems){
            Path path = new Path(createParts(pathItems));
            pathItems.getFirst().setData(path);
        }
    }

    private static ChainList<Part> createParts(ChainList<ChainListItem<Part>> pathItems){
        ChainList<Part> parts = new CachedChainList<>();
        boolean dot = false;
        for(ChainListItem<Part> item : pathItems){
            if(item == pathItems.getFirst()){
                parts.addLast(item.getData());
            } else {
                parts.addLast(item.remove());
            }
            if(dot) parts.removeLast(); // not including dot
            dot = !dot;
        }
        return parts;
    }

    private static boolean isDotSpecial(ChainListItem<Part> item){
        if(item == null) return false;
        if(item.getData() instanceof Special) return item.getData().getContent().equals(".");
        return false;
    }
}
