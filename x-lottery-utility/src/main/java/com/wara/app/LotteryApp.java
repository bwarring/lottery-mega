package com.wara.app;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.wara.app.domain.LotteryPick;
import com.wara.app.domain.MegaMillionNumbers;
import com.wara.app.view.LotteryPickView;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 6:46:17 AM
 */
public class LotteryApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif", "csv");
        chooser.setFileFilter(filter);
        
        File fileDir = new File("C:\\$Warring Associates LLC\\Contracts\\BiteMe");
        chooser.setCurrentDirectory(fileDir);
        //chooser.setCurrentDirectory(new File  
        //		(System.getProperty("user.home") + System.getProperty("file.separator")+ "Music"));
        
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
        */
        
		LotteryApp app = new LotteryApp();
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        dialog.setDirectory("C:\\$Warring Associates LLC\\Contracts\\BiteMe");
        String file = dialog.getFile();
        String filepathname = dialog.getDirectory() + "\\" + file;
        System.out.println(file + " chosen.\n\n");
        
        Map<String, List<LotteryPick>> numbersMap = app.loadNumbersMap(new File(filepathname));
        //System.out.println("Lottery Picks");
        
        LotteryPickView view = new LotteryPickView(numbersMap);
        view.toView();
        
        /*
        for (String key : numbersMap.keySet()){
        	LotteryPick lotteryPick = numbersMap.get(key);
        	System.out.println(key + ": " + lotteryPick.toString());
        }
        */
        
        System.exit(0);

	}
	
	@SuppressWarnings("static-access")
	private Map<String, List<LotteryPick>> loadNumbersMap(File file){
		Boolean firstLine = Boolean.TRUE;
		Integer row = 0;
		MegaMillionNumbers megaMillionNumbers;
		//Map<String, LotteryPick> numbersMap = new LinkedHashMap<>();
		Map<String, List<LotteryPick>> numbersListMap = new LinkedHashMap<>();
		
		try {
			Reader reader = new InputStreamReader(new FileInputStream(file));
		    CSVParser assetCsvParser = CSVFormat.EXCEL.newFormat(',').withQuote('"').parse(reader);
		    for (CSVRecord record : assetCsvParser) {
		        if (firstLine) {
		          firstLine = Boolean.FALSE;
		          continue;
		        }
		        megaMillionNumbers = this.createFromCsvRecord(record);
		        
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
	
/*	
	@SuppressWarnings("static-access")
	private Map<String, LotteryPick> x_loadNumbersMap(File file){
		Boolean firstLine = Boolean.TRUE;
		Integer row = 0;
		MegaMillionNumbers megaMillionNumbers;
		Map<String, LotteryPick> numbersMap = new LinkedHashMap<>();
		
		//BufferedReader fileReader = null;
		 
		try {
			//String line = "";
			//String filename = file.getName();
			//fileReader = new BufferedReader(new FileReader(filename));
			
			Reader reader = new InputStreamReader(new FileInputStream(file));
		    CSVParser assetCsvParser = CSVFormat.EXCEL.newFormat(',').withQuote('"').parse(reader);
		    for (CSVRecord record : assetCsvParser) {
		        if (firstLine) {
		          firstLine = Boolean.FALSE;
		          continue;
		        }
		        megaMillionNumbers = this.createFromCsvRecord(record);
		        if (numbersMap.get(megaMillionNumbers.getNumber1()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber1(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber1(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber1());
		        	lotteryPick.incrementCount();
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getNumber2()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber2(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber2(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber2());
		        	lotteryPick.incrementCount();
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getNumber3()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber3(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber3(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber3());
		        	lotteryPick.incrementCount();
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getNumber4()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber4(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber4(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber4());
		        	lotteryPick.incrementCount();
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getNumber5()) == null) {
		        	numbersMap.put(megaMillionNumbers.getNumber5(), new LotteryPick(LotteryPick.TYPE_SELECTED, megaMillionNumbers.getNumber5(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getNumber5());
		        	lotteryPick.incrementCount();
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getMegaball()) == null) {
		        	numbersMap.put(megaMillionNumbers.getMegaball(), new LotteryPick(LotteryPick.TYPE_MEGABALL, megaMillionNumbers.getMegaball(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getMegaball());
		        	lotteryPick.incrementCount();
		        }
		        
		        if (numbersMap.get(megaMillionNumbers.getMegaplier()) == null) {
		        	numbersMap.put(megaMillionNumbers.getMegaplier(), new LotteryPick(LotteryPick.TYPE_MEGAPLIER, megaMillionNumbers.getMegaplier(), new Integer("1")));
		        }else{
		        	LotteryPick lotteryPick = numbersMap.get(megaMillionNumbers.getMegaplier());
		        	lotteryPick.incrementCount();
		        }
		        
		        row += 1;
		    }
 
 
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		}
		return numbersMap;
	}
*/	
	
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
/*
	    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	    AssetEntity assetEntity = new AssetEntity();
	    assetEntity.setGeneralSequence(new GeneralSequenceEntity());

	    assetEntity.setDescription(lotDescription);
	    assetEntity.setEnd(format.parse(endDate.substring(0, 10)));
	    assetEntity.setBidIncrement(new BigDecimal(NumberUtils.toInt(increment)));
	    assetEntity.setStartingBid(new BigDecimal(NumberUtils.toInt(startingBid)));
	    assetEntity.setReserveBid(new BigDecimal(NumberUtils.toInt(reservePrice)));
	    assetEntity.setRetailPrice(new BigDecimal(NumberUtils.toInt(replacementPrice)));
	    assetEntity.setStatus(AssetStatus.DRAFT.name());
	    assetEntity.setName(lotName);
	    AssetCategoryEntity assetCategoryEntity = this.assetCategoryService.findOrCreateByName(lotName);
	    assetEntity.addCategory(assetCategoryEntity);
	    //AP-389
	    assetEntity.setWeight(weight);
	    assetEntity.setBoxLength(length);
	    assetEntity.setBoxWidth(width);
	    assetEntity.setBoxHeight(height);
	    assetEntity.setSizeDescription(size);

	    // assetEntity.addImage(this.imageService.findOrCreate(Boolean.TRUE, null, "Certificate of Authenticity", cert));//TODO return error rows
	    assetEntity.setType(AssetType.AUCTION.name());
	    //assetEntity.addImage(this.imageService.findOrCreate(Boolean.FALSE, null, null, lotImage));
	    return assetEntity;
	    */
	  }

}
