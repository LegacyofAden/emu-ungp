package org.l2junity.tools.parsers.ai.ll.stack;

import org.l2junity.tools.parsers.ai.class_model.ClassMethod;
import org.l2junity.tools.parsers.ai.class_model.MethodParameter;
import org.l2junity.tools.parsers.ai.ll.AiClass;

import java.util.Iterator;

/**
 * Created by Дмитрий on 18.05.2015.
 */
public class CallSuperEvent extends StackObject {
	private final ClassMethod method;

	public CallSuperEvent(AiClass aiClass, ClassMethod method) {
		super(aiClass);
		this.method = method;
	}

	@Override
	public String toString() {
		String parameters = "";

		Iterator<MethodParameter> it = method.getSortedParameters().iterator();
		while (it.hasNext()) {
			MethodParameter p = it.next();

			String name = p.eventName;
			if (name.equals("private")) {
				name = "_private";
			}
			parameters += name;

			if (it.hasNext()) parameters += ", ";
		}

		return "\t\tsuper." + method.getName() + "(" + parameters + ");\n";
	}
}
