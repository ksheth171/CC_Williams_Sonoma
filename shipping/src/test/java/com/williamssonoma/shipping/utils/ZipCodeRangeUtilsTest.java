package com.williamssonoma.shipping.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import com.williamssonoma.shipping.model.ZipCodeRange;

public class ZipCodeRangeUtilsTest {

	// SUT
	ZipCodeRangeUtils utils;

	//collaborators
	ArrayList<ZipCodeRange> zipCodeRanges = new ArrayList<ZipCodeRange>();	
	@Mock
	ZipCodeRange zipCodeRange;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		zipCodeRanges.add(new ZipCodeRange(94133,94133));
		zipCodeRanges.add(new ZipCodeRange(94600,94699));
		zipCodeRanges.add(new ZipCodeRange(94200,94299));
	}

	@Test
	public void givenValidZipAndRange_WhenIsWithinRange_ThenReturnTrue() {
		assertTrue(utils.isWithinRange(94650, zipCodeRanges.get(1)));
	}

	@Test
	public void givenInvalidZipAndRange_WhenIsWithinRange_ThenReturnFalse() {
		assertFalse(utils.isWithinRange(94150, zipCodeRanges.get(2)));
	}
	
	@Test
	public void givenValidZipCodeRanges_WhenConsolidateOverlappingZipCodes_ThenReturnCorrectListSize(){
		assertEquals(utils.consolidateOverlappingZipCodes(zipCodeRanges).size(),3);
	}
	
	@Test
	public void givenOverLappingZipCodeRanges_WhenConsolidateOverlappingZipCodes_ThenReturnCorrectListSize(){
		ArrayList<ZipCodeRange> zipCodeRanges = new ArrayList<ZipCodeRange>();	
		zipCodeRanges.add(new ZipCodeRange(94133,94133));
		zipCodeRanges.add(new ZipCodeRange(94600,94699));
		zipCodeRanges.add(new ZipCodeRange(94132,94299));
		
		assertEquals(utils.consolidateOverlappingZipCodes(zipCodeRanges).size(),2);
		assertEquals(utils.consolidateOverlappingZipCodes(zipCodeRanges).get(0).getStartRange(),zipCodeRanges.get(0).getStartRange());
		assertEquals(utils.consolidateOverlappingZipCodes(zipCodeRanges).get(0).getEndRange(),zipCodeRanges.get(0).getEndRange());
		
	}
	
	@Test
	public void givenBiggerZipCode_WhenIsZipCodeLargerThanEndRange_ThenReturnTrue(){
		assertTrue(utils.isZipCodeLargerThanEndRange(94650, zipCodeRanges.get(0)));
	}
	
	@Test
	public void givenSmallerZipCode_WhenIsZipCodeLargerThanEndRange_ThenReturnFalse(){
		assertFalse(utils.isZipCodeLargerThanEndRange(94131, zipCodeRanges.get(0)));
	}
}
