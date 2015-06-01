package it.polimi.ingsw.DiNapoliDiNardo.model;

import java.util.List;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.LifeboatBox;



public class AlienPlayer extends Player {
	
	private boolean humanfed = false;
	private boolean hasAttacked = false;
	
	
	

	//constructor
	 	public AlienPlayer(GalileiMap galilei, GameState gs, String name){
			this.gamestate = gs;
			this.name = name;
			this.setPosition(galilei.getAlienStartBox());
			position.setPlayer(this);
			
		}
	
	//triple movement check
	public boolean isValidTripleMovement(Box destination, Box position){
		List<Box> aroundBoxes = gamestate.getGalilei().givemeAroundBoxes(position);
		List<Box> oneStepBoxes = checkBoxes(aroundBoxes, position);
		for (Box box: oneStepBoxes){
			if (isValidDoubleMovement(destination, box))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean movement (Box destination, Box position){
		if (destination instanceof LifeboatBox)
			return false;
		if (humanfed){
			if(isValidTripleMovement(destination, position)){
				this.position.unsetPlayer(this);
				this.position = destination;
				this.position.setPlayer(this);
				return true;
			}
			else{
				return false;
			}
		}
		else {
			if(isValidDoubleMovement(destination, position)){
				this.position.unsetPlayer(this);
				this.position = destination;
				this.position.setPlayer(this);
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	
	
	public boolean isHasAttacked() {
		return hasAttacked;
	}

	public void setHumanfed(boolean humanfed) {
		this.humanfed = humanfed;
	}
	
	public void setHasAttacked(boolean humanfed) {
		this.hasAttacked = humanfed;
	}
}
