package org.l2junity.gameserver.retail.model.formation;

import org.l2junity.gameserver.retail.EventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class Party {
    @EventId(36)
    public int id;

    @EventId(76)
    public int member_count;
}