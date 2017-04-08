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

import lombok.Data;

/**
 * Created by Дмитрий on 15.05.2015.
 */
@Data
public class ParameterDefine implements Comparable {
	private final Class typeClass;
	private final String name;
	private final String value;

	public ParameterDefine(Class typeClass, String name, String value) {
		this.typeClass = typeClass;
		this.value = value;

		if (name.equals("type"))
			this.name = "_type";
		else
			this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof ParameterDefine && typeClass == ((ParameterDefine) obj).typeClass
				&& name.equals(((ParameterDefine) obj).name);
	}

	@Override
	public int compareTo(Object o) {
		return ((ParameterDefine) o).name.compareTo(this.name);
	}
}
