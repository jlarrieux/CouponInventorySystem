package com.jeannius.cs401.project.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class PurchaseGUI extends GUIPanel {

    JTextField couponProviderNameTextField, productNameTextField, priceTextField, discountRateTextField, expirationDateTextfield;

    JButton buy, cancel;
    Dimension dimension;


    public PurchaseGUI(int height, int width){
        setPreferredSize(new Dimension(width,height));
        init();
    }


    @Override
    protected void init(){
        super.init();

        textFieldConstructor();
        buttonConstructor();

        fieldPanel.add(couponProviderNameTextField);
        fieldPanel.add(productNameTextField);
        fieldPanel.add(discountRateTextField);
        fieldPanel.add(expirationDateTextfield);
        fieldPanel.add(priceTextField);

        add(fieldPanel);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER,40,30));
        buttons.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        buttons.add(buy);
        buttons.add(cancel);

        add(buttons);

    }


    private void textFieldConstructor(){
        couponProviderNameTextField = new JTextField(20);
        couponProviderNameTextField.setDocument(new JTextFieldLimiter(20));

        productNameTextField = new JTextField();
        priceTextField = new JTextField();
        discountRateTextField = new JTextField();
        expirationDateTextfield =new JTextField();

    }

    protected void buttonConstructor(){
        dimension = new Dimension(80,50);
        buy = new JButton("Buy");
        buy.setPreferredSize(dimension);
        cancel = new JButton("Clear");
        cancel.setPreferredSize(dimension);
    }


}
