/*
 * Copyright (C) 2004-2015 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.actor.instance;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.ai.CharacterAI;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.ai.DoppelgangerAI;
import org.l2junity.gameserver.enums.Team;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.olympiad.OlympiadGameManager;
import org.l2junity.gameserver.model.skills.BuffInfo;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.BooleanStat;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nik
 */
public class DoppelgangerInstance extends Npc {
	protected static final Logger log = LoggerFactory.getLogger(DoppelgangerInstance.class);

	private boolean _copySummonerEffects = true;

	public DoppelgangerInstance(NpcTemplate template, Player owner) {
		super(template);

		setSummoner(owner);
		setCloneObjId(owner.getObjectId());
		setClanId(owner.getClanId());
		setInstance(owner.getInstanceWorld()); // set instance to same as owner
		setXYZInvisible(owner.getX() + Rnd.get(-100, 100), owner.getY() + Rnd.get(-100, 100), owner.getZ());
		((DoppelgangerAI) getAI()).setStartFollowController(true);
		followSummoner(true);
	}

	@Override
	protected CharacterAI initAI() {
		return new DoppelgangerAI(this);
	}

	@Override
	public void onSpawn() {
		super.onSpawn();

		if (_copySummonerEffects && (getSummoner() != null)) {
			for (BuffInfo summonerInfo : getSummoner().getEffectList().getEffects()) {
				if (summonerInfo.getAbnormalTime() > 0) {
					final BuffInfo info = new BuffInfo(getSummoner(), this, summonerInfo.getSkill(), false, null, null);
					info.setAbnormalTime(summonerInfo.getAbnormalTime());
					getEffectList().add(info);
				}

			}
		}
	}

	public void followSummoner(boolean followSummoner) {
		if (followSummoner) {
			if ((getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE) || (getAI().getIntention() == CtrlIntention.AI_INTENTION_ACTIVE)) {
				setRunning();
				getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, getSummoner());
			}
		} else {
			if (getAI().getIntention() == CtrlIntention.AI_INTENTION_FOLLOW) {
				getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
			}
		}
	}

	public void setCopySummonerEffects(boolean copySummonerEffects) {
		_copySummonerEffects = copySummonerEffects;
	}

	@Override
	public final byte getPvpFlag() {
		return getSummoner() != null ? getSummoner().getPvpFlag() : 0;
	}

	@Override
	public final Team getTeam() {
		return getSummoner() != null ? getSummoner().getTeam() : Team.NONE;
	}

	@Override
	public boolean isAutoAttackable(Creature attacker) {
		return (getSummoner() != null) ? getSummoner().isAutoAttackable(attacker) : super.isAutoAttackable(attacker);
	}

	@Override
	public void doAttack(double damage, Creature target, Skill skill, boolean isDOT, boolean directlyToHp, boolean critical, boolean reflect) {
		super.doAttack(damage, target, skill, isDOT, directlyToHp, critical, reflect);
		sendDamageMessage(target, skill, (int) damage, critical, false);
	}

	@Override
	public void sendDamageMessage(Creature target, Skill skill, int damage, boolean crit, boolean miss) {
		if (miss || (getSummoner() == null) || !getSummoner().isPlayer()) {
			return;
		}

		// Prevents the double spam of system messages, if the target is the owning player.
		if (target.getObjectId() != getSummoner().getObjectId()) {
			if (getActingPlayer().isInOlympiadMode() && (target.isPlayer()) && ((Player) target).isInOlympiadMode() && (((Player) target).getOlympiadGameId() == getActingPlayer().getOlympiadGameId())) {
				OlympiadGameManager.getInstance().notifyCompetitorDamage(getSummoner().getActingPlayer(), damage);
			}

			final SystemMessage sm;

			if ((target.isHpBlocked() && !target.isNpc()) || (target.isPlayer() && target.getStat().has(BooleanStat.FACE_OFF) && (target.getActingPlayer().getAttackerObjId() != getObjectId()))) {
				sm = SystemMessage.getSystemMessage(SystemMessageId.THE_ATTACK_HAS_BEEN_BLOCKED);
			} else {
				sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_INFLICTED_S3_DAMAGE_ON_C2_S4);
				sm.addNpcName(this);
				sm.addCharName(target);
				sm.addInt(damage);
				sm.addPopup(target.getObjectId(), getObjectId(), (damage * -1));
			}

			sendPacket(sm);
		}
	}

	@Override
	public void reduceCurrentHp(double damage, Creature attacker, Skill skill) {
		super.reduceCurrentHp(damage, attacker, skill);

		if ((getSummoner() != null) && getSummoner().isPlayer() && (attacker != null) && !isDead() && !isHpBlocked()) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_RECEIVED_S3_DAMAGE_FROM_C2);
			sm.addNpcName(this);
			sm.addCharName(attacker);
			sm.addInt((int) damage);
			sendPacket(sm);
		}
	}

	@Override
	public Player getActingPlayer() {
		return getSummoner() != null ? getSummoner().getActingPlayer() : super.getActingPlayer();
	}

	@Override
	public void onTeleported() {
		deleteMe(); // In retail, doppelgangers disappear when summoner teleports.
	}

	@Override
	public void sendPacket(IClientOutgoingPacket... packets) {
		if (getSummoner() != null) {
			getSummoner().sendPacket(packets);
		}
	}

	@Override
	public void sendPacket(SystemMessageId id) {
		if (getSummoner() != null) {
			getSummoner().sendPacket(id);
		}
	}

	@Override
	public String toString() {
		return super.toString() + "(" + getId() + ") Summoner: " + getSummoner();
	}
}
