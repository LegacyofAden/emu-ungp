package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.ll.AiClass;

/**
 * Created by dmitrij on 21.05.15.
 */
public class BooleanObject extends StackObject {
	private final boolean value;

	public BooleanObject(AiClass aiClass, boolean value) {
		super(aiClass);
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
