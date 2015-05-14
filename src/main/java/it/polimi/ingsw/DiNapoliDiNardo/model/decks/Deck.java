package it.polimi.ingsw.DiNapoliDiNardo.model.decks;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> discards = new ArrayList<Card>();
	
	
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
		if (deck.size()==0)
			return true;
		else return false;
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
