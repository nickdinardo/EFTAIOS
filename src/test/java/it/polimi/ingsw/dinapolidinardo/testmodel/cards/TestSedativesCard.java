package it.polimi.ingsw.dinapolidinardo.testmodel.cards;

import static org.junit.Assert.*;

import java.io.IOException;

import it.polimi.ingsw.dinapolidinardo.model.GameState;
import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;
import it.polimi.ingsw.dinapolidinardo.model.Player;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.SedativesCard;

import org.junit.Before;
import org.junit.Test;

public class TestSedativesCard {

	private Card sedatives;
	private Player player;
	
	@Before
	public void setUp() throws IOException{
		sedatives = new SedativesCard();
		GameState teststate = new GameState(null);
		player = new HumanPlayer(teststate.getMap(), teststate, "testplayer");
	}

	@Test
	public void nameTest(){
		assertEquals(sedatives.getName(), "SedativesCard");
	}
	
	@Test
	public void useMessageTest(){
		assertEquals(((SedativesCard)sedatives).getUseMessage(), "-Injecting yourself the sedatives you calm down and control your body. You'll not make noise around this turn-");
	}
	
	@Test
	public void actionTest(){
		((SedativesCard)sedatives).doAction((HumanPlayer)player);
		assertTrue(player.isSedated());
	}

}
