package it.polimi.ingsw.dinapolidinardo.testmodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.dinapolidinardo.model.GalileiMap;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Box;

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
		Box cornerBox1 = new Box(1, 1);
		Box cornerBox2 = new Box(1, 14);
		Box cornerBox3 = new Box(23, 1);
		Box cornerBox4 = new Box(23, 14);
		Box sideBox1 = new Box(23, 10);
		Box sideBox2 = new Box(10, 14);
		Box sideBox3 = new Box(10, 1);
		Box sideBox4 = new Box(1, 10);
		Box normalBox = new Box(10, 10);
		
		//Corner boxes
		around.add(map[1][0]);
		around.add(map[0][1]);
		around.add(map[1][1]);
		assertEquals(galilei.givemeAroundBoxes(cornerBox1), around);
		around.clear();
		
		around.add(map[12][0]);
		around.add(map[12][1]);
		around.add(map[13][1]);
		assertEquals(galilei.givemeAroundBoxes(cornerBox2), around);
		around.clear();
		
		around.add(map[0][21]);
		around.add(map[1][21]);
		around.add(map[1][22]);
		assertEquals(galilei.givemeAroundBoxes(cornerBox3), around);
		around.clear();
		
		around.add(map[12][21]);
		around.add(map[13][21]);
		around.add(map[12][22]);
		assertEquals(galilei.givemeAroundBoxes(cornerBox4), around);
		around.clear();
		
		
		
		//Side boxes
		for (int i = sideBox1.getCoordX() - 1; i <= sideBox1.getCoordX(); i++)
			for (int j = sideBox1.getCoordY() - 1; j <= sideBox1.getCoordY() + 1; j++)
				around.add(map[j-1][i-1]);
		assertEquals(galilei.givemeAroundBoxes(sideBox1), around);
		around.clear();
		
		for (int i=sideBox2.getCoordX()-1; i<=sideBox2.getCoordX()+1; i++)
			for (int j=sideBox2.getCoordY()-1; j<=sideBox2.getCoordY(); j++)
				around.add(map[j-1][i-1]);
		assertEquals(galilei.givemeAroundBoxes(sideBox2), around);
		around.clear();
		
		for (int i=sideBox3.getCoordX()-1; i<=sideBox3.getCoordX()+1; i++)
			for (int j=sideBox3.getCoordY(); j<=sideBox3.getCoordY()+1; j++)
				around.add(map[j-1][i-1]);
		assertEquals(galilei.givemeAroundBoxes(sideBox3), around);
		around.clear();
		
		for (int i=sideBox4.getCoordX(); i<=sideBox4.getCoordX() + 1; i++)
			for (int j=sideBox4.getCoordY()-1; j<=sideBox4.getCoordY()+1; j++)
				around.add(map[j-1][i-1]);
		assertEquals(galilei.givemeAroundBoxes(sideBox4), around);
		around.clear();
		
		
		
		
		//Normal boxes
		for (int i = normalBox.getCoordX() - 1; i <= normalBox.getCoordX() + 1; i++)
			for (int j = normalBox.getCoordY()-1; j <= normalBox.getCoordY() + 1; j++)
				around.add(map[j-1][i-1]);
		assertEquals(galilei.givemeAroundBoxes(normalBox), around);
		around.clear();
		
	}

}
