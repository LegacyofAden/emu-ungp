package org.l2junity.gameserver.retail.model.formation;

import org.l2junity.gameserver.retail.AiEventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class Party {
    @AiEventId(36)
    public int id;

    @AiEventId(76)
    public int member_count;
}