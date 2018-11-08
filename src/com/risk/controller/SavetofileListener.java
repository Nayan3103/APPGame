package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.risk.utility.staticApplicationVariables;

/**
 * This class belongs to a listener which saves all the data to the text file
 * 
 * @author Sukhmeet
 * @version 1.0
 */
public class SavetofileListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			//saving map file to the text file
			staticApplicationVariables.gb.SaveMapToFile("output.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
