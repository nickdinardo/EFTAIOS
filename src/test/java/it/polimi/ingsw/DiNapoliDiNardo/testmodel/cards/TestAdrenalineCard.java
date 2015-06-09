package it.polimi.ingsw.DiNapoliDiNardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.GalileiMap;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;

import org.junit.Before;
import org.junit.Test;

public class TestAdrenalineCard {

	private Card adrenaline;
	private Player player;
	
	@Before
	public void setUp(){
		adrenaline = new AdrenalineCard();
		GalileiMap galilei = new GalileiMap();
		GameState teststate = new GameState(null);
		player = new HumanPlayer(galilei, teststate, "testplayer");
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

