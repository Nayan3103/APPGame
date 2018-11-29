package com.risk.model;
import java.util.List;

/**
 * this class simulate the attack phase
 * 
 * @author Sukhmeet 
 * @version 1.0.0
 */
public class Attack {
	public List<Dice> attackerDices;
	public Country attackerCountry;
	public Country defenderCountry;
	public Player defenderPlayer;
	public boolean isCaptured;
	public boolean isOccupied  ;
	public List<Dice> defenderDices;
	private static Attack instance;
	

	/**
	 * This constructor creates the object of parameters 
	 * 
	 * 
	 * @param param_defenderCountry
	 *            is defender country
	 * @param param_attackerCountry
	 *            is the country that launches attack
	 * @param param_defenderPlayer
	 *            is defender player
	 */
	public Attack(Country param_attackerCountry, Country param_defenderCountry, Player param_defenderPlayer) {
		this.defenderCountry = param_defenderCountry;
		this.attackerCountry = param_attackerCountry;
		this.defenderPlayer = param_defenderPlayer;
		isCaptured = false;
		isOccupied = false;
	}

}
