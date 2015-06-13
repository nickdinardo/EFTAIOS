package it.polimi.ingsw.dinapolidinardo.model.boxes;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

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