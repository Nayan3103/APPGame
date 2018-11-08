package com.risk.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.risk.utility.staticApplicationVariables;

/**
 * This class belongs to a listener which performs action on events
 * 
 * @author Manjinder Kaur
 * @version 1.0
 */

public class EndReinforcementPhaseListener implements ActionListener {
	static JFrame jFrame;

	/**
	 *  new window to Pop-up. asks the user to end reinforcement phase
	 *
	 * 
	 * @param e
	 * is the event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		jFrame = new JFrame("End Reinforcement Phase");
		jFrame.setSize(300, 150);
		JPanel jPanel = new JPanel();
		JLabel jMessage = new JLabel("Do You Want To End Reinforcement Phase? ");
		System.out.println(staticApplicationVariables.gb.turnOrganizer.GetCurrentPhase());
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {

			/**
			 * action performs on the ok button
			 * 
			 * @param actionEvent
			 *            Not used.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayer().EndReinforcementPhase();
				System.out.println(staticApplicationVariables.gb.turnOrganizer.GetCurrentPhase());
				JOptionPane.showMessageDialog(null, " Reinforcement Phase Ended Successfully!");
				jFrame.dispose();
			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		
		jPanel.add(btnOK);
		jPanel.add(jMessage);
		jPanel.add(btnCancel);
		jFrame.add(jPanel);
		jFrame.setVisible(true);
	}
}
