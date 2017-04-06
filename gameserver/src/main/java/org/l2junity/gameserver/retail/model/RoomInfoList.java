package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.EventId;

import java.util.HashMap;

/**
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class RoomInfoList extends HashMap<Integer, RoomInfo> {
    @EventId(385941504)
    public RoomInfo GetRoomInfo(int room) {
        return get(room);
    }
}