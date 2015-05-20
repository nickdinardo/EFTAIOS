package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.*;

public class HumanPlayer extends Player {

	protected boolean adrenalized = false;
	protected boolean sedated = false;
	
	
	//constructor
	public HumanPlayer(GalileiMap Galilei, Main game, String name){
		this.game = game;
		this.name = name;
		this.setPosition(Galilei.getHumanStartBox());
		position.setPlayer(this);
		
	}
	
	//human movement method
	public void movement (Box destination, Box position){
		this.position.unsetPlayer(this);
		
		if (adrenalized){
			if(isValidDoubleMovement(destination, position)){
				this.position = destination;
			}
			else{
				System.out.println("Not a valid movement, you'll stand still");
			}
		}
		else {
			if(isValidSingleMovement(destination, position))
				this.position=destination;
			else{
				System.out.println("Not a valid movement, you'll stand still");
			}
		}
		this.position.setPlayer(this);
		
		//sector card drawing
		if (this.position instanceof DangerousBox && !this.isSedated()){
			game.drawSectorCard(this);
		}
			
	}
		
	
	public void teleport(){
		this.position.unsetPlayer(this);
		this.setPosition(game.getGalilei().getHumanStartBox());
		this.position.setPlayer(this);
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
