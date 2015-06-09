package it.polimi.ingsw.DiNapoliDiNardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.NoiseAnywhereCardPlusItem;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.NoiseHereCardPlusItem;

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
