package com.udev.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * User: oleg.krupenya
 * Date: 2/5/14
 * Time: 7:58 PM
 */
public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T> {

    private Node<T> node;
    private int size;

    public Node<T> getNode() {
        return node;
    }

    public void setNode(Node<T> node) {
        this.node = node;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    protected static class Node<T extends Comparable<T>> {
        private T data;
        private Node<T> parent;
        private Node<T> leftChild;
        private Node<T> rightChild;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<T> leftChild) {
            this.leftChild = leftChild;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<T> rightChild) {
            this.rightChild = rightChild;
        }
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
        } else {
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
            newNode.parent = parent;
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
        if (obj == null) {
            throw new IllegalArgumentException("The object to delete must be not null");
        }
        Node<T> nodeToDelete = getNodeByValue(obj);
        if (nodeToDelete == null) {
            return null;
        }
        Node<T> leftChild = nodeToDelete.leftChild;
        Node<T> rightChild = nodeToDelete.rightChild;
        if (leftChild == null && rightChild == null) {
            if (nodeToDelete.parent != null && nodeToDelete.parent.rightChild != null
                    && nodeToDelete.parent.rightChild.equals(nodeToDelete)) {
                nodeToDelete.parent.rightChild = null;
            } else if (nodeToDelete.parent != null) {
                nodeToDelete.parent.leftChild = null;
            } else {
                node = null;
            }
            nodeToDelete = null;
        } else {
            if (leftChild == null && rightChild != null) {
                Node<T> leftMin = findMinInSubTree(rightChild);
                if (leftMin.equals(rightChild)) {
                    leftMin.parent = nodeToDelete.parent;
                    if (nodeToDelete.equals(leftMin.parent.rightChild)) {
                        leftMin.parent.rightChild = leftMin;
                    } else {
                        leftMin.parent.leftChild = leftMin;
                    }
                } else {
                    leftMin.parent.leftChild = null;
                    nodeToDelete.rightChild.parent = leftMin;
                    leftMin.rightChild = nodeToDelete.rightChild;
                    leftMin.parent = nodeToDelete.parent;
                }
                nodeToDelete = null;
            } else if (leftChild != null && rightChild == null) {
                Node<T> rightMax = findMaxInSubTree(leftChild);
                if (rightMax.equals(leftChild)) {
                    rightMax.parent = nodeToDelete.parent;
                    if (nodeToDelete.equals(rightMax.parent.rightChild)) {
                        rightMax.parent.rightChild = rightMax;
                    } else {
                        rightMax.parent.leftChild = rightMax;
                    }
                } else {
                    rightMax.parent.rightChild = null;
                    nodeToDelete.leftChild.parent = rightMax;
                    rightMax.leftChild = nodeToDelete.leftChild;
                    rightMax.parent = nodeToDelete.parent;
                    nodeToDelete.parent.rightChild  = rightMax;
                }
                nodeToDelete = null;
            } else {
                Node<T> leftMin = findMinInSubTree(rightChild);
                leftMin.parent.leftChild = leftMin.rightChild;
                nodeToDelete.data = leftMin.data;
                nodeToDelete.leftChild = leftMin.leftChild;
                nodeToDelete.rightChild = leftMin.rightChild;
                if (leftMin.equals(rightChild)) {
                    nodeToDelete.rightChild = rightChild.rightChild;
                } else {
                    nodeToDelete.rightChild = rightChild;
                }
                nodeToDelete.leftChild = leftChild;
                if (nodeToDelete.rightChild != null) {
                    nodeToDelete.rightChild.parent = nodeToDelete;
                }
                if (nodeToDelete.leftChild != null) {
                    nodeToDelete.leftChild.parent = nodeToDelete;
                }
            }
        }
        size--;
        return obj;
    }

    /**
     * @return The size of the tree.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * The process of visiting each node in a tree data structure.
     *
     * @return {@link java.util.List} of the data.
     */
    @Override
    public List<T> breadthFirstTraversal() {
        Queue<Node<T>> queue = new ArrayDeque<Node<T>>();
        List<T> nodes = new ArrayList<T>();
        if (node != null) {
            queue.add(node);
            while (!queue.isEmpty()) {
                Node<T> nodeFromQueue = queue.poll();
                nodes.add(nodeFromQueue.data);
                if (nodeFromQueue.getLeftChild() != null) {
                    queue.add(nodeFromQueue.getLeftChild());
                }
                if (nodeFromQueue.getRightChild() != null) {
                    queue.add(nodeFromQueue.getRightChild());
                }
            }
        }
        return nodes;
    }

    /**
     * Finds {@link com.udev.tree.BinarySearchTree.Node} by its data.
     *
     * @param obj The criteria of the search.
     * @return {@link com.udev.tree.BinarySearchTree.Node} if it exists in the tree.
     */
    private Node<T> getNodeByValue(T obj) {
        if (obj == null) {
            return null;
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

    private Node<T> findMinInSubTree(Node<T> currentNode) {
        while (currentNode.leftChild != null) {
            currentNode = currentNode.leftChild;
        }
        return currentNode;
    }

    private Node<T> findMaxInSubTree(Node<T> currentNode) {
        while (currentNode.rightChild != null) {
            currentNode = currentNode.rightChild;
        }
        return currentNode;
    }
}
