package org.l2junity.tools.parsers.ai.ll.stack;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.retail.ai.MakerEventHandler;
import org.l2junity.gameserver.retail.ai.NpcEventHandler;
import org.l2junity.tools.parsers.ai.class_model.ClassContainer;
import org.l2junity.tools.parsers.ai.class_model.ClassField;
import org.l2junity.tools.parsers.ai.class_model.ClassMethod;
import org.l2junity.tools.parsers.ai.class_model.MethodParameter;
import org.l2junity.tools.parsers.ai.ll.AiClass;

import java.util.List;
import java.util.Stack;

/**
 * Created by Дмитрий on 15.05.2015.
 */
@Slf4j
public class EventObject extends StackObject {
	private final int eventId;
	public MethodParameter parameter;
	public Class eventType;
	protected EventObject parent;
	String name;
	private boolean negate;

	public EventObject(AiClass aiClass, int eventId, EventObject parent) {
		super(aiClass);
		this.eventId = eventId;
		this.parent = parent;

		appendName();
	}


	private void appendName() {

		Class clazz = NpcEventHandler.class;
		if (aiClass.type == 1) {
			clazz = MakerEventHandler.class;
		}

		if (parent != null) {
			clazz = parent.eventType;
		}

		if (!(this instanceof FuncCallEvent)) {
			ClassContainer classContainer = ClassContainer.getInstance();

			ClassField field = classContainer.findField(eventId, clazz);

			if (field == null) {
				ClassMethod classMethod = classContainer.findMethod(aiClass.handler, clazz);
				if (classMethod == null) {
					log.error("Can't find class eventId " + eventId + " in file " + aiClass.getClassName());
					return;
				}
				MethodParameter parameter = classMethod.findParameter(eventId);
				if (parameter == null) {
					log.error("Can't find class method parameter with id=" + eventId + " in file " + aiClass.getClassName() + " in handler " + classMethod.getName());
					return;
				}
				String pName = parameter.eventName;
				if (pName.equals("private")) {
					pName = "_private";
				}
				name = pName;
				eventType = parameter.type;
				this.parameter = parameter;
			} else {
				String fName = field.getEventName();
				if (fName.equals("type")) {
					fName = "_type";
				}
				name = fName;
				eventType = field.getType();
			}
		}
	}

	public int getEventId() {
		return eventId;
	}

	public Class getEventType() {
		return eventType;
	}

	public EventObject addSubEvent(int subEventId) {
		return new EventObject(aiClass, subEventId, this);
	}

	public ClassMethod getFunc(int eventId) {
		ClassContainer classContainer = ClassContainer.getInstance();

		ClassMethod classMethod = classContainer.findMethod(eventId, eventType);

		return classMethod;
	}

	public FuncCallEvent addFuncWith(int eventId, ClassMethod method, List<StackObject> params) {
		return new FuncCallEvent(aiClass, eventId, this, method, params);
	}

	@Override
	public String toString() {
		String str = "";
		Stack<EventObject> tempStack = new Stack<>();
		EventObject parent = this.parent;

		while (parent != null) {
			tempStack.push(parent);
			parent = parent.parent;
		}

		while (tempStack.size() > 0) {
			str += tempStack.pop().getName();
			str += ".";
		}
		str += this.getName();
		if (negate)
			str = "-" + str;

		return str;
	}

	public String getName() {
		String eventName = aiClass.eventHandler.getEventName(parameter);
		if (eventName != null) return eventName;
		return name;
	}

	public EventObject negate() {
		this.negate = !negate;
		return this;
	}
}
