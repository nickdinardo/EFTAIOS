package it.polimi.ingsw.dinapolidinardo.testmodel.cards;

import static org.junit.Assert.*;

import java.io.IOException;

import it.polimi.ingsw.dinapolidinardo.model.GameState;
import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;
import it.polimi.ingsw.dinapolidinardo.model.Player;
import it.polimi.ingsw.dinapolidinardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;

import org.junit.Before;
import org.junit.Test;

public class TestAdrenalineCard {

	private Card adrenaline;
	private Player player;
	
	@Before
	public void setUp() throws IOException{
		adrenaline = new AdrenalineCard();
		GameState teststate = new GameState(null);
		player = new HumanPlayer(teststate.getMap(), teststate, "testplayer");
	}

	@Test
	public void nameTest(){
		assertEquals(adrenaline.getName(), "AdrenalineCard");
	}
	
	@Test
	public void useMessageTest(){
		assertEquals(((AdrenalineCard)adrenaline).getUseMessage(), "-Injecting yourself adrenaline you feel your body answer more quickly. You're faster in movements this turn-");
	}
	
	@Test
	public void actionTest(){
		((AdrenalineCard)adrenaline).doAction((HumanPlayer)player);
		assertTrue(player.isAdrenalized());
	}
}


