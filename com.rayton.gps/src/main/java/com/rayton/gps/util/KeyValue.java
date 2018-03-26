package com.rayton.gps.util;

public class KeyValue {
    private Object key;
    private Object value;

    public static KeyValue of(Object key, Object value) {
        KeyValue kv = new KeyValue();
        kv.setKey(key);
        kv.setValue(value);

        return kv;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
