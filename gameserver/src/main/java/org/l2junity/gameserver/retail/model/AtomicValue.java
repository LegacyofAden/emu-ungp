package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.AiEventId;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ANZO
 * @since 20.03.2017
 */
public class AtomicValue extends AtomicInteger {
    @AiEventId(8)
    public int value;

    @AiEventId(352387074)
    public int Exchange(int value) {
        return getAndSet(value);
    }

    @AiEventId(570490882)
    public int ExchangeGet(int value) {
        return getAndSet(value);
    }

    @AiEventId(352321539)
    public int GetValue() {
        return get();
    }

    @AiEventId(570425347)
    public int GetValue2() {
        return get();
    }

    @AiEventId(352387073)
    public void Decrement(int value) {
        addAndGet(-value);
    }

    @AiEventId(570490881)
    public void Decrement2(int value) {
        addAndGet(-value);
    }

    @AiEventId(352452612)
    public int CompareExchange(int i0, int i1) {
        if (compareAndSet(i0, i1)) {
            return i1;
        }
        return get();
    }

    @AiEventId(352387072)
    public int Increment(int count) {
        return getAndAdd(count);
    }
}
