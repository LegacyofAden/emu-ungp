package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.ll.AiClass;

/**
 * Created by Дмитрий on 16.05.2015.
 */
public class IntObject extends StackObject {
	private final long value;
	private boolean not;

	public IntObject(AiClass aiClass, long value) {
		super(aiClass);
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	@Override
	public String toString() {
		String str = String.valueOf(value);
		if (value > Integer.MAX_VALUE) {
			str += "L";
		}

		if (not)
			str = "~" + str;

		return str;
	}

	public IntObject negate() {
		return new IntObject(aiClass, -value);
	}

	public StackObject not() {
		this.not = true;
		return this;
	}
}
