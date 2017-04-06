package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.EventId;
import org.l2junity.gameserver.retail.model.actor.Creature;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class HateInfo {
    @EventId(8)
    public Creature creature;

    @EventId(16)
    public int hate;
}
