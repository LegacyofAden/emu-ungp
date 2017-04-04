package org.l2junity.commons.collections;

/**
 * @author PointerRage
 *
 */
public interface IMultiValue<K> {
    Object put(K key, Object value);
    Object remove(K key);
    Object get(K key);

    boolean isReplaceStringValue();
    void setReplaceStringValue(boolean value);

    default void set(K key, Object value) {
        put(key, value);
    }

    default void set(K key, String value) {
        put(key, value);
    }

    default void set(K key, boolean value) {
        put(key, value ? Boolean.TRUE : Boolean.FALSE);
    }

    default void set(K key, int value) {
        put(key, Integer.valueOf(value));
    }

    default void set(K key, int[] value) {
        put(key, value);
    }

    default void set(K key, long value) {
        put(key, Long.valueOf(value));
    }

    default void set(K key, double value) {
        put(key, Double.valueOf(value));
    }

    default void set(K key, Enum<?> value) {
        put(key, value);
    }

    default void unset(K key) {
        remove(key);
    }

    default boolean isSet(K key) {
        return get(key) != null;
    }

    default boolean getBool(K key) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).intValue() != 0;

        if(val instanceof String) {
            final boolean value = Boolean.parseBoolean((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue();

        throw new IllegalArgumentException("Boolean value required, but found: " + val + "!");
    }

    default boolean getBool(K key, boolean defaultValue) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).intValue() != 0;

        if(val instanceof String) {
            final boolean value = Boolean.parseBoolean((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue();

        return defaultValue;
    }

    default byte getByte(K key) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).byteValue();

        if(val instanceof String) {
            final byte value = Byte.parseByte((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return (byte) (((Boolean) val).booleanValue() ? 1 : 0);

        throw new IllegalArgumentException("Byte value required, but found: " + val + "!");
    }

    default byte getByte(K key, byte defaultValue) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).byteValue();

        if(val instanceof String) {
            final byte value = Byte.parseByte((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return (byte) (((Boolean) val).booleanValue() ? 1 : 0);

        return defaultValue;
    }

    default int getInteger(K key) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).intValue();

        if(val instanceof String) {
            final int value = Integer.parseInt((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue() ? 1 : 0;

        throw new IllegalArgumentException("Integer value required, but found: " + val + "!");
    }

    default int getInteger(K key, int defaultValue) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).intValue();

        if(val instanceof String) {
            final int value = Integer.parseInt((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue() ? 1 : 0;

        return defaultValue;
    }

    default int[] getIntegerArray(K key) {
        final Object val = get(key);

        if(val instanceof int[])
            return (int[]) val;

        if(val instanceof String) {
            final String[] vals = ((String) val).split(";");
            final int[] result = new int[vals.length];

            for(int i = 0; i < vals.length; i++) {
                result[i] = Integer.parseInt(vals[i]);
            }

            if(isReplaceStringValue()) {
                set(key, result);
            }

            return result;
        }

        if(val instanceof Number)
            return new int[] { ((Number) val).intValue() };

        throw new IllegalArgumentException("Integer array required, but found: " + val + "!");
    }

    default int[] getIntegerArray(K key, int[] defaultArray) {
        try {
            return getIntegerArray(key);
        } catch(IllegalArgumentException e) {
            return defaultArray;
        }
    }

    default long getLong(K key) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).longValue();

        if(val instanceof String) {
            final long value = Long.parseLong((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue() ? 1L : 0L;

        throw new IllegalArgumentException("Long value required, but found: " + val + "!");
    }

    default long getLong(K key, long defaultValue) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).longValue();

        if(val instanceof String) {
            final long value = Long.parseLong((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue() ? 1L : 0L;

        return defaultValue;
    }

    default long[] getLongArray(K key) {
        final Object val = get(key);

        if(val instanceof long[])
            return (long[]) val;

        if(val instanceof Number)
            return new long[] { ((Number) val).longValue() };

        if(val instanceof String) {
            final String[] vals = ((String) val).split(";");
            final long[] value = new long[vals.length];

            for(int i = 0; i < vals.length; i++) {
                value[i] = Integer.parseInt(vals[i]);
            }

            if(isReplaceStringValue()) {
                set(key, value);
            }

            return value;
        }

        throw new IllegalArgumentException("Integer array required, but found: " + val + "!");
    }

    default long[] getLongArray(K key, long[] defaultValue) {
        try {
            return getLongArray(key);
        } catch(IllegalArgumentException e) {
            return defaultValue;
        }
    }

    default double getDouble(K key) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).doubleValue();

        if(val instanceof String) {
            final double value = Double.parseDouble((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue() ? 1. : 0.;

        throw new IllegalArgumentException("Double value required, but found: " + val + "!");
    }

    default double getDouble(K key, double defaultValue) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).doubleValue();

        if(val instanceof String) {
            final double value = Double.parseDouble((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue() ? 1. : 0.;

        return defaultValue;
    }

    default float getFloat(K key) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).floatValue();

        if(val instanceof String) {
            final float value = Float.parseFloat((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue() ? 1.f : 0.f;

        throw new IllegalArgumentException("Float value required, but found: " + val + "!");
    }

    default float getFloat(K key, float defaultValue) {
        final Object val = get(key);

        if(val instanceof Number)
            return ((Number) val).floatValue();

        if(val instanceof String) {
            final float value = Float.parseFloat((String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        if(val instanceof Boolean)
            return ((Boolean) val).booleanValue() ? 1.f : 0.f;

        return defaultValue;
    }

    default String getString(K key) {
        final Object val = get(key);

        if(val != null)
            return String.valueOf(val);

        throw new IllegalArgumentException("String value required, but not specified!");
    }

    default String getString(K key, String defaultValue) {
        final Object val = get(key);

        if(val != null)
            return String.valueOf(val);

        return defaultValue;
    }

    default Object getObject(K key) {
        return get(key);
    }

    default Object getObject(K key, Object defaultValue) {
        final Object val = get(key);

        if(val != null)
            return val;

        return defaultValue;
    }

    @SuppressWarnings("unchecked")
    default <E extends Enum<E>> E getEnum(K key, Class<E> enumClass) {
        final Object val = get(key);

        if(val != null && enumClass.isInstance(val))
            return (E) val;

        if(val instanceof String) {
            final E value = Enum.valueOf(enumClass, (String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        throw new IllegalArgumentException("Enum value of type " + enumClass.getName() + "required, but found: " + val + "!");
    }

    @SuppressWarnings("unchecked")
    default <E extends Enum<E>> E getEnum(K key, Class<E> enumClass, E defaultValue) {
        final Object val = get(key);

        if(val != null && enumClass.isInstance(val))
            return (E) val;

        if(val instanceof String) {
            final E value = Enum.valueOf(enumClass, (String) val);
            if(isReplaceStringValue()) {
                set(key, value);
            }
            return value;
        }

        return defaultValue;
    }
}
