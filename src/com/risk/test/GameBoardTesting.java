package com.risk.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.risk.model.Country;
import com.risk.model.LandFactory;
import com.risk.model.GameBoard;
import com.risk.model.Map;
import com.risk.model.Player;
import com.risk.utility.LoggingWindow;
import com.risk.utility.MapParser;
import com.risk.utility.TurnPhases;
import com.risk.utility.staticApplicationVariables;

/**
 * The class <code>TestGameBoard</code> contains tests for the class
 * <code> {@link GameBoard}</code>
 * 
 * @author Manjinder
 * @version 1.0
 */

public class GameBoardTesting {
	GameBoard gb;

	/**
	 * Test case Initialization for TestGameBoard
	 */

	@Before
	public void BeforeTestGameBoard() {
		gb = new GameBoard();
	}

	/**
	 * This testGetGameBoard checks the single instance creation (singleton)
	 */
	@Test
	public void TestGetGameBoard() {

		assertNotNull(gb.GetGameBoard());
	}

	/**
	 * this method verifies the game by checking the number of armies
	 * 
	 * @throws Exception
	 *             if the logging window file does not exist
	 * 
	 */
	@Test
	public void StartupPhase() throws Exception {
		gb = GameBoard.GetGameBoard();
		gb.LoadMap("Earth.map");
		gb.turnOrganizer.SetCurrentPhase(TurnPhases.Startup);
		;
		gb.BuildDeck(gb.map.GetCountries().size());
		gb.SetupPlayers(3);
		gb.turnOrganizer.players = gb.players;
		gb.AssignCountriesRandom();
		gb.AllocateInitialArmies();
		int intArmies = gb.players.get(0).GetArmies();
		System.out.println("Current player armies:" + intArmies);
		assertEquals(35, intArmies);
	}
	/**
	 *  tests the save game
	 * @throws Exception file not exist
	 */
	@Test
	public void SaveGame() throws Exception{
		gb = GameBoard.GetGameBoard();
		gb.LoadMap("Earth.map");
		String result=gb.SaveGameToFile("testDB.jason");
		assertTrue(result.equalsIgnoreCase("Successful"));
	}
	/**
	 * this test load the game from a file
	 * @throws FileNotFoundException file not exist
	 * 
	 * @throws JsonIOException jason file not exist
	 * @throws JsonSyntaxException jason issues
	 */
	@Test
	public void LoadGame() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL,null);
	}
	/**this test the map entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException jason syntax error
	 * @throws JsonIOException jason file not exist
	 * 
	 */
	@Test
	public void LoadGameMap() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.map,null);
	}
	/**
	 * this test the player entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException jason errors
	 * @throws JsonIOException jason file not exist
	 * 
	 */
	@Test
	public void LoadGamePlayer() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.players,null);
	}
	/**
	 * @throws Exception 
	 * 
	 */
	/**this tests the countries entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException jason syntax error
	 * @throws JsonIOException json file not exist
	 * 
	 */
	@Test
	public void LoadGameCountries() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.map.GetCountries(),null);
	}
	
	/**
	 * this test the continent connectivity
	 * @throws Exception file not exist
	 * 
	 */
	@Test
	public void ValidConnectedMap() throws Exception{
		gb.LoadMap("TestFile/3D Cliff.map");
		assertTrue(gb.map.ValidateContinentsConnectivity());
	}
	/**this tests the continent connectivity
	 * @throws Exception file not exist
	 * 
	 */
	@Test
	public void ValidConnectedContinent() throws Exception{
		gb.LoadMap("TestFile/World.map");
		assertTrue(gb.map.ValidateContinentsConnectivity());
	}
	
	/**
	 * this test the turnOrganizer entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException json syntax error
	 * @throws JsonIOException json file not exist
	 * 
	 */
	@Test
	public void LoadGameTurnOrganizer() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.turnOrganizer,null);
	}
	/**
	 * this test the continents entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException json syntax error
	 * @throws JsonIOException json file not exist
	 * 
	 */
	@Test
	public void LoadGameContinents() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.map.GetContinents(),null);
	}
	/**
	 * this tests the single mode
	 * @throws Exception file not exist
	 * 
	 */
	@Test
	public void SingleMode() throws Exception{
		String mode = gb.GameEntry(0);
		assertTrue(mode.equalsIgnoreCase("SingleMode"));
	}
	/**
	 * this test the single mode
	 * @throws Exception file not exist
	 * 
	 */
	@Test
	public void Tournament() throws Exception{
		String mode = gb.GameEntry(1);
		assertTrue(mode.equalsIgnoreCase("Tournament"));
	}

}