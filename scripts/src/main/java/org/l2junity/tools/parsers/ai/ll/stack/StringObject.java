package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.ll.AiClass;

/**
 * Created by Дмитрий on 16.05.2015.
 */
public class StringObject extends StackObject {
	private final String str;

	public StringObject(AiClass aiClass, String str) {
		super(aiClass);
		this.str = str.replace("\\", "\\\\");
	}

	@Override
	public String toString() {
		return str;
	}
}
