package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

public class Wall extends Box {
	public Wall(Coordinates coordinates) {
		super(coordinates);
		
	}
	
	public Wall(int x, int y){
		super(x, y);
	}
	
}