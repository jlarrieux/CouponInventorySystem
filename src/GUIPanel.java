package com.jeannius.cs401.project;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jeannius on 6/20/2015.
 */
public abstract class GUIPanel<T> extends JPanel {


    protected JPanel fieldPanel;
    JLabel couponProviderNameLabel, ProductNameLabel, priceLabel, discountRateLabel, expirationDateLabel;
    public static UnsortedList masterList2;

    protected void init() {

        labelConstructor();
        fieldPanel = new JPanel(new GridLayout(2, 5, 25, 10));
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 50));
        fieldPanel.add(couponProviderNameLabel);
        fieldPanel.add(ProductNameLabel);
        fieldPanel.add(priceLabel);
        fieldPanel.add(discountRateLabel);
        fieldPanel.add(expirationDateLabel);
    }


    protected void labelConstructor() {
        couponProviderNameLabel = new JLabel("Coupon Provider");
        couponProviderNameLabel.setHorizontalAlignment(JLabel.CENTER);
        ProductNameLabel = new JLabel("Product Name");
        ProductNameLabel.setHorizontalAlignment(JLabel.CENTER);
        discountRateLabel = new JLabel("Discount Rate");
        discountRateLabel.setHorizontalAlignment(JLabel.CENTER);
        expirationDateLabel = new JLabel("Expiration Date");
        expirationDateLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel = new JLabel("Price");
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
    }


    protected boolean validateMyTextField(JTextField jTextField,Class c ){
        Validator val = new Validator(jTextField,c);
        val.validate();

        return false;
    }

    public static void initializeList() throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        FileInputStream inFile;
        String fileName ="coupon.txt";
        StringBuffer progromText = new StringBuffer();
        String current ="";

        inFile = new FileInputStream(fileName);

        int c;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));
        boolean succeed = false;
        Coupon coupon1 = new Coupon();
        while(current!=null){

            coupon1 = createCoupon(reader.readLine());

            if(coupon1!=null){
                GUIPanel.masterList2.insert(coupon1);
                GUIPanel.masterList2.showStructure();
//               masterList.notifyListeners();
            }
        }
//        masterList.showStructure();


    }

    private static Coupon createCoupon(String c){

        if(c==null) return null;
        else {
            String[] coup= parser(c);
            Coupon coupon = new Coupon();
            coupon.setProviderName(coup[0]);
            coupon.setProductName(coup[1]);
            coupon.setPrice(Double.valueOf(coup[2]));
            coupon.setDiscountRate(Double.valueOf(coup[3]));
            coupon.setExpirationDate(Integer.valueOf(coup[4]));
            CouponStatus status = CouponStatus.Redeemed;
//            System.out.printf("Coupon name: %s\t adding status: %s\n", coupon.getCouponProviderName(), coup[5]);
//            if (coup[5].equals("R")) status = CouponStatus.Redeemed;
//            else status= CouponStatus.Unused;
            coupon.setCouponStatus(CouponStatus.valueOf(coup[5]));

            return coupon;
        }
    }

    private static String[] parser(String c){
        String[] red =new String[6];
        String current ="";
        int j=0, i=0;
//        System.out.printf("String: %s\n with length: %d\n", c, c.length());
        if(c!=null) {
            while (j < c.length()) {
                current = "";
//            System.out.println(c.length());
                while (j < c.length()) {
                    if (String.valueOf(c.charAt(j)).equals(",")) break;
                    current = current + c.charAt(j);
                    j++;


                }
//            System.out.printf("Current: %s and j: %d\n",current, j);
                red[i] = current;
                i++;
                j++;

            }
        }
        System.out.print("Array Printing ");
        for(int k=0; k<red.length; k++){
            System.out.printf(" \t %s \t", red[k]);
        }
        System.out.println();
        return red;
    }


}
