package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;

public class Box {
	private int coordX;
	private int coordY;
	
	public Box(int x, int y){
		this.setCoordX(x);
		this.setCoordY(y);
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
}
