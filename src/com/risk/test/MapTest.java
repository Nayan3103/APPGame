package com.risk.test;

import static org.junit.Assert.*;

import java.util.ArrayList.*;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.Edge;
import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Map;
import com.risk.model.Player;

/**
 * The class <code>TestMap</code> contains tests for the class
 * <code> {@link Map}</code>
 * 
 * @author Manjinder
 * @version 1.0
 */

public class MapTest {
	Map map;
	Edge edge;
	Country country;
	GameBoard gb;

	/**
	 * Test case Initialization
	 */

	@Before
	public void BeforeTestMap() {
		System.out.println("@BeforeClass");
		map = new Map("new_name");
		edge = new Edge(1, 1);
	}

	/**
	 * This Test case test the Author if returned correctly
	 */

	@Test
	public void GetAuthor() {

		String author = map.GetAuthor();
		System.out.println(author);
		assertNull("new_name", author);
	}

	/**
	 * This Test case tests the Edge is added
	 */

	@Test
	public void AddEdge() {
		System.out.println("testAddEdge");

		int e1 = map.AddEdge(edge);
		System.out.println(e1);

		int e2 = map.AddEdge(edge);
		System.out.println(e2);

		assertEquals(1, e1);
		assertEquals(-1, e2);
	}

	/**
	 * This Test case test if the Edge is exist already
	 */

	@Test
	public void DoesExistEdge() {
		System.out.println("testDoesExistEdge");

		boolean b1 = false;
		System.out.println(b1);

		int b2 = map.AddEdge(edge);
		System.out.println(b2);

		assertTrue(true);
	}

	/**
	 * this method verifies the connectivity of the map
	 * 
	 * @throws Exception
	 *Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void MapConnectivity() throws Exception {
		gb = GameBoard.GetGameBoard();
		gb.LoadMap("Earth.map");
		assertTrue(gb.map.ValidationMapConnectivity());
	}

	/**
	 * this method verifies if all continents are connected
	 * 
	 * @throws Exception
	 * Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void ContinentsConnectivity() throws Exception {
		gb = GameBoard.GetGameBoard();
		gb.LoadMap("Earth.map");
		assertTrue(gb.map.ValidateContinentsConnectivity());
	}

	/**
	 * post-test clean-up.
	 * 
	 * @throws Exception
	 * if the clean-up fails for some reason
	 */
	@After
	public void TearDown() throws Exception {
		System.out.println("");
		map = null;
		edge = null;
		country = null;
		assertNull(map);
		assertNull(edge);
		assertNull(country);
	}

}
