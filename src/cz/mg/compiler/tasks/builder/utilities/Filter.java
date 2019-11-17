package cz.mg.compiler.tasks.builder.utilities;

import cz.mg.collections.Collection;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.*;
import cz.mg.compiler.entities.structured.parts.Void;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.entities.structured.parts.chains.Path;
import cz.mg.compiler.entities.structured.parts.Colon;
import cz.mg.compiler.entities.structured.parts.groups.Group;
import cz.mg.compiler.entities.structured.parts.brackets.CurlyBrackets;
import cz.mg.compiler.entities.structured.parts.brackets.RoundBrackets;
import cz.mg.compiler.entities.structured.parts.brackets.SquareBrackets;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public abstract class Filter {
    public static final KeywordFilter ALIAS = new KeywordFilter("ALIAS");
    public static final KeywordFilter ALL = new KeywordFilter("ALL");
    public static final KeywordFilter AS = new KeywordFilter("AS");
    public static final KeywordFilter BREAK = new KeywordFilter("BREAK");
    public static final KeywordFilter CASE = new KeywordFilter("CASE");
    public static final KeywordFilter CLASS = new KeywordFilter("CLASS");
    public static final KeywordFilter CONTINUE = new KeywordFilter("CONTINUE");
    public static final KeywordFilter EACH = new KeywordFilter("EACH");
    public static final KeywordFilter ELSE = new KeywordFilter("ELSE");
    public static final KeywordFilter FILES = new KeywordFilter("FILES");
    public static final KeywordFilter FOR = new KeywordFilter("FOR");
    public static final KeywordFilter FUNCTION = new KeywordFilter("FUNCTION");
    public static final KeywordFilter INPUT = new KeywordFilter("INPUT");
    public static final KeywordFilter IF = new KeywordFilter("IF");
    public static final KeywordFilter IN = new KeywordFilter("IN");
    public static final KeywordFilter IS = new KeywordFilter("IS");
    public static final KeywordFilter LIKE = new KeywordFilter("LIKE");
    public static final KeywordFilter LOCATION = new KeywordFilter("LOCATION");
    public static final KeywordFilter NATIVE = new KeywordFilter("NATIVE");
    public static final KeywordFilter NULL = new KeywordFilter("NULL");
    public static final KeywordFilter OF = new KeywordFilter("OF");
    public static final KeywordFilter OPERATOR = new KeywordFilter("OPERATOR");
    public static final KeywordFilter OUTPUT = new KeywordFilter("OUTPUT");
    public static final KeywordFilter RETURN = new KeywordFilter("RETURN");
    public static final KeywordFilter SOURCE = new KeywordFilter("SOURCE");
    public static final KeywordFilter STAMP = new KeywordFilter("STAMP");
    public static final KeywordFilter STRUCTURE = new KeywordFilter("STRUCTURE");
    public static final KeywordFilter SWITCH = new KeywordFilter("SWITCH");
    public static final KeywordFilter TYPE = new KeywordFilter("TYPE");
    public static final KeywordFilter USING = new KeywordFilter("USING");
    public static final KeywordFilter VIEW = new KeywordFilter("VIEW");
    public static final KeywordFilter WHILE = new KeywordFilter("WHILE");

    public static final PartFilter _DECLARATION_ = new PartFilter("<declaration>", Declaration.class);
    public static final PartFilter _EXPRESSION_ = new PartFilter("<expression>", Expression.class);
    public static final PartFilter _KEYWORD_ = new PartFilter("<keyword>", Keyword.class);
    public static final PartFilter _NAME_ = new PartFilter("<name>", Name.class);
    public static final PartFilter _NULL_ = new PartFilter("<null>", Null.class);
    public static final PartFilter _VOID_ = new PartFilter("<void>", Null.class);
    public static final PartFilter _OPERATOR_ = new PartFilter("<operator>", Operator.class);
    public static final PartFilter _SPECIAL_ = new PartFilter("<special>", Special.class);
    public static final PartFilter _STAMP_ = new PartFilter("<stamp>", Stamp.class);
    public static final PartFilter _TYPE_ = new PartFilter("<type>", Type.class);
    public static final PartFilter _VALUE_ = new PartFilter("<value>", Value.class);
    public static final PartFilter _GROUP_ = new PartFilter("<group>", Group.class);
    public static final PartFilter _COLON_ = new PartFilter("<colon>", Colon.class);
    public static final PartFilter _CURLY_BRACKETS_ = new PartFilter("<curly brackets>", CurlyBrackets.class);
    public static final PartFilter _ROUND_BRACKETS_ = new PartFilter("<round brackets>", RoundBrackets.class);
    public static final PartFilter _SQUARE_BRACKETS_ = new PartFilter("<square brackets>", SquareBrackets.class);
    public static final PartFilter _LIST_ = new PartFilter("<list>", List.class);
    public static final PartFilter _PATH_ = new PartFilter("<path>", Path.class);

    public static final Filter _DECLARATION_LIST_ = new PartListFilter("<declaration list>", Declaration.class);
    public static final Filter _EXPRESSION_LIST_ = new PartListFilter("<expression list>", Expression.class);
    public static final Filter _KEYWORD_LIST_ = new PartListFilter("<keyword list>", Keyword.class);
    public static final Filter _NAME_LIST_ = new PartListFilter("<name list>", Name.class);
    public static final Filter _NULL_LIST_ = new PartListFilter("<null list>", Null.class);
    public static final Filter _VOID_LIST_ = new PartListFilter("<void list>", Null.class);
    public static final Filter _OPERATOR_LIST_ = new PartListFilter("<operator list>", Operator.class);
    public static final Filter _SPECIAL_LIST_ = new PartListFilter("<special list>", Special.class);
    public static final Filter _STAMP_LIST_ = new PartListFilter("<stamp list>", Stamp.class);
    public static final Filter _TYPE_LIST_ = new PartListFilter("<type list>", Type.class);
    public static final Filter _VALUE_LIST_ = new PartListFilter("<value list>", Value.class);

    public static final Filter _DECLARATION_PATH_ = new PartPathFilter("<declaration path>", Declaration.class);
    public static final Filter _EXPRESSION_PATH_ = new PartPathFilter("<expression path>", Expression.class);
    public static final Filter _KEYWORD_PATH_ = new PartPathFilter("<keyword path>", Keyword.class);
    public static final Filter _NAME_PATH_ = new PartPathFilter("<name path>", Name.class);
    public static final Filter _NULL_PATH_ = new PartPathFilter("<null path>", Null.class);
    public static final Filter _VOID_PATH_ = new PartPathFilter("<void path>", Null.class);
    public static final Filter _OPERATOR_PATH_ = new PartPathFilter("<operator path>", Operator.class);
    public static final Filter _SPECIAL_PATH_ = new PartPathFilter("<special path>", Special.class);
    public static final Filter _STAMP_PATH_ = new PartPathFilter("<stamp path>", Stamp.class);
    public static final Filter _TYPE_PATH_ = new PartPathFilter("<type path>", Type.class);
    public static final Filter _VALUE_PATH_ = new PartPathFilter("<value path>", Value.class);

    public static final Filter _ANY_VALUE_ = new MultiPartFilter("<any value>", Expression.class, Name.class, Value.class, Null.class, Void.class);

    private static final ChainList<Filter> createFilters = new CachedChainList<>();

    static {
        Collection<Field> fields = ReflectionUtilities.getAllFields(Filter.class, true);
        for(Field field : fields){
            if(Modifier.isStatic(field.getModifiers())){
                if(ReflectionUtilities.typeof(field, KeywordFilter.class)){
                    createFilters.addLast((Filter) ReflectionUtilities.readField(null, field));
                }

                if(ReflectionUtilities.typeof(field, PartFilter.class)){
                    createFilters.addLast((Filter) ReflectionUtilities.readField(null, field));
                }
            }
        }
    }

    public static ChainList<Filter> getCreateFilters(){
        return createFilters;
    }

    private final String label;

    public Filter(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public abstract boolean match(Part part);

    @Override
    public String toString() {
        return label;
    }

    private static class KeywordFilter extends Filter {
        public KeywordFilter(String keyword) {
            super(keyword);
        }

        @Override
        public boolean match(Part part) {
            if(!(part instanceof Keyword)) return false;
            return part.getContent().equals(getLabel());
        }
    }

    private static class PartFilter extends Filter {
        private final Class clazz;

        public PartFilter(String label, Class clazz) {
            super(label);
            this.clazz = clazz;
        }

        @Override
        public boolean match(Part part) {
            return part.getClass() == clazz;
        }
    }

    private static class MultiPartFilter extends Filter {
        private final Class[] clazzes;

        public MultiPartFilter(String label, Class... clazzes) {
            super(label);
            this.clazzes = clazzes;
        }

        @Override
        public boolean match(Part part) {
            for(Class clazz : clazzes) if(part.getClass() != clazz) return true;
            return false;
        }
    }

    private static class PartListFilter extends Filter {
        private final Class clazz;

        public PartListFilter(String label, Class clazz) {
            super(label);
            this.clazz = clazz;
        }

        @Override
        public boolean match(Part part) {
            return part.getClass() == clazz || part.getClass() == List.class;
        }
    }

    private static class PartPathFilter extends Filter {
        private final Class clazz;

        public PartPathFilter(String label, Class clazz) {
            super(label);
            this.clazz = clazz;
        }

        @Override
        public boolean match(Part part) {
            return part.getClass() == clazz || part.getClass() == Path.class;
        }
    }

    private static class UnknownFilter extends Filter {
        public UnknownFilter(Part part) {
            super("<unknown>[" + part.getClass().getSimpleName() + "]{" + part.getContent() + "}");
        }

        @Override
        public boolean match(Part part) {
            return true;
        }
    }

    public static Filter create(Part part){
        for(Filter filter : getCreateFilters()){
            if(filter.match(part)) return filter;
        }
        return new UnknownFilter(part);
    }
}
