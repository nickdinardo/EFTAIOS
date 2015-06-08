package it.polimi.ingsw.DiNapoliDiNardo.testmodel;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.Server.GameController;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;

import org.junit.Before;
import org.junit.Test;


public class TestGameState {

	private GameState testState;
	private GameController controller = new GameController();
	
	@Before
	public void setUp(){
		testState = new GameState(controller);
	}
	
}
