package com.udev.tree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * User: oleg.krupenya
 * Date: 1/28/14
 * Time: 6:39 PM
 */
public class NAryTreeTest {
    private NAryTree<Integer> tree;

    @Before
    public void init() {
        this.tree = new NAryTree<Integer>();
    }

    @After
    public void after() {
        this.tree = null;
    }

    private void createTree() {
        this.tree.addChildToParent(null, 1);
        this.tree.addChildToParent(1, 2);
        this.tree.addChildToParent(1, 7);
        this.tree.addChildToParent(1, 8);
        this.tree.addChildToParent(2, 3);
        this.tree.addChildToParent(2, 6);
        this.tree.addChildToParent(3, 4);
        this.tree.addChildToParent(3, 5);
        this.tree.addChildToParent(8, 9);
        this.tree.addChildToParent(8, 12);
        this.tree.addChildToParent(9, 10);
        this.tree.addChildToParent(9, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_nullTree_error() {
        this.tree.addChildToParent(null, null);
    }

    @Test
    public void add_root_returnsRoot() {
        Integer added = this.tree.addChildToParent(null, 5);
        Assert.assertEquals(Integer.valueOf(5), added);
        Assert.assertEquals(Integer.valueOf(5), this.tree.root());
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(5)));
        Assert.assertEquals(1, this.tree.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_rootWhenExists_error() {
        Integer root = this.tree.addChildToParent(null, 5);
        Assert.assertEquals(Integer.valueOf(5), root);
        Assert.assertEquals(Integer.valueOf(5), this.tree.root());
        Integer newRoot = this.tree.addChildToParent(null, 7);
        Assert.assertEquals(Integer.valueOf(7), newRoot);
        Assert.assertEquals(Integer.valueOf(7), this.tree.root());
        Assert.assertFalse(this.tree.containsElement(Integer.valueOf(5)));
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(7)));
        Assert.assertEquals(1, this.tree.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_duplicates_error() {
        this.tree.addChildToParent(5, 5);
    }

    @Test
    public void add_unExistingParent_error() {
        Integer root = this.tree.addChildToParent(null, 5);
        Assert.assertEquals(Integer.valueOf(5), root);
        Assert.assertNull(this.tree.addChildToParent(1, 2));
    }

    @Test
    public void add_childrenToRoot_hp() {
        Integer root = this.tree.addChildToParent(null, 5);
        Integer left = this.tree.addChildToParent(5, 1);
        Integer middle = this.tree.addChildToParent(5, 3);
        Integer right = this.tree.addChildToParent(5, 2);
        /*List<INAryTree<Integer>> nodes = this.tree.getNodes();
        Assert.assertEquals(4, this.tree.size());
        Assert.assertEquals(3, nodes.size());
        Assert.assertEquals(Integer.valueOf(5), root);
        Assert.assertEquals(Integer.valueOf(1), left);
        Assert.assertEquals(Integer.valueOf(2), right);
        Assert.assertEquals(Integer.valueOf(3), middle);
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(5)));
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(1)));
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(2)));*/
    }

    @Test
    public void add_childrenToChildrenOfRoot_hp() {
        Integer root = this.tree.addChildToParent(null, 5);
        Integer left = this.tree.addChildToParent(5, 1);
        Integer middle = this.tree.addChildToParent(5, 3);
        Integer right = this.tree.addChildToParent(5, 2);
       /* List<INAryTree<Integer>> nodes = this.tree.getNodes();
        Assert.assertEquals(4, this.tree.size());
        Assert.assertEquals(3, nodes.size());
        Assert.assertEquals(Integer.valueOf(5), root);
        Assert.assertEquals(Integer.valueOf(1), left);
        Assert.assertEquals(Integer.valueOf(2), right);
        Assert.assertEquals(Integer.valueOf(3), middle);
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(5)));
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(1)));
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(2)));

        Integer lLeft = this.tree.addChildToParent(1, 10);
        Integer lMiddle = this.tree.addChildToParent(3, 30);
        Integer lRight = this.tree.addChildToParent(2, 20);
        nodes = this.tree.getNodes();
        Assert.assertEquals(7, this.tree.size());
        Assert.assertEquals(6, nodes.size());
        Assert.assertEquals(Integer.valueOf(10), lLeft);
        Assert.assertEquals(Integer.valueOf(30), lMiddle);
        Assert.assertEquals(Integer.valueOf(20), lRight);
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(10)));
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(20)));
        Assert.assertTrue(this.tree.containsElement(Integer.valueOf(30)));*/
    }

    @Test
    public void contains_treePopulated_hp() {
        createTree();
        Assert.assertTrue(this.tree.containsElement(1));
        Assert.assertTrue(this.tree.containsElement(2));
        Assert.assertTrue(this.tree.containsElement(3));
        Assert.assertTrue(this.tree.containsElement(4));
        Assert.assertTrue(this.tree.containsElement(5));
        Assert.assertTrue(this.tree.containsElement(6));
        Assert.assertTrue(this.tree.containsElement(7));
        Assert.assertTrue(this.tree.containsElement(8));
        Assert.assertTrue(this.tree.containsElement(9));
        Assert.assertTrue(this.tree.containsElement(10));
        Assert.assertTrue(this.tree.containsElement(11));
        Assert.assertTrue(this.tree.containsElement(12));
    }
}
