package it.polimi.ingsw.DiNapoliDiNardo.testmodel.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.SilenceCard;

import org.junit.Before;
import org.junit.Test;

public class TestSilenceCard {

	private Card silence;
	
	@Before
	public void setUp(){
		silence = new SilenceCard();
	}

	@Test
	public void workTest(){
		assertEquals(((SilenceCard)silence).giveWalkOnMessage(), " declares: SILENCE.");
		assertTrue(!((SilenceCard)silence).revealsPosition());
		assertTrue(!((SilenceCard)silence).isWithItemType());
		assertTrue(!((SilenceCard)silence).requiresViewCall());
	}
	
	@Test
	public void nameTest(){
		assertEquals(silence.getName(), "Card");
	}
}
