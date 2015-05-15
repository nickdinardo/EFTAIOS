package it.polimi.ingsw.DiNapoliDiNardo.model;
import java.util.ArrayList;

import it.polimi.ingsw.DiNapoliDiNardo.Main;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;


public abstract class Player {
	
	protected Main game;
	protected Box position;
	protected boolean isAlive=true;
	protected ArrayList< Card > personalDeck = new ArrayList< Card >();
	
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
	
	public boolean isValidDoubleMovement(Box destination){
		if (destination instanceof Wall) return false;
		if (destination instanceof HumanBox) return false;
		if (destination instanceof AlienBox) return false;
		//check the reachable boxes down or on the same level of actual position
		if (destination.getCoordY()-position.getCoordY() == 0 || destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == 2)
			if (destination.getCoordX()-position.getCoordX() > -3 && destination.getCoordX()-position.getCoordX() <3)
				return true;
		//check the upper reachable boxes
		if (destination.getCoordY()-position.getCoordY() == -1)
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 0 || destination.getCoordX()-position.getCoordX() == 1)
				return true;
		if (destination.getCoordY()-position.getCoordY() == -2)
			if (destination.getCoordX()-position.getCoordX() == 0)
				return true;
		return false;
	};
	
	public abstract void movement(Box destination);
	
	public void drawItemCard(){
		Card itemcard = game.getItemdeck().drawCard();
		//codice che chiama la view per chiedere se si vuole tenere la carta pescata 
		if (personalDeck.size()<3)
			personalDeck.add(itemcard);
	}
	
}
		
	


