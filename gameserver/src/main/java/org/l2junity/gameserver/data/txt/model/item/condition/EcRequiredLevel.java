package org.l2junity.gameserver.data.txt.model.item.condition;

/**
 * @author Camelion
 * @since 14.01.16
 */
public class EcRequiredLevel implements Condition {
	private final int level;

	public EcRequiredLevel(int level) {
		this.level = level;
	}
}
