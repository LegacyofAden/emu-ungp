package org.l2junity.tools.parsers.ai.class_model;

import org.l2junity.gameserver.retail.AiEventId;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by dmitrij on 15.06.15.
 */
public class ClassMethod {
	public Method method;
	public Map<Integer, MethodParameter> parameters = new HashMap<>();
	public List<MethodParameter> sortedParameters = new ArrayList<>();

	public ClassMethod(Method method) {
		this.method = method;
	}

	public Map<Integer, MethodParameter> getParameters() {
		return parameters;
	}

	public String getName() {
		return method.getName();
	}

	public List<ClassInfo> process() {
		List<ClassInfo> classInfos = new ArrayList<>();
		for (Parameter parameter : method.getParameters()) {
			AiEventId eventId = parameter.getAnnotation(AiEventId.class);

			MethodParameter methodParameter;
			if (eventId != null) {
				methodParameter = new MethodParameter(eventId.value(), eventId.name(), parameter.getName(), parameter.getType());
				parameters.put(eventId.value(), methodParameter);
			} else {
				methodParameter = new MethodParameter(parameter.getName(), parameter.getType());
				parameters.put(-1 - parameters.size(), methodParameter);
			}
			sortedParameters.add(methodParameter);
			classInfos.addAll(methodParameter.process());
		}

		return classInfos;
	}

	public Class getReturnType() {
		return method.getReturnType();
	}

	public MethodParameter findParameter(int eventId) {
		if (parameters.containsKey(eventId))
			return parameters.get(eventId);
		return null;
	}

	public Collection<MethodParameter> getSortedParameters() {
		return sortedParameters;
	}
}
