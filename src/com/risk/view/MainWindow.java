package com.risk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//simport org.jgraph.JGraph;

import com.risk.controller.AddContinentListener;
import com.risk.controller.AddCountryListener;
import com.risk.controller.CreateMapfromGUI;
import com.risk.controller.EndReinforcementPhaseListener;
import com.risk.controller.ExchangeCardsforAplayerListener;
import com.risk.controller.Numberofplayers;
import com.risk.controller.OpenListener;
import com.risk.controller.PlaceArmiesonaCountry;

import com.risk.controller.RemoveContinentListener;
import com.risk.controller.RemoveCountryListener;
import com.risk.controller.SavetofileListener;
import com.risk.controller.ShowCurrentPlayer;
import com.risk.controller.ShowPlayerCountries;
import com.risk.utility.staticApplicationVariables;

/**
 * This class defines the main window or gui of our program. 
 * It defines the main components of the user interface. 
 *author nayan
 */
public class MainWindow extends JFrame {

	public MainWindow() {
	}

	private JMenuBar jMenuBar;
	private static MainWindow instance = null;
	private JMenu jGameMenu;

	private JMenu jFileMenu;
	private JMenu jEditMenu;
	private JMenu jPlayMenu;

	private JMenu jHelpMenu;
	private JMenuItem jmiOpen;
	private JMenuItem jmiSave;
	private JMenuItem jmiPlacearmiesoncountry;
	private JMenuItem jmiAddContinent;
	private JMenuItem jmiAddCountry;
	private JMenuItem jmiRemoveContinent;
	private JMenuItem jmiEndreinforcementphase;
	private JMenuItem jmiRemoveCountry;
	private JMenuItem jmiShowplayercountries;
	private JMenuItem jmiAddPlayer;
	private JMenuItem jmiExchangeCardsforplayer;
	private JMenuItem jmiCreateMap;
	private JMenuItem jmiShowCurrentPlayer;
	private JMenuItem jmiNumberofplayers;
	private JMenuItem jmiStartgame;
	private JMenuItem jmiHelp;
	private JMenuItem jmiStart, jmiPause;

	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}

	public void open() {
		// CREATING FRAME and Setting
		JFrame mainFrame = new JFrame("Map Editor and Game Board");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// Uses the specific input screen of the as per the user
		staticApplicationVariables.SCREEN_WIDTH = (int) (dim.width * 0.9);
		staticApplicationVariables.SCREEN_HEIGHT = (int) (dim.height * 0.9);
		mainFrame.setSize(staticApplicationVariables.SCREEN_WIDTH, staticApplicationVariables.SCREEN_HEIGHT);
		mainFrame.setLocation(dim.width / 2 - staticApplicationVariables.SCREEN_WIDTH / 2,
				dim.height / 2 - staticApplicationVariables.SCREEN_HEIGHT / 2);
		mainFrame.setLayout(new FlowLayout());
		jMenuBar = new JMenuBar();
		jFileMenu = new JMenu("File");
		jEditMenu = new JMenu("Edit");
		jGameMenu = new JMenu("Game");
		jHelpMenu = new JMenu("Help");
		jPlayMenu = new JMenu("Play");

		
		jmiCreateMap = new JMenuItem("Create Map");
		jmiShowCurrentPlayer = new JMenuItem("Show Current Player");
		jmiPlacearmiesoncountry = new JMenuItem("Place Armies On Country");
		jmiPlacearmiesoncountry = new JMenuItem("Place Armies On Country");
		jmiExchangeCardsforplayer = new JMenuItem("Exchange Cards");
		jmiOpen = new JMenuItem("Open");
		jmiSave = new JMenuItem("Save to file");
		jmiAddContinent = new JMenuItem("Add Continent");
		jmiAddCountry = new JMenuItem("Add  Country");
		jmiRemoveContinent = new JMenuItem("Remove Continent");
		jmiRemoveCountry = new JMenuItem("Remove Country");
		jmiShowplayercountries = new JMenuItem("Show Player Countries");
		jmiNumberofplayers = new JMenuItem(" Start Game");
		jmiEndreinforcementphase = new JMenuItem("End reinforcement phase");
		/*
		 * Create the menu items for the simulation menu.
		 */
		jmiStart = new JMenuItem("Start");
		jmiPause = new JMenuItem("Pause");

		jmiHelp = new JMenuItem("Help");
		
		jFileMenu.add(jmiOpen);
		jFileMenu.addSeparator();
		
		jFileMenu.add(jmiSave);
		jFileMenu.add(jmiCreateMap);

		jEditMenu.add(jmiAddContinent);
		jEditMenu.add(jmiRemoveContinent);
		jEditMenu.addSeparator();
		jEditMenu.add(jmiAddCountry);
		jGameMenu.add(jmiShowplayercountries);
		jGameMenu.add(jmiEndreinforcementphase);
		jEditMenu.add(jmiRemoveCountry);
		jGameMenu.add(jmiNumberofplayers);
		jGameMenu.add(jmiShowCurrentPlayer);
		
		jGameMenu.add(jmiExchangeCardsforplayer);
		jPlayMenu.add(jmiPlacearmiesoncountry);
		jHelpMenu.add(jmiHelp);
		jMenuBar.add(jHelpMenu);
		jMenuBar.add(jFileMenu);
		jMenuBar.add(jEditMenu);
		jMenuBar.add(jGameMenu);
		jMenuBar.add(jPlayMenu);
		
		jmiOpen.addActionListener(new OpenListener());
		jmiShowplayercountries.addActionListener(new ShowPlayerCountries());
		jmiAddContinent.addActionListener(new AddContinentListener());
		jmiRemoveContinent.addActionListener(new RemoveContinentListener());
		jmiAddCountry.addActionListener(new AddCountryListener());
		jmiRemoveCountry.addActionListener(new RemoveCountryListener());
		jmiSave.addActionListener(new SavetofileListener());
		jmiShowCurrentPlayer.addActionListener(new ShowCurrentPlayer());
		jmiNumberofplayers.addActionListener(new Numberofplayers());
		jmiCreateMap.addActionListener(new CreateMapfromGUI());
		
		jmiPlacearmiesoncountry.addActionListener(new PlaceArmiesonaCountry());
		jmiEndreinforcementphase.addActionListener(new EndReinforcementPhaseListener());
		jmiExchangeCardsforplayer.addActionListener(new ExchangeCardsforAplayerListener());
		mainFrame.setJMenuBar(jMenuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}