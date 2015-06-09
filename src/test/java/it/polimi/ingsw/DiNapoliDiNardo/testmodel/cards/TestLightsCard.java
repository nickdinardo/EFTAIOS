package it.polimi.ingsw.DiNapoliDiNardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.LightsCard;

import org.junit.Before;
import org.junit.Test;

public class TestLightsCard {

private Card lights;
	
	@Before
	public void setUp(){
		lights = new LightsCard();
	}
	
	@Test
	public void nameTest(){
		assertEquals(lights.getName(), "LightsCard");
		
	}
	
	@Test
	public void useMeassageTest(){
		assertEquals(((LightsCard)lights).getUseMessage(), "-Pointing your lights into darkness, you reveal all the alien and human being hiding into shadows-");
	}
	

}
