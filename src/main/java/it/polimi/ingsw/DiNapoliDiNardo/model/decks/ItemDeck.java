package it.polimi.ingsw.DiNapoliDiNardo.model.decks;

import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;

public class ItemDeck extends Deck{
	public ItemDeck(){
		int i;
		for (i=0; i<2; i++){
			deck.add(new AttackCard());
			}
		for (i=0; i<2; i++){
			deck.add(new TeleportCard());
			}
		for (i=0; i<3; i++){
			deck.add(new SedativesCard());
			}
		for (i=0; i<200; i++){
			deck.add(new LightsCard());
			}
		for (i=0; i<2; i++){
			deck.add(new AdrenalineCard());
			}
		deck.add(new DefenseCard());
		this.shuffleDeck();	
		}
			
}

		//attacco 2
		//teletr 2
		//sedatvi 3
		//spolights 2
		//difesa 1
		//adrenalina 2