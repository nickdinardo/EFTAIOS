package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;
import java.util.ArrayList;

public class Box {
	private int coordX;
	private int coordY;
	
	private Player[] playerHere = new ArrayList(0);
	
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
	
	public void setPlayer(Player p){
		playerHere.add(Player p);
		
	}
	
	public boolean isEmpty(){
		return playerHere.size() == 0;
	}
}
