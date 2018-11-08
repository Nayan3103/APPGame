package com.risk.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.LandFactory;
import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Map;
import com.risk.utility.MapParser;

import org.junit.Test;

/**
 * The class <code>TestMapParser</code> contains tests for the class
 * <code> {@link MapParser}</code>
 * 
 * @author Ravneet
 * @version 1.0
 */

public class MapParserTest {
	/**
	 * Test case Initialization
	 */

	@Before
	public void BeforeTestMapMarser() {

	}

	/**
	 * the TestInvalidHeader tests .map file is valid file by validating it
	 * contains Header [Map]
	 */
	@Test
	public void InvalidHeader() {
		try {
			assertEquals("Legal Values: Head validator should be [Map]",
					MapParser.BMapValidator("TestFile/InvalidHeaderValidator.map"));
			Assert.fail("must have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Header validator failed";
			Assert.assertEquals("Exception must be correct", expectedMessage, e.getMessage());
		}
	}

	/**
	 * tests .map file is valid file by
	 * checking if the .map file contains continents
	 */

	@Test
	public void MapContainContinents() {

		try {
			assertEquals("Legal Values: Map must contain continents",
					MapParser.BMapValidator("TestFile/Nocontinents.map"));
			Assert.fail("must have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Map contains no continent!";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}

	/**
	 * tests .pdf file is not accepted
	 */

	@Test
	public void InvalidMapFile() {
		try {
			assertEquals("Legal Values: Map must contain continents",
					MapParser.BMapValidator("TestFile/invalidfile.pdf"));
			Assert.fail("must have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Header validator failed";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}

}
