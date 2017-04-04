package org.l2junity.gameserver.data.txt.model.item.condition;

import java.util.List;

/**
 * @author Camelion
 * @since 14.01.16
 */
public class EcCategory implements Condition {
	private final List<String> categories;

	public EcCategory(List<String> categories) {
		this.categories = categories;
	}
}
