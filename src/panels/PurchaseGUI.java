package com.jeannius.cs401.project.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class PurchaseGUI extends GUIPanel implements ActionListener{

    JTextField couponProviderNameTextField, productNameTextField, priceTextField, discountRateTextField, expirationDateTextfield;
    public static final String CLEAR="Clear", BUY ="Buy", SEARCH="Search";
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
        buy = new JButton(BUY);
        buy.setPreferredSize(dimension);
        cancel = new JButton(CLEAR);
        cancel.setPreferredSize(dimension);
        cancel.addActionListener(this);
    }
    private void clearAll(){
        Component[] component = fieldPanel.getComponents();
//        System.out.println(component.length);
        for(int i=0; i<fieldPanel.getComponentCount(); i++){

//            System.out.println((component[i].getClass().toString()));
            if(component[i] instanceof JTextField){
//                System.out.println(((JTextField)component[i]).getText());
                        ((JTextField) component[i]).setText("");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if(source instanceof JButton){
            if(((JButton) source).getText().equals(CLEAR)){
//                System.out.println("CLEARING");
                clearAll();
            }
        }

    }
}
