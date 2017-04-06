package org.l2junity.gameserver.retail.model.formation;

import org.l2junity.gameserver.retail.EventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class Clan {
    @EventId(68)
    public int db_id;

    @EventId(72)
    public String name;

    @EventId(196)
    public int skill_level;

    @EventId(232)
    public int castle_id;

    @EventId(236)
    public int agit_id;

    @EventId(240)
    public int fortress_id;

    @EventId(1968)
    public int prev_server;

    @EventId(2020)
    public int airship_count;

    @EventId(2024)
    public int pledge_union_type;
}
