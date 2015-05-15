package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.AlienBox;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.HumanBox;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Wall;

public class AlienPlayer extends Player{
	
	protected boolean humanfed = false;
	
	public AlienPlayer(GalileiMap Galilei){
		this.setPosition(Galilei.getMap()[5][11]);
	}
	
	public boolean isValidTripleMovement(Box destination){
		if (destination instanceof Wall) return false;
		if (destination instanceof HumanBox) return false;
		if (destination instanceof AlienBox) return false;
		//check the reachable boxes down or on the same level of actual position
		if (destination.getCoordY()-position.getCoordY() >-1 && destination.getCoordY()-position.getCoordY() < 4)
			if (destination.getCoordX()-position.getCoordX() >-4 && destination.getCoordX()-position.getCoordX() <4)
				return true;
		//check the upper reachable boxes
		if (destination.getCoordY()-position.getCoordY() == -1)
			if (destination.getCoordX()-position.getCoordX() >-3 && destination.getCoordX()-position.getCoordX() <3)
				return true;
		if (destination.getCoordY()-position.getCoordY() == -2)
			if (destination.getCoordX()-position.getCoordX() >-2 && destination.getCoordX()-position.getCoordX() <2)
				return true;
		if (destination.getCoordY()-position.getCoordY() == -3)
			if (destination.getCoordX()-position.getCoordX() == 0)
				return true;
		return false;
	};
	
	
	
}
