package com.wara.app.ui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 12:20:01 PM
 */
public class LimitLinesDocumentListener implements DocumentListener {

	private int lines;
    private boolean isAppend;

    public LimitLinesDocumentListener(int lines, boolean isAppend)
    {
        super();
        this.lines = lines;
        this.isAppend = isAppend;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void changedUpdate(DocumentEvent e)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void insertUpdate(DocumentEvent e)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void removeUpdate(DocumentEvent e)
    {
        // TODO Auto-generated method stub

    }

    /**
     * @return the lines
     */
    public int getLines()
    {
        return lines;
    }

    /**
     * @return the isAppend
     */
    public boolean isAppend()
    {
        return isAppend;
    }

    /**
     * @param lines
     *            the lines to set
     */
    public void setLines(int lines)
    {
        this.lines = lines;
    }

    /**
     * @param isAppend
     *            the isAppend to set
     */
    public void setAppend(boolean isAppend)
    {
        this.isAppend = isAppend;
    }

}
