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
package org.l2junity.gameserver.ai;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.enums.ItemLocation;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.instancemanager.SuperpointManager;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.actor.templates.L2NpcTemplate;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcMoveFinished;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.AutoAttackStop;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.taskmanager.AttackStanceTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static org.l2junity.gameserver.ai.CtrlIntention.*;

/**
 * This class manages AI of L2Character.<br>
 * L2CharacterAI :
 * <ul>
 * <li>L2AttackableAI</li>
 * <li>L2DoorAI</li>
 * <li>L2PlayerAI</li>
 * <li>L2SummonAI</li>
 * </ul>
 */
public class CharacterAI extends AbstractAI {
	private static final Logger LOGGER = LoggerFactory.getLogger(CharacterAI.class);

	public static class IntentionCommand {
		protected final CtrlIntention _crtlIntention;
		protected final Object _arg0, _arg1;

		protected IntentionCommand(CtrlIntention pIntention, Object pArg0, Object pArg1) {
			_crtlIntention = pIntention;
			_arg0 = pArg0;
			_arg1 = pArg1;
		}

		public CtrlIntention getCtrlIntention() {
			return _crtlIntention;
		}
	}

	/**
	 * Cast Task
	 *
	 * @author Zoey76
	 */
	public static class CastTask implements Runnable {
		private final Creature _activeChar;
		private final WorldObject _target;
		private final Skill _skill;
		private final ItemInstance _item;
		private final boolean _forceUse;
		private final boolean _dontMove;

		public CastTask(Creature actor, Skill skill, WorldObject target, ItemInstance item, boolean forceUse, boolean dontMove) {
			_activeChar = actor;
			_target = target;
			_skill = skill;
			_item = item;
			_forceUse = forceUse;
			_dontMove = dontMove;
		}

		@Override
		public void run() {
			if (_activeChar.isAttackingNow()) {
				_activeChar.abortAttack();
			}
			_activeChar.getAI().changeIntentionToCast(_skill, _target, _item, _forceUse, _dontMove);
		}
	}

	public CharacterAI(Creature creature) {
		super(creature);
	}

	public IntentionCommand getNextIntention() {
		return null;
	}

	@Override
	protected void onEvtAttacked(Creature attacker) {
		clientStartAutoAttack();
	}

	/**
	 * Manage the Idle Intention : Stop Attack, Movement and Stand Up the actor.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Set the AI Intention to AI_INTENTION_IDLE</li>
	 * <li>Init cast and attack target</li>
	 * <li>Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)</li>
	 * <li>Stand up the actor server side AND client side by sending Server->Client packet ChangeWaitType (broadcast)</li>
	 * </ul>
	 */
	@Override
	protected void onIntentionIdle() {
		// Set the AI Intention to AI_INTENTION_IDLE
		changeIntention(AI_INTENTION_IDLE);

		// Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)
		clientStopMoving(null);

		// Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)
		clientStopAutoAttack();

	}

	/**
	 * Manage the Active Intention : Stop Attack, Movement and Launch Think Event.<br>
	 * <B><U> Actions</U> : <I>if the Intention is not already Active</I></B>
	 * <ul>
	 * <li>Set the AI Intention to AI_INTENTION_ACTIVE</li>
	 * <li>Init cast and attack target</li>
	 * <li>Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)</li>
	 * <li>Launch the Think Event</li>
	 * </ul>
	 */
	@Override
	protected void onIntentionActive() {
		// Check if the Intention is not already Active
		if (getIntention() != AI_INTENTION_ACTIVE) {
			// Set the AI Intention to AI_INTENTION_ACTIVE
			changeIntention(AI_INTENTION_ACTIVE);

			// Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)
			clientStopMoving(null);

			// Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)
			clientStopAutoAttack();

			// Also enable random animations for this L2Character if allowed
			// This is only for mobs - town npcs are handled in their constructor
			if (_actor.isAttackable()) {
				((Npc) _actor).startRandomAnimationTask();
			}

			// Launch the Think Event
			onEvtThink();
		}
	}

	/**
	 * Manage the Rest Intention.<br>
	 * <B><U> Actions</U> : </B>
	 * <ul>
	 * <li>Set the AI Intention to AI_INTENTION_IDLE</li>
	 * </ul>
	 */
	@Override
	protected void onIntentionRest() {
		// Set the AI Intention to AI_INTENTION_IDLE
		setIntention(AI_INTENTION_IDLE);
	}

	/**
	 * Manage the Attack Intention : Stop current Attack (if necessary), Start a new Attack and Launch Think Event.<br>
	 * <B><U> Actions</U> : </B>
	 * <ul>
	 * <li>Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Set the Intention of this AI to AI_INTENTION_ATTACK</li>
	 * <li>Set or change the AI attack target</li>
	 * <li>Start the actor Auto Attack client side by sending Server->Client packet AutoAttackStart (broadcast)</li>
	 * <li>Launch the Think Event</li>
	 * </ul>
	 * <B><U> Overridden in</U> :</B>
	 * <ul>
	 * <li>L2AttackableAI : Calculate attack timeout</li>
	 * </ul>
	 */
	@Override
	protected void onIntentionAttack(Creature target) {
		if ((target == null) || !target.isTargetable()) {
			clientActionFailed();
			return;
		}

		if (getIntention() == AI_INTENTION_REST) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		if (_actor.isAllSkillsDisabled() || _actor.isCastingNow() || _actor.isControlBlocked()) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		// Check if the Intention is already AI_INTENTION_ATTACK
		if (getIntention() == AI_INTENTION_ATTACK) {
			// Check if the AI already targets the L2Character
			if (getTarget() != target) {
				// Set the AI attack target (change target)
				setTarget(target);

				stopFollow();

				// Launch the Think Event
				notifyEvent(CtrlEvent.EVT_THINK, null);

			} else {
				clientActionFailed(); // else client freezes until cancel target
			}
		} else {
			// Set the Intention of this AbstractAI to AI_INTENTION_ATTACK
			changeIntention(AI_INTENTION_ATTACK, target);

			// Set the AI attack target
			setTarget(target);

			stopFollow();

			// Launch the Think Event
			notifyEvent(CtrlEvent.EVT_THINK, null);
		}
	}

	/**
	 * Manage the Cast Intention : Stop current Attack, Init the AI in order to cast and Launch Think Event.<br>
	 * <B><U> Actions</U> : </B>
	 * <ul>
	 * <li>Set the AI cast target</li>
	 * <li>Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor</li>
	 * <li>Set the AI skill used by INTENTION_CAST</li>
	 * <li>Set the Intention of this AI to AI_INTENTION_CAST</li>
	 * <li>Launch the Think Event</li>
	 * </ul>
	 */
	@Override
	protected void onIntentionCast(Skill skill, WorldObject target, ItemInstance item, boolean forceUse, boolean dontMove) {
		if ((getIntention() == AI_INTENTION_REST) && skill.isMagic()) {
			clientActionFailed();
			return;
		}

		if (_actor.isAttackingNow()) {
			ThreadPool.getInstance().scheduleGeneral(new CastTask(_actor, skill, target, item, forceUse, dontMove), _actor.getAttackEndTime() - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()), TimeUnit.MILLISECONDS);
		} else {
			changeIntentionToCast(skill, target, item, forceUse, dontMove);
		}
	}

	protected void changeIntentionToCast(Skill skill, WorldObject target, ItemInstance item, boolean forceUse, boolean dontMove) {
		// Set the AI skill used by INTENTION_CAST
		_skill = skill;

		// Set the AI item that triggered this skill
		_item = item;

		// Set the ctrl/shift pressed parameters
		_forceUse = forceUse;
		_dontMove = dontMove;

		// Change the Intention of this AbstractAI to AI_INTENTION_CAST
		changeIntention(AI_INTENTION_CAST, skill);

		// Launch the Think Event
		notifyEvent(CtrlEvent.EVT_THINK, null);
	}

	/**
	 * Manage the Move To Intention : Stop current Attack and Launch a Move to Location Task.<br>
	 * <B><U> Actions</U> : </B>
	 * <ul>
	 * <li>Stop the actor auto-attack server side AND client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Set the Intention of this AI to AI_INTENTION_MOVE_TO</li>
	 * <li>Move the actor to Location (x,y,z) server side AND client side by sending Server->Client packet CharMoveToLocation (broadcast)</li>
	 * </ul>
	 */
	@Override
	protected void onIntentionMoveTo(ILocational loc) {
		if (getIntention() == AI_INTENTION_REST) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		if (_actor.isAllSkillsDisabled() || _actor.isCastingNow()) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		// Set the Intention of this AbstractAI to AI_INTENTION_MOVE_TO
		changeIntention(AI_INTENTION_MOVE_TO, loc);

		// Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)
		clientStopAutoAttack();

		// Abort the attack of the L2Character and send Server->Client ActionFailed packet
		_actor.abortAttack();

		// Move the actor to Location (x,y,z) server side AND client side by sending Server->Client packet CharMoveToLocation (broadcast)
		moveTo(loc.getX(), loc.getY(), loc.getZ());
	}

	/**
	 * Manage the Follow Intention : Stop current Attack and Launch a Follow Task.<br>
	 * <B><U> Actions</U> : </B>
	 * <ul>
	 * <li>Stop the actor auto-attack server side AND client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Set the Intention of this AI to AI_INTENTION_FOLLOW</li>
	 * <li>Create and Launch an AI Follow Task to execute every 1s</li>
	 * </ul>
	 */
	@Override
	protected void onIntentionFollow(Creature target) {
		if (getIntention() == AI_INTENTION_REST) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		if (_actor.isAllSkillsDisabled() || _actor.isCastingNow()) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		if (_actor.isMovementDisabled() || (_actor.getMoveSpeed() <= 0)) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		// Dead actors can`t follow
		if (_actor.isDead()) {
			clientActionFailed();
			return;
		}

		// do not follow yourself
		if (_actor == target) {
			clientActionFailed();
			return;
		}

		// Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)
		clientStopAutoAttack();

		// Set the Intention of this AbstractAI to AI_INTENTION_FOLLOW
		changeIntention(AI_INTENTION_FOLLOW, target);

		// Create and Launch an AI Follow Task to execute every 1s
		startFollow(target);
	}

	/**
	 * Manage the PickUp Intention : Set the pick up target and Launch a Move To Pawn Task (offset=20).<br>
	 * <B><U> Actions</U> : </B>
	 * <ul>
	 * <li>Set the AI pick up target</li>
	 * <li>Set the Intention of this AI to AI_INTENTION_PICK_UP</li>
	 * <li>Move the actor to Pawn server side AND client side by sending Server->Client packet MoveToPawn (broadcast)</li>
	 * </ul>
	 */
	@Override
	protected void onIntentionPickUp(WorldObject object) {
		if (getIntention() == AI_INTENTION_REST) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		if (_actor.isAllSkillsDisabled() || _actor.isCastingNow()) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		// Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)
		clientStopAutoAttack();

		if ((object instanceof ItemInstance) && (((ItemInstance) object).getItemLocation() != ItemLocation.VOID)) {
			return;
		}

		// Set the Intention of this AbstractAI to AI_INTENTION_PICK_UP
		changeIntention(AI_INTENTION_PICK_UP, object);

		// Set the AI pick up target
		setTarget(object);
		if ((object.getX() == 0) && (object.getY() == 0)) // TODO: Find the drop&spawn bug
		{
			LOGGER.warn("Object in coords 0,0 - using a temporary fix");
			object.setXYZ(getActor().getX(), getActor().getY(), getActor().getZ() + 5);
		}

		// Move the actor to Pawn server side AND client side by sending Server->Client packet MoveToPawn (broadcast)
		moveToPawn(object, 20);
	}

	/**
	 * Manage the Interact Intention : Set the interact target and Launch a Move To Pawn Task (offset=60).<br>
	 * <B><U> Actions</U> : </B>
	 * <ul>
	 * <li>Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Set the AI interact target</li>
	 * <li>Set the Intention of this AI to AI_INTENTION_INTERACT</li>
	 * <li>Move the actor to Pawn server side AND client side by sending Server->Client packet MoveToPawn (broadcast)</li>
	 * </ul>
	 */
	@Override
	protected void onIntentionInteract(WorldObject object) {
		if (getIntention() == AI_INTENTION_REST) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		if (_actor.isAllSkillsDisabled() || _actor.isCastingNow()) {
			// Cancel action client side by sending Server->Client packet ActionFailed to the L2PcInstance actor
			clientActionFailed();
			return;
		}

		// Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)
		clientStopAutoAttack();

		if (getIntention() != AI_INTENTION_INTERACT) {
			// Set the Intention of this AbstractAI to AI_INTENTION_INTERACT
			changeIntention(AI_INTENTION_INTERACT, object);

			// Set the AI interact target
			setTarget(object);

			// Move the actor to Pawn server side AND client side by sending Server->Client packet MoveToPawn (broadcast)
			moveToPawn(object, 60);
		}
	}

	/**
	 * Do nothing.
	 */
	@Override
	protected void onEvtThink() {
		// do nothing
	}

	/**
	 * Do nothing.
	 */
	@Override
	protected void onEvtAggression(Creature target, int aggro) {
		// do nothing
	}

	/**
	 * Launch actions corresponding to the Event Stunned then onAttacked Event.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)</li>
	 * <li>Break an attack and send Server->Client ActionFailed packet and a System Message to the L2Character</li>
	 * <li>Break a cast and send Server->Client ActionFailed packet and a System Message to the L2Character</li>
	 * <li>Launch actions corresponding to the Event onAttacked (only for L2AttackableAI after the stunning periode)</li>
	 * </ul>
	 */
	@Override
	protected void onEvtActionBlocked(Creature attacker) {
		// Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)
		_actor.broadcastPacket(new AutoAttackStop(_actor.getObjectId()));
		if (AttackStanceTaskManager.getInstance().hasAttackStanceTask(_actor)) {
			AttackStanceTaskManager.getInstance().removeAttackStanceTask(_actor);
		}

		// Stop Server AutoAttack also
		setAutoAttacking(false);

		// Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)
		clientStopMoving(null);
	}

	/**
	 * Launch actions corresponding to the Event Rooted.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)</li>
	 * <li>Launch actions corresponding to the Event onAttacked</li>
	 * </ul>
	 */
	@Override
	protected void onEvtRooted(Creature attacker) {
		// Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)
		// _actor.broadcastPacket(new AutoAttackStop(_actor.getObjectId()));
		// if (AttackStanceTaskManager.getInstance().hasAttackStanceTask(_actor))
		// AttackStanceTaskManager.getInstance().removeAttackStanceTask(_actor);

		// Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)
		clientStopMoving(null);

		// Launch actions corresponding to the Event onAttacked
		onEvtAttacked(attacker);

	}

	/**
	 * Launch actions corresponding to the Event Confused.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)</li>
	 * <li>Launch actions corresponding to the Event onAttacked</li>
	 * </ul>
	 */
	@Override
	protected void onEvtConfused(Creature attacker) {
		// Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)
		clientStopMoving(null);

		// Launch actions corresponding to the Event onAttacked
		onEvtAttacked(attacker);
	}

	/**
	 * Launch actions corresponding to the Event Muted.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Break a cast and send Server->Client ActionFailed packet and a System Message to the L2Character</li>
	 * </ul>
	 */
	@Override
	protected void onEvtMuted(Creature attacker) {
		// Break a cast and send Server->Client ActionFailed packet and a System Message to the L2Character
		onEvtAttacked(attacker);
	}

	/**
	 * Do nothing.
	 */
	@Override
	protected void onEvtEvaded(Creature attacker) {
		// do nothing
	}

	/**
	 * Launch actions corresponding to the Event ReadyToAct.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Launch actions corresponding to the Event Think</li>
	 * </ul>
	 */
	@Override
	protected void onEvtReadyToAct() {
		// Launch actions corresponding to the Event Think
		onEvtThink();
	}

	/**
	 * Launch actions corresponding to the Event Arrived.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>If the Intention was AI_INTENTION_MOVE_TO, set the Intention to AI_INTENTION_ACTIVE</li>
	 * <li>Launch actions corresponding to the Event Think</li>
	 * </ul>
	 */
	@Override
	protected void onEvtArrived() {
		getActor().revalidateZone(true);

		if (getActor().moveToNextRoutePoint()) {
			return;
		}

		if (getActor().isAttackable()) {
			((Attackable) getActor()).setisReturningToSpawnPoint(false);
		}
		clientStoppedMoving();

		// If the Intention was AI_INTENTION_MOVE_TO, set the Intention to AI_INTENTION_ACTIVE
		if (getIntention() == AI_INTENTION_MOVE_TO) {
			setIntention(AI_INTENTION_ACTIVE);
		}

		if (_actor.isNpc()) {
			Npc npc = (Npc) _actor;
			SuperpointManager.getInstance().onArrived(npc); // Walking Manager support

			// Notify to scripts
			EventDispatcher.getInstance().notifyEventAsync(new OnNpcMoveFinished(npc), npc);
		}

		// Launch actions corresponding to the Event Think
		onEvtThink();
	}

	/**
	 * Launch actions corresponding to the Event ArrivedRevalidate.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Launch actions corresponding to the Event Think</li>
	 * </ul>
	 */
	@Override
	protected void onEvtArrivedRevalidate() {
		// Launch actions corresponding to the Event Think
		onEvtThink();
	}

	/**
	 * Launch actions corresponding to the Event ArrivedBlocked.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)</li>
	 * <li>If the Intention was AI_INTENTION_MOVE_TO, set the Intention to AI_INTENTION_ACTIVE</li>
	 * <li>Launch actions corresponding to the Event Think</li>
	 * </ul>
	 */
	@Override
	protected void onEvtArrivedBlocked(Location blocked_at_loc) {
		// If the Intention was AI_INTENTION_MOVE_TO, set the Intention to AI_INTENTION_ACTIVE
		if ((getIntention() == AI_INTENTION_MOVE_TO) || (getIntention() == AI_INTENTION_CAST)) {
			setIntention(AI_INTENTION_ACTIVE);
		}

		// Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)
		clientStopMoving(blocked_at_loc);

		// Launch actions corresponding to the Event Think
		onEvtThink();
	}

	/**
	 * Launch actions corresponding to the Event ForgetObject.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>If the object was targeted and the Intention was AI_INTENTION_INTERACT or AI_INTENTION_PICK_UP, set the Intention to AI_INTENTION_ACTIVE</li>
	 * <li>If the object was targeted to attack, stop the auto-attack, cancel target and set the Intention to AI_INTENTION_ACTIVE</li>
	 * <li>If the object was targeted to cast, cancel target and set the Intention to AI_INTENTION_ACTIVE</li>
	 * <li>If the object was targeted to follow, stop the movement, cancel AI Follow Task and set the Intention to AI_INTENTION_ACTIVE</li>
	 * <li>If the targeted object was the actor , cancel AI target, stop AI Follow Task, stop the movement and set the Intention to AI_INTENTION_IDLE</li>
	 * </ul>
	 */
	@Override
	protected void onEvtForgetObject(WorldObject object) {
		final WorldObject target = getTarget();

		// Stop any casting pointing to this object.
		getActor().abortCast(sc -> sc.getTarget() == object);

		// If the object was targeted and the Intention was AI_INTENTION_INTERACT or AI_INTENTION_PICK_UP, set the Intention to AI_INTENTION_ACTIVE
		if (target == object) {
			setTarget(null);

			if (isFollowing()) {
				// Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)
				clientStopMoving(null);

				// Stop an AI Follow Task
				stopFollow();
			}
			// Stop any intention that has target we want to forget.
			if (getIntention() != AI_INTENTION_MOVE_TO) {
				setIntention(AI_INTENTION_ACTIVE);
			}
		}

		// Check if the targeted object was the actor
		if (_actor == object) {
			// Cancel AI target
			setTarget(null);

			// Stop an AI Follow Task
			stopFollow();

			// Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)
			clientStopMoving(null);

			// Set the Intention of this AbstractAI to AI_INTENTION_IDLE
			changeIntention(AI_INTENTION_IDLE);
		}
	}

	/**
	 * Launch actions corresponding to the Event Cancel.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Stop an AI Follow Task</li>
	 * <li>Launch actions corresponding to the Event Think</li>
	 * </ul>
	 */
	@Override
	protected void onEvtCancel() {
		_actor.abortCast();

		// Stop an AI Follow Task
		stopFollow();

		if (!AttackStanceTaskManager.getInstance().hasAttackStanceTask(_actor)) {
			_actor.broadcastPacket(new AutoAttackStop(_actor.getObjectId()));
		}

		// Launch actions corresponding to the Event Think
		onEvtThink();
	}

	/**
	 * Launch actions corresponding to the Event Dead.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Stop an AI Follow Task</li>
	 * <li>Kill the actor client side by sending Server->Client packet AutoAttackStop, StopMove/StopRotation, Die (broadcast)</li>
	 * </ul>
	 */
	@Override
	protected void onEvtDead() {
		// Stop an AI Tasks
		stopAITask();

		// Kill the actor client side by sending Server->Client packet AutoAttackStop, StopMove/StopRotation, Die (broadcast)
		clientNotifyDead();

		if (!_actor.isPlayable()) {
			_actor.setWalking();
		}
	}

	/**
	 * Launch actions corresponding to the Event Fake Death.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Stop an AI Follow Task</li>
	 * </ul>
	 */
	@Override
	protected void onEvtFakeDeath() {
		// Stop an AI Follow Task
		stopFollow();

		// Stop the actor movement and send Server->Client packet StopMove/StopRotation (broadcast)
		clientStopMoving(null);

		// Init AI
		_intention = AI_INTENTION_IDLE;
		setTarget(null);
	}

	/**
	 * Do nothing.
	 */
	@Override
	protected void onEvtFinishCasting() {
		// do nothing
	}

	protected boolean maybeMoveToPosition(ILocational worldPosition, int offset) {
		if (worldPosition == null) {
			LOGGER.warn("maybeMoveToPosition: worldPosition == NULL!");
			return false;
		}

		if (offset < 0) {
			return false; // skill radius -1
		}

		if (!_actor.isInRadius2d(worldPosition, offset + _actor.getTemplate().getCollisionRadius())) {
			if (_actor.isMovementDisabled() || (_actor.getMoveSpeed() <= 0)) {
				return true;
			}

			if (!_actor.isRunning() && !(this instanceof PlayerAI) && !(this instanceof SummonAI)) {
				_actor.setRunning();
			}

			stopFollow();

			double x = _actor.getX();
			double y = _actor.getY();

			double dx = worldPosition.getX() - x;
			double dy = worldPosition.getY() - y;

			double dist = Math.sqrt((dx * dx) + (dy * dy));

			double sin = dy / dist;
			double cos = dx / dist;

			dist -= offset - 5;

			x += (int) (dist * cos);
			y += (int) (dist * sin);

			moveTo(x, y, worldPosition.getZ());
			return true;
		}

		if (isFollowing()) {
			stopFollow();
		}

		return false;
	}

	/**
	 * Manage the Move to Pawn action in function of the distance and of the Interact area.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Get the distance between the current position of the L2Character and the target (x,y)</li>
	 * <li>If the distance > offset+20, move the actor (by running) to Pawn server side AND client side by sending Server->Client packet MoveToPawn (broadcast)</li>
	 * <li>If the distance <= offset+20, Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)</li>
	 * </ul>
	 * <B><U> Example of use </U> :</B>
	 * <ul>
	 * <li>L2PLayerAI, L2SummonAI</li>
	 * </ul>
	 *
	 * @param target The targeted L2Object
	 * @param offset The Interact area radius
	 * @return True if a movement must be done
	 */
	protected boolean maybeMoveToPawn(WorldObject target, int offset) {
		// Get the distance between the current position of the L2Character and the target (x,y)
		if (target == null) {
			LOGGER.warn("maybeMoveToPawn: target == NULL!");
			return false;
		}
		if (offset < 0) {
			return false; // skill radius -1
		}

		offset += _actor.getTemplate().getCollisionRadius();
		if (target.isCreature()) {
			offset += ((Creature) target).getTemplate().getCollisionRadius();
		}

		if (!_actor.isInRadius2d(target, offset)) {
			// Caller should be L2Playable and thinkAttack/thinkCast/thinkInteract/thinkPickUp
			if (isFollowing()) {
				// allow larger hit range when the target is moving (check is run only once per second)
				if (!_actor.isInRadius2d(target, offset + 100)) {
					return true;
				}

				stopFollow();
				return false;
			}

			if (_actor.isMovementDisabled() || (_actor.getMoveSpeed() <= 0)) {
				// If player is trying attack target but he cannot move to attack target
				// change his intention to idle
				if (_actor.getAI().getIntention() == CtrlIntention.AI_INTENTION_ATTACK) {
					_actor.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				}

				return true;
			}

			// while flying there is no move to cast
			if (_actor.getAI().getIntention() == CtrlIntention.AI_INTENTION_CAST) {
				if (_actor.checkTransformed(transform -> !transform.isCombat())) {
					_actor.sendPacket(SystemMessageId.THE_DISTANCE_IS_TOO_FAR_AND_SO_THE_CASTING_HAS_BEEN_STOPPED);
					_actor.sendPacket(ActionFailed.STATIC_PACKET);
					return true;
				}
			}

			// If not running, set the L2Character movement type to run and send Server->Client packet ChangeMoveType to all others L2PcInstance
			if (!_actor.isRunning() && !(this instanceof PlayerAI) && !(this instanceof SummonAI)) {
				_actor.setRunning();
			}

			stopFollow();
			if (target.isCreature() && !target.isDoor()) {
				if (((Creature) target).isMoving()) {
					offset -= 100;
				}
				if (offset < 5) {
					offset = 5;
				}

				startFollow((Creature) target, offset);
			} else {
				// Move the actor to Pawn server side AND client side by sending Server->Client packet MoveToPawn (broadcast)
				moveToPawn(target, offset);
			}
			return true;
		}

		if (isFollowing()) {
			stopFollow();
		}

		// Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)
		// clientStopMoving(null);
		return false;
	}

	/**
	 * Modify current Intention and actions if the target is lost or dead.<br>
	 * <B><U> Actions</U> : <I>If the target is lost or dead</I></B>
	 * <ul>
	 * <li>Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)</li>
	 * <li>Set the Intention of this AbstractAI to AI_INTENTION_ACTIVE</li>
	 * </ul>
	 * <B><U> Example of use </U> :</B>
	 * <ul>
	 * <li>L2PLayerAI, L2SummonAI</li>
	 * </ul>
	 *
	 * @param target The targeted L2Object
	 * @return True if the target is lost or dead (false if fakedeath)
	 */
	protected boolean checkTargetLostOrDead(Creature target) {
		if ((target == null) || target.isAlikeDead()) {
			// check if player is fakedeath
			if ((target instanceof PlayerInstance) && ((PlayerInstance) target).isFakeDeath()) {
				target.stopFakeDeath(true);
				return false;
			}

			// Set the Intention of this AbstractAI to AI_INTENTION_ACTIVE
			setIntention(AI_INTENTION_ACTIVE);
			return true;
		}
		return false;
	}

	/**
	 * Modify current Intention and actions if the target is lost.<br>
	 * <B><U> Actions</U> : <I>If the target is lost</I></B>
	 * <ul>
	 * <li>Stop the actor auto-attack client side by sending Server->Client packet AutoAttackStop (broadcast)</li>
	 * <li>Stop the actor movement server side AND client side by sending Server->Client packet StopMove/StopRotation (broadcast)</li>
	 * <li>Set the Intention of this AbstractAI to AI_INTENTION_ACTIVE</li>
	 * </ul>
	 * <B><U> Example of use </U> :</B>
	 * <ul>
	 * <li>L2PLayerAI, L2SummonAI</li>
	 * </ul>
	 *
	 * @param target The targeted L2Object
	 * @return True if the target is lost
	 */
	protected boolean checkTargetLost(WorldObject target) {
		// check if player is fakedeath
		if (target instanceof PlayerInstance) {
			PlayerInstance target2 = (PlayerInstance) target; // convert object to chara

			if (target2.isFakeDeath()) {
				target2.stopFakeDeath(true);
				return false;
			}
		}
		if (target == null) {
			// Set the Intention of this AbstractAI to AI_INTENTION_ACTIVE
			setIntention(AI_INTENTION_ACTIVE);
			return true;
		}
		if ((_actor != null) && (_skill != null) && _skill.isBad() && (_skill.getAffectRange() > 0) && !GeoData.getInstance().canSeeTarget(_actor, target)) {
			setIntention(AI_INTENTION_ACTIVE);
			return true;
		}
		return false;
	}

	protected class SelfAnalysis {
		public boolean isMage = false;
		public boolean isHealer = false;
		public Set<Skill> healSkills = ConcurrentHashMap.newKeySet();
		public boolean hasHealOrResurrect = false;
		public int maxCastRange = 0;

		public SelfAnalysis() {
		}

		public void init() {
			switch (((L2NpcTemplate) _actor.getTemplate()).getAIType()) {
				case MAGE:
					isMage = true;
					break;
				case HEALER:
					isHealer = true;
					break;
			}

			// skill analysis
			for (Skill sk : _actor.getAllSkills()) {
				if (sk.isPassive() || sk.isContinuous()) {
					continue;
				}

				int castRange = sk.getCastRange();
				if (sk.hasEffectType(L2EffectType.HEAL)) {
					healSkills.add(sk);
					hasHealOrResurrect = true;
				}

				if (castRange > maxCastRange) {
					maxCastRange = castRange;
				}
			}
		}
	}
}
