package org.l2junity.gameserver.data.txt.model.item.condition;

/**
 * @author Camelion
 * @since 14.01.16
 */
public class UcRestartPoint implements Condition {
	private final int restartPoint;

	public UcRestartPoint(int restartPoint) {
		this.restartPoint = restartPoint;
	}
}
