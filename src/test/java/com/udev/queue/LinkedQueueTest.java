package com.udev.queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: oleg.krupenya
 * Date: 10/22/13
 * Time: 10:53 PM
 */
public class LinkedQueueTest {
    @Test
    public void addTest_hp() {
        IQueue<Integer> queue = new LinkedQueue<Integer>();
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
}
