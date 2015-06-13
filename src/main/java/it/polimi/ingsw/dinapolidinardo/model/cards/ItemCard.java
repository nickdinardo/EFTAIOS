package it.polimi.ingsw.dinapolidinardo.model.cards;

import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;


public abstract class ItemCard extends Card{
		
	@Override
	public String getName(){
		return "ItemCard";
	}
	
	public boolean isActivable(){
		return true;
	}
	
	public abstract String getUseMessage();
		
	public void doAction(HumanPlayer player){
		
	}
	
}
