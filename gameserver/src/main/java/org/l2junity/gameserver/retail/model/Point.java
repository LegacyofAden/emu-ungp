package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.EventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class Point {
    @EventId(8)
    public int x;
    @EventId(12)
    public int y;
    @EventId(16)
    public int z;
}