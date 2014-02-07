package com.udev.tree;

/**
 * User: oleg.krupenya
 * Date: 2/5/14
 * Time: 7:58 PM
 */
public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T> {

    private Node<T> node;
    private int size;

    private static class Node<T extends Comparable<T>> {
        private T data;
        private Node<T> leftChild;
        private Node<T> rightChild;
    }

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
        if (obj == null) {
            throw new IllegalArgumentException("The object to add must be not null");
        }
        T data = null;
        if (this.node == null) {
            this.node = new Node<T>();
            data = obj;
            this.node.data = data;
        }
        else {
            Node currentNode = node;
            Node parent = node;
            while (currentNode != null) {
                if (currentNode.data.compareTo(obj) == 0) {
                    return obj;
                }
                if (currentNode.data.compareTo(obj) > 0) {
                    parent = currentNode;
                    currentNode = currentNode.leftChild;
                } else {
                    parent = currentNode;
                    currentNode = currentNode.rightChild;
                }
            }
            Node<T> newNode = new Node<T>();
            data = obj;
            newNode.data = data;
            if (parent.data.compareTo(obj) > 0) {
                parent.leftChild = newNode;
            } else {
                parent.rightChild = newNode;
            }
        }
        size++;
        return data;
    }

    /**
     * Finds the object in the tree and returns <code>true</code> if the tree contains it.
     *
     * @param obj The criteria of the search.
     * @return <code>true</code> if the tree contains the object.
     * @throws IllegalArgumentException If the object is null.
     */
    @Override
    public boolean contains(T obj) throws IllegalArgumentException {
        return getNodeByValue(obj) != null;
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
        // TODO: implement
        return null;
    }

    /**
     * @return The size of the tree.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Finds {@link com.udev.tree.BinarySearchTree.Node} by its data.
     * @param obj The criteria of the search.
     * @return {@link com.udev.tree.BinarySearchTree.Node} if it exists in the tree.
     */
    private Node<T> getNodeByValue(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("The object for the search must be not null");
        }
        Node<T> currentNode = node;
        while (currentNode != null) {
            if (currentNode.data.compareTo(obj) == 0) {
                return currentNode;
            }
            if (currentNode.data.compareTo(obj) > 0) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
        }
        return null;
    }
}
