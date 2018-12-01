package com.risk.test;

import static org.junit.Assert.*;

/**
 * This is Testclass for Player. It tests if player get name and Id succesfully and  he is able to Add or Remove land.
 */

import org.junit.After;
import org.junit.Before;

import org.junit.Test;

import com.risk.model.Country;
import com.risk.model.GameBoard;

import com.risk.model.Land;
import com.risk.model.Player;
import com.risk.utility.TurnPhases;

/**
 * The class <code>TestPlayer</code> contains tests for the class
 * <code> {@link Player}</code>
 * 
 * @author Ravneet
 * 
 * @version 1.0
 */

public class PlayerTest {

	Player p1;
	Player p2;
	GameBoard gb;

	/**
	 * Test case Initialization
	 * 
	 * @throws Exception
	 * Exception if the logging window file does not exist
	 */

	@Before
	public void BeforeTestPlayer() throws Exception {
		System.out.println("@BeforeClass");
		p1 = new Player(6, "adam", null, null, null);
		p2 = new Player(7, "lilly", null, null, null);
		gb = GameBoard.GetGameBoard();
		gb.LoadMap("Earth.map");
	}

	
	@Test
	public void GetName() {
		System.out.println("testGetName");
		String name = p1.GetName();
		System.out.println(name);
		assertEquals("adam", name);

	}

	/**
	 *  shows GetId method in Player class works fine
	 */

	@Test
	public void GetId() {
		System.out.println("testGetId");
		int id = p1.GetId();
		System.out.println(id);
		assertEquals(6, id);
	}

	/**
	 * this method verifies the reinforcement calculation
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void ReinforcementCalculation() throws Exception {
		gb.StartupGame(3);
		gb.players.get(0).CalculateReinforcementArmies();
		int armies = gb.players.get(0).GetArmies();
		System.out.println("Current player armies:" + armies);
		assertNotEquals(11, armies);
	}

	/**
	 * this test verifies conformity of fortification to the rules
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void Fortification() throws Exception {
		gb.StartupGame(3);
		Player currentPlayer = gb.turnOrganizer.GetCurrentPlayer();
		Country countryS = gb.map.GetCountryByName("Cockpit01");
		Country countryD = gb.map.GetCountryByName("Cockpit02");
		countryS.SetArmies(5);
		countryD.SetArmies(5);
		int armiesD = countryD.GetArmies();
		gb.map.GetCountryByName("Cockpit01").SetPlayerId(currentPlayer.GetId());
		gb.map.GetCountryByName("Cockpit02").SetPlayerId(currentPlayer.GetId());
		gb.turnOrganizer.GetCurrentPlayer().MoveArmiesToCountryFromCountry(countryS.GetId(), countryD.GetId(),
				1);
		assertEquals("PhaseNotValid", gb.turnOrganizer.GetCurrentPlayer()
				.MoveArmiesToCountryFromCountry(countryS.GetId(), countryD.GetId(), 1));
	}

	/**
	 * this method verifies attack conformity to the rules /**this applies
	 * conditions after test
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void AttackValidphase() throws Exception {
		gb.StartupGame(3);
		Player currentPlayer = gb.turnOrganizer.GetCurrentPlayer();
		Country countryS = gb.map.GetCountryByName("Cockpit01");
		Country countryD = gb.map.GetCountryByName("Cockpit02");
		countryS.SetArmies(5);
		countryD.SetArmies(5);
		int armiesD = countryD.GetArmies();
		gb.map.GetCountryByName("Cockpit01").SetPlayerId(currentPlayer.GetId());
		gb.map.GetCountryByName("Cockpit02").SetPlayerId(currentPlayer.GetId() + 1);
		gb.turnOrganizer.GetCurrentPlayer().Attack(2, 2);
		assertEquals("PhaseNotValid", gb.turnOrganizer.GetCurrentPlayer()
				.MoveArmiesToCountryFromCountry(countryS.GetId(), countryD.GetId(), 1));
	}

	
	@Test
	public void DeclareAttackValidphase() throws Exception {
		gb.StartupGame(3);
		Player currentPlayer = gb.turnOrganizer.GetCurrentPlayer();
		Player defenderPlayer = gb.GetPlayerById(gb.turnOrganizer.GetCurrentPlayer().GetId() + 1);
		Country countryS = gb.map.GetCountryByName("Cockpit01");
		Country countryD = gb.map.GetCountryByName("Cockpit02");
		countryS.SetArmies(5);
		countryD.SetArmies(5);
		int armiesD = countryD.GetArmies();
		gb.map.GetCountryByName("Cockpit01").SetPlayerId(currentPlayer.GetId());
		gb.map.GetCountryByName("Cockpit02").SetPlayerId(currentPlayer.GetId() + 1);
		gb.turnOrganizer.GetCurrentPlayer().DeclareAttack(countryS.GetId(), countryD.GetId(), defenderPlayer);
		assertEquals("PhaseNotValid", gb.turnOrganizer.GetCurrentPlayer()
				.MoveArmiesToCountryFromCountry(countryS.GetId(), countryD.GetId(), 1));
	}

	/**
	 * this method verifies the end of the game method
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void EndOfGame() throws Exception {
		gb.StartupGame(3);
		gb.turnOrganizer.GetCurrentPlayer().EndGame();
		assertNotEquals(gb.turnOrganizer.GetCurrentPhase(), TurnPhases.GameOver);
	}
    
	@Test
	public void Occupation() throws Exception{
		gb.StartupGame(3);
		Player currentPlayer = gb.turnOrganizer.GetCurrentPlayer();
		String result = currentPlayer.OccupyCountry(2);
		assertTrue(result.contains("the current phase is not attack phase"));
	}
	@After
	public void TestAfter() {
		System.out.println("@AfterClass");

		// Adding additional tear down code here
		System.out.println("@AfterClass - oneTimeTearDown");
		p1 = null;
		p2 = null;
		assertNull(p1);
		assertNull(p2);
	}
}
