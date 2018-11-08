package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JComboBox; 

import com.risk.model.Country;
import com.risk.utility.staticApplicationVariables; 

/**
 * This class shows players per Countries
 * 
 * @author Ravneet
 * @version 1.0
 */
public class ShowPlayerCountries implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("The total number of countries :" + staticApplicationVariables.gb.map.GetCountries().size());
		System.out
				.println("The Id of current player is:" + staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId());
		System.out.println(" countries per player is : ");
		System.out.println(
				(staticApplicationVariables.gb.map.GetCountriesByPlayerId(staticApplicationVariables.gb.players.size()))
						.size());
	}
}
