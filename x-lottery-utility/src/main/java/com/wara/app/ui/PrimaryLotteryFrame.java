package com.wara.app.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.wara.app.type.OperatingSystemType;
import com.wara.app.type.RegXPatternType;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 1:00:35 PM
 */
public class PrimaryLotteryFrame extends JFrame {
	private static final long serialVersionUID = 1L;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private boolean hdGraphics = false;

    private JPanel newPanel = new JPanel(new GridBagLayout());
    private JLabel labelPath = new JLabel("Enter path: ");
    // private JLabel labelPassword = new JLabel("Enter password: ");
    private JTextField pathTextField = new JTextField("C:/$Warring Associates LLC/Contracts/BiteMe", 50);
    // private JPasswordField fieldPassword = new JPasswordField(20);
    private JLabel labelError = new JLabel(" ");
    private JButton buttonStartLottery = buttonStartLottery();
    private JButton buttonCancel = buttonCancel();
    private JButton buttonBrowse = buttonBrowse();
    private JCheckBox hdgraphicsCheckbox;

    JFileChooser chooser;
    String choosertitle = "Choose MegaMillion CSV File";
    
    public PrimaryLotteryFrame(){
    	super("MegaMillion Lottery Dashboard");
    	init();
    }
    
    private void init(){
    	try
        {
            /**
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

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelPath, constraints);

        constraints.gridx = 1;
        newPanel.add(pathTextField, constraints);
        
        hdgraphicsCheckbox = new JCheckBox("HD Graphics - Select if running 4KHD graphic card.", true);
        hdgraphicsCheckbox.addItemListener(new CheckBoxListener());
        setSize(300,300);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        newPanel.add(hdgraphicsCheckbox, constraints);

        GridBagConstraints constraintsError = new GridBagConstraints();
        constraintsError.gridx = 0;
        constraintsError.gridy = 4;
        constraintsError.gridwidth = 2;
        newPanel.add(labelError, constraintsError);
        labelError.setForeground(Color.RED);
        labelError.setVisible(false);

        /*
         * constraints.gridx = 0;
         * constraints.gridy = 2;
         * constraints.gridwidth = 2;
         * constraints.anchor = GridBagConstraints.CENTER;
         * newPanel.add(new FileChooserPanel());
         */

        // constraints.gridx = 0;
        // constraints.gridy = 1;
        // newPanel.add(labelPassword, constraints);

        // constraints.gridx = 1;
        // newPanel.add(fieldPassword, constraints);

        JPanel buttonPanel = new JPanel(new GridLayout(1,3,20,10));
        GridBagConstraints bpContrainst1 = new GridBagConstraints();
        bpContrainst1.gridx = 0;
        bpContrainst1.gridy = 0;
        bpContrainst1.gridwidth = 3;
        bpContrainst1.anchor = GridBagConstraints.WEST;
        buttonPanel.add(buttonStartLottery, bpContrainst1);
        
        GridBagConstraints bpContrainst2 = new GridBagConstraints();
        bpContrainst2.gridx = 1;
        bpContrainst2.gridy = 0;
        bpContrainst2.gridwidth = 3;
        bpContrainst2.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(buttonCancel, bpContrainst2);
        
        GridBagConstraints bpContrainst3 = new GridBagConstraints();
        bpContrainst3.gridx = 2;
        bpContrainst3.gridy = 0;
        bpContrainst3.gridwidth = 3;
        bpContrainst3.anchor = GridBagConstraints.EAST;
        buttonPanel.add(buttonBrowse, bpContrainst3);
        
        GridBagConstraints bpContrainst4 = new GridBagConstraints();
        bpContrainst4.gridx = 0;
        bpContrainst4.gridy = 3;
        bpContrainst4.gridwidth = 2;
        bpContrainst4.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonPanel, bpContrainst4);
        
        
        /*
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        newPanel.add(buttonStartLottery, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonCancel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        newPanel.add(buttonBrowse, constraints);
        */

        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lottery Numbers"));

        // add the panel to this frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);
    }
    
    private class CheckBoxListener implements ItemListener{
        @Override
		public void itemStateChanged(ItemEvent e) {
            if(e.getSource()==hdgraphicsCheckbox){
                if(hdgraphicsCheckbox.isSelected()) 
                {
                    hdGraphics = true;
                    System.out.println("hdgraphicsCheckbox has been selected");
                } 
                else 
                {
                    hdGraphics = false;
                    System.out.println("nothing");
                }
            }
        }
    }
    
    private JButton buttonStartLottery()
    {
        buttonStartLottery = new JButton("Submit");

        buttonStartLottery.addActionListener(new ActionListener()
        {
            @Override
			public void actionPerformed(ActionEvent event)
            {
            	if (isValidCsv(pathTextField.getText())){
	            	String filepath = pathTextField.getText();
	                System.out.println(pathTextField.getText());
	                LotteryUtilityFrame dashboardFrame = new LotteryUtilityFrame(filepath, hdGraphics);
	                setVisible(false);
	                dashboardFrame.setVisible(true);
            	}else{
                    labelError.setText("Invalid *.csv filename: " + pathTextField.getText());
                    labelError.setForeground(Color.RED);
                    labelError.setVisible(true);
                    pack();
                    System.out.println("Invalid *.csv filename: " + pathTextField.getText());
                }
            	
            	
            	/*
                //boolean dynoRunning = true;
                String dynamoDBPath, configPath;
                dynamoDBPath = configPath = pathTextField.getText();
                if (isWindows())
                {
                    configPath = configPath + "/config";
                }
                if (isMac())
                {
                    configPath = configPath + "/config";
                }

                
                if (!StringUtils.isEmpty(dynamoDBPath))
                {
                    DynamoDBUtility util = new DynamoDBUtility(configPath);
                    if (!util.isDynamoDBLocalRunning(configPath))
                    {
                        try
                        {
                            // **** start minimized "cmd /c start /MIN start.bat"
                            // **** start hide "cmd /c start /B start.bat"
                            if (isWindows())
                            {
                                // @SuppressWarnings("unused")
                                String pathJava = dynamoDBPath.replace("/", "\\\\");
                                Runtime.getRuntime().exec("cmd /c start /MIN start.bat", null, new File(pathJava));
                                dynoRunning = true;
                            }
                            else
                            {
                                if (isMac())
                                {
                                    // @SuppressWarnings("unused")
                                    String cmd = "bash " + dynamoDBPath + "/start.sh";
                                    ProcessBuilder pb = new ProcessBuilder(cmd);
                                    pb.start();
                                    // Runtime.getRuntime().exec("bash start.sh", null, new File(dynamoDBPath));
                                    // Process p = Runtime.getRuntime().exec("cmd /c start /MIN start.sh", null, new File(dynamoDBPath));
                                    dynoRunning = true;
                                }
                            }

                        }
                        catch (Exception exc)
                        {
                            labelError.setText("EXCEPTION: " + exc.getMessage());
                            labelError.setForeground(Color.RED);
                            labelError.setVisible(true);
                            pack();
                            System.out.println("EXCEPTION: " + exc.getMessage());
                        }
                    }
                    else
                    {
                        dynoRunning = true;
                    }
                }
                else
                {
                    labelError.setText("Path is required");
                    labelError.setForeground(Color.RED);
                    labelError.setVisible(true);
                    pack();
                    System.out.println("Path is required");
                }
                */
            }
        });

        return buttonStartLottery;
    }

    private JButton buttonCancel()
    {
        buttonCancel = new JButton("Cancel");

        buttonCancel.addActionListener(new ActionListener()
        {
            @Override
			public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });

        return buttonCancel;
    }

    private JButton buttonBrowse()
    {
        buttonBrowse = new JButton("Browse");

        buttonBrowse.addActionListener(new ActionListener()
        {
            @Override
			public void actionPerformed(ActionEvent event)
            {
            	// ==== 
            	/*
            	FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
                dialog.setMode(FileDialog.LOAD);
                dialog.setVisible(true);
                dialog.setDirectory("C:\\$Warring Associates LLC\\Contracts\\BiteMe");
                String file = dialog.getFile();
                String filepathname = dialog.getDirectory() + "\\" + file;
            	*/
            	// ====
            	
            	labelError.setVisible(false);
            	
                chooser = new JFileChooser();
                //chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setCurrentDirectory(new java.io.File("C:\\$Warring Associates LLC\\Contracts\\BiteMe"));
                chooser.setDialogTitle(choosertitle);
                //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                //
                // disable the "All files" option.
                //
                chooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
                chooser.setFileFilter(filter);
                if (chooser.showOpenDialog(newPanel) == JFileChooser.APPROVE_OPTION)
                {
                    // pathTextField.setText(chooser.getCurrentDirectory().getPath());
                    pathTextField.setText(chooser.getSelectedFile().getPath());
                    // System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                    System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
                }
                else
                {
                    System.out.println("No Selection ");
                }
            }
        });

        return buttonBrowse;
    }
    
    private boolean isValidCsv(String value){
		boolean answer = false;
		
		String patternStr = RegXPatternType.CSV_FILE_PATTERN.getPattern();
		// Create a Pattern object
		Pattern pattern = Pattern.compile(patternStr);
		
		// Now create matcher object.
		Matcher m = pattern.matcher(value);
		if (m.find( )) {
			answer = true;
		}
		
		return answer;
    }
    
    @SuppressWarnings("unused")
	private boolean isWindows()
    {
        return OperatingSystemType.isWindows(OS);
    }

    @SuppressWarnings("unused")
    private boolean isMac()
    {
        return OperatingSystemType.isMac(OS);
    }

    @SuppressWarnings("unused")
    private boolean isUnix()
    {
        return OperatingSystemType.isUnix(OS);
    }

    @SuppressWarnings("unused")
    private boolean isSolaris()
    {
        return OperatingSystemType.isSolaris(OS);
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
                new PrimaryLotteryFrame().setVisible(true);
            }
        });

	}

}
