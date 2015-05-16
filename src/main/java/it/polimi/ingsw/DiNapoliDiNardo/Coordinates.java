package it.polimi.ingsw.DiNapoliDiNardo;

public class Coordinates {
	int coordX;
	int coordY;
	
	public Coordinates (int x, int y){
		this.setCoordX(x);
		this.setCoordY(y);
	}
	
	public int getCoordX(){
		return this.coordX;
	}
	
	public int getCoordY(){
		return this.coordY;
	}
	
	public void setCoordX(int x){
		this.coordX = x;
	}
	
	public void setCoordY(int y){
		this.coordY = y;
	}
}

