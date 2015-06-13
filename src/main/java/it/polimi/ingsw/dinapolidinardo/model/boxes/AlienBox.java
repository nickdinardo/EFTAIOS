package it.polimi.ingsw.dinapolidinardo.model.boxes;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

public class AlienBox extends Box {
	
	
	public AlienBox(Coordinates coordinates) {
		super(coordinates);
		
	}
	
	public AlienBox(int x, int y){
		super(x,y);
	}
	
	@Override
	public boolean isCanBeCrossedType(){
		return false;
	}
	
}
