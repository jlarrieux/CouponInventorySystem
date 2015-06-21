package com.jeannius.cs401.project;

import com.jeannius.cs401.project.myInterfaces.List;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Jeannius on 6/21/2015.
 */
public class SortedList<T extends Comparable<T>> extends UnsortedList<T> implements List<T> {



   public SortedList(){
       super();
   }


    @Override
    public void insert(T element) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        SListNode<T> previousLocation, location;
        T currentElement;
//        System.out.println("addddinnngggg ttoooooo sooooorrrrteeedddd!!!!");

        location = head;
        previousLocation = null;


        while (location != null) {
            currentElement = location.getElement();
            int comp=0;
//            System.out.printf("\n\n\nElement Class: %s\n\n\n",element.getClass().toString());

            if(element instanceof Coupon){
                Method method = Coupon.class.getMethod(((Coupon) element).getMethodNameToIterate(),null);

                Object objNew = method.invoke(element);
                Object objCurrent = method.invoke(currentElement);

                if(objCurrent instanceof String) comp = ((String)objCurrent).compareTo((String)objNew);
                else if(objCurrent instanceof Integer) comp = Integer.compare((Integer)objCurrent,(Integer) objNew);
                System.out.printf("\n\nObjNew: %s\tObjCurrent: %s\tcomp: %d\n\n", String.valueOf(objNew), String.valueOf(objCurrent), comp);

            }
            else {
                comp = currentElement.compareTo(element);
                System.out.printf("\n\n\nNot coupon!\n\n\n");
            }

            if (comp < 0) {
                previousLocation = location;
                location = location.getNext();
            } else break;
        }


        SListNode<T> newNode = new SListNode<T>(element, null);
        if (previousLocation == null) {
            newNode.setNext(head);
            head = newNode;
            cursor = head;
        } else {
            newNode.setNext(location);
            previousLocation.setNext(newNode);
            cursor = head;
        }
        numberOfElements++;
        notifyListeners();
        if (head==null)System.out.println("First NODE NULL");
        if(cursor==null) System.out.println("Current node null!!!");
//        showStructure();

    }
}
