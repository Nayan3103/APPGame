package com.risk.model;
import java.util.List;
import java.util.Observable;

/**
 * this class provides an observable object for exchange view it shows the vards
 * of hand and its change
 * 
 * @author Raghav
 * @version 1.0
 */
public class ExchangeCard extends Observable {
	public List<Cards> hands;
	
	
	//default constructor
	public ExchangeCard() {
	}

	/**
	 * this cunstruct builds the object and sets the hand cards
	 * 
	 * @param param_hand
	 *            cards in hand
	 */
	public ExchangeCard(List<Cards> param_hand) {
		this.hands = param_hand;
	}

	/**
	 * this method updates the hand after successful exchange
	 * 
	 * @param param_hand
	 *            updated cards in hand
	 */
	public void UpdateHandCards(List<Cards> param_hand) {
		this.hands = param_hand;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * this method returns the hand
	 * 
	 * @return hand cards
	 */
	public List<Cards> GetCards() {
		return this.hands;
	}

}
