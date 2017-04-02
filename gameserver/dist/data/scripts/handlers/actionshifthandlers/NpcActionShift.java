/*
 * Copyright (C) 2004-2015 L2J DataPack
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
package handlers.actionshifthandlers;

import handlers.bypasshandlers.NpcViewMod;
import org.l2junity.commons.util.CommonUtil;
import org.l2junity.core.configs.NpcConfig;
import org.l2junity.gameserver.data.xml.impl.ClanHallData;
import org.l2junity.gameserver.enums.AttributeType;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.handler.ActionShiftHandler;
import org.l2junity.gameserver.handler.IActionShiftHandler;
import org.l2junity.gameserver.instancemanager.QuestManager;
import org.l2junity.gameserver.instancemanager.SuperpointManager;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.entity.ClanHall;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.spawns.NpcSpawnTemplate;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;

import java.util.Set;

public class NpcActionShift implements IActionShiftHandler {
	@Override
	public boolean action(PlayerInstance activeChar, WorldObject target, boolean interact) {
		// Check if the L2PcInstance is a GM
		if (activeChar.isGM()) {
			// Set the target of the L2PcInstance activeChar
			activeChar.setTarget(target);

			final Npc npc = (Npc) target;
			final NpcHtmlMessage html = new NpcHtmlMessage(0, 1);
			final ClanHall clanHall = ClanHallData.getInstance().getClanHallByNpcId(npc.getId());
			html.setFile(activeChar.getHtmlPrefix(), "data/html/admin/npcinfo.htm");

			html.replace("%objid%", String.valueOf(target.getObjectId()));
			html.replace("%class%", target.getClass().getSimpleName());
			html.replace("%race%", npc.getTemplate().getRace().toString());
			html.replace("%id%", String.valueOf(npc.getTemplate().getId()));
			html.replace("%lvl%", String.valueOf(npc.getTemplate().getLevel()));
			html.replace("%name%", String.valueOf(npc.getTemplate().getName()));
			html.replace("%tmplid%", String.valueOf(npc.getTemplate().getId()));
			html.replace("%aggro%", String.valueOf((target instanceof Attackable) ? ((Attackable) target).getAggroRange() : 0));
			html.replace("%hp%", String.valueOf((int) npc.getCurrentHp()));
			html.replace("%hpmax%", String.valueOf(npc.getMaxHp()));
			html.replace("%mp%", String.valueOf((int) npc.getCurrentMp()));
			html.replace("%mpmax%", String.valueOf(npc.getMaxMp()));

			html.replace("%patk%", String.valueOf(npc.getPAtk()));
			html.replace("%matk%", String.valueOf(npc.getMAtk()));
			html.replace("%pdef%", String.valueOf(npc.getPDef()));
			html.replace("%mdef%", String.valueOf(npc.getMDef()));
			html.replace("%accu%", String.valueOf(npc.getAccuracy()));
			html.replace("%evas%", String.valueOf(npc.getEvasionRate()));
			html.replace("%crit%", String.valueOf(npc.getCriticalHit()));
			html.replace("%rspd%", String.valueOf(npc.getMoveSpeed()));
			html.replace("%aspd%", String.valueOf(npc.getPAtkSpd()));
			html.replace("%cspd%", String.valueOf(npc.getMAtkSpd()));
			html.replace("%atkType%", String.valueOf(npc.getTemplate().getBaseAttackType()));
			html.replace("%atkRng%", String.valueOf(npc.getTemplate().getBaseAttackRange()));
			html.replace("%str%", String.valueOf(npc.getSTR()));
			html.replace("%dex%", String.valueOf(npc.getDEX()));
			html.replace("%con%", String.valueOf(npc.getCON()));
			html.replace("%int%", String.valueOf(npc.getINT()));
			html.replace("%wit%", String.valueOf(npc.getWIT()));
			html.replace("%men%", String.valueOf(npc.getMEN()));
			html.replace("%loc%", String.valueOf(target.getX() + " " + target.getY() + " " + target.getZ()));
			html.replace("%heading%", String.valueOf(npc.getHeading()));
			html.replace("%collision_radius%", String.valueOf(npc.getTemplate().getfCollisionRadius()));
			html.replace("%collision_height%", String.valueOf(npc.getTemplate().getfCollisionHeight()));
			html.replace("%dist%", String.valueOf((int) activeChar.distance3d(target)));
			html.replace("%clanHall%", clanHall != null ? clanHall.getName() : "none");
			html.replace("%mpRewardValue%", npc.getTemplate().getMpRewardValue());
			html.replace("%mpRewardTicks%", npc.getTemplate().getMpRewardTicks());
			html.replace("%mpRewardType%", npc.getTemplate().getMpRewardType().name());
			html.replace("%mpRewardAffectType%", npc.getTemplate().getMpRewardAffectType().name());

			AttributeType attackAttribute = npc.getAttackElement();
			html.replace("%ele_atk%", attackAttribute.name());
			html.replace("%ele_atk_value%", String.valueOf(npc.getAttackElementValue(attackAttribute)));
			html.replace("%ele_dfire%", String.valueOf(npc.getDefenseElementValue(AttributeType.FIRE)));
			html.replace("%ele_dwater%", String.valueOf(npc.getDefenseElementValue(AttributeType.WATER)));
			html.replace("%ele_dwind%", String.valueOf(npc.getDefenseElementValue(AttributeType.WIND)));
			html.replace("%ele_dearth%", String.valueOf(npc.getDefenseElementValue(AttributeType.EARTH)));
			html.replace("%ele_dholy%", String.valueOf(npc.getDefenseElementValue(AttributeType.HOLY)));
			html.replace("%ele_ddark%", String.valueOf(npc.getDefenseElementValue(AttributeType.DARK)));

			final L2Spawn spawn = npc.getSpawn();
			if (spawn != null) {
				final NpcSpawnTemplate template = spawn.getNpcSpawnTemplate();
				if (template != null) {
					final String fileName = template.getSpawnTemplate().getPath().toString();
					html.replace("%spawnfile%", fileName);
					html.replace("%spawnname%", String.valueOf(template.getSpawnTemplate().getName()));
					html.replace("%spawngroup%", String.valueOf(template.getGroup().getName()));
					if (template.getSpawnTemplate().getAI() != null) {
						final Quest script = QuestManager.getInstance().getQuest(template.getSpawnTemplate().getAI());
						if (script != null) {
							html.replace("%spawnai%", "<a action=\"bypass -h admin_quest_info " + script.getName() + "\"><font color=\"LEVEL\">" + script.getName() + "</font></a>");
						}
					}
					html.replace("%spawnai%", "<font color=FF0000>" + template.getSpawnTemplate().getAI() + "</font>");
				}

				html.replace("%spawn%", npc.getSpawn().getX() + " " + npc.getSpawn().getY() + " " + npc.getSpawn().getZ());
				html.replace("%loc2d%", String.valueOf((int) npc.distance2d(npc.getSpawn())));
				html.replace("%loc3d%", String.valueOf((int) npc.distance3d(npc.getSpawn())));
				if (npc.getSpawn().getRespawnMinDelay() == 0) {
					html.replace("%resp%", "None");
				} else if (npc.getSpawn().hasRespawnRandom()) {
					html.replace("%resp%", String.valueOf(npc.getSpawn().getRespawnMinDelay() / 1000) + "-" + String.valueOf((npc.getSpawn().getRespawnMaxDelay() / 1000) + " sec"));
				} else {
					html.replace("%resp%", String.valueOf(npc.getSpawn().getRespawnMinDelay() / 1000) + " sec");
				}
			} else {
				html.replace("%spawn%", "<font color=FF0000>null</font>");
				html.replace("%loc2d%", "<font color=FF0000>--</font>");
				html.replace("%loc3d%", "<font color=FF0000>--</font>");
				html.replace("%resp%", "<font color=FF0000>--</font>");
			}

			html.replace("%spawnfile%", "<font color=FF0000>--</font>");
			html.replace("%spawnname%", "<font color=FF0000>--</font>");
			html.replace("%spawngroup%", "<font color=FF0000>--</font>");
			html.replace("%spawnai%", "<font color=FF0000>--</font>");

			if (npc.hasAI()) {
				Set<Integer> clans = npc.getTemplate().getClans();
				Set<Integer> ignoreClanNpcIds = npc.getTemplate().getIgnoreClanNpcIds();
				String clansString = clans != null ? CommonUtil.implode(clans, ", ") : "";
				String ignoreClanNpcIdsString = ignoreClanNpcIds != null ? CommonUtil.implode(ignoreClanNpcIds, ", ") : "";

				html.replace("%ai_intention%", "<tr><td><table width=270 border=0 bgcolor=131210><tr><td width=100><font color=FFAA00>Intention:</font></td><td align=right width=170>" + String.valueOf(npc.getAI().getIntention().name()) + "</td></tr></table></td></tr>");
				html.replace("%ai%", "<tr><td><table width=270 border=0><tr><td width=100><font color=FFAA00>AI</font></td><td align=right width=170>" + npc.getAI().getClass().getSimpleName() + "</td></tr></table></td></tr>");
				html.replace("%ai_type%", "<tr><td><table width=270 border=0 bgcolor=131210><tr><td width=100><font color=FFAA00>AIType</font></td><td align=right width=170>" + String.valueOf(npc.getAiType()) + "</td></tr></table></td></tr>");
				html.replace("%ai_clan%", "<tr><td><table width=270 border=0><tr><td width=100><font color=FFAA00>Clan & Range:</font></td><td align=right width=170>" + clansString + " " + String.valueOf(npc.getTemplate().getClanHelpRange()) + "</td></tr></table></td></tr>");
				html.replace("%ai_enemy_clan%", "<tr><td><table width=270 border=0 bgcolor=131210><tr><td width=100><font color=FFAA00>Ignore & Range:</font></td><td align=right width=170>" + ignoreClanNpcIdsString + " " + String.valueOf(npc.getTemplate().getAggroRange()) + "</td></tr></table></td></tr>");
			} else {
				html.replace("%ai_intention%", "");
				html.replace("%ai%", "");
				html.replace("%ai_type%", "");
				html.replace("%ai_clan%", "");
				html.replace("%ai_enemy_clan%", "");
			}

			final String routeName = SuperpointManager.getInstance().getRouteName(npc);
			if (!routeName.isEmpty()) {
				html.replace("%route%", "<tr><td><table width=270 border=0><tr><td width=100><font color=LEVEL>Route:</font></td><td align=right width=170>" + routeName + "</td></tr></table></td></tr>");
			} else {
				html.replace("%route%", "");
			}
			activeChar.sendPacket(html);
		} else if (NpcConfig.ALT_GAME_VIEWNPC) {
			if (!target.isNpc()) {
				return false;
			}
			activeChar.setTarget(target);
			NpcViewMod.sendNpcView(activeChar, (Npc) target);
		}
		return true;
	}

	@Override
	public InstanceType getInstanceType() {
		return InstanceType.L2Npc;
	}

	public static void main(String[] args) {
		ActionShiftHandler.getInstance().registerHandler(new NpcActionShift());
	}
}