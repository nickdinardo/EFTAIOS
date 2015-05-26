package it.polimi.ingsw.DiNapoliDiNardo.model;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;



public class AlienPlayer extends Player {
	
	protected boolean humanfed = false;
	protected boolean hasAttacked = false;
	
	

	//constructor
	 	public AlienPlayer(GalileiMap Galilei, GameState gs, String name){
			this.gamestate = gs;
			this.name = name;
			this.setPosition(Galilei.getAlienStartBox());
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
	
	//alien movement method
	public boolean movement (Box destination, Box position){
		
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
	
	//alien attack method, with setHumanfed
	@Override
	public List<Player> attack(Box position){
		List<Player> playerInBox = new ArrayList<Player>();
		if(!position.isEmpty()){
			playerInBox = position.getPlayerHere();
			for(Player player : playerInBox ){
				if (this.humanfed == false && player instanceof HumanPlayer)
					this.setHumanfed(true);
			}
		}
		
		playerInBox.remove(this);
		return playerInBox;
		
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
