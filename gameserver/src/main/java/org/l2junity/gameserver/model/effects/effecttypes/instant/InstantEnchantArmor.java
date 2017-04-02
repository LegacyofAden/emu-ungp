/*
 * Copyright (C) 2004-2017 L2J Unity
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
package org.l2junity.gameserver.model.effects.effecttypes.instant;

import org.l2junity.commons.util.CommonUtil;
import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.data.xml.impl.EnchantItemGroupsData;
import org.l2junity.gameserver.enums.ItemSkillType;
import org.l2junity.gameserver.enums.StatModifierType;
import org.l2junity.gameserver.enums.UserInfoType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.actor.request.EnchantItemRequest;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.holders.ItemSkillHolder;
import org.l2junity.gameserver.model.items.L2Item;
import org.l2junity.gameserver.model.items.enchant.EnchantItemGroup;
import org.l2junity.gameserver.model.items.enchant.EnchantResultType;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.items.type.EtcItemType;
import org.l2junity.gameserver.model.skills.CommonSkill;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.skills.SkillConditionScope;
import org.l2junity.gameserver.network.Debug;
import org.l2junity.gameserver.network.client.send.EnchantResult;
import org.l2junity.gameserver.network.client.send.InventoryUpdate;
import org.l2junity.gameserver.network.client.send.MagicSkillUse;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Sdw
 */
public class InstantEnchantArmor extends AbstractEffect {
	private static final Logger LOGGER = LoggerFactory.getLogger(InstantEnchantArmor.class);
	private static final Logger LOG_ENCHANT = LoggerFactory.getLogger("enchant.items");

	private double _bonusRate = 0;

	public InstantEnchantArmor(StatsSet params) {
		final double bonusRate = params.getDouble("bonusRate", 0);
		final StatModifierType mode = params.getEnum("mode", StatModifierType.class, StatModifierType.DIFF);

		switch (mode) {
			case DIFF: {
				_bonusRate = bonusRate * 100;
				break;
			}
			case PER: {
				_bonusRate = bonusRate;
				break;
			}
		}
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final PlayerInstance casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		final EnchantItemRequest request = casterPlayer.getRequest(EnchantItemRequest.class);
		if ((request == null) || !request.isProcessing()) {
			return;
		}
		final ItemInstance itemToEnchant = request.getEnchantingItem();
		final ItemInstance scroll = request.getEnchantingScroll();
		final ItemInstance support = request.getSupportItem();
		if ((itemToEnchant == null) || (scroll == null)) {
			casterPlayer.removeRequest(request.getClass());
			casterPlayer.getStat().setSupportItemBonusRate(0);
			return;
		}

		final InventoryUpdate iu = new InventoryUpdate();
		synchronized (itemToEnchant) {
			// last validation check
			if ((itemToEnchant.getOwnerId() != casterPlayer.getObjectId()) || !itemToEnchant.isEnchantable()) {
				casterPlayer.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
				casterPlayer.removeRequest(request.getClass());
				casterPlayer.getStat().setSupportItemBonusRate(0);
				casterPlayer.sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));
				return;
			}

			List<ItemSkillHolder> skills = scroll.getItem().getSkills(ItemSkillType.NORMAL);
			if (!skills.stream().allMatch(s -> s.getSkill().checkConditions(SkillConditionScope.GENERAL, casterPlayer, itemToEnchant))) {
				casterPlayer.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
				casterPlayer.removeRequest(request.getClass());
				casterPlayer.getStat().setSupportItemBonusRate(0);
				casterPlayer.sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));
				return;
			}

			if (casterPlayer.getInventory().destroyItem("Enchant", scroll.getObjectId(), 1, casterPlayer, item) == null) {
				casterPlayer.sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
				Util.handleIllegalPlayerAction(casterPlayer, "Player " + casterPlayer.getName() + " tried to enchant with a scroll he doesn't have", GeneralConfig.DEFAULT_PUNISH);
				casterPlayer.removeRequest(request.getClass());
				casterPlayer.sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));
				return;
			}

			if (support != null) {
				if (casterPlayer.getInventory().destroyItem("Enchant", support.getObjectId(), 1, casterPlayer, item) == null) {
					casterPlayer.sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
					Util.handleIllegalPlayerAction(casterPlayer, "Player " + casterPlayer.getName() + " tried to enchant with a support item he doesn't have", GeneralConfig.DEFAULT_PUNISH);
					casterPlayer.removeRequest(request.getClass());
					casterPlayer.sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));
					return;
				}

				skills = support.getItem().getSkills(ItemSkillType.NORMAL);
				if (!skills.stream().allMatch(s -> s.getSkill().checkConditions(SkillConditionScope.GENERAL, casterPlayer, itemToEnchant))) {
					casterPlayer.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
					casterPlayer.removeRequest(request.getClass());
					casterPlayer.getStat().setSupportItemBonusRate(0);
					casterPlayer.sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));
					return;
				}
			}

			final EnchantResultType resultType = calculateSuccess(casterPlayer, itemToEnchant, support);
			switch (resultType) {
				case ERROR: {
					casterPlayer.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
					casterPlayer.removeRequest(request.getClass());
					casterPlayer.getStat().setSupportItemBonusRate(0);
					casterPlayer.sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));
					break;
				}
				case SUCCESS: {
					final L2Item it = itemToEnchant.getItem();
					itemToEnchant.setEnchantLevel(itemToEnchant.getEnchantLevel() + 1);
					itemToEnchant.updateDatabase();
					casterPlayer.sendPacket(new EnchantResult(EnchantResult.SUCCESS, itemToEnchant));

					if (GeneralConfig.LOG_ITEM_ENCHANTS) {
						if (itemToEnchant.getEnchantLevel() > 0) {
							if (support == null) {
								LOG_ENCHANT.info("Success, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
							} else {
								LOG_ENCHANT.info("Success, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
							}
						} else {
							if (support == null) {
								LOG_ENCHANT.info("Success, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
							} else {
								LOG_ENCHANT.info("Success, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
							}
						}
					}

					// announce the success
					final int minEnchantAnnounce = 6;
					if (itemToEnchant.getEnchantLevel() == minEnchantAnnounce) {
						SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_SUCCESSFULLY_ENCHANTED_A_S2_S3);
						sm.addCharName(casterPlayer);
						sm.addInt(itemToEnchant.getEnchantLevel());
						sm.addItemName(itemToEnchant);
						casterPlayer.broadcastPacket(sm);

						Skill firework = CommonSkill.FIREWORK.getSkill();
						if (firework != null) {
							casterPlayer.broadcastPacket(new MagicSkillUse(casterPlayer, casterPlayer, firework.getId(), firework.getLevel(), firework.getHitTime(), skill.getReuseDelay()));
						}
					}

					if (itemToEnchant.isArmor() && itemToEnchant.isEquipped()) {
						it.forEachSkill(ItemSkillType.ON_ENCHANT, holder ->
						{
							// add skills bestowed from +4 armor
							if (itemToEnchant.getEnchantLevel() >= holder.getValue()) {
								casterPlayer.addSkill(holder.getSkill(), false);
								casterPlayer.sendSkillList();
							}
						});
					}
					break;
				}
				case FAILURE: {
					if (scroll.getEtcItem().getItemType() == EtcItemType.ENCHT_ATTR_CRYSTAL_ENCHANT_AM) {
						// safe enchant - remain old value
						casterPlayer.sendPacket(SystemMessageId.ENCHANT_FAILED_THE_ENCHANT_VALUE_FOR_THE_CORRESPONDING_ITEM_WILL_BE_EXACTLY_RETAINED);
						casterPlayer.sendPacket(new EnchantResult(EnchantResult.SAFE_FAIL, itemToEnchant));

						if (GeneralConfig.LOG_ITEM_ENCHANTS) {
							if (itemToEnchant.getEnchantLevel() > 0) {
								if (support == null) {
									LOG_ENCHANT.info("Safe Fail, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
								} else {
									LOG_ENCHANT.info("Safe Fail, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
								}
							} else {
								if (support == null) {
									LOG_ENCHANT.info("Safe Fail, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
								} else {
									LOG_ENCHANT.info("Safe Fail, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
								}
							}
						}
					} else {
						// unequip item on enchant failure to avoid item skills stack
						if (itemToEnchant.isEquipped()) {
							if (itemToEnchant.getEnchantLevel() > 0) {
								SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_EQUIPMENT_S1_S2_HAS_BEEN_REMOVED);
								sm.addInt(itemToEnchant.getEnchantLevel());
								sm.addItemName(itemToEnchant);
								casterPlayer.sendPacket(sm);
							} else {
								SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_UNEQUIPPED);
								sm.addItemName(itemToEnchant);
								casterPlayer.sendPacket(sm);
							}

							ItemInstance[] unequiped = casterPlayer.getInventory().unEquipItemInSlotAndRecord(itemToEnchant.getLocationSlot());
							for (ItemInstance itm : unequiped) {
								iu.addModifiedItem(itm);
							}

							casterPlayer.sendInventoryUpdate(iu);
							casterPlayer.broadcastUserInfo();
						}

						if (scroll.getEtcItem().getItemType() == EtcItemType.BLESS_ENCHT_AM) {
							// blessed enchant - clear enchant value
							casterPlayer.sendPacket(SystemMessageId.THE_BLESSED_ENCHANT_FAILED_THE_ENCHANT_VALUE_OF_THE_ITEM_BECAME_0);

							itemToEnchant.setEnchantLevel(0);
							itemToEnchant.updateDatabase();
							casterPlayer.sendPacket(new EnchantResult(EnchantResult.BLESSED_FAIL, 0, 0));

							if (GeneralConfig.LOG_ITEM_ENCHANTS) {
								if (itemToEnchant.getEnchantLevel() > 0) {
									if (support == null) {
										LOG_ENCHANT.info("Blessed Fail, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
									} else {
										LOG_ENCHANT.info("Blessed Fail, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
									}
								} else {
									if (support == null) {
										LOG_ENCHANT.info("Blessed Fail, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
									} else {
										LOG_ENCHANT.info("Blessed Fail, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
									}
								}
							}
						} else {
							// enchant failed, destroy item
							int crystalId = itemToEnchant.getItem().getCrystalItemId();
							int count = itemToEnchant.getCrystalCount() - ((itemToEnchant.getItem().getCrystalCount() + 1) / 2);
							if (count < 1) {
								count = 1;
							}

							if (casterPlayer.getInventory().destroyItem("Enchant", itemToEnchant, casterPlayer, null) == null) {
								// unable to destroy item, cheater ?
								Util.handleIllegalPlayerAction(casterPlayer, "Unable to delete item on enchant failure from player " + casterPlayer.getName() + ", possible cheater !", GeneralConfig.DEFAULT_PUNISH);
								casterPlayer.removeRequest(request.getClass());
								casterPlayer.getStat().setSupportItemBonusRate(0);
								casterPlayer.sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));

								if (GeneralConfig.LOG_ITEM_ENCHANTS) {
									if (itemToEnchant.getEnchantLevel() > 0) {
										if (support == null) {
											LOG_ENCHANT.info("Unable to destroy, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
										} else {
											LOG_ENCHANT.info("Unable to destroy, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
										}
									} else {
										if (support == null) {
											LOG_ENCHANT.info("Unable to destroy, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
										} else {
											LOG_ENCHANT.info("Unable to destroy, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
										}
									}
								}
								return;
							}

							ItemInstance crystals = null;
							if (crystalId != 0) {
								crystals = casterPlayer.getInventory().addItem("Enchant", crystalId, count, casterPlayer, itemToEnchant);

								SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S2_S1_S);
								sm.addItemName(crystals);
								sm.addLong(count);
								casterPlayer.sendPacket(sm);
							}

							if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
								if (crystals != null) {
									iu.addItem(crystals);
								}
							}

							if (crystalId == 0) {
								casterPlayer.sendPacket(new EnchantResult(EnchantResult.NO_CRYSTAL, 0, 0));
							} else {
								casterPlayer.sendPacket(new EnchantResult(EnchantResult.FAIL, crystalId, count));
							}

							if (GeneralConfig.LOG_ITEM_ENCHANTS) {
								if (itemToEnchant.getEnchantLevel() > 0) {
									if (support == null) {
										LOG_ENCHANT.info("Fail, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
									} else {
										LOG_ENCHANT.info("Fail, Character:{} [{}] Account:{} IP:{}, +{} {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getEnchantLevel(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
									}
								} else {
									if (support == null) {
										LOG_ENCHANT.info("Fail, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId());
									} else {
										LOG_ENCHANT.info("Fail, Character:{} [{}] Account:{} IP:{}, {}({}) [{}], {}({}) [{}], {}({}) [{}]", casterPlayer.getName(), casterPlayer.getObjectId(), casterPlayer.getAccountName(), casterPlayer.getIPAddress(), itemToEnchant.getName(), itemToEnchant.getCount(), itemToEnchant.getObjectId(), scroll.getName(), scroll.getCount(), scroll.getObjectId(), support.getName(), support.getCount(), support.getObjectId());
									}
								}
							}
						}
					}
					break;
				}
			}

			if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
				if (scroll.getCount() == 0) {
					iu.addRemovedItem(scroll);
				} else {
					iu.addModifiedItem(scroll);
				}

				if (itemToEnchant.getCount() == 0) {
					iu.addRemovedItem(itemToEnchant);
				} else {
					iu.addModifiedItem(itemToEnchant);
				}

				if (support != null) {
					if (support.getCount() == 0) {
						iu.addRemovedItem(support);
					} else {
						iu.addModifiedItem(support);
					}
				}

				casterPlayer.sendInventoryUpdate(iu);
			} else {
				casterPlayer.sendItemList(true);
			}

			request.setProcessing(false);
			casterPlayer.broadcastUserInfo(UserInfoType.ENCHANTLEVEL);
		}
	}

	public EnchantResultType calculateSuccess(PlayerInstance player, ItemInstance enchantItem, ItemInstance supportItem) {
		final EnchantItemGroup group = EnchantItemGroupsData.getInstance().getItemGroup(enchantItem.getItem(), enchantItem.getItem().getEnchantGroup());
		if (group == null) {
			LOGGER.warn("Couldn't find enchant item group for item: {} requested by: {}", enchantItem.getId(), player);
			return EnchantResultType.ERROR;
		}
		final double chance = group.getChance(enchantItem.getEnchantLevel());

		final double supportBonusRate = (supportItem != null) ? player.getStat().getSupportItemBonus() : 1;
		final double finalChance = Math.min((chance + _bonusRate) * supportBonusRate, 100);

		final double random = 100 * Rnd.nextDouble();
		boolean success = (random < finalChance) || player.tryLuck();

		if (player.isDebug()) {
			final StatsSet set = new StatsSet();
			set.set("chance", CommonUtil.formatDouble(chance, "#.##"));
			if (_bonusRate > 0) {
				set.set("bonusRate", CommonUtil.formatDouble(_bonusRate, "#.##"));
			}
			if (supportBonusRate > 0) {
				set.set("supportBonusRate", CommonUtil.formatDouble(supportBonusRate, "#.##"));
			}
			set.set("finalChance", CommonUtil.formatDouble(finalChance, "#.##"));
			set.set("random", CommonUtil.formatDouble(random, "#.##"));
			set.set("success", success);
			set.set("item group", group.getName());
			Debug.sendItemDebug(player, enchantItem, set);
		}
		return success ? EnchantResultType.SUCCESS : EnchantResultType.FAILURE;
	}
}
