package it.polimi.ingsw.DiNapoliDiNardo.model.decks;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;

public class LifeboatDeck extends Deck{
	
	public LifeboatDeck(){
		int i;
		for (i=0; i<3; i++){
			deck.add(new RedLifeboatCard());
			}
		for (i=0; i<3; i++){
			deck.add(new GreenLifeboatCard());
			}
		this.shuffleDeck();	
		}
}
