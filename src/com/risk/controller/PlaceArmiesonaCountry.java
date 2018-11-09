package com.risk.controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton; 
import javax.swing.JComboBox;
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField; 
import javax.swing.border.EmptyBorder;

import com.risk.model.Country;
import com.risk.utility.staticApplicationVariables;

/**
 * This class puts army on the countries.
 * 
 * @author Ravneet Singh Brar
 * @version 1.0
 */
public class PlaceArmiesonaCountry implements ActionListener {
	static JFrame jFrame;

	/**
	 * Causes a new window to Pop-up. This window then asks the user to put armies on the enemy's land
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		jFrame = new JFrame("Place Armies On a Country");
		jFrame.setSize(340, 210);
		JLabel listofcountriesofplayer = new JLabel("Select My countries:");
		int intCurrentPlayerid = staticApplicationVariables.gb.turnOrganizer.GetCurrentPlayerId();
		JComboBox<String> jComboBoxList2 = new JComboBox<>(getCountryListForaplayer(intCurrentPlayerid));
		jComboBoxList2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sName = ((JComboBox) e.getSource()).getSelectedItem().toString();
				JOptionPane.showMessageDialog(null, " Country Selected " + sName + "Enter Armies now :");
			} 
		});
		JLabel numberofarmies = new JLabel("Number Of Armies:");
		JTextField jtfNumberOfArmiesTField = new JTextField(7);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
		JButton btOK = new JButton("OK");
		btOK.addActionListener(new ActionListener() {

			//ok button action perform
			@Override
			public void actionPerformed(ActionEvent e) { 
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, " Countries Allocated  Successfully!");
				jFrame.dispose();

			}
		});
		//cancel button action perform
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		
		jPanel.add(btOK);
		jPanel.add(btnCancel);
		jPanel.add(listofcountriesofplayer);
		jPanel.add(jComboBoxList2);
		jPanel.add(numberofarmies);
		jPanel.add(jtfNumberOfArmiesTField);
		jFrame.add(jPanel);
		jFrame.setVisible(true);
	}

	//getting country list based on player's army
	String[] getCountryListForaplayer(int playerid) {
		List<Country> listCountry = staticApplicationVariables.gb.map.GetCountriesByPlayerId(playerid);
		String[] countryString = new String[listCountry.size()];

		for (int j = 0; j < listCountry.size(); j++) {
			countryString[j] = listCountry.get(j).GetName();
		}
		return countryString;
	}
}
