package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.EventId;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ANZO
 * @since 20.03.2017
 */
public class AtomicValue extends AtomicInteger {
    @EventId(8)
    public int value;

    @EventId(352387074)
    public int Exchange(int value) {
        return getAndSet(value);
    }

    @EventId(570490882)
    public int ExchangeGet(int value) {
        return getAndSet(value);
    }

    @EventId(352321539)
    public int GetValue() {
        return get();
    }

    @EventId(570425347)
    public int GetValue2() {
        return get();
    }

    @EventId(352387073)
    public void Decrement(int value) {
        addAndGet(-value);
    }

    @EventId(570490881)
    public void Decrement2(int value) {
        addAndGet(-value);
    }

    @EventId(352452612)
    public int CompareExchange(int i0, int i1) {
        if (compareAndSet(i0, i1)) {
            return i1;
        }
        return get();
    }

    @EventId(352387072)
    public int Increment(int count) {
        return getAndAdd(count);
    }
}
