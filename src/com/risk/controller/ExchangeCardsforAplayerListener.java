package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.risk.utility.staticApplicationVariables;

/**
 * @author Manjinder Kaur
 */
public class ExchangeCardsforAplayerListener extends JFrame implements ActionListener {

	/**
	 * Exchanging the cards
	 * 
	 * @param e is the event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// remove comments if you are using gui format
			/*int output = staticApplicationVariables.gb
					.ExchangeCards(staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId());
			if (output == 1)
				*/
			JOptionPane.showMessageDialog(null, "Cards Excahnged successfully!");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
