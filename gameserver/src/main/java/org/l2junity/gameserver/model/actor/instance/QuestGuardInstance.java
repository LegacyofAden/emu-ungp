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
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableAttack;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableKill;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * This class extends Guard class for quests, that require tracking of onAttack and onKill events from monsters' attacks.
 *
 * @author GKR
 */
public final class QuestGuardInstance extends GuardInstance {
	private boolean _isAutoAttackable = true;
	private boolean _isPassive = false;

	public QuestGuardInstance(NpcTemplate template) {
		super(template);
		setInstanceType(InstanceType.L2QuestGuardInstance);
	}

	@Override
	public void addDamage(Creature attacker, int damage, Skill skill) {
		super.addDamage(attacker, damage, skill);

		if (attacker instanceof Attackable) {
			EventDispatcher.getInstance().notifyEventAsync(new OnAttackableAttack(null, this, damage, skill, false), this);
		}
	}

	@Override
	public boolean doDie(Creature killer) {
		// Kill the NpcInstance (the corpse disappeared after 7 seconds)
		if (!super.doDie(killer)) {
			return false;
		}

		if (killer instanceof Attackable) {
			// Delayed notification
			EventDispatcher.getInstance().notifyEventAsync(new OnAttackableKill(null, this, false), this);
		}
		return true;
	}

	@Override
	public void addDamageHate(Creature attacker, int damage, int aggro) {
		if (!_isPassive && !(attacker instanceof Player)) {
			super.addDamageHate(attacker, damage, aggro);
		}
	}

	public void setPassive(boolean state) {
		_isPassive = state;
	}

	@Override
	public boolean isAutoAttackable(Creature attacker) {
		return _isAutoAttackable && !(attacker instanceof Player);
	}

	@Override
	public void setAutoAttackable(boolean state) {
		_isAutoAttackable = state;
	}

	public boolean isPassive() {
		return _isPassive;
	}
}
