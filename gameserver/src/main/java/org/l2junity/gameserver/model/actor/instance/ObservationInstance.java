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

import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.network.packets.s2c.NpcHtmlMessage;

/**
 * @author NightMarez
 * @version $Revision: 1.3.2.2.2.5 $ $Date: 2005/03/27 15:29:32 $
 */
public final class ObservationInstance extends Npc {
	public ObservationInstance(NpcTemplate template) {
		super(template);
		setInstanceType(InstanceType.L2ObservationInstance);
	}

	@Override
	public void showChatWindow(Player player, int val) {
		String filename = null;

		if (isInRadius2d(-79884, 86529, 50) || isInRadius2d(-78858, 111358, 50) || isInRadius2d(-76973, 87136, 50) || isInRadius2d(-75850, 111968, 50)) {
			if (val == 0) {
				filename = "observation/" + getId() + "-Oracle.htm";
			} else {
				filename = "observation/" + getId() + "-Oracle-" + val + ".htm";
			}
		} else {
			if (val == 0) {
				filename = "observation/" + getId() + ".htm";
			} else {
				filename = "observation/" + getId() + "-" + val + ".htm";
			}
		}

		final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		html.setFile(player.getLang(), filename);
		html.replace("%objectId%", String.valueOf(getObjectId()));
		player.sendPacket(html);
	}
}