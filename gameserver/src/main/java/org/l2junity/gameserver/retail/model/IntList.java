package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.AiEventId;

import java.util.ArrayList;

/**
 * @author ANZO
 */
public class IntList extends ArrayList<Integer> {
    @AiEventId(536936448)
    public void Add(int value) {
        add(value);
    }

    @AiEventId(536936449)
    public void Remove(int value) {
        remove((Integer)value);
    }

    @AiEventId(536936451)
    public int Get(int index) {
        return get(index);
    }

    @AiEventId(536870914)
    public void Clear() {
        clear();
    }

    @AiEventId(536870918)
    public int GetSize() {
        return size();
    }
}