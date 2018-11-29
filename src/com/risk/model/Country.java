package com.risk.model;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a country it maintains name, id,army, and coordinates
 * of the country it has different methods to change the state and returns the
 * state of the object
 * 
 * @author Sukhmeet Kaur 
 * @version 1.0.0
 */
public class Country extends Land {
	private int armies;
	private int intContinentId;
	private int x,y,z;
	public List<Country> neighbors;
	public boolean bVisited;
	
	//standard json constructor
	public Country(){
		super();
	}
	/**
	 * This constructor initialize the object
	 * 
	 * @param param_x
	 * is the x coordinate of the country
	 * @param param_y
	 *            is the y coordinate of the country
	 * @param param_name
	 *            which is string will be the name of the country
	 * @param param_continentId
	 *             is the id of the continent to which the
	 *            country belongs
	 * 
	 */
	public Country(String param_name, int param_continentId, int param_x, int param_y) {
		super(param_name);
		this.intContinentId = param_continentId;
		this.x = param_x;
		this.y = param_y;
		neighbors = new ArrayList<Country>();
		bVisited = false;
	}

	/**
	 * This method returns the id of the player to which the country was
	 * assigned
	 * 
	 * @return playerId is the id of the player who owns the
	 *         country
	 */
	public int GetPlayerId() {
		return this.playerId;
	}

	/**
	 * this method sets the id of the player who will own the country
	 * 
	 * @param prm_playerId,
	 *             id of the player who will own the
	 *            country
	 */
	public void SetPlayerId(int prm_playerId) {
		this.playerId = prm_playerId;
	}

	/**
	 * This method returns x coordinate of the country's position
	 * 
	 * @return x, The x coordinate of the country
	 */
	public int GetX() {
		return this.x;
	}

	/**
	 * This method returns y coordinate of the country's position
	 * 
	 * @return y, The y coordinate of the country
	 */
	public int GetY() {
		return this.y;
	}

	/**
	 * This method returns the id of the continent to which the country belongs
	 * 
	 * @return continentId,The id of the continent to which
	 *         the country belongs
	 */
	public int GetContinentId() {
		return this.intContinentId;
	}

	/**
	 * this method returns army of a given country
	 * 
	 * which is the id of the given country
	 * 
	 * @return , is the number of army of the country
	 */
	public int GetArmies() {
		return this.armies;
	}

	/**
	 * this method sets the number of armies
	 * 
	 * @param param_armies
	 *            is the number of new armies
	 */
	public void SetArmies(int param_armies) {
		this.armies = param_armies;
	}

	/**
	 * this method adds a country to the list of neighbors
	 * 
	 * @param param_country
	 *            is the neighbor country
	 */
	public void AddNeighbor(Country param_country) {
		this.neighbors.add(param_country);
	}

	/**
	 * this method returns the neighbor country by id
	 * 
	 * @param param_neighborId
	 *            is the id of the neighbor country
	 * @return the neighbor country
	 */
	public Country GetNeighborById(int param_neighborId) {
		for (Country cnt : this.neighbors) {
			if (cnt.GetId() == param_neighborId)
				return cnt;
		}
		return null;
	}
	/**
	 * this method returns neighbors of a country
	 * 
	 * @return list of neighbor countries
	 */
	public List<Country> GetNeighbors() {
		return this.neighbors;
		}

	
	/**
	 * this method adds or subtract from the armies from the belonging country
	 * 
	 * @param param_addedArmies
	 *            the number of armies that is added or subtracted from the
	 *            country's armies
	 */
	public void AddArmies(int param_addedArmies) {
		this.armies += param_addedArmies;
	}
	


	/**
	 * returns the list of neighbors that exist in the same
	 * continent
	 * 
	 * @return list of neighbor countries in the same continent
	 */
	public List<Country> GetNeighborsInContinent() {
		List<Country> listNeighborsInContinent = new ArrayList<Country>();
		for (Country cntNeighbor : neighbors) {
			if (this.GetContinentId() == cntNeighbor.GetContinentId()) {
				listNeighborsInContinent.add(cntNeighbor);
			}
		}
		return listNeighborsInContinent;
	}

}
