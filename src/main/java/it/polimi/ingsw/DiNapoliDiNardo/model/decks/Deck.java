package it.polimi.ingsw.DiNapoliDiNardo.model.decks;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	protected ArrayList<Card> deck = new ArrayList<Card>();
	protected ArrayList<Card> discards = new ArrayList<Card>();
	
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public ArrayList<Card> getDiscards() {
		return discards;
	}
	
	
	public void shuffleDeck(){
		Collections.shuffle(deck);
	}
	
	public boolean isEmpty(){
		return deck.isEmpty();
	}
	
	public Card drawCard(){
		if (deck.isEmpty()){
			int discardSize=discards.size();
			for (int i=0; i<discardSize; i++){
				Card reshuffled = discards.get(0);
				discards.remove(0);
				deck.add(reshuffled);
				}
			Collections.shuffle(deck);
		}
		Card drawed = deck.get(0);
		deck.remove(0);
		discards.add(drawed);
		return drawed;
	}
	

}
