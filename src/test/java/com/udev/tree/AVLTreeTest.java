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
    public void leftLeftCaseTest() {
        this.tree.add(50);
        this.tree.add(30);
        this.tree.add(40);
        List<Integer> expected = Arrays.asList(50,30,40);
        List<Integer> list = this.tree.breadthFirstTraversal();
        Assert.assertEquals(expected, list);
    }
}
