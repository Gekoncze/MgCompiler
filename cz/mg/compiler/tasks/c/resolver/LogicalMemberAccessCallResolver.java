//package cz.mg.compiler.tasks.c.resolver;
//
//
//public class LogicalMemberAccessCallResolver {
//    @Override
//	public ChainList<MgCall> getPhysicalCallCandidates(Context context) {
//        ChainList<MgCall> leftArguments = left.getPhysicalCallCandidates(context);
//        if(leftArguments.size() != 1){
//            if(leftArguments.size() < 1) throw new CompileException(location, "No candidates for left operand of member access.");
//            if(leftArguments.size() > 1) throw new CompileException(location, "Too many candidates for left operand of member access.");
//        }
//        
//        MgCall parent = leftArguments.getFirst();
//		
//        MgVariableGroup parentOutput = parent.getOutput();
//        if(parentOutput.size() != 1){
//            if(parentOutput.size() < 1) throw new CompileException(location, "Left operand for member access does not return value.");
//            if(parentOutput.size() > 1) throw new CompileException(location, "Left operand for member access returns multiple values.");
//        }
//        
//        MgTypename typename = parentOutput.getVariables().getFirst().getType().getTypename();
//        context = new Context(typename);
//		
//		ChainList<MgCall> candidates = new ChainList<>();
//		for(MgCall member : right.getPhysicalCallCandidates(context)){
//			candidates.addLast(new MgMemberAccessCall(location, parent, member));
//		}
//		
//        return candidates;
//	}
//}
