package com.risk.model;

import java.util.Observable;
import java.util.Observer;

import com.risk.utility.TurnPhases;

/**
 * this class represents observer for the phase view
 * 
 * @author Ravneet
 * @version 1.0.0.0
 */
public class ObserverPhaseView implements Observer {
	String sPhaseView = "";
	
	//Standard Json constructor
	public  ObserverPhaseView(){
		
	}

	@Override
	public void update(Observable obj, Object arg) {
		// TODO Auto-generated method stub
		TurnOrganizer turnOrganizer = (TurnOrganizer) obj;
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Startup) {
			sPhaseView = "Current Phase: " + turnOrganizer.GetCurrentPhase() + "\n" + "Current Player: "
					+ turnOrganizer.GetCurrentPlayer().GetName();
		} else {
			sPhaseView = "Current Phase: " + turnOrganizer.GetCurrentPhase();
			if(turnOrganizer.GetCurrentPlayerId()!=-1)
				sPhaseView+="\n" + "Current Player: " + turnOrganizer.GetCurrentPlayer().GetName();
		}
		System.out.println("_____Phase View_____");
		System.out.println(sPhaseView);
		System.out.println("_____End of Phase View_____");
	}

	public void GetPhaseView() {
		System.out.println(sPhaseView);
	}

}
