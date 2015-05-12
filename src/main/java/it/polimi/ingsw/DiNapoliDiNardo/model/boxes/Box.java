package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;
import it.polimi.ingsw.DiNapoliDiNardo.model.*;
import java.util.ArrayList;

public class Box {
	private int coordX;
	private int coordY;
	
	private ArrayList<Player> playerHere = new ArrayList<Player>();
	
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
		playerHere.add(p);
		
	}
	
	public boolean isEmpty(){
		return playerHere.size() == 0;
	}
}
