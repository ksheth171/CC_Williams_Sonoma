// Copyright (c) 2018 KS

package com.williamssonoma.shipping.app;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import com.williamssonoma.shipping.model.ZipCodeRange;
import com.williamssonoma.shipping.utils.ZipCodeRangeUtils;

/**
 * @author karna.sheth
 */

public class App {
	public static final String SPLIT_REGEX = "\\s*]\\s*";
	public static final String REPLACE_REGEX = "\\[|\\]";
	static ArrayList<ZipCodeRange> zipCodeRanges = new ArrayList<ZipCodeRange>();
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter restrictd zip code ranges (For eg. [94133,94133] [94200,94299] [94600,94699]): ");
		String inputValue = br.readLine();

		// Split the input to get all the zip code ranges
		String[] inputRange = inputValue.split(SPLIT_REGEX);
		
		if (inputRange.length > 0) {
			prepareZipCodeList(inputRange);
		}

		//Sort the list
		Collections.sort(zipCodeRanges, new ZipCodeRange());

		// Consolidated zip code range list
		ZipCodeRangeUtils.consolidateOverlappingZipCodes(zipCodeRanges);
	
	}

	private static ArrayList<ZipCodeRange> prepareZipCodeList(String[] inputRange){
		for (String a : inputRange) {
			String[] startEndValues = getStartEndZipCodeValues(a);
			zipCodeRanges.add(new ZipCodeRange(Integer.parseInt(startEndValues[0]), Integer.parseInt(startEndValues[1])));
		}
		return zipCodeRanges;
	}
	
	private static String[] getStartEndZipCodeValues(String inputRange) {
		return inputRange.trim().replaceAll(REPLACE_REGEX, "").split(",");
	}
}
