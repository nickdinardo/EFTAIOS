package it.polimi.ingsw.DiNapoliDiNardo.testmodel.boxes;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Wall;

import org.junit.Before;
import org.junit.Test;

public class TestWallBox {

	private Wall wall;
	
	@Before
	public void setUp(){
		Coordinates coord = new Coordinates(1, 1);
		wall = new Wall(coord);
	}
	
	@Test
	public void work(){
		assertTrue(!wall.isCanBeCrossedType());
	}

}
