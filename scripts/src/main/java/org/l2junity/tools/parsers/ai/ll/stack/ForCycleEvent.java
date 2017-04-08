package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.ll.AiClass;

import java.util.Stack;

/**
 * Created by dmitrij on 21.05.15.
 */
public class ForCycleEvent extends SubHandlerBody {
	private final Stack<StackObject> postExpression;
	private final StackObject preExpression;

	public ForCycleEvent(AiClass aiClass, StackObject preExpression, StackObject expression,
						 Stack<StackObject> postExpression) {
		super(aiClass, SubHandlerBody.FOR_TYPE, expression);

		this.preExpression = preExpression;
		this.postExpression = postExpression;
	}

	@Override
	public String toString() {
		String str = "";

		String postStr = "";

		for (int i = 0; i < postExpression.size(); i++) {
			postStr += postExpression.get(i);

			if (i + 1 != postExpression.size()) {
				postStr += ", ";
			}
		}

		postStr = postStr.replaceAll(";", "").trim();

		if (postStr.equals("i1")) postStr = ""; // br_fire_elemental_base#CREATED for cycle bug

		String strExpression = "";
		if (preExpression instanceof VariableInitEvent && getExpression() instanceof ExpressionEvent
				&& (((ExpressionEvent) getExpression()).getOperationType() == ExpressionEvent.LESS
				|| ((ExpressionEvent) getExpression()).getOperationType() == ExpressionEvent.LESS_EQUAL)) {

			strExpression += ((VariableInitEvent) preExpression).getLeft() + " = " + ((VariableInitEvent) preExpression).getRight() + "; ";
			strExpression += ((VariableInitEvent) preExpression).getLeft();

			if (((ExpressionEvent) getExpression()).getOperationType() == ExpressionEvent.LESS)
				strExpression += " < "; // exclusive
			else // LESS_EQUAL
				strExpression += " <= "; // inclusive

			strExpression += ((ExpressionEvent) getExpression()).getRightOperand();

			strExpression += "; " + ((VariableInitEvent) preExpression).getLeft() + "++";

			str = "\t\tfor(" + strExpression + ") {\n";
		} else if (preExpression instanceof VariableInitEvent && getExpression() instanceof ExpressionEvent
				&& ((ExpressionEvent) getExpression()).getOperationType() == ExpressionEvent.EQUAL) {

			strExpression = getExpression().toString();

			str = "\t\t" + preExpression.toString() + "\n";
			str += "\t\twhile(" + strExpression + ") {\n";
		} else {
			System.err.println("Error");
		}


		for (StackObject object : stack) {
			String objectStr = object.toString();
			if (!objectStr.endsWith("}")) {
				str += "\t" + objectStr + ";\n";
			}
		}

		str += "\t\t}\n";

		return str;
	}
}
