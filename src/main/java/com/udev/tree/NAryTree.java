package com.udev.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * User: oleg.krupenya
 * Date: 1/28/14
 * Time: 5:12 PM
 */
public class NAryTree<T extends Comparable<T>> implements INAryTree<T> {

    private Node<T> node = new Node<T>();

    private int size;

    public Node<T> getNode() {
        return node;
    }

    public void setNode(Node<T> node) {
        this.node = node;
    }

    private class Node<T extends Comparable<T>> {
        private T data;
        private List<Node<T>> nodes = new ArrayList<Node<T>>();

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public List<Node<T>> getNodes() {
            return nodes;
        }

        public void setNodes(List<Node<T>> nodes) {
            this.nodes = nodes;
        }
    }

    @Override
    public boolean containsElement(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Parameter shouldn't be null");
        }
        return getNode(obj) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T addChildToParent(T parent, T child) {
        if (child == null) {
            throw new IllegalArgumentException("Child shouldn't be null");
        }
        if (node.getData() != null && parent == null) {
            throw new IllegalArgumentException("Root already exists");
        }
        if (child.equals(parent)) {
            throw new IllegalArgumentException("Parent and child should be different");
        }
        T value = null;
        if (node.getData() == null) {
            node.setData(child);
            size++;
            return node.getData();
        }
        Node<T> parentNode = getNode(parent);
        if (parentNode != null) {
            Node<T> childNode = new Node<T>();
            childNode.setData(child);
            parentNode.getNodes().add(childNode);
            value = childNode.getData();
        }
        size++;
        return value;
    }

    @Override
    public INAryTree addSubTree(INAryTree<T> tree) {
        return null;
    }

    @Override
    public T delete(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Parameter shouldn't be null");
        }
        T value = null;
        Node<T> nodeToDelete = getNode(obj);
        if (nodeToDelete != null) {
            nodeToDelete.setData(null);
            nodeToDelete.getNodes().clear();
            // TODO: remove all children
        }
        return value;
    }

    @Override
    public INAryTree deleteSubTree(INAryTree<T> tree) {
        return null;
    }

    @Override
    public T root() {
        return node.getData();
    }

    @Override
    public T replaceValue(T oldValue, T newValue) {
        if (oldValue == null || newValue == null) {
            throw new IllegalArgumentException("Parameters shouldn't be null");
        }
        T value = null;
        Node<T> node = getNode(oldValue);
        if (node != null) {
            node.setData(newValue);
            value = newValue;
        }
        return value;
    }

    private Node<T> getNode(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Parameter should not be null");
        }
        if (obj.equals(this.node.data)) {
            return this.node;
        }
        Stack<Node<T>> stack = new Stack<Node<T>>();
        stack.add(this.node);
        while (!stack.isEmpty()) {
            Node<T> nodeFromStack = stack.pop();
            System.out.println(nodeFromStack.getData());
            if (nodeFromStack.getData().equals(obj)) {
                return nodeFromStack;
            }
            for (int i = nodeFromStack.getNodes().size() - 1; i >= 0; i--) {
                stack.push(nodeFromStack.getNodes().get(i));
            }
        }
        return null;
    }
}
