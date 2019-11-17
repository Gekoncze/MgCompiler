package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.tasks.composer.ComposeException;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.brackets.Brackets;
import cz.mg.compiler.entities.structured.parts.brackets.CurlyBrackets;
import cz.mg.compiler.entities.structured.parts.brackets.RoundBrackets;
import cz.mg.compiler.entities.structured.parts.brackets.SquareBrackets;


public class ComposeBrackets implements ComposeParts {
    public ComposeBrackets() {
    }

    @Override
    public void compose(ChainList<Part> parts){
        ChainList<ChainList<Part>> containers = new CachedChainList<>();
        containers.addLast(new CachedChainList<>());

        for(Part part : parts){
            String content = part.getContent().toString();

            switch(content){
                case "(":
                case "[":
                case "{":
                    sink(containers);
                    break;
                case ")":
                case "]":
                case "}":
                    part = raise(part, containers);
                    break;
            }

            containers.getLast().addLast(part);
        }

        if(containers.count() > 1){
            throw new ComposeException(containers.getLast().getFirst(), "Missing closing bracket(s).");
        }

        parts.clear();
        parts.addCollectionLast(containers.getLast());
    }

    private static void sink(ChainList<ChainList<Part>> containers){
        containers.addLast(new CachedChainList<>());
    }

    private static Brackets raise(Part closingBracket, ChainList<ChainList<Part>> containers){
        if(containers.count() <= 1) throw new ComposeException(closingBracket, "Missing opening bracket(s).");
        containers.getLast().addLast(closingBracket);
        return create(containers.removeLast());
    }

    private static Brackets create(ChainList<Part> parts){
        Special openingBracket = (Special)parts.removeFirst();
        Special closingBracket = (Special) parts.removeLast();
        if(!matches(openingBracket, closingBracket)) throw new ComposeException(closingBracket, "Opening and closing bracket mismatch: \"", openingBracket, "\" vs \"", closingBracket, "\".");
        switch (openingBracket.getContent().toString()){
            case "(": return new RoundBrackets(parts, openingBracket, closingBracket);
            case "[": return new SquareBrackets(parts, openingBracket, closingBracket);
            case "{": return new CurlyBrackets(parts, openingBracket, closingBracket);
            default: throw new RuntimeException("Part \"" + openingBracket + "\" is not a bracket?");
        }
    }

    private static boolean matches(Part a, Part b){
        String ac = a.getContent().toString();
        String bc = b.getContent().toString();
        switch (ac){
            case "(": return bc.equals(")");
            case "[": return bc.equals("]");
            case "{": return bc.equals("}");
            default: throw new RuntimeException("Part \"" + a + "\" is not a bracket?");
        }
    }
}
