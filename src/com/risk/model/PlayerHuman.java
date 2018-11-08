package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.risk.utility.LoggingWindow;
import com.risk.utility.TurnPhases;
/**this class represent a human player strategy implemented strategy pattern
 * 
 * @author Nayan
 * 
 * @version 1.0.0.0
 */
public class PlayerHuman implements StrategyPlayer {
	//constructor
	public PlayerHuman(){
		
	}

	@Override
	/**reinforcement for human player
	 * 
	 */
	public String ReinforcementPhase(Player param_currentPlayer) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		int option = 1;
		Scanner scInInteger = new Scanner(System.in); 
		Scanner inString = new Scanner(System.in); 
		Country cntWeakest = new Country();
	    while(option==1 && param_currentPlayer.GetArmies()>1){
			System.out.println("Enter options: 1:to reinforcement, 2:End reinforcement, 0:to quit the game");
			option = scInInteger.nextInt();
			if(option==1){
				System.out.println("An option to reinforcement:");
				cntWeakest = param_currentPlayer.map.GetWeakestCountry(param_currentPlayer.GetId());
				System.out.println("An option to reinforcement: candidate country: "+cntWeakest.GetName()+", its armies: "+cntWeakest.GetArmies()+" remaining armies of the player: "+param_currentPlayer.GetArmies());
				System.out.println("Enter option: 1:To perform suggested reinforcement enter, 2:end reinforcement, 0:to quit");
				option = scInInteger.nextInt();
				if(option==1){
					System.out.println("Enter number of armies to reinforcement between 1 and "+param_currentPlayer.GetArmies());
					int intArmies = scInInteger.nextInt();
					param_currentPlayer.ReinforcementCountry(cntWeakest.GetId(), intArmies);
				}
			}
		}
		
	    param_currentPlayer.DoCardExchange();
	    param_currentPlayer.EndReinforcementPhase();
	    AttackPhase(param_currentPlayer);
	    return "FailedNotEnoughArmies";
	}
	
	@Override
	/**attack for human player
	 * 
	 */
	public String AttackPhase(Player param_currentPlayer) throws Exception {
		String result = "";
		int quit = -1;
		Scanner inInteger = new Scanner(System.in); 
		Scanner inString = new Scanner(System.in); 
		Country weakest = new Country();
		int option=1;
	    while(option ==1){
	    	System.out.println("Enter options: 1:to attack, 2:end attack, 0:to quit the game");
			option = inInteger.nextInt();
			if(option==1){
				//getting id of current player
				Country cntAttacker = param_currentPlayer.map.GetBorderCountryByPlayerId(param_currentPlayer.GetId());
				Country cntDefender = param_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(cntAttacker.GetId()).get(0);
				if(cntAttacker!=null && cntDefender!=null){
					System.out.println("Declare attack(specifying the attacker and defender country):");
					System.out.println("A suggested option is: attacker country: "+cntAttacker.GetName()+" defender country: "+cntDefender.GetName());
					System.out.println("Suggested option accepted: 1:yes, 0:no");
					option = inInteger.nextInt();
					if(option==1){
						int result1 = cntAttacker.GetArmies()>=3 ? 3:cntAttacker.GetArmies();
						int result2 = cntAttacker.GetArmies()>=2 ? 2:cntAttacker.GetArmies();
						System.out.println("Decide dices to roll: attacker dices<="+result1+" defender dices <=" +result2);
						System.out.println("enter attacker dice:");
						int intAttackerDice = inInteger.nextInt();
						System.out.println("enter defender dice:");
						int defenderDice = inInteger.nextInt();
						if(intAttackerDice<=result1 && defenderDice<=result2){
							param_currentPlayer.PerformDeduction(param_currentPlayer, cntAttacker, intAttackerDice, cntDefender, defenderDice);
						}
						if(cntAttacker.GetArmies()<2) option=2;
					}
				}
			}
		}
	    if(option==2){
	    	param_currentPlayer.EndAttackPhase();
	    	param_currentPlayer.Fortification();
	    }
		return "";
	}
	
	
	@Override
	/**fortification for human player
	 * 
	 */
	public String FortificationPhase(Player param_currentPlayer) throws Exception {
		// TODO Auto-generated method stub
		// tbd
				if (param_currentPlayer.turnOrganizer.GetCurrentPhase() != TurnPhases.Fortification)
					return "PhaseNotValid";
				Country countryS = param_currentPlayer.map.GetHighArmiesCountry(param_currentPlayer.GetId());
				if(countryS!=null){
					if(param_currentPlayer.map.GetNeighborsByCountryIdSamePlayer(countryS.GetId()).size()>0){
						Country countryD = param_currentPlayer.map.GetNeighborsByCountryIdSamePlayer(countryS.GetId()).get(0);
						int countrySArmies = param_currentPlayer.map.GetCountryById(countryS.GetId()).GetArmies();
						int countryDArmies = param_currentPlayer.map.GetCountryById(countryD.GetId()).GetArmies();
						if (!param_currentPlayer.map.IsNeighborByCountryId(countryS.GetId(), countryD.GetId())) {
							LoggingWindow.Log("The two countries are not connected to move armies between them");
							return "Countries are not connected";
						}
						LoggingWindow.Log("The two countries are connected to move armies between them");
						int armies = countryS.GetArmies()>2?2:0;
						if (countrySArmies < armies-2) {
							LoggingWindow.Log("The source country does not have at least tw0 country after fortification");
							return "The source country should keep one country after fortification";
						}
						LoggingWindow.Log("The source country has at least one country after fortification");
						if (param_currentPlayer.map.GetCountryById(countryS.GetId()).playerId != param_currentPlayer.map.GetCountryById(countryD.GetId()).playerId) {
							LoggingWindow.Log("The two country for fortification do not belong the same player");
							return "The two country for fortification do not belong the same player";
						}
						param_currentPlayer.map.GetCountryById(countryS.GetId()).AddArmies(-1 * armies);
						param_currentPlayer.map.GetCountryById(countryD.GetId()).AddArmies(armies);
						if(param_currentPlayer.turnOrganizer.IsAttackSuccessful()){
							LoggingWindow.Log(
									"The fortification is done, one card was drawn because a country was captured from attack and turn is ended");
						}
					}
					
				}
				param_currentPlayer.EndFortificationPhase();
				param_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
				return "Fortification is done successfully";
	}

}
