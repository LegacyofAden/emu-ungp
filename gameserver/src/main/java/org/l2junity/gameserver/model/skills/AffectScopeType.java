package org.l2junity.gameserver.model.skills;

import java.util.function.Consumer;

import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.skills.affectscopetypes.BalakasScope;
import org.l2junity.gameserver.model.skills.affectscopetypes.DeadParty;
import org.l2junity.gameserver.model.skills.affectscopetypes.DeadPartyPledge;
import org.l2junity.gameserver.model.skills.affectscopetypes.DeadPledge;
import org.l2junity.gameserver.model.skills.affectscopetypes.DeadUnion;
import org.l2junity.gameserver.model.skills.affectscopetypes.Fan;
import org.l2junity.gameserver.model.skills.affectscopetypes.FanPB;
import org.l2junity.gameserver.model.skills.affectscopetypes.None;
import org.l2junity.gameserver.model.skills.affectscopetypes.Party;
import org.l2junity.gameserver.model.skills.affectscopetypes.PartyPledge;
import org.l2junity.gameserver.model.skills.affectscopetypes.Pledge;
import org.l2junity.gameserver.model.skills.affectscopetypes.PointBlank;
import org.l2junity.gameserver.model.skills.affectscopetypes.Range;
import org.l2junity.gameserver.model.skills.affectscopetypes.RangeSortByHp;
import org.l2junity.gameserver.model.skills.affectscopetypes.RingRange;
import org.l2junity.gameserver.model.skills.affectscopetypes.Single;
import org.l2junity.gameserver.model.skills.affectscopetypes.Square;
import org.l2junity.gameserver.model.skills.affectscopetypes.SquarePB;
import org.l2junity.gameserver.model.skills.affectscopetypes.StaticObjectScope;
import org.l2junity.gameserver.model.skills.affectscopetypes.SummonExceptMaster;
import org.l2junity.gameserver.model.skills.affectscopetypes.WyvernScope;

/**
 * @author ANZO
 * @since 02.04.2017
 */
public enum AffectScopeType {
	NONE(new None()),
	BALAKAS_SCOPE(new BalakasScope()),
	DEAD_PARTY(new DeadParty()),
	DEAD_PARTY_PLEDGE(new DeadPartyPledge()),
	DEAD_PLEDGE(new DeadPledge()),
	DEAD_UNION(new DeadUnion()),
	FAN(new Fan()),
	FAN_PB(new FanPB()),
	PARTY(new Party()),
	PARTY_PLEDGE(new PartyPledge()),
	PLEDGE(new Pledge()),
	POINT_BLANK(new PointBlank()),
	RANGE(new Range()),
	RANGE_SORT_BY_HP(new RangeSortByHp()),
	RING_RANGE(new RingRange()),
	SINGLE(new Single()),
	SQUARE(new Square()),
	SQUARE_PB(new SquarePB()),
	STATIC_OBJECT_SCOPE(new StaticObjectScope()),
	SUMMON_EXCEPT_MASTER(new SummonExceptMaster()),
	WYVERN_SCOPE(new WyvernScope());

	IAffectScopeHandler scopeHandler;

	AffectScopeType(IAffectScopeHandler scopeHandler) {
		this.scopeHandler = scopeHandler;
	}

	public void forEachAffected(Creature activeChar, WorldObject target, Skill skill, Consumer<? super WorldObject> action) {
		scopeHandler.forEachAffected(activeChar, target, skill, action);
	}

	public void drawEffected(Creature activeChar, WorldObject target, Skill skill) {
		scopeHandler.drawEffected(activeChar, target, skill);
	}
}
