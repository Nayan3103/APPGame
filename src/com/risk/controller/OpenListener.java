package com.risk.controller;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.Edge;
import com.risk.model.GameBoard;
import com.risk.utility.MapParser;
import com.risk.utility.staticApplicationVariables;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
//import org.jgraph.graph.GraphLayoutCache;

import com.risk.view.MainWindow;

/**
 * 
 * "File" menu of the main user interface.
 *  It's responsible for requesting the user for a file to open, and then tries to open it and display it in the main
 * user interface. 
 * @author Manjinder Kaur
 */
public class OpenListener implements ActionListener {

	private MainWindow awgui;

	/**
	 * Constructor.
	 */
	public OpenListener() {
		awgui = MainWindow.getInstance();
	}

	/**
	 * 
	 * @param actionEvent
	 *            Not used.
	 */

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		JFileChooser jfChooser = new JFileChooser();

		// user can select only one file.
		jfChooser.setMultiSelectionEnabled(false);
		jfChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// user can select files with the map extension only.
		FileNameExtensionFilter fnefMapFileFilter = new FileNameExtensionFilter("Map files", "map");
		jfChooser.setFileFilter(fnefMapFileFilter);
		int selection = jfChooser.showOpenDialog(awgui.getContentPane());

		// If the user has selected a file.
		if (selection != JFileChooser.CANCEL_OPTION) {
			try {
				File file = jfChooser.getSelectedFile();
				System.out.println();
				staticApplicationVariables.FILENAME = file.getPath();
				GameBoard gameboard = GameBoard.GetGameBoard();
				// MapParser mp = new MapParser();
				gameboard.map = MapParser.MapParser(staticApplicationVariables.FILENAME);
				staticApplicationVariables.gb = gameboard;
				MapParser.WriteMapObjToFile(gameboard.map, "output.txt");

				/*
				 * user can save his work!
				 *  If the user chooses to proceed, all of his/her
				 * work is lost forever.
				 */
				int intUserResponse = JOptionPane.showConfirmDialog(awgui.getContentPane(),
						"If you open this file without saving the one you're\n"
								+ "already working on, all of your work will be lost.\n"
								+ "Click select yes if you don't want to save your work.\n",
						"Open Without Saving Confirmation", JOptionPane.YES_NO_OPTION);
				if (intUserResponse == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(awgui.getContentPane(), "File has been Uploaded sucessfully! ", "MESSAGE",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(awgui.getContentPane(), "Unable to Upload Map file", "MESSAGE",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (FileNotFoundException fnfe) {
				JOptionPane.showMessageDialog(awgui.getContentPane(), fnfe.getMessage(), "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
