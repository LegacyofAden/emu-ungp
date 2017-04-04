/*
 * Copyright (C) 2004-2015 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.data.xml.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.items.type.CrystalType;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * This class holds the Enchant HP Bonus Data.
 *
 * @author MrPoke, Zoey76
 */
@Slf4j
@StartupComponent(value = "Data", dependency = ItemTable.class)
public class EnchantItemHPBonusData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final EnchantItemHPBonusData instance = new EnchantItemHPBonusData();

	private static float FULL_ARMOR_MODIFIER = 1.5f;

	private final Map<CrystalType, List<Integer>> _armorHPBonuses = new EnumMap<>(CrystalType.class);

	private EnchantItemHPBonusData() {
		_armorHPBonuses.clear();
		parseDatapackFile("data/stats/enchantHPBonus.xml");
		LOGGER.info("Loaded {} Enchant HP Bonuses.", _armorHPBonuses.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode ->
		{
			forEach(listNode, "onePieceFactor", onePieceFactorNode ->
			{
				FULL_ARMOR_MODIFIER = Integer.parseInt(onePieceFactorNode.getTextContent()) / 100f;
			});
			forEach(listNode, "enchantHP", enchantHPNode ->
			{
				final List<Integer> bonuses = new ArrayList<>(12);

				forEach(enchantHPNode, "bonus", bonusNode ->
				{
					bonuses.add(Integer.parseInt(bonusNode.getTextContent()));
				});
				_armorHPBonuses.put(parseEnum(enchantHPNode.getAttributes(), CrystalType.class, "grade"), bonuses);
			});
		});
	}

	public int getLoadedElementsCount() {
		return _armorHPBonuses.size();
	}

	/**
	 * Gets the HP bonus.
	 *
	 * @param item the item
	 * @return the HP bonus
	 */
	public final int getHPBonus(ItemInstance item) {
		if ((item.getOlyEnchantLevel() <= 0) || (item.getItem().getType2() != ItemTemplate.TYPE2_SHIELD_ARMOR)) {
			return 0;
		}

		final List<Integer> values = _armorHPBonuses.get(item.getItem().getCrystalTypePlus());
		if ((values == null) || values.isEmpty()) {
			return 0;
		}

		final int bonus = values.get(Math.min(item.getOlyEnchantLevel(), values.size()) - 1);
		if (item.getItem().getBodyPart() == ItemTemplate.SLOT_FULL_ARMOR) {
			return (int) (bonus * FULL_ARMOR_MODIFIER);
		}
		return bonus;
	}
}
