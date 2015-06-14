package it.polimi.ingsw.dinapolidinardo.model.cards;

import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;


public abstract class ItemCard extends Card{
		
	@Override
	public abstract String getName();
	
	public boolean isActivable(){
		return true;
	}
	
	public abstract String getUseMessage();
		
	public void doAction(HumanPlayer player){
		
	}
	
}
