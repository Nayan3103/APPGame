package com.risk.model;

import java.util.Observable;
import java.util.Observer;

/**
 * this class represents a class to show world domination percentage it uses map
 * observable to show the percentage
 * 
 * @author Ravneet Singh Brar
 * @version 1.0.0.0
 */
public class DominationWorldObserver implements Observer {

	public String Domination = "No country distributed";

	public DominationWorldObserver() {

	}

	/**
	 * this method updates the status as per observable changes
	 * 
	 */
	@Override
	public void update(Observable obj, Object arg) {
		// TODO Auto-generated method stub
		Map map = (Map) obj;
		Domination = map.GetWorldDomination();
		System.out.println("_____World Domination View_____");
		System.out.println(Domination);
		System.out.println("_____End of World Domination View_____");
	}

	public void GetWorldDomination() {
		System.out.println(Domination);
	}

}
