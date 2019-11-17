package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Colon;


public class ComposeArguments implements ComposeParts {
    public ComposeArguments() {
    }

    @Override
    public void compose(ChainList<Part> parts){
        ChainList<ChainList<Part>> containers = new CachedChainList<>();
        containers.addLast(new CachedChainList<>());

        for(Part part : parts){
            if(part.getContent().equals(":")) sink(part, containers);
            containers.getLast().addLast(part);
        }

        while(containers.count() >= 2){
            Colon colon = create(containers.removeLast());
            containers.getLast().addLast(colon);
        }

        parts.clear();
        parts.addCollectionLast(containers.getLast());
    }

    private static void sink(Part colon, ChainList<ChainList<Part>> containers){
        containers.addLast(new CachedChainList<>());
        containers.getLast().addLast(colon);
    }

    private static Colon create(ChainList<Part> parts){
        Special colon = (Special) parts.removeFirst();
        Text content = Text.merge(colon.getContent(), parts.getLast().getContent());
        parts.removeFirst(); // not including colon
        return new Colon(parts, colon);
    }
}
