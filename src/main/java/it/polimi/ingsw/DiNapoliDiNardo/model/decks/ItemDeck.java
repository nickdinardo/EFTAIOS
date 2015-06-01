package it.polimi.ingsw.DiNapoliDiNardo.model.decks;

import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AttackCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.DefenseCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.LightsCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.SedativesCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.TeleportCard;

import java.util.Collections;


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
		for (i=0; i<2; i++){
			deck.add(new LightsCard());
			}
		for (i=0; i<2; i++){
			deck.add(new AdrenalineCard());
			}
		deck.add(new DefenseCard());
		this.shuffleDeck();	
		}

	
	@Override
	public Card drawCard(){
		//same method than others deck but don't discard cards that players keep till they use them
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
		return drawed;
	}
	
	
	
	
}























		//attacco 2
		//teletr 2
		//sedatvi 3
		//spolights 2
		//difesa 1
		//adrenalina 2