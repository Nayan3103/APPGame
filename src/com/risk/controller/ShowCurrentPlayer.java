package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.risk.utility.staticApplicationVariables;

/**
 * This class shows current player on the events captured from the user
 * interface.
 * 
 * @author Sukhmeet
 * @version 1.0
 */
public class ShowCurrentPlayer implements ActionListener {

	
	@Override
	public void actionPerformed(ActionEvent e) {
		//getting current player id
		System.out.println(staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId());
		//getting the phase of the game according to player's id
		System.out.println(staticApplicationVariables.gb.turnOrganizer.GetCurrentPhase());
	}
}
