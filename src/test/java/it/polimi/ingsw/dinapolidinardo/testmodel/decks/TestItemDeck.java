package it.polimi.ingsw.dinapolidinardo.testmodel.decks;


import static org.junit.Assert.*;

import java.util.List;

import it.polimi.ingsw.dinapolidinardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.AttackCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.DefenseCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.LightsCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.SedativesCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.TeleportCard;
import it.polimi.ingsw.dinapolidinardo.model.decks.ItemDeck;


import org.junit.Before;
import org.junit.Test;


public class TestItemDeck {

	ItemDeck deckItems = new ItemDeck();
	List<Card> discard = deckItems.getDiscards();
	
	@Before
	public void setUp(){
		deckItems = new ItemDeck();
		discard = deckItems.getDiscards();
	}
	
	@Test
	public void workTest(){
		
		
		
		while(deckItems.getDeck().size() > 0){
			//have to discard manually because item deck doesn't discard cards drawn
			discard.add(deckItems.drawCard());
		}
		assertTrue(deckItems.isEmpty());
		Card card1 = deckItems.drawCard();
		discard.remove(card1);
		assertTrue(deckItems.getDeck().containsAll(discard));
		Card card2 = deckItems.drawCard();
		if(card2 instanceof AttackCard){
			assertEquals(card2.getName(), "AttackCard");
		}	
		if(card2 instanceof LightsCard){
			assertEquals(card2.getName(), "LightsCard");
		}
		if(card2 instanceof SedativesCard){
			assertEquals(card2.getName(), "SedativesCard");
		}
		if(card2 instanceof AdrenalineCard){
			assertEquals(card2.getName(), "AdrenalineCard");
		}
		if(card2 instanceof DefenseCard){
			assertEquals(card2.getName(), "DefenseCard");
		}
		if(card2 instanceof TeleportCard){
			assertEquals(card2.getName(), "TeleportCard");
		}
		
		assertEquals(deckItems.getDiscards().size(), 0);
	}

}
