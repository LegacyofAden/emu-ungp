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
package org.l2junity.gameserver.model.instancezone.conditions;

import org.l2junity.gameserver.instancemanager.InstanceManager;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.instancezone.InstanceTemplate;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

import java.util.Arrays;

/**
 * Instance reenter conditions
 *
 * @author malyelfik
 */
public final class ConditionReenter extends Condition {

	public ConditionReenter(InstanceTemplate template, StatsSet parameters, boolean onlyLeader, boolean showMessageAndHtml) {
		super(template, parameters, onlyLeader, showMessageAndHtml);
		setSystemMessage(SystemMessageId.C1_MAY_NOT_RE_ENTER_YET, (message, player) -> message.addCharName(player));
	}

	@Override
	protected boolean test(Player player, Npc npc) {
		final String templateIds = getParameters().getString("instanceId", String.valueOf(getInstanceTemplate().getId()));
		//@formatter:off
		return Arrays.stream(templateIds.split(";"))
				.mapToInt(Integer::parseInt)
				.mapToLong(templateId -> InstanceManager.getInstance().getInstanceTime(player, templateId))
				.allMatch(reenterTime -> System.currentTimeMillis() > reenterTime);
		//@formatter:on
	}
}