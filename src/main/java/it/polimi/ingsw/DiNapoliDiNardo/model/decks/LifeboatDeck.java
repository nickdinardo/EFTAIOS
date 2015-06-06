package it.polimi.ingsw.DiNapoliDiNardo.model.decks;

import it.polimi.ingsw.DiNapoliDiNardo.model.cards.GreenLifeboatCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.RedLifeboatCard;


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
