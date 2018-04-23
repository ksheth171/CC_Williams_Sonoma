 //* Copyright (c) 2018 KS

package com.williamssonoma.shipping.model;

import java.util.Comparator;

/**
 * @author karna.sheth
 */

public class ZipCodeRange implements Comparator<ZipCodeRange>{

	private Integer startRange;
	private Integer endRange;
	
	public ZipCodeRange(){

	}
	
	public ZipCodeRange(Integer startRange, Integer endRange){
		this.startRange = startRange;
		this.endRange =  endRange;
	}
	
	public Integer getStartRange() {
		return startRange;
	}
	
	public void setStartRange(Integer startRange) {
		this.startRange = startRange;
	}
	
	public Integer getEndRange() {
		return endRange;
	}
	
	public void setEndRange(Integer endRange) {
		this.endRange = endRange;
	}

	public int compare(ZipCodeRange o1, ZipCodeRange o2) {
		// TODO Auto-generated method stub
		return o1.getStartRange() - o2.getStartRange();
	}
	
}
