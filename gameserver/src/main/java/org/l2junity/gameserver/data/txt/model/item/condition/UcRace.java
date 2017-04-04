package org.l2junity.gameserver.data.txt.model.item.condition;

import java.util.List;

/**
 * @author Camelion
 * @since 14.01.16
 */
public class UcRace implements Condition {
	private final List<Integer> races;

	public UcRace(List<Integer> races) {
		this.races = races;
	}
}
