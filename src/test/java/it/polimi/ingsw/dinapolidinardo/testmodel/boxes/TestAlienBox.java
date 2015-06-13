package it.polimi.ingsw.dinapolidinardo.testmodel.boxes;

import static org.junit.Assert.*;
import it.polimi.ingsw.dinapolidinardo.model.boxes.AlienBox;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

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
