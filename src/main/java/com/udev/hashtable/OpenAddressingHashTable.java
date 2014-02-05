package com.udev.hashtable;

/**
 * User: oleg.krupenya
 * Date: 12/4/13
 * Time: 8:20 PM
 */
public class OpenAddressingHashTable<K, V> implements IHashTable<K, V> {

    private static final int DEFAULT_SIZE = 16;
    public static final double DEFAULT_LOAD_FACTOR = 0.75;
    private Entry<K, V>[] array;
    private int size;
    private double loadFactor;

    private class Entry<K, V> {
        private K key;
        private V value;
        private boolean isDeleted;
        private int iterNbr;
    }

    public OpenAddressingHashTable() {
        this.array = new Entry[DEFAULT_SIZE];
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    @Override
    public void clear() {
        this.size = 0;
        array = new Entry[array.length];
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        for (Entry entry : this.array) {
            if (entry != null && key.equals(entry.key) && !entry.isDeleted) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null) {
            return false;
        }
        for (Entry entry : this.array) {
            if (entry != null && value.equals(entry.value) && !entry.isDeleted) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        int i = 0;
        V data = null;
        int hash = 0;
        Entry<K, V> entry = null;
        while (entry == null) {
            hash = hash(key, i);
            entry = this.array[hash];
            if (key.equals(entry.key) && !entry.isDeleted){
                data = entry.value;
                return data;
            }
            i++;
        }
        return data;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        int i = 0;
        V data = null;
        int hash = 0;
        Entry<K, V> entry = null;
        while (entry == null || i <= size) {
            hash = hash(key, i);
            entry = this.array[hash];
            if (key.equals(entry.key)){
                data = entry.value;
                entry.value = null;
                entry.isDeleted = true;
                size--;
                return data;
            }
            i++;
        }
        return data;
    }

    @Override
    public V put(K key, V value) {
        if (size > loadFactor * this.array.length)
        {
            resizeTable();
        }
        int i = 0;
        V data = null;
        int hash = hash(key, i);
        Entry<K, V> entry = this.array[hash];
        while (entry != null) {
            if (key.equals(entry.key)) {
                entry.value = value;
                if (entry.isDeleted) {
                    entry.isDeleted = false;
                    entry.iterNbr = i;
                    size++;
                }
                return value;
            }
            else if (entry.isDeleted) {
                break;
            }
            hash = hash(key, i);
            entry = this.array[hash];
            i++;
        }
        if (entry == null) {
            entry = new Entry<K, V>();
            entry.key = key;
            entry.value = value;
            entry.iterNbr = i;
            array[hash] = entry;
            data = value;
            size++;
        }
        return data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private int hash(K key, int i) {
        //return  1 + i;
        return  (key.hashCode() + i) % this.array.length;
    }

    private void resizeTable() {
        Entry<K, V> [] oldArray = this.array;
        this.array = new Entry[oldArray.length * 2];
        this.size = 0;
        for (int i = 0; i < oldArray.length; i++) {
            Entry<K, V> entry = oldArray[i];
            if (entry != null && !entry.isDeleted) {
                put(entry.key, entry.value);
            }
        }
    }
}
