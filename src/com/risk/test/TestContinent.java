package com.risk.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;
import com.risk.model.Continent;
import com.risk.model.Land;

/**
 * The class <code>TestContinent</code> contains tests for the class
 * <code> {@link Continent}</code>
 * 
 * @author manjinder
 * @version 1.0
 */

public class TestContinent {

	Continent cnt;

	/**
	 * Test case Initialization
	 */

	@BeforeClass
	public static void BeforeClass() {
		System.out.println("Entered the TestContinent Class");
	}

	/**
	 * This method runs after all test cases were ran
	 */
	@AfterClass
	public static void AfterClass() {
		System.out.println("Left TestContinent Class");
	}

	/**
	 * This method initiate the variable before each test case
	 */

	@Before
	public void BeforeTestContinent() {
		cnt = new Continent("Asia", 5);
	}

	/**
	 * This testcase tests the continent Id in continent test class and shows
	 * that the id is correct
	 */
	@Test
	public void TestGetContinentId() {

		int identity = cnt.GetContinentId();
		System.out.println(identity);
		assertEquals(1, identity);
	}

	/**
	 * This testcase tests the continent control value in continent test class
	 * and shows that the control value is correct
	 * 
	 */

	@Test
	public void TestGetControl() {
		int intContinentControl = cnt.GetControl();
		assertEquals(5, intContinentControl);
	}
}
