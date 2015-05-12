package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;

public class AttackCard {
	
	public boolean attack (Box position){
			
		if (position.isEmpty())
			return false;
		else 
			return true;
	}

}
