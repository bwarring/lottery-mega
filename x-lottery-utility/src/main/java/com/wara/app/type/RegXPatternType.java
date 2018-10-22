package com.wara.app.type;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 13, 2018 9:27:57 AM
 */
public enum RegXPatternType {

	CSV_FILE_PATTERN(".+\\.csv"),
	
	LAT_LON_PATTERN("^-?([1-8]?[0-9]\\.{1}\\d{1,14}$|90\\.{1}0{1,14}$)")
	;
	
	private String pattern;
	
	RegXPatternType(String pattern){
		this.pattern=pattern;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
