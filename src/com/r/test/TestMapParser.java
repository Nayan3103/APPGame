package com.r.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.r.model.Continent;
import com.r.model.Country;
import com.r.model.Factory;
import com.r.model.GameBoard;
import com.r.model.Land;
import com.r.model.Map;
import com.r.utility.MapParser;

import org.junit.Test;

/**
 * The class <code>TestMapParser</code> contains tests for the class
 * <code> {@link MapParser}</code>
 * 
 * @author Sukhmeet Kaur
 * @version 1.0
 */

public class TestMapParser {
	/**
	 * Test case Initialization for TestMapParser
	 */

	@Before
	public void BeforeTestMapMarser() {

	}

	/**
	 * the TestInvalidHeader tests .map file is valid file by validating it contains Header [Map]
	 * @throws IOException if the assert.fail is successful
	 */

	@Test
	public void TestInvalidHeader() {
		try {
			assertEquals("Legal Values: Head validator should be [Map]",
					MapParser.MapValidator("TestFile/InvalidHeaderValidator.map"));
			Assert.fail("Should have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Header validator failed";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}
	/**
	 * The TestLessThan5countries tests .map file is valid file by validating .map file contain more than 5 countries
	 */

	@Test
	public void TestLessThan5countries() {

		try {
			assertEquals("Legal Values: Countries should be more than 5",MapParser.MapValidator("TestFile/lessthan5countries.map"));
			Assert.fail("Should have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Map contains less than 5 countries!";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}
	/**
	 * The TestMapMustContainContinents tests .map file is valid file by checking if the .map file contains continents
	 */

	@Test
	public void TestMapMustContainContinents() {

		try {
			assertEquals("Legal Values: Map must contain continents", MapParser.MapValidator("TestFile/Nocontinents.map"));
			Assert.fail("Should have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Map contains no continent!";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}
	/**
	 * The TestInvalidMapFile tests .pdf file is not accepted
	 */

	@Test
	public void TestInvalidMapFile() {
		try {
			assertEquals("Legal Values: Map must contain continents", MapParser.MapValidator("TestFile/invalidfile.pdf"));
			Assert.fail("Should have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Header validator failed";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}

}
