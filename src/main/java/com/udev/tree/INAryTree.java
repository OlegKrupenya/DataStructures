package com.udev.tree;

/**
 * User: oleg.krupenya
 * Date: 1/24/14
 * Time: 4:45 PM
 */
public interface INAryTree<T extends Comparable<T>> {
    boolean containsElement(T obj);

    T addChildToParent(T parent, T child);

    INAryTree addSubTree(INAryTree<T> tree);

    T delete(T obj);

    INAryTree deleteSubTree(INAryTree<T> tree);

    T replaceValue(T oldValue, T newValue);

    T root();

    int size();
}
