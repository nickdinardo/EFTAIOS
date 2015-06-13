package it.polimi.ingsw.dinapolidinardo.model.decks;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	protected List<Card> coveredDeck = new ArrayList<Card>();
	protected List<Card> discards = new ArrayList<Card>();
	
	
	public List<Card> getDeck() {
		return coveredDeck;
	}
	
	public List<Card> getDiscards() {
		return discards;
	}
	
	
	public void shuffleDeck(){
		Collections.shuffle(coveredDeck);
	}
	
	public boolean isEmpty(){
		return coveredDeck.isEmpty();
	}
	
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
		Card drawed = coveredDeck.get(0);
		coveredDeck.remove(0);
		discards.add(drawed);
		return drawed;
	}
	

}
