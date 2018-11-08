package com.risk.model;

/**
 * this class simulate the method to roll dice
 * 
 * @author Ravneet
 * @version 1.0
 */
public class Dice implements Comparable {
	private int dice;

	//standard json constructor
	public Dice() {
	}

	/**
	 * this method simulate the rolling the dice and set the dice property of
	 * the object
	 * 
	 * @return is the result of the rolling the dice
	 */
	//rolling dice using some mathematical formula
	public int RollDice() {
		return dice = (int) (Math.random() * 6 + 1);
	}

	
	/**
	 * this method makes the dice objects comparable
	 * and it is used to sort dices
	 */
	@Override
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		if (this.dice == ((Dice) obj).GetDice())
			return 0;
		else if (this.dice > ((Dice) obj).GetDice())
			return 1;
		else if (this.dice < ((Dice) obj).GetDice())
			return -1;
		return 0;
	}

	/**
	 * this method returns the result of the rolling the dice
	 * 
	 * @return is the result of the dice
	 */
	public int GetDice() {
		return this.dice;
	}

}

