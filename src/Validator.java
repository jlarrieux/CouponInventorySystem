package com.jeannius.cs401.project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class Validator<T> {
    JTextField currentTextField;
    Class currentType;

    public Validator(JTextField jTextField, Class cl){
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


    private void error(String text){
        Border border = BorderFactory.createLineBorder(Color.red,2);
        System.out.println("validating");
        currentTextField.setBorder(border);
    }





}
