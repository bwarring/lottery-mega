package com.wara.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wara.app.domain.LotteryPick;
import com.wara.app.domain.MegaMillionNumbers;
import com.wara.app.view.LotteryPickListView;
import com.wara.app.view.LotteryPickView;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 3:01:32 PM
 */
public class ListLotteryItemsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
    private JPanel buttonPanel = new JPanel(new GridBagLayout());
    private JButton buttonSubmit = buttonSubmit();
    private JButton buttonCancel = buttonCancel();
    private JLabel lotteryNameLabel = new JLabel("Lottery Name: ");
    private JTextField filePathNameValue = new JTextField("File", 50);
    private JLabel labelError = new JLabel(" ");

    private JPanel dataPanel = new JPanel(new GridBagLayout());
    private JPanel itemListPanel;
    private JTextArea dataTable;
    private JScrollPane listTableItemsScrollPane;
    private JButton buttonExit = buttonExit();
    private String filepathname;
    private List<String> lotteryItems;
    private boolean hdgraphic = false;
    
    public ListLotteryItemsPanel()
    {
        super();
    }

    public ListLotteryItemsPanel(String filepathname, boolean hdgraphic)
    {
        super();
        this.filepathname = filepathname;
        this.hdgraphic=hdgraphic;
        init(hdgraphic);
    }

    private void init(boolean hdgraphic)
    {
        Color veryLightGray = new Color(243, 243, 250);

        dataPanel.setBackground(veryLightGray);
        dataPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "List Lottery Items"));
        
        if(hdgraphic)
        {
            //1200, 400
            dataPanel.setMaximumSize(new Dimension(1200, 400));
            dataPanel.setMinimumSize(new Dimension(1200, 400));
            dataPanel.setPreferredSize(new Dimension(1200, 400));
        }
        else
        {
            dataPanel.setMaximumSize(new Dimension(500, 200));
            dataPanel.setMinimumSize(new Dimension(500, 200));
            dataPanel.setPreferredSize(new Dimension(500, 200));
        }

        DocumentFilter filter = new UppercaseDocumentFilter();
        ((AbstractDocument) filePathNameValue.getDocument()).setDocumentFilter(filter);
        filePathNameValue = new JTextField(this.filepathname, 50);
        //filePathNameValue.setText(this.filepathname);
        
        // ====
        if (!StringUtils.isEmpty(getFilePathName()))
        {
            try
            {
                itemListPanel = getItemListPanel();
                // dataPanel.setPreferredSize(new Dimension(800, 400));
                veryLightGray = new Color(243, 243, 250);
                itemListPanel.setBackground(veryLightGray);
                itemListPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Table Items Tables"));

                if(hdgraphic)
                {
                    itemListPanel.setMaximumSize(new Dimension(2000, 1400));
                    itemListPanel.setMinimumSize(new Dimension(2000, 1400));
                    itemListPanel.setPreferredSize(new Dimension(2000, 1400));
                }
                
                JPanel buttonPanel = new JPanel(new GridBagLayout());
                buttonPanel.setBackground(veryLightGray);

                GridBagConstraints buttonConstraints = new GridBagConstraints();
                buttonConstraints.insets = new Insets(10, 10, 10, 10);
                buttonConstraints.gridx = 0;
                buttonConstraints.gridy = 2;
                buttonConstraints.gridwidth = 1;
                buttonPanel.add(buttonExit, buttonConstraints);

                GridBagConstraints buttonPanelConstraints = new GridBagConstraints();
                buttonPanelConstraints.insets = new Insets(10, 10, 10, 10);
                buttonPanelConstraints.gridx = 0;
                buttonPanelConstraints.gridy = 3;
                buttonPanelConstraints.gridwidth = 2;
                itemListPanel.add(buttonPanel, buttonPanelConstraints);

                /**
                 * RETRIEVE TABLE ITEMS
                 */
                dataList(getFilePathName());
                if(hdgraphic)
                {
                    dataTable.setFont(new Font("SansSerif", Font.PLAIN, 24));
                }
                listTableItemsScrollPane = new JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                listTableItemsScrollPane.setMinimumSize(new Dimension(1800, 1200));
                listTableItemsScrollPane.setPreferredSize(new Dimension(1800, 1200));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.anchor = GridBagConstraints.LINE_START;
                itemListPanel.add(listTableItemsScrollPane, gbc);

                dataPanel.setVisible(false);
                itemListPanel.setVisible(true);
                add(itemListPanel);
            }
            catch (Exception exc)
            {
                labelError.setText("EXCEPTION: " + exc.getMessage());
                labelError.setForeground(Color.RED);
                labelError.setVisible(true);
                validate();
            }
        }
        else
        {
            labelError.setText("Table name is required");
            labelError.setForeground(Color.RED);
            labelError.setVisible(true);
            validate();
        }
        // ====

        GridBagConstraints gbc_1 = new GridBagConstraints();
        gbc_1.insets = new Insets(10, 10, 10, 10);
        gbc_1.gridx = 0;
        gbc_1.gridy = 0;
        // dataEntryPanel.add(tableNameLabel, gbc_1);
        dataPanel.add(lotteryNameLabel, gbc_1);

        GridBagConstraints gbc_2 = new GridBagConstraints();
        gbc_2.insets = new Insets(10, 10, 10, 10);
        gbc_2.gridx = 1;
        gbc_2.gridy = 0;
        gbc_2.gridwidth = 2;
        // dataEntryPanel.add(tableNameValue, gbc_2);
        dataPanel.add(filePathNameValue, gbc_2);

        GridBagConstraints gbc_3 = new GridBagConstraints();
        gbc_3.insets = new Insets(10, 10, 10, 10);
        gbc_3.gridx = 0;
        gbc_3.gridy = 1;
        gbc_3.gridwidth = 3;
        // dataEntryPanel.add(labelError, gbc_3);
        labelError.setForeground(Color.RED);
        labelError.setVisible(false);
        dataPanel.add(labelError, gbc_3);

        GridBagConstraints buttonConstraints_1 = new GridBagConstraints();
        buttonConstraints_1.insets = new Insets(10, 10, 10, 10);

        // add submit button to button panel
        buttonConstraints_1.gridx = 0;
        buttonConstraints_1.gridy = 2;
        buttonConstraints_1.gridwidth = 1;
        buttonPanel.add(buttonSubmit, buttonConstraints_1);

        // add cancel button to button panel
        GridBagConstraints buttonConstraints_2 = new GridBagConstraints();
        buttonConstraints_2.insets = new Insets(10, 10, 10, 10);
        buttonConstraints_2.gridx = 1;
        buttonConstraints_2.gridy = 2;
        buttonConstraints_2.gridwidth = 1;
        buttonPanel.add(buttonCancel, buttonConstraints_2);

        // add button panel to data panel
        GridBagConstraints gbc_4 = new GridBagConstraints();
        gbc_4.gridx = 2;
        gbc_4.gridy = 3;
        gbc_4.gridwidth = 2;
        // dataEntryPanel.add(buttonPanel, gbc_4);
        // dataEntryPanel.setPreferredSize(new Dimension(600, 400));
        buttonPanel.setBackground(veryLightGray);
        dataPanel.add(buttonPanel, gbc_4);

        // add the panel to this panel
        add(dataPanel);
    }

    private JButton buttonExit()
    {
        buttonExit = new JButton("Exit");

        buttonExit.addActionListener(new ActionListener()
        {
            @Override
			public void actionPerformed(ActionEvent event)
            {
                listTableItemsScrollPane.setVisible(false);
                dataTable.setText(null);
                setVisible(false);
            }
        });

        return buttonExit;
    }

    @SuppressWarnings("unchecked")
	private JTextArea dataList(String filepathname)
    {
    	//Map<String, LotteryPick> numbersMap = loadNumbersMap(new File(filepathname));
    	Map<String, List<LotteryPick>> numbersListMap = loadNumbersMap(new File(filepathname));
    	
    	LotteryPickView view = new LotteryPickView(numbersListMap);
        Map<String, Object> response = view.toView();
        
        List<LotteryPick> selectedList = (response.get(LotteryPick.TYPE_SELECTED) != null) ? (List<LotteryPick>)response.get(LotteryPick.TYPE_SELECTED) : new ArrayList<LotteryPick>();
        List<LotteryPick> megaballList = (response.get(LotteryPick.TYPE_MEGABALL) != null) ? (List<LotteryPick>)response.get(LotteryPick.TYPE_MEGABALL) : new ArrayList<LotteryPick>();
        List<LotteryPick> megaplierList = (response.get(LotteryPick.TYPE_MEGAPLIER) != null) ? (List<LotteryPick>)response.get(LotteryPick.TYPE_MEGAPLIER) : new ArrayList<LotteryPick>();
    	
        // build selected counter list
        Map<String, LotteryPick> lotteryNumberMap = new LinkedHashMap<>();
        List<LotteryPick> lotteryPickSelectedList = new ArrayList<>();
        for (LotteryPick lotteryPick : selectedList){
        	if (lotteryNumberMap.get(lotteryPick.getNumber()) == null){
        		lotteryNumberMap.put(lotteryPick.getNumber(), lotteryPick);
        	}else{
        		lotteryNumberMap.get(lotteryPick.getNumber()).incrementCount();
        	}
        }
        Collection<LotteryPick> lotteryPickCollection = lotteryNumberMap.values();
        for (Object obj : lotteryPickCollection){
        	lotteryPickSelectedList.add((LotteryPick)obj);
        }
        lotteryPickSelectedList.sort(new Comparator<LotteryPick>() {
			@Override
			public int compare(LotteryPick o1, LotteryPick o2) {
				return o2.getCount() - o1.getCount();
			}
		});
        
        // build megaball counter list
        lotteryNumberMap = new LinkedHashMap<>();
        List<LotteryPick> lotteryPickMegaballList = new ArrayList<>();
        for (LotteryPick lotteryPick : megaballList){
        	if (lotteryNumberMap.get(lotteryPick.getNumber()) == null){
        		lotteryNumberMap.put(lotteryPick.getNumber(), lotteryPick);
        	}else{
        		lotteryNumberMap.get(lotteryPick.getNumber()).incrementCount();
        	}
        }
        lotteryPickCollection = lotteryNumberMap.values();
        for (Object obj : lotteryPickCollection){
        	lotteryPickMegaballList.add((LotteryPick)obj);
        }
        lotteryPickMegaballList.sort(new Comparator<LotteryPick>() {
			@Override
			public int compare(LotteryPick o1, LotteryPick o2) {
				return o2.getCount() - o1.getCount();
			}
		});
        
        // build megaplier counter list
        lotteryNumberMap = new LinkedHashMap<>();
        List<LotteryPick> lotteryPickMegaplierList = new ArrayList<>();
        for (LotteryPick lotteryPick : megaplierList){
        	if (lotteryNumberMap.get(lotteryPick.getNumber()) == null){
        		lotteryNumberMap.put(lotteryPick.getNumber(), lotteryPick);
        	}else{
        		lotteryNumberMap.get(lotteryPick.getNumber()).incrementCount();
        	}
        }
        lotteryPickCollection = lotteryNumberMap.values();
        for (Object obj : lotteryPickCollection){
        	lotteryPickMegaplierList.add((LotteryPick)obj);
        }
        lotteryPickMegaplierList.sort(new Comparator<LotteryPick>() {
			@Override
			public int compare(LotteryPick o1, LotteryPick o2) {
				return o2.getCount() - o1.getCount();
			}
		});
        
        dataTable = new JTextArea();
        String numbersListView = "";
        StringBuffer sb = new StringBuffer();
        sb.append("LOTTERY NUMBERS : " + filepathname);
        sb.append("\n\n");
        
        LotteryPickListView lotteryPickListView = new LotteryPickListView();
        sb.append("SELECTED NUMBERS\n");
        numbersListView = sb.toString();
        //sb.append("{\"numbers\": \n[");
        if (!lotteryPickSelectedList.isEmpty()){
        	//int cnt = 1;
        	for (LotteryPick lotteryPick : lotteryPickSelectedList)
	        {
        		lotteryPickListView.add(lotteryPick);
        		/*
        		String json = toJson(lotteryPick, false);
        		sb.append(json);
	            //sb.append(lotteryPick.toString());
        		if (cnt < lotteryPickSelectedList.size()){
        			cnt++;
        			sb.append(",\n");
        		}else{
        			sb.append("\n]}\n");
        		}
        		*/
	        }
	        //numbersListView = sb.toString();
        }else{
        	numbersListView = "SELECTED NUMBERS - NONE";
        }
        //numbersListView = numbersListView + "\n\n";
        numbersListView = numbersListView + toJson(lotteryPickListView, true);
        numbersListView = numbersListView + "\n\n";
        
        sb = new StringBuffer();
        lotteryPickListView.getLotteryPicks().clear();
        sb.append("MEGABALL NUMBERS\n");
        numbersListView = numbersListView + sb.toString();
        //sb.append("{\"numbers\": \n[");
        if (!lotteryPickMegaballList.isEmpty()){
        	//int cnt = 1;
        	for (LotteryPick lotteryPick : lotteryPickMegaballList)
	        {
        		lotteryPickListView.add(lotteryPick);
        		/*
        		String json = toJson(lotteryPick, false);
        		sb.append(json);
	            //sb.append(lotteryPick.toString());
        		if (cnt < lotteryPickMegaballList.size()){
        			cnt++;
        			sb.append(",\n");
        		}else{
        			sb.append("\n]}\n");
        		}
        		*/
	        }
	        //numbersListView = numbersListView + sb.toString();
        }else{
        	numbersListView = numbersListView + "MEGABALL NUMBERS - NONE";
        }
        numbersListView = numbersListView + toJson(lotteryPickListView, true);
        numbersListView = numbersListView + "\n\n";
        
        sb = new StringBuffer();
        lotteryPickListView.getLotteryPicks().clear();
        sb.append("MULTIPLIER NUMBERS\n");
        numbersListView = numbersListView + sb.toString();
        //sb.append("{\"numbers\": \n[");
        if (!lotteryPickMegaplierList.isEmpty()){
        	//int cnt = 1;
        	for (LotteryPick lotteryPick : lotteryPickMegaplierList)
	        {
        		lotteryPickListView.add(lotteryPick);
        		/*
        		String json = toJson(lotteryPick, false);
        		sb.append(json);
	            //sb.append(lotteryPick.toString());
        		if (cnt < lotteryPickMegaplierList.size()){
        			cnt++;
        			sb.append(",\n");
        		}else{
        			sb.append("\n]}\n");
        		}
        		*/
	        }
	        //numbersListView = numbersListView + sb.toString();
        }else{
        	numbersListView = numbersListView + "MEGABALL NUMBERS - NONE";
        }
        numbersListView = numbersListView + toJson(lotteryPickListView, true);
        dataTable.setText(numbersListView);

        return dataTable;
    }

    private JButton buttonSubmit()
    {
        buttonSubmit = new JButton("Submit");

        buttonSubmit.addActionListener(new ActionListener()
        {
            @Override
			public void actionPerformed(ActionEvent event)
            {
                // getDataEntryPanel().setVisible(false);
                setFilePathName(filePathNameValue.getText());
                if (!StringUtils.isEmpty(getFilePathName()))
                {
                    try
                    {
                        itemListPanel = getItemListPanel();
                        // dataPanel.setPreferredSize(new Dimension(800, 400));
                        Color veryLightGray = new Color(243, 243, 250);
                        itemListPanel.setBackground(veryLightGray);
                        itemListPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Table Items Tables"));

                        if(hdgraphic)
                        {
                            itemListPanel.setMaximumSize(new Dimension(2000, 1400));
                            itemListPanel.setMinimumSize(new Dimension(2000, 1400));
                            itemListPanel.setPreferredSize(new Dimension(2000, 1400));
                        }
                        //else
                        //{
                        //    itemListPanel.setMaximumSize(new Dimension(500, 200));
                        //    itemListPanel.setMinimumSize(new Dimension(500, 200));
                        //    itemListPanel.setPreferredSize(new Dimension(500, 200));
                        //}
                        
                        JPanel buttonPanel = new JPanel(new GridBagLayout());
                        buttonPanel.setBackground(veryLightGray);

                        GridBagConstraints buttonConstraints = new GridBagConstraints();
                        buttonConstraints.insets = new Insets(10, 10, 10, 10);
                        buttonConstraints.gridx = 0;
                        buttonConstraints.gridy = 2;
                        buttonConstraints.gridwidth = 1;
                        buttonPanel.add(buttonExit, buttonConstraints);

                        GridBagConstraints buttonPanelConstraints = new GridBagConstraints();
                        buttonPanelConstraints.insets = new Insets(10, 10, 10, 10);
                        buttonPanelConstraints.gridx = 0;
                        buttonPanelConstraints.gridy = 3;
                        buttonPanelConstraints.gridwidth = 2;
                        itemListPanel.add(buttonPanel, buttonPanelConstraints);

                        /**
                         * RETRIEVE TABLE ITEMS
                         */
                        dataList(getFilePathName());
                        if(hdgraphic)
                        {
                            dataTable.setFont(new Font("SansSerif", Font.PLAIN, 24));
                        }
                        listTableItemsScrollPane = new JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        //listTableItemsScrollPane.setMinimumSize(new Dimension(800, 400));
                        //listTableItemsScrollPane.setPreferredSize(new Dimension(800, 400));
                        listTableItemsScrollPane.setMinimumSize(new Dimension(1800, 1200));
                        listTableItemsScrollPane.setPreferredSize(new Dimension(1800, 1200));

                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.insets = new Insets(5, 5, 5, 5);
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        gbc.gridheight = 1;
                        gbc.gridwidth = 1;
                        gbc.anchor = GridBagConstraints.LINE_START;
                        itemListPanel.add(listTableItemsScrollPane, gbc);

                        dataPanel.setVisible(false);
                        itemListPanel.setVisible(true);
                        add(itemListPanel);
                        // dataPanel.setVisible(true);
                        // getDataPanel().setVisible(true);
                    }
                    catch (Exception exc)
                    {
                        labelError.setText("EXCEPTION: " + exc.getMessage());
                        labelError.setForeground(Color.RED);
                        labelError.setVisible(true);
                        validate();
                        // pack();
                        // System.out.println("EXCEPTION: " + exc.getMessage());
                    }
                }
                else
                {
                    labelError.setText("Table name is required");
                    labelError.setForeground(Color.RED);
                    labelError.setVisible(true);
                    validate();
                    // pack();
                    // System.out.println("Path is required");
                }
            }
        });

        return buttonSubmit;
    }

    private JButton buttonCancel()
    {
        buttonCancel = new JButton("Cancel");

        buttonCancel.addActionListener(new ActionListener()
        {
            @Override
			public void actionPerformed(ActionEvent event)
            {
                setVisible(false);
            }
        });

        return buttonCancel;
    }

    /**
     * @return the lotteryItems
     */
    public List<String> getLotteryItems()
    {
        return lotteryItems;
    }

    /**
     * @param lotteryItems
     *            the lotteryItems to set
     */
    public void setLotteryItems(List<String> lotteryItems)
    {
        this.lotteryItems = lotteryItems;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
			public void run()
            {
                ListLotteryItemsPanel coreTablesPanel = new ListLotteryItemsPanel();
                coreTablesPanel.setLotteryItems(new ArrayList<String>());

                JPanel dataPanel = new JPanel(new GridBagLayout());
                dataPanel.setBackground(Color.GREEN);
                dataPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Core Tables"));

                JTextArea coreTable = coreTablesPanel.dataList("LOTTERY ITEMS");
                JScrollPane listCoreTablesScrollPane = new JScrollPane(coreTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                listCoreTablesScrollPane.setMinimumSize(new Dimension(400, 200));
                listCoreTablesScrollPane.setPreferredSize(new Dimension(400, 200));
                dataPanel.add(listCoreTablesScrollPane);

                JPanel buttonPanel = new JPanel(new GridBagLayout());
                GridBagConstraints buttonConstraints = new GridBagConstraints();
                buttonConstraints.insets = new Insets(10, 10, 10, 10);
                buttonConstraints.gridx = 0;
                buttonConstraints.gridy = 2;
                buttonConstraints.gridwidth = 1;
                buttonPanel.add(coreTablesPanel.buttonExit, buttonConstraints);

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.gridx = 0;
                constraints.gridy = 3;
                constraints.gridwidth = 2;
                dataPanel.add(buttonPanel, constraints);

                JFrame frame = new JFrame("Scroll Pane Test");
                frame.getContentPane().add(dataPanel, BorderLayout.CENTER);

                // make it easy to close the application
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // set the frame size (you'll usually want to call frame.pack())
                // frame.setSize(new Dimension(600, 250));

                // center the frame
                frame.setLocationRelativeTo(null);

                frame.pack();

                // make it visible to the user
                frame.setVisible(true);

                // frame.add(dataPanel);
                // frame.setSize(new Dimension(600, 250));
                // frame.pack();
                // frame.setLocationRelativeTo(null);
                // frame.setVisible(true);
            }
        });

    }
    
    @SuppressWarnings("static-access")
	private Map<String, List<LotteryPick>> loadNumbersMap(File file){
		Boolean firstLine = Boolean.TRUE;
		Integer row = 0;
		MegaMillionNumbers megaMillionNumbers;
		//Map<String, LotteryPick> numbersMap = new LinkedHashMap<>();
		Map<String, List<LotteryPick>> numbersListMap = new LinkedHashMap<>();
		Map<String, List<MegaMillionNumbers>> firstNumberListMap = new LinkedHashMap<>();
		
		try {
			Reader reader = new InputStreamReader(new FileInputStream(file));
		    CSVParser assetCsvParser = CSVFormat.EXCEL.newFormat(',').withQuote('"').parse(reader);
		    for (CSVRecord record : assetCsvParser) {
		        if (firstLine) {
		          firstLine = Boolean.FALSE;
		          continue;
		        }
		        megaMillionNumbers = this.createFromCsvRecord(record);
		        
		        // first number map
		        doFirstNumberMap("2", firstNumberListMap, megaMillionNumbers);
		        // end first number map
		        
		        
		        if (numbersListMap.containsKey(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber1())){
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber1()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber1(), new Integer("1")));
		        }else{
		        	numbersListMap.put(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber1(), new ArrayList<LotteryPick>());
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber1()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber1(), new Integer("1")));
		        }
		        	
	        	if (numbersListMap.containsKey(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber2())){
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber2()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber2(), new Integer("1")));
		        }else{
		        	numbersListMap.put(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber2(), new ArrayList<LotteryPick>());
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber2()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber2(), new Integer("1")));
		        }
		        	
		        	
	        	if (numbersListMap.containsKey(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber3())){
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber3()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber3(), new Integer("1")));
		        }else{
		        	numbersListMap.put(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber3(), new ArrayList<LotteryPick>());
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber3()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber3(), new Integer("1")));
		        }
	        	
	        	if (numbersListMap.containsKey(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber4())){
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber4()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber4(), new Integer("1")));
		        }else{
		        	numbersListMap.put(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber4(), new ArrayList<LotteryPick>());
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber4()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber4(), new Integer("1")));
		        }
	        	
	        	if (numbersListMap.containsKey(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber5())){
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber5()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber5(), new Integer("1")));
		        }else{
		        	numbersListMap.put(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber5(), new ArrayList<LotteryPick>());
		        	numbersListMap.get(LotteryPick.TYPE_SELECTED + megaMillionNumbers.getNumber5()).add(new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber5(), new Integer("1")));
		        }
	        	
	        	if (numbersListMap.containsKey(LotteryPick.TYPE_MEGABALL + megaMillionNumbers.getMegaball())){
		        	numbersListMap.get(LotteryPick.TYPE_MEGABALL + megaMillionNumbers.getMegaball()).add(new LotteryPick(LotteryPick.TYPE_MEGABALL, megaMillionNumbers.getMegaball(), new Integer("1")));
		        }else{
		        	numbersListMap.put(LotteryPick.TYPE_MEGABALL + megaMillionNumbers.getMegaball(), new ArrayList<LotteryPick>());
		        	numbersListMap.get(LotteryPick.TYPE_MEGABALL + megaMillionNumbers.getMegaball()).add(new LotteryPick(LotteryPick.TYPE_MEGABALL, megaMillionNumbers.getMegaball(), new Integer("1")));
		        }
	        	
	        	if (numbersListMap.containsKey(LotteryPick.TYPE_MEGAPLIER + megaMillionNumbers.getMegaplier())){
		        	numbersListMap.get(LotteryPick.TYPE_MEGAPLIER + megaMillionNumbers.getMegaplier()).add(new LotteryPick(LotteryPick.TYPE_MEGAPLIER, megaMillionNumbers.getMegaplier(), new Integer("1")));
		        }else{
		        	numbersListMap.put(LotteryPick.TYPE_MEGAPLIER + megaMillionNumbers.getMegaplier(), new ArrayList<LotteryPick>());
		        	numbersListMap.get(LotteryPick.TYPE_MEGAPLIER + megaMillionNumbers.getMegaplier()).add(new LotteryPick(LotteryPick.TYPE_MEGAPLIER, megaMillionNumbers.getMegaplier(), new Integer("1")));
		        }
	        	
		        /*
		        if (numbersMap.get(megaMillionNumbers.getNumber1()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber1(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber1(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber1());
		        	if (lotteryPick.getType().equals(LotteryPick.TYPE_SELECTED)){
		        		lotteryPick.incrementCount();
		        	}else{
		        		numbersMap.put(megaMillionNumbers.getNumber1(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber1(), new Integer("1")));
		        	}
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getNumber2()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber2(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber2(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber2());
		        	if (lotteryPick.getType().equals(LotteryPick.TYPE_SELECTED)){
		        		lotteryPick.incrementCount();
		        	}else{
		        		numbersMap.put(megaMillionNumbers.getNumber2(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber2(), new Integer("1")));
		        	}
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getNumber3()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber3(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber3(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber3());
		        	if (lotteryPick.getType().equals(LotteryPick.TYPE_SELECTED)){
		        		lotteryPick.incrementCount();
		        	}else{
		        		numbersMap.put(megaMillionNumbers.getNumber3(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber3(), new Integer("1")));
		        	}
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getNumber4()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber4(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber4(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber4());
		        	if (lotteryPick.getType().equals(LotteryPick.TYPE_SELECTED)){
		        		lotteryPick.incrementCount();
		        	}else{
		        		numbersMap.put(megaMillionNumbers.getNumber4(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber4(), new Integer("1")));
		        	}
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getNumber5()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber5(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber5(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber5());
		        	if (lotteryPick.getType().equals(LotteryPick.TYPE_SELECTED)){
		        		lotteryPick.incrementCount();
		        	}else{
		        		numbersMap.put(megaMillionNumbers.getNumber5(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber5(), new Integer("1")));
		        	}
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getMegaball()) == null) {
		        	numbersMap.put(megaMillionNumbers.getMegaball(), new LotteryPick(LotteryPick.TYPE_MEGABALL, megaMillionNumbers.getMegaball(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getMegaball());
		        	if (lotteryPick.getType().equals(LotteryPick.TYPE_MEGABALL)){
		        		lotteryPick.incrementCount();
		        	}else{
		        		numbersMap.put(megaMillionNumbers.getMegaball(), new LotteryPick(LotteryPick.TYPE_MEGABALL, megaMillionNumbers.getMegaball(), new Integer("1")));
		        	}
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getMegaplier()) == null) {
		        	numbersMap.put(megaMillionNumbers.getMegaplier(), new LotteryPick(LotteryPick.TYPE_MEGAPLIER, megaMillionNumbers.getMegaplier(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getMegaplier());
		        	if (lotteryPick.getType().equals(LotteryPick.TYPE_MEGAPLIER)){
		        		lotteryPick.incrementCount();
		        	}else{
		        		numbersMap.put(megaMillionNumbers.getMegaplier(), new LotteryPick(LotteryPick.TYPE_MEGAPLIER, megaMillionNumbers.getMegaplier(), new Integer("1")));
		        	}
		        }
		        */
		        
		        row += 1;
		    }
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} 
		return numbersListMap;
	}
    
    private MegaMillionNumbers createFromCsvRecord(final CSVRecord csvRecord) throws ParseException {
	    String month = csvRecord.get(1);
	    String day = csvRecord.get(2);
	    String year = csvRecord.get(3);
	    String number1 = csvRecord.get(4);
	    String number2 = csvRecord.get(5);
	    String number3 = csvRecord.get(6);
	    String number4 = csvRecord.get(7);
	    String number5 = csvRecord.get(8);
	    String megaball = csvRecord.get(9);
	    String megaplier = csvRecord.get(10);
	    String jackpot = csvRecord.get(11);
	    
	    MegaMillionNumbers numbers = new MegaMillionNumbers();
	    numbers.setMonth(month);
	    numbers.setDay(day);
	    numbers.setYear(year);
	    numbers.setNumber1(number1);
	    numbers.setNumber2(number2);
	    numbers.setNumber3(number3);
	    numbers.setNumber4(number4);
	    numbers.setNumber5(number5);
	    numbers.setMegaball(megaball);
	    numbers.setMegaplier(megaplier);
	    numbers.setJackpot(jackpot);
	    
	    return numbers;
	  }
    
    // create map based on number selected in first lottery pick position
    private void doFirstNumberMap(String number, Map<String, List<MegaMillionNumbers>> firstNumberListMap, MegaMillionNumbers megaMillionNumbers){
    	// first number map
    	String keyNumber = (StringUtils.isBlank(number)) ? "1" : number;
    	
    	if (!keyNumber.equals(megaMillionNumbers.getNumber1())){
    		return;
    	}
    	
    	if (firstNumberListMap.containsKey(LotteryPick.TYPE_SELECTED + keyNumber)){
    		firstNumberListMap.get(LotteryPick.TYPE_SELECTED + keyNumber).add(megaMillionNumbers);
        	//firstNumberListMap.get(LotteryPick.TYPE_SELECTED + keyNumber).add(new LotteryPick(LotteryPick.TYPE_SELECTED, keyNumber, new Integer("1")));
        }else{
        	firstNumberListMap.put(LotteryPick.TYPE_SELECTED + keyNumber, new ArrayList<MegaMillionNumbers>());
        	firstNumberListMap.get(LotteryPick.TYPE_SELECTED + keyNumber).add(megaMillionNumbers);
        }
        // end first number map
    }
    
    public String toJson(Object object, boolean prettyPrinter)
    {
		ObjectWriter ow = (prettyPrinter)?new ObjectMapper().writer().withDefaultPrettyPrinter() : new ObjectMapper().writer();
        String json = null;
        try
        {
            json = ow.writeValueAsString(object);
        }
        catch (JsonProcessingException e)
        {
            return message(object, e.getMessage());
        }
        catch (Exception e)
        {
            return message(object, e.getMessage());
        }
        return json;

    }
    
    private String message(Object object, String exceptionMessage)
    {

        String className = "UNKNOWN CLASS NAME";
        if (object != null)
        {
            className = getClassName(object);
        }
        if (StringUtils.isEmpty(exceptionMessage))
        {
            exceptionMessage = "UNKNOWN EXCEPTION MESSAGE";
        }

        return message(className, exceptionMessage);
    }
    
    private static String getClassName(Object object)
    {
        String response = "UNKNOWN CLASS NAME";
        if (object == null)
        {
            return response;
        }

        if (object.getClass() != null)
        {
            response = object.getClass().getName();
        }
        return response;
    }

    /**
     * @return the filepathname
     */
    public String getFilePathName()
    {
        return filepathname;
    }

    /**
     * @param filepathname
     *            the filepathname to set
     */
    public void setFilePathName(String filepathname)
    {
        this.filepathname = filepathname;
    }

    /**
     * @return the dataEntryPanel
     */
    // public JPanel getDataEntryPanel()
    // {
    // return dataEntryPanel;
    // }

    /**
     * @param dataEntryPanel
     *            the dataEntryPanel to set
     */
    // public void setDataEntryPanel(JPanel dataEntryPanel)
    // {
    // this.dataEntryPanel = dataEntryPanel;
    // }

    /**
     * @return the dataPanel
     */
    public JPanel getDataPanel()
    {
        return dataPanel;
    }

    /**
     * @param dataPanel
     *            the dataPanel to set
     */
    public void setDataPanel(JPanel dataPanel)
    {
        this.dataPanel = dataPanel;
    }

    /**
     * @return the listTableItemsScrollPane
     */
    public JScrollPane getListTableItemsScrollPane()
    {
        return listTableItemsScrollPane;
    }

    /**
     * @param listTableItemsScrollPane
     *            the listTableItemsScrollPane to set
     */
    public void setListTableItemsScrollPane(JScrollPane listTableItemsScrollPane)
    {
        this.listTableItemsScrollPane = listTableItemsScrollPane;
    }

    /**
     * @return the itemListPanel
     */
    public JPanel getItemListPanel()
    {
        if (itemListPanel == null)
        {
            itemListPanel = new JPanel(new GridBagLayout());
        }
        return itemListPanel;
    }

    /**
     * @param itemListPanel
     *            the itemListPanel to set
     */
    public void setItemListPanel(JPanel itemListPanel)
    {
        this.itemListPanel = itemListPanel;
    }

}
