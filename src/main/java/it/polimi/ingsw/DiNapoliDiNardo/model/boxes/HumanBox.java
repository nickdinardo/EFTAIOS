package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

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