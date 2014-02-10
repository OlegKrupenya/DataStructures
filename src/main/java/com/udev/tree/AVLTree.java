package com.udev.tree;

/**
 * User: oleg.krupenya
 * Date: 2/10/14
 * Time: 7:06 PM
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    /**
     * Adds a new object into the tree.
     *
     * @param obj The new object to add.
     * @return The object resulting from the domainObject add, or <code>null</code> if the add was
     * not successful.
     * @throws IllegalArgumentException If the object is null.
     */
    @Override
    public T add(T obj) throws IllegalArgumentException {
        return super.add(obj);
    }

    /**
     * Deletes the object from the tree.
     *
     * @param obj The object to delete.
     * @return The object resulting from the domainObject delete, or <code>null</code> if the delete
     * was not successful
     * @throws IllegalArgumentException If the object is null.
     */
    @Override
    public T delete(T obj) throws IllegalArgumentException {
        return super.delete(obj);
    }

    /**
     * After inserting a node, it is necessary to check each of the node's ancestors for consistency with the rules
     * of AVL. The balance factor is calculated as follows:
     * balanceFactor = height(left subtree) - height(right subtree).
     * For each node checked, if the balance factor remains −1, 0, or +1 then no rotations are necessary.
     * However, if balance factor becomes less than -1 or greater than +1,
     * the subtree rooted at this node is unbalanced.
     *
     * @param obj
     */
    private void balance(T obj) {
        // TODO: implement
    }
}
