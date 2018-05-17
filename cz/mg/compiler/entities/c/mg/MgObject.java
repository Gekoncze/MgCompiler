//package cz.mg.compiler.entities.c.mg;
//
//import cz.mg.collections.ChainList;
//import cz.mg.collections.TreeNode;
//
//
//public abstract class MgObject extends TreeNode {
//	private final MgAccess access;
//	private final String name;
//
//	public MgObject(TreeNode parent, MgAccess access, String name) {
//		super(parent);
//        this.access = access;
//        this.name = name;
//	}
//	
//	public final MgAccess getAccess(){
//		return access;
//	}
//	
//    public final String getName() {
//        return name;
//    }
//	
//	public final String getClassName(){
//		ChainList<String> className = new ChainList<>();
//		String s = getClass().getSimpleName();
//		String[] r = s.split("(?=\\p{Lu})");
//		for(int i = 0; i < r.length; i++){
//			if(i == 0 && r[i].equals("Mg")) continue;
//			className.addLast(r[i]);
//		}
//		return className.toString(" ");
//	}
//}
