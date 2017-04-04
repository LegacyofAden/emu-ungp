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
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.items.Weapon;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.client.send.CharInfo;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.taskmanager.DecayTaskManager;

public class DecoyInstance extends Npc {
	private final Player _owner;

	public DecoyInstance(NpcTemplate template, Player owner, int totalLifeTime) {
		super(template);
		setInstanceType(InstanceType.L2DecoyInstance);
		_owner = owner;
		setXYZInvisible(owner.getX(), owner.getY(), owner.getZ());
		setIsInvul(false);
	}

	@Override
	public boolean doDie(Creature killer) {
		if (!super.doDie(killer)) {
			return false;
		}

		DecayTaskManager.getInstance().add(this);
		return true;
	}

	@Override
	public void onSpawn() {
		super.onSpawn();
		sendPacket(new CharInfo(this, false));
	}

	@Override
	public void updateAbnormalVisualEffects() {
		getWorld().forEachVisibleObject(this, Player.class, player ->
		{
			if (isVisibleFor(player)) {
				player.sendPacket(new CharInfo(this, isInvisible() && player.canOverrideCond(PcCondOverride.SEE_ALL_PLAYERS)));
			}
		});
	}

	@Override
	public void onDecay() {
		deleteMe(_owner);
	}

	@Override
	public boolean isAutoAttackable(Creature attacker) {
		return _owner.isAutoAttackable(attacker);
	}

	@Override
	public ItemInstance getActiveWeaponInstance() {
		return null;
	}

	@Override
	public Weapon getActiveWeaponItem() {
		return null;
	}

	@Override
	public ItemInstance getSecondaryWeaponInstance() {
		return null;
	}

	@Override
	public Weapon getSecondaryWeaponItem() {
		return null;
	}

	@Override
	public final int getId() {
		return getTemplate().getId();
	}

	public void deleteMe(Player owner) {
		decayMe();
	}

	@Override
	public Player getActingPlayer() {
		return _owner;
	}

	@Override
	public void sendInfo(Player activeChar) {
		activeChar.sendPacket(new CharInfo(this, isInvisible() && activeChar.canOverrideCond(PcCondOverride.SEE_ALL_PLAYERS)));
	}

	@Override
	public void sendPacket(IClientOutgoingPacket... packets) {
		if (getActingPlayer() != null) {
			getActingPlayer().sendPacket(packets);
		}
	}

	@Override
	public void sendPacket(SystemMessageId id) {
		if (getActingPlayer() != null) {
			getActingPlayer().sendPacket(id);
		}
	}
}
