package it.polimi.ingsw.dinapolidinardo.model.cards;

import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;

/**
 * Abstract class that represent a single card 
 * of the Item Deck of the game
 */
public abstract class ItemCard extends Card{
	
	
	/**
	 * Activate the action linked to this card in the game specification
	 * @param player the player that will be affected
	 */
	public void doAction(HumanPlayer player){
		
	}
	
	
	//getters
	
	@Override
	public abstract String getName();
	
	public boolean isActivable(){
		return true;
	}
	
	public abstract String getUseMessage();
	
	
	
}
