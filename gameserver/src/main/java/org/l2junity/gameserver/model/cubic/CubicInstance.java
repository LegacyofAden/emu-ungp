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
package org.l2junity.gameserver.model.cubic;

import java.util.Comparator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.templates.L2CubicTemplate;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.ExUserInfoCubic;
import org.l2junity.gameserver.network.client.send.MagicSkillUse;

/**
 * @author UnAfraid
 */
public class CubicInstance {
	private final Player _owner;
	private final Player _caster;
	private final L2CubicTemplate _template;
	private ScheduledFuture<?> _skillUseTask;
	private ScheduledFuture<?> _expireTask;

	public CubicInstance(Player owner, Player caster, L2CubicTemplate template) {
		_owner = owner;
		_caster = caster;
		_template = template;
		activate();
	}

	private void activate() {
		_skillUseTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(this::tryToUseSkill, 0, _template.getDelay() * 1000, TimeUnit.MILLISECONDS);
		_expireTask = ThreadPool.getInstance().scheduleGeneral(this::deactivate, _template.getDuration() * 1000, TimeUnit.MILLISECONDS);
	}

	public void deactivate() {
		if ((_skillUseTask != null) && !_skillUseTask.isDone()) {
			_skillUseTask.cancel(true);
		}
		_skillUseTask = null;

		if ((_expireTask != null) && !_expireTask.isDone()) {
			_expireTask.cancel(true);
		}
		_expireTask = null;
		_owner.getCubics().remove(_template.getId());
		_owner.sendPacket(new ExUserInfoCubic(_owner));
		_owner.broadcastCharInfo();
	}

	private void tryToUseSkill() {
		final double random = Rnd.nextDouble() * 100;
		double commulativeChance = 0;
		for (CubicSkill cubicSkill : _template.getSkills()) {
			if ((commulativeChance += cubicSkill.getTriggerRate()) > random) {
				final Skill skill = cubicSkill.getSkill();
				if ((skill != null) && (Rnd.get(100) < cubicSkill.getSuccessRate())) {
					final Creature target = findTarget(cubicSkill);
					if (target != null) {
						_caster.broadcastPacket(new MagicSkillUse(_owner, target, skill.getDisplayId(), skill.getDisplayLevel(), skill.getHitTime(), skill.getReuseDelay()));
						skill.activateSkill(_owner, target);
						break;
					}
				}
			}
		}
	}

	private Creature findTarget(CubicSkill cubicSkill) {
		switch (_template.getTargetType()) {
			case BY_SKILL: {
				if (!_template.validateConditions(this, _owner, _owner)) {
					return null;
				}

				final Skill skill = cubicSkill.getSkill();
				if (skill != null) {
					switch (cubicSkill.getTargetType()) {
						case HEAL: {
							final Party party = _owner.getParty();
							if (party != null) {
								return party.getMembers().stream().filter(member -> cubicSkill.validateConditions(this, _owner, member) && member.isInRadius3d(_owner, PlayerConfig.ALT_PARTY_RANGE)).sorted(Comparator.comparingInt(Creature::getCurrentHpPercent).reversed()).findFirst().orElse(null);
							}
							if (cubicSkill.validateConditions(this, _owner, _owner)) {
								return _owner;
							}
							break;
						}
						case MASTER: {
							if (cubicSkill.validateConditions(this, _owner, _owner)) {
								return _owner;
							}
							break;
						}
						case TARGET: {
							final WorldObject possibleTarget = skill.getTarget(_owner, false, false, false);
							if ((possibleTarget != null) && possibleTarget.isCreature()) {
								if (cubicSkill.validateConditions(this, _owner, (Creature) possibleTarget)) {
									return (Creature) possibleTarget;
								}
							}
							break;
						}
					}
				}
				break;
			}
			case TARGET: {
				switch (cubicSkill.getTargetType()) {
					case HEAL: {
						final Party party = _owner.getParty();
						if (party != null) {
							return party.getMembers().stream().filter(member -> cubicSkill.validateConditions(this, _owner, member) && member.isInRadius3d(_owner, PlayerConfig.ALT_PARTY_RANGE)).sorted(Comparator.comparingInt(Creature::getCurrentHpPercent).reversed()).findFirst().orElse(null);
						}
						if (cubicSkill.validateConditions(this, _owner, _owner)) {
							return _owner;
						}
						break;
					}
					case MASTER: {
						if (cubicSkill.validateConditions(this, _owner, _owner)) {
							return _owner;
						}
						break;
					}
					case TARGET: {
						final WorldObject possibleTarget = cubicSkill.getSkill().getTarget(_owner, false, false, false);
						if ((possibleTarget != null) && possibleTarget.isCreature()) {
							if (cubicSkill.validateConditions(this, _owner, (Creature) possibleTarget)) {
								return (Creature) possibleTarget;
							}
						}
						break;
					}
				}
				break;
			}
			case HEAL: {
				final Party party = _owner.getParty();
				if (party != null) {
					return party.getMembers().stream().filter(member -> member.isInRadius3d(_owner, PlayerConfig.ALT_PARTY_RANGE)).sorted(Comparator.comparingInt(Creature::getCurrentHpPercent).reversed()).findFirst().orElse(null);
				}
				if (cubicSkill.validateConditions(this, _owner, _owner)) {
					return _owner;
				}
				break;
			}
		}
		return null;
	}

	/**
	 * @return the {@link Creature} that owns this cubic
	 */
	public Creature getOwner() {
		return _owner;
	}

	/**
	 * @return the {@link Creature} that casted this cubic
	 */
	public Creature getCaster() {
		return _caster;
	}

	/**
	 * @return {@code true} if cubic is casted from someone else but the owner, {@code false}
	 */
	public boolean isGivenByOther() {
		return _caster != _owner;
	}

	/**
	 * @return the {@link L2CubicTemplate} of this cubic
	 */
	public L2CubicTemplate getTemplate() {
		return _template;
	}
}
