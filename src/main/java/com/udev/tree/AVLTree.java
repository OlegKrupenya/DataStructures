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
        if (size() > 2) {
            balanceTree(obj);
        }
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
        if (size() > 2) {
            balanceTree(obj);
        }
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
            if (getBalanceFactor(rightChild) == 1) { // right left case
                // reduce to right right case
                rotateRight(rightChild);
            }
            //Right Right Case
            rotateLeft(currentNode);
        }
    }

    private void balanceTree(T obj) {
        Node<T> tNode = getNodeByValue(obj);
        while (tNode != null) {
            balance(tNode);
            tNode = tNode.getParent();
        }
    }

    private void rotateLeft(Node<T> currentNode) {
        if (currentNode.getParent() != null) {
            // TODO: check balance factor
            Node<T> rCh = currentNode.getRightChild();
            if (currentNode.getParent() != null) {
                currentNode.getParent().setLeftChild(rCh);
            }
            if (rCh != null) {
                rCh.setParent(currentNode.getParent());
            }
            currentNode.setParent(rCh);
            Node<T> lCh = rCh.getLeftChild();
            if (lCh != null) {
                lCh.setParent(currentNode);
            }
            currentNode.setRightChild(lCh);
            if (rCh != null) {
                rCh.setLeftChild(currentNode);
            }
        } else {
            Node<T> oldRoot = this.getNode();
            this.setNode(currentNode.getRightChild());
            this.getNode().setParent(null);
            Node<T> lCh = getNode().getLeftChild();
            if (lCh != null) {
                lCh.setParent(oldRoot);
            }
            oldRoot.setRightChild(lCh);
            getNode().setLeftChild(oldRoot);
            oldRoot.setParent(getNode());
        }
    }

    private void rotateRight(Node<T> currentNode) {
        if (currentNode.getParent() != null) {
            // TODO: check balance factor
            Node<T> lCh = currentNode.getLeftChild();
            if (currentNode.getParent() != null) {
                currentNode.getParent().setRightChild(lCh);
            }
            if (lCh != null) {
                lCh.setParent(currentNode.getParent());
            }
            currentNode.setParent(lCh);
            Node<T> rCh = lCh.getRightChild();
            if (rCh != null) {
                rCh.setParent(currentNode);
            }
            currentNode.setLeftChild(rCh);
            if (lCh != null) {
                lCh.setRightChild(currentNode);
            }
        } else {
            Node<T> oldRoot = getNode();
            setNode(currentNode.getLeftChild());
            getNode().setParent(null);
            Node<T> rCh = getNode().getRightChild();
            if (rCh != null) {
                rCh.setParent(oldRoot);
            }
            oldRoot.setLeftChild(rCh);
            getNode().setRightChild(oldRoot);
            oldRoot.setParent(getNode());
        }
    }

    private int getBalanceFactor(Node<T> parent) {
        return (height(parent.getLeftChild()) + 1) - (height(parent.getRightChild()) + 1);
    }

    private int height(Node<T> currentNode) {
        if (currentNode == null) {
            return 0;
        }
        int maxWidth = 1;
        int width = 1;
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
