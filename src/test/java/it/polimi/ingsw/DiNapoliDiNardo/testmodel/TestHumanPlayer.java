package it.polimi.ingsw.DiNapoliDiNardo.testmodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.DiNapoliDiNardo.model.AlienPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.GalileiMap;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;


import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;

import org.junit.Before;
import org.junit.Test;


public class TestHumanPlayer {

	private HumanPlayer human;
	private GalileiMap galilei = new GalileiMap();
	private GameState teststate = new GameState(null);
	
	@Before 
	public void setUp(){
		human = new HumanPlayer(galilei, teststate, "testhuman");
	}


	@Test
	public void correctPositionTest(){
		assertEquals(human.getPosition().getCoordX(), 12);
		assertEquals(human.getPosition().getCoordY(), 8);
	}
	
	@Test
	public void setPositionTest(){
		assertTrue(human.getPosition().getPlayerHere().contains(human));
		assertFalse(human.getPosition().isEmpty());
	}

	@Test
	public void teleportTest(){
		//first I move the player far from starting box
		human.setPosition(new Box(3,7));
		//then I telport him back
		human.teleport();
		assertEquals(human.getPosition().getCoordX(), 12);
		assertEquals(human.getPosition().getCoordY(), 8);
			
	}

	@Test
	public void attackTest(){
		
		HumanPlayer victim1 = new HumanPlayer(galilei, teststate, "humanvictim");
		victim1.setPosition(galilei.getMap()[1][2]);
		AlienPlayer victim2 = new AlienPlayer(galilei, teststate, "alienvictim");
		victim2.setPosition(galilei.getMap()[1][2]);
		List<Player> victims = new ArrayList<Player>();
		victims.add(victim1);
		victims.add(victim2);
		human.setPosition(galilei.getMap()[1][2]);
		List<Player> effectivevictims = human.attack(human.getPosition());
		
		assertEquals(effectivevictims, victims);
		
	}
	
	@Test
	public void coorectMoveRange(){
		assertEquals(human.getMoveRange(), 1);
	}
	
	@Test
	public void isAdrenalized(){
		human.setAdrenalized(true);
		assertTrue(human.isAdrenalized());
	}
	
	@Test
	public void isSedated(){
		human.setSedated(true);
		assertTrue(human.isSedated());
	}
	
	@Test
	public void isEscaped(){
		human.setEscaped(true);
		assertTrue(human.isEscaped());
	}











}



