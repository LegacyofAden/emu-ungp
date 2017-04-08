package org.l2junity.tools.parsers.ai.ll;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.retail.ai.MakerEventHandler;
import org.l2junity.gameserver.retail.ai.NpcEventHandler;
import org.l2junity.tools.parsers.ai.class_model.ClassContainer;
import org.l2junity.tools.parsers.ai.class_model.ClassField;
import org.l2junity.tools.parsers.ai.class_model.ClassMethod;
import org.l2junity.tools.parsers.ai.class_model.MethodParameter;
import org.l2junity.tools.parsers.ai.ll.stack.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * Created by dmitrij on 15.05.2015.
 */
@Slf4j
public class EventHandler {
	private final int type;
	private final int eventId;
	private final AiClass aiClass;

	public Stack<StackObject> stack;
	private Set<MethodParameter> usedParameters = new HashSet<>();

	private ClassMethod method;
	private Class eventEventClass;

	public EventHandler(int type, int eventId, AiClass aiClass) {
		this.type = type;
		this.eventId = eventId;
		this.aiClass = aiClass;
		this.stack = new Stack<>();
		if (type == 1) {
			eventEventClass = MakerEventHandler.class;
		} else {
			eventEventClass = NpcEventHandler.class;
		}

		method = ClassContainer.getInstance().findMethod(eventId, eventEventClass);
		if (method == null) {
			log.warn("EventHandler: Not found method for eventId={} in class={}", eventId, eventEventClass.getSimpleName());
		}
	}

	public void writeToFile(BufferedWriter bufferedWriter) throws IOException {
		String parameters = "";

		Iterator<MethodParameter> it = method.getSortedParameters().iterator();
		while (it.hasNext()) {
			MethodParameter p = it.next();

			String name = p.eventName;

			parameters += p.type.getSimpleName() + " " + (name.equals("private") ? "_" + name : name);

			if (it.hasNext()) {
				parameters += ",\n\t\t\t\t\t\t\t";
			}
		}

		bufferedWriter.write("\t@Override\n");
		bufferedWriter.write("\tpublic void " + method.getName() + "(" + parameters + ") {\n");

		String stackStr = "";

		int stackIndex = 0;

		for (StackObject object : stack) {
			// Если return посреди метода, не парсим все что дальше
			if (object instanceof ReturnEvent) {
				if (stack.size() != stackIndex) {
					break;
				}
			}
			// bug with myself.i_ai1 == 0;
			if (object instanceof ExpressionEvent && ((ExpressionEvent) object).getOperationType() == ExpressionEvent.EQUAL) {
				object = new VariableInitEvent(aiClass, ((ExpressionEvent) object).getLeftOperand(), ((ExpressionEvent) object).getRightOperand(),
						VariableInitEvent.EXPRESSION_TYPE);
			}

			String str = object.toString();
			if (object instanceof VariableInitEvent || (object instanceof FuncCallEvent && !str.endsWith(";\n")))
				str += ";\n";
			stackStr += str + "\n";
			stackIndex++;
		}

		if (usedParameters.size() > 0)
			bufferedWriter.write("\n");

		bufferedWriter.write(stackStr);

		bufferedWriter.write("\t}\n");
	}

	public void addVariable(String var) {
		if (var.equals("myself") ||
				!(method.getName().equals("TALKED") || method.getName().equals("TALK_SELECTED")) &&
						(var.equals("_choiceN") || var.equals("_code") || var.equals("_from_choice")))
			return;

		for (MethodParameter parameter : method.getParameters().values()) {
			if (parameter.realName.equals(var) || parameter.eventName.equals(var))
				return;
		}

		ClassField field = ClassContainer.getInstance().findField(var, eventEventClass);
		if (field != null) {
			return;
		}

		// NASC BUG and not using variables
		if (var.equals("talke")) {
			return;
		}
		if (var.equals("damege")) {
			return;
		}
		if (var.equals("need_quest")) {
			return;
		}
		log.warn("Can't find definition for variable {} in method {} in class {}", var, method.getName(), aiClass.getClassName());
	}

	public ClassMethod getMethod() {
		return method;
	}

	public void addVarCall(MethodParameter parameter) {
		usedParameters.add(parameter);
	}

	public String getEventName(MethodParameter parameter) {
		if (usedParameters.contains(parameter)) {
			return /*"_" + */parameter.eventName;
		}
		return null;
	}
}
