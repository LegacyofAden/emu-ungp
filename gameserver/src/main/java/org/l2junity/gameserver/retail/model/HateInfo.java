package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.AiEventId;
import org.l2junity.gameserver.retail.model.actor.Creature;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class HateInfo {
    @AiEventId(8)
    public Creature creature;

    @AiEventId(16)
    public int hate;
}
