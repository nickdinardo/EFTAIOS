package it.polimi.ingsw.DiNapoliDiNardo.model;
import java.util.ArrayList;

import it.polimi.ingsw.DiNapoliDiNardo.Main;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;


public abstract class Player {
	
	protected Main game;
	protected Box position;
	protected final Box humanStartBox = new Box(12, 8);
	protected final Box alienStartBox = new Box(12, 6);
	protected boolean isAlive=true;
	protected ArrayList< Card > personalDeck = new ArrayList< Card >();
	protected boolean teleportized = false;
	
	//getters and setters
	public boolean isAlive() {
		return isAlive;
	}
	public ArrayList<Card> getPersonalDeck() {
		return personalDeck;
	}
	public Box getPosition() {
		return position;
	}
	public void setPosition(Box position) {
		this.position = position;
	}
	
	
	
	public abstract void movement(Box destination, Box position);
	public abstract void teleport();
	public abstract ArrayList<Player> attack(Box position);
	
	
	public boolean isValidSingleMovement(Box destination, Box position){
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
			
	
	public boolean isValidDoubleMovement(Box destination, Box position){
		
		ArrayList<Box> aroundBoxes = game.getGalilei().givemeAroundBoxes(position);
		ArrayList<Box> oneStepBoxes = checkBoxes(aroundBoxes, position);
		for (Box box: oneStepBoxes){
			aroundBoxes = game.getGalilei().givemeAroundBoxes(box);
			ArrayList<Box> twoStepBoxes = checkBoxes(aroundBoxes, box);
			if (twoStepBoxes.contains(destination))
				return true;
		}
		return false;
	};
	
	
	//check a group of boxes and return only the reachable from actual position with a single step
	public ArrayList<Box> checkBoxes (ArrayList<Box> boxesToCheck, Box position){
		ArrayList<Box> validBoxes = new ArrayList<Box>();
		for (Box box: boxesToCheck){
			if (isValidSingleMovement(box, position))
					validBoxes.add(box);
			}
		return validBoxes;
	}
		
	
	public void drawItemCard(){
		Card itemcard = game.getItemdeck().drawCard();
		//codice che chiama la view per chiedere se si vuole tenere la carta pescata 
		if (personalDeck.size()<3){
			personalDeck.add(itemcard);
			if (itemcard instanceof TeleportCard)
				this.teleportized = true;
		}	
	}
	
	public void kill(){
		this.isAlive = false;
	}
}
		
	


