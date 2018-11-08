package com.risk.model;

import java.io.IOException;

public class AggressivePlayer implements StrategyPlayer{

	@Override
	public String ReinforcementPhase(Player param_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		Country strongestC = null;
		int armies=0;
		for(Country cnt : param_currentPlayer.map.GetBorderCountriesByPlayerId(param_currentPlayer.GetId())){
			if(cnt.GetArmies()>=armies){
				strongestC=cnt;
				armies=cnt.GetArmies();
			}
		}
		if(strongestC!=null){
			param_currentPlayer.PlaceReinforcedArmiesOnCountry(strongestC.GetId(), param_currentPlayer.GetArmies());
		}
		param_currentPlayer.DoCardExchange();
		param_currentPlayer.EndReinforcementPhase();
		AttackPhase(param_currentPlayer);
		return null;
	}

	@Override
	public String AttackPhase(Player param_currentPlayer) throws IOException, Exception {
		// TODO Auto-generated method stub
		Country cntStrongest = new Country();
		Country cntDefender = new Country();
		int armies=0;
		for(Country c : param_currentPlayer.map.GetBorderCountriesByPlayerId(param_currentPlayer.GetId())){
			if(c.GetArmies()>=armies){
				cntStrongest=c;
				armies=c.GetArmies();
			}
		}
		if(cntStrongest!=null){
			while(cntStrongest.GetArmies()>2){
				armies=100000;
				for(Country defender : param_currentPlayer.map.GetNeighborsByCountryIdOpponentPlayer(cntStrongest.GetId())){
					if(defender.GetArmies()<=armies){
						cntDefender=defender;
						armies=defender.GetArmies();
					}
				}
				if(cntDefender!=null){
					param_currentPlayer.PerformDeduction(param_currentPlayer, cntStrongest, cntStrongest.GetArmies()>3?3:cntStrongest.GetArmies(), cntDefender, cntStrongest.GetArmies()>2?2:cntStrongest.GetArmies());
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
		Country cntStrongest = new Country();
		int armies=0;
		for(Country cnt : param_currentPlayer.map.GetBorderCountriesByPlayerId(param_currentPlayer.GetId())){
			if(cnt.GetArmies()>=armies){
				cntStrongest=cnt;
				armies=cnt.GetArmies();
			}
		}
		armies=2;
		Country countryS = null;
		if(cntStrongest!=null){
			for(Country neighbor : param_currentPlayer.map.GetNeighborsByCountryIdSamePlayer(cntStrongest.GetId())){
				if(neighbor.GetArmies()>=armies){
					countryS=neighbor;
					armies=countryS.GetArmies();
				}
			}
		}
		if(cntStrongest!=null && countryS!=null){
			if(countryS.GetArmies()>2){
				param_currentPlayer.MoveArmiesToCountryFromCountry(countryS.GetId(), cntStrongest.GetId(), countryS.GetArmies()-2);
			}
		}
		param_currentPlayer.EndFortificationPhase();
		param_currentPlayer.turnOrganizer.GetCurrentPlayer().Reinforcement();
		return "";
	}

}
