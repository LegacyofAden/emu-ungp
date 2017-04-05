package org.l2junity.gameserver.data.txt.model.decodata;

import lombok.Data;

/**
 * @author ANZO
 * @since 04.04.2017
 */
@Data
public class DecoCostData {
	private int days;
	private int cost;

	public DecoCostData(int days, int cost) {
		this.days = days;
		this.cost = cost;
	}
}