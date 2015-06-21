package com.jeannius.cs401.project;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class IconListRenderer extends DefaultListCellRenderer {


    private static final long serialVersionUID = 1L;
    private Map<Object, Icon> icons = null;


    public IconListRenderer(Map<Object, Icon> iconMap) {
        this.icons = iconMap;
    }


    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        Icon icon = icons.get(value);

        label.setIcon(icon);
        return label;
    }
}
