package com.udev.tree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * User: oleg.krupenya
 * Date: 2/10/14
 * Time: 7:24 PM
 */
public class AVLTreeTest {

    private AVLTree<Integer> tree;

    @Before
    public void init() {
        this.tree = new AVLTree<Integer>();
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

        //Assert.assertEquals(9, this.tree.size());
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
    public void leftLeftCaseTest() {
        this.tree.add(50);
        this.tree.add(30);
        this.tree.add(40);
        List<Integer> expected = Arrays.asList(40,30,50);
        List<Integer> list = this.tree.breadthFirstTraversal();
        Assert.assertEquals(expected, list);
    }

    @Test
    public void rightRightCaseTest() {
        this.tree.add(30);
        this.tree.add(50);
        this.tree.add(40);
        List<Integer> expected = Arrays.asList(40,30,50);
        List<Integer> list = this.tree.breadthFirstTraversal();
        Assert.assertEquals(expected, list);
    }
}
