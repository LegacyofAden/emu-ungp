package org.l2junity.tools.parsers.ai.model;

import java.util.ArrayList;
import java.util.List;

public class DBClassInfo {
	private String name;

	private String parent;

	private List<String> parameters = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
}
