package com.udev.deque;

import java.util.Arrays;

/**
 * User: oleg.krupenya
 * Date: 11/2/13
 * Time: 8:18 PM
 */
public class ArrayDeque<T> implements IDeque<T> {

    private T[] data;

    private int head = 0;

    public ArrayDeque() {
        this.data = (T[]) new Object[16];
    }

    @Override
    public boolean pushBack(T element) {
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
            } else {
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
    public boolean pushFront(T element) {
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

    @Override
    public T popBack() {
        if (isEmpty()) {
            return null;
        }
        T removed = data[0];
        for (int i = 1; i <= data.length; i++) {
            data[i - 1] = data[i];
        }
        head--;
        return removed;
    }

    @Override
    public T popFront() {
        T removed = data[head - 1];
        data[head - 1] = null;
        head--;
        return removed;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T head() {
        if (isEmpty()){
            return null;
        }
        return data[head - 1];
    }

    @Override
    public T tail() {
        return data[0];
    }

    @Override
    public void clear() {
        for (int i = 0; i <= data.length; i++) {
            data[i] = null;
        }
    }

    @Override
    public int size() {
        return head;
    }

    @Override
    public boolean contains(T t) {
        for (int i = 0; i < data.length; i++) {
            if (t != null && t.equals(data[i])) {
                return true;
            }
        }
        return false;
    }
}
