package org.l2junity.gameserver.data.txt.model.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.l2junity.gameserver.data.txt.model.constants.AttributeType;

/**
 * @author ANZO
 * @since 24.03.2017
 */
@Data
@AllArgsConstructor
public final class AttributeAttack {
	private final AttributeType type;
	private final int value;
}