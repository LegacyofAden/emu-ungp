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
package org.l2junity.gameserver.model.actor.status;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.l2junity.commons.util.concurrent.ThreadPool;
import org.l2junity.gameserver.config.GeneralConfig;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.actor.stat.CharStat;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureHpChange;
import org.l2junity.gameserver.model.skills.AbnormalType;
import org.l2junity.gameserver.model.stats.Formulas;
import org.l2junity.gameserver.model.stats.DoubleStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharStatus
{
	protected static final Logger _log = LoggerFactory.getLogger(CharStatus.class);
	
	private final Creature _activeChar;
	
	private double _currentHp = 0; // Current HP of the L2Character
	private double _currentMp = 0; // Current MP of the L2Character
	
	/** Array containing all clients that need to be notified about hp/mp updates of the L2Character */
	private Set<Creature> _StatusListener;
	
	private Future<?> _regTask;
	
	protected byte _flagsRegenActive = 0;
	
	protected static final byte REGEN_FLAG_CP = 4;
	private static final byte REGEN_FLAG_HP = 1;
	private static final byte REGEN_FLAG_MP = 2;
	
	private final AtomicInteger _previousHpPercent = new AtomicInteger();
	
	public CharStatus(Creature activeChar)
	{
		_activeChar = activeChar;
	}
	
	/**
	 * Add the object to the list of L2Character that must be informed of HP/MP updates of this L2Character.<br>
	 * <B><U>Concept</U>:</B><br>
	 * Each L2Character owns a list called <B>_statusListener</B> that contains all L2PcInstance to inform of HP/MP updates.<br>
	 * Players who must be informed are players that target this L2Character.<br>
	 * When a RegenTask is in progress sever just need to go through this list to send Server->Client packet StatusUpdate.<br>
	 * <B><U>Example of use</U>:</B>
	 * <ul>
	 * <li>Target a PC or NPC</li>
	 * <ul>
	 * @param object L2Character to add to the listener
	 */
	public final void addStatusListener(Creature object)
	{
		if (object == getActiveChar())
		{
			return;
		}
		
		getStatusListener().add(object);
	}
	
	/**
	 * Remove the object from the list of L2Character that must be informed of HP/MP updates of this L2Character.<br>
	 * <B><U>Concept</U>:</B><br>
	 * Each L2Character owns a list called <B>_statusListener</B> that contains all L2PcInstance to inform of HP/MP updates.<br>
	 * Players who must be informed are players that target this L2Character.<br>
	 * When a RegenTask is in progress sever just need to go through this list to send Server->Client packet StatusUpdate.<br>
	 * <B><U>Example of use </U>:</B>
	 * <ul>
	 * <li>Untarget a PC or NPC</li>
	 * </ul>
	 * @param object L2Character to add to the listener
	 */
	public final void removeStatusListener(Creature object)
	{
		getStatusListener().remove(object);
	}
	
	/**
	 * Return the list of L2Character that must be informed of HP/MP updates of this L2Character.<br>
	 * <B><U>Concept</U>:</B><br>
	 * Each L2Character owns a list called <B>_statusListener</B> that contains all L2PcInstance to inform of HP/MP updates.<br>
	 * Players who must be informed are players that target this L2Character.<br>
	 * When a RegenTask is in progress sever just need to go through this list to send Server->Client packet StatusUpdate.
	 * @return The list of L2Character to inform or null if empty
	 */
	public final Set<Creature> getStatusListener()
	{
		if (_StatusListener == null)
		{
			_StatusListener = ConcurrentHashMap.newKeySet();
		}
		return _StatusListener;
	}
	
	// place holder, only PcStatus has CP
	public void reduceCp(int value)
	{
	}
	
	public void reduceHp(double value, Creature attacker, boolean awake, boolean isDOT, boolean isHPConsumption)
	{
		final Creature activeChar = getActiveChar();
		if (activeChar.isDead())
		{
			return;
		}
		
		// invul handling
		if (activeChar.isHpBlocked())
		{
			return;
		}
		
		if (attacker != null)
		{
			final PlayerInstance attackerPlayer = attacker.getActingPlayer();
			if ((attackerPlayer != null) && attackerPlayer.isGM() && !attackerPlayer.getAccessLevel().canGiveDamage())
			{
				return;
			}
		}
		
		if (!isDOT && !isHPConsumption)
		{
			if (awake)
			{
				activeChar.stopEffectsOnDamage();
			}
			if (Formulas.calcStunBreak(activeChar))
			{
				activeChar.stopStunning(true);
			}
			if (Formulas.calcRealTargetBreak())
			{
				getActiveChar().getEffectList().stopEffects(AbnormalType.REAL_TARGET);
			}
		}
		
		if (value > 0)
		{
			final double oldHp = getCurrentHp();
			final double newHp = Math.max(getCurrentHp() - value, activeChar.isUndying() ? 1 : 0);
			setCurrentHp(newHp);
			EventDispatcher.getInstance().notifyEventAsync(new OnCreatureHpChange(activeChar, oldHp, newHp), activeChar);
		}
		
		if ((activeChar.getCurrentHp() < 0.5)) // Die
		{
			activeChar.doDie(attacker);
		}
	}
	
	public void reduceMp(double value)
	{
		setCurrentMp(Math.max(getCurrentMp() - value, 0));
	}
	
	/**
	 * Start the HP/MP/CP Regeneration task.<br>
	 * <B><U>Actions</U>:</B>
	 * <ul>
	 * <li>Calculate the regen task period</li>
	 * <li>Launch the HP/MP/CP Regeneration task with Medium priority</li>
	 * </ul>
	 */
	public final synchronized void startHpMpRegeneration()
	{
		if ((_regTask == null) && !getActiveChar().isDead())
		{
			if (GeneralConfig.DEBUG)
			{
				_log.debug("HP/MP regen started");
			}
			
			// Get the Regeneration period
			int period = Formulas.getRegeneratePeriod(getActiveChar());
			
			// Create the HP/MP/CP Regeneration task
			_regTask = ThreadPool.scheduleAtFixedRate(new RegenTask(), period, period, TimeUnit.MILLISECONDS);
		}
	}
	
	/**
	 * Stop the HP/MP/CP Regeneration task.<br>
	 * <B><U>Actions</U>:</B>
	 * <ul>
	 * <li>Set the RegenActive flag to False</li>
	 * <li>Stop the HP/MP/CP Regeneration task</li>
	 * </ul>
	 */
	public final synchronized void stopHpMpRegeneration()
	{
		if (_regTask != null)
		{
			if (GeneralConfig.DEBUG)
			{
				_log.debug("HP/MP regen stop");
			}
			
			// Stop the HP/MP/CP Regeneration task
			_regTask.cancel(false);
			_regTask = null;
			
			// Set the RegenActive flag to false
			_flagsRegenActive = 0;
		}
	}
	
	// place holder, only PcStatus has CP
	public double getCurrentCp()
	{
		return 0;
	}
	
	// place holder, only PcStatus has CP
	public void setCurrentCp(double newCp)
	{
	}
	
	// place holder, only PcStatus has CP
	public void setCurrentCp(double newCp, boolean broadcastPacket)
	{
		
	}
	
	public final double getCurrentHp()
	{
		return _currentHp;
	}
	
	public final void setCurrentHp(double newHp)
	{
		setCurrentHp(newHp, true);
	}
	
	/**
	 * Sets the current hp of this character.
	 * @param newHp the new hp
	 * @param broadcastPacket if true StatusUpdate packet will be broadcasted.
	 * @return @{code true} if hp was changed, @{code false} otherwise.
	 */
	public boolean setCurrentHp(double newHp, boolean broadcastPacket)
	{
		// Get the Max HP of the L2Character
		int currentHp = (int) getCurrentHp();
		final double maxHp = getActiveChar().getStat().getMaxHp();
		
		synchronized (this)
		{
			if (getActiveChar().isDead())
			{
				return false;
			}
			
			if (newHp >= maxHp)
			{
				// Set the RegenActive flag to false
				_currentHp = maxHp;
				_flagsRegenActive &= ~REGEN_FLAG_HP;
				
				// Stop the HP/MP/CP Regeneration task
				if (_flagsRegenActive == 0)
				{
					stopHpMpRegeneration();
				}
			}
			else
			{
				// Set the RegenActive flag to true
				_currentHp = newHp;
				_flagsRegenActive |= REGEN_FLAG_HP;
				
				// Start the HP/MP/CP Regeneration task with Medium priority
				startHpMpRegeneration();
			}
		}
		
		boolean hpWasChanged = currentHp != _currentHp;
		
		// Send the Server->Client packet StatusUpdate with current HP and MP to all other L2PcInstance to inform
		if (hpWasChanged)
		{
			final int lastHpPercent = _previousHpPercent.get();
			final int currentHpPercent = (int) ((_currentHp * 100) / maxHp);
			//@formatter:off
			if (((lastHpPercent >= 60) && (currentHpPercent <= 60))
				|| ((currentHpPercent >= 60) && (lastHpPercent <= 60))
				|| ((lastHpPercent >= 30) && (currentHpPercent <= 30))
				|| ((currentHpPercent >= 30) && (lastHpPercent <= 30)))
			//@formatter:on
			{
				if (_previousHpPercent.compareAndSet(lastHpPercent, currentHpPercent))
				{
					_activeChar.getStat().recalculateStats(broadcastPacket);
				}
			}
			
			if (broadcastPacket)
			{
				getActiveChar().broadcastStatusUpdate();
			}
		}
		
		return hpWasChanged;
	}
	
	public final void setCurrentHpMp(double newHp, double newMp)
	{
		boolean hpOrMpWasChanged = setCurrentHp(newHp, false);
		hpOrMpWasChanged |= setCurrentMp(newMp, false);
		if (hpOrMpWasChanged)
		{
			getActiveChar().broadcastStatusUpdate();
		}
	}
	
	public final double getCurrentMp()
	{
		return _currentMp;
	}
	
	public final void setCurrentMp(double newMp)
	{
		setCurrentMp(newMp, true);
	}
	
	/**
	 * Sets the current mp of this character.
	 * @param newMp the new mp
	 * @param broadcastPacket if true StatusUpdate packet will be broadcasted.
	 * @return @{code true} if mp was changed, @{code false} otherwise.
	 */
	public final boolean setCurrentMp(double newMp, boolean broadcastPacket)
	{
		// Get the Max MP of the L2Character
		int currentMp = (int) getCurrentMp();
		final int maxMp = getActiveChar().getStat().getMaxMp();
		
		synchronized (this)
		{
			if (getActiveChar().isDead())
			{
				return false;
			}
			
			if (newMp >= maxMp)
			{
				// Set the RegenActive flag to false
				_currentMp = maxMp;
				_flagsRegenActive &= ~REGEN_FLAG_MP;
				
				// Stop the HP/MP/CP Regeneration task
				if (_flagsRegenActive == 0)
				{
					stopHpMpRegeneration();
				}
			}
			else
			{
				// Set the RegenActive flag to true
				_currentMp = newMp;
				_flagsRegenActive |= REGEN_FLAG_MP;
				
				// Start the HP/MP/CP Regeneration task with Medium priority
				startHpMpRegeneration();
			}
		}
		
		boolean mpWasChanged = currentMp != _currentMp;
		
		// Send the Server->Client packet StatusUpdate with current HP and MP to all other L2PcInstance to inform
		if (mpWasChanged && broadcastPacket)
		{
			getActiveChar().broadcastStatusUpdate();
		}
		
		return mpWasChanged;
	}
	
	protected void doRegeneration()
	{
		final CharStat charstat = getActiveChar().getStat();
		
		// Modify the current HP of the L2Character and broadcast Server->Client packet StatusUpdate
		if (getCurrentHp() < charstat.getMaxRecoverableHp())
		{
			setCurrentHp(getCurrentHp() + getActiveChar().getStat().getValue(DoubleStat.REGENERATE_HP_RATE), false);
		}
		
		// Modify the current MP of the L2Character and broadcast Server->Client packet StatusUpdate
		if (getCurrentMp() < charstat.getMaxRecoverableMp())
		{
			setCurrentMp(getCurrentMp() + getActiveChar().getStat().getValue(DoubleStat.REGENERATE_MP_RATE), false);
		}
		
		if (!getActiveChar().isInActiveRegion())
		{
			// no broadcast necessary for characters that are in inactive regions.
			// stop regeneration for characters who are filled up and in an inactive region.
			if ((getCurrentHp() == charstat.getMaxRecoverableHp()) && (getCurrentMp() == charstat.getMaxMp()))
			{
				stopHpMpRegeneration();
			}
		}
		else
		{
			getActiveChar().broadcastStatusUpdate(); // send the StatusUpdate packet
		}
	}
	
	/** Task of HP/MP regeneration */
	class RegenTask implements Runnable
	{
		@Override
		public void run()
		{
			try
			{
				doRegeneration();
			}
			catch (Exception e)
			{
				_log.error("", e);
			}
		}
	}
	
	public Creature getActiveChar()
	{
		return _activeChar;
	}
}
