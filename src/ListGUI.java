package com.jeannius.cs401.project;


import com.jeannius.cs401.project.myInterfaces.List;
import com.jeannius.cs401.project.myInterfaces.ListUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

//import com.jeannius.cs401.project.SL


/**
 * Created by Jeannius on 6/20/2015.
 */
public class ListGUI<T> extends GUIPanel implements ListUpdateListener, ItemListener {


    private static final String DESCENDING = "Descending", ASCENDING = "Ascending";
    JComboBox couponProvideComboBoxSort, productNameComboBoxSort, priceComboBoxSort, discountRateComboBoxSort, expirationDateComboBoxSort, finalPriceComboBoxSort;
    JList couponProviderList, productNameList, priceList, discountRateList, expirationDateList, finalPriceList;
    JLabel finalPriceLabel;
    private DefaultListModel couponProviderListModel, productNameListModel, priceListModel, discountRateListModel, expirationDateListModel, finalPriceListModel;
    SortedList sortedList;



    public ListGUI(int width, int heigth) {
        setPreferredSize(new Dimension(width, heigth));
        init();

    }


    @Override
    protected void init() {
        super.init();
        listModelConstructor();
//        couponList = new ArrayList<>();

        sortedList = new SortedList();
        sortedList.addListener(this);
        fieldPanel.setLayout(new GridLayout(3, 6, 25, 10));
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


    private void comboBoxConstructor() {

        Map<Object, Icon> icons = new HashMap<Object, Icon>();

        icons.put(DESCENDING, MyLauncher.createImageIcon("images/descending_sort.png", DESCENDING));
        icons.put(ASCENDING, MyLauncher.createImageIcon("images/ascending_sort.jpg", ASCENDING));

        couponProvideComboBoxSort = new JComboBox(new Object[]{" ", ASCENDING});
        couponProvideComboBoxSort.setRenderer(new IconListRenderer(icons));
        couponProvideComboBoxSort.addItemListener(this);

        productNameComboBoxSort = new JComboBox(new Object[]{" ",  ASCENDING});
        productNameComboBoxSort.setRenderer(new IconListRenderer(icons));
        productNameComboBoxSort.addItemListener(this);

        priceComboBoxSort = new JComboBox(new Object[]{" ", ASCENDING});
        priceComboBoxSort.setRenderer(new IconListRenderer(icons));
        priceComboBoxSort.addItemListener(this);

        discountRateComboBoxSort = new JComboBox(new Object[]{" ", ASCENDING});
        discountRateComboBoxSort.setRenderer(new IconListRenderer(icons));
        discountRateComboBoxSort.addItemListener(this);

        expirationDateComboBoxSort = new JComboBox(new Object[]{" ", ASCENDING});
        expirationDateComboBoxSort.setRenderer(new IconListRenderer(icons));
        expirationDateComboBoxSort.addItemListener(this);

        finalPriceComboBoxSort = new JComboBox(new Object[]{" ", ASCENDING});
        finalPriceComboBoxSort.setRenderer(new IconListRenderer(icons));
        finalPriceComboBoxSort.addItemListener(this);
    }


    private void listModelConstructor() {
        couponProviderListModel = new DefaultListModel();
        productNameListModel = new DefaultListModel();
        priceListModel = new DefaultListModel();
        discountRateListModel = new DefaultListModel();
        expirationDateListModel = new DefaultListModel();
        finalPriceListModel = new DefaultListModel();
    }


    private void listConstructor() {
        couponProviderList = new JList(couponProviderListModel);
        productNameList = new JList(productNameListModel);
        priceList = new JList(priceListModel);
        discountRateList = new JList(discountRateListModel);
        expirationDateList = new JList(expirationDateListModel);
        finalPriceList = new JList(finalPriceListModel);

    }





    private void iterRatePopulation(List list) {
        clearListModel();
        boolean result= true;
        if (list.size() > 0) {
            list.gotoBeginning();
//            for (int i = 0; i < list.size(); i++) {
//                populate((Coupon) list.getCursor());
//                list.gotoNext();
//            }
            while(result){
                populate((Coupon) list.getCursor());
                result = list.gotoNext();
            }
        }
    }


    private void populate(Coupon coupon) {

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
//        couponList.add(coupon);

        couponProviderListModel.addElement(coupon.getCouponProviderName());

        productNameListModel.addElement(coupon.getProductName());
        priceListModel.addElement(currency.format(coupon.getPrice()));
        discountRateListModel.addElement(percent.format(coupon.getDiscountRate()/100));
        expirationDateListModel.addElement(coupon.getExpirationDate());
//        double finalPrice = coupon.getPrice() * (1 - (coupon.getDiscountRate() / 100));
        finalPriceListModel.addElement(currency.format(coupon.getFinalPrice()));

    }


    private void clearListModel() {
        couponProviderListModel.removeAllElements();
        productNameListModel.removeAllElements();
        priceListModel.removeAllElements();
        discountRateListModel.removeAllElements();
        expirationDateListModel.removeAllElements();
        finalPriceListModel.removeAllElements();
//        couponList.clear();

    }





    private void deselectAllOtherComboBox(JComboBox selected) {
        Component[] components = fieldPanel.getComponents();
        for (int i = 0; i < fieldPanel.getComponentCount(); i++) {
            if (components[i] instanceof JComboBox) {

                if (((JComboBox) components[i]).equals(selected)) {
//                    System.out.println(i);
                } else {
                    ((JComboBox) components[i]).setSelectedIndex(0);
                }
            }
        }

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
//        System.out.printf("Component index: %d\n", getComponentIndex((Component) source));
        test();

        if (source instanceof JComboBox) {
            deselectAllOtherComboBox((JComboBox) source);
            sortedList.clear();
            clearListModel();
            MyLauncher.masterList.gotoBeginning();



            MyLauncher.masterList.gotoBeginning();
            Coupon coupon = null;
            boolean result=true;
            if(e.getStateChange()==1 && (e.getItem().toString().equals(DESCENDING) || e.getItem().toString().equals(ASCENDING)) && MyLauncher.masterList.getCursor()!=null) {
                System.out.println("Goint ahead!");

                while (result) {
                   System.out.printf("Current value: %s\t\n", ((Coupon) MyLauncher.masterList.getCursor()).getCouponProviderName());
                    coupon = (Coupon) MyLauncher.masterList.getCursor();
                    coupon.setMethodNameToIterate(selectMethod(getComponentIndex((Component) source)));
                    try {
                        sortedList.insert(coupon);
                    } catch (InvocationTargetException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchMethodException e1) {
                        e1.printStackTrace();
                    }

                    result = MyLauncher.masterList.gotoNext();
                    System.out.println("Result: " + result);

                }
            }


        }
    }


    @Override
    public void listHasBeenUpdated(List list) {

        iterRatePopulation(list);
    }


    private int getComponentIndex(Component component){
        if(component!=null) {
            Container container = component.getParent();
            for(int i=0; i<container.getComponentCount(); i++){
                if(container.getComponent(i)==component) return i;
            }
        }

        return -1;
    }



    private void test(){
        Method[] methods = Coupon.class.getDeclaredMethods();
        for(int i=0; i< methods.length; i++){
            System.out.printf("metor[%d]: %s\n", i, methods[i].getName());
        }
    }


    private String selectMethod(int componentIndex){
       String s="";
        switch (componentIndex){
            case 6: s= "getCouponProviderName";
                    break;

            case 7: s= "getProductName";
                break;

            case 8: s= "getPrice";
                break;

            case 9: s= "getDiscountRate";
                break;

            case 10: s= "getExpirationDate";
                break;

            case 11: s= "getFinalPrice";
                break;


            case 12: s = "getStatus";
                        break;
        }

        System.out.printf("Returning method: %s\twith index fo component: %d\n\n",s, componentIndex);
        return s;
    }

}
