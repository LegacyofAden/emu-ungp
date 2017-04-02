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
package handlers;

import handlers.targethandlers.*;
import handlers.targethandlers.None;
import handlers.targethandlers.affectobject.*;
import handlers.targethandlers.affectscope.*;
import org.l2junity.gameserver.handler.TargetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Target Master handler.
 *
 * @author Nik
 */
public final class TargetMasterHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(TargetMasterHandler.class);

	public static void main(String[] args) {
		// Target type handlers.
		TargetHandler.getInstance().registerTargetTypeHandler("ADVANCE_BASE", new AdvanceBase());
		TargetHandler.getInstance().registerTargetTypeHandler("ARTILLERY", new Artillery());
		TargetHandler.getInstance().registerTargetTypeHandler("DOOR_TREASURE", new DoorTreasure());
		TargetHandler.getInstance().registerTargetTypeHandler("ENEMY", new Enemy());
		TargetHandler.getInstance().registerTargetTypeHandler("ENEMY_NOT", new EnemyNot());
		TargetHandler.getInstance().registerTargetTypeHandler("ENEMY_ONLY", new EnemyOnly());
		TargetHandler.getInstance().registerTargetTypeHandler("FORTRESS_FLAGPOLE", new FortressFlagpole());
		TargetHandler.getInstance().registerTargetTypeHandler("GROUND", new Ground());
		TargetHandler.getInstance().registerTargetTypeHandler("HOLYTHING", new HolyThing());
		TargetHandler.getInstance().registerTargetTypeHandler("ITEM", new Item());
		TargetHandler.getInstance().registerTargetTypeHandler("MY_MENTOR", new MyMentor());
		TargetHandler.getInstance().registerTargetTypeHandler("MY_PARTY", new MyParty());
		TargetHandler.getInstance().registerTargetTypeHandler("NONE", new None());
		TargetHandler.getInstance().registerTargetTypeHandler("NPC_BODY", new NpcBody());
		TargetHandler.getInstance().registerTargetTypeHandler("OTHERS", new Others());
		TargetHandler.getInstance().registerTargetTypeHandler("PC_BODY", new PcBody());
		TargetHandler.getInstance().registerTargetTypeHandler("SELF", new Self());
		TargetHandler.getInstance().registerTargetTypeHandler("SUMMON", new Summon());
		TargetHandler.getInstance().registerTargetTypeHandler("TARGET", new Target());
		TargetHandler.getInstance().registerTargetTypeHandler("WYVERN_TARGET", new WyvernTarget());

		// Affect scope handlers.
		TargetHandler.getInstance().registerAffectScopeHandler("BALAKAS_SCOPE", new BalakasScope());
		TargetHandler.getInstance().registerAffectScopeHandler("DEAD_PARTY", new DeadParty());
		TargetHandler.getInstance().registerAffectScopeHandler("DEAD_PARTY_PLEDGE", new DeadPartyPledge());
		TargetHandler.getInstance().registerAffectScopeHandler("DEAD_PLEDGE", new DeadPledge());
		TargetHandler.getInstance().registerAffectScopeHandler("DEAD_UNION", new DeadUnion());
		TargetHandler.getInstance().registerAffectScopeHandler("FAN", new Fan());
		TargetHandler.getInstance().registerAffectScopeHandler("FAN_PB", new FanPB());
		TargetHandler.getInstance().registerAffectScopeHandler("NONE", new handlers.targethandlers.affectscope.None());
		TargetHandler.getInstance().registerAffectScopeHandler("PARTY", new Party());
		TargetHandler.getInstance().registerAffectScopeHandler("PARTY_PLEDGE", new PartyPledge());
		TargetHandler.getInstance().registerAffectScopeHandler("PLEDGE", new Pledge());
		TargetHandler.getInstance().registerAffectScopeHandler("POINT_BLANK", new PointBlank());
		TargetHandler.getInstance().registerAffectScopeHandler("RANGE", new Range());
		TargetHandler.getInstance().registerAffectScopeHandler("RANGE_SORT_BY_HP", new RangeSortByHp());
		TargetHandler.getInstance().registerAffectScopeHandler("RING_RANGE", new RingRange());
		TargetHandler.getInstance().registerAffectScopeHandler("SINGLE", new Single());
		TargetHandler.getInstance().registerAffectScopeHandler("SQUARE", new Square());
		TargetHandler.getInstance().registerAffectScopeHandler("SQUARE_PB", new SquarePB());
		TargetHandler.getInstance().registerAffectScopeHandler("STATIC_OBJECT_SCOPE", new StaticObjectScope());
		TargetHandler.getInstance().registerAffectScopeHandler("SUMMON_EXCEPT_MASTER", new SummonExceptMaster());
		TargetHandler.getInstance().registerAffectScopeHandler("WYVERN_SCOPE", new WyvernScope());

		// Affect object handlers.
		TargetHandler.getInstance().registerAffectObjectHandler("ALL", new All());
		TargetHandler.getInstance().registerAffectObjectHandler("CLAN", new Clan());
		TargetHandler.getInstance().registerAffectObjectHandler("FRIEND", new Friend());
		TargetHandler.getInstance().registerAffectObjectHandler("FRIEND_PC", new FriendPc());
		TargetHandler.getInstance().registerAffectObjectHandler("HIDDEN_PLACE", new HiddenPlace());
		TargetHandler.getInstance().registerAffectObjectHandler("INVISIBLE", new Invisible());
		TargetHandler.getInstance().registerAffectObjectHandler("NOT_FRIEND", new NotFriend());
		TargetHandler.getInstance().registerAffectObjectHandler("NOT_FRIEND_PC", new NotFriendPc());
		TargetHandler.getInstance().registerAffectObjectHandler("OBJECT_DEAD_NPC_BODY", new ObjectDeadNpcBody());
		TargetHandler.getInstance().registerAffectObjectHandler("UNDEAD_REAL_ENEMY", new UndeadRealEnemy());
		TargetHandler.getInstance().registerAffectObjectHandler("WYVERN_OBJECT", new WyvernObject());

		LOGGER.info("Loaded {} target type handlers.", TargetHandler.getInstance().getTargetTypeHandlersSize());
		LOGGER.info("Loaded {} affect scope handlers.", TargetHandler.getInstance().getAffectScopeHandlersSize());
		LOGGER.info("Loaded {} affect object handlers.", TargetHandler.getInstance().getAffectObjectHandlersSize());
	}
}
