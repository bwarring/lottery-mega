package com.wara.app.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 11:59:01 AM
 */
public class MasterPanelGrid extends JPanel {
	private static final long serialVersionUID = 1L;
    private static final int GAP = 5;

    private MasterPanel masterPanel = null;
    private Image img;
    private String filepathname = null;
    private boolean hdgraphics = false;
	
	
	public MasterPanelGrid(String filepathname, Image img, boolean hdgraphics)
    {
        super();
        this.img = img;
        this.filepathname = filepathname;
        this.hdgraphics = hdgraphics;
        init();
    }

    public MasterPanelGrid(String filepathname)
    {
        super();
        this.filepathname = filepathname;
        init();
    }

    public MasterPanelGrid()
    {
        super();
        init();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
    
    void init()
    {
        // **** SET BACKGROUND COLOR INSTEAD OF BACKGROUND IMAGE
        // Color veryLightGray = new Color(243, 243, 250);
        Color beachSkyBlue = new Color(193, 191, 255);
        setBackground(beachSkyBlue); // commented out to display background image instead

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 650));
        setMaximumSize(new Dimension(1200, 650));
        setMinimumSize(new Dimension(1200, 650));
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));

        masterPanel = getMasterPanel();

        GridBagConstraints masterGbc = new GridBagConstraints();
        masterGbc.insets = new Insets(10, 10, 10, 10);
        masterGbc.anchor = GridBagConstraints.CENTER;
        masterGbc.fill = GridBagConstraints.HORIZONTAL;
        masterGbc.gridx = 0;
        masterGbc.gridy = 0;

        getMessageConsolePanel().setVisible(false);
        masterPanel.setVisible(true);
        add(masterPanel, masterGbc);
    }
    
    /**
     * @return the listLotteryItemsPanel
     */
    public ListLotteryItemsPanel getListLotteryItemsPanel()
    {
        return getMasterPanel().getListLotteryItemsPanel();
    }
    
    public MessageConsolePanel getMessageConsolePanel()
    {
        return getMasterPanel().getMessageConsolePanel();
    }
    
    /**
     * @return the masterPanel
     */
    public MasterPanel getMasterPanel()
    {
        if (masterPanel == null)
        {
            masterPanel = new MasterPanel(new GridBagLayout(), getFilepathname(), hdgraphics);
        }
        return masterPanel;
    }

    /**
     * @param masterPanel
     *            the masterPanel to set
     */
    public void setMasterPanel(MasterPanel masterPanel)
    {
        this.masterPanel = masterPanel;
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

}
