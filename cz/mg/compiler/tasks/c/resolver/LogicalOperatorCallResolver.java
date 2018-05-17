//package cz.mg.compiler.tasks.c.resolver;
//
//import cz.mg.compiler.entities.c.mg.MgOperator;
//import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;
//
//
//public class LogicalOperatorCallResolver {
//    @Override
//	public ChainList<MgCall> getPhysicalCallCandidates(Context context) {
//		ChainList<MgObject> candidates = new ChainList<MgObject>();
//		context.findObjects(candidates, new OperatorFilter(typeString, type));
//		ChainList<MgCall> leftCandidates = leftArgument.getPhysicalCallCandidates(context);
//		ChainList<MgCall> rightCandidates = rightArgument.getPhysicalCallCandidates(context);
//		ChainList<MgCall> physicalCandidates = new ChainList<>();
//		for(MgObject candidate : candidates){
//			MgOperator operatorCandidate = (MgOperator) candidate;
//			for(MgCall leftCandidate : leftCandidates){
//				if(leftCandidate.getOutput().size() != 1) continue;
//				MgType leftType = leftCandidate.getOutput().getVariables().getFirst().getType();
//				if(!leftType.matches(operatorCandidate.getLeft().getType())) continue;
//				for(MgCall rightCandidate : rightCandidates){
//					if(rightCandidate.getOutput().size() != 1) continue;
//					MgType rightType = rightCandidate.getOutput().getVariables().getFirst().getType();
//					if(!rightType.matches(operatorCandidate.getRight().getType())) continue;
//					physicalCandidates.addLast(new MgOperatorCall(getLocation(), operatorCandidate, leftCandidate, rightCandidate));
//				}
//			}
//		}
//		if(physicalCandidates.size() <= 0) throw new CompileException(this, "No matching operator found. Candidates are: \n", candidates);
//		return physicalCandidates;
//	}
//    
//    private static MgOperator.Type operatorToType(OperatorToken.Operator operator){
//		switch(operator){
//			case INCREMENT: return MgOperator.Type.INCREMENT;
//			case DECREMENT: return MgOperator.Type.DECREMENT;
//			case ADDRESS: return MgOperator.Type.ADDRESS;
//			case DEREFERENCE: return MgOperator.Type.DEREFERENCE;
//			case FACTORIAL: return MgOperator.Type.FACTORIAL;
//			case POWER: return MgOperator.Type.POWER;
//			case MULTIPLY: return MgOperator.Type.MULTIPLY;
//			case INTEGER_DIVIDE:  return MgOperator.Type.INTEGER_DIVIDE;
//			case MODULO: return MgOperator.Type.MODULO;
//			case REAL_DIVIDE: return MgOperator.Type.REAL_DIVIDE;
//			case PLUS: return MgOperator.Type.PLUS;
//			case MINUS: return MgOperator.Type.MINUS;
//			case BITSHIFT_LEFT: return MgOperator.Type.BITSHIFT_LEFT;
//			case BITSHIFT_RIGHT: return MgOperator.Type.BITSHIFT_RIGHT;
//			case AND: return MgOperator.Type.AND;
//			case NAND: return MgOperator.Type.NAND;
//			case XOR: return MgOperator.Type.XOR;
//			case XNOR: return MgOperator.Type.XNOR;
//			case OR: return MgOperator.Type.OR;
//			case NOR: return MgOperator.Type.NOR;
//			case SMALLER: return MgOperator.Type.SMALLER;
//			case LARGER: return MgOperator.Type.LARGER;
//			case SMALLER_OR_EQUAL: return MgOperator.Type.SMALLER_OR_EQUAL;
//			case LARGER_OR_EQUAL: return MgOperator.Type.LARGER_OR_EQUAL;
//			case EQUAL: return MgOperator.Type.EQUAL;
//			case NOT: return MgOperator.Type.NOT;
//			case AS: return MgOperator.Type.AS;
//			case IS: return MgOperator.Type.IS;
//			default: throw new RuntimeException("Unknown operator " + operator);
//		}
//	}
//}
