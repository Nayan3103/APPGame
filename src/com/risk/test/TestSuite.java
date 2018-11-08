package com.risk.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This class is a test suite for all the tests cases
 * 
 * @author Ravneet
 * @version 1.0
 */
@RunWith(Suite.class)
@SuiteClasses({ TestContinent.class, CountryTest.class, EdgeTest.class, GameBoardTesting.class, LandTest.class,
		MapTest.class, MapParserTest.class, PlayerTest.class, })
public class TestSuite {

}
