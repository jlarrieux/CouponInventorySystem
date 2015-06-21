package com.jeannius.cs401.project;

import javax.swing.*;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class SearchGUI extends PurchaseGUI {




    public SearchGUI(int height, int width) {
        super(height, width);



    }


    @Override
    protected void buttonConstructor() {
        super.buttonConstructor();
        buy = new JButton("Search");
        buy.setPreferredSize(dimension);

    }
}
