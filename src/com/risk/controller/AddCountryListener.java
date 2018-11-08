package com.risk.controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.GameBoard;
import com.risk.utility.MapParser;
import com.risk.utility.staticApplicationVariables;
import com.risk.view.MainWindow;

/**
 * This class belongs to a listener which Adds a country to the map file based
 * on the events captured from the user interface.
 * 
 * @author Ravneet Singh Brar
 * @version 1.0
 */
public class AddCountryListener extends JFrame implements ActionListener {

	JPanel jPanel;
	JFrame jFrame;
	JButton btnOK, btnCancel;
	JLabel jlblContinentName,jBanner, jlblCountryName ;
	JTextField jtfCountryName, jtfContinentName;
	/**
	 * This window then asks the user to select the
	 * name of Continent from a list, the X and Y cordinates, and the country name
	 *  which needs 
	 *to be added.
	 * 
	 * @param actionEvent
	 *            Not used.
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		jFrame = new JFrame("Add Country");
		jFrame.setSize(650, 170);
		Container cContent = getContentPane();
		cContent.removeAll();

		JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel.setBorder(new EmptyBorder(25, 15, 15, 15));

		JPanel jpanelComboBox = new JPanel();
		jpanelComboBox.setLayout(new FlowLayout());

		jlblContinentName = new JLabel("Select Continent: ");
		String[] continentString = new String[staticApplicationVariables.gb.map.GetContinents().size()];
		int i = 0;
		for (Object o : staticApplicationVariables.gb.map.GetContinents()) {
			continentString[i] = ((Continent) o).GetName();
			i++;
		}
		JComboBox<String> jcomboBoxList = new JComboBox<>(continentString);

		JLabel jlblCountryNametobeadded = new JLabel("Country Name:");
		JTextField jtfCountryField = new JTextField(10);
		JLabel jlblEnterXcordinates = new JLabel("X:");
		JTextField jtfXField = new JTextField(5);

		JLabel jlblEnterYcordinates = new JLabel("Y:");
		JTextField jtfYField = new JTextField(5);

		JButton btnOK = new JButton("ADD COUNTRY ");
		/**
		 * ok button action listener
		 * addition of a country to the continent selected by user on press of OK
		 * button.
		 * 
		 * @param actionEvent
		 *            Not used.
		 */
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = jcomboBoxList.getSelectedItem().toString();
				int id = staticApplicationVariables.gb.map.GetContinentIdByName(name);

				if (staticApplicationVariables.gb.map.DoesExistCountry(
						staticApplicationVariables.gb.map.GetCountryIdByName(jtfCountryField.getText())) == true) {
					JOptionPane.showMessageDialog(null, jtfCountryField.getText() + " Already Exists");
				} else {
					int before = staticApplicationVariables.gb.map.GetCountries().size();
					System.out.println("Before is :" + before);
					String output = staticApplicationVariables.gb.map.AddCountry(jtfCountryField.getText(), id,
							Integer.parseInt(jtfXField.getText()), Integer.parseInt(jtfYField.getText()));
					System.out.println("HERE1: " + output);
					int after = staticApplicationVariables.gb.map.GetCountries().size();
					System.out.println(after + "After");

					try {
						staticApplicationVariables.gb.SaveMapToFile("output.txt");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, jtfCountryField.getText() + " Country Added to:: " + name);
					
					jFrame.dispose();
					try {
						staticApplicationVariables.gb.SaveMapToFile("output.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			}

		});

		/**
		 *  frame to be closed on pressing of cancel button.
		 * 
		 * @param actionEvent
		 *            Not used.
		 */
		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jpanelComboBox.add(jlblCountryNametobeadded);
		jpanelComboBox.add(jtfCountryField);
		jpanelComboBox.add(jlblEnterXcordinates);
		jpanelComboBox.add(jtfXField);
		jpanelComboBox.add(jlblContinentName);
		jpanelComboBox.add(jcomboBoxList);
		jpanelComboBox.add(jcomboBoxList);
		
		jpanelComboBox.add(jlblEnterYcordinates);
		jpanelComboBox.add(jtfYField);

		jPanel.add(jpanelComboBox);
		jPanel.add(btnOK);
		jPanel.add(btnCancel);

		jFrame.add(jPanel);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
