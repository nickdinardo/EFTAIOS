package it.polimi.ingsw.dinapolidinardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.NoiseHereCard;

import org.junit.Before;
import org.junit.Test;

public class TestNoiseHereCard {

private Card noiseHere;
	
	@Before
	public void setUp(){
		noiseHere = new NoiseHereCard();
	}
	
	@Test
	public void workTest(){
		assertEquals(((NoiseHereCard)noiseHere).giveWalkOnMessage(),  " declares: NOISE in sector ");
		assertTrue(((NoiseHereCard)noiseHere).revealsPosition());
	}

}
