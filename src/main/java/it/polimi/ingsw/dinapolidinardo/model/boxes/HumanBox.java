package it.polimi.ingsw.dinapolidinardo.model.boxes;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

/**
 * Class that represent the starting sector 
 * for the humans on the map.
 */
public class HumanBox extends Box {
	
	
	public HumanBox(Coordinates coordinates) {
		super(coordinates);
		
	}
	
	public HumanBox(int x, int y){
		super(x, y);
	}
	
	@Override
	public boolean isCanBeCrossedType(){
		return false;
	}
	
}