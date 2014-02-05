package com.udev.queue;

import com.udev.stack.ArrayStack;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: oleg.krupenya
 * Date: 10/21/13
 * Time: 5:29 PM
 */
public class ArrayQueueTest {
    @Test
    public void addTest_hp() {
        IQueue<Integer> queue = new ArrayQueue<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        Assert.assertTrue(queue.size() == 3);
        Assert.assertTrue(queue.contains(1));
        Assert.assertTrue(queue.contains(2));
        Assert.assertTrue(queue.contains(3));
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(Integer.valueOf(3), queue.tail());
        Assert.assertEquals(Integer.valueOf(1), queue.head());
    }

    @Test
    public void removeTest_hp() {
        IQueue<Integer> queue = new ArrayQueue<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        Assert.assertTrue(queue.size() == 3);
        Assert.assertTrue(queue.contains(1));
        Assert.assertTrue(queue.contains(2));
        Assert.assertTrue(queue.contains(3));
        Assert.assertEquals(Integer.valueOf(3), queue.tail());
        Assert.assertEquals(Integer.valueOf(1), queue.head());

        Integer removed = queue.remove();
        Assert.assertTrue(queue.size() == 2);
        Assert.assertTrue(queue.contains(3));
        Assert.assertTrue(queue.contains(2));
        Assert.assertFalse(queue.contains(1));
        Assert.assertEquals(Integer.valueOf(2), queue.head());
        Assert.assertEquals(Integer.valueOf(3), queue.tail());
        Assert.assertEquals(Integer.valueOf(1), removed);
        Assert.assertFalse(queue.isEmpty());

        removed = queue.remove();
        Assert.assertTrue(queue.size() == 1);
        Assert.assertTrue(queue.contains(3));
        Assert.assertFalse(queue.contains(2));
        Assert.assertFalse(queue.contains(1));
        Assert.assertEquals(Integer.valueOf(3), queue.head());
        Assert.assertEquals(Integer.valueOf(3), queue.tail());
        Assert.assertEquals(Integer.valueOf(2), removed);
        Assert.assertFalse(queue.isEmpty());

        removed = queue.remove();
        Assert.assertTrue(queue.size() == 0);
        Assert.assertFalse(queue.contains(1));
        Assert.assertFalse(queue.contains(2));
        Assert.assertFalse(queue.contains(3));
        Assert.assertNull(queue.head());
        Assert.assertNull(queue.tail());
        Assert.assertEquals(Integer.valueOf(3), removed);
        Assert.assertTrue(queue.isEmpty());
    }
}
