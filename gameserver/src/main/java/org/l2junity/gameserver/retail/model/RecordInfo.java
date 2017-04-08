package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.AiEventId;

/**
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class RecordInfo {
    @AiEventId(60)
    public double hp;

    @AiEventId(64)
    public double mp;

    @AiEventId(68)
    public int x;

    @AiEventId(72)
    public int y;

    @AiEventId(76)
    public int z;

    @AiEventId(80)
    public int death_time;

    @AiEventId(84)
    public boolean is_alive;

    @AiEventId(88)
    public int db_value;
}
