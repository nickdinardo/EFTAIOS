package it.polimi.ingsw.dinapolidinardo.model.boxes;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

public class DangerousBox extends Box{
		
	public DangerousBox(Coordinates coordinates) {
		super(coordinates);
		
	}
	
	public DangerousBox(int x, int y){
		super(x, y);
	}
	
	@Override
	public boolean isDrawingSectorCardHere() {
		return true;
	}
	
}
