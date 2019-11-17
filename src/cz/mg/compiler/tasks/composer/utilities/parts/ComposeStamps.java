package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.entities.structured.parts.brackets.SquareBrackets;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.entities.structured.parts.Stamp;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class ComposeStamps implements ComposeParts {
    public ComposeStamps() {
    }

    @Override
    public void compose(ChainList<Part> parts) {
        for(int i = 0; i < parts.count(); i++){
            ChainListItem<Part> i0 = parts.getItem(i+0);
            ChainListItem<Part> i1 = parts.getItem(i+1);
            ChainListItem<Part> i2 = parts.getItem(i+2);
            boolean isStamp = isStamp(i0);
            boolean isName = isName(i1);
            boolean isBrackets = isBrackets(i2);
            if(isStamp && isName && isBrackets){
                Special stamp = (Special) i0.getData();
                Name name = (Name) i1.getData();
                SquareBrackets brackets = (SquareBrackets) i2.getData();

                Text content = Text.merge(stamp.getContent(), name.getContent());
                if(content.contains(' ')) continue;

                Text between = Text.between(content, brackets.getContent());
                if(between.contains(' ')) continue;

                List arguments = cast(sweep(brackets), List.class);
                i0.setData(new Stamp(stamp, name, sweepList(arguments)));

                i1.remove();
                i2.remove();
            } else if(isStamp && isName){
                Special stamp = (Special) i0.getData();
                Name name = (Name) i1.getData();

                Text content = Text.merge(stamp.getContent(), name.getContent());
                if(content.contains(' ')) continue;

                i0.setData(new Stamp(stamp, name, null));

                i1.remove();
            }
        }
    }

    private static boolean isStamp(ChainListItem<Part> part){
        if(part == null) return false;
        if(part.getData() instanceof Special) if(part.getData().getContent().equals("@")) return true;
        return false;
    }

    private static boolean isName(ChainListItem<Part> part){
        if(part == null) return false;
        return part.getData() instanceof Name;
    }

    private static boolean isBrackets(ChainListItem<Part> part){
        if(part == null) return false;
        return part.getData() instanceof SquareBrackets;
    }
}
