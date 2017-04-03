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
package org.l2junity.gameserver.model;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.ai.ControllableMobAI;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.datatables.SpawnTable;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.L2ControllableMobInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.templates.L2NpcTemplate;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author littlecrow
 */
public final class MobGroup {
	private final L2NpcTemplate _npcTemplate;
	private final int _groupId;
	private final int _maxMobCount;

	private Set<L2ControllableMobInstance> _mobs;

	public MobGroup(int groupId, L2NpcTemplate npcTemplate, int maxMobCount) {
		_groupId = groupId;
		_npcTemplate = npcTemplate;
		_maxMobCount = maxMobCount;
	}

	public int getActiveMobCount() {
		return getMobs().size();
	}

	public int getGroupId() {
		return _groupId;
	}

	public int getMaxMobCount() {
		return _maxMobCount;
	}

	public Set<L2ControllableMobInstance> getMobs() {
		if (_mobs == null) {
			_mobs = ConcurrentHashMap.newKeySet();
		}

		return _mobs;
	}

	public String getStatus() {
		try {
			ControllableMobAI mobGroupAI = (ControllableMobAI) getMobs().stream().findFirst().get().getAI();

			switch (mobGroupAI.getAlternateAI()) {
				case ControllableMobAI.AI_NORMAL:
					return "Idle";
				case ControllableMobAI.AI_FORCEATTACK:
					return "Force Attacking";
				case ControllableMobAI.AI_FOLLOW:
					return "Following";
				case ControllableMobAI.AI_CAST:
					return "Casting";
				case ControllableMobAI.AI_ATTACK_GROUP:
					return "Attacking Group";
				default:
					return "Idle";
			}
		} catch (Exception e) {
			return "Unspawned";
		}
	}

	public L2NpcTemplate getTemplate() {
		return _npcTemplate;
	}

	public boolean isGroupMember(L2ControllableMobInstance mobInst) {
		for (L2ControllableMobInstance groupMember : getMobs()) {
			if (groupMember == null) {
				continue;
			}

			if (groupMember.getObjectId() == mobInst.getObjectId()) {
				return true;
			}
		}

		return false;
	}

	public void spawnGroup(double x, double y, double z) {
		if (getActiveMobCount() > 0) {
			return;
		}

		try {
			for (int i = 0; i < getMaxMobCount(); i++) {
				L2GroupSpawn spawn = new L2GroupSpawn(getTemplate());

				int signX = (Rnd.nextInt(2) == 0) ? -1 : 1;
				int signY = (Rnd.nextInt(2) == 0) ? -1 : 1;
				int randX = Rnd.nextInt(MobGroupTable.RANDOM_RANGE);
				int randY = Rnd.nextInt(MobGroupTable.RANDOM_RANGE);

				spawn.setXYZ(x + (signX * randX), y + (signY * randY), z);
				spawn.stopRespawn();

				SpawnTable.getInstance().addNewSpawn(spawn, false);
				getMobs().add((L2ControllableMobInstance) spawn.doGroupSpawn());
			}
		} catch (ClassNotFoundException e) {
		} catch (NoSuchMethodException e2) {
		}
	}

	public void spawnGroup(Player activeChar) {
		spawnGroup(activeChar.getX(), activeChar.getY(), activeChar.getZ());
	}

	public void teleportGroup(Player player) {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			if (!mobInst.isDead()) {
				double x = player.getX() + Rnd.nextInt(50);
				double y = player.getY() + Rnd.nextInt(50);

				mobInst.teleToLocation(new Location(x, y, player.getZ()), true);
				ControllableMobAI ai = (ControllableMobAI) mobInst.getAI();
				ai.follow(player);
			}
		}
	}

	public L2ControllableMobInstance getRandomMob() {
		removeDead();

		if (getActiveMobCount() == 0) {
			return null;
		}

		int choice = Rnd.nextInt(getActiveMobCount());
		for (L2ControllableMobInstance mob : getMobs()) {
			if (--choice == 0) {
				return mob;
			}
		}
		return null;
	}

	public void unspawnGroup() {
		removeDead();

		if (getActiveMobCount() == 0) {
			return;
		}

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			if (!mobInst.isDead()) {
				mobInst.deleteMe();
			}

			SpawnTable.getInstance().deleteSpawn(mobInst.getSpawn(), false);
		}

		getMobs().clear();
	}

	public void killGroup(Player activeChar) {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			if (!mobInst.isDead()) {
				mobInst.reduceCurrentHp(mobInst.getMaxHp() + 1, activeChar, null);
			}

			SpawnTable.getInstance().deleteSpawn(mobInst.getSpawn(), false);
		}

		getMobs().clear();
	}

	public void setAttackRandom() {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			ControllableMobAI ai = (ControllableMobAI) mobInst.getAI();
			ai.setAlternateAI(ControllableMobAI.AI_NORMAL);
			ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
		}
	}

	public void setAttackTarget(Creature target) {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			ControllableMobAI ai = (ControllableMobAI) mobInst.getAI();
			ai.forceAttack(target);
		}
	}

	public void setIdleMode() {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			ControllableMobAI ai = (ControllableMobAI) mobInst.getAI();
			ai.stop();
		}
	}

	public void returnGroup(Creature activeChar) {
		setIdleMode();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			int signX = (Rnd.nextInt(2) == 0) ? -1 : 1;
			int signY = (Rnd.nextInt(2) == 0) ? -1 : 1;
			int randX = Rnd.nextInt(MobGroupTable.RANDOM_RANGE);
			int randY = Rnd.nextInt(MobGroupTable.RANDOM_RANGE);

			ControllableMobAI ai = (ControllableMobAI) mobInst.getAI();
			ai.move(activeChar.getX() + (signX * randX), activeChar.getY() + (signY * randY), activeChar.getZ());
		}
	}

	public void setFollowMode(Creature character) {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			ControllableMobAI ai = (ControllableMobAI) mobInst.getAI();
			ai.follow(character);
		}
	}

	public void setCastMode() {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			ControllableMobAI ai = (ControllableMobAI) mobInst.getAI();
			ai.setAlternateAI(ControllableMobAI.AI_CAST);
		}
	}

	public void setNoMoveMode(boolean enabled) {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			ControllableMobAI ai = (ControllableMobAI) mobInst.getAI();
			ai.setNotMoving(enabled);
		}
	}

	protected void removeDead() {
		getMobs().removeIf(Creature::isDead);
	}

	public void setInvul(boolean invulState) {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst != null) {
				mobInst.setInvul(invulState);
			}
		}
	}

	public void setAttackGroup(MobGroup otherGrp) {
		removeDead();

		for (L2ControllableMobInstance mobInst : getMobs()) {
			if (mobInst == null) {
				continue;
			}

			ControllableMobAI ai = (ControllableMobAI) mobInst.getAI();
			ai.forceAttackGroup(otherGrp);
			ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
		}
	}
}