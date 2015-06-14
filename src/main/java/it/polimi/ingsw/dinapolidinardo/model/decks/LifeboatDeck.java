package it.polimi.ingsw.dinapolidinardo.model.decks;

import it.polimi.ingsw.dinapolidinardo.model.cards.GreenLifeboatCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.RedLifeboatCard;

/**
 * Class that represent the LifeBoat Deck of the game
 */
public class LifeboatDeck extends Deck{
	
	/**
	 * Constructor that initialize the deck filling it with the specified amount of any card
	 */
	public LifeboatDeck(){
		int i;
		for (i=0; i<3; i++){
			coveredDeck.add(new RedLifeboatCard());
			}
		for (i=0; i<3; i++){
			coveredDeck.add(new GreenLifeboatCard());
			}
		this.shuffleDeck();	
		}
}
