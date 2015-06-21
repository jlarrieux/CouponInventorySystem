package com.jeannius.cs401.project;

import com.jeannius.cs401.project.panels.ListGUI;
import com.jeannius.cs401.project.panels.PurchaseGUI;
import com.jeannius.cs401.project.panels.SearchGUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class MyLauncher {


    public static final String SEARCH_STRING ="Search", PURCHASE_STRING="Purchase", LIST_STRING ="List";
    public static void main(String[] arg){

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });



    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        ImageIcon searchIcon = createImageIcon("images/search_icon.png", "Search Icon");
        ImageIcon purchaseIcon = createImageIcon("images/buy_icon.png", "Buy Icon");
        ImageIcon listIcon = createImageIcon("images/sort_icon.png", "Sort Icon");



        PurchaseGUI purchase = new PurchaseGUI(5,5);
        purchase.setAlignmentY((float) .5);
        tabbedPane.addTab(formatMyString(PURCHASE_STRING), purchaseIcon,new PurchaseGUI(5,5));
        tabbedPane.addTab(formatMyString(LIST_STRING),listIcon,new ListGUI(50,5));
        tabbedPane.addTab(SEARCH_STRING, searchIcon, new SearchGUI(50,5));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame.add(tabbedPane);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1300,800);
        frame.setLocation(dim.width/2 - frame.getSize().width/2, dim.height/2 - frame.getSize().height/2);
    }



    public static ImageIcon createImageIcon(String path, String description){
        URL imgURL = MyLauncher.class.getResource(path);
        if(imgURL !=null) return new ImageIcon(imgURL, description);
        else{
            //todo add system error path not found
            System.out.printf("Image not found at %s\t%s\n", path,imgURL);
            return null;
        }
    }

    private static String formatMyString(String name){

        return String.format("%-20s", name);
    }



}
