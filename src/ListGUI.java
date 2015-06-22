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
    JComboBox couponProvideComboBoxSort, productNameComboBoxSort, priceComboBoxSort, discountRateComboBoxSort, expirationDateComboBoxSort, finalPriceComboBoxSort, couponStatusComboBoxSort;
    JList couponProviderList, productNameList, priceList, discountRateList, expirationDateList, finalPriceList, couponStatusList;
    JLabel finalPriceLabel, couponStatusLabel;
    private DefaultListModel couponProviderListModel, productNameListModel, priceListModel, discountRateListModel, expirationDateListModel, finalPriceListModel, couponStatusModel;
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
        GUIPanel.masterList2.addListener(this);

        sortedList = new SortedList();
        sortedList.addListener(this);
        fieldPanel.setLayout(new GridLayout(3, 7, 25, 10));
        fieldPanel.add(finalPriceLabel);
        fieldPanel.add(couponStatusLabel);

        comboBoxConstructor();
        fieldPanel.add(couponProvideComboBoxSort);
        fieldPanel.add(productNameComboBoxSort);
        fieldPanel.add(priceComboBoxSort);
        fieldPanel.add(discountRateComboBoxSort);
        fieldPanel.add(expirationDateComboBoxSort);
        fieldPanel.add(finalPriceComboBoxSort);
        fieldPanel.add(couponStatusComboBoxSort);

        listConstructor();
        fieldPanel.add(couponProviderList);
        fieldPanel.add(productNameList);
        fieldPanel.add(priceList);
        fieldPanel.add(discountRateList);
        fieldPanel.add(expirationDateList);
        fieldPanel.add(finalPriceList);
        fieldPanel.add(couponStatusList);

        add(fieldPanel);
        iterRatePopulation(GUIPanel.masterList2);
        GUIPanel.masterList2.showStructure();

    }


    @Override
    protected void labelConstructor() {
        super.labelConstructor();
        finalPriceLabel = new JLabel("Final Price");
        finalPriceLabel.setHorizontalAlignment(JLabel.CENTER);

        couponStatusLabel = new JLabel("Coupon Status");
        couponStatusLabel.setHorizontalAlignment(JLabel.CENTER);
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

        couponStatusComboBoxSort = new JComboBox(new Object[]{" ", ASCENDING});
        couponStatusComboBoxSort.setRenderer(new IconListRenderer(icons));
        couponStatusComboBoxSort.addItemListener(this);
    }


    private void listModelConstructor() {
        couponProviderListModel = new DefaultListModel();
        productNameListModel = new DefaultListModel();
        priceListModel = new DefaultListModel();
        discountRateListModel = new DefaultListModel();
        expirationDateListModel = new DefaultListModel();
        finalPriceListModel = new DefaultListModel();
        couponStatusModel = new DefaultListModel();
    }


    private void listConstructor() {
        couponProviderList = new JList(couponProviderListModel);
        productNameList = new JList(productNameListModel);
        priceList = new JList(priceListModel);
        discountRateList = new JList(discountRateListModel);
        expirationDateList = new JList(expirationDateListModel);
        finalPriceList = new JList(finalPriceListModel);
        couponStatusList = new JList(couponStatusModel);

    }





    private void iterRatePopulation(List list) {
        System.out.println("ITERATING THROUGH POPULATION");
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


        couponProviderListModel.addElement(coupon.getCouponProviderName());

        productNameListModel.addElement(coupon.getProductName());
        priceListModel.addElement(currency.format(coupon.getPrice()));
        discountRateListModel.addElement(percent.format(coupon.getDiscountRate()/100));
        expirationDateListModel.addElement(coupon.getExpirationDate());

        finalPriceListModel.addElement(currency.format(coupon.getFinalPrice()));
        couponStatusModel.addElement(coupon.getStatus());

    }


    private void clearListModel() {
        couponProviderListModel.removeAllElements();
        productNameListModel.removeAllElements();
        priceListModel.removeAllElements();
        discountRateListModel.removeAllElements();
        expirationDateListModel.removeAllElements();
        finalPriceListModel.removeAllElements();
        couponStatusModel.removeAllElements();
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
//        test();

        if (source instanceof JComboBox) {
            deselectAllOtherComboBox((JComboBox) source);
            sortedList.clear();
            clearListModel();
            GUIPanel.masterList2.gotoBeginning();



            GUIPanel.masterList2.gotoBeginning();
            Coupon coupon = null;
            boolean result=true;
            if(e.getStateChange()==1 && (e.getItem().toString().equals(DESCENDING) || e.getItem().toString().equals(ASCENDING)) && GUIPanel.masterList2.getCursor()!=null) {
                System.out.println("Goint ahead!");

                while (result) {
                   System.out.printf("Current value: %s\t\n", ((Coupon) GUIPanel.masterList2.getCursor()).getCouponProviderName());
                    coupon = (Coupon) GUIPanel.masterList2.getCursor();
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

                    result = GUIPanel.masterList2.gotoNext();
                    System.out.println("Result: " + result);

                }
            }


        }
    }


    @Override
    public void listHasBeenUpdated(List list) {
        System.out.println("List has been updated");
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
            case 7: s= "getCouponProviderName";
                    break;

            case 8: s= "getProductName";
                break;

            case 9: s= "getPrice";
                break;

            case 10: s= "getDiscountRate";
                break;

            case 11: s= "getExpirationDate";
                break;

            case 12: s= "getFinalPrice";
                break;


            case 13: s = "getStatus";
                        break;
        }

        System.out.printf("Returning method: %s\twith index fo component: %d\n\n",s, componentIndex);
        return s;
    }

}
