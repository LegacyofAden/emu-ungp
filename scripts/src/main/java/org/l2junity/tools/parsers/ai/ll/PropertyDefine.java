/*
 * Copyright 2015-2017 JTS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.l2junity.tools.parsers.ai.ll;

import java.util.List;

/**
 * Created by dmitrij on 24.05.15.
 */
public class PropertyDefine {
	private final String propertyName;
	private final List<Object> objects;
	private final Class clazz;
	private boolean override;

	public PropertyDefine(String propertyName, List<Object> objects, Class clazz) {
		this.propertyName = propertyName;
		this.objects = objects;
		this.clazz = clazz;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Class getClazz() {
		return clazz;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (!override) {
			str.append("\tprotected static ").append(clazz.getSimpleName()).append("[] ").append(propertyName).append(" = new ").append(clazz.getSimpleName()).append("[").append(objects.size()).append("];\n");
		}

		if (objects.size() > 0) {
			str.append("\tstatic {\n");
			if (override) {
				str.append("\t\t").append(propertyName).append(" = new ").append(clazz.getSimpleName()).append("[").append(objects.size()).append("];\n");
			}
			for (int index = 0; index < objects.size(); index++) {
				str.append("\t\t").append(propertyName).append("[").append(index).append("] = ").append(objects.get(index).toString()).append(";\n");
			}
			str.append("\t}");
		}

		str.append("\n");

		return str.toString();
	}

	public void override() {
		this.override = true;
	}
}
