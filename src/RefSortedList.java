package com.jeannius.cs401.project;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class RefSortedList<T extends Comparable<T>> extends RefUnsortedList<T>  implements  ListInterface<T>{

    public RefSortedList(){
        super();
    }


    @Override
    public void add(T element) {
        SListNode<T> previousLocation, location;
        T currentElement;

        location = firstNode;
        previousLocation = null;

        while (location!=null){
            currentElement = location.getElement();
            if(currentElement.compareTo(element)<0){
                previousLocation = location;
                location = location.getNext();
            }
            else break;
        }


        SListNode<T> newNode = new SListNode<T>(element, null);
        if(previousLocation==null){
            newNode.setNext(firstNode);
            firstNode = newNode;
        }
        else {
            newNode.setNext(location);
            previousLocation.setNext(newNode);
        }
        numberOfElements++;

    }



}
