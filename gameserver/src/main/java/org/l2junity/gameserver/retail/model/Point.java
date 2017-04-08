package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.AiEventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class Point {
    @AiEventId(8)
    public int x;
    @AiEventId(12)
    public int y;
    @AiEventId(16)
    public int z;
}