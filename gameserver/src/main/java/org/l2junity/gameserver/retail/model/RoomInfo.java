package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.EventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class RoomInfo {
    @EventId(12)
    public int member_count;

    @EventId(44)
    public int time;

    @EventId(48)
    public int party_id;

    @EventId(72)
    public AtomicValue status;

    @EventId(369164289)
    public void SetParty(int party_id) {
    }

    @EventId(369164288)
    public int GetMemberID(int index) {
        return 0;
    }

    @EventId(369098754)
    public void Clear() {
    }

    @EventId(369098756)
    public boolean PartyChanged() {
        return false;
    }
}
