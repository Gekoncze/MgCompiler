package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.a.segments.tokens.TypeToken;


public class Rules {    
    public static final Rule MODULE = new Rule() {
        @Override
        public boolean applicable(Line line) {
            if(line.getChildren().count() < 1) return false;
            Object first = line.getChildren().getFirst();
            if(!(first instanceof KeywordToken)) return false;
            KeywordToken keywordToken = (KeywordToken) first;
            return keywordToken.getKeyword().equals(Keyword.MODULE);
        }
    };
    
    public static final Rule USING = new Rule() {
        @Override
        public boolean applicable(Line line) {
            if(line.getChildren().count() < 1) return false;
            Object first = line.getChildren().getFirst();
            if(!(first instanceof KeywordToken)) return false;
            KeywordToken keywordToken = (KeywordToken) first;
            return keywordToken.getKeyword().equals(Keyword.USING);
        }
    };
    
    public static final Rule CLASS = new Rule() {
        @Override
        public boolean applicable(Line line) {
            if(line.getChildren().count() < 3) return false;
            Object first = line.getChildren().getFirst();
            Object third = line.getChildren().get(2);
            if(!(first instanceof KeywordToken)) return false;
            if(!(third instanceof KeywordToken)) return false;
            KeywordToken firstKeyword = (KeywordToken) first;
            KeywordToken thirdKeyword = (KeywordToken) third;
            return firstKeyword.getKeyword().equals(Keyword.DEFINE) && thirdKeyword.getKeyword().equals(Keyword.CLASS);
        }
    };
    
    public static final Rule ATTRIBUTE = new Rule() {
        @Override
        public boolean applicable(Line line) {
            if(line.getChildren().count() < 3) return false;
            Object first = line.getChildren().getFirst();
            Object second = line.getChildren().get(1);
            Object third = line.getChildren().get(2);
            if(!(first instanceof KeywordToken || first instanceof TypeToken)) return false;
            if(!(second instanceof KeywordToken || second instanceof TypeToken)) return false;
            if(!(third instanceof NameToken)) return false;
            return true;
        }
    };
    
    public static final Rule FUNCTION = new Rule() {
        @Override
        public boolean applicable(Line line) {
            if(line.getChildren().count() < 3) return false;
            Object first = line.getChildren().getFirst();
            Object third = line.getChildren().get(2);
            if(!(first instanceof KeywordToken)) return false;
            if(!(third instanceof KeywordToken)) return false;
            KeywordToken firstKeyword = (KeywordToken) first;
            KeywordToken thirdKeyword = (KeywordToken) third;
            return firstKeyword.getKeyword().equals(Keyword.DEFINE) && thirdKeyword.getKeyword().equals(Keyword.FUNCTION);
        }
    };
    
    public static final Rule OPERATOR = new Rule() {
        @Override
        public boolean applicable(Line line) {
            if(line.getChildren().count() < 3) return false;
            Object first = line.getChildren().getFirst();
            Object third = line.getChildren().get(2);
            if(!(first instanceof KeywordToken)) return false;
            if(!(third instanceof KeywordToken)) return false;
            KeywordToken firstKeyword = (KeywordToken) first;
            KeywordToken thirdKeyword = (KeywordToken) third;
            return firstKeyword.getKeyword().equals(Keyword.DEFINE) && thirdKeyword.getKeyword().equals(Keyword.OPERATOR);
        }
    };
}
