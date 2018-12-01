package com.risk.model;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a continent and it maintains name, id, control number and
 * playerId 
 * 
 * @author Ravneet Singh Brar
 * @version 1.0
 */

public class Continent extends Land {
	int control;
	//standard json constructor
	public Continent(){
		super();
	}


	/**
	 * This is the constructor of the Continent class, it inherits partially
	 * from the parent superclass, Land, to set the continentId and the name and
	 * the constructor sets the control value and assign -1 to playerId which
	 * means that in Gameplay phase it will be assigned to a player
	 * 
	 * @param param_name this parameter will be the name of the continent which is created
	 * @param param_control this parameter has the value of the control of the continent           
	 */
	public Continent(String param_name, int param_control) {
		super(param_name);
		this.control = param_control;
	}

	

	/**
	 * This method returns the value of the control object
	 * 
	 * @return control of the object as integer
	 */
	public int GetControl() {
		return control;
	}

	/**
	 * This method returns the continentID of the object
	 * 
	 * @return continentID as integer 
	 */
	public int GetContinentId() {
		return this.id;
	}

	/**
	 * this method returns the id of the player
	 * 
	 * @return the id of the player who owns the continent
	 */
	public int GetPlayerId() {
		return this.playerId;
	}
/**
	 * this method sets the playerId of the continent
	 * 
	 * @param param_playerId
	 *            is the id of the player who owns the continent
	 */
	public void SetPlayerId(int param_playerId) {
		// TODO Auto-generated method stub
		this.playerId = param_playerId;
	}
}
