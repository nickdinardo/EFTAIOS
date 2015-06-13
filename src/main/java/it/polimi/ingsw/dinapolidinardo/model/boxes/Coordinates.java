package it.polimi.ingsw.dinapolidinardo.model.boxes;

public class Coordinates implements java.io.Serializable {
	
	
	
	private static final long serialVersionUID = 1L;
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

