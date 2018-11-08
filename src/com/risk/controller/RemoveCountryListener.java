
package com.risk.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.GameBoard;
import com.risk.utility.staticApplicationVariables;
import com.risk.view.MainWindow;

/**
 * This class belongs to a listener which removes a country from the map file based
 * on the events captured from the user interface.
 * 
 * @author Ravneet
 * @version 1.0
 */
public class RemoveCountryListener implements ActionListener {
	static JFrame jFrame;

	

	@Override
	public void actionPerformed(ActionEvent e) {
		jFrame = new JFrame("Remove Country");
		jFrame.setSize(410, 220);
		JPanel jPanel = new JPanel();
		JLabel ContinentNametoberemoved = new JLabel("Continent Name:");
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
		JLabel jlblCountryNametoberemoved = new JLabel("Country  Name:");
		JPanel jPanelComboBox = new JPanel();
		jPanelComboBox.setLayout(new FlowLayout());
		String[] stringContinent = new String[staticApplicationVariables.gb.map.GetContinents().size()];
		int i = 0;
		for (Object a : staticApplicationVariables.gb.map.GetContinents()) {
			stringContinent[i] = ((Continent) a).GetName();
			i++;
		}
		JComboBox<String> jcomboBoxList = new JComboBox(stringContinent);
		JComboBox<String> jcomboBoxList2 = new JComboBox(
				staticApplicationVariables.gb.map.getCountryListStringForCombobox(
						staticApplicationVariables.gb.map.GetContinents().get(0).GetContinentId()));
		int CountrySelected = 0;
		jcomboBoxList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = ((JComboBox) e.getSource()).getSelectedItem().toString();
				int id = staticApplicationVariables.gb.map.GetContinentIdByName(name);
				System.out.println("selected: " + name);
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel(
						staticApplicationVariables.gb.map.GetCountriesByContinentIdInStrings(id));
				jcomboBoxList2.setModel(model);
				String[] S1 = staticApplicationVariables.gb.map.GetCountriesByContinentIdInStrings(id);
				String S2 = "";
				for (int i = 0; i < S1.length; i++) {
					S2 += S1[i];
				}
				System.out.println("The list populated is : " + S2);
			}
		});

		//ok button action perform
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sName = jcomboBoxList2.getSelectedItem().toString();
				staticApplicationVariables.gb.map
						.RemoveCountry(staticApplicationVariables.gb.map.GetCountryByName(sName));
				JOptionPane.showMessageDialog(null, sName + " country was deleted successfully!!");
				try {
					staticApplicationVariables.gb.SaveMapToFile("output.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				//getting the country which was deleted
				System.out.println("try to get country which was removed : "
						+ staticApplicationVariables.gb.map.GetCountryIdByName(sName));
				jFrame.dispose();
			}
		});
		// cancel button action perform
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		
		jPanel.add(jPanelComboBox);
		jPanelComboBox.add(ContinentNametoberemoved);
		jPanelComboBox.add(jcomboBoxList);
		jPanelComboBox.add(jlblCountryNametoberemoved);
		jPanelComboBox.add(jcomboBoxList2);
		jPanel.add(btnOK);
		jPanel.add(btnCancel);
		jFrame.add(jPanel);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
