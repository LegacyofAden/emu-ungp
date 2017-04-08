package org.l2junity.gameserver.retail.model.formation;

import org.l2junity.gameserver.retail.AiEventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class Clan {
    @AiEventId(68)
    public int db_id;

    @AiEventId(72)
    public String name;

    @AiEventId(196)
    public int skill_level;

    @AiEventId(232)
    public int castle_id;

    @AiEventId(236)
    public int agit_id;

    @AiEventId(240)
    public int fortress_id;

    @AiEventId(1968)
    public int prev_server;

    @AiEventId(2020)
    public int airship_count;

    @AiEventId(2024)
    public int pledge_union_type;
}
