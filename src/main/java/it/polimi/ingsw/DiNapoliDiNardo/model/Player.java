package it.polimi.ingsw.DiNapoliDiNardo.model;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;


public abstract class Player {
	
	protected Box position;
	
	
	public Box getPosition() {
		return position;
	}
	public void setPosition(Box position) {
		this.position = position;
	}
	
	public boolean isValidDoubleMovement(Box destination){
		if (destination instanceof Wall) return false;
		if (destination instanceof HumanBox) return false;
		if (destination instanceof AlienBox) return false;
		//check the reachable boxes down or on the same level of actual position
		if (destination.getCoordY()-position.getCoordY() == 0 || destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == 2)
			if (destination.getCoordX()-position.getCoordX() > -3 && destination.getCoordX()-position.getCoordX() <3)
				return true;
		//check the upper reachable boxes
		if (destination.getCoordY()-position.getCoordY() == -1)
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 0 || destination.getCoordX()-position.getCoordX() == 1)
				return true;
		if (destination.getCoordY()-position.getCoordY() == -2)
			if (destination.getCoordX()-position.getCoordX() == 0)
				return true;
		return false;
	};
	
		
	}

