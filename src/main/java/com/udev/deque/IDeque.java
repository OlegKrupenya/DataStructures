package com.udev.deque;

/**
 * User: oleg.krupenya
 * Date: 10/31/13
 * Time: 8:48 PM
 */
public interface IDeque<T> {
    public boolean pushBack(T element);

    public boolean pushFront(T element);

    public T popBack();

    public T popFront();

    public boolean isEmpty();

    public T head();

    public T tail();

    public void clear();

    public int size();

    public boolean contains(T t);
}
