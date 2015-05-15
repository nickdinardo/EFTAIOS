package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.*;

public class HumanPlayer extends Player {

	protected boolean adrenalized = false;
	
	//constructor
	public HumanPlayer(GalileiMap Galilei, Main game){
		this.setPosition(Galilei.getMap()[7][11]);
		this.game = game;
	}
	
	//human movement method
	public void movement (Box destination, Box position){
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
		if (this.position instanceof DangerousBox){
			game.drawSectorCard(this);
		}
			
	}
	
	
	
	//public boolean attack(){
		
	//}
}
