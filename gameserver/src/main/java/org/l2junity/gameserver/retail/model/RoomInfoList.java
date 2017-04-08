package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.AiEventId;

import java.util.HashMap;

/**
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class RoomInfoList extends HashMap<Integer, RoomInfo> {
    @AiEventId(385941504)
    public RoomInfo GetRoomInfo(int room) {
        return get(room);
    }
}