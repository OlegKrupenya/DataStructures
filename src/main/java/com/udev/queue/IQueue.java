package com.udev.queue;

/**
 * User: oleg.krupenya
 * Date: 10/21/13
 * Time: 4:14 PM
 */
public interface IQueue<T> {
    public boolean add(T element);
    public T remove();
    public T head();
    public T tail();
    public boolean contains(T t);
    public boolean isEmpty();
    public int size();
}
