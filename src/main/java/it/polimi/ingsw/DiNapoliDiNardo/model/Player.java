package it.polimi.ingsw.DiNapoliDiNardo.model;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.ItemCard;

import java.util.ArrayList;

public abstract class Player {
	
	private Box position;
	private ArrayList< ItemCard > personalDeck = new ArrayList< ItemCard >();
	private GalileiMap map;
	//public boolean isValidMovement(Box toPosition){
	//}
	
	public Box getPosition() {
		return position;
	}
	public void setPosition(Box position) {
		this.position = position;
	}
		
	
		

}
