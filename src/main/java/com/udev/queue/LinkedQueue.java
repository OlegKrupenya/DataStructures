package com.udev.queue;

/**
 * User: oleg.krupenya
 * Date: 10/22/13
 * Time: 10:41 PM
 */
public class LinkedQueue<T> implements IQueue<T> {

    private Entry<T> head;
    private Entry<T> tail;
    private int size = 0;

    private class Entry<T> {
        private T data;
        private Entry<T> next;
    }

    @Override
    public boolean add(T element) {
        try {
            if (tail == null && head == null) {
                Entry<T> newRecord = new Entry<T>();
                newRecord.data = element;
                tail = newRecord;
                head = newRecord;
                size++;
            } else {
                Entry<T> newRecord = new Entry<T>();
                newRecord.data = element;
                newRecord.next = tail;
                tail = newRecord;
                size++;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T head() {
        if (head != null) {
            return head.data;
        }
        return null;
    }

    @Override
    public T tail() {
        if (tail != null) {
            return tail.data;
        }
        return null;
    }

    @Override
    public boolean contains(T t) {
        if (tail == null) {
            return false;
        }
        Entry<T> currentElement = tail;
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
