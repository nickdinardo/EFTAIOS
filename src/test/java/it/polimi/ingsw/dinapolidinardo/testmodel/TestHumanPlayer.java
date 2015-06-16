package it.polimi.ingsw.dinapolidinardo.testmodel;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.dinapolidinardo.model.AlienPlayer;
import it.polimi.ingsw.dinapolidinardo.model.GameState;
import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;
import it.polimi.ingsw.dinapolidinardo.model.Player;

import org.junit.Before;
import org.junit.Test;


public class TestHumanPlayer {

	private HumanPlayer human;
	private GameState teststate;
	
	@Before 
	public void setUp() throws IOException{
		teststate = new GameState(null);
		human = new HumanPlayer(teststate.getMap(), teststate, "testhuman");
	}


	@Test
	public void correctPositionTest(){
		assertEquals(human.getPosition().getCoordX(), 12);
		assertEquals(human.getPosition().getCoordY(), 8);
	}
	
	@Test
	public void setPositionTest(){
		assertTrue(human.getPosition().getPlayersHere().contains(human));
		assertFalse(human.getPosition().isEmpty());
	}

	@Test
	public void attackTest(){
		
		HumanPlayer victim1 = new HumanPlayer(teststate.getMap(), teststate, "humanvictim");
		victim1.setPosition(teststate.getMap().getMap()[1][2]);
		AlienPlayer victim2 = new AlienPlayer(teststate.getMap(), teststate, "alienvictim");
		victim2.setPosition(teststate.getMap().getMap()[1][2]);
		List<Player> victims = new ArrayList<Player>();
		victims.add(victim1);
		victims.add(victim2);
		human.setPosition(teststate.getMap().getMap()[1][2]);
		List<Player> effectivevictims = human.attack(human.getPosition());
		
		assertEquals(effectivevictims, victims);
		
	}
	
	@Test
	public void coorectMoveRange(){
		assertEquals(human.getMoveRange(), 1);
		human.setAdrenalized(true);
		assertEquals(human.getMoveRange(), 2);
	}
	
	@Test
	public void correctGets(){
		
		human.setAdrenalized(true);
		assertTrue(human.isAdrenalized());
		human.setSedated(true);
		assertTrue(human.isSedated());
		human.setEscaped(true);
		assertTrue(human.isEscaped());
		assertTrue(human.isLosesIfKilledType());
		
	}

}



