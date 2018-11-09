package com.risk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class is used for the lower window of the main frame 
 * 
 */
public class GuiPanelGame extends JPanel{

	public GuiPanelGame(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.CYAN);
		this.setLayout(new FlowLayout());

	
	}

}
