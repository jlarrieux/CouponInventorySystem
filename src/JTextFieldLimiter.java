package com.jeannius.cs401.project;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class JTextFieldLimiter extends PlainDocument {


    private int limit;


    public JTextFieldLimiter(int limit) {
        super();
        this.limit = limit;
    }


    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }


}
