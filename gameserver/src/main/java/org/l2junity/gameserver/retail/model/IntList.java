package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.EventId;

import java.util.ArrayList;

/**
 * @author ANZO
 */
public class IntList extends ArrayList<Integer> {
    @EventId(536936448)
    public void Add(int value) {
        add(value);
    }

    @EventId(536936449)
    public void Remove(int value) {
        remove((Integer)value);
    }

    @EventId(536936451)
    public int Get(int index) {
        return get(index);
    }

    @EventId(536870914)
    public void Clear() {
        clear();
    }

    @EventId(536870918)
    public int GetSize() {
        return size();
    }
}