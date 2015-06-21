package com.jeannius.cs401.project.myInterfaces;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jeannius on 6/20/2015.
 */
public interface ListInterface<T> {


    void add(T element) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    boolean contains(T element);

    T get(T element);

    T getCursor();

    T getNext();

    void goToNext();

    boolean remove(T element);

    void reset();

    int size();

    String toString();


}
