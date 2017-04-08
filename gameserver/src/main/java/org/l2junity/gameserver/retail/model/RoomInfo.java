package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.AiEventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class RoomInfo {
    @AiEventId(12)
    public int member_count;

    @AiEventId(44)
    public int time;

    @AiEventId(48)
    public int party_id;

    @AiEventId(72)
    public AtomicValue status;

    @AiEventId(369164289)
    public void SetParty(int party_id) {
    }

    @AiEventId(369164288)
    public int GetMemberID(int index) {
        return 0;
    }

    @AiEventId(369098754)
    public void Clear() {
    }

    @AiEventId(369098756)
    public boolean PartyChanged() {
        return false;
    }
}
