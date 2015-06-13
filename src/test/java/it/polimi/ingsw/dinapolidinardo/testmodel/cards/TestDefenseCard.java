package it.polimi.ingsw.dinapolidinardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.DefenseCard;

import org.junit.Before;
import org.junit.Test;

public class TestDefenseCard {

	private Card defense;
	
	@Before
	public void setUp(){
		defense = new DefenseCard();
	}
	
	@Test
	public void nameTest(){
		assertEquals(defense.getName(), "DefenseCard");
	}
	
	@Test
	public void useMessageTest(){
		assertEquals(((DefenseCard)defense).getUseMessage(),"You can't use a Defense Card, it will activate by itself when you'll be attacked");
		assertTrue(!((DefenseCard)defense).isActivable());
	}

}
