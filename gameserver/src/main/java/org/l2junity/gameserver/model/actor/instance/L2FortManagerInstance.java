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
package org.l2junity.gameserver.model.actor.instance;

import org.l2junity.commons.util.CommonUtil;
import org.l2junity.core.configs.FeatureConfig;
import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.data.xml.impl.TeleportersData;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.model.ClanPrivilege;
import org.l2junity.gameserver.model.actor.templates.L2NpcTemplate;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.model.entity.Fort.FortFunction;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.teleporter.TeleportHolder;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;
import org.l2junity.gameserver.network.client.send.WareHouseDepositList;
import org.l2junity.gameserver.network.client.send.WareHouseWithdrawalList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

/**
 * Fortress Foreman implementation used for: Area Teleports, Support Magic, Clan Warehouse, Exp Loss Reduction
 */
public class L2FortManagerInstance extends L2MerchantInstance {
	private static final Logger LOGGER = LoggerFactory.getLogger(L2FortManagerInstance.class);

	protected static final int COND_ALL_FALSE = 0;
	protected static final int COND_BUSY_BECAUSE_OF_SIEGE = 1;
	protected static final int COND_OWNER = 2;

	public L2FortManagerInstance(L2NpcTemplate template) {
		super(template);
		setInstanceType(InstanceType.L2FortManagerInstance);
	}

	@Override
	public boolean isWarehouse() {
		return true;
	}

	private void sendHtmlMessage(Player player, NpcHtmlMessage html) {
		html.replace("%objectId%", String.valueOf(getObjectId()));
		html.replace("%npcId%", String.valueOf(getId()));
		player.sendPacket(html);
	}

	@Override
	public void onBypassFeedback(Player player, String command) {
		// BypassValidation Exploit plug.
		if (player.getLastFolkNPC().getObjectId() != getObjectId()) {
			return;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		int condition = validateCondition(player);
		if (condition <= COND_ALL_FALSE) {
			return;
		} else if (condition == COND_BUSY_BECAUSE_OF_SIEGE) {
			return;
		} else if (condition == COND_OWNER) {
			StringTokenizer st = new StringTokenizer(command, " ");
			String actualCommand = st.nextToken(); // Get actual command

			String val = "";
			if (st.countTokens() >= 1) {
				val = st.nextToken();
			}
			if (actualCommand.equalsIgnoreCase("expel")) {
				if (player.hasClanPrivilege(ClanPrivilege.CS_DISMISS)) {
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player.getLang(), "fortress/foreman-expel.htm");
					html.replace("%objectId%", String.valueOf(getObjectId()));
					player.sendPacket(html);
				} else {
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player.getLang(), "fortress/foreman-noprivs.htm");
					html.replace("%objectId%", String.valueOf(getObjectId()));
					player.sendPacket(html);
				}
				return;
			} else if (actualCommand.equalsIgnoreCase("banish_foreigner")) {
				if (player.hasClanPrivilege(ClanPrivilege.CS_DISMISS)) {
					getFort().banishForeigners(); // Move non-clan members off fortress area
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player.getLang(), "fortress/foreman-expeled.htm");
					html.replace("%objectId%", String.valueOf(getObjectId()));
					player.sendPacket(html);
				} else {
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player.getLang(), "fortress/foreman-noprivs.htm");
					html.replace("%objectId%", String.valueOf(getObjectId()));
					player.sendPacket(html);
				}
				return;
			} else if (actualCommand.equalsIgnoreCase("receive_report")) {
				if (getFort().getFortState() < 2) {
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player.getLang(), "fortress/foreman-report.htm");
					html.replace("%objectId%", String.valueOf(getObjectId()));
					if (FeatureConfig.FS_MAX_OWN_TIME > 0) {
						int hour = (int) Math.floor(getFort().getTimeTillRebelArmy() / 3600);
						int minutes = (int) (Math.floor(getFort().getTimeTillRebelArmy() - (hour * 3600)) / 60);
						html.replace("%hr%", String.valueOf(hour));
						html.replace("%min%", String.valueOf(minutes));
					} else {
						int hour = (int) Math.floor(getFort().getOwnedTime() / 3600);
						int minutes = (int) (Math.floor(getFort().getOwnedTime() - (hour * 3600)) / 60);
						html.replace("%hr%", String.valueOf(hour));
						html.replace("%min%", String.valueOf(minutes));
					}
					player.sendPacket(html);
				} else {
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player.getLang(), "fortress/foreman-castlereport.htm");
					html.replace("%objectId%", String.valueOf(getObjectId()));
					int hour, minutes;
					if (FeatureConfig.FS_MAX_OWN_TIME > 0) {
						hour = (int) Math.floor(getFort().getTimeTillRebelArmy() / 3600);
						minutes = (int) (Math.floor(getFort().getTimeTillRebelArmy() - (hour * 3600)) / 60);
						html.replace("%hr%", String.valueOf(hour));
						html.replace("%min%", String.valueOf(minutes));
					} else {
						hour = (int) Math.floor(getFort().getOwnedTime() / 3600);
						minutes = (int) (Math.floor(getFort().getOwnedTime() - (hour * 3600)) / 60);
						html.replace("%hr%", String.valueOf(hour));
						html.replace("%min%", String.valueOf(minutes));
					}
					hour = (int) Math.floor(getFort().getTimeTillNextFortUpdate() / 3600);
					minutes = (int) (Math.floor(getFort().getTimeTillNextFortUpdate() - (hour * 3600)) / 60);
					html.replace("%castle%", getFort().getContractedCastle().getName());
					html.replace("%hr2%", String.valueOf(hour));
					html.replace("%min2%", String.valueOf(minutes));
					player.sendPacket(html);
				}
				return;
			} else if (actualCommand.equalsIgnoreCase("operate_door")) // door
			// control
			{
				if (player.hasClanPrivilege(ClanPrivilege.CS_OPEN_DOOR)) {
					if (!val.isEmpty()) {
						boolean open = (Integer.parseInt(val) == 1);
						while (st.hasMoreTokens()) {
							getFort().openCloseDoor(player, Integer.parseInt(st.nextToken()), open);
						}
						if (open) {
							final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
							html.setFile(player.getLang(), "fortress/foreman-opened.htm");
							html.replace("%objectId%", String.valueOf(getObjectId()));
							player.sendPacket(html);
						} else {
							final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
							html.setFile(player.getLang(), "fortress/foreman-closed.htm");
							html.replace("%objectId%", String.valueOf(getObjectId()));
							player.sendPacket(html);
						}
					} else {
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player.getLang(), "fortress/" + getTemplate().getId() + "-d.htm");
						html.replace("%objectId%", String.valueOf(getObjectId()));
						html.replace("%npcname%", getName());
						player.sendPacket(html);
					}
				} else {
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player.getLang(), "fortress/foreman-noprivs.htm");
					html.replace("%objectId%", String.valueOf(getObjectId()));
					player.sendPacket(html);
				}
				return;
			} else if (actualCommand.equalsIgnoreCase("manage_vault")) {
				final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
				if (player.hasClanPrivilege(ClanPrivilege.CL_VIEW_WAREHOUSE)) {
					if (val.equalsIgnoreCase("deposit")) {
						showVaultWindowDeposit(player);
					} else if (val.equalsIgnoreCase("withdraw")) {
						showVaultWindowWithdraw(player);
					} else {
						html.setFile(player.getLang(), "fortress/foreman-vault.htm");
						sendHtmlMessage(player, html);
					}
				} else {
					html.setFile(player.getLang(), "fortress/foreman-noprivs.htm");
					sendHtmlMessage(player, html);
				}
				return;
			} else if (actualCommand.equalsIgnoreCase("functions")) {
				if (val.equalsIgnoreCase("tele")) {
					final FortFunction func = getFort().getFortFunction(Fort.FUNC_TELEPORT);
					if (func == null) {
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player.getLang(), "fortress/foreman-nac.htm");
						sendHtmlMessage(player, html);
					} else {
						final String listName = "tel" + func.getLvl();
						final TeleportHolder holder = TeleportersData.getInstance().getHolder(getId(), listName);
						if (holder != null) {
							holder.showTeleportList(player, this, "npc_" + getObjectId() + "_goto");
						}
					}
				} else if (val.equalsIgnoreCase("support")) {
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					if (getFort().getFortFunction(Fort.FUNC_SUPPORT) == null) {
						html.setFile(player.getLang(), "fortress/foreman-nac.htm");
					} else {
						html.setFile(player.getLang(), "fortress/support" + getFort().getFortFunction(Fort.FUNC_SUPPORT).getLvl() + ".htm");
						html.replace("%mp%", String.valueOf((int) getCurrentMp()));
					}
					sendHtmlMessage(player, html);
				} else if (val.equalsIgnoreCase("back")) {
					showChatWindow(player);
				} else {
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player.getLang(), "fortress/foreman-functions.htm");
					if (getFort().getFortFunction(Fort.FUNC_RESTORE_EXP) != null) {
						html.replace("%xp_regen%", String.valueOf(getFort().getFortFunction(Fort.FUNC_RESTORE_EXP).getLvl()));
					} else {
						html.replace("%xp_regen%", "0");
					}
					if (getFort().getFortFunction(Fort.FUNC_RESTORE_HP) != null) {
						html.replace("%hp_regen%", String.valueOf(getFort().getFortFunction(Fort.FUNC_RESTORE_HP).getLvl()));
					} else {
						html.replace("%hp_regen%", "0");
					}
					if (getFort().getFortFunction(Fort.FUNC_RESTORE_MP) != null) {
						html.replace("%mp_regen%", String.valueOf(getFort().getFortFunction(Fort.FUNC_RESTORE_MP).getLvl()));
					} else {
						html.replace("%mp_regen%", "0");
					}
					sendHtmlMessage(player, html);
				}
				return;
			} else if (actualCommand.equalsIgnoreCase("manage")) {
				if (player.hasClanPrivilege(ClanPrivilege.CS_SET_FUNCTIONS)) {
					if (val.equalsIgnoreCase("recovery")) {
						if (st.countTokens() >= 1) {
							if (getFort().getOwnerClan() == null) {
								player.sendMessage("This fortress has no owner, you cannot change the configuration.");
								return;
							}
							val = st.nextToken();
							if (val.equalsIgnoreCase("hp_cancel")) {
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-cancel.htm");
								html.replace("%apply%", "recovery hp 0");
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("mp_cancel")) {
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-cancel.htm");
								html.replace("%apply%", "recovery mp 0");
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("exp_cancel")) {
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-cancel.htm");
								html.replace("%apply%", "recovery exp 0");
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("edit_hp")) {
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-apply.htm");
								html.replace("%name%", "(HP Recovery Device)");
								int percent = Integer.parseInt(val);
								int cost;
								switch (percent) {
									case 300:
										cost = FeatureConfig.FS_HPREG1_FEE;
										break;
									default: // 400
										cost = FeatureConfig.FS_HPREG2_FEE;
										break;
								}

								html.replace("%cost%", String.valueOf(cost) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_HPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Provides additional HP recovery for clan members in the fortress.<font color=\"00FFFF\">" + String.valueOf(percent) + "%</font>");
								html.replace("%apply%", "recovery hp " + String.valueOf(percent));
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("edit_mp")) {
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-apply.htm");
								html.replace("%name%", "(MP Recovery)");
								int percent = Integer.parseInt(val);
								int cost;
								switch (percent) {
									case 40:
										cost = FeatureConfig.FS_MPREG1_FEE;
										break;
									default: // 50
										cost = FeatureConfig.FS_MPREG2_FEE;
										break;
								}
								html.replace("%cost%", String.valueOf(cost) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_MPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Provides additional MP recovery for clan members in the fortress.<font color=\"00FFFF\">" + String.valueOf(percent) + "%</font>");
								html.replace("%apply%", "recovery mp " + String.valueOf(percent));
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("edit_exp")) {
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-apply.htm");
								html.replace("%name%", "(EXP Recovery Device)");
								int percent = Integer.parseInt(val);
								int cost;
								switch (percent) {
									case 45:
										cost = FeatureConfig.FS_EXPREG1_FEE;
										break;
									default: // 50
										cost = FeatureConfig.FS_EXPREG2_FEE;
										break;
								}
								html.replace("%cost%", String.valueOf(cost) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_EXPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Restores the Exp of any clan member who is resurrected in the fortress.<font color=\"00FFFF\">" + String.valueOf(percent) + "%</font>");
								html.replace("%apply%", "recovery exp " + String.valueOf(percent));
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("hp")) {
								if (st.countTokens() >= 1) {
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player.getLang(), "fortress/functions-apply_confirmed.htm");
									if (getFort().getFortFunction(Fort.FUNC_RESTORE_HP) != null) {
										if (getFort().getFortFunction(Fort.FUNC_RESTORE_HP).getLvl() == Integer.parseInt(val)) {
											html.setFile(player.getLang(), "fortress/functions-used.htm");
											html.replace("%val%", String.valueOf(val) + "%");
											sendHtmlMessage(player, html);
											return;
										}
									}
									int percent = Integer.parseInt(val);
									switch (percent) {
										case 0:
											fee = 0;
											html.setFile(player.getLang(), "fortress/functions-cancel_confirmed.htm");
											break;
										case 300:
											fee = FeatureConfig.FS_HPREG1_FEE;
											break;
										default: // 400
											fee = FeatureConfig.FS_HPREG2_FEE;
											break;
									}
									if (!getFort().updateFunctions(player, Fort.FUNC_RESTORE_HP, percent, fee, FeatureConfig.FS_HPREG_FEE_RATIO, (getFort().getFortFunction(Fort.FUNC_RESTORE_HP) == null))) {
										html.setFile(player.getLang(), "fortress/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									sendHtmlMessage(player, html);
								}
								return;
							} else if (val.equalsIgnoreCase("mp")) {
								if (st.countTokens() >= 1) {
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player.getLang(), "fortress/functions-apply_confirmed.htm");
									if (getFort().getFortFunction(Fort.FUNC_RESTORE_MP) != null) {
										if (getFort().getFortFunction(Fort.FUNC_RESTORE_MP).getLvl() == Integer.parseInt(val)) {
											html.setFile(player.getLang(), "fortress/functions-used.htm");
											html.replace("%val%", String.valueOf(val) + "%");
											sendHtmlMessage(player, html);
											return;
										}
									}
									int percent = Integer.parseInt(val);
									switch (percent) {
										case 0:
											fee = 0;
											html.setFile(player.getLang(), "fortress/functions-cancel_confirmed.htm");
											break;
										case 40:
											fee = FeatureConfig.FS_MPREG1_FEE;
											break;
										default: // 50
											fee = FeatureConfig.FS_MPREG2_FEE;
											break;
									}
									if (!getFort().updateFunctions(player, Fort.FUNC_RESTORE_MP, percent, fee, FeatureConfig.FS_MPREG_FEE_RATIO, (getFort().getFortFunction(Fort.FUNC_RESTORE_MP) == null))) {
										html.setFile(player.getLang(), "fortress/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									sendHtmlMessage(player, html);
								}
								return;
							} else if (val.equalsIgnoreCase("exp")) {
								if (st.countTokens() >= 1) {
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player.getLang(), "fortress/functions-apply_confirmed.htm");
									if (getFort().getFortFunction(Fort.FUNC_RESTORE_EXP) != null) {
										if (getFort().getFortFunction(Fort.FUNC_RESTORE_EXP).getLvl() == Integer.parseInt(val)) {
											html.setFile(player.getLang(), "fortress/functions-used.htm");
											html.replace("%val%", String.valueOf(val) + "%");
											sendHtmlMessage(player, html);
											return;
										}
									}
									int percent = Integer.parseInt(val);
									switch (percent) {
										case 0:
											fee = 0;
											html.setFile(player.getLang(), "fortress/functions-cancel_confirmed.htm");
											break;
										case 45:
											fee = FeatureConfig.FS_EXPREG1_FEE;
											break;
										default: // 50
											fee = FeatureConfig.FS_EXPREG2_FEE;
											break;
									}
									if (!getFort().updateFunctions(player, Fort.FUNC_RESTORE_EXP, percent, fee, FeatureConfig.FS_EXPREG_FEE_RATIO, (getFort().getFortFunction(Fort.FUNC_RESTORE_EXP) == null))) {
										html.setFile(player.getLang(), "fortress/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									sendHtmlMessage(player, html);
								}
								return;
							}
						}
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player.getLang(), "fortress/edit_recovery.htm");
						String hp = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 300\">300%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_hp 400\">400%</a>]";
						String exp = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 45\">45%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_exp 50\">50%</a>]";
						String mp = "[<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 40\">40%</a>][<a action=\"bypass -h npc_%objectId%_manage recovery edit_mp 50\">50%</a>]";
						if (getFort().getFortFunction(Fort.FUNC_RESTORE_HP) != null) {
							html.replace("%hp_recovery%", String.valueOf(getFort().getFortFunction(Fort.FUNC_RESTORE_HP).getLvl()) + "%</font> (<font color=\"FFAABB\">" + String.valueOf(getFort().getFortFunction(Fort.FUNC_RESTORE_HP).getLease()) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_HPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%hp_period%", "Withdraw the fee for the next time at " + format.format(getFort().getFortFunction(Fort.FUNC_RESTORE_HP).getEndTime()));
							html.replace("%change_hp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery hp_cancel\">Deactivate</a>]" + hp);
						} else {
							html.replace("%hp_recovery%", "none");
							html.replace("%hp_period%", "none");
							html.replace("%change_hp%", hp);
						}
						if (getFort().getFortFunction(Fort.FUNC_RESTORE_EXP) != null) {
							html.replace("%exp_recovery%", String.valueOf(getFort().getFortFunction(Fort.FUNC_RESTORE_EXP).getLvl()) + "%</font> (<font color=\"FFAABB\">" + String.valueOf(getFort().getFortFunction(Fort.FUNC_RESTORE_EXP).getLease()) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_EXPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%exp_period%", "Withdraw the fee for the next time at " + format.format(getFort().getFortFunction(Fort.FUNC_RESTORE_EXP).getEndTime()));
							html.replace("%change_exp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery exp_cancel\">Deactivate</a>]" + exp);
						} else {
							html.replace("%exp_recovery%", "none");
							html.replace("%exp_period%", "none");
							html.replace("%change_exp%", exp);
						}
						if (getFort().getFortFunction(Fort.FUNC_RESTORE_MP) != null) {
							html.replace("%mp_recovery%", String.valueOf(getFort().getFortFunction(Fort.FUNC_RESTORE_MP).getLvl()) + "%</font> (<font color=\"FFAABB\">" + String.valueOf(getFort().getFortFunction(Fort.FUNC_RESTORE_MP).getLease()) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_MPREG_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%mp_period%", "Withdraw the fee for the next time at " + format.format(getFort().getFortFunction(Fort.FUNC_RESTORE_MP).getEndTime()));
							html.replace("%change_mp%", "[<a action=\"bypass -h npc_%objectId%_manage recovery mp_cancel\">Deactivate</a>]" + mp);
						} else {
							html.replace("%mp_recovery%", "none");
							html.replace("%mp_period%", "none");
							html.replace("%change_mp%", mp);
						}
						sendHtmlMessage(player, html);
					} else if (val.equalsIgnoreCase("other")) {
						if (st.countTokens() >= 1) {
							if (getFort().getOwnerClan() == null) {
								player.sendMessage("This fortress has no owner, you cannot change the configuration.");
								return;
							}
							val = st.nextToken();
							if (val.equalsIgnoreCase("tele_cancel")) {
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-cancel.htm");
								html.replace("%apply%", "other tele 0");
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("support_cancel")) {
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-cancel.htm");
								html.replace("%apply%", "other support 0");
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("edit_support")) {
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-apply.htm");
								html.replace("%name%", "Insignia (Supplementary Magic)");
								int stage = Integer.parseInt(val);
								int cost;
								switch (stage) {
									case 1:
										cost = FeatureConfig.FS_SUPPORT1_FEE;
										break;
									default:
										cost = FeatureConfig.FS_SUPPORT2_FEE;
										break;
								}
								html.replace("%cost%", String.valueOf(cost) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_SUPPORT_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Enables the use of supplementary magic.");
								html.replace("%apply%", "other support " + String.valueOf(stage));
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("edit_tele")) {
								val = st.nextToken();
								final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
								html.setFile(player.getLang(), "fortress/functions-apply.htm");
								html.replace("%name%", "Mirror (Teleportation Device)");
								int stage = Integer.parseInt(val);
								int cost;
								switch (stage) {
									case 1:
										cost = FeatureConfig.FS_TELE1_FEE;
										break;
									default:
										cost = FeatureConfig.FS_TELE2_FEE;
										break;
								}
								html.replace("%cost%", String.valueOf(cost) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_TELE_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day</font>)");
								html.replace("%use%", "Teleports clan members in a fort to the target <font color=\"00FFFF\">Stage " + String.valueOf(stage) + "</font> staging area");
								html.replace("%apply%", "other tele " + String.valueOf(stage));
								sendHtmlMessage(player, html);
								return;
							} else if (val.equalsIgnoreCase("tele")) {
								if (st.countTokens() >= 1) {
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player.getLang(), "fortress/functions-apply_confirmed.htm");
									if (getFort().getFortFunction(Fort.FUNC_TELEPORT) != null) {
										if (getFort().getFortFunction(Fort.FUNC_TELEPORT).getLvl() == Integer.parseInt(val)) {
											html.setFile(player.getLang(), "fortress/functions-used.htm");
											html.replace("%val%", "Stage " + String.valueOf(val));
											sendHtmlMessage(player, html);
											return;
										}
									}
									int lvl = Integer.parseInt(val);
									switch (lvl) {
										case 0:
											fee = 0;
											html.setFile(player.getLang(), "fortress/functions-cancel_confirmed.htm");
											break;
										case 1:
											fee = FeatureConfig.FS_TELE1_FEE;
											break;
										default:
											fee = FeatureConfig.FS_TELE2_FEE;
											break;
									}
									if (!getFort().updateFunctions(player, Fort.FUNC_TELEPORT, lvl, fee, FeatureConfig.FS_TELE_FEE_RATIO, (getFort().getFortFunction(Fort.FUNC_TELEPORT) == null))) {
										html.setFile(player.getLang(), "fortress/low_adena.htm");
										sendHtmlMessage(player, html);
									}
									sendHtmlMessage(player, html);
								}
								return;
							} else if (val.equalsIgnoreCase("support")) {
								if (st.countTokens() >= 1) {
									int fee;
									val = st.nextToken();
									final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
									html.setFile(player.getLang(), "fortress/functions-apply_confirmed.htm");
									if (getFort().getFortFunction(Fort.FUNC_SUPPORT) != null) {
										if (getFort().getFortFunction(Fort.FUNC_SUPPORT).getLvl() == Integer.parseInt(val)) {
											html.setFile(player.getLang(), "fortress/functions-used.htm");
											html.replace("%val%", "Stage " + String.valueOf(val));
											sendHtmlMessage(player, html);
											return;
										}
									}
									int lvl = Integer.parseInt(val);
									switch (lvl) {
										case 0:
											fee = 0;
											html.setFile(player.getLang(), "fortress/functions-cancel_confirmed.htm");
											break;
										case 1:
											fee = FeatureConfig.FS_SUPPORT1_FEE;
											break;
										default:
											fee = FeatureConfig.FS_SUPPORT2_FEE;
											break;
									}
									if (!getFort().updateFunctions(player, Fort.FUNC_SUPPORT, lvl, fee, FeatureConfig.FS_SUPPORT_FEE_RATIO, (getFort().getFortFunction(Fort.FUNC_SUPPORT) == null))) {
										html.setFile(player.getLang(), "fortress/low_adena.htm");
										sendHtmlMessage(player, html);
									} else {
										sendHtmlMessage(player, html);
									}
								}
								return;
							}
						}
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player.getLang(), "fortress/edit_other.htm");
						String tele = "[<a action=\"bypass -h npc_%objectId%_manage other edit_tele 1\">Level 1</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_tele 2\">Level 2</a>]";
						String support = "[<a action=\"bypass -h npc_%objectId%_manage other edit_support 1\">Level 1</a>][<a action=\"bypass -h npc_%objectId%_manage other edit_support 2\">Level 2</a>]";
						if (getFort().getFortFunction(Fort.FUNC_TELEPORT) != null) {
							html.replace("%tele%", "Stage " + String.valueOf(getFort().getFortFunction(Fort.FUNC_TELEPORT).getLvl()) + "</font> (<font color=\"FFAABB\">" + String.valueOf(getFort().getFortFunction(Fort.FUNC_TELEPORT).getLease()) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_TELE_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%tele_period%", "Withdraw the fee for the next time at " + format.format(getFort().getFortFunction(Fort.FUNC_TELEPORT).getEndTime()));
							html.replace("%change_tele%", "[<a action=\"bypass -h npc_%objectId%_manage other tele_cancel\">Deactivate</a>]" + tele);
						} else {
							html.replace("%tele%", "none");
							html.replace("%tele_period%", "none");
							html.replace("%change_tele%", tele);
						}
						if (getFort().getFortFunction(Fort.FUNC_SUPPORT) != null) {
							html.replace("%support%", "Stage " + String.valueOf(getFort().getFortFunction(Fort.FUNC_SUPPORT).getLvl()) + "</font> (<font color=\"FFAABB\">" + String.valueOf(getFort().getFortFunction(Fort.FUNC_SUPPORT).getLease()) + "</font>Adena /" + String.valueOf(FeatureConfig.FS_SUPPORT_FEE_RATIO / 1000 / 60 / 60 / 24) + " Day)");
							html.replace("%support_period%", "Withdraw the fee for the next time at " + format.format(getFort().getFortFunction(Fort.FUNC_SUPPORT).getEndTime()));
							html.replace("%change_support%", "[<a action=\"bypass -h npc_%objectId%_manage other support_cancel\">Deactivate</a>]" + support);
						} else {
							html.replace("%support%", "none");
							html.replace("%support_period%", "none");
							html.replace("%change_support%", support);
						}
						sendHtmlMessage(player, html);
					} else if (val.equalsIgnoreCase("back")) {
						showChatWindow(player);
					} else {
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						html.setFile(player.getLang(), "fortress/manage.htm");
						sendHtmlMessage(player, html);
					}
				} else {
					final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
					html.setFile(player.getLang(), "fortress/foreman-noprivs.htm");
					sendHtmlMessage(player, html);
				}
				return;
			} else if (actualCommand.equalsIgnoreCase("support")) {
				setTarget(player);
				Skill skill;
				if (val.isEmpty()) {
					return;
				}

				try {
					int skill_id = Integer.parseInt(val);
					try {
						if (getFort().getFortFunction(Fort.FUNC_SUPPORT) == null) {
							return;
						}
						if (getFort().getFortFunction(Fort.FUNC_SUPPORT).getLvl() == 0) {
							return;
						}
						final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
						int skill_lvl = 0;
						if (st.countTokens() >= 1) {
							skill_lvl = Integer.parseInt(st.nextToken());
						}
						skill = SkillData.getInstance().getSkill(skill_id, skill_lvl);
						if (skill.hasEffectType(L2EffectType.SUMMON)) {
							player.doCast(skill);
						} else {
							if (!((skill.getMpConsume() + skill.getMpInitialConsume()) > getCurrentMp())) {
								this.doCast(skill);
							} else {
								html.setFile(player.getLang(), "fortress/support-no_mana.htm");
								html.replace("%mp%", String.valueOf((int) getCurrentMp()));
								sendHtmlMessage(player, html);
								return;
							}
						}
						html.setFile(player.getLang(), "fortress/support-done.htm");
						html.replace("%mp%", String.valueOf((int) getCurrentMp()));
						sendHtmlMessage(player, html);
					} catch (Exception e) {
						player.sendMessage("Invalid skill level, contact your admin!");
					}
				} catch (Exception e) {
					player.sendMessage("Invalid skill level, contact your admin!");
				}
				return;
			} else if (actualCommand.equalsIgnoreCase("support_back")) {
				final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
				if (getFort().getFortFunction(Fort.FUNC_SUPPORT).getLvl() == 0) {
					return;
				}
				html.setFile(player.getLang(), "fortress/support" + getFort().getFortFunction(Fort.FUNC_SUPPORT).getLvl() + ".htm");
				html.replace("%mp%", String.valueOf((int) getStatus().getCurrentMp()));
				sendHtmlMessage(player, html);
				return;
			} else if (actualCommand.equalsIgnoreCase("goto")) // goto listId locId
			{
				final FortFunction func = getFort().getFortFunction(Fort.FUNC_TELEPORT);
				if ((func == null) || !st.hasMoreTokens()) {
					return;
				}

				final int funcLvl = (val.length() >= 4) ? CommonUtil.parseInt(val.substring(3), -1) : -1;
				if (func.getLvl() == funcLvl) {
					final TeleportHolder holder = TeleportersData.getInstance().getHolder(getId(), val);
					if (holder != null) {
						holder.doTeleport(player, this, CommonUtil.parseNextInt(st, -1));
					}
				}
				return;
			}
			super.onBypassFeedback(player, command);
		}
	}

	@Override
	public void showChatWindow(Player player) {
		player.sendPacket(ActionFailed.STATIC_PACKET);
		String filename = "fortress/foreman-no.htm";

		int condition = validateCondition(player);
		if (condition > COND_ALL_FALSE) {
			if (condition == COND_BUSY_BECAUSE_OF_SIEGE) {
				filename = "fortress/foreman-busy.htm"; // Busy because of siege
			} else if (condition == COND_OWNER) {
				filename = "fortress/foreman.htm"; // Owner message window
			}
		}

		final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		html.setFile(player.getLang(), filename);
		html.replace("%objectId%", String.valueOf(getObjectId()));
		html.replace("%npcname%", getName());
		player.sendPacket(html);
	}

	protected int validateCondition(Player player) {
		if ((getFort() != null) && (getFort().getResidenceId() > 0)) {
			if (player.getClan() != null) {
				if (getFort().getZone().isActive()) {
					return COND_BUSY_BECAUSE_OF_SIEGE; // Busy because of siege
				} else if ((getFort().getOwnerClan() != null) && (getFort().getOwnerClan().getId() == player.getClanId())) {
					return COND_OWNER; // Owner
				}
			}
		}
		return COND_ALL_FALSE;
	}

	private void showVaultWindowDeposit(Player player) {
		player.sendPacket(ActionFailed.STATIC_PACKET);
		player.setActiveWarehouse(player.getClan().getWarehouse());
		player.sendPacket(new WareHouseDepositList(player, WareHouseDepositList.CLAN));
	}

	private void showVaultWindowWithdraw(Player player) {
		if (player.isClanLeader() || player.hasClanPrivilege(ClanPrivilege.CL_VIEW_WAREHOUSE)) {
			player.sendPacket(ActionFailed.STATIC_PACKET);
			player.setActiveWarehouse(player.getClan().getWarehouse());
			player.sendPacket(new WareHouseWithdrawalList(player, WareHouseWithdrawalList.CLAN));
		} else {
			final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
			html.setFile(player.getLang(), "fortress/foreman-noprivs.htm");
			sendHtmlMessage(player, html);
		}
	}
}
