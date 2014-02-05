package com.udev.hashtable;

/**
 * User: oleg.krupenya
 * Date: 11/20/13
 * Time: 1:41 PM
 */
public interface IHashTable<K,V> {
    public void clear();
    public boolean containsKey(K key);
    public boolean containsValue(V value);
    public V get(K key);
    public V remove(K key);
    public V put(K key, V value);
    public boolean isEmpty();
    public int size();
}
