package com.udev.hashtable;

/**
 * User: oleg.krupenya
 * Date: 11/23/13
 * Time: 5:43 PM
 */
public class ChainHashTable<K, V> implements IHashTable<K, V> {

    private static final int DEFAULT_SIZE = 16;
    public static final double DEFAULT_LOAD_FACTOR = 0.75;
    private Entry<K, V>[] array;
    private int size;
    private double loadFactor;

    private class Entry<K, V> {
        private V data;
        private Entry<K, V> next;
        private int hash;
        private K key;
    }

    public ChainHashTable() {
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
        for (Entry<K, V> entry : this.array) {
            if (entry == null) {
                continue;
            }
            if (entry.next == null) {
                if (entry.key.equals(key)) {
                    return true;
                }
            } else {
                while (entry != null) {
                    if (entry.key.equals(key)) {
                        return true;
                    }
                    entry = entry.next;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (Entry<K, V> entry : this.array) {
            if (entry == null) {
                continue;
            }
            if (entry.next == null) {
                if (entry.data.equals(value)) {
                    return true;
                }
            } else {
                while (entry != null) {
                    if (entry.data.equals(value)) {
                        return true;
                    }
                    entry = entry.next;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int hash = hash(key, this.array);
        Entry<K, V> entry = this.array[hash];
        if (entry.next == null) {
            if (entry.key.equals(key)) {
                return entry.data;
            }
        } else {
            while (entry != null) {
                if (entry.key.equals(key)) {
                    return entry.data;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (size > loadFactor * this.array.length)
        {
            resizeTable();
        }
        return put(key, value,this.array);
    }

    @Override
    public V remove(K key) {
        int hash = hash(key, this.array);
        Entry<K, V> entry = this.array[hash];
        V value = null;
        if (entry == null) {
            return value;
        }
        if (entry.next == null) {
            if (entry.key.equals(key)) {
                value = entry.data;
                this.array[hash] = null;
                size--;
                return value;
            }
        } else {
            if (entry.key.equals(key)) {
                value = entry.data;
                this.array[hash] = entry.next;
                size--;
                return value;
            }
            Entry<K, V> nextEntry = entry.next;
            while (nextEntry != null) {
                if (nextEntry.key.equals(key)) {
                    value = nextEntry.data;
                    entry.next = nextEntry.next;
                    size--;
                    return value;
                }
                entry = nextEntry;
                nextEntry = nextEntry.next;
            }
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private int hash(K key, Entry[] arr) {
        return key.hashCode() % arr.length;
    }

    private V put(K key, V value, Entry[] arr) {
        int hash = hash(key, arr);
        Entry<K, V> entry = arr[hash];
        V data = null;
        if (entry == null) {
            entry = new Entry<K, V>();
            entry.hash = hash;
            entry.data = value;
            entry.key = key;
            arr[hash] = entry;
            size++;
        } else {
            if (entry.next == null) {
                if (entry.key.equals(key)) {
                    entry.data = value;
                    return entry.data;
                }
                Entry<K, V> newEntry = new Entry<K, V>();
                newEntry.hash = hash;
                newEntry.data = value;
                data = newEntry.data;
                newEntry.key = key;
                newEntry.next = entry;
                arr[hash] = newEntry;
                size++;
            } else {
                Entry<K, V> oldEntry = entry;
                while (oldEntry != null) {
                    if (oldEntry.key.equals(key)) {
                        oldEntry.data = value;
                        return oldEntry.data;
                    }
                    oldEntry = oldEntry.next;
                }
                Entry<K, V> newEntry = new Entry<K, V>();
                newEntry.hash = hash;
                newEntry.data = value;
                data = newEntry.data;
                newEntry.key = key;
                newEntry.next = entry;
                arr[hash] = newEntry;
                size++;
            }
        }
        return data;
    }

    private void resizeTable() {
        Entry<K, V> [] newArray = new Entry[this.array.length * 2];
        size = 0;
        for (int i = 0; i < this.array.length; i++) {
            Entry<K, V> entry = this.array[i];
            while (entry != null) {
                put(entry.key, entry.data, newArray);
                entry = entry.next;
            }
        }
        this.array = newArray;
    }
}
