package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

public class DangerousBox extends Box{
	private boolean drawingSectorCardHere = true;
	
	public DangerousBox(Coordinates coordinates) {
		super(coordinates);
		
	}
	
	public DangerousBox(int x, int y){
		super(x, y);
	}
	
	@Override
	public boolean isDrawingSectorCardHere() {
		return drawingSectorCardHere;
	}
	
}
