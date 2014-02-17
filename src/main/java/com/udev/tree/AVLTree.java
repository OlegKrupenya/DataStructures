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
        T added = super.add(obj);
        balanceTree();
        return added;
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
        T deleted = super.delete(obj);
        balanceTree();
        return deleted;
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
    private void balance(Node<T> currentNode) {
        int balanceFactor = getBalanceFactor(currentNode);
        if (balanceFactor > 1) {
            Node<T> leftChild = currentNode.getLeftChild();
            if (getBalanceFactor(leftChild) == -1) { // Left right case
                // reduce to left left case
                rotateLeft(leftChild);
            }
            // left left case
            rotateRight(currentNode);
        } else if (balanceFactor < -1) {
            Node<T> rightChild = currentNode.getRightChild();
            if (balanceFactor == 1) { // right left case
                // reduce to right right case
                rotateRight(rightChild);
            }
            //Right Right Case
            rotateLeft(currentNode);
        }
    }

    private void balanceTree() {
        Node<T> tNode = this.getNode();
        Set<T> observedNodes = new HashSet<T>();
        while (tNode != this.getNode().getParent()) {
            if (!observedNodes.contains(tNode.getData())) {
                balance(tNode);
            }
            observedNodes.add(tNode.getData());
            if (tNode.getLeftChild() != null && !observedNodes.contains(tNode.getLeftChild().getData())) {
                tNode = tNode.getLeftChild();
            } else if (tNode.getRightChild() != null && !observedNodes.contains(tNode.getRightChild().getData())) {
                tNode = tNode.getRightChild();
            } else {
                tNode = tNode.getParent();
            }
        }
    }

    private void rotateLeft(Node<T> currentNode) {
        Node<T> rCh = currentNode.getRightChild();
        currentNode.getParent().setLeftChild(rCh);
        rCh.setParent(currentNode.getParent());
        currentNode.setParent(rCh);
        Node<T> lCh = rCh.getLeftChild();
        lCh.setParent(currentNode);
        currentNode.setRightChild(lCh);
        rCh.setLeftChild(currentNode);
    }

    private void rotateRight(Node<T> currentNode) {
        Node<T> lCh = currentNode.getLeftChild();
        currentNode.getParent().setRightChild(lCh);
        lCh.setLeftChild(currentNode.getParent());
        currentNode.setParent(lCh);
        Node<T> rCh = lCh.getRightChild();
        rCh.setParent(currentNode);
        currentNode.setLeftChild(rCh);
        lCh.setRightChild(currentNode);
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
