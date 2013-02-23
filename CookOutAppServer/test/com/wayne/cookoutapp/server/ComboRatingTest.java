package com.wayne.cookoutapp.server;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComboRatingTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetRating() {
		ComboRating testItem = new ComboRating(1, 2, 20, 20);
		
		assertEquals(1.0, testItem.getRating(), 0.001);
		
		testItem.setTotalRating(40);
		
		assertEquals(2.0, testItem.getRating(), 0.001);
		
		testItem.setTimesRated(80);
		
		assertEquals(0.5, testItem.getRating(), 0.001);
		
	}

}
