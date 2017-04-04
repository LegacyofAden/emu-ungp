package org.l2junity.gameserver.data.txt.model.item.condition;

import java.util.List;

/**
 * @author Camelion
 * @since 14.01.16
 */
public class EcRace implements Condition {
	private final List<Integer> races;

	public EcRace(List<Integer> races) {
		this.races = races;
	}
}
