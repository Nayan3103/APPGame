   package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.risk.utility.LoggingWindow;
/**this class represents cheater player strategy implementig the strategy pattern
 * 
 * @author Raghav verma
 * @version 1.0.0.0
 */
public class PlayerCheater implements StrategyPlayer{

	@Override
	public String ReinforcementPhase(Player param_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		for(Country cnt : param_currentPlayer.map.GetCountriesByPlayerId(param_currentPlayer.GetId())){
			cnt.AddArmies(cnt.GetArmies());
		}
		param_currentPlayer.DoCardExchange();
		param_currentPlayer.EndReinforcementPhase();
		AttackPhase(param_currentPlayer);
		return "";
	}

	@Override
	public String AttackPhase(Player param_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		List<Country> listNeighbors = new ArrayList<Country>();
		for(Country c : param_currentPlayer.map.GetCountriesByPlayerId(param_currentPlayer.GetId())){
			for(Country cntNeighbor : param_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(c.GetId())){
				listNeighbors.add(cntNeighbor);
			}
		}
		for(Country cnt : listNeighbors){
			param_currentPlayer.map.ConquerCountry(cnt.GetId(), param_currentPlayer.GetId());
			LoggingWindow.Log("country: "+cnt.GetName()+" occupied by player"+param_currentPlayer.GetName());
		}
		boolean isWorldCaptured = param_currentPlayer.map.IsWorldCaptured(param_currentPlayer.GetId());
		if(isWorldCaptured){
			System.out.println("Game is ended the winner is: " + param_currentPlayer.GetName());
			param_currentPlayer.turnOrganizer.winner=param_currentPlayer.GetName();
			return "";
		}
		param_currentPlayer.EndAttackPhase();
		param_currentPlayer.turnOrganizer.isAttackSuccessfull=true;
		FortificationPhase(param_currentPlayer);
		return null;
	}

	@Override
	public String FortificationPhase(Player param_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		for(Country c: param_currentPlayer.map.GetBorderCountriesByPlayerId(param_currentPlayer.GetId())){
			c.AddArmies(c.GetArmies());
		}
		param_currentPlayer.EndFortificationPhase();
		param_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
		return "";
	}

}
