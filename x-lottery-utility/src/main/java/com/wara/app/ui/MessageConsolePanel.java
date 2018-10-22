package com.wara.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 12:13:54 PM
 */
public class MessageConsolePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
    private TextareaPanel consoleOutput = null;

    public MessageConsolePanel(boolean hdgraphic)
    {
        super();
        init(hdgraphic);
    }

    private void init(boolean hdgraphic)
    {
        // (red, green, blue)
        Color veryLightGray = new Color(243, 243, 250);

        // setPreferredSize(new Dimension(600, 400));
        // TextareaPanel consoleOutput = new TextareaPanel();
        // JTextArea consoleOutput = new JTextArea(5, 20);
        // consoleOutput.setMargin(new Insets(5, 5, 5, 5));
        // consoleOutput.setEditable(false);
        setLayout(new GridLayout(1, 1));

        consoleOutput = new TextareaPanel("CONSOLE MESSAGES");
        //consoleOutput.setPreferredSize(new Dimension(900, 400));
        consoleOutput.setPreferredSize(new Dimension(1800, 1600));
        consoleOutput.getTextArea().setBackground(veryLightGray);
        // consoleOutput.setPreferredSize(null);
        // JTextArea consoleOutput = new JTextArea("Console");
        // JScrollPane scrollPane = new JScrollPane(consoleOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        // JScrollPane scrollPane = new JScrollPane(consoleOutput);
        // scrollPane.setPreferredSize(new Dimension(800, 600));
        add(consoleOutput);
        // setPreferredSize(new Dimension(900, 400));

        MessageConsole mc = new MessageConsole(consoleOutput.getTextArea());
        mc.redirectOut(Color.BLUE, null);
        mc.redirectErr(Color.RED, null);
        mc.setMessageLines(100);
    }

    public void clear()
    {
        getConsoleOutput().setText("");
        repaint();
    }

    public TextareaPanel getConsoleOutput()
    {
        return consoleOutput;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Master Frame");
        Toolkit toolkit = frame.getToolkit();
        boolean hdgraphics = (toolkit.getScreenResolution()>200);

        MessageConsolePanel panel = new MessageConsolePanel(hdgraphics);
        JTextArea consoleOutput = new JTextArea(5, 20);
        consoleOutput.setMargin(new Insets(5, 5, 5, 5));
        consoleOutput.setEditable(false);

        JTextArea textArea = new JTextArea("Console");
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        MessageConsole mc = new MessageConsole(textArea);
        mc.redirectOut(Color.BLUE, null);
        mc.redirectErr(Color.RED, null);
        mc.setMessageLines(100);

        frame.add(panel);
        frame.validate();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
