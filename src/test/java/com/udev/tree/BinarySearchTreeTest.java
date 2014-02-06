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
    private IBinarySearchTree<Integer> tree;

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
}
