package it.polimi.ingsw.dinapolidinardo.testmodel.boxes;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.dinapolidinardo.model.AlienPlayer;
import it.polimi.ingsw.dinapolidinardo.model.GameState;
import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;
import it.polimi.ingsw.dinapolidinardo.model.Player;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Box;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.server.GameController;

import org.junit.Before;
import org.junit.Test;

public class TestBox {

	private Box box;
	private GameState teststate; 
	
	
	@Before
	public void setUp() throws IOException{
		teststate = new GameState(new GameController (1, null, null));
		box = new Box(10, 10);
	}
	
	@Test
	public void correctCoordinatesTest(){
		assertEquals(box.getCoordX(), 10);
		assertEquals(box.getCoordY(), 10);
	}
	
	@Test
	public void getPlayerHereTest(){
		Player human = new HumanPlayer(teststate.getMap(), teststate, "humantest");
		Player alien = new AlienPlayer(teststate.getMap(), teststate, "alientest");
		List<Player> playerInBox = new ArrayList<Player>();
		
		playerInBox.add(human);
		playerInBox.add(alien);
		box.setPlayer(human);
		box.setPlayer(alien);
		assertEquals(box.getPlayersHere(), playerInBox);
		
		playerInBox.remove(human);
		box.unsetPlayer(human);
		assertEquals(box.getPlayersHere(), playerInBox);
		
		playerInBox.clear();
		box.unsetPlayer(alien);
		assertEquals(box.getPlayersHere(), playerInBox);
	}
	
	@Test
	public void isLifeboatShipHereTest(){
		Box nolifeboat = teststate.getMap().getMap()[3][0];
		nolifeboat.setLifeBoatShipHere(false);
		Coordinates coord = new Coordinates();
		coord.setCoordX(teststate.getMap().getMap()[1][1].getCoordX());
		coord.setCoordY(teststate.getMap().getMap()[1][1].getCoordX());
		Box lifeboat = new Box(coord);
		lifeboat.setLifeBoatShipHere(true);
		
		assertTrue(!nolifeboat.isLifeBoatShipHere());
		assertFalse(nolifeboat.isLifeBoatShipHere());
		assertTrue(lifeboat.isLifeBoatShipHere());
		assertFalse(!lifeboat.isLifeBoatShipHere());
	}
	
	@Test
	public void correctFieldsTest(){
		Box box = teststate.getMap().getMap()[3][0];
		assertTrue(box.isCanBeCrossedType());
		assertTrue(!box.isDrawingSectorCardHere());
		
	}

}
