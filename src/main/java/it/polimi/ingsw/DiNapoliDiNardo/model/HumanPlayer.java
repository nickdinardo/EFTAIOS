package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.*;
import it.polimi.ingsw.DiNapoliDiNardo.*;

public class HumanPlayer extends Player {

	protected boolean adrenalized = false;
	
	
	public HumanPlayer(GalileiMap Galilei, Main game){
		this.setPosition(Galilei.getMap()[7][11]);
		this.game = game;
	}
	
	
	public boolean isValidSingleMovement(Box destination){
		if (destination instanceof Wall) return false;
		if (destination instanceof HumanBox) return false;
		if (destination instanceof AlienBox) return false;
		if (position.getCoordX()%2==0){//BASSA
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 1)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == 0)
					return true;
			if (destination.getCoordX()-position.getCoordX() == 0)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == -1)
					return true;
		}
		else{//ALTA
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 1)
				if (destination.getCoordY()-position.getCoordY() == -1 || destination.getCoordY()-position.getCoordY() == 0)
					return true;
			if (destination.getCoordX()-position.getCoordX() == 0)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == -1)
					return true;
		}
		return false;
	};
	
	
	public void movement (Box destination){
		if (adrenalized){
			if(isValidDoubleMovement(destination)){
				this.position = destination;
				this.adrenalized = false;
			}
			else{
				System.out.println("Not a valid movement, you'll stand still");
			}
		}
		else {
			if(isValidSingleMovement(destination))
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
