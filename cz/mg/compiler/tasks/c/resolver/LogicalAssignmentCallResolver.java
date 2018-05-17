//package cz.mg.compiler.tasks.c.resolver;
//
//
//public class LogicalAssignmentCallResolver {
//    @Override
//	public ChainList<MgCall> getPhysicalCallCandidates(Context context) {
//		ChainList<MgCall> leftArguments = new ChainList<>();
//		for(LogicalCall argument : getNarrowedLeftArguments()){
//			ChainList<MgCall> argumentCandidates = argument.getPhysicalCallCandidates(context);
//			if(argumentCandidates.size() > 1) throw new CompileException(location, "Left arguments of assignment have more than one candidate.");
//			leftArguments.addLast(argumentCandidates.getFirst());
//		}
//		
//		ChainList<ChainList<MgCall>> rightArgumentsCandidates = new ChainList<>();
//		for(LogicalCall argument : getNarrowedRightArguments()){
//			rightArgumentsCandidates.addLast(argument.getPhysicalCallCandidates(context));
//		}
//		
//		return new CallCandidateExplorer(leftArguments, rightArgumentsCandidates).explore();
//	}
//	
//	protected static class CallCandidateExplorer {
//		private final ChainList<MgCall> leftArguments;
//		private final ChainList<ChainList<MgCall>> rightArgumentsCandidates;
//		private final MgVariableGroup requiredInput = new MgVariableGroup();
//
//		public CallCandidateExplorer(ChainList<MgCall> leftArguments, ChainList<ChainList<MgCall>> rightArguments) {
//			this.leftArguments = leftArguments;
//			this.rightArgumentsCandidates = rightArguments;
//			for(MgCall left : leftArguments){
//				for(MgVariable variable : left.getOutput()){
//					requiredInput.add(variable);					
//				}
//			}
//		}
//		
//		public ChainList<MgCall> explore(){
//			ChainList<MgCall> callCandidates = new ChainList<>();
//			explore(rightArgumentsCandidates.getFirstItem(), new MgVariableGroup(), new ChainList<>(), callCandidates);
//			if(callCandidates.size() <= 0) throw new CompileException(location, "No candidates found for assignment.");
//			return callCandidates;
//		}
//
//		private void explore(ChainListItem<ChainList<MgCall>> argumentColumn, MgVariableGroup previousInput, ChainList<MgCall> currentArguments, ChainList<MgCall> callCandidates){
//			for(MgCall currentArgument : argumentColumn.getData()){
//				MgVariableGroup currentInput = currentArgument.getOutput();
//				MgVariableGroup input = MgVariableGroup.unionAll(previousInput, currentInput);
//				if(requiredInput.matches(input)){
//					if(argumentColumn.hasNext()){
//						explore(argumentColumn.getNextItem(), input, ChainList.unionAll(currentArguments, currentArgument), currentArguments);
//					} else if(requiredInput.size() == input.size()) {
//						callCandidates.addLast(new MgAssignmentCall(location, leftArguments, ChainList.unionAll(currentArguments, currentArgument)));
//					}
//				}
//			}
//		}
//	}
//	
//	private ChainList<LogicalCall> getNarrowedLeftArguments() {
//        ChainList<LogicalCall> narrowedArguments = new ChainList<>();
//        narrow(narrowedArguments, leftArguments);
//        return narrowedArguments;
//    }
//	
//	private ChainList<LogicalCall> getNarrowedRightArguments() {
//        ChainList<LogicalCall> narrowedArguments = new ChainList<>();
//        narrow(narrowedArguments, rightArguments);
//        return narrowedArguments;
//    }
//	
//	private void narrow(ChainList<LogicalCall> narrowedArguments, LogicalGroupCall group){
//		for(LogicalCall call : group){
//			if(call instanceof LogicalGroupCall){
//				narrow(narrowedArguments, (LogicalGroupCall) call);
//			} else {
//				narrowedArguments.addLast(call);
//			}
//		}
//	}
//}
