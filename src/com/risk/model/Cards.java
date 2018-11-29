package com.risk.model;

import com.risk.utility.*;

/**
 * this class represent a card it has properties such as id, name, playerId
 * 
 * @author Sukhmeet
 * @version 1.0
 */
public class Cards {
	private static int icounter = 3;
	protected int id;
	protected ECards eCardType;
	protected int playerId;
	//constructor
	public Cards(){
		
	}

	/**
	 * constructor takes the name and creates the card object
	 * 
	 * @param param_name
	 *            is the type of the card
	 */
	public Cards(ECards param_name) {
		icounter++;
		this.id = this.icounter;
		this.eCardType = param_name;
		this.playerId = 1;

	}

	/**
	 * this method returns the name of the card
	 * 
	 * @return the type of the object
	 */
	public ECards GetType() {
		return this.eCardType;
	}

	/**
	 * this method sets playerId
	 * 
	 * @param param_playerId
	 *            is the id of the player
	 */
	public void SetPlayerId(int param_playerId) {
		this.playerId = param_playerId;
	}

	/**
	 * this method returns the id of the object
	 * 
	 * @return is the id of the card
	 */
	public int GetId() {
		return this.id;
	}
}
