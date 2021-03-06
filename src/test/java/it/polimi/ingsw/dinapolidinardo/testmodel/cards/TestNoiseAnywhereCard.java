package it.polimi.ingsw.dinapolidinardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.NoiseAnywhereCard;

import org.junit.Before;
import org.junit.Test;

public class TestNoiseAnywhereCard {

	private Card noiseAnywhere;
	
	@Before
	public void setUp(){
		noiseAnywhere = new NoiseAnywhereCard();
	}
	
	@Test
	public void workTest(){
		assertEquals(((NoiseAnywhereCard)noiseAnywhere).giveWalkOnMessage(),  " declares: NOISE in sector ");
		assertTrue(((NoiseAnywhereCard)noiseAnywhere).requiresViewCall());
	}
	

}
