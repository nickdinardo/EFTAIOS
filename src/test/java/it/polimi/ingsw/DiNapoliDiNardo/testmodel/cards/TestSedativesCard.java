package it.polimi.ingsw.DiNapoliDiNardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.GalileiMap;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.SedativesCard;

import org.junit.Before;
import org.junit.Test;

public class TestSedativesCard {

	private Card sedatives;
	private Player player;
	
	@Before
	public void setUp(){
		sedatives = new SedativesCard();
		GalileiMap galilei = new GalileiMap();
		GameState teststate = new GameState(null);
		player = new HumanPlayer(galilei, teststate, "testplayer");
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
