package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.ll.AiClass;

/**
 * Created by Дмитрий on 16.05.2015.
 */
public class ParameterObject extends StackObject {
	private final String parameterName;

	public ParameterObject(AiClass aiClass, String parameterName) {
		super(aiClass);
		if (parameterName.equals("type"))
			this.parameterName = "_type";
		else
			this.parameterName = parameterName;
	}

	@Override
	public String toString() {
		return parameterName;
	}
}
