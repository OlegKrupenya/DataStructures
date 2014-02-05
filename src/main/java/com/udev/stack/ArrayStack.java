package com.udev.stack;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * User: oleg.krupenya
 * Date: 10/16/13
 * Time: 8:10 PM
 */
public class ArrayStack<T> implements IStack<T> {

    private T[] data;

    private int head = 0;

    public ArrayStack() {
        this.data = (T[]) new Object[16];
    }

    public boolean add(T element) {
        try {
            if (head == data.length) {
                data = Arrays.copyOf(data, data.length * 2);
            }
            data[head] = element;
            head++;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public T remove() {
        T removed = data[head - 1];
        data[head - 1] = null;
        head--;
        return removed;
    }

    public T head() {
        if (head == 0) {
            return null;
        }
        return data[head - 1];
    }

    public boolean contains(T t) {
        for (int i = 0; i < data.length; i++) {
            if (t != null && t.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return head;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
