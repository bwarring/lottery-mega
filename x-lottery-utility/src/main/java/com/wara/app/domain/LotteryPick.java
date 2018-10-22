package com.wara.app.domain;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 8:37:10 AM
 */
public class LotteryPick {
	
	/**
	 * "S"
	 */
	static public String TYPE_SELECTED = "S";
	/**
	 * "MP"
	 */
	static public String TYPE_MEGAPLIER = "MP";
	/**
	 * "MB"
	 */
	static public String TYPE_MEGABALL = "MB";
	
	private String type;
	private String number;
	private Integer count;
	
	public LotteryPick(){
		super();
	}
	
	/**
	 * 
	 * @param type (String)
	 * @param number (String)
	 * @param count (Integer)
	 */
	public LotteryPick(String type, String number, Integer count){
		super();
		this.type=type;
		this.number=number;
		this.count=count;
	}
	
	public void incrementCount(){
		if (this.count == null){
			this.count = new Integer("1");
		}
		this.count = this.count.intValue() + 1;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("type: ");
		sb.append("\"" + this.getType() + "\"");
		sb.append(", ");
		
		sb.append("number: ");
		sb.append("\"" + this.getNumber() + "\"");
		sb.append(", ");
		
		sb.append("count: ");
		sb.append("\"" + this.getCount() + "\"");
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	

}
