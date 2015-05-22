package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.*;

public class HumanPlayer extends Player {

	protected boolean adrenalized = false;
	protected boolean sedated = false;
	
	
	//multiplayer constructor
 	public HumanPlayer(GalileiMap Galilei, GameState gs, String name){
		this.gamestate = gs;
		this.name = name;
		this.setPosition(Galilei.getHumanStartBox());
		position.setPlayer(this);
		
	}
	
	//human movement method
	public boolean movement (Box destination, Box position){
		
		
		if (adrenalized){
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
		else {
			if(isValidSingleMovement(destination, position)){
				this.position.unsetPlayer(this);
				this.position=destination;
				this.position.setPlayer(this);
				return true;	
			}
			else{
				return false;
			}
		}
		
		
		
	}
		
	
	public void teleport(){
		this.position.unsetPlayer(this);
		this.setPosition(gamestate.getGalilei().getHumanStartBox());
		this.position.setPlayer(this);
		//farlo fare alla view:
		System.out.println("You're back in the starting position");
	}
	
	
	//getters and setters
	public boolean isAdrenalized() {
		return adrenalized;
	}

	public void setAdrenalized(boolean adrenalized) {
		this.adrenalized = adrenalized;
	}

	public boolean isSedated() {
		return sedated;
	}

	public void setSedated(boolean sedated) {
		this.sedated = sedated;
	}
	
	
	
}
