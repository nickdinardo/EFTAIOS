package it.polimi.ingsw.DiNapoliDiNardo.testmodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.DiNapoliDiNardo.model.AlienPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.GalileiMap;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;

import org.junit.Before;
import org.junit.Test;

public class TestAlienPlayer {

	private AlienPlayer alien;
	private GalileiMap map = new GalileiMap();
	private GameState teststate = new GameState(null);
	
	@Before
	public void setUp(){
		alien = new AlienPlayer(map, teststate, "alientest");
	}
	
	@Test
	public void correctPositionTest(){
		assertEquals(alien.getPosition().getCoordX(), 12);
		assertEquals(alien.getPosition().getCoordY(), 6);
	}
	
	@Test
	public void setPositionTest(){
		assertTrue(alien.getPosition().getPlayerHere().contains(alien));
		assertFalse(alien.getPosition().isEmpty());
	}
	
	@Test
	public void attackTest(){
		
		HumanPlayer victim1 = new HumanPlayer(map, teststate, "humanvictim");
		victim1.setPosition(map.getMap()[1][2]);
		AlienPlayer victim2 = new AlienPlayer(map, teststate, "alienvictim");
		victim2.setPosition(map.getMap()[1][2]);
		List<Player> victims = new ArrayList<Player>();
		victims.add(victim1);
		victims.add(victim2);
		alien.setPosition(map.getMap()[1][2]);
		List<Player> effectivevictims = alien.attack(alien.getPosition());
		
		assertEquals(effectivevictims, victims);
		
	}
	
	@Test
	public void coorectMoveRange(){
		assertEquals(alien.getMoveRange(), 2);
	}
	
	@Test
	public void humanFed(){
		alien.setHumanfed(true);
		assertTrue(alien.isHumanFed());
	}
	
	@Test
	public void hasAttacked(){
		alien.setHasAttacked(true);
		assertTrue(alien.isHasAttacked());
	}

}
