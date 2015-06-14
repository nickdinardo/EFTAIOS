package it.polimi.ingsw.dinapolidinardo.model.boxes;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

/**
 * Class that represent a wall on the map, 
 * provides an overrided getter that signals that you  
 * can't cross this box
 */
public class Wall extends Box {
	public Wall(Coordinates coordinates) {
		super(coordinates);
		
	}
	
	public Wall(int x, int y){
		super(x, y);
	}
	
	@Override
	public boolean isCanBeCrossedType(){
		return false;
	}
}