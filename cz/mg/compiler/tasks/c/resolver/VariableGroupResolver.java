//package cz.mg.compiler.tasks.c.resolver;
//
//import cz.mg.collections.ChainList;
//import cz.mg.compiler.entities.b.logical.source.LogicalVariableGroup;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.compiler.entities.c.mg.MgVariable;
//import cz.mg.compiler.tasks.c.resolver.filters.NoFilter;
//import java.util.Iterator;
//
//
//public class VariableGroupResolver {
//    public boolean matches(LogicalVariableGroup group) {
//		ChainList<MgVariable> variables = getVariables();
//		ChainList<MgVariable> groupVariables = group.getVariables();
//        if(groupVariables.size() > variables.size()) return false;
//        Iterator<MgVariable> a = variables.iterator();
//        Iterator<MgVariable> b = groupVariables.iterator();
//        while(b.hasNext()){
//            MgVariable va = a.next();
//            MgVariable vb = b.next();
//            if(!va.getType().matches(vb.getType())) return false;
//        }
//        return true;
//    }
//    
//    public static LogicalVariableGroup unionAll(LogicalVariableGroup a, LogicalVariableGroup b){
//		LogicalVariableGroup group = new LogicalVariableGroup(a.getParent(), false, false);
//		for(MgVariable v : a.getVariables()) group.add(v);
//		for(MgVariable v : b.getVariables()) group.add(v);
//        return group;
//    }
//    
//        public ChainList<MgVariable> getVariables() {
//		ChainList<MgObject> objects = new ChainList<>();
//		findObjects(objects, new NoFilter());
//		ChainList<MgVariable> variables = new ChainList<>();
//		for(MgObject object : objects) variables.addLast((MgVariable) object);
//        return variables;
//    }
//}
