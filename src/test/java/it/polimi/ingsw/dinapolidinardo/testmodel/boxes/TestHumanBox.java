package it.polimi.ingsw.dinapolidinardo.testmodel.boxes;

import static org.junit.Assert.*;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.model.boxes.HumanBox;

import org.junit.Before;
import org.junit.Test;

public class TestHumanBox {

private HumanBox humanBox;
	
	@Before
	public void setUp(){
		Coordinates coord = new Coordinates(1, 1);
		humanBox = new HumanBox(coord);
	}
	
	@Test
	public void workTest(){
		assertTrue(!humanBox.isCanBeCrossedType());
	}
}
