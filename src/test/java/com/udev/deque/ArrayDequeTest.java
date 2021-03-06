package com.udev.deque;

import com.udev.queue.ArrayQueue;
import com.udev.queue.IQueue;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: oleg.krupenya
 * Date: 11/2/13
 * Time: 8:44 PM
 */
public class ArrayDequeTest {
    @Test
    public void addBackTest_hp() {
        IDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.pushBack(1);
        deque.pushBack(2);
        deque.pushBack(3);
        Assert.assertTrue(deque.size() == 3);
        Assert.assertTrue(deque.contains(1));
        Assert.assertTrue(deque.contains(2));
        Assert.assertTrue(deque.contains(3));
        Assert.assertFalse(deque.isEmpty());
        Assert.assertEquals(Integer.valueOf(3), deque.tail());
        Assert.assertEquals(Integer.valueOf(1), deque.head());
    }

    @Test
    public void addFrontTest_hp() {
        IDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.pushFront(1);
        deque.pushFront(2);
        deque.pushFront(3);
        Assert.assertTrue(deque.size() == 3);
        Assert.assertTrue(deque.contains(1));
        Assert.assertTrue(deque.contains(2));
        Assert.assertTrue(deque.contains(3));
        Assert.assertFalse(deque.isEmpty());
        Assert.assertEquals(Integer.valueOf(3), deque.head());
    }

    @Test
    public void removeFrontTest_hp() {
        IDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.pushBack(1);
        deque.pushBack(2);
        deque.pushBack(3);
        Assert.assertTrue(deque.size() == 3);
        Assert.assertTrue(deque.contains(1));
        Assert.assertTrue(deque.contains(2));
        Assert.assertTrue(deque.contains(3));
        Assert.assertEquals(Integer.valueOf(3), deque.tail());
        Assert.assertEquals(Integer.valueOf(1), deque.head());

        Integer removed = deque.popFront();
        Assert.assertTrue(deque.size() == 2);
        Assert.assertTrue(deque.contains(3));
        Assert.assertTrue(deque.contains(2));
        Assert.assertFalse(deque.contains(1));
        Assert.assertEquals(Integer.valueOf(2), deque.head());
        Assert.assertEquals(Integer.valueOf(3), deque.tail());
        Assert.assertEquals(Integer.valueOf(1), removed);
        Assert.assertFalse(deque.isEmpty());

        removed = deque.popFront();
        Assert.assertTrue(deque.size() == 1);
        Assert.assertTrue(deque.contains(3));
        Assert.assertFalse(deque.contains(2));
        Assert.assertFalse(deque.contains(1));
        Assert.assertEquals(Integer.valueOf(3), deque.head());
        Assert.assertEquals(Integer.valueOf(3), deque.tail());
        Assert.assertEquals(Integer.valueOf(2), removed);
        Assert.assertFalse(deque.isEmpty());

        removed = deque.popFront();
        Assert.assertTrue(deque.size() == 0);
        Assert.assertFalse(deque.contains(1));
        Assert.assertFalse(deque.contains(2));
        Assert.assertFalse(deque.contains(3));
        Assert.assertNull(deque.head());
        Assert.assertNull(deque.tail());
        Assert.assertEquals(Integer.valueOf(3), removed);
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void removeBackTest_hp() {
        IDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.pushBack(1);
        deque.pushBack(2);
        deque.pushBack(3);
        Assert.assertTrue(deque.size() == 3);
        Assert.assertTrue(deque.contains(1));
        Assert.assertTrue(deque.contains(2));
        Assert.assertTrue(deque.contains(3));
        Assert.assertEquals(Integer.valueOf(3), deque.tail());
        Assert.assertEquals(Integer.valueOf(1), deque.head());

        Integer removed = deque.popBack();
        Assert.assertTrue(deque.size() == 2);
        Assert.assertTrue(deque.contains(1));
        Assert.assertTrue(deque.contains(2));
        Assert.assertFalse(deque.contains(3));
        Assert.assertEquals(Integer.valueOf(1), deque.head());
        Assert.assertEquals(Integer.valueOf(2), deque.tail());
        Assert.assertEquals(Integer.valueOf(1), removed);
        Assert.assertFalse(deque.isEmpty());

        removed = deque.popBack();
        Assert.assertTrue(deque.size() == 1);
        Assert.assertTrue(deque.contains(1));
        Assert.assertFalse(deque.contains(2));
        Assert.assertFalse(deque.contains(3));
        Assert.assertEquals(Integer.valueOf(1), deque.head());
        Assert.assertEquals(Integer.valueOf(1), deque.tail());
        Assert.assertEquals(Integer.valueOf(2), removed);
        Assert.assertFalse(deque.isEmpty());


        removed = deque.popBack();
        Assert.assertTrue(deque.size() == 0);
        Assert.assertFalse(deque.contains(1));
        Assert.assertFalse(deque.contains(2));
        Assert.assertFalse(deque.contains(3));
        Assert.assertNull(deque.head());
        Assert.assertNull(deque.tail());
        Assert.assertEquals(Integer.valueOf(3), removed);
        Assert.assertTrue(deque.isEmpty());
    }
}
