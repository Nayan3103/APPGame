package com.risk.model;

import java.util.Observable;
import java.util.Observer;

/**this class represents observer class for card exchange view
 * it looks at CardExchange observable to show the current cards of player in hand
 * @author Ravneet Singh Brar
 *@version 1.0.0.0
 */
public class CardExchangeObserver implements Observer{

	private String sHand="";
	//this method updates the state once is notified
	  
	
	//constructor
	public CardExchangeObserver(){
		
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		sHand = "";
		ExchangeCard exchangeCard = (ExchangeCard)o;
		for(Cards card : exchangeCard.hands){
			sHand+=card.GetType().toString();
		}
		if(sHand!=""){
			System.out.println("_____Card Exchange View_____");
			System.out.println(sHand);
			System.out.println("_____End of Card Exchange View_____");
		}
		
	}

}
