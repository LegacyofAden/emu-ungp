package org.l2junity.tools.parsers.ai.model;

import lombok.Data;

/**
 * @author ANZO
 * @since 21.03.2017
 */
@Data
public class PlainClassData {
	private int type;
	private String className;
	private String parent;
	private String classBody;
}
