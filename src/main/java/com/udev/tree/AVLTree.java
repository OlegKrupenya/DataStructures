package com.udev.tree;

import java.util.*;

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
     * @param currentNode
     */
    public void balance(Node<T> currentNode) {
        int balanceFactor = getBalanceFactor(currentNode);
        if (balanceFactor > 1) {
            Node<T> leftChild = currentNode.getLeftChild();
            if (getBalanceFactor(leftChild) == -1) { // Left right case
                // reduce to left left case
                // TODO: rotateLeft(currentNode);
            }
            // left left case
            // TODO: rotateRight(leftChild);
        } else if (balanceFactor < -1) {
            Node<T> rightChild = currentNode.getRightChild();
            if (balanceFactor == 1) { // right left case
                // reduce to right right case
                // TODO: rotateRight(currentNode);
            }
            //Right Right Case
            // TODO: rotateLeft(L);
        }
    }

    private int getBalanceFactor(Node<T> parent) {
        return (width(parent.getLeftChild()) + 1) - (width(parent.getRightChild()) + 1);
    }

    private int width(Node<T> currentNode) {
        int maxWidth = 0;
        int width = 0;
        if (currentNode == null) {
            return maxWidth;
        }
        Node<T> tNode = currentNode;
        Set<T> observedNodes = new HashSet<T>();
        while (tNode != currentNode.getParent()) {
            observedNodes.add(tNode.getData());
            if (tNode.getLeftChild() != null && !observedNodes.contains(tNode.getLeftChild().getData())) {
                width++;
                if (width > maxWidth) {
                    maxWidth = width;
                }
                tNode = tNode.getLeftChild();
            } else if (tNode.getRightChild() != null && !observedNodes.contains(tNode.getRightChild().getData())) {
                width++;
                if (width > maxWidth) {
                    maxWidth = width;
                }
                tNode = tNode.getRightChild();
            } else {
                width--;
                tNode = tNode.getParent();
            }
        }
        return maxWidth;
    }
}
