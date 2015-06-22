package com.jeannius.cs401.project;

import javax.swing.*;
import java.awt.event.ActionEvent;

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
        buy.addActionListener(this);

    }






    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(((JButton) source).getText().equals(CLEAR)) clearAll();
        else{
            Coupon toSearch = buyCoupon();

            boolean contains = GUIPanel.masterList2.contains(toSearch);
            System.out.println("Search result: " + contains);
          if( contains && GUIPanel.masterList2.indexOfFound>=0){
              MyLauncher.Showdialog(String.format("Coupon found in %dth data comparison\n\nCopon: {Provider: %s,\nProduct Name: %s, \nPrice: %.2f,\nDiscount Rate: %.2f,\nExpiratrion Date: %d, \nStatus: %s}",
                      GUIPanel.masterList2.indexOfFound+1, toSearch.getCouponProviderName(), toSearch.getProductName(),toSearch.getPrice(),toSearch.getDiscountRate(), toSearch.getExpirationDate(), toSearch.getStatus().toString()));
          }
            else MyLauncher.Showdialog("Coupon Not found!");
        }
    }
}
