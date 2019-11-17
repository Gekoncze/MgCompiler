package cz.mg.compiler.entities.structured;

import cz.mg.collections.Collection;
import cz.mg.compiler.utilities.debug.Text;


public class Part extends StructureEntity {
    public Part(Text content) {
        super(content);
    }

    protected static Text merge(Collection objects){
        Text text = null;
        for(Object object : objects){
            if(object instanceof Text){
                text = Text.merge(text, (Text) object);
            }

            if(object instanceof Part){
                text = Text.merge(text, ((Part)object).getContent());
            }
        }
        return text;
    }

    protected static Text merge(Object... objects){
        Text text = null;
        for(Object object : objects){
            if(object instanceof Text){
                text = Text.merge(text, (Text) object);
            }

            if(object instanceof Part){
                text = Text.merge(text, ((Part)object).getContent());
            }
        }
        return text;
    }
}
