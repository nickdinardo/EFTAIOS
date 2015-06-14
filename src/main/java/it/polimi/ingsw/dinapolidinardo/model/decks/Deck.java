package it.polimi.ingsw.dinapolidinardo.model.decks;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Abstract class that represent a generic deck of the game
 */
public class Deck {

	protected List<Card> coveredDeck = new ArrayList<Card>();
	protected List<Card> discards = new ArrayList<Card>();
	
		
	/**
	 * Remove the top card from the deck, adds it to the discard pile of the deck. 
	 * <p>
	 * If there are no cards in the deck, gets all the cards in the discards pile, 
	 * adds them to the deck, shuffles the deck, and finally draws the first card
	 * @return the card drawn
	 */
	public Card drawCard(){
		if (coveredDeck.isEmpty()){
			int discardSize=discards.size();
			for (int i=0; i<discardSize; i++){
				Card reshuffled = discards.get(0);
				discards.remove(0);
				coveredDeck.add(reshuffled);
				}
			Collections.shuffle(coveredDeck);
		}
		Card drawn = coveredDeck.get(0);
		coveredDeck.remove(0);
		discards.add(drawn);
		return drawn;
	}

	
	
	public void shuffleDeck(){
		Collections.shuffle(coveredDeck);
	}
	
	public boolean isEmpty(){
		return coveredDeck.isEmpty();
	}
	
	public List<Card> getDeck() {
		return coveredDeck;
	}
	
	public List<Card> getDiscards() {
		return discards;
	}
	
}
