package it.polimi.ingsw.DiNapoliDiNardo.testmodel.boxes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.DiNapoliDiNardo.model.AlienPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.GalileiMap;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.Map;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;

import org.junit.Before;
import org.junit.Test;

public class TestBox {

	private Box box;
	private Map galilei = new GalileiMap();
	private GameState teststate = new GameState(null);
	
	
	@Before
	public void setUp(){
		box = new Box(10, 10);
	}
	
	@Test
	public void correctCoordinates(){
		assertEquals(box.getCoordX(), 10);
		assertEquals(box.getCoordY(), 10);
	}
	
	@Test
	public void getPlayerHere(){
		Player human = new HumanPlayer(galilei, teststate, "humantest");
		Player alien = new AlienPlayer(galilei, teststate, "alientest");
		List<Player> playerInBox = new ArrayList<Player>();
		
		playerInBox.add(human);
		playerInBox.add(alien);
		box.setPlayer(human);
		box.setPlayer(alien);
		assertEquals(box.getPlayerHere(), playerInBox);
		
		playerInBox.remove(human);
		box.unsetPlayer(human);
		assertEquals(box.getPlayerHere(), playerInBox);
		
		playerInBox.clear();
		box.unsetPlayer(alien);
		assertEquals(box.getPlayerHere(), playerInBox);
	}
	
	@Test
	public void isLifeboatShipHere(){
		Box nolifeboat = galilei.getMap()[3][0];
		Box lifeboat = galilei.getMap()[1][1];
		
		assertTrue(!nolifeboat.isLifeBoatShipHere());
		assertFalse(nolifeboat.isLifeBoatShipHere());
		assertTrue(lifeboat.isLifeBoatShipHere());
		assertFalse(!lifeboat.isLifeBoatShipHere());
	}
	

}
