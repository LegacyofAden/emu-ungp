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

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.retail.ai.MakerEventHandler;
import org.l2junity.gameserver.retail.ai.NpcEventHandler;
import org.l2junity.tools.parsers.ai.class_model.MethodParameter;
import org.l2junity.tools.parsers.ai.model.PlainClassData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Camellion, ANZO
 * @since 15.05.2015
 */
@Slf4j
public class AiClass {
	public final int type;
	private final String parent;
	private String className;
	public int handler;
	public EventHandler eventHandler;
	public TreeSet<ParameterDefine> parameterDefines;
	public List<PropertyDefine> propertyDefines;
	private List<EventHandler> eventHandlers;
	private Set<String> imports;

	public AiClass(PlainClassData classData) {
		this.type = classData.getType();
		this.className = classData.getClassName();
		this.eventHandlers = new ArrayList<>();
		this.parameterDefines = new TreeSet<>();
		this.propertyDefines = new ArrayList<>();
		this.imports = new HashSet<>();

		if (classData.getParent() == null) {
			this.parent = type == 0 ? NpcEventHandler.class.getSimpleName() : MakerEventHandler.class.getSimpleName();
			if (this.parent.equals(NpcEventHandler.class.getSimpleName())) {
				imports.add(NpcEventHandler.class.getName());
			} else {
				imports.add(MakerEventHandler.class.getName());
			}
		} else {
			this.parent = classData.getParent();
		}
	}

	public String getParent() {
		return parent;
	}

	public String getClassName() {
		return className;
	}

	public void writeToFile() throws IOException {
		String packagePath = type == 0 ? "org.l2junity.gameserver.retail.ai.npc" : "ru.jts.gameserver.ai.maker";

		String filePrefix = type == 0 ? "src/main/java/org/l2junity/gameserver/retail/ai/npc/" : "src/main/java/org/l2junity/gameserver/retail/ai/maker/";

		if (className.equals("object"))
			className = "_object";

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filePrefix + className + ".java")))) {
			bufferedWriter.write("package " + packagePath + ";\n\n");

			for (String import_ : imports) {
				bufferedWriter.write("import " + import_ + ";\n");
			}
			bufferedWriter.write("import org.l2junity.gameserver.retail.AiClass;\n");

			if (imports.size() > 0)
				bufferedWriter.write("\n");

			bufferedWriter.write("@AiClass(\"" + className + "\")");
			bufferedWriter.write("public class " + className + " extends " + parent + " {\n");

			// parameter defines

			for (ParameterDefine parameterDefine : parameterDefines) {
				bufferedWriter.write("\t" + parameterDefine.getTypeClass().getSimpleName() + " " + parameterDefine.getName() + " = " + parameterDefine.getValue() + ";\n\n");
			}

			// property defines

			for (PropertyDefine propertyDefine : propertyDefines) {
				bufferedWriter.write(propertyDefine.toString() + "\n");
			}

			for (int i = 0; i < eventHandlers.size(); i++) {
				EventHandler eventHandler = eventHandlers.get(i);

				eventHandler.writeToFile(bufferedWriter);

				if (i + 1 != eventHandlers.size()) {
					bufferedWriter.write("\n");
				}
			}

			// class body

			bufferedWriter.write("}");

			bufferedWriter.flush();
		} catch (Exception e) {
			log.error("Error while writing to disk class=[{}]", className, e);
		}
	}

	public void setParameterDefines(TreeSet<ParameterDefine> parameterDefines) {
		this.parameterDefines = parameterDefines;
	}

	public void addEventHandler(EventHandler eventHandler) {
		eventHandlers.add(eventHandler);
	}

	public void setPropertyDefines(List<PropertyDefine> propertyDefines) {
		this.propertyDefines = propertyDefines;
	}

	public void restoreImports() {
		if (parent == null) {
			imports.add(type == 0 ? NpcEventHandler.class.getName() : MakerEventHandler.class.getName());
		}

		for (ParameterDefine parameter : parameterDefines) {
			if (parameter.getTypeClass() != int.class && parameter.getTypeClass() != double.class
					&& parameter.getTypeClass() != String.class && parameter.getTypeClass() != long.class) {
				imports.add(parameter.getTypeClass().getName());
			}
		}

		if (propertyDefines.size() > 0) {
			for (PropertyDefine property : propertyDefines) {
				imports.add(property.getClazz().getName());
			}
		}


		for (EventHandler eventHandler : eventHandlers) {
			for (MethodParameter parameter : eventHandler.getMethod().getParameters().values()) {
				if (parameter.type != int.class && parameter.type != double.class && parameter.type != String.class) {
					imports.add(parameter.type.getName());
				}
			}
		}
	}

	public void addStaticImport(String _import) {
		imports.add(_import);
	}
}
