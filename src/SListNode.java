package com.jeannius.cs401.project;//--------------------------------------------------------------------
//
//  Laboratory 7                                       SListNode.jshl
//
//  Class definition for the singly linked list implementation of 
//  the List ADT
//
//  The student is to complete all missing or incomplete method 
//     implementations for each class (SList and SListNode)
//
//--------------------------------------------------------------------



class SListNode<T> {


    // Data members
    private T element ;         // List element
    private SListNode next;         // Reference to the next element


    // Constructor
    SListNode(T elem, SListNode nextPtr)
    // Creates a list node containing element elem and next pointer
    // nextPtr.
    {
        this.element = elem;
        setNext(nextPtr);


    }


    // Class Methods used by client class
    SListNode getNext()                    // Return reference to next element
    {
        return next;
    }


    SListNode setNext(SListNode nextVal)  // Set reference to next element
    {                                       //  & return that reference
        this.next = nextVal;
        return next;
    }


    T getElement()             // Return the element in the current node
    {
        return element;
    }


    void setElement(T newElem)         // Set current element to newElem
    {
        this.element = newElem;
    }

} // class SListNode
