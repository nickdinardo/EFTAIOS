package it.polimi.ingsw.DiNapoliDiNardo.testmodel.boxes;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.AlienBox;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import org.junit.Before;
import org.junit.Test;

public class TestAlienBox {

	private AlienBox alienBox;
	
	@Before
	public void setUp(){
		Coordinates coord = new Coordinates(1, 1);
		alienBox = new AlienBox(coord);
	}
	
	@Test
	public void work(){
		assertTrue(!alienBox.isCanBeCrossedType());
	}

}
