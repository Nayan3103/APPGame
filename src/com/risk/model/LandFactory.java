package com.risk.model;

/**
 * This class constructs objects of continent and country classes
 * 
 * @author Nayan
 * @version 1.0.0.0
 */
public class LandFactory {

	public static Land GetLand(String type, String param_name, int param_continentId, int param_x, int param_y,
			int param_control) {
		switch (type) {
		//if country then it returns the constructs objects of country
		case "Country":
			return new Country(param_name, param_continentId, param_x, param_y);
			//if continent then it returns the constructs objects of continent
		case "Continent":
			return new Continent(param_name, param_control);

		default:
			return null;

		}
	}

}
