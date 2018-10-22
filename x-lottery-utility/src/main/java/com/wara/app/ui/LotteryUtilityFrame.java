package com.wara.app.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 10:45:08 AM
 */
public class LotteryUtilityFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private String filepathname = null;
    private boolean hdgraphics = false;
    
    private JMenuBar menuBar = null;
    private JMenu menu = null;
    private JMenu menuMegaNumbers = null;
    //private JMenu menuList = null;
    //private JMenu menuDelete = null;
    
    private List<JMenuItem> menuItems = null;
    private JMenuItem itemListMegaMillionNumbers = null;
    //private JMenuItem itemStopDynamoDB = null;
    private JMenuItem itemClear = null;
    private JMenuItem itemExit = null;
    
    private MasterPanelGrid masterPanelGrid;
    
    public LotteryUtilityFrame(){
    	super();
    }
    
    public LotteryUtilityFrame(String filepathname, boolean hdgraphics){
    	super();
    	setTitle("Lottery Numbers");
    	this.filepathname=filepathname;
    	this.hdgraphics=hdgraphics;
    	this.init();
    }
    
    public void init()
    {
    	this.initBackgroundImage();
        
        Toolkit toolkit = getToolkit();
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        setMaximizedBounds(env.getMaximumWindowBounds());
        // setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        GraphicsDevice device = getGraphicsConfiguration().getDevice();
        if (device.isFullScreenSupported())
        {
            // dispose();
            // setUndecorated(true);
            // setAlwaysOnTop(true);
            // device.setFullScreenWindow(this);
            // setVisible(true);
        }

        setAlwaysOnTop(false);
        this.initHdGraphics(toolkit);
        
        setResizable(true);
        addComponentListener(new ComponentAdapter()
        {

            @Override
            public void componentResized(ComponentEvent e)
            {
                setSize(new Dimension(getWidth(), getHeight()));
                super.componentResized(e);
            }

        });

        this.initMenuBar();

        masterPanelGrid = getMasterPanelGrid();
        masterPanelGrid.setVisible(true);

        setLayout(new BorderLayout());
        getContentPane().add(masterPanelGrid, BorderLayout.CENTER);

        // **** LIST LOTTERY ITEMS
        /*
        itemListMegaMillionNumbers.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                getMasterPanelGrid().getMessageConsolePanel().clear();
                // masterPanelGrid = getMasterPanelGrid();
                // add(masterPanelGrid);
                getMasterPanelGrid().setVisible(true);
                //getMasterPanelGrid().getListCoreTablesPanel().setVisible(true);
                // getMasterPanelGrid().getListCoreTablesPanel().setVisible(true);
                // getMasterPanelGrid().getListTableItemsPanel().setVisible(false);

                getMasterPanelGrid().getMessageConsolePanel().setVisible(true);
                //getMasterPanelGrid().getCreateTablePanel().setVisible(false);
                //getMasterPanelGrid().getLoadTablePanel().setVisible(false);
                //getMasterPanelGrid().getDeleteCorePanel().setVisible(false);
                //getMasterPanelGrid().getDeletePanel().setVisible(false);
                //getMasterPanelGrid().getListTableItemsPanel().setVisible(false);
                //getMasterPanelGrid().getListTableItemsPanel().getDataPanel().setVisible(false);
                //getMasterPanelGrid().getListTableItemsPanel().getItemListPanel().setVisible(false);
                //getMasterPanelGrid().getTextAreaPanel().setVisible(false);
                //getMasterPanelGrid().getTextAreaPanel().setVisible(false);

                validate();
            }
        });
        */
        
     // **** LIST LOTTERY ITEMS
        itemListMegaMillionNumbers.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	
                getMasterPanelGrid().getMessageConsolePanel().clear();
                // masterPanelGrid = getMasterPanelGrid();
                // add(masterPanelGrid);
                getMasterPanelGrid().setVisible(true);
            	// 2018-1015 - do not display dataPanel - filepathname is already populated.
                // set ListLotteryItemsPanel visible to true
                //getMasterPanelGrid().getListLotteryItemsPanel().getDataPanel().setVisible(true);
                getMasterPanelGrid().getListLotteryItemsPanel().setVisible(true);

                getMasterPanelGrid().getMessageConsolePanel().setVisible(false);
                //getMasterPanelGrid().getCreateTablePanel().setVisible(false);
                //getMasterPanelGrid().getLoadTablePanel().setVisible(false);
                //getMasterPanelGrid().getDeleteCorePanel().setVisible(false);
                //getMasterPanelGrid().getDeletePanel().setVisible(false);
                //getMasterPanelGrid().getListTableItemsPanel().getItemListPanel().setVisible(false);
                //getMasterPanelGrid().getListCoreTablesPanel().setVisible(false);
                //getMasterPanelGrid().getTextAreaPanel().setVisible(false);

                validate();
            }
        });

        // **** CLEAR VIEW
        itemClear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                getMasterPanelGrid().getMessageConsolePanel().clear();
                // masterPanelGrid = getMasterPanelGrid();
                // add(masterPanelGrid);

                getMasterPanelGrid().getMessageConsolePanel().setVisible(false);
                //getMasterPanelGrid().getCreateTablePanel().setVisible(false);
                //getMasterPanelGrid().getLoadTablePanel().setVisible(false);
                //getMasterPanelGrid().getDeleteCorePanel().setVisible(false);
                //getMasterPanelGrid().getDeletePanel().setVisible(false);
                //getMasterPanelGrid().getListTableItemsPanel().setVisible(false);
                //getMasterPanelGrid().getListTableItemsPanel().getDataPanel().setVisible(false);
                //getMasterPanelGrid().getListCoreTablesPanel().setVisible(false);
                //getMasterPanelGrid().getTextAreaPanel().setVisible(false);

                getMasterPanelGrid().setVisible(true);

                validate();
            }
        });

        // **** EXIT APPLICATION
        itemExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	/*
                try
                {
                    Runtime.getRuntime().exec("taskkill /im cmd.exe");
                }
                catch (Exception exc)
                {
                    System.out.println("EXCEPTION: " + exc.getMessage());
                }
                */
                System.exit(0);
            }
        });

        menuBar.add(menu);
        for (JMenuItem menuItem : menuItems)
        {
            menu.add(menuItem);
        }

        //menuBar.add(menuCreate);
        //menuBar.add(menuList);
        //menuBar.add(menuDelete);

        setJMenuBar(menuBar);

        setSize(1200, 650); // default size is 0,0
        // setLocation(10, 200); // default is 0,0 (top left corner)
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // **** Centers Frame on Screen
        // **** Get resolution of monitor
        //Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        // Window Listeners
        addWindowListener(new WindowAdapter()
        {
            @Override
			public void windowClosing(WindowEvent e)
            {
            	/*
                try
                {
                    Runtime.getRuntime().exec("taskkill /im cmd.exe");
                }
                catch (Exception exc)
                {
                    System.out.println("EXCEPTION: " + exc.getMessage());
                }
                */
                System.exit(0);
            } // windowClosing
        });
    }
    
    private void initBackgroundImage(){
    	try
        {
            /**
             * Set frame background image.<br><br>
             * <p/>
             * The answer is this stackoverflow works for exporting a runnable jar:<br>
             * http://stackoverflow.com/questions/13989785/runnable-jar-doesnt-see-resources-and-other-libraries
             * </p>
             */
            InputStream is = this.getClass().getResourceAsStream("/images/nice_ass_bluesky.jpg");
            BufferedImage bi = ImageIO.read(is);
            ImageIcon img = new ImageIcon(bi);
            setIconImage(img.getImage());
        }
        catch (Exception e)
        {
            // do nothing
        }
    }
    
    private void initHdGraphics(Toolkit toolkit){
    	if (toolkit != null)
        {
            if (toolkit.getScreenResolution() > 200)
            {
                hdgraphics = true;
            }
        }
        
        if (hdgraphics)
        {
            setMaximumSize(new Dimension(2500, 2000));
            setMinimumSize(new Dimension(2500, 2000));
            setPreferredSize(new Dimension(2500, 2000));
        }
        else
        {
            setMaximumSize(new Dimension(1000, 700));
            setMinimumSize(new Dimension(1000, 700));
            setPreferredSize(new Dimension(1000, 700));
        }
    }
    
    private void initMenuBar(){
    	menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuMegaNumbers = new JMenu("MegaMillion Numbers");
        //menuList = new JMenu("List");
        //menuDelete = new JMenu("Delete");

        menu.addSeparator();
        menuMegaNumbers.addSeparator();
        //menuList.addSeparator();
        //menuDelete.addSeparator();
        
        menuItems = new ArrayList<JMenuItem>();
        itemListMegaMillionNumbers = new JMenuItem("List MegaMillion Numbers");
        //itemStopDynamoDB = new JMenuItem("Stop DynamoDB");
        itemExit = new JMenuItem("Exit");
        itemClear = new JMenuItem("Clear");
        menuItems.add(itemListMegaMillionNumbers);
        //menuItems.add(itemStopDynamoDB);
        menuItems.add(itemClear);
        menuItems.add(itemExit);
        
        itemListMegaMillionNumbers.setToolTipText("Create list of MegaMillion selected numbers.");
        menuMegaNumbers.add(itemListMegaMillionNumbers);

        /*
        itemCreateTable.setToolTipText("Table description is pulled from remote DEV DynamoDB.");
        menuCreate.add(itemCreateTable);

        itemCreateTables.setToolTipText("Starts process that creates and loads all core tables.");
        menuCreate.add(itemCreateTables);
        
        itemLoadTable.setToolTipText("Starts process to load an empty table.");
        menuCreate.add(itemLoadTable);
        
        menuDelete.add(itemDeleteCoreTable);
        menuDelete.add(itemDeleteTable);
        menuList.add(itemListCoreTables);
        menuList.add(itemListTableItems);
        */
    }
    
    /**
     * @return the masterPanelGrid
     */
    private MasterPanelGrid getMasterPanelGrid(){
        if (masterPanelGrid == null)
        {
            boolean imageFound = false;
            try
            {
                /**
                 * <p/>
                 * The answer is this stackoverflow works for exporting a runnable jar:<br>
                 * http://stackoverflow.com/questions/13989785/runnable-jar-doesnt-see-resources-and-other-libraries
                 * </p><br>
                 * bombay-sapphire-beach1.jpg<br>
                 * beach_guitar_01_1200x650.png<br>
                 * contra-01.png<br>
                 * contra-02.png
                 */
                String backgroundImage = "beach_drinks.jpg";
                InputStream is = this.getClass().getResourceAsStream("/images/" + backgroundImage);
                
                imageFound = true;
                BufferedImage bi = ImageIO.read(is);
                ImageIcon imageIcon = new ImageIcon(bi);
                masterPanelGrid = new MasterPanelGrid(getFilepathname(), imageIcon.getImage(), hdgraphics);
            }
            catch (Exception e)
            {
                // do nothing
            }
            if (!imageFound)
            {
                masterPanelGrid = new MasterPanelGrid(getFilepathname());
            }
        }
        return masterPanelGrid;
    }
    

	/**
	 * @return the filepathname
	 */
	public String getFilepathname() {
		return filepathname;
	}

	/**
	 * @param path the filepathname to set
	 */
	public void setFilepathname(String filepathname) {
		this.filepathname = filepathname;
	}

	/**
	 * @return the hdgraphics
	 */
	public boolean isHdgraphics() {
		return hdgraphics;
	}

	/**
	 * @param hdgraphics the hdgraphics to set
	 */
	public void setHdgraphics(boolean hdgraphics) {
		this.hdgraphics = hdgraphics;
	}
    
	/**
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.out.println("Exception " + e.getMessage());
        }

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new LotteryUtilityFrame().setVisible(true);
            }
        });
    }

}
