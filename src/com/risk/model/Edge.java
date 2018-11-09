package com.risk.model;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a connection between two countries it maintains id of
 * two countries which are neighbor it has different methods to change and set
 * the state of the object
 * 
 * @author Ravneet Singh Brar
 * @version 1.0
 */
public class Edge implements Comparable {
	private static int counter = 0;
	private int id;
	private int countryId1;
	private int countryId2;
	
	//standard json constructor
	public Edge(){
		
	}

	/**
	 * the constructor sets the object id and id of the
	 * two neighbor countries
	 * 
	 * @param prm_countryId1,
	 *          is the id of the first country
	 * @param prm_countryId2,
	 *            is the id of the second country
	 */
	public Edge(int param_countryId1, int param_countryId2) {
		counter++;
		this.id = this.counter;
		this.countryId1 = param_countryId1;
		this.countryId2 = param_countryId2;
	}

	/**
	 * This method returns the id of the first country
	 * 
	 * @return countryId1, the id of the first country
	 */
	public int GetCountryId1() {
		return this.countryId1;
	}

	
	/**
	 * This method returns the id of the object 
	 * @return, the id of the object
	 * 
	 * @return is the id of the edge
	 */
	public int GetId() {
		return this.id;
	}
	/**
	 * This method returns the id of the second country
	 * 
	 * @return countryId2,is the id of the second country
	 */
	public int GetCountryId2() {
		return this.countryId2;
	}

	
	@Override
	/**
	 * This method implements the compareTo method from comparable interface
	 * 
	 * @param obj,
	 *            is an edge which will be compared to the
	 *            object in terms of the id of the neighbor countries
	 * @return an integer which could be 0 or -1, 0 means that two edges are
	 *         equal and -1 means the two edges are not equal
	 */
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		Edge edge = (Edge) obj;
		if ((this.GetCountryId1() == edge.GetCountryId1() && this.GetCountryId2() == edge.GetCountryId2())
				|| (this.GetCountryId1() == edge.GetCountryId2() && this.GetCountryId2() == edge.GetCountryId1())) {
			return 0;
		} else
			return -1;
	}


	

	/**
	 * this method returns the neighbor of a given country in the current edge
	 * 
	 * @param prm_countryId
	 *            , which is integer, is the id of a given country
	 * @return the neighbor's id in the current edge
	 */
	public int GetNeighborId(int prm_countryId) {
		if (this.countryId1 == prm_countryId) {
			return this.countryId2;
		} else if (this.countryId2 == prm_countryId) {
			return this.countryId1;
		} else {
			return -1;
		}

	}
	/**
	 * This method verifies if a given country exists in the current edge
	 * 
	 * @param param_countryId
	 *            , which is integer, is the id of the given country
	 * @return , which is boolean, is true if the country exists in the edge,
	 *         otherwise returns false
	 */
	public boolean DoesExistCountry(int param_countryId) {
		boolean result = false;
		if (this.GetCountryId1() == param_countryId || this.GetCountryId2() == param_countryId) {
			return true;
		}
		return result;
	}
	
	/**
	 * the method verifies if the current edge contains a given country
	 * 
	 * @param param_countryId
	 *            is the id of the country under query
	 * @return a boolean type which is true if the country under the query is
	 *         contained in the current edge
	 */
	public boolean DoesContainCountry(int param_countryId) {
		if (this.countryId1 == param_countryId || this.countryId2 == param_countryId) {
			return true;
		} else {
			return false;
		}

	}

}
