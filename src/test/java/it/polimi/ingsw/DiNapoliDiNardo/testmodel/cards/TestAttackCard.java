package it.polimi.ingsw.DiNapoliDiNardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AttackCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.ItemCard;

import org.junit.Before;
import org.junit.Test;

public class TestAttackCard {

	private ItemCard attack;
	
	@Before
	public void setUp(){
		attack = new AttackCard();
	}
	
	@Test
	public void nameTest(){
		assertEquals(attack.getName(), "AttackCard");
		
	}
	
	@Test
	public void useMeassageTest(){
		assertEquals(((AttackCard)attack).getUseMessage(), "-You charge, point and fire your weapon in the darkness. If someone (or something) is there he will suffer the consequences-");
	}
	
	@Test
	public void correctGetsTest(){
		assertTrue(attack.isActivable());
	}
	

}
