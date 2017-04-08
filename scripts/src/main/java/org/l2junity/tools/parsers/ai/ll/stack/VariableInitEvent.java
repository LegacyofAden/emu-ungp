package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.ll.AiClass;

/**
 * Created by dmitrij on 20.05.15.
 */
public class VariableInitEvent extends StackObject {
	public static final int EXPRESSION_TYPE = 0;
	public static final int INCREMENT_TYPE = 1;
	public static final int DECREMENT_TYPE = 2;
	private final int varType;
	private StackObject left;
	private StackObject right;

	public VariableInitEvent(AiClass aiClass, StackObject left, StackObject right, int varType) {
		super(aiClass);
		this.left = left;
		this.right = right;
		this.varType = varType;

		if (left instanceof EventObject && ((EventObject) left).parameter != null) {
			//((EventObject) left).name = "_" + ((EventObject) left).name;
			aiClass.eventHandler.addVarCall(((EventObject) left).parameter);
		}

		if (left instanceof EventObject && right instanceof VariableInitEvent
				&& ((VariableInitEvent) right).getLeft() instanceof EventObject
				&& ((EventObject) ((VariableInitEvent) right).getLeft()).getEventId() == ((EventObject) left).getEventId()) {
			this.left = ((VariableInitEvent) right).getLeft();
			this.right = ((VariableInitEvent) right).getRight();
		}
	}

	public StackObject getLeft() {
		return left;
	}

	public StackObject getRight() {
		return right;
	}

	@Override
	public String toString() {
		String str = "";
		switch (varType) {
			case EXPRESSION_TYPE:
				str = "\t\t" + left + " = " + right;
				break;
			case INCREMENT_TYPE:
				str = left + " += 1;";
				break;
			case DECREMENT_TYPE:
				str = left + " -= 1;";
				break;
			default:
				System.err.println("Unknown varType=" + varType);
				break;
		}

		return str;
	}
}
