package it.polimi.ingsw.dinapolidinardo.model.boxes;



import it.polimi.ingsw.dinapolidinardo.model.Player;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that represent a single safe sector of the map, is also super class for all the 
 * others box since is neutral and without special properties
 */
public class Box {
	
	
	private Coordinates coord = new Coordinates(0, 0);
	private List<Player> playerHere = new ArrayList<Player>();
	protected boolean lifeBoatShipHere = false;
	
	/**
	 * First constructor, via coordinates object
	 * @param coordinates the coordinates on the map of this box
	 */
	public Box(Coordinates coordinates){
		this.setCoord(coordinates);
	}
	
	/**
	 * Second constructor, with x and y coordinate defined explicitly and put in the owned Coordinate object
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 * @see Coordinates
	 */
	public Box(int x, int y){
		this.coord.setCoordX(x);
		this.coord.setCoordY(y);
	}
	
	
	//getters and setters
	
	public int getCoordX(){
		return this.coord.getCoordX();
	}

	public int getCoordY() {
		return this.coord.getCoordY();
	}

	public void setCoord(Coordinates coordinates) {
		this.coord = coordinates;
	}
	
	public List<Player> getPlayersHere(){
		return playerHere;
	}

	public void setPlayer(Player p){
		playerHere.add(p);
		
	}
	
	public void unsetPlayer(Player p){
		playerHere.remove(p);
		
	}
		
	public boolean isEmpty(){
		return playerHere.isEmpty();
	}
	
	public boolean isCanBeCrossedType(){
		return true;
	}
	
	public boolean isLifeBoatShipHere() {
		return this.lifeBoatShipHere;
	}
	
	public void setLifeBoatShipHere(boolean lifeboat) {
		this.lifeBoatShipHere = lifeboat;
	}
	
	public boolean isDrawingSectorCardHere() {
		return false;
	}
	
}
