package it.polimi.ingsw.DiNapoliDiNardo.testmodel.boxes;

import static org.junit.Assert.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.GalileiMap;
import it.polimi.ingsw.DiNapoliDiNardo.model.Map;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.LifeboatBox;

import org.junit.Before;
import org.junit.Test;

public class TestLifeboatBox {

	private Map galilei = new GalileiMap();
	private Box lifeboat1;
	private Box lifeboat2;
	private Box lifeboat3;
	private Box lifeboat4;
	
	@Before
	public void setUp(){
		
		lifeboat1 = galilei.getMap()[1][1];
		lifeboat2 = galilei.getMap()[22][1];
		lifeboat3 = galilei.getMap()[22][13];
		lifeboat4 = galilei.getMap()[1][13];
	}
	
	@Test
	public void correctNumber(){
		
		assertEquals(((LifeboatBox)lifeboat1).getNumber(), 1);
		assertEquals(((LifeboatBox)lifeboat2).getNumber(), 2);
		assertEquals(((LifeboatBox)lifeboat3).getNumber(), 3);
		assertEquals(((LifeboatBox)lifeboat4).getNumber(), 4);
	}
	
}
