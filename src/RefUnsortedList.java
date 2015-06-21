package com.jeannius.cs401.project;

import com.jeannius.cs401.project.myInterfaces.ListInterface;
import com.jeannius.cs401.project.myInterfaces.ListUpdateListener;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class RefUnsortedList<T> implements ListInterface<T> {


    protected int numberOfElements;
    protected SListNode currentPosition, locationOfFound, previousLocation, firstNode;
    protected boolean found;
    protected List<ListUpdateListener> listeners = new ArrayList<>();


    public RefUnsortedList() {
        setUp(30);

    }


    private void setUp(int size) {

        numberOfElements = 0;
        firstNode = null;
        currentPosition = null;
    }


    public RefUnsortedList(int size) {
        setUp(size);
    }


    @Override
    public void add(T element) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("REF UNSORTED ADDING");
        if (element != null) {
            if (firstNode == null) {
                firstNode = new SListNode(element, null);
                currentPosition = firstNode;

            } else {
//                if(currentPosition==null) currentPosition = firstNode;
                System.out.println(firstNode.getElement());
                System.out.println(currentPosition.getElement());
                SListNode newNode = new SListNode(element, currentPosition.getNext());
                currentPosition.setNext(newNode);
                currentPosition = newNode;
            }
            numberOfElements++;
            if(element instanceof Coupon) System.out.println("CurrentPosition: "+((Coupon)currentPosition.getElement()).getCouponProviderName());
            notifyListeners();

        }
    }

//    private void goToLast(){
//        currentPosition = firstNode;
//
//        while(currentPosition.getNext()!=null){
//            currentPosition = currentPosition.getNext();
//        }
//        System.out.println("GOTOLAST \tCurrentPosition: "+((Coupon)currentPosition.getElement()).getCouponProviderName());
//    }


    @Override
    public boolean contains(T element) {
//        todo add empty list case
        find(element);
        return found;
    }


    protected void find(T target) {
        locationOfFound = firstNode;
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


    @Override
    public T get(T element) {
        find(element);
        if (found) return (T) locationOfFound.getElement();
        else return null;
    }

    @Override
    public T getCursor(){
        if(!isEmpty()) {
//            if(currentPosition== null) goToLast();
            return (T) currentPosition.getElement();
        }
        else return null;
    }


    @Override
    public T getNext() {
        if (isEmpty()) {
            // todo give user a warning!
            return null;
        }

        else {
//            if(currentPosition==null) goToLast();
            return (T) currentPosition.getNext();
        }


    }

    @Override
    public void goToNext(){
        currentPosition = currentPosition.getNext();
    }


    @Override
    public boolean remove(T element) {
        if (!isEmpty()) {
            find(element);
            if (found) {
                if (firstNode == locationOfFound) firstNode = firstNode.getNext();
                else {
                    previousLocation.setNext(locationOfFound.getNext());
                    numberOfElements--;
                }
                notifyListeners();
            }
            return found;
        } else {
            return false;
//            TODO make this print an error message to the user!
        }

    }


    public boolean isEmpty() {
        if (firstNode == null) return true;
        else return false;
    }


    @Override
    public void reset() {

//        currentPosition = firstNode;
//        System.out.println("resetting");
//        if(currentPosition==null) System.out.printf("\n\n\nCurrent position ==nulll!!!!!\n\n\n");
//        showStructure();
//        System.out.println(currentPosition.getElement());

    }


    @Override
    public int size() {
        return numberOfElements;
    }


    @Override
    public String toString() {
        SListNode currentNode = firstNode;
        String listString = "List:\n";
        while (currentNode != null) {
            listString = listString + "\t" + currentNode.getElement() + "\n";
            currentNode = currentNode.getNext();
        }

        return listString;
    }


    public void addListener(ListUpdateListener toAdd){
        listeners.add(toAdd);
    }

    public void notifyListeners(){
//        System.out.println("REF UNSORT NOTIFY OTHERS");
//        for(ListUpdateListener listUpdateListener : listeners) listUpdateListener.listHasBeenUpdated(this);
//        showStructure();
    }

    public void clear(){
        firstNode = null;
    }



    public void showStructure() {
        int j = 0;
        if (firstNode == null) System.out.println("Empty list!");

        else {
            System.out.println("SHOWING STRUCTURE");
            System.out.printf("HEAD\t\t");
            SListNode sl = new SListNode(firstNode.getElement(), firstNode.getNext());
            do {
                if(sl.getElement() instanceof  Coupon) {
                    String form ="{%s, %s,%.2f, %.2f, %d}\t";
                    Coupon coupon = (Coupon) sl.getElement();
                    if(sl == currentPosition) form ="["+form+"]";
                    System.out.printf(form, coupon.getCouponProviderName(), coupon.getProductName(), coupon.getPrice(), coupon.getDiscountRate(), coupon.getExpirationDate());
                }
                else{
                    if(sl == currentPosition) System.out.printf("[%s]\t", String.valueOf(sl.getElement()));
                    else System.out.printf("%s\t", String.valueOf(sl.getElement()));
                }
                j++;
                sl = sl.getNext();
                if (j > 10) break;
            } while (sl != null);
//            if(currentPosition==null) goToLast();
            System.out.printf("\t\tEND\t with currentPosition: %s", ((Coupon)currentPosition.getElement()).getCouponProviderName());


        }
    }
}
