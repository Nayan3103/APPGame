package com.risk.model;

import java.io.IOException;

import com.risk.utility.LoggingWindow;
/**this class represents Benevolent player strategy implementig the strategy pattern
 * this strategy focuses on protecting its weak countries.
 * @author Raghav verma
 * @version 1.0.0.0
 */
public class PlayerBenevolent implements StrategyPlayer{
	//constructor
	public PlayerBenevolent(){
		
	}

	@Override
	public String ReinforcementPhase(Player param_currentPlayer) throws Exception {
		// TODO Auto-generated method stub
		int nWeakCountries = param_currentPlayer.map.GetWeakCountriesByPlayerId(param_currentPlayer.GetId()).size();
		int totalArmies = param_currentPlayer.GetArmies();
		if(nWeakCountries>0){
			for(Country cnt : param_currentPlayer.map.GetWeakCountriesByPlayerId(param_currentPlayer.GetId())){
				cnt.AddArmies(totalArmies/nWeakCountries);
				if (param_currentPlayer.GetArmies() >= totalArmies/nWeakCountries) {
					param_currentPlayer.map.AddArmiesToCountry(cnt.GetId(), totalArmies/nWeakCountries);
					param_currentPlayer.AddArmies(-1 * totalArmies/nWeakCountries);
				}
				else if(param_currentPlayer.GetArmies()>0){
					param_currentPlayer.map.AddArmiesToCountry(cnt.GetId(), param_currentPlayer.GetArmies());
					param_currentPlayer.AddArmies(-1 * param_currentPlayer.GetArmies());
				}
			}
		}
		param_currentPlayer.DoCardExchange();
		param_currentPlayer.EndReinforcementPhase();
		AttackPhase(param_currentPlayer);
		return null;
	}

	@Override
	public String AttackPhase(Player prm_currentPlayer) throws Exception {
		// TODO Auto-generated method stub
		LoggingWindow.Log("Benevolent player skips Attack phase");
		prm_currentPlayer.EndAttackPhase();
		FortificationPhase(prm_currentPlayer);
		return null;
	}

	@Override
	public String FortificationPhase(Player prm_currentPlayer)
			throws IOException, Exception {
		// TODO Auto-generated method stub
		Country cntWeakest = prm_currentPlayer.map.GetWeakestCountry(prm_currentPlayer.GetId());
		Country cntHighest = prm_currentPlayer.map.GetHighArmiesCountry(prm_currentPlayer.GetId());
		if(cntWeakest!=null && cntHighest!=null && cntHighest.GetArmies()>2){
			prm_currentPlayer.MoveArmiesToCountryFromCountry(cntHighest.GetId(), cntWeakest.GetId(), cntHighest.GetArmies()-2);
		}
		prm_currentPlayer.EndFortificationPhase();
		prm_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
		return null;
	}

}
