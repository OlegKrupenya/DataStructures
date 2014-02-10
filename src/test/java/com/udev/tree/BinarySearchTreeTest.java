package com.udev.tree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * User: oleg.krupenya
 * Date: 2/6/14
 * Time: 9:32 PM
 */
public class BinarySearchTreeTest {
    protected IBinarySearchTree<Integer> tree;

    @Before
    public void init() {
        this.tree = new BinarySearchTree<Integer>();
    }

    @After
    public void after() {
        this.tree = null;
    }

    @Test
    public void addTest_hp() {
        Integer root = this.tree.add(8);
        Integer left = this.tree.add(3);
        Integer right = this.tree.add(10);
        Integer leftRight = this.tree.add(6);
        Integer leftLeft = this.tree.add(1);
        Integer leftRightLeft = this.tree.add(5);
        Integer leftRightRight = this.tree.add(7);
        Integer rightRight = this.tree.add(14);
        Integer rightRightLeft = this.tree.add(13);

        Assert.assertEquals(9, this.tree.size());
        Assert.assertTrue(this.tree.contains(root));
        Assert.assertTrue(this.tree.contains(left));
        Assert.assertTrue(this.tree.contains(right));
        Assert.assertTrue(this.tree.contains(leftRight));
        Assert.assertTrue(this.tree.contains(leftLeft));
        Assert.assertTrue(this.tree.contains(leftRightLeft));
        Assert.assertTrue(this.tree.contains(leftRightRight));
        Assert.assertTrue(this.tree.contains(rightRight));
        Assert.assertTrue(this.tree.contains(rightRightLeft));
    }

    @Test
    public void deleteTest_hp() {
        this.tree.add(8);
        this.tree.add(3);
        this.tree.add(10);
        this.tree.add(6);
        this.tree.add(1);
        this.tree.add(5);
        this.tree.add(7);
        this.tree.add(14);
        this.tree.add(13);

        Integer root = this.tree.delete(8);
        Integer left = this.tree.delete(3);
        Integer right = this.tree.delete(10);
        Integer leftRight = this.tree.delete(6);
        Integer leftLeft = this.tree.delete(1);
        Integer leftRightLeft = this.tree.delete(5);
        Integer leftRightRight = this.tree.delete(7);
        Integer rightRight = this.tree.delete(14);
        Integer rightRightLeft = this.tree.delete(13);

        Assert.assertEquals(0, this.tree.size());
        Assert.assertFalse(this.tree.contains(root));
        Assert.assertFalse(this.tree.contains(left));
        Assert.assertFalse(this.tree.contains(right));
        Assert.assertFalse(this.tree.contains(leftRight));
        Assert.assertFalse(this.tree.contains(leftLeft));
        Assert.assertFalse(this.tree.contains(leftRightLeft));
        Assert.assertFalse(this.tree.contains(leftRightRight));
        Assert.assertFalse(this.tree.contains(rightRight));
        Assert.assertFalse(this.tree.contains(rightRightLeft));
    }
}
