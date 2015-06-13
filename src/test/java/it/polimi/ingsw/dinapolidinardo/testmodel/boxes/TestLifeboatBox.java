package it.polimi.ingsw.dinapolidinardo.testmodel.boxes;

import static org.junit.Assert.*;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.model.boxes.LifeboatBox;

import org.junit.Before;
import org.junit.Test;

public class TestLifeboatBox {

	private LifeboatBox lifeboat1;
	private LifeboatBox lifeboat2;
	
	@Before
	public void setUp(){
		
		lifeboat1 = new LifeboatBox(2, 2, 1);
		Coordinates coord = new Coordinates();
		coord.setCoordX(2);
		coord.setCoordY(13);
		lifeboat2 = new LifeboatBox(coord, 4);
	}
	
	@Test
	public void correctNumber(){
		
		assertEquals(lifeboat1.getNumber(), 1);
		assertEquals(lifeboat2.getNumber(), 4);
	}
	
	@Test
	public void lifeboatHere(){
		lifeboat1.setLifeBoatShipHere(true);
		assertEquals(lifeboat1.isLifeBoatShipHere(), true);
	}
	
}
