package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.class_model.ClassMethod;
import org.l2junity.tools.parsers.ai.ll.AiClass;

import java.util.List;

/**
 * Created by Дмитрий on 16.05.2015.
 */
public class FuncCallEvent extends EventObject {
	private final ClassMethod method;
	private final List<StackObject> params;

	public FuncCallEvent(AiClass aiClass, int eventId, EventObject parent, ClassMethod method, List<StackObject> params) {
		super(aiClass, eventId, parent);
		this.method = method;
		this.params = params;

		this.eventType = method.getReturnType();
	}

	@Override
	public String toString() {
		String str = parent.toString() + ".";

		String methodParams = "";

		for (int i = 0; i < params.size(); i++) {
			methodParams += params.get(i).toString();

			if (i + 1 != params.size()) methodParams += ", ";
		}
		str += this.method.getName() + "(" + methodParams + ")";

		return str;
	}
}
