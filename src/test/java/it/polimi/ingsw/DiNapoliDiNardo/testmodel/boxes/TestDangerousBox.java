package it.polimi.ingsw.DiNapoliDiNardo.testmodel.boxes;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.DangerousBox;

import org.junit.Before;
import org.junit.Test;

public class TestDangerousBox {

	private DangerousBox gray;
	
	@Before
	public void setUp(){
		Coordinates coord = new Coordinates(1, 1);
		gray = new DangerousBox(coord);
	}
	
	@Test
	public void drawingSector(){
		assertTrue(!gray.isCanBeCrossedType());
	}

}
