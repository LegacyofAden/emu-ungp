package org.l2junity.gameserver.data.txt.model.item.condition;

import java.util.List;

/**
 * @author Camelion
 * @since 14.01.16
 */
public class EcInzoneNum implements Condition {
	private final List<Integer> instanceZones;

	public EcInzoneNum(List<Integer> instanceZones) {
		this.instanceZones = instanceZones;
	}
}
