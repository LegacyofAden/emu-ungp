package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.ll.AiClass;

/**
 * Created by dmitrij on 24.05.15.
 */
public class BreakEvent extends StackObject {
	public BreakEvent(AiClass aiClass) {
		super(aiClass);
	}

	@Override
	public String toString() {
		return "\t\t\tbreak;\n";
	}
}
