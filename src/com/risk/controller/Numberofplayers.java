package com.risk.controller;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.risk.model.GameBoard;
import com.risk.utility.staticApplicationVariables;

/**
 * This class Adds a country to the map file based
 * on the events captured from the user interface.
 * 
 * @author Nayan
 * @version 1.0
 */

public class Numberofplayers implements ActionListener {

	JPanel jPanel;
	JFrame jFrame;
	JLabel jlblSelectplayerlabel;
	JTextField jtfCountryName, jtfContinentName;
	JButton btnOK, btnCancel;

	/**
	 * This class asks user to enter the number of the players
	 * it must be between 3 and 5 
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		jFrame = new JFrame("Set Number Of players ");
		jFrame.setSize(420, 140);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(25, 15, 15, 15));
		JPanel jPanelComboBox = new JPanel();
		jPanelComboBox.setLayout(new FlowLayout());
		JLabel jlblSelectplayerlabel = new JLabel("Select Number of  Players:");
		int intMaxnumberOfPlayers=5;
		final JComboBox jComboBoxlist = new JComboBox();
		for (int i = 0; i <= maxintMaxnumberOfPlayers {
			jComboBoxlist.addItem(i);
		}
		JButton btnOK = new JButton("OK ");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numberofplayerselected = Integer.parseInt(jComboBoxlist.getSelectedItem().toString());
				if (numberofplayerselected < 2) {
					JOptionPane.showMessageDialog(null, "please slect atleast 2 players ");
					jFrame.dispose();
				} else {
					try { 
						Object staticApplicationVariables;
						staticApplicationVariables.gb.StartupGame(numberofplayerselected);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Number of Players Selected  " + numberofplayerselected);
				}
			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		
		panel.add(btnCancel);
		jPanelComboBox.add(jlblSelectplayerlabel);
		jPanelComboBox.add(jComboBoxlist);
		panel.add(jPanelComboBox);
		panel.add(btnOK);
		jFrame.add(panel);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
