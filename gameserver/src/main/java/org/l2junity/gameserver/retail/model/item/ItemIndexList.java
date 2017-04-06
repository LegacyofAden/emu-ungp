package org.l2junity.gameserver.retail.model.item;

import org.l2junity.gameserver.retail.EventId;

import java.util.HashMap;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class ItemIndexList extends HashMap<Integer, Integer> {
    @EventId(520093696)
    public int GetSize() {
        return size();
    }

    @EventId(520159233)
    public int GetItemIndex(int item_id) {
        for (Entry<Integer, Integer> entry : entrySet()) {
            if (entry.getValue() == item_id) {
                return entry.getKey();
            }
        }
        return 0;
    }
}