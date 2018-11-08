package com.risk.controller;

import java.awt.Container;

import java.awt.FlowLayout;
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

import com.risk.model.GameBoard;
import com.risk.utility.staticApplicationVariables;

/**
 * This class is a controller which creates map from GUI
 * 
 * @author Nayan
 * @version 1.0
 */

public class CreateMapfromGUI implements ActionListener {
	JFrame jFrame;
	JPanel jPanel;

	JLabel jlblFileName, jlblCountryName, jBanner;
	JTextField jtfCountryName, jtfContinentName;
	JButton btnOK, butnCancel;

	/**
	 * new window to Pop-up. This window asks the user to write the
	 * Continent name, the Control value. 
	 * The control value must be set as Integer.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		jFrame = new JFrame("Create Map");
		jFrame.setSize(250, 250);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(25, 15, 15, 15));
		jlblFileName = new JLabel("Do You Want to Create Map");
		JButton btnOK = new JButton("CREATE MAP");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameBoard.GetGameBoard().CreateMap();
				JOptionPane.showMessageDialog(null, "Please Add Countries and Continents and then Save file!");
				jFrame.dispose();
			}
		});
		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		panel.add(jlblFileName);
		panel.add(btnOK);
		panel.add(btnCancel);
		jFrame.add(panel);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
