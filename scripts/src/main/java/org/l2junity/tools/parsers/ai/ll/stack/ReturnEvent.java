package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.ll.AiClass;

/**
 * Created by Дмитрий on 16.05.2015.
 */
public class ReturnEvent extends StackObject {
	public ReturnEvent(AiClass aiClass) {
		super(aiClass);
	}

	@Override
	public String toString() {
		return "\t\treturn;\n";
	}
}
