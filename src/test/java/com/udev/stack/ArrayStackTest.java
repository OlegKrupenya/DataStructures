package com.udev.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: oleg.krupenya
 * Date: 10/16/13
 * Time: 8:24 PM
 */
public class ArrayStackTest {

    @Test
    public void addTest_hp() {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        Assert.assertTrue(stack.size() == 3);
        Assert.assertTrue(stack.contains(1));
        Assert.assertTrue(stack.contains(2));
        Assert.assertTrue(stack.contains(3));
        Assert.assertFalse(stack.isEmpty());
        Assert.assertEquals(Integer.valueOf(3), stack.head());
    }

    @Test
    public void removeTest_hp() {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        Assert.assertTrue(stack.size() == 3);
        Assert.assertTrue(stack.contains(1));
        Assert.assertTrue(stack.contains(2));
        Assert.assertTrue(stack.contains(3));
        Assert.assertEquals(Integer.valueOf(3), stack.head());

        Integer removed = stack.remove();
        Assert.assertTrue(stack.size() == 2);
        Assert.assertTrue(stack.contains(1));
        Assert.assertTrue(stack.contains(2));
        Assert.assertFalse(stack.contains(3));
        Assert.assertEquals(Integer.valueOf(2), stack.head());
        Assert.assertEquals(Integer.valueOf(3), removed);
        Assert.assertFalse(stack.isEmpty());

        removed = stack.remove();
        Assert.assertTrue(stack.size() == 1);
        Assert.assertTrue(stack.contains(1));
        Assert.assertFalse(stack.contains(2));
        Assert.assertFalse(stack.contains(3));
        Assert.assertEquals(Integer.valueOf(1), stack.head());
        Assert.assertEquals(Integer.valueOf(2), removed);
        Assert.assertFalse(stack.isEmpty());

        removed = stack.remove();
        Assert.assertTrue(stack.size() == 0);
        Assert.assertFalse(stack.contains(1));
        Assert.assertFalse(stack.contains(2));
        Assert.assertFalse(stack.contains(3));
        Assert.assertNull(stack.head());
        Assert.assertEquals(Integer.valueOf(1), removed);
        Assert.assertTrue(stack.isEmpty());
    }
}
