package it.polimi.ingsw.DiNapoliDiNardo.model.decks;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> discards = new ArrayList<Card>();
	
	void shuffleDeck(){
		Collections.shuffle(deck);
	}
	boolean isEmpty(){
		if (deck.size()==0)
			return true;
		else return false;
	}
	Card drawCard(){
		Card drawed = deck.get(0);
		deck.remove(0);
		discards.add(drawed);
		if (deck.isEmpty()){
			deck=discards;
			deck.shuffle();
			discards.clear();
		}
		return drawed;
	}
	

}
