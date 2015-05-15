package it.polimi.ingsw.DiNapoliDiNardo.model;
import java.util.ArrayList;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;

public class GalileiMap {
	private Box[][] map;
	
	public GalileiMap() {
		this.setMap(new Box[14][23]);
	}

	public Box[][] getMap() {
		return map;
	}

	public void setMap(Box[][] map) {
		this.map = map;
	}
	
	public ArrayList<Box> givemeAroundBoxes (Box center){
		ArrayList<Box> aroundBoxes = new ArrayList<Box>();
		//corners box, to be treated differently in order to avoid IndexOutofBorder
		if (center.getCoordX()==1 && center.getCoordY()==1){
			aroundBoxes.add(map[1][0]);
			aroundBoxes.add(map[0][1]);
			aroundBoxes.add(map[1][1]);
			return aroundBoxes;
		}
		if (center.getCoordX()==1 && center.getCoordY()==14){
			aroundBoxes.add(map[12][0]);
			aroundBoxes.add(map[12][1]);
			aroundBoxes.add(map[13][1]);
			return aroundBoxes;
		}
		if (center.getCoordX()==23 && center.getCoordY()==1){
			aroundBoxes.add(map[0][21]);
			aroundBoxes.add(map[1][21]);
			aroundBoxes.add(map[1][22]);
			return aroundBoxes;
		}
		if (center.getCoordX()==23 && center.getCoordY()==14){
			aroundBoxes.add(map[12][21]);
			aroundBoxes.add(map[13][21]);
			aroundBoxes.add(map[12][22]);
			return aroundBoxes;
		}
		//side boxes
		if (center.getCoordX()==1){
			for (int i=center.getCoordX(); i<=center.getCoordX()+1; i++)
				for (int j=center.getCoordY()-1; j<=center.getCoordY()+1; j++)
					aroundBoxes.add(map[j-1][i-1]);
			return aroundBoxes;
		}
		if (center.getCoordY()==14){
			for (int i=center.getCoordX()-1; i<=center.getCoordX()+1; i++)
				for (int j=center.getCoordY()-1; j<=center.getCoordY(); j++)
					aroundBoxes.add(map[j-1][i-1]);
			return aroundBoxes;
		}
		if (center.getCoordY()==1){
			for (int i=center.getCoordX()-1; i<=center.getCoordX()+1; i++)
				for (int j=center.getCoordY(); j<=center.getCoordY()+1; j++)
					aroundBoxes.add(map[j-1][i-1]);
			return aroundBoxes;
			
		}
		if (center.getCoordX()==23){
			for (int i=center.getCoordX()-1; i<=center.getCoordX(); i++)
				for (int j=center.getCoordY()-1; j<=center.getCoordY()+1; j++)
					aroundBoxes.add(map[j-1][i-1]);
			return aroundBoxes;		
		}
		
		for (int i=center.getCoordX()-1; i<=center.getCoordX()+1; i++)
			for (int j=center.getCoordY()-1; j<=center.getCoordY()+1; j++)
				aroundBoxes.add(map[j-1][i-1]);
		return aroundBoxes;		
		
	}
	
	
	public void buildmap(){
		for (int i=0; i<14; i++){
			for (int j=0; j<23; j++){
				map[i][j] = new Wall(i+1,j+1);
			}
		}
		map[1][0] = new DangerousBox(1,2);
		map[2][0] = new DangerousBox(1,3);
		map[3][0] = new Box(1,4);
		map[4][0] = new Box(1,5);
		map[5][0] = new Box(1,6);
		map[8][0] = new Box(1,9);
		map[9][0] = new Box(1,10);
		map[10][0] = new Box(1,11);
		map[11][0] = new Box(1,12);
		map[12][0] = new Box(1,13);
		map[13][0] = new DangerousBox(1,14);
		
		map[0][1] = new DangerousBox(2,1);
		map[1][1] = new LifeboatBox(2,2);
		map[2][1] = new DangerousBox(2,3);
		map[3][1] = new DangerousBox(2,4);
		map[4][1] = new Box(2,5);
		map[5][1] = new DangerousBox(2,6);
		map[7][1] = new DangerousBox(2,8);
		map[8][1] = new DangerousBox(2,9);
		map[9][1] = new Box(2,10);
		map[10][1] = new DangerousBox(2,11);
		map[11][1] = new DangerousBox(2,12);
		map[12][1] = new LifeboatBox(2,13);
		map[13][1] = new DangerousBox(2,14);
		
		map[0][2] = new Box(3,1);
		for (int i=2; i<14; i++){
			map[i-1][2] = new DangerousBox(3,i);
		}
		map[13][2] = new Box(3,14);
		
		map[1][3] = new DangerousBox(4,2);
		map[2][3] = new DangerousBox(4,3);
		map[4][3] = new DangerousBox(4,5);
		map[7][3] = new DangerousBox(4,8);
		map[8][3] = new DangerousBox(4,9);
		map[9][3] = new Box(4,10);
		map[10][3] = new DangerousBox(4,11);
		map[11][3] = new DangerousBox(4,12);
		map[12][3] = new DangerousBox(4,13);
		map[13][3] = new Box(4,14);
		
		map[1][4] = new Box(5,2);
		map[2][4] = new DangerousBox(5,3);
		map[3][4] = new DangerousBox(5,4);
		map[4][4] = new DangerousBox(5,5);
		map[5][4] = new DangerousBox(5,6);
		map[7][4] = new DangerousBox(5,8);
		map[8][4] = new DangerousBox(5,9);
		map[9][4] = new DangerousBox(5,10);
		map[10][4] = new DangerousBox(5,11);
		map[11][4] = new Box(5,12);
		map[12][4] = new DangerousBox(5,13);
		
		map[0][5] = new Box(6,1);
		map[1][5] = new DangerousBox(6,2);
		map[2][5] = new DangerousBox(6,3);
		map[3][5] = new DangerousBox(6,4);
		map[4][5] = new DangerousBox(6,5);
		map[5][5] = new DangerousBox(6,6);
		map[6][5] = new DangerousBox(6,7);
		map[7][5] = new DangerousBox(6,8);
		map[8][5] = new DangerousBox(6,9);
		map[9][5] = new Box(6,10);
		map[10][5] = new DangerousBox(6,11);
		
		map[0][6] = new DangerousBox(7,1);
		map[1][6] = new DangerousBox(7,2);
		map[2][6] = new DangerousBox(7,3);
		map[3][6] = new DangerousBox(7,4);
		map[4][6] = new DangerousBox(7,5);
		map[5][6] = new DangerousBox(7,6);
		map[6][6] = new Box(7,7);
		map[7][6] = new DangerousBox(7,8);
		map[8][6] = new DangerousBox(7,9);
		map[9][6] = new DangerousBox(7,10);
		map[10][6] = new DangerousBox(7,11);
		map[11][6] = new Box(7,12);
		map[12][6] = new DangerousBox(7,13);
		map[13][6] = new Box(7,14);
		
		map[0][7] = new Box(8,1);
		map[1][7] = new Box(8,2);
		map[2][7] = new Box(8,3);
		map[3][7] = new DangerousBox(8,4);
		map[5][7] = new DangerousBox(8,6);
		map[6][7] = new Box(8,7);
		map[7][7] = new DangerousBox(8,8);
		map[8][7] = new DangerousBox(8,9);
		map[11][7] = new DangerousBox(8,12);
		map[12][7] = new DangerousBox(8,13);
		map[13][7] = new Box(8,14);
		
		map[0][8] = new Box(9,1);
		map[1][8] = new DangerousBox(9,2);
		map[2][8] = new DangerousBox(9,3);
		map[3][8] = new DangerousBox(9,4);
		map[4][8] = new DangerousBox(9,5);
		map[6][8] = new DangerousBox(9,7);
		map[7][8] = new DangerousBox(9,8);
		map[8][8] = new Box(9,9);
		map[9][8] = new DangerousBox(9,10);
		map[10][8] = new DangerousBox(9,11);
		map[12][8] = new DangerousBox(9,13);
		map[13][8] = new Box(9,14);
		
		map[0][9] = new Box(10,1);
		map[1][9] = new DangerousBox(10,2);
		map[2][9] = new DangerousBox(10,3);
		map[3][9] = new DangerousBox(10,4);
		map[4][9] = new DangerousBox(10,5);
		map[5][9] = new DangerousBox(10,6);
		map[7][9] = new DangerousBox(10,8);
		map[8][9] = new DangerousBox(10,9);
		map[9][9] = new DangerousBox(10,10);
		map[10][9] = new DangerousBox(10,11);
		map[12][9] = new DangerousBox(10,13);
		map[13][9] = new Box(10,14);
		
		map[0][10] = new DangerousBox(11,1);
		map[1][10] = new Box(11,2);
		map[2][10] = new DangerousBox(11,3);
		map[3][10] = new DangerousBox(11,4);
		map[4][10] = new Box(11,5);
		map[5][10] = new DangerousBox(11,6);
		map[7][10] = new DangerousBox(11,8);
		map[8][10] = new Box(11,9);
		map[9][10] = new DangerousBox(11,10);
		map[10][10] = new Box(11,11);
		map[11][10] = new DangerousBox(11,12);
		map[13][10] = new DangerousBox(11,14);
		
		map[0][11] = new DangerousBox(12,1);
		map[1][11] = new Box(12,2);
		map[2][11] = new DangerousBox(12,3);
		map[3][11] = new Box(12,4);
		map[4][11] = new DangerousBox(12,5);
		map[5][11] = new AlienBox(12,6);
		map[7][11] = new HumanBox(12,8);
		map[8][11] = new Box(12,9);
		map[9][11] = new DangerousBox(12,10);
		map[10][11] = new Box(12,11);
		map[11][11] = new DangerousBox(12,12);
		map[12][11] = new DangerousBox(12,13);
		map[13][11] = new Box(12,14);
		
		
		map[0][12] = new DangerousBox(13, 1);
		map[1][12] = new Box(13, 2);
		map[2][12] = new DangerousBox(13, 3);
		map[3][12] = new DangerousBox(13, 4);
		map[4][12] = new Box(13, 5);
		map[5][12] = new DangerousBox(13, 6);
		map[7][12] = new DangerousBox(13, 8);
		map[8][12] = new Box(13, 9);
		map[9][12] = new DangerousBox(13, 10);
		map[10][12] = new Box(13, 11);
		map[11][12] = new DangerousBox(13, 12);
		map[12][12] = new DangerousBox(13, 13);
		map[13][12] = new DangerousBox(13, 14);
		
		map[0][13] = new DangerousBox(14, 1);
		map[1][13] = new DangerousBox(14, 2);
		map[2][13] = new Box(14, 3);
		map[3][13] = new DangerousBox(14, 4);
		map[4][13] = new DangerousBox(14, 5);
		map[5][13] = new DangerousBox(14, 6);
		map[7][13] = new DangerousBox(14, 8);
		map[8][13] = new DangerousBox(14, 9);
		map[9][13] = new DangerousBox(14, 10);
		map[10][13] = new DangerousBox(14, 11);
		map[11][13] = new DangerousBox(14, 12);
		map[12][13] = new DangerousBox(14, 13);
		map[13][13] = new Box(14, 14);
		
		map[1][14] = new DangerousBox(15, 2);
		map[4][14] = new Box(15, 5);
		map[5][14] = new DangerousBox(15, 6);
		map[6][14] = new DangerousBox(15, 7);
		map[7][14] = new DangerousBox(15, 8);
		map[8][14] = new Box(15, 9);
		map[9][14] = new DangerousBox(15, 10);
		map[10][14] = new DangerousBox(15, 11);
		map[11][14] = new DangerousBox(15, 12);
		map[12][14] = new DangerousBox(15, 13);
		map[13][14] = new Box(15, 14);
		
		map[0][15] = new Box(16, 1);
		map[1][15] = new DangerousBox(16, 2);
		map[2][15] = new Box(16, 3);
		map[3][15] = new Box(16, 4);
		map[4][15] = new DangerousBox(16, 5);
		map[5][15] = new DangerousBox(16, 6);
		map[6][15] = new DangerousBox(16, 7);
		map[7][15] = new DangerousBox(16, 8);
		map[8][15] = new DangerousBox(16, 9);
		map[9][15] = new DangerousBox(16, 10);
		map[10][15] = new DangerousBox(16, 11);
		map[11][15] = new Box(16, 12);
		map[12][15] = new DangerousBox(16, 13);
		map[13][15] = new DangerousBox(16, 14);
		
		map[0][16] = new Box(17, 1);
		map[1][16] = new DangerousBox(17, 2);
		map[2][16] = new DangerousBox(17, 3);
		map[3][16] = new Box(17, 4);
		map[4][16] = new DangerousBox(17, 5);
		map[5][16] = new Box(17, 6);
		map[6][16] = new DangerousBox(17, 7);
		map[7][16] = new DangerousBox(17, 8);
		map[8][16] = new DangerousBox(17, 9);
		map[9][16] = new DangerousBox(17, 10);
		map[10][16] = new Box(17, 11);
		map[11][16] = new DangerousBox(17, 12);
		map[12][16] = new DangerousBox(17, 13);
		map[13][16] = new Box(17, 14);
		
		map[0][17] = new Box(18, 1);
		map[1][17] = new DangerousBox(18, 2);
		map[2][17] = new DangerousBox(18, 3);
		map[3][17] = new Box(18, 4);
		map[4][17] = new DangerousBox(18, 5);
		map[5][17] = new Box(18, 6);
		map[6][17] = new Box(18, 7);
		map[7][17] = new Box(18, 8);
		map[8][17] = new DangerousBox(18, 9);
		map[11][17] = new Box(18, 12);
		map[12][17] = new DangerousBox(18, 13);
		
		map[1][18] = new DangerousBox(19, 2);
		map[3][18] = new DangerousBox(19, 4);
		map[4][18] = new DangerousBox(19, 5);
		map[5][18] = new DangerousBox(19, 6);
		map[6][18] = new DangerousBox(19, 7);
		map[7][18] = new DangerousBox(19, 8);
		map[8][18] = new DangerousBox(19, 9);
		map[11][18] = new DangerousBox(19, 12);
		map[12][18] = new DangerousBox(19, 13);
		
		map[1][19] = new DangerousBox(20, 2);
		map[4][19] = new DangerousBox(20, 5);
		map[5][19] = new DangerousBox(20, 6);
		map[6][19] = new Box(20, 7);
		map[7][19] = new Box(20, 8);
		map[10][19] = new DangerousBox(20, 11);
		map[11][19] = new DangerousBox(20, 12);
		map[12][19] = new DangerousBox(20, 13);
		map[13][19] = new Box(20, 14);
		
		map[0][20] = new Box(21, 1);
		map[1][20] = new DangerousBox(21, 2);
		map[2][20] = new DangerousBox(21, 3);
		map[3][20] = new DangerousBox(21, 4);
		map[4][20] = new Box(21, 5);
		map[5][20] = new DangerousBox(21, 6);
		map[6][20] = new DangerousBox(21, 7);
		map[7][20] = new DangerousBox(21, 8);
		map[8][20] = new DangerousBox(21, 9);
		map[9][20] = new DangerousBox(21, 10);
		map[10][20] = new DangerousBox(21, 11);
		map[11][20] = new Box(21, 12);
		map[12][20] = new DangerousBox(21, 13);
		map[13][20] = new DangerousBox(21, 14);
		
		map[0][21] = new Box(22, 1);
		map[1][21] = new LifeboatBox(22, 2);
		map[2][21] = new DangerousBox(22, 3);
		map[3][21] = new DangerousBox(22, 4);
		map[4][21] = new DangerousBox(22, 5);
		map[5][21] = new DangerousBox(22, 6);
		map[7][21] = new Box(22, 8);
		map[8][21] = new DangerousBox(22, 9);
		map[9][21] = new DangerousBox(22, 10);
		map[10][21] = new DangerousBox(22, 11);
		map[11][21] = new DangerousBox(22, 12);
		map[12][21] = new LifeboatBox(22, 13);
		map[13][21] = new DangerousBox(22, 14);
		
		map[1][22] = new DangerousBox(23, 2);
		map[2][22] = new Box(23, 3);
		map[3][22] = new Box(23, 4);
		map[4][22] = new Box(23, 5);
		map[5][22] = new Box(23, 6);
		map[8][22] = new DangerousBox(23, 9);
		map[9][22] = new Box(23, 10);
		map[10][22] = new Box(23, 11);
		map[11][22] = new Box(23, 12);
		map[12][22] = new DangerousBox(23, 13);
		map[13][22] = new DangerousBox(23, 14);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	@Override
	public String toString(){
		String ret="";
		ret+="-----------------------------------------------------------------------\n";
		for	(int i=0;i<14;i++){
			ret+="| ";
			for(int j=0;j<23;j++){
				if (map[i][j] instanceof Wall) {
				ret+="W| ";}
				else if (map[i][j] instanceof DangerousBox) {
					ret+="D| ";}
				else if (map[i][j] instanceof HumanBox) {
					ret+="H| ";}
				else if (map[i][j] instanceof AlienBox) {
					ret+="A| ";}
				else if (map[i][j] instanceof LifeboatBox) {
					ret+="L| ";}
				else if (map[i][j] instanceof Box) {
					ret+=" | ";}
				else ret+=" | ";
			}
			ret+="\n";
			ret+="-----------------------------------------------------------------------\n";
		}
	ret+="\n";
	return ret;
	}
	
}