package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.Edge;
import com.risk.model.Map;

/**
 * The class <code>TestEdge</code> contains tests for the class
 * <code> {@link Edge}</code>
 * 
 * @author Manjinder
 * @version 1.0
 */

public class EdgeTest {

	
	Map map;
	Edge edge;
	/**
	 * Test case Initialization
	 */

	@Before
	public void TestEdgeBefore() {
		edge = new Edge(1, 2);
		map = new Map("new_name");
	}

	/**
	 * This Test case test the country id if returned correctly
	 */

	@Test
	public void GetCountryId1() {
		System.out.println("testGetCountryId1");
		int countryid1 = edge.GetCountryId1();
		System.out.println(countryid1);
		assertEquals(1, countryid1);
	}

	/**
	 * This Test case test the player id if returned correctly
	 */

	

	@Test
	public void GetId() {
		System.out.println("testGetEdgeId");
		int id = edge.GetId();
		System.out.println(id);
		assertEquals(3, id);
	}

	@Test
	public void GetCountryId2() {
		System.out.println("testGetCountryId2");
		int countryid2 = edge.GetCountryId2();
		System.out.println(countryid2);
		assertEquals(2, countryid2);
	}
	@Test
	public void DoesCountryExist() {
		System.out.println("DoesExistEdge");

		boolean bCountryresult = false;
		System.out.println(bCountryresult);

		String sCountryresult = map.AddCountry("abc", 0, 35, 46);
		System.out.println(sCountryresult);
		assertTrue(sCountryresult.contains("successful"));
	}
}
