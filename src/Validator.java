package com.jeannius.cs401.project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class Validator<T> {
    JTextField currentTextField;
    Object currentType;
    String error;

    public Validator(JTextField jTextField, Object cl){
        this.currentTextField = jTextField;
        this.currentType = cl;
    }


    public boolean validate(){
        currentTextField.setBorder(UIManager.getBorder("TextField.border"));
        System.out.println(currentType);
        if(currentTextField.getText().length()==0){
            error("No text");
            return true;
        }


        else return false;


    }

    private boolean lengthValidation(){
        boolean test =  currentTextField.getText().length()==0;
        if(test)error("No text");
        return  test;
    }

    private boolean isNumericValidation(){
        boolean test = false;
        if(currentType instanceof  Double || currentType instanceof  Integer)test= !currentTextField.getText().matches("-?\\d+(\\.\\d+)?");
        if(test) error("Not a number or digit!");
        return test;
    }


    private void error(String text){
        error = text;
        Border border = BorderFactory.createLineBorder(Color.red,2);
        currentTextField.setBorder(border);
        System.out.println("validating");

    }







}
