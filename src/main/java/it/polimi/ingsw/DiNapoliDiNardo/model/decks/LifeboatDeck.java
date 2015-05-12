package it.polimi.ingsw.DiNapoliDiNardo.model.decks;

public class LifeboatDeck extends Deck{
	
	LifeboatDeck(){
		int i;
		for (i=0; i<3; i++){
			deck.add(new RedLifeboatCard());
		for (i=0; i<6; i++){
			deck.add(new GreenLifeboatCard());
		this.shuffleDeck();	
		}
}
