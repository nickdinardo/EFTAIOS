package it.polimi.ingsw.DiNapoliDiNardo.model;
import java.util.ArrayList;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;

public class GalileiMap {
	private Box[][] map;
	private Box humanStartBox;
	private Box alienStartBox;
	
		
	public Box[][] getMap() {
		return map;
	}

	public void setMap(Box[][] map) {
		this.map = map;
	}
	
	public Box getHumanStartBox() {
		return humanStartBox;
	}

	public Box getAlienStartBox() {
		return alienStartBox;
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
	
	
	public GalileiMap(){
		this.setMap(new Box[14][23]);
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
		alienStartBox = map[5][11];
		map[7][11] = new HumanBox(12,8);
		humanStartBox = map[7][11];
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
	
	/*@Override
	public String toString(){
		String ret ="   ___       ___       ___       ___       ___       ___       ___       ___       ___       ___       ___       ___    \n";
				ret += "  /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\ \n";
				ret += " /    \\___/ C01\\___/    \\___/ G01\\___/ I01\\___/ K01\\___/ M01\\___/    \\___/ Q01\\___/    \\___/ U01\\___/    \\ \n";
				ret += "\\     /  \\     /         /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /  \\        \\     /  \\     /\n";
				ret += " \\___/ B01\\___/      ___/ F01\\___/ H01\\___/ J01\\___/ L01\\___/ N01\\___/ P01\\___/ R01\\___     \\___/ V01\\___/ \n";
				ret += "  /  \\  D  /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /  \\     /  \\     /  \\ \n";
				ret += " / A02\\___/ C02\\___/ E02\\___/ G02\\___/ I02\\___/ K02\\___/ M02\\___/ O02\\___/ Q02\\___/ S02\\___/ U02\\___/ W02\\\n";
				ret += "\\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /\n";
				ret += " \\___/ B02\\___/ D02\\___/ F02\\___/ H02\\___/ J02\\___/ L02\\___/ N02\\___/ P02\\___/ R02\\___/ T02\\___/  3 \\___/ \n";
				ret += "  /  \\  L  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  L  /  \\ \n";
				ret += " / A03\\___/ C03\\___/ E03\\___/ G03\\___/ I03\\___/ K03\\___/ M03\\___/    \\___/ Q03\\___/    \\___/ U03\\___/ W03\\\n";
				ret += "\\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\        \\  D  /  \\     /\n";
				ret += " \\___/ B03\\___/ D03\\___/ F03\\___/ H03\\___/ J03\\___/ L03\\___/ N03\\   / P03\\___/ R03\\___     \\___/ V03\\___/ \n";
				ret += "  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\     /  \\  D  /  \\ \n";
				ret += " / A04\\___/ C04\\___/ E04\\___/ G04\\___/ I04\\___/ K04\\___/ M04\\___/    \\___/ Q04\\___/ S04\\   / U04\\___/ W04\\\n";
				ret += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\  D  /  \\     /\n";
				ret += " \\___/ B04\\___/    \\___/ F04\\___/ H04\\___/ J04\\___/ L04\\___/ N04\\___/ P04\\___/ R04\\___/    \\___/ V04\\___/ \n";
				ret += "  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\     /  \\     /  \\     /  \\  D  /  \\ \n";
				ret += " / A05\\___/ C05\\___/ E05\\___/ G05\\___/ I05\\___/ K05\\___/ M05\\___/ O05\\___/ Q05\\___/ S05\\___/ U05\\___/ W05\\\n";
				ret += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /\n";
				ret += " \\___/ B05\\___/ D05\\___/ F05\\___/    \\___/ J05\\___/ L05\\___/ N05\\___/ P05\\___/ R05\\___/ T05\\___/ V05\\___/ \n";
				ret += "  /  \\     /  \\  D  /  \\  D  /  \\        \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\ \n";
				ret += " / A06\\___/ C06\\___/ E06\\___/ G06\\___     \\___/ K06\\___/ M06\\___/ O06\\___/ Q06\\___/ S06\\___/ U06\\___/ W06\\\n";
				ret += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /\n";
				ret += " \\___/ B06\\___/    \\___/ F06\\___/ H06\\___/ J06\\___/  A \\___/ N06\\___/ P06\\___/ R06\\___/ T06\\___/ V06\\___/ \n";
				ret += "  /  \\  D  /  \\        \\  D  /  \\  D  /  \\  D  /  \\ Box /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\ \n";
				ret += " /    \\___/ C07\\        \\___/ G07\\___/ I07\\___/    \\___/    \\___/ O07\\___/ Q07\\___/ S07\\___/ U07\\___/    \\\n";
				ret += "\\        \\  D  /         /  \\     /  \\  D  /                      \\  D  /  \\  D  /  \\  D  /  \\  D  /         /\n";
				ret += " \\        \\___/      ___/ F07\\___/ H07\\___/      ___       ___     \\___/ P07\\___/ R07\\___/ T07\\___/         / \n";
				ret += "  /         /  \\     /  \\  D  /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\     /  \\     /  \\        \\ \n";
				ret += " /      ___/ C08\\___/ E08\\___/ G08\\___/ I08\\___/ K08\\___/ M08\\___/ O08\\___/ Q08\\___/ S08\\___/ U08\\___     \\\n";
				ret += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /\n";
				ret += " \\___/ B08\\___/ D08\\___/ F08\\___/ H08\\___/ J08\\___/  H \\___/ N08\\___/ P08\\___/ R08\\___/ T08\\___/ V08\\___/ \n";
			    ret += "  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\ Box /  \\  D  /  \\  D  /  \\     /  \\     /  \\     /  \\ \n";
				ret += " / A09\\___/ C09\\___/ E09\\___/ G09\\___/ I09\\___/ K09\\___/ M09\\___/ O09\\___/ Q09\\___/ S09\\___/ U09\\___/ W09\\\n";
				ret += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /\n";
				ret += " \\___/ B09\\___/ D09\\___/ F09\\___/ H09\\___/ J09\\___/ L09\\___/ N09\\___/ P09\\___/ R09\\___/    \\___/ V09\\___/ \n";
				ret += "  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /         /  \\  D  /  \\ \n";
				ret += " / A10\\___/ C10\\___/ E10\\___/ G10\\___/ I10\\___/ K10\\___/ M10\\___/ O10\\___/ Q10\\___/         / U10\\___/ W10\\\n";
				ret += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /            \\  D  /  \\     /\n";
				ret += " \\___/ B10\\___/ D10\\___/ F10\\___/    \\___/ J10\\___/ L10\\___/ N10\\___/ P10\\___/              \\___/ V10\\___/ \n";
				ret += "  /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\               /  \\  D  /  \\ \n";
				ret += " / A11\\___/ C11\\___/ E11\\___/ G11\\   / I11\\___/ K11\\___/ M11\\___/ O11\\___/ Q11\\          ___/ U11\\___/ W11\\\n";
				ret += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\     /         /  \\  D  /  \\     /\n";
				ret += " \\___/ B11\\___/ D11\\___/ F11\\___/    \\___/ J11\\___/ L11\\___/ N11\\___/ P11\\___/      ___/ T11\\___/ V11\\___/ \n";
				ret += "  /  \\  D  /  \\  D  /  \\  D  /  \\        \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\ \n";
				ret += " / A12\\___/ C12\\___/ E12\\___/ G12\\___     \\___/ K12\\___/ M12\\___/ O12\\___/ Q12\\___/ S12\\___/ U12\\___/ W12\\\n";
				ret += "\\     /  \\  D  /  \\     /  \\     /  \\        \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /\n";
				ret += " \\___/ B12\\___/ D12\\___/    \\___/ H12\\___     \\___/ L12\\___/ N12\\___/ P12\\___/ R12\\___/ T12\\___/ V12\\___/ \n";
				ret += "  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\        \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\  D  /  \\ \n";
				ret += " / A13\\___/ C13\\___/ E13\\   / G13\\___/ I13\\___     \\___/ M13\\___/ O13\\___/ Q13\\___/ S13\\___/ U13\\___/ W13\\\n";
				ret += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /\n";
				ret += " \\___/ B13\\___/ D13\\___/    \\___/ H13\\___/ J13\\___/ L13\\___/ N13\\___/ P13\\___/ R13\\___/ T13\\___/  4 \\___/ \n";
				ret += "  /  \\  L  /  \\  D  /         /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  L  /  \\ \n";
				ret += " / A14\\___/ C14\\___/         / G14\\___/ I14\\___/ K14\\___/ M14\\___/ O14\\___/ Q14\\___/    \\___/ U14\\___/ W14\\\n";
				ret += "\\  D  /  \\     /  \\        \\     /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /         /  \\  D  /  \\  D  /\n";
				ret += " \\___/ B14\\___/ D14\\___     \\___/ H14\\___/ J14\\___/ L14\\___/ N14\\___/ P14\\___/      ___/ T14\\___/ V14\\___/ \n";
				ret += "     \\  D  /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\     /  \\     /  \\  D  /     \n";
				ret += "      \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/      \n";
		
		return ret;
		
	}*/
	
}