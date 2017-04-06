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
package org.l2junity.gameserver.model.skills;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.instancemanager.GameTimeManager;
import org.l2junity.gameserver.model.CharEffectList;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.options.Options;
import org.l2junity.gameserver.model.stats.Formulas;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Buff Info.<br>
 * Complex DTO that holds all the information for a given buff (or debuff or dance/song) set of effects issued by an skill.
 *
 * @author Zoey76
 */
public final class BuffInfo {
	// Data
	/**
	 * Data.
	 */
	private final int _effectorObjectId;
	private final Creature _effector;
	private final Creature _effected;
	private final Skill _skill;
	/**
	 * The effects.
	 */
	private final List<AbstractEffect> _effects = new ArrayList<>(1);
	// Tasks
	/**
	 * Effect tasks for ticks.
	 */
	private volatile Map<AbstractEffect, ScheduledFuture<?>> _tasks;
	/**
	 * Scheduled future.
	 */
	private ScheduledFuture<?> _scheduledFutureTimeTask;
	// Time and ticks
	/**
	 * Abnormal time.
	 */
	private int _abnormalTime;
	/**
	 * The game ticks at the start of this effect.
	 */
	private int _periodStartTicks;
	// Misc
	/**
	 * If {@code true} then this effect has been cancelled.
	 */
	private volatile boolean _isRemoved = false;
	/**
	 * If {@code true} then this effect is in use (or has been stop because an Herb took place).
	 */
	private volatile boolean _isInUse = true;
	private final boolean _hideStartMessage;
	private final ItemInstance _item;
	private final Options _option;

	/**
	 * Buff Info constructor.
	 *
	 * @param effector         the effector
	 * @param effected         the effected
	 * @param skill            the skill
	 * @param hideStartMessage hides start message
	 * @param item
	 * @param option
	 */
	public BuffInfo(Creature effector, Creature effected, Skill skill, boolean hideStartMessage, ItemInstance item, Options option) {
		_effectorObjectId = (effector != null) ? effector.getObjectId() : 0;
		_effector = effector;
		_effected = effected;
		_skill = skill;
		_abnormalTime = Formulas.calcEffectAbnormalTime(effector, effected, skill);
		_periodStartTicks = GameTimeManager.getInstance().getGameTicks();
		_hideStartMessage = hideStartMessage;
		_item = item;
		_option = option;
	}

	/**
	 * Gets the effects on this buff info.
	 *
	 * @return the effects
	 */
	public List<AbstractEffect> getEffects() {
		return _effects;
	}

	/**
	 * Adds an effect to this buff info.
	 *
	 * @param effect the effect to add
	 */
	public void addEffect(AbstractEffect effect) {
		_effects.add(effect);
	}

	/**
	 * Adds an effect task to this buff info.<br>
	 * Uses double-checked locking to initialize the map if it's necessary.
	 *
	 * @param effect          the effect that owns the task
	 * @param scheduledFuture the task
	 */
	private void addEffectTask(AbstractEffect effect, ScheduledFuture<?> scheduledFuture) {
		if (_tasks == null) {
			synchronized (this) {
				if (_tasks == null) {
					_tasks = new ConcurrentHashMap<>();
				}
			}
		}
		_tasks.put(effect, scheduledFuture);
	}

	/**
	 * Gets the task for the given effect.
	 *
	 * @param effect the effect
	 * @return the task
	 */
	private ScheduledFuture<?> getEffectTask(AbstractEffect effect) {
		return (_tasks == null) ? null : _tasks.get(effect);
	}

	/**
	 * Gets the skill that created this buff info.
	 *
	 * @return the skill
	 */
	public Skill getSkill() {
		return _skill;
	}

	/**
	 * Gets the calculated abnormal time.
	 *
	 * @return the abnormal time
	 */
	public int getAbnormalTime() {
		return _abnormalTime;
	}

	/**
	 * Sets the abnormal time.
	 *
	 * @param abnormalTime the abnormal time to set
	 */
	public void setAbnormalTime(int abnormalTime) {
		_abnormalTime = abnormalTime;
	}

	/**
	 * Gets the period start ticks.
	 *
	 * @return the period start
	 */
	public int getPeriodStartTicks() {
		return _periodStartTicks;
	}

	/**
	 * @return the item that triggered this skill
	 */
	public ItemInstance getItem() {
		return _item;
	}

	/**
	 * @return the options that issued this effect
	 */
	public Options getOption() {
		return _option;
	}

	/**
	 * Get the remaining time in seconds for this buff info.
	 *
	 * @return the elapsed time
	 */
	public int getTime() {
		return _abnormalTime - ((GameTimeManager.getInstance().getGameTicks() - _periodStartTicks) / GameTimeManager.TICKS_PER_SECOND);
	}

	/**
	 * Verify if this buff info has been cancelled.
	 *
	 * @return {@code true} if this buff info has been cancelled, {@code false} otherwise
	 */
	public boolean isRemoved() {
		return _isRemoved;
	}

	/**
	 * Set the buff info to removed.
	 *
	 * @param val the value to set
	 */
	public void setRemoved(boolean val) {
		_isRemoved = val;
	}

	/**
	 * Verify if this buff info is in use.
	 *
	 * @return {@code true} if this buff info is in use, {@code false} otherwise
	 */
	public boolean isInUse() {
		return _isInUse;
	}

	/**
	 * Set the buff info to in use.
	 *
	 * @param val the value to set
	 */
	public void setInUse(boolean val) {
		_isInUse = val;

		// Send message that the effect is applied or removed.
		if ((_skill != null) && !_skill.isHidingMesseges() && _effected.isPlayer()) {
			if (val) {
				if (!_hideStartMessage && !_skill.isAura()) {
					final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S_EFFECT_CAN_BE_FELT);
					sm.addSkillName(_skill);
					_effected.sendPacket(sm);
				}
			} else {
				final SystemMessage sm = SystemMessage.getSystemMessage(_skill.isToggle() ? SystemMessageId.S1_HAS_BEEN_ABORTED : SystemMessageId.THE_EFFECT_OF_S1_HAS_BEEN_REMOVED);
				sm.addSkillName(_skill);
				_effected.sendPacket(sm);
			}
		}
	}

	/**
	 * Gets the character's object id that launched the buff.
	 *
	 * @return the object id of the effector.
	 */
	public int getEffectorObjectId() {
		return _effectorObjectId;
	}

	/**
	 * Gets the character that launched the buff.
	 *
	 * @return the effector
	 */
	public Creature getEffector() {
		return _effector;
	}

	/**
	 * Gets the target of the skill.
	 *
	 * @return the effected
	 */
	public Creature getEffected() {
		return _effected;
	}

	/**
	 * Stops all the effects for this buff info.<br>
	 * Removes effects stats.<br>
	 * <b>It will not remove the buff info from the effect list</b>.<br>
	 * Instead call {@link CharEffectList#stopSkillEffects(boolean, Skill)}
	 *
	 * @param removed if {@code true} the skill will be handled as removed
	 */
	public void stopAllEffects(boolean removed) {
		setRemoved(removed);
		// Cancels the task that will end this buff info
		if ((_scheduledFutureTimeTask != null) && !_scheduledFutureTimeTask.isCancelled()) {
			_scheduledFutureTimeTask.cancel(true);
		}
		finishEffects();
	}

	public void initializeEffects() {
		if ((_effected == null) || (_skill == null)) {
			return;
		}

		// When effects are initialized, the successfully landed.
		if (!_hideStartMessage && _effected.isPlayer() && !_skill.isHidingMesseges() && !_skill.isAura()) {
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S_EFFECT_CAN_BE_FELT);
			sm.addSkillName(_skill);
			_effected.sendPacket(sm);
		}

		// Creates a task that will stop all the effects.
		if (_abnormalTime > 0) {
			_scheduledFutureTimeTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new BuffTimeTask(this), 0, 1000L, TimeUnit.MILLISECONDS);
		}

		for (AbstractEffect effect : _effects) {
			if (_effected.isDead() && !_skill.isPassive()) {
				continue;
			}

			// Call on start.
			effect.pumpStart(getEffector(), getEffected(), getSkill());

			// If it's a continuous effect, if has ticks schedule a task with period, otherwise schedule a simple task to end it.
			if (effect.getTicks() > 0) {
				// The task for the effect ticks.
				final ScheduledFuture<?> effectTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(() -> onTick(effect), effect.getTicks() * PlayerConfig.EFFECT_TICK_RATIO, effect.getTicks() * PlayerConfig.EFFECT_TICK_RATIO, TimeUnit.MILLISECONDS);
				// Adds the task for ticking.
				addEffectTask(effect, effectTask);
			}
		}
	}

	/**
	 * Called on each tick.<br>
	 * Verify if the effect should end and the effect task should be cancelled.
	 *
	 * @param effect the effect that is ticking
	 */
	public void onTick(AbstractEffect effect) {
		if (_isInUse) {
			effect.tick(getEffector(), getEffected(), getSkill());

			if (_skill.isToggle() && !effect.consume(getEffected(), getSkill())) {
				final ScheduledFuture<?> effectTask = getEffectTask(effect);
				if (effectTask != null) {
					effectTask.cancel(false);
					_effected.getEffectList().stopSkillEffects(true, getSkill()); // Remove the buff from the effect list.
				}
			}
		}
	}

	public void finishEffects() {
		// Cancels the ticking task.
		if (_tasks != null) {
			for (ScheduledFuture<?> effectTask : _tasks.values()) {
				effectTask.cancel(false);
			}
		}

		// Notify on exit.
		for (AbstractEffect effect : _effects) {
			effect.pumpEnd(getEffector(), getEffected(), getSkill());
		}

		// Set the proper system message.
		if ((_skill != null) && _effected.isPlayer() && !_skill.isHidingMesseges()) {
			SystemMessageId smId = null;
			if (_skill.isToggle()) {
				smId = SystemMessageId.S1_HAS_BEEN_ABORTED;
			} else if (isRemoved()) {
				smId = SystemMessageId.THE_EFFECT_OF_S1_HAS_BEEN_REMOVED;
			} else if (!_skill.isPassive()) {
				smId = SystemMessageId.S1_HAS_WORN_OFF;
			}

			if (smId != null) {
				final SystemMessage sm = SystemMessage.getSystemMessage(smId);
				sm.addSkillName(_skill);
				_effected.sendPacket(sm);
			}
		}
	}

	public void resetAbnormalTime(int abnormalTime) {
		if (_abnormalTime > 0) {
			_periodStartTicks = GameTimeManager.getInstance().getGameTicks();
			_abnormalTime = abnormalTime;
			if ((_scheduledFutureTimeTask != null) && !_scheduledFutureTimeTask.isCancelled()) {
				_scheduledFutureTimeTask.cancel(true);
			}
			_scheduledFutureTimeTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new BuffTimeTask(this), 0, 1000L, TimeUnit.MILLISECONDS);
		}
	}

	public boolean isAbnormalType(AbnormalType type) {
		return _skill.getAbnormalType().equals(type);
	}
}
