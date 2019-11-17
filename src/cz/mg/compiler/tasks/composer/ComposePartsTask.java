package cz.mg.compiler.tasks.composer;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.*;
import cz.mg.compiler.entities.structured.parts.groups.Group;
import cz.mg.compiler.entities.structured.parts.groups.UnitedGroup;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.SpecialToken;
import cz.mg.compiler.entities.text.tokens.ValueToken;
import cz.mg.compiler.entities.text.tokens.WordToken;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.composer.utilities.PartUtilities;
import cz.mg.compiler.tasks.composer.utilities.parts.*;


public class ComposePartsTask extends ComposeTask {
    private static final ComposeParts KEYWORD_VALUES = new ComposeKeywordValues();
    private static final ComposeParts KEYWORD_OPERATORS = new ComposeKeywordOperators();
    private static final ComposeParts KEYWORD_GROUPS = new ComposeKeywordGroups();
    private static final ComposeParts OPERATORS = new ComposeOperators();
    private static final ComposeParts BRACKETS = new ComposeBrackets();
    private static final ComposeParts ARGUMENTS = new ComposeArguments();
    private static final ComposeParts LISTS = new ComposeLists();
    private static final ComposeParts PATHS = new ComposePaths();
    private static final ComposeParts STAMPS = new ComposeStamps();
    private static final ComposeParts TYPES = new ComposeTypes();
    private static final ComposeParts DECLARATIONS = new ComposeDeclarations();
    private static final ComposeParts ARGUMENTS_EXPRESSIONS = new ComposeArgumentsExpressions();
    private static final ComposeParts OPERATOR_EXPRESSIONS = new ComposeOperatorExpressions();

    private final Line line;
    private ChainList<Part> parts;

    public ComposePartsTask(Task parentTask, Line line) {
        super(parentTask);
        this.line = line;
    }

    public ChainList<Part> getParts() {
        return parts;
    }

    @Override
    protected void onRun() {
        parts = createBasicParts(line);
        compose(parts);
        sweepRoots(parts);
    }

    private static ChainList<Part> createBasicParts(Line line){
        ChainList<Part> parts = new CachedChainList<>();
        for(Token token : line.getTokens()){
            parts.addLast(createBasicPart(token));
        }
        return parts;
    }

    private static Part createBasicPart(Token token){
        if(token instanceof SpecialToken) return new Special(token.getContent());
        if(token instanceof ValueToken) return new Value(token.getContent());
        if(token instanceof WordToken){
            WordToken wordToken = (WordToken) token;
            if(wordToken.hasUpper() && !wordToken.hasLower() && !wordToken.hasNumber() && !wordToken.hasUnderscore()) return new Keyword(wordToken.getContent());
            if(wordToken.hasLower()) return new Name(wordToken.getContent());
            throw new ComposeException(token, "Illegal word token. " + wordToken.hasLeadingUpper() + " " + wordToken.hasUpper() + " " + wordToken.hasLower() + " " + wordToken.hasNumber());
        }
        throw new RuntimeException();
    }

    private static void compose(ChainList<Part> parts){
        for(ChainList<Part> root : getRoots(parts)) KEYWORD_VALUES.compose(root);
        for(ChainList<Part> root : getRoots(parts)) KEYWORD_OPERATORS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) KEYWORD_GROUPS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) OPERATORS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) BRACKETS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) ARGUMENTS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) LISTS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) PATHS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) STAMPS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) TYPES.compose(root);
        for(ChainList<Part> root : getRoots(parts)) DECLARATIONS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) ARGUMENTS_EXPRESSIONS.compose(root);
        for(ChainList<Part> root : getRoots(parts)) OPERATOR_EXPRESSIONS.compose(root);
    }

    private static void sweepRoots(ChainList<Part> parts){
        for(ChainListItem<Part> item = parts.getFirstItem(); item != null; item = item.getNextItem()){
            if(item.getData() instanceof Group){
                sweepRoots(((Group) item.getData()).getParts());
                item.setData(PartUtilities.sweep(item.getData()));
            }
        }
    }

    private static ChainList<ChainList<Part>> getRoots(ChainList<Part> parts){
        ChainList<ChainList<Part>> roots = new CachedChainList<>();
        roots.addLast(parts);
        for(Part part : parts){
            if(part instanceof Group){
                addRoots(roots, (Group) part);
            }
        }
        return roots;
    }

    private static void addRoots(ChainList<ChainList<Part>> roots, Group group){
        if(group instanceof UnitedGroup) roots.addLast(group.getParts());

        for(Part part : group.getParts()){
            if(part instanceof Group){
                addRoots(roots, (Group) part);
            }
        }
    }
}
