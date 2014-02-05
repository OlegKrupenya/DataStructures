package com.udev.deque;

/**
 * User: oleg.krupenya
 * Date: 11/14/13
 * Time: 8:47 PM
 */
public class LinkedDeque<T> implements IDeque<T> {

    private Entry<T> head;
    private Entry<T> tail;

    private int size = 0;

    private class Entry<T> {
        private T data;
        private Entry<T> next;
        private Entry<T> previous;
    }

    @Override
    public boolean pushBack(T element) {
        if (element == null) {
            return false;
        }
        try {
            if (size == 0) {
                Entry <T> entry = new Entry<T>();
                entry.data = element;
                entry.next = null;
                entry.previous = null;
                this.head = entry;
                this.tail = entry;
            } else {
                Entry <T> entry = new Entry<T>();
                entry.data = element;
                entry.next = tail;
                entry.previous = null;
                this.tail.previous = entry;
                this.tail = entry;
            }
            size++;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean pushFront(T element) {
        try {
            if (size == 0) {
                Entry <T> entry = new Entry<T>();
                entry.data = element;
                entry.next = null;
                entry.previous = null;
                this.head = entry;
                this.tail = entry;
            } else {
                Entry <T> entry = new Entry<T>();
                entry.data = element;
                entry.previous = head;
                entry.next = null;
                this.head.next = entry;
                this.head = entry;
            }
            size++;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public T popBack() {
        if (size == 0) {
            return null;
        }
        Entry<T> entry = this.tail;
        T data = entry.data;
        this.tail = this.tail.next;
        if (tail != null) {
            this.tail.previous = null;
        }
        entry = null;
        size--;
        if (size == 0) {
            head = null;
        }
        return data;
    }

    @Override
    public T popFront() {
        if (size == 0) {
            return null;
        }
        Entry<T> entry = this.head;
        T data = entry.data;
        this.head = this.head.previous;
        if (head != null) {
            this.head.next = null;
        }
        entry = null;
        size--;
        if (size == 0) {
            tail = null;
        }
        return data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T head() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    @Override
    public T tail() {
        if (tail == null) {
            return null;
        }
        return tail.data;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
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
            currentElement = currentElement.previous;
        } while (currentElement != null);
        return false;
    }
}
