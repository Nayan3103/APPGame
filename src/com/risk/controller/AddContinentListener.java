package com.risk.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.GameBoard;
import com.risk.model.Map;
import com.risk.utility.staticApplicationVariables;
import com.risk.view.MainWindow;

/**
 * This class belongs to a listener which performs action of adding a continent
 * based on the events captured from the front end.
 * @author Nayan
 * @version 1.0
 */

public class AddContinentListener implements ActionListener {
	static JFrame jframe;

	@Override
	/**
	 * Causes a new window to Pop-up. This window then asks the user to indicate the
	 * Continent name, the Control value. The control value must be set as Integer.
	 * @param actionEvent Not used.
	 */
	public void actionPerformed(ActionEvent e) {
		jframe = new JFrame("Add Continent Frame");
		jframe.setSize(350, 200);

		

		JLabel jContinentcontrolLabel = new JLabel("Control Value:");

		JTextField jtfContinentfield = new JTextField(10);

		JLabel jContinentLabel = new JLabel("Continent Name:");

		JTextField jtfNameField = new JTextField(15);
		
		JPanel jPanel = new JPanel();

		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JButton btnOK = new JButton("OK");

		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (staticApplicationVariables.gb.map.DoesExistContinent(jtfNameField.getText()) == true) {
					JOptionPane.showMessageDialog(null, "Continent " + jtfNameField.getText() + " Exists!");
				} else {
					String Sopt = staticApplicationVariables.gb.map.AddContinent(jtfNameField.getText(),
							Integer.parseInt(jtfContinentfield.getText()));
					System.out.println(Sopt);
					System.out.println(staticApplicationVariables.gb.map.lands.size());
					JOptionPane.showMessageDialog(null, "Continent with name " + jtfNameField.getText()
							+ ",Control value : " + jtfContinentfield.getText() + " is successfully added!");

					try {
						staticApplicationVariables.gb.SaveMapToFile("output.txt");
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					jframe.dispose();
				}

			}
		});

		JButton btnCancel = new JButton("Cancel");

		/**
		 *  frame to be closed on press of cancel button.
		 * 
		 * @param actionEvent
		 *            Not used.
		 */
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();
			}
		});

		
		jPanel.add(jtfContinentfield);
		jPanel.add(btnOK);
		jPanel.add(btnCancel);
		jPanel.add(jContinentLabel);
		jPanel.add(jtfNameField);
		jPanel.add(jContinentcontrolLabel);
		jframe.add(jPanel);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
