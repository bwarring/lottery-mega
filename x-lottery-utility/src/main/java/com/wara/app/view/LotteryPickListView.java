package com.wara.app.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wara.app.domain.LotteryPick;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 17, 2018 6:57:12 AM
 */
public class LotteryPickListView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="numbers")
	private List<LotteryPick> lotteryPicks;
	
	/**
	 * 
	 * @param lotteryPicks
	 */
	@JsonIgnore
	public void add(LotteryPick lotteryPick){
		this.getLotteryPicks().add(lotteryPick);
	}

	/**
	 * @return the lotteryPicks
	 */
	public List<LotteryPick> getLotteryPicks() {
		if (lotteryPicks==null){
			lotteryPicks = new ArrayList<>();
		}
		return lotteryPicks;
	}

	/**
	 * @param lotteryPicks the lotteryPicks to set
	 */
	public void setLotteryPicks(List<LotteryPick> lotteryPicks) {
		this.lotteryPicks = lotteryPicks;
	}
	
}
