package org.l2junity.gameserver.data.txt.model.item.condition;

import java.util.List;

/**
 * @author Camelion
 * @since 14.01.16
 */
public class UcTransmodeExclude implements Condition {
	private final List<String> transModes;

	public UcTransmodeExclude(List<String> transModes) {
		this.transModes = transModes;
	}
}
