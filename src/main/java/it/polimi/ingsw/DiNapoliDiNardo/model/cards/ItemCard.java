package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;


public abstract class ItemCard extends Card{
		
	@Override
	public String getName(){
		return "ItemCard";
	}
	
	public abstract String getUseMessage();
		
	public void doAction(HumanPlayer player){}
	
}
