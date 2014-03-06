package com.udev.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * User: oleg.krupenya
 * Date: 3/3/14
 * Time: 9:18 PM
 */
public class RedBlackTree<T extends Comparable<T>> implements IBinarySearchTree<T> {

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

    protected enum Color {
        BLACK, RED;
    }

    protected static class Node<T extends Comparable<T>> {
        private T data;
        private Color color;
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

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
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
            this.node.color = Color.BLACK;
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
                    nodeToDelete.parent.rightChild = rightMax;
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
    protected Node<T> getNodeByValue(T obj) {
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

    /**
     * Returns the grandparent of the current node
     *
     * @param currentNode The current node
     * @return The grandparent of the current node
     */
    private Node<T> getGrandparent(Node<T> currentNode) {
        if ((currentNode != null) && (currentNode.getParent() != null)) {
            return currentNode.getParent().getParent();
        } else {
            return null;
        }
    }

    /**
     * Returns the uncle of the current node
     *
     * @param currentNode The current node
     * @return The uncle of the current node
     */
    private Node<T> getUncle(Node<T> currentNode) {
        Node<T> grandparent = getGrandparent(currentNode);
        if (grandparent == null) {
            return null;
        }
        if (currentNode.getParent().equals(grandparent.getLeftChild())) {
            return grandparent.getRightChild();
        }
        return grandparent.getLeftChild();
    }

    private void insertCase1(Node<T> nodeToInsert) {
        if (nodeToInsert.getParent() == null) {
            nodeToInsert.setColor(Color.BLACK);
        } else {
            insertCase2(nodeToInsert);
        }
    }

    private void insertCase2(Node<T> nodeToInsert) {
        if (nodeToInsert == null) {
            throw new IllegalArgumentException("The object to add must be not null");
        }
        if (nodeToInsert.getParent() != null && Color.BLACK.equals(nodeToInsert.getParent().getColor())) {
            return;
        }
        insertCase3(nodeToInsert);
    }

    private void insertCase3(Node<T> nodeToInsert) {
        Node<T> grandparent = getGrandparent(nodeToInsert);
        Node<T> uncle = getUncle(nodeToInsert);
        if (uncle != null && Color.RED.equals(uncle.getColor())) {
            nodeToInsert.getParent().setColor(Color.BLACK);
            uncle.setColor(Color.BLACK);
            grandparent.setColor(Color.RED);
            insertCase1(grandparent);
        } else {
            insertCase4(nodeToInsert);
        }
    }

    private void insertCase4(Node<T> nodeToInsert) {
        Node<T> grandparent = getGrandparent(nodeToInsert);
        if (nodeToInsert.equals(nodeToInsert.getParent().getRightChild()) && nodeToInsert.getParent()
                .equals(grandparent.getLeftChild())) {
            rotateLeft(nodeToInsert.getParent(), false);
            nodeToInsert = nodeToInsert.getLeftChild();
        } else if (nodeToInsert.equals(nodeToInsert.getParent().getLeftChild()) && nodeToInsert.getParent()
                .equals(grandparent.getRightChild())) {
            rotateRight(nodeToInsert.getParent(), false);
            nodeToInsert = nodeToInsert.getRightChild();
        }
        insertCase5(nodeToInsert);
    }

    private void insertCase5(Node<T> nodeToInsert) {
        Node<T> grandparent = getGrandparent(nodeToInsert);
        nodeToInsert.getParent().setColor(Color.BLACK);
        grandparent.setColor(Color.RED);
        if (nodeToInsert.equals(nodeToInsert.parent.getLeftChild())) {
            rotateLeft(grandparent, false);
        } else {
            rotateRight(grandparent, false);
        }
    }
    
    private void rotateLeft(Node<T> currentNode, boolean changeRootOfSubtree) {
        if (currentNode.getParent() != null) {
            if (changeRootOfSubtree) {
                Node<T> oldRoot = currentNode;
                Node<T> newRoot = currentNode.getRightChild();
                newRoot.setParent(oldRoot.getParent());
                oldRoot.getParent().setRightChild(newRoot);
                Node<T> lCh = newRoot.getLeftChild();
                if (lCh != null) {
                    lCh.setParent(oldRoot);
                }
                oldRoot.setRightChild(lCh);
                oldRoot.setParent(newRoot);
                newRoot.setLeftChild(oldRoot);
            } else {
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

    private void rotateRight(Node<T> currentNode, boolean changeRootOfSubtree) {
        if (currentNode.getParent() != null) {
            if (changeRootOfSubtree) {
                Node<T> oldRoot = currentNode;
                Node<T> newRoot = currentNode.getLeftChild();
                newRoot.setParent(oldRoot.getParent());
                oldRoot.getParent().setLeftChild(newRoot);
                Node<T> rCh = newRoot.getRightChild();
                if (rCh != null) {
                    rCh.setParent(oldRoot);
                }
                oldRoot.setLeftChild(rCh);
                oldRoot.setParent(newRoot);
                newRoot.setRightChild(oldRoot);
            } else {
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
}
