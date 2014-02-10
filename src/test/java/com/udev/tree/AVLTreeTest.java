package com.udev.tree;

/**
 * User: oleg.krupenya
 * Date: 2/10/14
 * Time: 7:24 PM
 */
public class AVLTreeTest extends BinarySearchTreeTest {
    @Override
    public void init() {
        this.tree = new AVLTree<Integer>();
    }
}
