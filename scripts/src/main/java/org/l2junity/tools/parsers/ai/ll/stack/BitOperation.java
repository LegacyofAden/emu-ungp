package org.l2junity.tools.parsers.ai.ll.stack;

/**
 * Created by dmitrij on 31.05.15.
 */
public class BitOperation extends StackObject {
	private static final int AND = 0;
	private static final int OR = 1;

	private StackObject owner;

	private int operationType;
	private StackObject rightOperand;
	private boolean not;

	private boolean braces;

	public BitOperation(StackObject owner) {
		super(owner.aiClass);
		this.owner = owner;
	}

	public static BitOperation or(StackObject left, StackObject right) {
		BitOperation event = new BitOperation(left);

		event.operationType = OR;
		event.rightOperand = right;

		return event;
	}

	public static BitOperation and(StackObject left, StackObject right) {
		BitOperation event = new BitOperation(left);

		event.operationType = AND;
		event.rightOperand = right;

		return event;
	}

	@Override
	public String toString() {
		String operator = "";
		switch (operationType) {
			case AND:
				operator = "&";
				break;
			case OR:
				operator = "|";
				break;
		}

		String str = owner + " " + operator + " " + rightOperand;

		if (not)
			str = "~" + "(" + str + ")";

		if (braces)
			str = "(" + str + ")";

		return str;
	}

	public StackObject not() {
		this.not = !this.not;
		return this;
	}

	public void setBraces(boolean braces) {
		this.braces = braces;
	}
}
