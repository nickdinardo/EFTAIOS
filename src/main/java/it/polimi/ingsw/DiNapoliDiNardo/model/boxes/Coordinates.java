package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;

public class Coordinates implements java.io.Serializable {
	int coordX;
	int coordY;
	
	public Coordinates (){
	}
	
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

