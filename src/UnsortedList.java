package com.jeannius.cs401.project;//--------------------------------------------------------------------
//
//  Laboratory 7                                         SList.jshl
//
//  Class definitions for the singly linked list implementation of 
//  the List ADT
//
//  The student is to complete all missing or incomplete method 
//     implementations for each class (Slist and SListNode)
//
//--------------------------------------------------------------------



import com.jeannius.cs401.project.myInterfaces.List;
import com.jeannius.cs401.project.myInterfaces.ListUpdateListener;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

class UnsortedList<T> implements List<T> {


    // Data members
    protected SListNode head,     // Reference to the beginning of the list
            cursor, // Reference to current cursor position
            locationOfFound, //location of where the search found the item
            previousLocation; // node before the cursor
    protected int numberOfElements=0;
    protected boolean found;
    protected java.util.List<ListUpdateListener> listeners = new ArrayList<>();





    // Constructors & Helper Method
    UnsortedList()                  // Default constructor: Creates an empty list
    {
        setup();
    }

     UnsortedList(int size)
    // Creates an empty list. The argument is included for compatibility
    // with the array implementation; size is ignored.
    {
        setup();
    }


    // Class Methods
    private void setup()   // Called by constructors only: Creates an empty list
    {
        head = null;
        cursor = null;
    }


    void moveToBeginning()                    // Move to beginning
    {
        if (isEmpty()) System.out.println("Attempting to move elements in an empty list!");
        else if (cursor != head) {
            SListNode current, next = null, prior = null;

            current = cursor;
            if (cursor.getNext() != null) next = cursor.getNext();
            else next = head;
            if (gotoPrior()) prior = cursor;
            else System.out.println("\n\nCould not complete move because GoToPrior Operation failed!\n\n");

            current.setNext(head);

            if (prior != null) prior.setNext(next);


            if (next == head) prior.setNext(null);
            head = current;
            cursor = head;
            System.out.printf("\nNext = : %s\tcurrent: %s\t prior: %s\n", String.valueOf(next.getElement()), String.valueOf(current.getElement()), String.valueOf(prior.getElement()));


        }
    }




    // ----- Insert method definitions for the interface List here ------ //
    @Override
    public void insert(T newElement) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (newElement == null) System.out.printf("Attempting to insert a null element!\n");
        else {
            if (head == null) {

                head = new SListNode(newElement, null);
                cursor = head;
                System.out.printf("Cursor: %s\t Head: %s\n", ((Coupon)cursor.getElement()).getCouponProviderName(), ((Coupon)head.getElement()).getCouponProviderName());

            } else {
                SListNode toInsert = new SListNode(newElement, cursor.getNext());
                cursor.setNext(toInsert);
                cursor = toInsert;
                System.out.printf("NOT NULL!!! Cursor: %s\t Head: %s\n", ((Coupon)cursor.getElement()).getCouponProviderName(), ((Coupon)head.getElement()).getCouponProviderName());
            }
            numberOfElements++;
            notifyListeners();
        }
    }


    @Override
    public void remove() {
        if (isEmpty()) System.out.println("Attempting to remove elements from an empty list");
        else if (cursor == head) {
            head = cursor.getNext();
            gotoNext();
        } else {
            if (cursor.getNext() != null) {
                if (gotoPrior()) {
                    cursor.setNext(cursor.getNext().getNext());
                    gotoNext();
                } else System.out.printf("Remove operation failed!\n");
            } else {
                if (gotoPrior()) {
                    cursor.setNext(null);
                    cursor = head;

                } else System.out.printf("Remove operation failed!\n");
            }
        }
    }


    @Override
    public void replace(Object newElement) {
        if (isEmpty()) System.out.println("Attempting to replace element from an empty list!");
        else if (cursor == head) {
            head.setElement(newElement);
        } else {
            SListNode sl = new SListNode(newElement, cursor.getNext());
            if (gotoPrior()) {
                cursor.setNext(sl);
                cursor = sl;
            } else System.out.printf("Replace operation failed!\n");
        }

    }


    @Override
    public void clear() {

//        head.setNext(null);
//        cursor.setNext(null);
        head = null;
        cursor = null;
        showStructure();
    }


    @Override
    public boolean isEmpty() {
        if (head == null) return true;
        else return false;
    }


    @Override
    public boolean isFull() {
        return false;
    }


    @Override
    public boolean gotoBeginning() {
        if (isEmpty()) return false;
        else {
            cursor = head;
            return true;
        }
    }


    @Override
    public boolean gotoEnd() {
        SListNode sl = cursor;
        if (isEmpty()) return false;
        else {
            while (sl.getNext() != null) {
                sl = sl.getNext();
            }
            cursor = sl;
            return true;
        }
    }


    @Override
    public boolean gotoNext() {
        if (cursor.getNext() == null) return false;
        else {
            cursor = cursor.getNext();
            return true;
        }
    }


    @Override
    public boolean gotoPrior() {
        if (cursor == head) return false;
        else {
            SListNode sl = head;
            while ((sl.getNext() != cursor)) {
                sl = sl.getNext();
            }
            cursor = sl;
            return true;
        }
    }

    public void notifyListeners(){
//        System.out.println("REF UNSORT NOTIFY OTHERS");
        for(ListUpdateListener listUpdateListener : listeners) listUpdateListener.listHasBeenUpdated(this);
//        showStructure();
    }

    public boolean contains(T element) {
//        todo add empty list case
        find(element);
        return found;
    }


    protected void find(T target) {
        locationOfFound = head;
        while (locationOfFound != null) {
            if (locationOfFound.getElement().equals(target)) {
                found = true;
                return;
            } else {
                previousLocation = locationOfFound;
                locationOfFound = locationOfFound.getNext();
            }
        }
    }


    public void addListener(ListUpdateListener toAdd){
        listeners.add(toAdd);
    }

    //--------------------------------------------------------------------
    //
    //                        In-lab operations
    //
    //--------------------------------------------------------------------


    @Override
    public T getCursor() {

        if(cursor==null) return null;
        else return (T) cursor.getElement();
    }


    @Override
    public void showStructure() {
        int j = 0;
        String toShow ="%s";
        if (head == null) System.out.println("Empty list!");

        else {
            System.out.println("SHOWING STRUCTURE");
            System.out.printf("HEAD\t");

            SListNode sl = new SListNode(head.getElement(),head.getNext());
            while(sl!=null) {
                if(sl ==cursor) toShow ="["+toShow+"]";
                if(sl.getElement() instanceof Coupon) System.out.printf(toShow+"\t", ((Coupon)sl.getElement()).getCouponProviderName());
                else System.out.printf("%s\t", String.valueOf(sl.getElement()));
                j++;
                sl = sl.getNext();
                if (j > 10) break;
            }
            System.out.println("\tEND");

            if(head.getElement() instanceof Coupon) System.out.printf("Cursor: %s\t Head: %s\n", ((Coupon)cursor.getElement()).getCouponProviderName(), ((Coupon)head.getElement()).getCouponProviderName());
            else  System.out.printf("\tEND\nCurrent cursor value: %s\n", String.valueOf(cursor.getElement()));
//            System.out.printf("Cursor: %s\t Head: %s\n", ((Coupon)cursor.getElement()).getCouponProviderName(), ((Coupon)head.getElement()).getCouponProviderName());


        }

    }


    @Override
    public int size() {
        return numberOfElements;
    }





} // class SList