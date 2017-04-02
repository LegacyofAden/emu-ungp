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

import org.l2junity.gameserver.data.xml.impl.ClanHallData;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.handler.ActionShiftHandler;
import org.l2junity.gameserver.handler.IActionShiftHandler;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.DoorInstance;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.model.entity.ClanHall;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.model.stats.DoubleStat;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;
import org.l2junity.gameserver.network.client.send.StaticObject;
import org.l2junity.gameserver.util.HtmlUtil;

/**
 * This class manage shift + click on {@link DoorInstance}.
 *
 * @author St3eT
 */
public class DoorInstanceActionShift implements IActionShiftHandler {
	@Override
	public boolean action(PlayerInstance activeChar, WorldObject target, boolean interact) {
		if (activeChar.isGM()) {
			activeChar.setTarget(target);
			final DoorInstance door = (DoorInstance) target;
			final ClanHall clanHall = ClanHallData.getInstance().getClanHallByDoorId(door.getId());
			final Fort fort = door.getFort();
			final Castle castle = door.getCastle();
			activeChar.sendPacket(new StaticObject(door, activeChar.isGM()));
			final NpcHtmlMessage html = new NpcHtmlMessage(0, 1);
			html.setFile(activeChar.getHtmlPrefix(), "data/html/admin/doorinfo.htm");
			// Hp / MP
			html.replace("%hpGauge%", HtmlUtil.getHpGauge(250, (long) door.getCurrentHp(), door.getMaxHp(), false));
			html.replace("%mpGauge%", HtmlUtil.getMpGauge(250, (long) door.getCurrentMp(), door.getMaxMp(), false));
			// Basic info
			html.replace("%doorName%", door.getName());
			html.replace("%objId%", String.valueOf(door.getObjectId()));
			html.replace("%doorId%", String.valueOf(door.getId()));
			html.replace("%defence%", String.valueOf(door.getStat().getValue(DoubleStat.PHYSICAL_DEFENCE)));
			// Position info
			html.replace("%position%", door.getX() + ", " + door.getY() + ", " + door.getZ());
			html.replace("%node1%", door.getX(0) + ", " + door.getY(0) + ", " + door.getZMin());
			html.replace("%node2%", door.getX(1) + ", " + door.getY(1) + ", " + door.getZMin());
			html.replace("%node3%", door.getX(2) + ", " + door.getY(2) + ", " + door.getZMax());
			html.replace("%node4%", door.getX(3) + ", " + door.getY(3) + ", " + door.getZMax());
			// Residence info
			html.replace("%fortress%", fort != null ? fort.getName() : "None");
			html.replace("%clanHall%", clanHall != null ? clanHall.getName() : "None");
			html.replace("%castle%", castle != null ? castle.getName() + " Castle" : "None");
			activeChar.sendPacket(html);
		}
		return true;
	}

	@Override
	public InstanceType getInstanceType() {
		return InstanceType.DoorInstance;
	}

	public static void main(String[] args) {
		ActionShiftHandler.getInstance().registerHandler(new DoorInstanceActionShift());
	}
}