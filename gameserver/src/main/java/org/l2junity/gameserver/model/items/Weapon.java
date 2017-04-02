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
package org.l2junity.gameserver.model.items;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.enums.ItemSkillType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureSkillUsed;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcSkillSee;
import org.l2junity.gameserver.model.items.type.WeaponType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.Formulas;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;

/**
 * This class is dedicated to the management of weapons.
 */
public final class Weapon extends L2Item {
	private WeaponType _type;
	private boolean _isMagicWeapon;
	private int _soulShotCount;
	private int _spiritShotCount;
	private int _mpConsume;
	private int _baseAttackRange;
	private int _baseAttackRadius;
	private int _baseAttackAngle;

	private int _reducedSoulshot;
	private int _reducedSoulshotChance;

	private int _reducedMpConsume;
	private int _reducedMpConsumeChance;

	private boolean _isForceEquip;
	private boolean _isAttackWeapon;
	private boolean _useWeaponSkillsOnly;

	/**
	 * Constructor for Weapon.
	 *
	 * @param set the StatsSet designating the set of couples (key,value) characterizing the weapon.
	 */
	public Weapon(StatsSet set) {
		super(set);
	}

	@Override
	public void set(StatsSet set) {
		super.set(set);
		_type = WeaponType.valueOf(set.getString("weapon_type", "none").toUpperCase());
		_type1 = L2Item.TYPE1_WEAPON_RING_EARRING_NECKLACE;
		_type2 = L2Item.TYPE2_WEAPON;
		_isMagicWeapon = set.getBoolean("is_magic_weapon", false);
		_soulShotCount = set.getInt("soulshots", 0);
		_spiritShotCount = set.getInt("spiritshots", 0);
		_mpConsume = set.getInt("mp_consume", 0);
		_baseAttackRange = set.getInt("attack_range", 40);
		String[] damgeRange = set.getString("damage_range", "").split(";"); // 0?;0?;fan sector;base attack angle
		if ((damgeRange.length > 1) && Util.isDigit(damgeRange[2]) && Util.isDigit(damgeRange[3])) {
			_baseAttackRadius = Integer.parseInt(damgeRange[2]);
			_baseAttackAngle = Integer.parseInt(damgeRange[3]);
		} else {
			_baseAttackRadius = 40;
			_baseAttackAngle = 120;
		}

		String[] reduced_soulshots = set.getString("reduced_soulshot", "").split(",");
		_reducedSoulshotChance = (reduced_soulshots.length == 2) ? Integer.parseInt(reduced_soulshots[0]) : 0;
		_reducedSoulshot = (reduced_soulshots.length == 2) ? Integer.parseInt(reduced_soulshots[1]) : 0;

		String[] reduced_mpconsume = set.getString("reduced_mp_consume", "").split(",");
		_reducedMpConsumeChance = (reduced_mpconsume.length == 2) ? Integer.parseInt(reduced_mpconsume[0]) : 0;
		_reducedMpConsume = (reduced_mpconsume.length == 2) ? Integer.parseInt(reduced_mpconsume[1]) : 0;

		_isForceEquip = set.getBoolean("isForceEquip", false);
		_isAttackWeapon = set.getBoolean("isAttackWeapon", true);
		_useWeaponSkillsOnly = set.getBoolean("useWeaponSkillsOnly", false);
	}

	/**
	 * @return the type of Weapon
	 */
	@Override
	public WeaponType getItemType() {
		return _type;
	}

	/**
	 * @return the ID of the Etc item after applying the mask.
	 */
	@Override
	public int getItemMask() {
		return getItemType().mask();
	}

	/**
	 * @return {@code true} if the weapon is magic, {@code false} otherwise.
	 */
	@Override
	public boolean isMagicWeapon() {
		return _isMagicWeapon;
	}

	/**
	 * @return the quantity of SoulShot used.
	 */
	public int getSoulShotCount() {
		return _soulShotCount;
	}

	/**
	 * @return the quantity of SpiritShot used.
	 */
	public int getSpiritShotCount() {
		return _spiritShotCount;
	}

	/**
	 * @return the reduced quantity of SoultShot used.
	 */
	public int getReducedSoulShot() {
		return _reducedSoulshot;
	}

	/**
	 * @return the chance to use Reduced SoultShot.
	 */
	public int getReducedSoulShotChance() {
		return _reducedSoulshotChance;
	}

	/**
	 * @return the MP consumption with the weapon.
	 */
	public int getMpConsume() {
		return _mpConsume;
	}

	public int getBaseAttackRange() {
		return _baseAttackRange;
	}

	public int getBaseAttackRadius() {
		return _baseAttackRadius;
	}

	public int getBaseAttackAngle() {
		return _baseAttackAngle;
	}

	/**
	 * @return the reduced MP consumption with the weapon.
	 */
	public int getReducedMpConsume() {
		return _reducedMpConsume;
	}

	/**
	 * @return the chance to use getReducedMpConsume()
	 */
	public int getReducedMpConsumeChance() {
		return _reducedMpConsumeChance;
	}

	/**
	 * @return {@code true} if the weapon is force equip, {@code false} otherwise.
	 */
	public boolean isForceEquip() {
		return _isForceEquip;
	}

	/**
	 * @return {@code true} if the weapon is attack weapon, {@code false} otherwise.
	 */
	public boolean isAttackWeapon() {
		return _isAttackWeapon;
	}

	/**
	 * @return {@code true} if the weapon is skills only, {@code false} otherwise.
	 */
	public boolean useWeaponSkillsOnly() {
		return _useWeaponSkillsOnly;
	}

	/**
	 * @param caster  the L2Character pointing out the caster
	 * @param target  the L2Character pointing out the target
	 * @param trigger
	 * @param type
	 */
	public void applyConditionalSkills(Creature caster, Creature target, Skill trigger, ItemSkillType type) {
		forEachSkill(type, holder ->
		{
			final Skill skill = holder.getSkill();
			if (Rnd.get(100) >= holder.getChance()) {
				return;
			}

			if (type == ItemSkillType.ON_MAGIC_SKILL) {
				// Trigger only if both are good or bad magic.
				if (trigger.isBad() != skill.isBad()) {
					return;
				}

				// No Trigger if not Magic Skill or is toggle
				if (trigger.isMagic() != skill.isMagic()) {
					return;
				}

				// No Trigger if skill is toggle
				if (trigger.isToggle()) {
					return;
				}

				if (skill.isBad() && (Formulas.calcShldUse(caster, target) == Formulas.SHIELD_DEFENSE_PERFECT_BLOCK)) {
					return;
				}
			}

			// Skill condition not met
			if (!skill.checkCondition(caster, target)) {
				return;
			}

			skill.activateSkill(caster, target);

			// TODO: Verify if this applies ONLY to ON_MAGIC_SKILL!
			if (type == ItemSkillType.ON_MAGIC_SKILL) {
				// notify quests of a skill use
				if (caster instanceof PlayerInstance) {
					World.getInstance().forEachVisibleObjectInRadius(caster, Npc.class, 1000, npc ->
					{
						EventDispatcher.getInstance().notifyEventAsync(new OnNpcSkillSee(npc, caster.getActingPlayer(), skill, false, target), npc);
						EventDispatcher.getInstance().notifyEventAsync(new OnCreatureSkillUsed(caster, target, skill, skill.isWithoutAction()), caster, skill);
					});
				}
				if (caster.isPlayer()) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_ACTIVATED);
					sm.addSkillName(skill);
					caster.sendPacket(sm);
				}
			}
		});
	}
}
