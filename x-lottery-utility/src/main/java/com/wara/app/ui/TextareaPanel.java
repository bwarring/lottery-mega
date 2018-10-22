package com.wara.app.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 12:15:42 PM
 */
public class TextareaPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
    private JTextArea textArea = new JTextArea("Console");
    private String name = "Default Name";

    public TextareaPanel(LayoutManager layoutManager)
    {
        super(layoutManager);
        init();
    }

    public TextareaPanel()
    {
        super();
        setLayout(null);
        init();
    }

    public TextareaPanel(String name)
    {
        super();
        this.name = name;
        setLayout(null);
        init();
    }

    public void setText(String text)
    {
        textArea.setText(text);
    }

    private void init()
    {
        textArea.setName(name);
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setPreferredSize(new Dimension(600, 400));
        setMinimumSize(new Dimension(600, 400));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridLayout(1, 1));
        // **** ADD SCROLL PANE TO PANEL
        add(scroll);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Textarea Test");

        TextareaPanel panel = new TextareaPanel();
        // JTextArea textArea = new JTextArea("Test");
        // textArea.setText(getJsonRequest());

        // JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        // **** ADD SCROLL PANE TO PANEL
        // panel.add(scroll);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    static String getJsonRequest()
    {

        return "\"action\": \"create\"";
    } // end of method

    /**
     * @return the textArea
     */
    public JTextArea getTextArea()
    {
        return textArea;
    }

    /**
     * @return the name
     */
    @Override
	public String getName()
    {
        return name;
    }

    /**
     * @param textArea
     *            the textArea to set
     */
    public void setTextArea(JTextArea textArea)
    {
        this.textArea = textArea;
    }

    /**
     * @param name
     *            the name to set
     */
    @Override
	public void setName(String name)
    {
        this.name = name;
    }

}
