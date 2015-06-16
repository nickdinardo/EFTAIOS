package it.polimi.ingsw.dinapolidinardo.testmodel.cards;

import static org.junit.Assert.*;

import java.io.IOException;

import it.polimi.ingsw.dinapolidinardo.model.GameState;
import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;
import it.polimi.ingsw.dinapolidinardo.model.Player;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Box;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.TeleportCard;

import org.junit.Before;
import org.junit.Test;

public class TestTeleportCard {

	private Card teleport;
	private Player player;
	
	@Before
	public void setUp() throws IOException{
		teleport = new TeleportCard();
		GameState teststate = new GameState(null);
		player = new HumanPlayer(teststate.getMap(), teststate, "testplayer");
	}

	@Test
	public void nameTest(){
		assertEquals(teleport.getName(), "TeleportCard");
	}
	
	@Test
	public void useMessageTest(){
		assertEquals(((TeleportCard)teleport).getUseMessage(), "-BZZZ...You successfully teleported back to L08, your starting position-");
	}
	
	@Test
	public void ActionTest(){
		//first I move the player far from starting box
		player.setPosition(new Box(3,7));
		//then I teleport him back
		((TeleportCard)teleport).doAction((HumanPlayer)player);;
		assertEquals(player.getPosition().getCoordX(), 12);
		assertEquals(player.getPosition().getCoordY(), 8);
	}
}
