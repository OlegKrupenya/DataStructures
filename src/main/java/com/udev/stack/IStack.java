package com.udev.stack;

/**
 * User: oleg.krupenya
 * Date: 10/17/13
 * Time: 1:20 PM
 */
public interface IStack<T> {
    public boolean add(T element);
    public T remove();
    public T head();
    public boolean contains(T t);
    public boolean isEmpty();
    public int size();
}
