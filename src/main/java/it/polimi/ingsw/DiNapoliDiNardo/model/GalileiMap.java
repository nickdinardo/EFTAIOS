package it.polimi.ingsw.DiNapoliDiNardo.model;
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
	
	public void buildmap(){
		for (int i=0; i<14; i++){
			for (int j=0; j<23; j++){
				map[i][j] = new Wall(i+1,j+1);
			}
		}
		map[1][0] = new DangerousBox(1,2);
		map[2][0] = new DangerousBox(1,3);
		map[3][0] = new Box(1,4);
		map[0][4] = new Box(1,5);
		map[0][5] = new Box(1,6);
		map[0][8] = new Box(1,9);
		map[0][9] = new Box(1,10);
		map[0][10] = new Box(1,11);
		map[0][11] = new Box(1,12);
		map[0][12] = new Box(1,13);
		map[0][13] = new DangerousBox(1,14);
		
		map[1][0] = new DangerousBox(2,1);
		map[1][1] = new LifeboatBox(2,2);
		map[1][2] = new DangerousBox(2,3);
		map[1][3] = new DangerousBox(2,4);
		map[1][4] = new Box(2,5);
		map[1][5] = new DangerousBox(2,6);
		map[1][7] = new DangerousBox(2,8);
		map[1][8] = new DangerousBox(2,9);
		map[1][9] = new Box(2,10);
		map[1][10] = new DangerousBox(2,11);
		map[1][11] = new DangerousBox(2,12);
		map[1][12] = new LifeboatBox(2,13);
		map[1][13] = new DangerousBox(2,14);
		
		map[2][0] = new Box(3,1);
		for (int i=1; i<13; i++){
			map[2][i] = new DangerousBox(3,i);
		}
		map[2][13] = new Box(3,14);
		
		map[3][1] = new DangerousBox(4,2);
		map[3][2] = new DangerousBox(4,3);
		map[3][4] = new DangerousBox(4,5);
		map[3][7] = new DangerousBox(4,8);
		map[3][8] = new DangerousBox(4,9);
		map[3][9] = new Box(4,10);
		map[3][10] = new DangerousBox(4,11);
		map[3][11] = new DangerousBox(4,12);
		map[3][12] = new DangerousBox(4,13);
		map[3][13] = new Box(4,14);
		
		map[4][1] = new Box(5,2);
		map[4][2] = new DangerousBox(5,3);
		map[4][3] = new DangerousBox(5,4);
		map[4][4] = new DangerousBox(5,5);
		map[4][5] = new DangerousBox(5,6);
		map[4][7] = new DangerousBox(5,8);
		map[4][8] = new DangerousBox(5,9);
		map[4][9] = new DangerousBox(5,10);
		map[4][10] = new DangerousBox(5,11);
		map[4][11] = new Box(5,12);
		map[4][12] = new DangerousBox(5,13);
		
		map[5][0] = new Box(6,1);
		map[5][1] = new DangerousBox(6,2);
		map[5][2] = new DangerousBox(6,3);
		map[5][3] = new DangerousBox(6,4);
		map[5][4] = new DangerousBox(6,5);
		map[5][5] = new DangerousBox(6,6);
		map[5][6] = new DangerousBox(6,7);
		map[5][7] = new DangerousBox(6,8);
		map[5][8] = new DangerousBox(6,9);
		map[5][9] = new Box(6,10);
		map[5][10] = new DangerousBox(6,11);
		
		map[6][0] = new DangerousBox(7,1);
		map[6][1] = new DangerousBox(7,2);
		map[6][2] = new DangerousBox(7,3);
		map[6][3] = new DangerousBox(7,4);
		map[6][4] = new DangerousBox(7,5);
		map[6][5] = new DangerousBox(7,6);
		map[6][6] = new Box(7,7);
		map[6][7] = new DangerousBox(7,8);
		map[6][8] = new DangerousBox(7,9);
		map[6][9] = new DangerousBox(7,10);
		map[6][10] = new DangerousBox(7,11);
		map[6][11] = new Box(7,12);
		map[6][12] = new DangerousBox(7,13);
		map[6][13] = new Box(7,14);
		
		map[7][0] = new Box(8,1);
		map[7][1] = new Box(8,2);
		map[7][2] = new Box(8,3);
		map[7][3] = new DangerousBox(8,4);
		map[7][5] = new DangerousBox(8,6);
		map[7][6] = new Box(8,7);
		map[7][7] = new DangerousBox(8,8);
		map[7][8] = new DangerousBox(8,9);
		map[7][11] = new DangerousBox(8,12);
		map[7][12] = new DangerousBox(8,13);
		map[7][13] = new Box(8,14);
		
		map[8][0] = new Box(9,1);
		map[8][1] = new DangerousBox(9,2);
		map[8][2] = new DangerousBox(9,3);
		map[8][3] = new DangerousBox(9,4);
		map[8][4] = new DangerousBox(9,5);
		map[8][6] = new DangerousBox(9,7);
		map[8][7] = new DangerousBox(9,8);
		map[8][8] = new Box(9,9);
		map[8][9] = new DangerousBox(9,10);
		map[8][10] = new DangerousBox(9,11);
		map[8][12] = new DangerousBox(9,13);
		map[8][13] = new Box(9,14);
		
		map[9][0] = new Box(10,1);
		map[9][1] = new DangerousBox(10,2);
		map[9][2] = new DangerousBox(10,3);
		map[9][3] = new DangerousBox(10,4);
		map[9][4] = new DangerousBox(10,5);
		map[9][5] = new DangerousBox(10,6);
		map[9][7] = new DangerousBox(10,8);
		map[9][8] = new DangerousBox(10,9);
		map[9][9] = new DangerousBox(10,10);
		map[9][10] = new DangerousBox(10,11);
		map[9][12] = new DangerousBox(10,13);
		map[9][13] = new Box(10,14);
		
		map[10][0] = new DangerousBox(11,1);
		map[10][1] = new Box(11,2);
		map[10][2] = new DangerousBox(11,3);
		map[10][3] = new DangerousBox(11,4);
		map[10][4] = new Box(11,5);
		map[10][5] = new DangerousBox(11,6);
		map[10][7] = new DangerousBox(11,8);
		map[10][8] = new Box(11,9);
		map[10][9] = new DangerousBox(11,10);
		map[10][10] = new Box(11,11);
		map[10][11] = new DangerousBox(11,12);
		map[10][13] = new DangerousBox(11,14);
		
		map[11][0] = new DangerousBox(12,1);
		map[11][1] = new Box(12,2);
		map[11][2] = new DangerousBox(12,3);
		map[11][3] = new Box(12,4);
		map[11][4] = new DangerousBox(12,5);
		map[11][5] = new AlienBox(12,6);
		map[11][7] = new HumanBox(12,7);
		map[11][8] = new Box(12,9);
		map[11][9] = new DangerousBox(12,10);
		map[11][10] = new Box(12,11);
		map[11][11] = new DangerousBox(12,12);
		map[11][12] = new DangerousBox(12,13);
		map[11][13] = new Box(12,14);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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