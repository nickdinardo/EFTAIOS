package it.polimi.ingsw.dinapolidinardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.NoiseAnywhereCardPlusItem;
import it.polimi.ingsw.dinapolidinardo.model.cards.NoiseHereCardPlusItem;

import org.junit.Before;
import org.junit.Test;

public class TestPlusItemCards {

	private Card noiseHere;
	private Card noiseAnywhere;
	
	@Before
	public void setUp(){
		noiseHere = new NoiseHereCardPlusItem();
		noiseAnywhere = new NoiseAnywhereCardPlusItem();
		
	}
	
	@Test
	public void workTest(){
		assertTrue(((NoiseHereCardPlusItem)noiseHere).isWithItemType());
		assertTrue(((NoiseAnywhereCardPlusItem)noiseAnywhere).isWithItemType());
		
	}

}
