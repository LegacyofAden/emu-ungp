package org.l2junity.gameserver.model.skills;

import org.l2junity.gameserver.handler.ITargetTypeHandler;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.skills.targettypes.*;

/**
 * @author ANZO
 * @since 02.04.2017
 */
public enum TargetType {
	NONE(new None()),
	ADVANCE_BASE(new AdvanceBase()),
	ARTILLERY(new Artillery()),
	DOOR_TREASURE(new DoorTreasure()),
	ENEMY(new Enemy()),
	ENEMY_NOT(new EnemyNot()),
	ENEMY_ONLY(new EnemyOnly()),
	FORTRESS_FLAGPOLE(new FortressFlagpole()),
	GROUND(new Ground()),
	HOLYTHING(new HolyThing()),
	ITEM(new Item()),
	MY_MENTOR(new MyMentor()),
	MY_PARTY(new MyParty()),
	NPC_BODY(new NpcBody()),
	OTHERS(new Others()),
	PC_BODY(new PcBody()),
	SELF(new Self()),
	SUMMON(new Summon()),
	TARGET(new Target()),
	WYVERN_TARGET(new WyvernTarget());

	ITargetTypeHandler targetTypeHandler;

	TargetType(ITargetTypeHandler targetTypeHandler) {
		this.targetTypeHandler = targetTypeHandler;
	}

	public WorldObject getTarget(Creature activeChar, WorldObject selectedTarget, Skill skill, boolean forceUse, boolean dontMove, boolean sendMessage) {
		return targetTypeHandler.getTarget(activeChar, selectedTarget, skill, forceUse, dontMove, sendMessage);
	}
}