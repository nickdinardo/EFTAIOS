package it.polimi.ingsw.dinapolidinardo.model;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.dinapolidinardo.model.boxes.*;

/**
 * Specific Map Class that provides the implementation of the Galilei Map
 */
public class GalileiMap extends Map{
	
	/**
	 * Constructor, it initializes the matrix representation respecting the design
	 * of the GalileiMap 
	 */
	public GalileiMap(){
		this.setMap(new Box[14][23]);
		for (int i=0; i<14; i++){
			for (int j=0; j<23; j++){
				gameMap[i][j] = new Wall(j+1,i+1);
			}
		}
		gameMap[1][0] = new DangerousBox(1,2);
		gameMap[2][0] = new DangerousBox(1,3);
		gameMap[3][0] = new Box(1,4);
		gameMap[4][0] = new Box(1,5);
		gameMap[5][0] = new Box(1,6);
		gameMap[8][0] = new Box(1,9);
		gameMap[9][0] = new Box(1,10);
		gameMap[10][0] = new Box(1,11);
		gameMap[11][0] = new Box(1,12);
		gameMap[12][0] = new Box(1,13);
		gameMap[13][0] = new DangerousBox(1,14);
		
		gameMap[0][1] = new DangerousBox(2,1);
		gameMap[1][1] = new LifeboatBox(2,2,1);
		gameMap[2][1] = new DangerousBox(2,3);
		gameMap[3][1] = new DangerousBox(2,4);
		gameMap[4][1] = new Box(2,5);
		gameMap[5][1] = new DangerousBox(2,6);
		gameMap[7][1] = new DangerousBox(2,8);
		gameMap[8][1] = new DangerousBox(2,9);
		gameMap[9][1] = new Box(2,10);
		gameMap[10][1] = new DangerousBox(2,11);
		gameMap[11][1] = new DangerousBox(2,12);
		gameMap[12][1] = new LifeboatBox(2,13,4);
		gameMap[13][1] = new DangerousBox(2,14);
		
		gameMap[0][2] = new Box(3,1);
		for (int i=2; i<14; i++){
			gameMap[i-1][2] = new DangerousBox(3,i);
		}
		gameMap[13][2] = new Box(3,14);
		
		gameMap[1][3] = new DangerousBox(4,2);
		gameMap[2][3] = new DangerousBox(4,3);
		gameMap[4][3] = new DangerousBox(4,5);
		gameMap[7][3] = new DangerousBox(4,8);
		gameMap[8][3] = new DangerousBox(4,9);
		gameMap[9][3] = new Box(4,10);
		gameMap[10][3] = new DangerousBox(4,11);
		gameMap[11][3] = new DangerousBox(4,12);
		gameMap[12][3] = new DangerousBox(4,13);
		gameMap[13][3] = new Box(4,14);
		
		gameMap[1][4] = new Box(5,2);
		gameMap[2][4] = new DangerousBox(5,3);
		gameMap[3][4] = new DangerousBox(5,4);
		gameMap[4][4] = new DangerousBox(5,5);
		gameMap[5][4] = new DangerousBox(5,6);
		gameMap[7][4] = new DangerousBox(5,8);
		gameMap[8][4] = new DangerousBox(5,9);
		gameMap[9][4] = new DangerousBox(5,10);
		gameMap[10][4] = new DangerousBox(5,11);
		gameMap[11][4] = new Box(5,12);
		gameMap[12][4] = new DangerousBox(5,13);
		
		gameMap[0][5] = new Box(6,1);
		gameMap[1][5] = new DangerousBox(6,2);
		gameMap[2][5] = new DangerousBox(6,3);
		gameMap[3][5] = new DangerousBox(6,4);
		gameMap[4][5] = new DangerousBox(6,5);
		gameMap[5][5] = new DangerousBox(6,6);
		gameMap[6][5] = new DangerousBox(6,7);
		gameMap[7][5] = new DangerousBox(6,8);
		gameMap[8][5] = new DangerousBox(6,9);
		gameMap[9][5] = new Box(6,10);
		gameMap[10][5] = new DangerousBox(6,11);
		
		gameMap[0][6] = new DangerousBox(7,1);
		gameMap[1][6] = new DangerousBox(7,2);
		gameMap[2][6] = new DangerousBox(7,3);
		gameMap[3][6] = new DangerousBox(7,4);
		gameMap[4][6] = new DangerousBox(7,5);
		gameMap[5][6] = new DangerousBox(7,6);
		gameMap[6][6] = new Box(7,7);
		gameMap[7][6] = new DangerousBox(7,8);
		gameMap[8][6] = new DangerousBox(7,9);
		gameMap[9][6] = new DangerousBox(7,10);
		gameMap[10][6] = new DangerousBox(7,11);
		gameMap[11][6] = new Box(7,12);
		gameMap[12][6] = new DangerousBox(7,13);
		gameMap[13][6] = new Box(7,14);
		
		gameMap[0][7] = new Box(8,1);
		gameMap[1][7] = new Box(8,2);
		gameMap[2][7] = new Box(8,3);
		gameMap[3][7] = new DangerousBox(8,4);
		gameMap[5][7] = new DangerousBox(8,6);
		gameMap[6][7] = new Box(8,7);
		gameMap[7][7] = new DangerousBox(8,8);
		gameMap[8][7] = new DangerousBox(8,9);
		gameMap[11][7] = new DangerousBox(8,12);
		gameMap[12][7] = new DangerousBox(8,13);
		gameMap[13][7] = new Box(8,14);
		
		gameMap[0][8] = new Box(9,1);
		gameMap[1][8] = new DangerousBox(9,2);
		gameMap[2][8] = new DangerousBox(9,3);
		gameMap[3][8] = new DangerousBox(9,4);
		gameMap[4][8] = new DangerousBox(9,5);
		gameMap[6][8] = new DangerousBox(9,7);
		gameMap[7][8] = new DangerousBox(9,8);
		gameMap[8][8] = new Box(9,9);
		gameMap[9][8] = new DangerousBox(9,10);
		gameMap[10][8] = new DangerousBox(9,11);
		gameMap[12][8] = new DangerousBox(9,13);
		gameMap[13][8] = new Box(9,14);
		
		gameMap[0][9] = new Box(10,1);
		gameMap[1][9] = new DangerousBox(10,2);
		gameMap[2][9] = new DangerousBox(10,3);
		gameMap[3][9] = new DangerousBox(10,4);
		gameMap[4][9] = new DangerousBox(10,5);
		gameMap[5][9] = new DangerousBox(10,6);
		gameMap[7][9] = new DangerousBox(10,8);
		gameMap[8][9] = new DangerousBox(10,9);
		gameMap[9][9] = new DangerousBox(10,10);
		gameMap[10][9] = new DangerousBox(10,11);
		gameMap[12][9] = new DangerousBox(10,13);
		gameMap[13][9] = new Box(10,14);
		
		gameMap[0][10] = new DangerousBox(11,1);
		gameMap[1][10] = new Box(11,2);
		gameMap[2][10] = new DangerousBox(11,3);
		gameMap[3][10] = new DangerousBox(11,4);
		gameMap[4][10] = new Box(11,5);
		gameMap[5][10] = new DangerousBox(11,6);
		gameMap[7][10] = new DangerousBox(11,8);
		gameMap[8][10] = new Box(11,9);
		gameMap[9][10] = new DangerousBox(11,10);
		gameMap[10][10] = new Box(11,11);
		gameMap[11][10] = new DangerousBox(11,12);
		gameMap[13][10] = new DangerousBox(11,14);
		
		gameMap[0][11] = new DangerousBox(12,1);
		gameMap[1][11] = new Box(12,2);
		gameMap[2][11] = new DangerousBox(12,3);
		gameMap[3][11] = new Box(12,4);
		gameMap[4][11] = new DangerousBox(12,5);
		gameMap[5][11] = new AlienBox(12,6);
		alienStartBox = gameMap[5][11];
		gameMap[7][11] = new HumanBox(12,8);
		humanStartBox = gameMap[7][11];
		gameMap[8][11] = new Box(12,9);
		gameMap[9][11] = new DangerousBox(12,10);
		gameMap[10][11] = new Box(12,11);
		gameMap[11][11] = new DangerousBox(12,12);
		gameMap[12][11] = new DangerousBox(12,13);
		gameMap[13][11] = new Box(12,14);
		
		
		gameMap[0][12] = new DangerousBox(13, 1);
		gameMap[1][12] = new Box(13, 2);
		gameMap[2][12] = new DangerousBox(13, 3);
		gameMap[3][12] = new DangerousBox(13, 4);
		gameMap[4][12] = new Box(13, 5);
		gameMap[5][12] = new DangerousBox(13, 6);
		gameMap[7][12] = new DangerousBox(13, 8);
		gameMap[8][12] = new Box(13, 9);
		gameMap[9][12] = new DangerousBox(13, 10);
		gameMap[10][12] = new Box(13, 11);
		gameMap[11][12] = new DangerousBox(13, 12);
		gameMap[12][12] = new DangerousBox(13, 13);
		gameMap[13][12] = new DangerousBox(13, 14);
		
		gameMap[0][13] = new DangerousBox(14, 1);
		gameMap[1][13] = new DangerousBox(14, 2);
		gameMap[2][13] = new Box(14, 3);
		gameMap[3][13] = new DangerousBox(14, 4);
		gameMap[4][13] = new DangerousBox(14, 5);
		gameMap[5][13] = new DangerousBox(14, 6);
		gameMap[7][13] = new DangerousBox(14, 8);
		gameMap[8][13] = new DangerousBox(14, 9);
		gameMap[9][13] = new DangerousBox(14, 10);
		gameMap[10][13] = new DangerousBox(14, 11);
		gameMap[11][13] = new DangerousBox(14, 12);
		gameMap[12][13] = new DangerousBox(14, 13);
		gameMap[13][13] = new Box(14, 14);
		
		gameMap[1][14] = new DangerousBox(15, 2);
		gameMap[4][14] = new Box(15, 5);
		gameMap[5][14] = new DangerousBox(15, 6);
		gameMap[6][14] = new DangerousBox(15, 7);
		gameMap[7][14] = new DangerousBox(15, 8);
		gameMap[8][14] = new Box(15, 9);
		gameMap[9][14] = new DangerousBox(15, 10);
		gameMap[10][14] = new DangerousBox(15, 11);
		gameMap[11][14] = new DangerousBox(15, 12);
		gameMap[12][14] = new DangerousBox(15, 13);
		gameMap[13][14] = new Box(15, 14);
		
		gameMap[0][15] = new Box(16, 1);
		gameMap[1][15] = new DangerousBox(16, 2);
		gameMap[2][15] = new Box(16, 3);
		gameMap[3][15] = new Box(16, 4);
		gameMap[4][15] = new DangerousBox(16, 5);
		gameMap[5][15] = new DangerousBox(16, 6);
		gameMap[6][15] = new DangerousBox(16, 7);
		gameMap[7][15] = new DangerousBox(16, 8);
		gameMap[8][15] = new DangerousBox(16, 9);
		gameMap[9][15] = new DangerousBox(16, 10);
		gameMap[10][15] = new DangerousBox(16, 11);
		gameMap[11][15] = new Box(16, 12);
		gameMap[12][15] = new DangerousBox(16, 13);
		gameMap[13][15] = new DangerousBox(16, 14);
		
		gameMap[0][16] = new Box(17, 1);
		gameMap[1][16] = new DangerousBox(17, 2);
		gameMap[2][16] = new DangerousBox(17, 3);
		gameMap[3][16] = new Box(17, 4);
		gameMap[4][16] = new DangerousBox(17, 5);
		gameMap[5][16] = new Box(17, 6);
		gameMap[6][16] = new DangerousBox(17, 7);
		gameMap[7][16] = new DangerousBox(17, 8);
		gameMap[8][16] = new DangerousBox(17, 9);
		gameMap[9][16] = new DangerousBox(17, 10);
		gameMap[10][16] = new Box(17, 11);
		gameMap[11][16] = new DangerousBox(17, 12);
		gameMap[12][16] = new DangerousBox(17, 13);
		gameMap[13][16] = new Box(17, 14);
		
		gameMap[0][17] = new Box(18, 1);
		gameMap[1][17] = new DangerousBox(18, 2);
		gameMap[2][17] = new DangerousBox(18, 3);
		gameMap[3][17] = new Box(18, 4);
		gameMap[4][17] = new DangerousBox(18, 5);
		gameMap[5][17] = new Box(18, 6);
		gameMap[6][17] = new Box(18, 7);
		gameMap[7][17] = new Box(18, 8);
		gameMap[8][17] = new DangerousBox(18, 9);
		gameMap[11][17] = new Box(18, 12);
		gameMap[12][17] = new DangerousBox(18, 13);
		
		gameMap[1][18] = new DangerousBox(19, 2);
		gameMap[3][18] = new DangerousBox(19, 4);
		gameMap[4][18] = new DangerousBox(19, 5);
		gameMap[5][18] = new DangerousBox(19, 6);
		gameMap[6][18] = new DangerousBox(19, 7);
		gameMap[7][18] = new DangerousBox(19, 8);
		gameMap[8][18] = new DangerousBox(19, 9);
		gameMap[11][18] = new DangerousBox(19, 12);
		gameMap[12][18] = new DangerousBox(19, 13);
		
		gameMap[1][19] = new DangerousBox(20, 2);
		gameMap[4][19] = new DangerousBox(20, 5);
		gameMap[5][19] = new DangerousBox(20, 6);
		gameMap[6][19] = new Box(20, 7);
		gameMap[7][19] = new Box(20, 8);
		gameMap[10][19] = new DangerousBox(20, 11);
		gameMap[11][19] = new DangerousBox(20, 12);
		gameMap[12][19] = new DangerousBox(20, 13);
		gameMap[13][19] = new Box(20, 14);
		
		gameMap[0][20] = new Box(21, 1);
		gameMap[1][20] = new DangerousBox(21, 2);
		gameMap[2][20] = new DangerousBox(21, 3);
		gameMap[3][20] = new DangerousBox(21, 4);
		gameMap[4][20] = new Box(21, 5);
		gameMap[5][20] = new DangerousBox(21, 6);
		gameMap[6][20] = new DangerousBox(21, 7);
		gameMap[7][20] = new DangerousBox(21, 8);
		gameMap[8][20] = new DangerousBox(21, 9);
		gameMap[9][20] = new DangerousBox(21, 10);
		gameMap[10][20] = new DangerousBox(21, 11);
		gameMap[11][20] = new Box(21, 12);
		gameMap[12][20] = new DangerousBox(21, 13);
		gameMap[13][20] = new DangerousBox(21, 14);
		
		gameMap[0][21] = new Box(22, 1);
		gameMap[1][21] = new LifeboatBox(22, 2, 2);
		gameMap[2][21] = new DangerousBox(22, 3);
		gameMap[3][21] = new DangerousBox(22, 4);
		gameMap[4][21] = new DangerousBox(22, 5);
		gameMap[5][21] = new DangerousBox(22, 6);
		gameMap[7][21] = new Box(22, 8);
		gameMap[8][21] = new DangerousBox(22, 9);
		gameMap[9][21] = new DangerousBox(22, 10);
		gameMap[10][21] = new DangerousBox(22, 11);
		gameMap[11][21] = new DangerousBox(22, 12);
		gameMap[12][21] = new LifeboatBox(22, 13, 3);
		gameMap[13][21] = new DangerousBox(22, 14);
		
		gameMap[1][22] = new DangerousBox(23, 2);
		gameMap[2][22] = new Box(23, 3);
		gameMap[3][22] = new Box(23, 4);
		gameMap[4][22] = new Box(23, 5);
		gameMap[5][22] = new Box(23, 6);
		gameMap[8][22] = new DangerousBox(23, 9);
		gameMap[9][22] = new Box(23, 10);
		gameMap[10][22] = new Box(23, 11);
		gameMap[11][22] = new Box(23, 12);
		gameMap[12][22] = new DangerousBox(23, 13);
		gameMap[13][22] = new DangerousBox(23, 14);
			
	}
	
		
	/**
	 * Provides all the matrix neighbors with a particular attention for side and corner boxes,
	 * in order to avoid out of bound indexes
	 * 
	 * @param center the Box for which we need the neighbors 
	 * @return the list of the selected Box neighbors in the matrix representation
	 */
	@Override
	public List<Box> givemeAroundBoxes (Box center){
		List<Box> aroundBoxes = new ArrayList<Box>();
		//corners box, to be treated differently in order to avoid IndexOutofBorder
		if (center.getCoordX()==1 && center.getCoordY()==1){
			aroundBoxes.add(gameMap[1][0]);
			aroundBoxes.add(gameMap[0][1]);
			aroundBoxes.add(gameMap[1][1]);
			return aroundBoxes;
		}
		if (center.getCoordX()==1 && center.getCoordY()==14){
			aroundBoxes.add(gameMap[12][0]);
			aroundBoxes.add(gameMap[12][1]);
			aroundBoxes.add(gameMap[13][1]);
			return aroundBoxes;
		}
		if (center.getCoordX()==23 && center.getCoordY()==1){
			aroundBoxes.add(gameMap[0][21]);
			aroundBoxes.add(gameMap[1][21]);
			aroundBoxes.add(gameMap[1][22]);
			return aroundBoxes;
		}
		if (center.getCoordX()==23 && center.getCoordY()==14){
			aroundBoxes.add(gameMap[12][21]);
			aroundBoxes.add(gameMap[13][21]);
			aroundBoxes.add(gameMap[12][22]);
			return aroundBoxes;
		}
		//side boxes
		if (center.getCoordX()==1){
			for (int i=center.getCoordX(); i<=center.getCoordX()+1; i++)
				for (int j=center.getCoordY()-1; j<=center.getCoordY()+1; j++)
					aroundBoxes.add(gameMap[j-1][i-1]);
			return aroundBoxes;
		}
		if (center.getCoordY()==14){
			for (int i=center.getCoordX()-1; i<=center.getCoordX()+1; i++)
				for (int j=center.getCoordY()-1; j<=center.getCoordY(); j++)
					aroundBoxes.add(gameMap[j-1][i-1]);
			return aroundBoxes;
		}
		if (center.getCoordY()==1){
			for (int i=center.getCoordX()-1; i<=center.getCoordX()+1; i++)
				for (int j=center.getCoordY(); j<=center.getCoordY()+1; j++)
					aroundBoxes.add(gameMap[j-1][i-1]);
			return aroundBoxes;
			
		}
		if (center.getCoordX()==23){
			for (int i=center.getCoordX()-1; i<=center.getCoordX(); i++)
				for (int j=center.getCoordY()-1; j<=center.getCoordY()+1; j++)
					aroundBoxes.add(gameMap[j-1][i-1]);
			return aroundBoxes;		
		}
		
		for (int i=center.getCoordX()-1; i<=center.getCoordX()+1; i++)
			for (int j=center.getCoordY()-1; j<=center.getCoordY()+1; j++)
				aroundBoxes.add(gameMap[j-1][i-1]);
		return aroundBoxes;		
		
	}
	
	
	
}	
	
	