package it.polimi.ingsw.DiNapoliDiNardo.testmodel;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.AlienPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.GalileiMap;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;

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
	public void kill(){
		assertTrue(human.isAlive());
		human.kill();
		human.setKiller(alien.getName());
		assertEquals(human.getKiller(), alien.getName());
		assertTrue(!human.isAlive());
	}
	
	public void losesIfKilledType(){
		assertTrue(human.isLosesIfKilledType());
		assertTrue(!alien.isLosesIfKilledType());
	}

}
