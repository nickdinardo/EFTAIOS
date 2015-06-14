package it.polimi.ingsw.dinapolidinardo.model.decks;

import it.polimi.ingsw.dinapolidinardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.AttackCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.DefenseCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.LightsCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.SedativesCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.TeleportCard;

import java.util.Collections;


/**
 * Class that represent the Item Deck of the game
 */
public class ItemDeck extends Deck{
	
	/**
	 * Constructor that initialize the deck filling it with the specified amount of any card
	 */
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

	/**
	 * Remove the top card from the deck, WITHOUT adding it to the discard pile
	 * (cards drawn here end up in the personal decks of the players). 
	 * <p>
	 * If there are no cards in the deck, gets all the cards in the discards pile, 
	 * adds them to the deck, shuffles the deck, and finally draws the first card
	 * @return the card drawn
	 */
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




