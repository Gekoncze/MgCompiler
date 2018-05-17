//package cz.mg.compiler.tasks.c.resolver;
//
//import cz.mg.collections.ChainList;
//import cz.mg.collections.ChainListItem;
//import cz.mg.compiler.entities.b.logical.source.calls.LogicalCall;
//import cz.mg.compiler.composer.objects.calls.LogicalGroupCall;
//import cz.mg.compiler.entities.c.mg.MgFunction;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.compiler.entities.c.mg.MgVariable;
//import cz.mg.compiler.entities.c.mg.MgVariableGroup;
//import cz.mg.compiler.entities.c.mg.calls.MgCall;
//import cz.mg.compiler.entities.c.mg.calls.MgFunctionCall;
//import cz.mg.compiler.entities.c.mg.calls.MgVariableCall;
//import cz.mg.compiler.tasks.c.resolver.filters.NameFilter;
//
//
//public class LogicalNameCallResolver {
//    @Override
//	public ChainList<MgCall> getPhysicalCallCandidates(Context context) {
//		ChainList<MgCall> physicalCandidates = new ChainList<>();
//		ChainList<MgObject> candidates = new ChainList<MgObject>();
//		context.findObjects(candidates, new NameFilter(getName()));
//		
//		if(arguments == null){
//			for(MgObject candidate : candidates){
//				if(candidate instanceof MgVariable){
//					MgVariable variableCandidate = (MgVariable) candidate;
//					physicalCandidates.addLast(new MgVariableCall(getLocation(), variableCandidate));
//				}
//				if(candidate instanceof MgFunction){
//					MgFunction functionCandidate = (MgFunction) candidate;
//					if(functionCandidate.getOutput().size() == 0) physicalCandidates.addLast(new MgFunctionCall(getLocation(), functionCandidate, null));
//				}
//			}
//		} else {
//			ChainList<ChainList<MgCall>> argumentsCandidates = new ChainList<>();
//			for(LogicalCall argument : getNarrowedArguments()){
//				argumentsCandidates.addLast(argument.getPhysicalCallCandidates(context));
//			}
//			
//			for(MgObject candidate : candidates){
//				if(candidate instanceof MgVariable){
//					//throw new CompileException(this, candidate.getClassName(), " does not accept any arguments.");
//					continue;
//				}
//				if(candidate instanceof MgFunction){
//					new CallCandidateExplorer(getLocation(), (MgFunction)candidate, physicalCandidates).explore(
//							argumentsCandidates.getFirstItem(),
//							new MgVariableGroup(),
//							new ChainList<>()
//					);
//				}
//			}
//		}
//		
//		if(physicalCandidates.size() <= 0) throw new CompileException(this, "No matching variable or function found. Candidates are: \n", candidates);
//		return physicalCandidates;
//	}
//	
//	private ChainList<LogicalCall> getNarrowedArguments() {
//        ChainList<LogicalCall> narrowedArguments = new ChainList<>();
//        narrow(narrowedArguments, arguments);
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
//	
//	protected static class CallCandidateExplorer {
//		private final MgFunction function;
//		private final ChainList<MgCall> acceptedCandidates;
//		private final Location location;
//
//		public CallCandidateExplorer(Location location, MgFunction function, ChainList<MgCall> acceptedCandidates) {
//			this.location = location;
//			this.function = function;
//			this.acceptedCandidates = acceptedCandidates;
//		}
//
//		public void explore(ChainListItem<ChainList<MgCall>> argumentColumn, MgVariableGroup previousInput, ChainList<MgCall> acceptedArguments){
//			for(MgCall currentArgument : argumentColumn.getData()){
//				MgVariableGroup currentInput = currentArgument.getOutput();
//				MgVariableGroup input = MgVariableGroup.unionAll(previousInput, currentInput);
//				MgVariableGroup requiredInput = function.getInput();
//				if(requiredInput.matches(input)){
//					if(argumentColumn.hasNext()){
//						explore(argumentColumn.getNextItem(), input, ChainList.unionAll(acceptedArguments, currentArgument));
//					} else if(requiredInput.size() == input.size()) {
//						acceptedCandidates.addLast(new MgFunctionCall(location, function, ChainList.unionAll(acceptedArguments, currentArgument)));
//					}
//				}
//			}
//		}
//	}
//}
