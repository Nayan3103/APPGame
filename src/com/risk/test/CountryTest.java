package com.risk.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.Land;
import com.risk.model.Player;

/**
 * The class <code>TestCountry</code> contains tests for the class
 * <code> {@link Country}</code>
 * 
 * @author Manjinder
 * @version 1.0
 */

public class CountryTest {

	Country cnt;
	Player p;
	Continent cont;

	/**
	 * Test case Initialization
	 */

	@Before
	public void TestCaseRGame() {
		cnt = new Country("Canada", 10, 11, 12);
	}

	/**
	 * Test case to tests x value is correct
	 */

	@Test
	public void GetXTest() {
		System.out.println("test-X");
		int x = cnt.GetX();
		System.out.println(x);
		assertEquals(11, x);
	}

	/**
	 * Test case to tests y value is correct
	 */

	@Test
	public void GetYTest() {
		System.out.println("test-Y");
		int y = cnt.GetY();
		System.out.println(y);
		assertEquals(12, y);
	}

	/**
	 * returns  int
	 */
	@Test
	public void GetArmiesTest() {
		System.out.println("GetArmies");
		int army = cnt.GetArmies();
		System.out.println(army);
		assertEquals(0, army);
	}

	@Test
	public void GetPlayerIdTest() {
		System.out.println("PlayerId");
		int id = cnt.GetPlayerId();
		System.out.println(id);
		assertEquals(-1, id);
	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * @throws Exception
	 * if the clean-up fails for some reason
	 */

	@After
	public void TearDown() throws Exception {
		System.out.println("");
		cnt = null;
		assertNull(cnt);
	}
}
