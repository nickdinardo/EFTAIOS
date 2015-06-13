package it.polimi.ingsw.dinapolidinardo.model.decks;

import it.polimi.ingsw.dinapolidinardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.AttackCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.DefenseCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.LightsCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.SedativesCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.TeleportCard;

import java.util.Collections;


public class ItemDeck extends Deck{
	public ItemDeck(){
		int i;
		for (i=0; i<2; i++){
			coveredDeck.add(new AttackCard());
			}
		for (i=0; i<2; i++){
			coveredDeck.add(new TeleportCard());
			}
		for (i=0; i<3; i++){
			coveredDeck.add(new SedativesCard());
			}
		for (i=0; i<2; i++){
			coveredDeck.add(new LightsCard());
			}
		for (i=0; i<2; i++){
			coveredDeck.add(new AdrenalineCard());
			}
		coveredDeck.add(new DefenseCard());
		this.shuffleDeck();	
		}

	
	@Override
	public Card drawCard(){
		//same method than others deck but don't discard cards that players keep till they use them
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
		return drawed;
	}
	
	
	
	
}




