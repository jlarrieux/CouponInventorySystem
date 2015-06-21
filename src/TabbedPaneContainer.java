package com.jeannius.cs401.project;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class TabbedPaneContainer extends JPanel {


    public static final String SEARCH_STRING = "Search", PURCHASE_STRING = "Purchase", LIST_STRING = "List";


    public TabbedPaneContainer() {
        super(new GridLayout(1, 1));
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                init();
            }
        });
    }


    private void init() {
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon searchIcon = createImageIcon("images/search_icon.png", "Search Icon");
        ImageIcon purchaseIcon = createImageIcon("images/buy_icon.png", "Buy Icon");
        ImageIcon listIcon = createImageIcon("images/sort_icon.png", "Sort Icon");

        JComponent searchPanel = makeTextPanel(SEARCH_STRING);
        JComponent purchasePanel = makeTextPanel(PURCHASE_STRING);
        JComponent listPanel = makeTextPanel(LIST_STRING);


        tabbedPane.addTab(SEARCH_STRING, searchIcon, searchPanel, "S.TE.ER.");
        tabbedPane.addTab(PURCHASE_STRING, purchaseIcon, purchasePanel, "rrrrr");

    }


    protected ImageIcon createImageIcon(String path, String description) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) return new ImageIcon(imgURL, description);
        else {
            //todo add system error path not found
            return null;
        }
    }


    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

}
