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
package handlers.effecthandlers.instant;

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.FlyToLocation;
import org.l2junity.gameserver.network.client.send.FlyToLocation.FlyType;
import org.l2junity.gameserver.network.client.send.ValidateLocation;
import org.l2junity.gameserver.util.Util;

/**
 * Teleport To Target effect implementation.
 *
 * @author Didldak, Adry_85
 */
public final class InstantTeleportToSummon extends AbstractEffect {
	private final double _maxDistance;

	public InstantTeleportToSummon(StatsSet params) {
		_maxDistance = params.getDouble("distance", -1);
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final PlayerInstance targetPlayer = target.asPlayer();
		if (targetPlayer == null) {
			return;
		}

		final Summon summon = targetPlayer.getFirstServitor();
		if ((summon == null) || ((_maxDistance > 0) && (caster.distance3d(summon) >= _maxDistance))) {
			return;
		}

		double px = summon.getX();
		double py = summon.getY();
		double ph = Util.convertHeadingToDegree(summon.getHeading());

		ph += 180;
		if (ph > 360) {
			ph -= 360;
		}

		ph = (Math.PI * ph) / 180;
		double x = px + (25 * Math.cos(ph));
		double y = py + (25 * Math.sin(ph));
		double z = summon.getZ();

		final Location loc = GeoData.getInstance().moveCheck(caster.getX(), caster.getY(), caster.getZ(), x, y, z, caster.getInstanceWorld());
		caster.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		caster.broadcastPacket(new FlyToLocation(caster, loc.getX(), loc.getY(), loc.getZ(), FlyType.DUMMY));
		caster.abortAttack();
		caster.abortCast();
		caster.setXYZ(loc);
		caster.broadcastPacket(new ValidateLocation(caster));
		caster.revalidateZone(true);
	}
}
