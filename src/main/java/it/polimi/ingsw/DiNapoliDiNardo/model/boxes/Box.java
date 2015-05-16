package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;
import it.polimi.ingsw.DiNapoliDiNardo.model.*;
import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;

import java.util.ArrayList;

public class Box {
	//private int coordX;
	//private int coordY;
	
	private Coordinates coord;
	
	
	private ArrayList<Player> playerHere = new ArrayList<Player>();
	
	public Box(Coordinates coordinates){
		this.setCoord(coordinates);
		
	}
	
	public Box(int x, int y){
		this.setCoordX(x);
		this.setCoordY(y);
	}
	
	public int getCoordX(){
		return this.coord.getCoordX();
	}

	public int getCoordY() {
		return this.coord.getCoordY();
	}

	public void setCoord(Coordinates coordinates) {
		this.coord = coordinates;
	}
	
	public void setCoordX(int x){
		this.coord.setCoordX(x);
	}
	
	public void setCoordY(int y){
		this.coord.setCoordY(y);
	}
	
	public void setPlayer(Player p){
		playerHere.add(p);
		
	}
	
	public boolean isEmpty(){
		return playerHere.size() == 0;
	}
}
