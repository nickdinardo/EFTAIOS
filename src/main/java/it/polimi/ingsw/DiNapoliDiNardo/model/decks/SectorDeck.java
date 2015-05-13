package it.polimi.ingsw.DiNapoliDiNardo.model.decks;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;

public class SectorDeck extends Deck{

	public SectorDeck(){
		int i;
		for (i=0; i<5; i++){
			deck.add(new SilenceCard());
		}
		for (i=0; i<6; i++){
			deck.add(new NoiseHereCard());
		}
		for (i=0; i<6; i++){
			deck.add(new NoiseAnywhereCard());
		}	
		for (i=0; i<4; i++){
			deck.add(new NoiseHereCardPlusItem());
		}	
		for (i=0; i<4; i++){
			deck.add(new NoiseAnywhereCardPlusItem());
		}
		this.shuffleDeck();	
		}
			
	
}


