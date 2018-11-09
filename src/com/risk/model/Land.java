package com.risk.model;

/**
 * This class represents the base class for continent and country class it
 * maintains id and name properties
 * 
 * @author Ravneet
 * @version 1.0
 */
public class Land implements ILand {
	private static int counter = 0;
	protected int id,playerId;
	protected String sName;
		
	//standard json constructor
	public Land(){
		
	}

	/**
	 * this constructor generates the land object 
	 * it generates the id of the object as a unique integer in incremental way
	 * this constructor will be used to construct continents and countries
	 * 
	 * @param param_name
	 *            is the name of the new object
	 */
	public Land(String param_name) {
		counter++;
		this.id = this.counter;
		this.sName = param_name;
		this.playerId = -1;
	}

	/**
	 * this method returns the name of the object which is used in the continent
	 * and country instances
	 * 
	 * @return the name of the object
	 */
	public String GetName() {
		return this.sName;
	}

	/**
	 * this function returns the id of the object which is used in the continent
	 * and country instances
	 * 
	 * @return which could be a continent or a country
	 */
	public int GetId() {
		return this.id;
	}

	/**
	 * this method returns the playerId
	 * 
	 * @return is the id of the player
	 */
	public int GetPlayerId() {
		return playerId;
	}

}
