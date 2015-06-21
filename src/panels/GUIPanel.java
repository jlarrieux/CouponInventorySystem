package com.jeannius.cs401.project.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jeannius on 6/20/2015.
 */
public abstract class GUIPanel extends JPanel {

    JLabel couponProviderNameLabel, ProductNameLabel, priceLabel, discountRateLabel, expirationDateLabel;
    protected JPanel fieldPanel;


    protected void labelConstructor(){
        couponProviderNameLabel = new JLabel("Coupon Provider");
        couponProviderNameLabel.setHorizontalAlignment(JLabel.CENTER);
        ProductNameLabel = new JLabel("Product Name");
        ProductNameLabel.setHorizontalAlignment(JLabel.CENTER);
        discountRateLabel = new JLabel("Discount Rate");
        discountRateLabel.setHorizontalAlignment(JLabel.CENTER);
        expirationDateLabel= new JLabel("Expiration Date");
        expirationDateLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel = new JLabel("Price");
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    protected void init(){
        labelConstructor();
        fieldPanel = new JPanel(new GridLayout(2,5,25,10));
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 50));
        fieldPanel.add(couponProviderNameLabel);
        fieldPanel.add(ProductNameLabel);
        fieldPanel.add(priceLabel);
        fieldPanel.add(discountRateLabel);
        fieldPanel.add(expirationDateLabel);
    }



}
