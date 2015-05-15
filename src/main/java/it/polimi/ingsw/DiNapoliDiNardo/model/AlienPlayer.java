package it.polimi.ingsw.DiNapoliDiNardo.model;

import java.util.ArrayList;

import it.polimi.ingsw.DiNapoliDiNardo.Main;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.DangerousBox;


public class AlienPlayer extends Player{
	
	protected boolean humanfed = true;
	
	//constructor
	public AlienPlayer(GalileiMap Galilei, Main game){
		this.setPosition(Galilei.getMap()[5][11]);
		this.game = game;
	}
	
	//triple movement check
	public boolean isValidTripleMovement(Box destination, Box position){
		ArrayList<Box> aroundBoxes = game.getGalilei().givemeAroundBoxes(position);
		ArrayList<Box> oneStepBoxes = checkBoxes(aroundBoxes, position);
		for (Box box: oneStepBoxes){
			if (isValidDoubleMovement(destination, box))
				return true;
		}
		return false;
	};
	
	//alien movement method
	public void movement (Box destination, Box position){
		if (humanfed){
			if(isValidTripleMovement(destination, position))
				this.position = destination;
			else{
				System.out.println("Not a valid movement, you'll stand still");
			}
		}
		else {
			if(isValidDoubleMovement(destination, position))
				this.position=destination;
			else{
				System.out.println("Not a valid movement, you'll stand still");
			}
		}
		if (this.position instanceof DangerousBox){
			game.drawSectorCard(this);
		}
	}
	
	
}
