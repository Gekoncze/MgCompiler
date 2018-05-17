//package cz.mg.compiler.tasks.c.resolver;
//
//import cz.mg.collections.ChainList;
//import cz.mg.compiler.entities.c.mg.MgBuildinTypename;
//import cz.mg.compiler.entities.c.mg.MgType;
//import cz.mg.compiler.entities.c.mg.MgTypename;
//import cz.mg.compiler.entities.c.mg.calls.MgCall;
//import cz.mg.compiler.entities.c.mg.calls.MgLiteralCall;
//
//
//public class LogicalLiteralCallResolver {
//    @Override
//	public ChainList<MgCall> getPhysicalCallCandidates(Context context) {
//		ChainList<MgCall> candidates = new ChainList<>();
//		if(value.equals("TRUE") || value.equals("FALSE")) booleanCandidates(candidates);
//		else if(value.equals("NULL")) nullCandidates(candidates);
//		else {
//			characterCandidates(candidates);
//			integerCandidates(candidates);
//			realCandidates(candidates);
//			stringCandidates(candidates);
//		}
//		return candidates;
//	}
//	
//	private void characterCandidates(ChainList<MgCall> candidates){
//        ChainList<MgTypename> typenames = new ChainList<>();
//        typenames.addLast(MgBuildinTypename.CHAR8);
//        typenamesToCallCandidates(typenames, candidates);
//	}
//	
//	private void integerCandidates(ChainList<MgCall> candidates){
//        ChainList<MgTypename> typenames = new ChainList<>();
//		typenames.addLast(MgBuildinTypename.INT8);
//		typenames.addLast(MgBuildinTypename.INT16);
//		typenames.addLast(MgBuildinTypename.INT32);
//		typenames.addLast(MgBuildinTypename.INT64);
//		typenames.addLast(MgBuildinTypename.UINT8);
//		typenames.addLast(MgBuildinTypename.UINT16);
//		typenames.addLast(MgBuildinTypename.UINT32);
//		typenames.addLast(MgBuildinTypename.UINT64);
//        typenamesToCallCandidates(typenames, candidates);
//	}
//	
//	private void realCandidates(ChainList<MgCall> candidates){
//        ChainList<MgTypename> typenames = new ChainList<>();
//        typenames.addLast(MgBuildinTypename.FLOAT32);
//        typenames.addLast(MgBuildinTypename.FLOAT64);
//        typenamesToCallCandidates(typenames, candidates);
//	}
//	
//	private void stringCandidates(ChainList<MgCall> candidates){
//        ChainList<MgTypename> typenames = new ChainList<>();
//        typenames.addLast(MgBuildinTypename.STRING);
//        typenamesToCallCandidates(typenames, candidates);
//	}
//	
//	private void booleanCandidates(ChainList<MgCall> candidates){
//        ChainList<MgTypename> typenames = new ChainList<>();
//        typenames.addLast(MgBuildinTypename.BOOL8);
//        typenamesToCallCandidates(typenames, candidates);
//	}
//	
//	private void nullCandidates(ChainList<MgCall> candidates){
//		candidates.addLast(new MgLiteralCall(getLocation(), new MgNullLiteralType(), value));
//	}
//	
//	private void typenamesToCallCandidates(ChainList<MgTypename> typenames, ChainList<MgCall> candidates){
//        for(MgTypename typename : typenames){
//			candidates.addLast(new MgLiteralCall(getLocation(), new MgType(typename), value));
//        }
//    }
//}
