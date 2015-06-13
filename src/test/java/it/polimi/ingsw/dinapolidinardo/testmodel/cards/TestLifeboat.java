package it.polimi.ingsw.dinapolidinardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.dinapolidinardo.model.cards.GreenLifeboatCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.LifeboatCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.RedLifeboatCard;

import org.junit.Before;
import org.junit.Test;

public class TestLifeboat {

	private LifeboatCard green;
	private LifeboatCard red;
	
	@Before
	public void setUp(){
		green = new GreenLifeboatCard();
		red = new RedLifeboatCard();
	}
	
	@Test
	public void workTest(){
		assertTrue(((GreenLifeboatCard)green).isWorking());
		assertTrue(!red.isWorking());
	}
	
	

}
