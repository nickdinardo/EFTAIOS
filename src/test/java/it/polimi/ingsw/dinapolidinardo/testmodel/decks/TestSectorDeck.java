package it.polimi.ingsw.dinapolidinardo.testmodel.decks;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.SectorCard;
import it.polimi.ingsw.dinapolidinardo.model.decks.SectorDeck;


public class TestSectorDeck {

	private SectorDeck sectordeck;
	
	@Before
	public void setUp(){
		sectordeck = new SectorDeck();
	}
	
	@Test
	public void reShuffleTest(){
		for (int i=0; i<25; i++)
			sectordeck.drawCard();
		sectordeck.drawCard();
		assertEquals(sectordeck.getDeck().size(), 24);
	}
	
	@Test
	public void typeTest(){
		Card card = sectordeck.drawCard();
		assertTrue(card instanceof SectorCard);
	}
		
	
}
