package com.wara.app.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wara.app.domain.LotteryPick;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 9:54:31 AM
 */
public class LotteryPickView implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, List<LotteryPick>> numbersListMap;
	
	public LotteryPickView(){
		super();
	}
	
	public LotteryPickView(Map<String, List<LotteryPick>> numbersListMap){
		super();
		this.numbersListMap=numbersListMap;
	}
	
	public Map<String, Object> toView(){
		
		Map<String, Object> responseMap = new HashMap<>();
		
		//****
		List<LotteryPick> lotteryPickListSelected = new ArrayList<>();
		List<LotteryPick> lotteryPickListMultiplier = new ArrayList<>();
		List<LotteryPick> lotteryPickListMegaball = new ArrayList<>();
		for (String key : this.numbersListMap.keySet()){
			List<LotteryPick> lotteryPickList = this.numbersListMap.get(key);
			for (LotteryPick lotteryPick : lotteryPickList){
				if (lotteryPick.getType().equals(LotteryPick.TYPE_SELECTED)){
					lotteryPickListSelected.add(lotteryPick);
				}else
				if (lotteryPick.getType().equals(LotteryPick.TYPE_MEGABALL)){
					lotteryPickListMegaball.add(lotteryPick);
				}else
				if (lotteryPick.getType().equals(LotteryPick.TYPE_MEGAPLIER)){
					lotteryPickListMultiplier.add(lotteryPick);
				}
			}
		}
		//****
		
		/*
		List<LotteryPick> lotteryPickListSelected = new ArrayList<>();
		List<LotteryPick> lotteryPickListMultiplier = new ArrayList<>();
		List<LotteryPick> lotteryPickListMegaball = new ArrayList<>();
		for (Object obj : objectArray){
			LotteryPick lotteryPick = (LotteryPick)obj;
			if (lotteryPick.getType().equals(LotteryPick.TYPE_SELECTED)){
				lotteryPickListSelected.add((LotteryPick)obj);
			}else
			if (lotteryPick.getType().equals(LotteryPick.TYPE_MEGABALL)){
				lotteryPickListMegaball.add((LotteryPick)obj);
			}else
			if (lotteryPick.getType().equals(LotteryPick.TYPE_MEGAPLIER)){
				lotteryPickListMultiplier.add((LotteryPick)obj);
			}
		}
		*/
		
		//List<LotteryPick> lotteryPickList = Arrays.asList(lotteryPickArray);
		
		lotteryPickListSelected.sort(new Comparator<LotteryPick>() {
			@Override
			public int compare(LotteryPick o1, LotteryPick o2) {
				return o2.getCount() - o1.getCount();
			}
		});
		
		lotteryPickListMegaball.sort(new Comparator<LotteryPick>() {
			@Override
			public int compare(LotteryPick o1, LotteryPick o2) {
				return o2.getCount() - o1.getCount();
			}
		});
		
		lotteryPickListMultiplier.sort(new Comparator<LotteryPick>() {
			@Override
			public int compare(LotteryPick o1, LotteryPick o2) {
				return o2.getCount() - o1.getCount();
			}
		});
		
		responseMap.put(LotteryPick.TYPE_SELECTED, lotteryPickListSelected);
		responseMap.put(LotteryPick.TYPE_MEGABALL, lotteryPickListMegaball);
		responseMap.put(LotteryPick.TYPE_MEGAPLIER, lotteryPickListMultiplier);
		
		System.out.println("Lottery Picks - Selected");
        for (LotteryPick lotteryPick : lotteryPickListSelected){
        	System.out.println(lotteryPick.getNumber() + ": " + lotteryPick.toString());
        }
        
        System.out.println("\n\nLottery Picks - Megaball");
        for (LotteryPick lotteryPick : lotteryPickListMegaball){
        	System.out.println(lotteryPick.getNumber() + ": " + lotteryPick.toString());
        }
        
        System.out.println("\n\nLottery Picks - Multiplier");
        for (LotteryPick lotteryPick : lotteryPickListMultiplier){
        	System.out.println(lotteryPick.getNumber() + ": " + lotteryPick.toString());
        }
        
        return responseMap;
	}

}
