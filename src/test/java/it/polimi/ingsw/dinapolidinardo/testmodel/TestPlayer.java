package it.polimi.ingsw.dinapolidinardo.testmodel;

import static org.junit.Assert.*;

import java.io.IOException;

import it.polimi.ingsw.dinapolidinardo.model.AlienPlayer;
import it.polimi.ingsw.dinapolidinardo.model.GameState;
import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;
import it.polimi.ingsw.dinapolidinardo.model.Player;

import org.junit.Before;
import org.junit.Test;

public class TestPlayer {

	Player human;
	Player alien;
	private GameState teststate; 
	
	@Before
	public void setUp() throws IOException{
		teststate = new GameState(null);
		human = new HumanPlayer(teststate.getMap(), teststate, "humantest");
		alien = new AlienPlayer(teststate.getMap(), teststate, "alientest");
	}
	
	
	@Test
	public void killTest(){
		assertTrue(human.isAlive());
		human.kill();
		human.setKiller(alien.getName());
		assertEquals(human.getKiller(), alien.getName());
		assertTrue(!human.isAlive());
	}
	
	@Test
	public void losesIfKilledTypeTest(){
		assertTrue(human.isLosesIfKilledType());
		assertTrue(!alien.isLosesIfKilledType());
	}

}
