package it.polimi.ingsw.dinapolidinardo.testmodel;

import static org.junit.Assert.*;
import it.polimi.ingsw.dinapolidinardo.model.AlienPlayer;
import it.polimi.ingsw.dinapolidinardo.model.GalileiMap;
import it.polimi.ingsw.dinapolidinardo.model.GameState;
import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;
import it.polimi.ingsw.dinapolidinardo.model.Player;

import org.junit.Before;
import org.junit.Test;

public class TestPlayer {

	Player human;
	Player alien;
	private GalileiMap map = new GalileiMap();
	private GameState teststate = new GameState(null);
	
	@Before
	public void setUp(){
		human = new HumanPlayer(map, teststate, "humantest");
		alien = new AlienPlayer(map, teststate, "alientest");
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
