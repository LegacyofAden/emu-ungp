package org.l2junity.gameserver.retail.model;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.retail.AiEventId;
import org.l2junity.gameserver.retail.model.actor.Creature;

import java.util.ArrayList;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class CodeInfo extends ArrayList<Creature> {
    @AiEventId(52)
    public int code;

    private int index = 0;

    public CodeInfo(Creature creature) {
    	add(creature);
	}

    @AiEventId(318767106)
    public Creature RandomSelectOne() {
        return get(Rnd.get(size()));
    }

    @AiEventId(301989889)
    public Creature RandomSelectOne2() {
        // TODO: Params?
		return get(Rnd.get(size()));
    }

    @AiEventId(301989888)
    public Creature Next() {
        return get(index++);
    }
}
