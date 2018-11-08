package com.risk.controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.risk.model.Continent;
import com.risk.model.Map;
import com.risk.utility.staticApplicationVariables;

/**
 * This class belongs to a listener which removes a continent from the map file based
 * on the events captured from the user interface.
 *  
 * @author Raghav Verma
 *  * @version 1.0
 */
public class RemoveContinentListener extends JFrame implements ActionListener {
	static JFrame jFrame;
	JLabel jlblContinentToBeRemoved;

	

	@Override   
	public void actionPerformed(ActionEvent e) {
		jFrame = new JFrame("Remove Continent Frame");
		jFrame.setSize(360, 210);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel.setBorder(new EmptyBorder(25, 15, 15, 15));
		jlblContinentToBeRemoved = new JLabel("Select the Continent: ");
		JPanel jPanelComboBox = new JPanel();
		jPanelComboBox.setLayout(new FlowLayout());
		String[] stringContinent = new String[staticApplicationVariables.gb.map.GetContinents().size()];
		int i = 0;
		for (Object a : staticApplicationVariables.gb.map.GetContinents()) {
			stringContinent[i] = ((Continent) a).GetName();
			i++;
		}
		JComboBox<String> jcomboBoxList = new JComboBox<>(stringContinent);
		//ok button action perform
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("OK button pressed:");
				String sName = jcomboBoxList.getSelectedItem().toString();
				Continent continent = new Continent(sName, 25);
				System.out.println(continent.GetName());
				System.out.println("Before" + staticApplicationVariables.gb.map.GetContinents().size());
				staticApplicationVariables.gb.map
						.RemoveContinent(staticApplicationVariables.gb.map.GetContinentByName(sName));
				try {
					staticApplicationVariables.gb.SaveMapToFile("output.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, continent.GetName() + " Continent was deleted!");
				System.out.println("After " + staticApplicationVariables.gb.map.GetContinents().size());
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
		
		jPanel.add(btnOK);
		jPanel.add(btnCancel);
		jPanelComboBox.add(jcomboBoxList);
		jPanel.add(jPanelComboBox);
		
		jFrame.add(jPanel);
		jPanel.add(jPanelComboBox);
		jPanel.add(btnOK);
		jPanel.add(btnCancel);
		
		jPanelComboBox.add(jcomboBoxList);
		jPanel.add(jlblContinentToBeRemoved);
		jFrame.setVisible(true);
		jFrame.add(jPanel);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
