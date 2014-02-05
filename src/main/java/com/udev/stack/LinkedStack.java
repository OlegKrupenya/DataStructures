package com.udev.stack;

/**
 * User: oleg.krupenya
 * Date: 10/17/13
 * Time: 6:17 PM
 */
public class LinkedStack<T> implements IStack<T> {

    private Entry<T> head;

    private int size = 0;

    private class Entry<T> {
        private T data;
        private Entry<T> next;
    }

    @Override
    public boolean add(T element) {
        try {
            if (head == null) {
                head = new Entry<T>();
                head.data = element;
            } else {
                Entry<T> next = head;
                head = new Entry<T>();
                head.data = element;
                head.next = next;
            }
            size++;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public T remove() {
        if (head == null) {
            return null;
        }
        Entry<T> currentHead = head;
        T data = currentHead.data;
        head = null;
        head = head.next;
        size--;
        return data;
    }

    @Override
    public T head() {
        if (head != null) {
            return head.data;
        }
        return null;
    }

    @Override
    public boolean contains(T t) {
        if (head == null) {
            return false;
        }
        Entry<T> currentElement = head;
        do {
            if (currentElement.data != null && currentElement.data.equals(t)) {
                return true;
            }
            currentElement = currentElement.next;
        } while (currentElement != null);
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
