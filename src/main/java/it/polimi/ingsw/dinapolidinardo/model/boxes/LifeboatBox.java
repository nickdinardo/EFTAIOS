package it.polimi.ingsw.dinapolidinardo.model.boxes;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;


/**
 * Class that represent a lifeboat ship on the map, 
 * provides an overrided getter that signals that you  
 * can escape from the main ship from here 
 */
public class LifeboatBox extends Box {
	private boolean lifeBoatShipHere = true;
	private int number;
	
	
	public LifeboatBox(Coordinates coordinates, int number) {
		super(coordinates);
		this.number = number;
		
	}
	
	public LifeboatBox(int x, int y, int number){
		super(x, y);
		this.number = number;
	}
	
	@Override
	public boolean isLifeBoatShipHere() {
		return this.lifeBoatShipHere;
	}
	@Override
	public void setLifeBoatShipHere(boolean lifeboat) {
		this.lifeBoatShipHere = lifeboat;
	}
	
	public int getNumber() {
		return number;
	}
}
