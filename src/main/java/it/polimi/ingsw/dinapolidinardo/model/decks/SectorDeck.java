package it.polimi.ingsw.dinapolidinardo.model.decks;

import it.polimi.ingsw.dinapolidinardo.model.cards.NoiseAnywhereCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.NoiseAnywhereCardPlusItem;
import it.polimi.ingsw.dinapolidinardo.model.cards.NoiseHereCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.NoiseHereCardPlusItem;
import it.polimi.ingsw.dinapolidinardo.model.cards.SilenceCard;


/**
 * Class that represent the Sector Deck of the game
 */
public class SectorDeck extends Deck{

	/**
	 * Constructor that initialize the deck filling it with the specified amount of any card
	 */
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
		for (i=0; i<4; i++){
			coveredDeck.add(new NoiseHereCardPlusItem());
		}	
		for (i=0; i<4; i++){
			coveredDeck.add(new NoiseAnywhereCardPlusItem());
		}
		this.shuffleDeck();	
		}
			
	
}


