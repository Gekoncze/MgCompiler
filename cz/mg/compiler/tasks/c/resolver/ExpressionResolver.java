//package cz.mg.compiler.tasks.c.resolver;
//
//
//public class ExpressionResolver {
//    private void evaluate(Context context, MgVariableGroup expectedOutput){
//		ChainList<MgCall> candidates = rootLogical.getPhysicalCallCandidates(context);
//		ChainList<MgCall> acceptedCandidates = new ChainList<>();
//		
//		for(MgCall candidate : candidates){
//			if(candidate.getOutput().matches(expectedOutput)) acceptedCandidates.addLast(candidate);
//		}
//		
//		if(acceptedCandidates.size() > 1) throw new CompileException(getLocation(), "Too many candidates found for call ", rootLogical, ".");
//		if(acceptedCandidates.size() < 1) throw new CompileException(getLocation(), "No candidates found for call ", rootLogical, ".");
//		
//		rootPhysical = acceptedCandidates.getFirst();
//	}
//}
