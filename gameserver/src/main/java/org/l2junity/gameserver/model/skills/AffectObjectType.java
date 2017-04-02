package org.l2junity.gameserver.model.skills;

import org.l2junity.gameserver.handler.IAffectObjectHandler;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.skills.affectobjecttypes.*;

/**
 * @author ANZO
 * @since 02.04.2017
 */
public enum AffectObjectType {
	ALL(new All()),
	CLAN(new Clan()),
	FRIEND(new Friend()),
	FRIEND_PC(new FriendPc()),
	HIDDEN_PLACE(new HiddenPlace()),
	INVISIBLE(new Invisible()),
	NOT_FRIEND(new NotFriend()),
	NOT_FRIEND_PC(new NotFriendPc()),
	OBJECT_DEAD_NPC_BODY(new ObjectDeadNpcBody()),
	UNDEAD_REAL_ENEMY(new UndeadRealEnemy()),
	WYVERN_OBJECT(new WyvernObject());

	IAffectObjectHandler affectObjectHandler;

	AffectObjectType(IAffectObjectHandler affectObjectHandler) {
		this.affectObjectHandler = affectObjectHandler;
	}

	public boolean checkAffectedObject(Creature activeChar, Creature target) {
		return affectObjectHandler.checkAffectedObject(activeChar, target);
	}
}