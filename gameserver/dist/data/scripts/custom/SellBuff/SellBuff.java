/*
 * Copyright (C) 2004-2014 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package custom.SellBuff;

import java.util.StringTokenizer;

import org.l2junity.core.configs.SellBuffConfig;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.handler.BypassHandler;
import org.l2junity.gameserver.handler.IBypassHandler;
import org.l2junity.gameserver.handler.IVoicedCommandHandler;
import org.l2junity.gameserver.handler.VoicedCommandHandler;
import org.l2junity.gameserver.instancemanager.SellBuffsManager;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.AbstractScript;
import org.l2junity.gameserver.model.holders.SellBuffHolder;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.s2c.string.CustomMessage;
import org.l2junity.gameserver.util.Util;

/**
 * Sell Buffs voice command
 *
 * @author St3eT
 */
public class SellBuff implements IVoicedCommandHandler, IBypassHandler {
	private static final String[] VOICED_COMMANDS =
			{
					"sellbuff",
					"sellbuffs",
			};

	private static final String[] BYPASS_COMMANDS =
			{
					"sellbuffadd",
					"sellbuffaddskill",
					"sellbuffedit",
					"sellbuffchangeprice",
					"sellbuffremove",
					"sellbuffbuymenu",
					"sellbuffbuyskill",
					"sellbuffstart",
					"sellbuffstop",
			};

	private SellBuff() {
		if (SellBuffConfig.SELLBUFF_ENABLED) {
			BypassHandler.getInstance().registerHandler(this);
			VoicedCommandHandler.getInstance().registerHandler(this);
		}
	}

	@Override
	public boolean useBypass(String command, Player activeChar, Creature target) {
		String cmd = "";
		String params = "";
		final StringTokenizer st = new StringTokenizer(command, " ");

		if (st.hasMoreTokens()) {
			cmd = st.nextToken();
		}

		while (st.hasMoreTokens()) {
			params += st.nextToken() + (st.hasMoreTokens() ? " " : "");
		}

		if (cmd.isEmpty()) {
			return false;
		}
		return useBypass(cmd, activeChar, params);
	}

	@Override
	public boolean useVoicedCommand(String command, Player activeChar, String params) {
		switch (command) {
			case "sellbuff":
			case "sellbuffs": {
				SellBuffsManager.getInstance().sendSellMenu(activeChar);
				break;
			}
		}
		return true;
	}

	public boolean useBypass(String command, Player activeChar, String params) {
		if (!SellBuffConfig.SELLBUFF_ENABLED) {
			return false;
		}

		switch (command) {
			case "sellbuffstart": {
				if (activeChar.isSellingBuffs() || (params == null) || params.isEmpty()) {
					return false;
				} else if (activeChar.getSellingBuffs().isEmpty()) {
					activeChar.sendMessage(CustomMessage.YOUR_LIST_OF_BUFFS_IS_EMPTY_PLEASE_ADD_SOME_BUFFS_FIRST);
					return false;
				} else {
					String title = "BUFF SELL: ";
					final StringTokenizer st = new StringTokenizer(params, " ");
					while (st.hasMoreTokens()) {
						title += st.nextToken() + " ";
					}

					if (title.length() > 40) {
						activeChar.sendMessage(CustomMessage.YOUR_TITLE_CANNOT_EXCEED_29_CHARACTERS_IN_LENGTH_PLEASE_TRY_AGAIN);
						return false;
					}

					SellBuffsManager.getInstance().startSellBuffs(activeChar, title);
				}
				break;
			}
			case "sellbuffstop": {
				if (activeChar.isSellingBuffs()) {
					SellBuffsManager.getInstance().stopSellBuffs(activeChar);
				}
				break;
			}
			case "sellbuffadd": {
				if (!activeChar.isSellingBuffs()) {
					int index = 0;
					if ((params != null) && !params.isEmpty() && Util.isDigit(params)) {
						index = Integer.parseInt(params);
					}

					SellBuffsManager.getInstance().sendBuffChoiceMenu(activeChar, index);
				}
				break;
			}
			case "sellbuffedit": {
				if (!activeChar.isSellingBuffs()) {
					SellBuffsManager.getInstance().sendBuffEditMenu(activeChar);
				}
				break;
			}
			case "sellbuffchangeprice": {
				if (!activeChar.isSellingBuffs() && (params != null) && !params.isEmpty()) {
					final StringTokenizer st = new StringTokenizer(params, " ");

					int skillId = -1;
					int price = -1;

					if (st.hasMoreTokens()) {
						skillId = Integer.parseInt(st.nextToken());
					}

					if (st.hasMoreTokens()) {
						try {
							price = Integer.parseInt(st.nextToken());
						} catch (NumberFormatException e) {
							activeChar.sendMessage(CustomMessage.TOO_BIG_PRICE_MAXIMAL_PRICE_IS_$, SellBuffConfig.SELLBUFF_MAX_PRICE);
							SellBuffsManager.getInstance().sendBuffEditMenu(activeChar);
						}
					}

					if ((skillId == -1) || (price == -1)) {
						return false;
					}

					final Skill skillToChange = activeChar.getKnownSkill(skillId);
					if (skillToChange == null) {
						return false;
					}

					final SellBuffHolder holder = activeChar.getSellingBuffs().stream()
							.filter(h -> (h.getSkillId() == skillToChange.getId())).findFirst().orElse(null);
					if ((holder != null)) {
						activeChar.sendMessage(CustomMessage.PRICE_OF_$_HAS_BEEN_CHANGED_TO_$,
								activeChar.getKnownSkill(holder.getSkillId()).getName(), price);
						holder.setPrice(price);
						SellBuffsManager.getInstance().sendBuffEditMenu(activeChar);
					}
				}
				break;
			}
			case "sellbuffremove": {
				if (!activeChar.isSellingBuffs() && (params != null) && !params.isEmpty()) {
					final StringTokenizer st = new StringTokenizer(params, " ");

					int skillId = -1;

					if (st.hasMoreTokens()) {
						skillId = Integer.parseInt(st.nextToken());
					}

					if ((skillId == -1)) {
						return false;
					}

					final Skill skillToRemove = activeChar.getKnownSkill(skillId);
					if (skillToRemove == null) {
						return false;
					}

					final SellBuffHolder holder = activeChar.getSellingBuffs().stream().filter(h -> (h.getSkillId() == skillToRemove.getId())).findFirst().orElse(null);
					if ((holder != null) && activeChar.getSellingBuffs().remove(holder)) {
						activeChar.sendMessage(CustomMessage.SKILL_$_HAS_BEEN_REMOVED, activeChar.getKnownSkill(holder.getSkillId()).getName());
						SellBuffsManager.getInstance().sendBuffEditMenu(activeChar);
					}
				}
				break;
			}
			case "sellbuffaddskill": {
				if (!activeChar.isSellingBuffs() && (params != null) && !params.isEmpty()) {
					final StringTokenizer st = new StringTokenizer(params, " ");

					int skillId = -1;
					long price = -1;

					if (st.hasMoreTokens()) {
						skillId = Integer.parseInt(st.nextToken());
					}

					if (st.hasMoreTokens()) {
						try {
							price = Integer.parseInt(st.nextToken());
						} catch (NumberFormatException e) {
							activeChar.sendMessage(CustomMessage.TOO_BIG_PRICE_MAXIMAL_PRICE_IS_$, SellBuffConfig.SELLBUFF_MIN_PRICE);
							SellBuffsManager.getInstance().sendBuffEditMenu(activeChar);
						}
					}

					if ((skillId == -1) || (price == -1)) {
						return false;
					}

					final Skill skillToAdd = activeChar.getKnownSkill(skillId);
					if (skillToAdd == null) {
						return false;
					} else if (price < SellBuffConfig.SELLBUFF_MIN_PRICE) {
						activeChar.sendMessage(CustomMessage.TOO_SMALL_PRICE_MINIMAL_PRICE_IS_$, SellBuffConfig.SELLBUFF_MIN_PRICE);
						return false;
					} else if (price > SellBuffConfig.SELLBUFF_MAX_PRICE) {
						activeChar.sendMessage(CustomMessage.TOO_BIG_PRICE_MAXIMAL_PRICE_IS_$, SellBuffConfig.SELLBUFF_MAX_PRICE);
						return false;
					} else if (activeChar.getSellingBuffs().size() >= SellBuffConfig.SELLBUFF_MAX_BUFFS) {
						activeChar.sendMessage(CustomMessage.YOU_ALREADY_REACHED_MAX_COUNT_OF_BUFFS_MAX_BUFFS_IS_$,
								SellBuffConfig.SELLBUFF_MAX_BUFFS);
						return false;
					} else if (!SellBuffsManager.getInstance().isInSellList(activeChar, skillToAdd)) {
						activeChar.getSellingBuffs().add(new SellBuffHolder(skillToAdd.getId(), price));
						activeChar.sendMessage(CustomMessage.$_HAS_BEEN_ADDED, skillToAdd.getName());
						SellBuffsManager.getInstance().sendBuffChoiceMenu(activeChar, 0);
					}
				}
				break;
			}
			case "sellbuffbuymenu": {
				if ((params != null) && !params.isEmpty()) {
					final StringTokenizer st = new StringTokenizer(params, " ");

					int objId = -1;
					int index = 0;
					if (st.hasMoreTokens()) {
						objId = Integer.parseInt(st.nextToken());
					}

					if (st.hasMoreTokens()) {
						index = Integer.parseInt(st.nextToken());
					}

					final Player seller = WorldManager.getInstance().getPlayer(objId);
					if (seller != null) {
						if (!seller.isSellingBuffs() || !activeChar.isInRadius3d(seller, Npc.INTERACTION_DISTANCE)) {
							return false;
						}

						SellBuffsManager.getInstance().sendBuffMenu(activeChar, seller, index);
					}
				}
				break;
			}
			case "sellbuffbuyskill": {
				if ((params != null) && !params.isEmpty()) {
					final StringTokenizer st = new StringTokenizer(params, " ");
					int objId = -1;
					int skillId = -1;
					int index = 0;

					if (st.hasMoreTokens()) {
						objId = Integer.parseInt(st.nextToken());
					}

					if (st.hasMoreTokens()) {
						skillId = Integer.parseInt(st.nextToken());
					}

					if (st.hasMoreTokens()) {
						index = Integer.parseInt(st.nextToken());
					}

					if ((skillId == -1) || (objId == -1)) {
						return false;
					}

					final Player seller = WorldManager.getInstance().getPlayer(objId);
					if (seller == null) {
						return false;
					}

					final Skill skillToBuy = seller.getKnownSkill(skillId);
					if (!seller.isSellingBuffs() || !Util.checkIfInRange(Npc.INTERACTION_DISTANCE, activeChar, seller, true) || (skillToBuy == null)) {
						return false;
					}

					if (seller.getCurrentMp() < (skillToBuy.getMpConsume() * SellBuffConfig.SELLBUFF_MP_MULTIPLER)) {
						activeChar.sendMessage(CustomMessage.$_HAS_NO_ENOUGH_MANA_FOR_$, seller.getName(), skillToBuy.getName());
						SellBuffsManager.getInstance().sendBuffMenu(activeChar, seller, index);
						return false;
					}

					final SellBuffHolder holder = seller.getSellingBuffs().stream().filter(h -> (h.getSkillId() == skillToBuy.getId())).findFirst().orElse(null);
					if (holder != null) {
						if (AbstractScript.getQuestItemsCount(activeChar, SellBuffConfig.SELLBUFF_PAYMENT_ID) >= holder.getPrice()) {
							AbstractScript.takeItems(activeChar, SellBuffConfig.SELLBUFF_PAYMENT_ID, holder.getPrice());
							AbstractScript.giveItems(seller, SellBuffConfig.SELLBUFF_PAYMENT_ID, holder.getPrice());
							seller.reduceCurrentMp(skillToBuy.getMpConsume() * SellBuffConfig.SELLBUFF_MP_MULTIPLER);
							skillToBuy.activateSkill(seller, activeChar);
						} else {
							final ItemTemplate item = ItemTable.getInstance().getTemplate(SellBuffConfig.SELLBUFF_PAYMENT_ID);
							if (item != null) {
								activeChar.sendMessage(CustomMessage.NOT_ENOUGH_$, item.getName());
							} else {
								activeChar.sendMessage(CustomMessage.NOT_ENOUGH_ITEMS);
							}
						}
					}
					SellBuffsManager.getInstance().sendBuffMenu(activeChar, seller, index);
				}
				break;
			}
		}
		return true;
	}

	@Override
	public String[] getVoicedCommandList() {
		return VOICED_COMMANDS;
	}

	@Override
	public String[] getBypassList() {
		return BYPASS_COMMANDS;
	}

	public static void main(String[] args) {
		new SellBuff();
	}
}