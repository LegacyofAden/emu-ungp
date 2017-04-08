package org.l2junity.gameserver.retail.model;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.retail.AiEventId;
import org.l2junity.gameserver.retail.model.actor.Creature;

import java.util.ArrayList;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class CodeInfoList extends ArrayList<CodeInfo> {
    int index = 0;

    @AiEventId(318898176)
    public void SetInfo(int index, Creature creature) {
        set(index, new CodeInfo(creature));
    }

    @AiEventId(318767105)
    public CodeInfo Next() {
        return get(index++);
    }

    @AiEventId(318767106)
    public CodeInfo RandomSelectOne() {
        return get(Rnd.get(size()));
    }
}
