     package com.risk.model;
import java.io.IOException;
import java.util.Random;
/**this class represents Random player strategy implementig the strategy pattern
 * this strategy reinforces,attacks and fortifies randomly.
 * @author Raghav verma
 * @version 1.0.0.0
 */

public class PlayerRandom implements StrategyPlayer{

	@Override
	public String ReinforcementPhase(Player param_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		int numCountries = param_currentPlayer.map.GetCountriesByPlayerId(param_currentPlayer.GetId()).size();
		if(numCountries>0){
			Random rand = new Random();
			int  a = rand.nextInt(numCountries);
			int intNumReinforcement = param_currentPlayer.GetArmies();
			int intRandomReinforcement = rand.nextInt(intNumReinforcement);
			if(a>0){
				Country Crandom = param_currentPlayer.map.GetCountriesByPlayerId(param_currentPlayer.GetId()).get(a);
				if(Crandom!=null && intNumReinforcement>0){
					param_currentPlayer.PlaceReinforcedArmiesOnCountry(Crandom.GetId(), intRandomReinforcement);
				}
				param_currentPlayer.PlaceReinforcedArmiesOnCountry(Crandom.GetId(), intRandomReinforcement);
			}
		}
		param_currentPlayer.DoCardExchange();
		param_currentPlayer.EndReinforcementPhase();
		AttackPhase(param_currentPlayer);
		return null;
	}

	@Override
	public String AttackPhase(Player param_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		Random randomNum = new Random();
		int intNumCountries = param_currentPlayer.map.GetBorderCountriesByPlayerId(param_currentPlayer.GetId()).size();
		if(intNumCountries>0){
			Random rand = new Random();
			int  n = rand.nextInt(intNumCountries);
			if(n>0){
				Country randomC = param_currentPlayer.map.GetBorderCountriesByPlayerId(param_currentPlayer.GetId()).get(n);
				int nDefender = rand.nextInt(param_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(randomC.GetId()).size());
				Country defenderC = param_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(randomC.GetId()).get(nDefender);
				while(randomC.GetArmies()>2 && defenderC.playerId!=param_currentPlayer.GetId() && randomNum.nextInt(2)==1){
					param_currentPlayer.PerformDeduction(param_currentPlayer, randomC, randomC.GetArmies()>3?3:randomC.GetArmies(), defenderC, randomC.GetArmies()>2?2:randomC.GetArmies());
					
				}
			}
		}
		param_currentPlayer.EndAttackPhase();
    	param_currentPlayer.Fortification();
		return null;
	}

	@Override
	public String FortificationPhase(Player param_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		int numCountries = param_currentPlayer.map.GetCountriesByPlayerId(param_currentPlayer.GetId()).size();
		if(numCountries>0){
			Random rand = new Random();
			int  n = rand.nextInt(numCountries);
			int numReinforcement = param_currentPlayer.GetArmies();
			int randomReinforcement = rand.nextInt(numReinforcement);
			Country randomC = param_currentPlayer.map.GetCountriesByPlayerId(param_currentPlayer.GetId()).get(n);
			param_currentPlayer.PlaceReinforcedArmiesOnCountry(randomC.GetId(), randomReinforcement);
		}
		param_currentPlayer.EndFortificationPhase();
		param_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
		return null;
	}

}
