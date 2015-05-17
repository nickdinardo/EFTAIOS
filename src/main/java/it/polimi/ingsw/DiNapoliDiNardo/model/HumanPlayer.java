package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.*;

public class HumanPlayer extends Player {

	protected boolean adrenalized = false;
	protected boolean sedated = false;
	protected boolean canAttack = false;
	
	//constructor
	public HumanPlayer(GalileiMap Galilei, Main game){
		this.game = game;
		this.setPosition(Galilei.getHumanStartBox());
		position.setPlayer(this);
		
	}
	
	//human movement method
	public void movement (Box destination, Box position){
		position.unsetPlayer(this);
		
		if (adrenalized){
			if(isValidDoubleMovement(destination, position)){
				this.position = destination;
				this.adrenalized = false;
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
		position.setPlayer(this);
		if (this.position instanceof DangerousBox && this.sedated == false){
			game.drawSectorCard(this);
		}
			
	}
	
	
	public void teleport(){
		position.unsetPlayer(this);
		this.setPosition(game.getGalilei().getHumanStartBox());
		position.setPlayer(this);
		System.out.println("You're back in the starting position");
	}
	
	
	
	
	
}
