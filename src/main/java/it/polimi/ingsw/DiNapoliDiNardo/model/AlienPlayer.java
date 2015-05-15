package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.Main;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.AlienBox;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.HumanBox;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Wall;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;

public class AlienPlayer extends Player{
	
	protected boolean humanfed = true;
	
	
	public AlienPlayer(GalileiMap Galilei, Main game){
		this.setPosition(Galilei.getMap()[5][11]);
		this.game = game;
	}
	
	
	public boolean isValidTripleMovement(Box destination){
		if (destination instanceof Wall) return false;
		if (destination instanceof HumanBox) return false;
		if (destination instanceof AlienBox) return false;
		if (position.getCoordX()%2==0){//BASSA
			//check the reachable boxes down or on the same level of actual position
			if (destination.getCoordY()-position.getCoordY() >-2 && destination.getCoordY()-position.getCoordY() < 3)
				if (destination.getCoordX()-position.getCoordX() >-4 && destination.getCoordX()-position.getCoordX() <4)
					return true;
			//check the upper reachable boxes
			if (destination.getCoordY()-position.getCoordY() == -2)
				if (destination.getCoordX()-position.getCoordX() >-3 && destination.getCoordX()-position.getCoordX() <3)
					return true;
			if (destination.getCoordY()-position.getCoordY() == -3)
				if (destination.getCoordX()-position.getCoordX() == 0)
					return true;
			if (destination.getCoordY()-position.getCoordY() == 3)
				if (destination.getCoordX()-position.getCoordX() >-2 && destination.getCoordX()-position.getCoordX() <2)
					return true;
		}
		else{//ALTA
			//check the reachable boxes down or on the same level of actual position
			if (destination.getCoordY()-position.getCoordY() >-3 && destination.getCoordY()-position.getCoordY() < 2)
				if (destination.getCoordX()-position.getCoordX() >-4 && destination.getCoordX()-position.getCoordX() <4)
					return true;
			//check the upper reachable boxes
			if (destination.getCoordY()-position.getCoordY() == -3)
				if (destination.getCoordX()-position.getCoordX() >-2 && destination.getCoordX()-position.getCoordX() <2)
					return true;
			if (destination.getCoordY()-position.getCoordY() == 2)
				if (destination.getCoordX()-position.getCoordX() >-3 && destination.getCoordX()-position.getCoordX() <3)
					return true;
			if (destination.getCoordY()-position.getCoordY() == 3)
				if (destination.getCoordX()-position.getCoordX() == 0)
					return true;
		}
		return false;
	};
	
	public void movement (Box destination){
		if (humanfed){
			if(isValidTripleMovement(destination))
				this.position = destination;
			else{
				System.out.println("Not a valid movement, you'll stand still");
			}
		}
		else {
			if(isValidDoubleMovement(destination))
				this.position=destination;
			else{
				System.out.println("Not a valid movement, you'll stand still");
			}
		}
	}
	
	
}
