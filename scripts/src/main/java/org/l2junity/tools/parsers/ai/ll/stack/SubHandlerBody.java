package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.ll.AiClass;

import java.util.Stack;

/**
 * Created by Дмитрий on 17.05.2015.
 */
public class SubHandlerBody extends StackObject {
	public static final int IF_TYPE = 0;
	public static final int ELSE_IF_TYPE = 1;
	public static final int ELSE_TYPE = 2;
	public static final int SWITCH_TYPE = 3;
	public static final int CASE_TYPE = 4;
	public static final int FOR_TYPE = 5;
	public static final int WHILE_TYPE = 6;

	public final Stack<StackObject> stack;
	private final StackObject expression;
	private int handlerType;

	public SubHandlerBody(AiClass aiClass, int handlerType, StackObject expression) {
		super(aiClass);

		this.expression = expression;
		this.stack = new Stack<>();
		this.handlerType = handlerType;
	}

	@Override
	public String toString() {
		String str = "";
		switch (handlerType) {
			case IF_TYPE:
				str = "\t\tif (" + expression.toString() + ") {\n";
				break;
			case ELSE_IF_TYPE:
				str = "\t\telse if (" + expression.toString() + ") {\n";
				break;
			case ELSE_TYPE:
				str = "\t\telse {\n";
				break;
			case SWITCH_TYPE:
				str = "\t\tswitch(" + expression.toString() + ") {\n";
				break;
			case CASE_TYPE:
				str = "\t\t\tcase " + expression.toString() + ": {\n";
				break;
			case WHILE_TYPE:
				String expressionString = expression.toString();

				if (expression instanceof EventObject) {
					EventObject eventObject = (EventObject) expression;
					if (eventObject.getEventType() == int.class) {
						expressionString = expressionString + " == 1";
					}
				}
				str = "\t\twhile(" + expressionString + ") {\n";
				break;
		}

		// TODO: If stack is empty or contain trash like code_info.code - remove this body
		int stackIndex = 0;
		for (StackObject object : stack) {
			// bug with myself.i_ai1 == 0;
			if (object instanceof ExpressionEvent && ((ExpressionEvent) object).getOperationType() == ExpressionEvent.EQUAL) {
				object = new VariableInitEvent(object.aiClass, ((ExpressionEvent) object).getLeftOperand(), ((ExpressionEvent) object).getRightOperand(),
						VariableInitEvent.EXPRESSION_TYPE);
			}

			if (object instanceof BreakEvent && stackIndex > 0) {
				StackObject prevStackObject = stack.get(stackIndex - 1);
				if (prevStackObject != null) {
					if (prevStackObject instanceof SubHandlerBody && ((SubHandlerBody) prevStackObject).getType() == ELSE_TYPE) {
						SubHandlerBody elseBody = (SubHandlerBody) prevStackObject;
						if (elseBody.stack.lastElement() instanceof ReturnEvent) {
							stackIndex++;
							continue;
						}
					}
				}
			}

			String objectStr = object.toString();
			String comment = "";
			if (object instanceof EventObject
					&& ((EventObject) object).getEventId() == 52
					&& ((EventObject) object).parent.getEventId() == 792) // code_info.code
				comment = "//";

			str += "\t\t\t" + comment + objectStr;

			if (object instanceof VariableInitEvent || comment.length() > 0
					|| (object instanceof FuncCallEvent && !objectStr.endsWith(";\n")))
				str += ";\n";
			else {
				str += "\n";
			}
			stackIndex++;
		}

		str += "\t\t}";
		return str;
	}

	public StackObject getExpression() {
		return expression;
	}

	public int getType() {
		return handlerType;
	}

	public void setType(int handlerType) {
		this.handlerType = handlerType;
	}
}
