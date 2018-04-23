package com.williamssonoma.shipping.utils;

import java.util.ArrayList;
import java.util.Collections;

import com.williamssonoma.shipping.model.ZipCodeRange;

public class ZipCodeRangeUtils {

	public static ArrayList<ZipCodeRange> consolidateOverlappingZipCodes(ArrayList<ZipCodeRange> zipCodeRanges) {
		//sort the list based on start range
		Collections.sort(zipCodeRanges, new ZipCodeRange());
		
		Integer startRange;
		Integer endRange;
		ArrayList<ZipCodeRange> consolidatedZipCodeRanges = new ArrayList<ZipCodeRange>();

		System.out.print("Consolidated zip code list: ");
		
		// consolidate overlapping zip code ranges
		for (int i = 0; i < zipCodeRanges.size(); i++) {
			startRange = zipCodeRanges.get(i).getStartRange();
			endRange = zipCodeRanges.get(i).getEndRange();

			for (int j = i; j < zipCodeRanges.size(); j++) {

				if (ZipCodeRangeUtils.isWithinRange(endRange, zipCodeRanges.get(j))) {
					endRange = zipCodeRanges.get(j).getEndRange();
					i = j;
				}
				if (ZipCodeRangeUtils.isZipCodeLargerThanEndRange(endRange, zipCodeRanges.get(j))) {
					i = j;
				}
			}

			consolidatedZipCodeRanges.add(new ZipCodeRange(startRange, endRange));
			System.out.print("[" + startRange + "," + endRange + "] ");
			
		}
		return consolidatedZipCodeRanges;
	}

	public static boolean isWithinRange(Integer zipCode, ZipCodeRange zipCodeRange) {
		return (zipCodeRange.getStartRange() <= zipCode) && (zipCode <= zipCodeRange.getEndRange());
	}

	public static boolean isZipCodeLargerThanEndRange(Integer zipCode, ZipCodeRange zipCodeRange) {
		return zipCode > zipCodeRange.getEndRange();
	}
}
