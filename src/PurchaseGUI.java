package com.jeannius.cs401.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class PurchaseGUI<T> extends GUIPanel<T> implements ActionListener {


    public static final String CLEAR = "Clear", BUY = "Buy", SEARCH = "Search";
    JTextField couponProviderNameTextField, productNameTextField, priceTextField, discountRateTextField, expirationDateTextfield;
    JButton buy, cancel;
    Dimension dimension;




    public PurchaseGUI(int height, int width) {
        setPreferredSize(new Dimension(width, height));
        init();

    }


    @Override
    protected void init() {
        super.init();

        textFieldConstructor();
        buttonConstructor();

        fieldPanel.add(couponProviderNameTextField);
        fieldPanel.add(productNameTextField);
        fieldPanel.add(priceTextField);
        fieldPanel.add(discountRateTextField);
        fieldPanel.add(expirationDateTextfield);


        add(fieldPanel);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 30));
        buttons.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        buttons.add(buy);
        buttons.add(cancel);

        add(buttons);


    }


    protected void textFieldConstructor() {
        couponProviderNameTextField = new JTextField(20);
        couponProviderNameTextField.setDocument(new JTextFieldLimiter(20));
        couponProviderNameTextField.setName("Coupon Provider");

        productNameTextField = new JTextField(2);
        productNameTextField.setDocument(new JTextFieldLimiter(20));
        productNameTextField.setName("Product Name");

        priceTextField = new JTextField();
        priceTextField.setName("PRICE");
        discountRateTextField = new JTextField();
        discountRateTextField.setName("Discount");
        expirationDateTextfield = new JTextField();
        expirationDateTextfield.setName("Expiration");


    }


    protected void buttonConstructor() {
        dimension = new Dimension(80, 50);
        buy = new JButton(BUY);
        buy.setPreferredSize(dimension);
        buy.addActionListener(this);

        cancel = new JButton(CLEAR);
        cancel.setPreferredSize(dimension);
        cancel.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        Object source = e.getSource();
        if (source instanceof JButton) {
            if (((JButton) source).getText().equals(CLEAR)) clearAll();
            else{



                if(!checkInputError()) {
                    try {
                        GUIPanel.masterList2.insert(buyCoupon());
                    } catch (NoSuchMethodException e1) {
                        e1.printStackTrace();
                    } catch (InvocationTargetException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                    clearAll();
                }
                else MyLauncher.Showdialog(errorlist);
//                validateMyTextField



            }
        }

    }

    protected boolean checkInputError(){
        errorlist="";
        boolean productGood, providerGood, discountGood, priceGood, expirationDateGood;
        providerGood= validateMyTextField(couponProviderNameTextField,null );
        productGood=  validateMyTextField(productNameTextField, null);
        discountGood = validateMyTextField(discountRateTextField, new Double(5));
        expirationDateGood = validateMyTextField(expirationDateTextfield, new Integer(5));
        priceGood = validateMyTextField(priceTextField,new Double(5));
        boolean fin = providerGood || productGood || discountGood || expirationDateGood || priceGood;
        System.out.println("Total eval: "+ fin );

        return fin;
    }




    protected void clearAll() {
        Component[] component = fieldPanel.getComponents();
        for (int i = 0; i < fieldPanel.getComponentCount(); i++) {

            if (component[i] instanceof JTextField) ((JTextField) component[i]).setText("");

        }
    }





    public Coupon buyCoupon(){
        Coupon myCoupon = new Coupon();

        myCoupon.setProviderName(couponProviderNameTextField.getText());
        myCoupon.setProductName(productNameTextField.getText());
        myCoupon.setPrice(Double.parseDouble(priceTextField.getText()));
        myCoupon.setDiscountRate(Double.parseDouble(discountRateTextField.getText()));
        myCoupon.setExpirationDate(Integer.parseInt(expirationDateTextfield.getText()));
        myCoupon.setCouponStatus(CouponStatus.Unused);


        return myCoupon;
    }



}
