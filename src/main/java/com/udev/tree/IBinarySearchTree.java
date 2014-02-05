package com.udev.tree;

/**
 * User: oleg.krupenya
 * Date: 2/5/14
 * Time: 7:18 PM
 */
public interface IBinarySearchTree<T extends Comparable<T>> {
    /**
     * Adds a new object into the tree.
     * @param obj The new object to add.
     * @return The object resulting from the domainObject add, or <code>null</code> if the add was
     * not successful.
     * @throws IllegalArgumentException If the object is null.
     */
    T add(T obj) throws IllegalArgumentException;

    /**
     * Finds the object in the tree and returns <code>true</code> if the tree contains it.
     * @param obj The criteria of the search.
     * @return <code>true</code> if the tree contains the object.
     * @throws IllegalArgumentException If the object is null.
     */
    boolean contains(T obj) throws IllegalArgumentException;

    /**
     * Deletes the object from the tree.
     * @param obj The object to delete.
     * @return The object resulting from the domainObject delete, or <code>null</code> if the delete
     *         was not successful
     * @throws IllegalArgumentException If the object is null.
     */
    T delete(T obj) throws IllegalArgumentException;

    /**
     * @return The size of the tree.
     */
    int size();
}
