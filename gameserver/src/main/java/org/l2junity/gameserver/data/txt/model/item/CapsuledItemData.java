package org.l2junity.gameserver.data.txt.model.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ANZO
 * @since 24.03.2017
 */
@Data
@AllArgsConstructor
public final class CapsuledItemData {
	private final String itemName;
	private final int minCount;
	private final int maxCount;
	private final double chance;
}