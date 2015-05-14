package it.polimi.ingsw.DiNapoliDiNardo.model;

import java.util.ArrayList;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.ItemCard;

public class HumanPlayer extends Player {

	protected ArrayList< ItemCard > personalDeck = new ArrayList< ItemCard >();
	
	public HumanPlayer(GalileiMap Galilei){
		this.setPosition(Galilei.getMap()[7][11]);
	}
	
	public boolean isValidSingleMovement(Box destination){
		if (destination instanceof Wall) return false;
		if (destination instanceof HumanBox) return false;
		if (destination instanceof AlienBox) return false;
		if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 1){
			if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == 0){
				return true;}}
		if (destination.getCoordX()-position.getCoordX() == 0){
			if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == -1){
				return true;}}
		return false;
	};
	
	
	public void singleMovement (Box destination){
		if(isValidSingleMovement(destination))
			this.position=destination;
		//else
	}
	
	
	//public boolean attack(){
		
	//}
}
