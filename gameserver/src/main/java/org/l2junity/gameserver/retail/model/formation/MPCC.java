package org.l2junity.gameserver.retail.model.formation;

import org.l2junity.gameserver.retail.EventId;

/**
 * @author ANZO
 * @since 22.03.2017
 */
public class MPCC {
    @EventId(36)
    public int mpcc_id;

    @EventId(72)
    public int party_count;
}
