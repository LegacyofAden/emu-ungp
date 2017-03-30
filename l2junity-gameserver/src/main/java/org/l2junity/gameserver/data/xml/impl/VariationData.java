/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.l2junity.gameserver.data.xml.impl;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.l2junity.commons.loader.annotations.Dependency;
import org.l2junity.commons.loader.annotations.InstanceGetter;
import org.l2junity.commons.loader.annotations.Load;
import org.l2junity.commons.loader.annotations.Reload;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.loader.LoadGroup;
import org.l2junity.gameserver.model.VariationInstance;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.options.OptionDataCategory;
import org.l2junity.gameserver.model.options.OptionDataGroup;
import org.l2junity.gameserver.model.options.Options;
import org.l2junity.gameserver.model.options.Variation;
import org.l2junity.gameserver.model.options.VariationFee;
import org.l2junity.gameserver.model.options.VariationWeaponType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

/**
 * @author Pere
 */
public class VariationData implements IGameXmlReader
{
	private static final Logger LOGGER = LoggerFactory.getLogger(VariationData.class);
	
	private final Map<Integer, Variation> _variations = new HashMap<>();
	private final Map<Integer, Map<Integer, VariationFee>> _fees = new HashMap<>();
	
	protected VariationData()
	{
	}
	
	@Reload("variation")
	@Load(group = LoadGroup.class, dependencies = @Dependency(clazz = OptionData.class))
	private void load() throws Exception
	{
		_variations.clear();
		_fees.clear();
		parseDatapackFile("data/variations.xml");
		LOGGER.info("Loaded: {} Variations.", _variations.size());
		LOGGER.info("Loaded: {} Fees.", _fees.size());
	}
	
	@Override
	public void parseDocument(Document doc, Path path)
	{
		forEach(doc, "list", listNode ->
		{
			forEach(listNode, "variations", variationsNode ->
			{
				forEach(variationsNode, "variation", variationNode ->
				{
					final int mineralId = parseInteger(variationNode.getAttributes(), "mineralId");
					final Variation variation = new Variation(mineralId);
					
					forEach(variationNode, "optionGroup", groupNode ->
					{
						final String weaponTypeString = parseString(groupNode.getAttributes(), "weaponType").toUpperCase();
						final VariationWeaponType weaponType = VariationWeaponType.valueOf(weaponTypeString);
						final int order = parseInteger(groupNode.getAttributes(), "order");
						
						final List<OptionDataCategory> sets = new ArrayList<>();
						forEach(groupNode, "optionCategory", categoryNode ->
						{
							final double chance = parseDouble(categoryNode.getAttributes(), "chance");
							final Map<Options, Double> options = new HashMap<>();
							forEach(categoryNode, "option|optionRange", optionNode ->
							{
								final double optionChance = parseDouble(optionNode.getAttributes(), "chance");
								switch (optionNode.getNodeName())
								{
									case "option":
										final int optionId = parseInteger(optionNode.getAttributes(), "id");
										final Options opt = OptionData.getInstance().getOptions(optionId);
										if (opt == null)
										{
											LOGGER.warn("Null option for id " + optionId);
											return;
										}
										options.put(opt, optionChance);
										break;
									case "optionRange":
										final int fromId = parseInteger(optionNode.getAttributes(), "from");
										final int toId = parseInteger(optionNode.getAttributes(), "to");
										for (int id = fromId; id <= toId; id++)
										{
											final Options op = OptionData.getInstance().getOptions(id);
											if (op == null)
											{
												LOGGER.warn("Null option for id " + id);
												return;
											}
											options.put(op, optionChance);
										}
										break;
								}
							});
							
							sets.add(new OptionDataCategory(options, chance));
						});
						
						variation.setEffectGroup(weaponType, order, new OptionDataGroup(sets));
					});
					
					_variations.put(mineralId, variation);
				});
			});
			
			final Map<Integer, List<Integer>> itemGroups = new HashMap<>();
			forEach(listNode, "itemGroups", variationsNode ->
			{
				forEach(variationsNode, "itemGroup", variationNode ->
				{
					final int id = parseInteger(variationNode.getAttributes(), "id");
					final List<Integer> items = new ArrayList<>();
					forEach(variationNode, "item", itemNode ->
					{
						final int itemId = parseInteger(itemNode.getAttributes(), "id");
						items.add(itemId);
					});
					
					itemGroups.put(id, items);
				});
			});
			
			forEach(listNode, "fees", variationNode ->
			{
				forEach(variationNode, "fee", feeNode ->
				{
					final int itemGroupId = parseInteger(feeNode.getAttributes(), "itemGroup");
					final List<Integer> itemGroup = itemGroups.get(itemGroupId);
					final int itemId = parseInteger(feeNode.getAttributes(), "itemId");
					final int itemCount = parseInteger(feeNode.getAttributes(), "itemCount");
					final int cancelFee = parseInteger(feeNode.getAttributes(), "cancelFee");
					
					final VariationFee fee = new VariationFee(itemId, itemCount, cancelFee);
					final Map<Integer, VariationFee> feeByMinerals = new HashMap<>();
					forEach(feeNode, "mineral|mineralRange", mineralNode ->
					{
						switch (mineralNode.getNodeName())
						{
							case "mineral":
								final int mId = parseInteger(mineralNode.getAttributes(), "id");
								feeByMinerals.put(mId, fee);
								break;
							case "mineralRange":
								final int fromId = parseInteger(mineralNode.getAttributes(), "from");
								final int toId = parseInteger(mineralNode.getAttributes(), "to");
								for (int id = fromId; id <= toId; id++)
								{
									feeByMinerals.put(id, fee);
								}
								break;
						}
					});
					
					for (int item : itemGroup)
					{
						Map<Integer, VariationFee> fees = _fees.computeIfAbsent(item, k -> new HashMap<>());
						fees.putAll(feeByMinerals);
					}
				});
			});
		});
	}
	
	public int getVariationCount()
	{
		return _variations.size();
	}
	
	public int getFeeCount()
	{
		return _fees.size();
	}
	
	/**
	 * Generate a new random variation instance
	 * @param variation The variation template to generate the variation instance from
	 * @param targetItem The item on which the variation will be applied
	 * @return VariationInstance
	 */
	public VariationInstance generateRandomVariation(Variation variation, ItemInstance targetItem)
	{
		final VariationWeaponType weaponType = ((targetItem.getWeaponItem() != null) && targetItem.getWeaponItem().isMagicWeapon()) ? VariationWeaponType.MAGE : VariationWeaponType.WARRIOR;
		return generateRandomVariation(variation, weaponType);
	}
	
	private VariationInstance generateRandomVariation(Variation variation, VariationWeaponType weaponType)
	{
		Options option1 = variation.getRandomEffect(weaponType, 0);
		Options option2 = variation.getRandomEffect(weaponType, 1);
		return ((option1 != null) && (option2 != null)) ? new VariationInstance(variation.getMineralId(), option1, option2) : null;
	}
	
	public final Variation getVariation(int mineralId)
	{
		return _variations.get(mineralId);
	}
	
	public final VariationFee getFee(int itemId, int mineralId)
	{
		return _fees.getOrDefault(itemId, Collections.emptyMap()).get(mineralId);
	}
	
	public final long getCancelFee(int itemId, int mineralId)
	{
		final Map<Integer, VariationFee> fees = _fees.get(itemId);
		if (fees == null)
		{
			return -1;
		}
		
		VariationFee fee = fees.get(mineralId);
		if (fee == null)
		{
			// FIXME This will happen when the data is pre-rework or when augments were manually given, but still that's a cheap solution
			LOGGER.warn("Cancellation fee not found for item [" + itemId + "] and mineral [" + mineralId + "]");
			fee = fees.values().iterator().next();
			if (fee == null)
			{
				return -1;
			}
		}
		
		return fee.getCancelFee();
	}
	
	public final boolean hasFeeData(int itemId)
	{
		Map<Integer, VariationFee> itemFees = _fees.get(itemId);
		return (itemFees != null) && !itemFees.isEmpty();
	}
	
	@InstanceGetter
	public static VariationData getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final VariationData INSTANCE = new VariationData();
	}
}
