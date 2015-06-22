package com.jeannius.cs401.project;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class MyLauncher  {


    public static final String SEARCH_STRING = "Search", PURCHASE_STRING = "Purchase", LIST_STRING = "List", PROGRAM_NAME = "Coupon Inventory System by Jeanius INC.";
//    public static UnsortedList masterList;
    public static JFrame frame;


    public static void main(String[] arg) {
//        masterList = new UnsortedList();
        GUIPanel.masterList2 = new UnsortedList();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createAndShowGUI();
            }
        });


        try {
            initializeList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private static void createAndShowGUI() {

        frame = new JFrame(PROGRAM_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        ImageIcon searchIcon = createImageIcon("images/search_icon.png", "Search Icon");
        ImageIcon purchaseIcon = createImageIcon("images/buy_icon.png", "Buy Icon");
        ImageIcon listIcon = createImageIcon("images/sort_icon.png", "Sort Icon");



        PurchaseGUI purchase = new PurchaseGUI(5, 5);
        purchase.setAlignmentY((float) .5);
        tabbedPane.addTab(formatMyString(PURCHASE_STRING), purchaseIcon, new PurchaseGUI(5, 5));

        ListGUI listGUI = new ListGUI(50,5);
//        masterList.addListener(listGUI);
        GUIPanel.masterList2.addListener(listGUI);
        tabbedPane.addTab(formatMyString(LIST_STRING), listIcon, listGUI);
        tabbedPane.addTab(SEARCH_STRING, searchIcon, new SearchGUI(50, 5));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame.add(tabbedPane);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1300, 800);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

    }



    public static ImageIcon createImageIcon(String path, String description) {
        URL imgURL = MyLauncher.class.getResource(path);
        if (imgURL != null) return new ImageIcon(imgURL, description);
        else {
            //todo add system error path not found
            System.out.printf("Image not found at %s\t%s\n", path, imgURL);
            return null;
        }
    }


    private static String formatMyString(String name) {

        return String.format("%-20s", name);
    }


    public static void Showdialog(String text){

        JOptionPane.showMessageDialog(frame, text);

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
//               GUIPanel.masterList2.notifyListeners();
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

            coupon.setCouponStatus(coup[5]);

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
