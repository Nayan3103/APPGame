package com.risk.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.risk.utility.*;
import com.risk.view.MainWindow;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//MainWindow appWindow = new MainWindow();
		System.out.println("Game started");
		//appWindow.open();
		//Demo_StartUp_Reinforcement();
		//Demo_LoadGameFromFile();
		//Demo_StartUp_SaveToFile();
	   Console_Game();
		
	}
	public static void Console_Game() throws Exception{
		Scanner scInteger = new Scanner(System.in); 
		Scanner scString = new Scanner(System.in); 
	    GameBoard gb = GameBoard.GetGameBoard();
		gb.GameEntry();
		System.out.println("Quit or load a game from file,Please select the options: 0 for Quit, 1 for already saved game");
		int intNewOrLoad = scInteger.nextInt();
		if(intNewOrLoad==1){
			scInteger = new Scanner(System.in);
			System.out.println("Enter name of the file in which a game has already been saved:");
			String sFile = scString.next();
			gb = GameBoard.LoadGameFromFile(sFile);
			switch(gb.turnOrganizer.GetCurrentPhase()){
				case Reinforcement:
					gb.turnOrganizer.GetCurrentPlayer().Reinforcement();
					break;
				case Attack:
					gb.turnOrganizer.GetCurrentPlayer().Attack();
					break;
				case Fortification:
					gb.turnOrganizer.GetCurrentPlayer().Fortification();
					break;
					
			}
		}
	}
	
	

}
