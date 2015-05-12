package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

public class AttackCard {
	
	public boolean attack (Box position){
			
		if (position.isEmpty())
			return false;
		else 
			return true;
	}

}
