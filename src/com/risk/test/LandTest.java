package com.risk.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Player;

/**
 * The class <code>TestLand</code> contains tests for the class
 * <code> {@link Land}</code>
 * 
 * @author Ravneet Singh Brar
 * @version 1.0
 */

public class LandTest {

	Land land;

	/**
	 * Test case Initialization
	 */

	@Before
	public void TestBefore() {
		System.out.println("@BeforeClass");
		land = new Land("new_name");

	}

	/**
	 * This Test case test the Name of the land if returned correctly
	 */

	@Test
	public void GetName() {
		System.out.println("testGetName");
		String landName = land.GetName();
		System.out.println(landName);
		assertEquals("new_name", landName);
	}

	/**
	 * This Test case test the Land playerId is returned correctly
	 */
	@Test
	public void GetId() {
		System.out.println("testGetLandId");
		int playerId = land.GetPlayerId();
		System.out.println(playerId);
		assertEquals(-1, playerId);

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
		land = null;
		assertNull(land);
	}
}
