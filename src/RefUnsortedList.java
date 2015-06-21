package com.jeannius.cs401.project;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class RefUnsortedList<T> implements ListInterface<T> {

    protected  int numberOfElements;
    protected SListNode currentPosition, locationOfFound, previousLocation, firstNode;
    protected boolean found;


    public RefUnsortedList(){
        setUp(30);

    }

    public RefUnsortedList(int size){
        setUp(size);
    }

    private void setUp(int size){
        
        numberOfElements = 0;
        firstNode = null;
        currentPosition = null;
    }

    public boolean isEmpty(){
        if(firstNode==null) return true;
        else return false;
    }

    @Override
    public int size() {
        return numberOfElements;
    }





    @Override
    public void add(T element) {

        if(element !=null){
            if(firstNode==null){
                firstNode = new SListNode(element,null);
                currentPosition = firstNode;
            }
            else {
                SListNode newNode = new SListNode(element,currentPosition.getNext());
                currentPosition.setNext(newNode);
                currentPosition = newNode;
            }
            numberOfElements++;
        }
    }


    @Override
    public boolean contains(T element) {
//        todo add empty list case
        find(element);
        return found;
    }



    protected void find(T target){
        locationOfFound = firstNode;
        while(locationOfFound != null){
            if(locationOfFound.getElement().equals(target)){
                found = true;
                return;
            }
            else {
                previousLocation = locationOfFound;
                locationOfFound = locationOfFound.getNext();
            }
        }
    }


    @Override
    public boolean remove(T element) {
        if(!isEmpty()) {
            find(element);
            if (found) {
                if (firstNode == locationOfFound) firstNode = firstNode.getNext();
                else {
                    previousLocation.setNext(locationOfFound.getNext());
                    numberOfElements--;
                }
            }
            return found;
        }
        else{
            return  false;
//            TODO make this print an error message to the user!
        }

    }


    @Override
    public T get(T element) {
        find(element);
        if(found) return (T) locationOfFound.getElement();
        else  return null;
    }


    @Override
    public String toString() {
        SListNode currentNode = firstNode;
        String listString ="List:\n";
        while (currentNode !=null){
            listString = listString + "\t"+currentNode.getElement()+"\n";
            currentNode = currentNode.getNext();
        }

        return listString;
    }


    @Override
    public void reset() {
        currentPosition = firstNode;
    }


    @Override
    public T getNext() {
        if(isEmpty()) {
            // todo give user a warning!
            return null;
        }
        else {
            return (T) currentPosition.getNext();
        }


    }



















}
