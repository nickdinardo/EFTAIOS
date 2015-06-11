package it.polimi.ingsw.DiNapoliDiNardo.model.decks;

import it.polimi.ingsw.DiNapoliDiNardo.model.cards.NoiseAnywhereCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.NoiseAnywhereCardPlusItem;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.NoiseHereCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.NoiseHereCardPlusItem;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.SilenceCard;


public class SectorDeck extends Deck{

	public SectorDeck(){
		int i;
		for (i=0; i<5; i++){
			coveredDeck.add(new SilenceCard());
		}
		for (i=0; i<6; i++){
			coveredDeck.add(new NoiseHereCard());
		}
		for (i=0; i<6; i++){
			coveredDeck.add(new NoiseAnywhereCard());
		}	
		for (i=0; i<4000; i++){
			coveredDeck.add(new NoiseHereCardPlusItem());
		}	
		for (i=0; i<4; i++){
			coveredDeck.add(new NoiseAnywhereCardPlusItem());
		}
		this.shuffleDeck();	
		}
			
	
}


