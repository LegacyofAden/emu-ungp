package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.EventId;

/**
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class RecordInfo {
    @EventId(60)
    public double hp;

    @EventId(64)
    public double mp;

    @EventId(68)
    public int x;

    @EventId(72)
    public int y;

    @EventId(76)
    public int z;

    @EventId(80)
    public int death_time;

    @EventId(84)
    public boolean is_alive;

    @EventId(88)
    public int db_value;
}
