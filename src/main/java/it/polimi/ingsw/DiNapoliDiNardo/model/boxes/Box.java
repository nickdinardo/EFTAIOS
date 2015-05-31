package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;
import it.polimi.ingsw.DiNapoliDiNardo.model.*;


import java.util.ArrayList;
import java.util.List;

public class Box {
	
	private Coordinates coord = new Coordinates(0, 0);
	private List<Player> playerHere = new ArrayList<Player>();
	protected boolean lifeBoatShipHere = false;
	protected boolean drawingSectorCardHere = false;
	
	public Box(Coordinates coordinates){
		this.setCoord(coordinates);
	}
	
	public Box(int x, int y){
		this.coord.setCoordX(x);
		this.coord.setCoordY(y);
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
	
	
	public void setPlayer(Player p){
		playerHere.add(p);
		
	}
	
	public void unsetPlayer(Player p){
		playerHere.remove(p);
		
	}
	
	public void clearPlayersHere(){
		playerHere.clear();
		
	}
	
	public boolean isEmpty(){
		return playerHere.isEmpty();
	}
	
	public List<Player> getPlayerHere(){
		return playerHere;
	}

	public boolean isLifeBoatShipHere() {
		return this.lifeBoatShipHere;
	}
	public void setLifeBoatShipHere(boolean lifeboat) {
		this.lifeBoatShipHere = lifeboat;
	}
	public boolean isDrawingSectorCardHere() {
		return drawingSectorCardHere;
	}
	
}
