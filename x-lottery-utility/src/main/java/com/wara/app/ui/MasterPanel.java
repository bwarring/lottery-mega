package com.wara.app.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 12:12:44 PM
 */
public class MasterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    private String filepathname;
    private boolean hdgraphic = false;

    private TextareaPanel textAreaPanel = new TextareaPanel();
    private MessageConsolePanel messageConsolePanel;
    private ListLotteryItemsPanel listLotteryItemsPanel;
    
    public MasterPanel()
    {
        super();
        init(hdgraphic);
    }

    public MasterPanel(LayoutManager layout, String filepathname, boolean hdgraphic)
    {
        super(layout);
        this.filepathname = filepathname;
        this.hdgraphic = hdgraphic;
        init(hdgraphic);
    }

    void init(boolean hdgraphic)
    {
        Color veryLightGray = new Color(243, 243, 250);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // **** MESSAGE CONSOLE PANEL
        messageConsolePanel = new MessageConsolePanel(hdgraphic);
        // messageConsolePanel.setPreferredSize(new Dimension(1200, 600));
        messageConsolePanel.setBackground(veryLightGray);
        messageConsolePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(messageConsolePanel, gbc);

        /*
        // **** LIST CORE TABLES PANEL
        listCoreTablesPanel = new ListCoreTablesPanel(getPath(), hdgraphic);
        listCoreTablesPanel.setBackground(veryLightGray);
        listCoreTablesPanel.setVisible(false);
        listCoreTablesPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(listCoreTablesPanel, gbc);
        */

        // **** LIST LOTTERY ITEMS PANEL
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        listLotteryItemsPanel = new ListLotteryItemsPanel(this.filepathname, hdgraphic);
        listLotteryItemsPanel.setBackground(veryLightGray);
        listLotteryItemsPanel.setVisible(false);
        listLotteryItemsPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(listLotteryItemsPanel, gbc);

    	/*
        // **** CREATE TABLE PANEL
        createTablePanel = new CreateTablePanel(path, messageConsolePanel, hdgraphic);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gbc.gridwidth = 2;
        // createTablePanel.setPreferredSize(new Dimension(1200, 600));
        createTablePanel.setBackground(veryLightGray);
        createTablePanel.setVisible(false);
        createTablePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(createTablePanel, gbc);
        
     // **** LOAD TABLE PANEL
        loadTablePanel = new LoadTablePanel(path, messageConsolePanel, hdgraphic);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gbc.gridwidth = 2;
        // createTablePanel.setPreferredSize(new Dimension(1200, 600));
        loadTablePanel.setBackground(veryLightGray);
        loadTablePanel.setVisible(false);
        loadTablePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(loadTablePanel, gbc);

        // **** DELETE TABLE PANEL
        deletePanel = new DeletePanel(path, false, messageConsolePanel, hdgraphic);
        // deletePanel.setPreferredSize(new Dimension(1200, 600));
        deletePanel.setBackground(veryLightGray);
        deletePanel.setVisible(false);
        deletePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(deletePanel, gbc);

        // **** DELETE CORE TABLE PANEL
        deleteCorePanel = new DeletePanel(path, true, messageConsolePanel, hdgraphic);
        // deleteCorePanel.setPreferredSize(new Dimension(1200, 600));
        deleteCorePanel.setBackground(veryLightGray);
        deleteCorePanel.setVisible(false);
        deleteCorePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(deleteCorePanel, gbc);
        */

    }

    /**
     * @return the filepathname
     */
    public String getFilepathname()
    {
        return filepathname;
    }

    /**
     * @param filepathname
     *            the filepathname to set
     */
    public void setFilepathname(String filepathname)
    {
        this.filepathname = filepathname;
    }

    /**
     * @return the textAreaPanel
     */
    public TextareaPanel getTextAreaPanel()
    {
        return textAreaPanel;
    }

    /**
     * @return the messageConsolePanel
     */
    public MessageConsolePanel getMessageConsolePanel()
    {
        return messageConsolePanel;
    }
    
    /**
     * @return the listLotteryItemsPanel
     */
    public ListLotteryItemsPanel getListLotteryItemsPanel()
    {
        return listLotteryItemsPanel;
    }


    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Master Frame");

        MasterPanel mainPanel = new MasterPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel paintPanel = new JPanel();
        paintPanel.setPreferredSize(new Dimension(200, 200));
        paintPanel.setBackground(Color.GREEN);

        JPanel textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension(400, 80));
        textPanel.setBackground(Color.LIGHT_GRAY);
        textPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TextareaPanel dashPanel = new TextareaPanel();
        dashPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainPanel.add(dashPanel);

        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setBackground(Color.BLUE);
        // mainPanel.setLayout(new GridLayout(1, 3));

        // mainPanel.add(paintPanel);
        mainPanel.add(textPanel);

        frame.add(mainPanel);
        frame.validate();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
