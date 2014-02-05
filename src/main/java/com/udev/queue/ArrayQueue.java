package com.udev.queue;

import java.util.Arrays;

/**
 * User: oleg.krupenya
 * Date: 10/21/13
 * Time: 4:19 PM
 */
public class ArrayQueue<T> implements IQueue<T> {

    private T[] data;

    private int head = 0;

    public ArrayQueue() {
        this.data =  (T[]) new Object[2];
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        try {
            if (head == data.length) {
                T[] newArr = (T[]) new Object[data.length * 2];
                System.arraycopy(data, 0, newArr, 1, head);
                data = newArr;
                data[0] = element;
                head++;
            }
            else {
                System.arraycopy(data, 0, data, 1, head);
                data[0] = element;
                head++;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public T remove() {
        T removed = data[head - 1];
        data[head - 1] = null;
        head--;
        return removed;
    }

    @Override
    public T head() {
        if (head == 0) {
            return null;
        }
        return data[head - 1];
    }

    @Override
    public T tail() {
        return data[0];
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
