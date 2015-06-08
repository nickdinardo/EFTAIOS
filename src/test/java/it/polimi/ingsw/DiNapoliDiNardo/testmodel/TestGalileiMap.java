package it.polimi.ingsw.DiNapoliDiNardo.testmodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.DiNapoliDiNardo.model.GalileiMap;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;

import org.junit.Before;
import org.junit.Test;

public class TestGalileiMap {

	private GalileiMap galilei;
	private Box[][] map;
	
	@Before
	public void setUp(){
		galilei = new GalileiMap();
		map = galilei.getMap();
	}
	
	@Test
	public void correctStartHumanPosition(){
		assertEquals(galilei.getHumanStartBox().getCoordX(), 12);
		assertEquals(galilei.getHumanStartBox().getCoordY(), 8);
	}
	
	@Test
	public void correctAlienStartPosition(){
		assertEquals(galilei.getAlienStartBox().getCoordX(), 12);
		assertEquals(galilei.getAlienStartBox().getCoordY(), 6);
	}
	
	@Test
	public void aroundBoxes(){
		List<Box> around = new ArrayList<Box>();
		Box cornerBox = new Box(1, 1);
		Box sideBox = new Box(23, 10);
		Box normalBox = new Box(10, 10);
		
		//Corner boxes
		around.add(map[1][0]);
		around.add(map[0][1]);
		around.add(map[1][1]);
		assertEquals(galilei.givemeAroundBoxes(cornerBox), around);
		around.clear();
		
		//Side boxes
		for (int i = sideBox.getCoordX() - 1; i <= sideBox.getCoordX(); i++)
			for (int j = sideBox.getCoordY() - 1; j <= sideBox.getCoordY() + 1; j++)
				around.add(map[j-1][i-1]);
		assertEquals(galilei.givemeAroundBoxes(sideBox), around);
		around.clear();
		
		//Normal boxes
		for (int i = normalBox.getCoordX() - 1; i <= normalBox.getCoordX() + 1; i++)
			for (int j = normalBox.getCoordY()-1; j <= normalBox.getCoordY() + 1; j++)
				around.add(map[j-1][i-1]);
		assertEquals(galilei.givemeAroundBoxes(normalBox), around);
		around.clear();
		
	}

}
