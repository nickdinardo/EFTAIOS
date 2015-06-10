package it.polimi.ingsw.DiNapoliDiNardo.model.deck;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AttackCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.DefenseCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.LightsCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.SedativesCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.TeleportCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.Deck;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.ItemDeck;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.SectorDeck;

import org.junit.Before;
import org.junit.Test;


public class TestItemDeck {

	private List<Card> itemDeck;
	private List<Card> discard;
	
	@Before
	public void setUp(){
		
		itemDeck = new ArrayList<Card>();
		int i;
		for (i=0; i<2; i++){
			itemDeck.add(new AttackCard());
			}
		for (i=0; i<2; i++){
			itemDeck.add(new TeleportCard());
			}
		for (i=0; i<3; i++){
			itemDeck.add(new SedativesCard());
			}
		for (i=0; i<2; i++){
			itemDeck.add(new LightsCard());
			}
		for (i=0; i<2; i++){
			itemDeck.add(new AdrenalineCard());
			}
		itemDeck.add(new DefenseCard());
		discard = new ArrayList<>();
	}
	
	@Test
	public void workTest(){
		Deck deckItems = new SectorDeck();
		Deck deck = new ItemDeck();
		assertTrue(!deckItems.isEmpty());
		Card card1 = deckItems.drawCard();
		discard.add(card1);
		assertTrue(!deckItems.getDeck().contains(card1));
		Card card2 = deck.drawCard();
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
		assertTrue(deckItems.getDiscards().containsAll(discard));
		assertEquals(deck.getDiscards().size(), 0);
		
	}

}
