package cz.mg.projectexplorer.maps;

import cz.mg.collections.chainlist.ChainList;
import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.entities.a.segments.Page;
import cz.mg.compiler.entities.a.segments.tokens.ClosingToken;
import cz.mg.compiler.entities.a.segments.tokens.CommentToken;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.a.segments.tokens.LiteralToken;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.entities.a.segments.tokens.OpeningToken;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.a.segments.tokens.TypeToken;
import cz.mg.compiler.entities.a.segments.tokens.TypenameToken;
import cz.mg.compiler.entities.resources.Stream;
import cz.mg.compiler.tasks.TaskError;


public class Names {
    public static String getName(TreeNode node){
        if(node instanceof TaskError) return getSaneClassName(((TaskError) node).getException());
        if(node instanceof Stream) return ((Stream) node).getName();
        if(node instanceof Page) return ((Page)node).getName();
        if(node instanceof Token) return getTokenName((Token) node);
        return getSaneClassName(node);
    }
    
    public static String getSaneClassName(Object object){
		ChainList<String> className = new ChainList<>();
		String s = object.getClass().getSimpleName();
		String[] r = s.split("(?=\\p{Lu})");
		for(int i = 0; i < r.length; i++) className.addLast(r[i]);
		return className.toString(" ");
	}
    
    public static String getTokenName(Token token){
        String type = "";
        if(token instanceof LiteralToken) type += "L";
        if(token instanceof OperatorToken) type += "O";
        if(token instanceof KeywordToken) type += "K";
        if(token instanceof NameToken) type += "N";
        if(token instanceof TypeToken) type += "T";
        if(token instanceof TypenameToken) type += "Tn";
        if(token instanceof OpeningToken) return "(";
        if(token instanceof ClosingToken) return ")";
        if(token instanceof CommentToken) type += "#";
        String text = token.getText();
        if(text.length() > 15) text = text.substring(0, 12) + "...";
        if(!(token instanceof CommentToken)) type += " ";
        return type + text;
    }
}
