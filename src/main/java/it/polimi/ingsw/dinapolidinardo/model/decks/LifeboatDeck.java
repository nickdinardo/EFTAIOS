package it.polimi.ingsw.dinapolidinardo.model.decks;

import it.polimi.ingsw.dinapolidinardo.model.cards.GreenLifeboatCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.RedLifeboatCard;


public class LifeboatDeck extends Deck{
	
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
