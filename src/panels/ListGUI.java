package com.jeannius.cs401.project.panels;


import com.jeannius.cs401.project.MyLauncher;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Jeannius on 6/20/2015.
 */
public class ListGUI extends GUIPanel {

    JComboBox couponProvideComboBoxSort, productNameComboBoxSort, priceComboBoxSort, discountRateComboBoxSort,expirationDateComboBoxSort, finalPriceComboBoxSort;
    JList couponProviderList, productNameList, priceList, discountRateList, expirationDateList, finalPriceList;
    JLabel finalPriceLabel;


    private static final String DESCENDING = "Descending", ASCENDING="Ascending";

    public ListGUI(int width, int heigth){
        setPreferredSize(new Dimension(width, heigth));
        init();

    }

    @Override
    protected void init(){
        super.init();
        fieldPanel.setLayout(new GridLayout(3,6,25,10));
        fieldPanel.add(finalPriceLabel);
        comboBoxConstructor();
        fieldPanel.add(couponProvideComboBoxSort);
        fieldPanel.add(productNameComboBoxSort);
        fieldPanel.add(priceComboBoxSort);
        fieldPanel.add(discountRateComboBoxSort);
        fieldPanel.add(expirationDateComboBoxSort);
        fieldPanel.add(finalPriceComboBoxSort);

        listConstructor();
        fieldPanel.add(couponProviderList);
        fieldPanel.add(productNameList);
        fieldPanel.add(priceList);
        fieldPanel.add(discountRateList);
        fieldPanel.add(expirationDateList);
        fieldPanel.add(finalPriceList);

        add(fieldPanel);

    }


    @Override
    protected void labelConstructor() {
        super.labelConstructor();
        finalPriceLabel = new JLabel("Final Price");
        finalPriceLabel.setHorizontalAlignment(JLabel.CENTER);
    }


    private void comboBoxConstructor(){

        Map<Object, Icon> icons = new HashMap<Object, Icon>();
        icons.put(DESCENDING, MyLauncher.createImageIcon("images/descending_sort.png", DESCENDING));
        icons.put(ASCENDING, MyLauncher.createImageIcon("images/ascending_sort.jpg", ASCENDING));

        couponProvideComboBoxSort = new JComboBox(new Object[]{DESCENDING, ASCENDING});
        couponProvideComboBoxSort.setRenderer(new IconListRenderer(icons));

        productNameComboBoxSort =new JComboBox(new Object[]{DESCENDING, ASCENDING});
        productNameComboBoxSort.setRenderer(new IconListRenderer(icons));

        priceComboBoxSort =new JComboBox(new Object[]{DESCENDING, ASCENDING});
        priceComboBoxSort.setRenderer(new IconListRenderer(icons));

        discountRateComboBoxSort = new JComboBox(new Object[]{DESCENDING, ASCENDING});
        discountRateComboBoxSort.setRenderer(new IconListRenderer(icons));

        expirationDateComboBoxSort = new JComboBox(new Object[]{DESCENDING, ASCENDING});
        expirationDateComboBoxSort.setRenderer(new IconListRenderer(icons));

        finalPriceComboBoxSort = new JComboBox(new Object[]{DESCENDING, ASCENDING});
        finalPriceComboBoxSort.setRenderer(new IconListRenderer(icons));
    }


    private void listConstructor(){
        couponProviderList = new JList();
        productNameList = new JList();
        priceList = new JList();
        discountRateList = new JList();
        expirationDateList = new JList();
        finalPriceList = new JList();

    }





}
