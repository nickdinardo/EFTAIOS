package it.polimi.ingsw.dinapolidinardo.model.cards;

import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;

/**
 * Representation of the Teleport Item Card
 */
public class TeleportCard extends ItemCard{
		
	@Override
	public String getName() {
		return "TeleportCard";
	}
	@Override
	public String getUseMessage(){
		return "-BZZZ...You successfully teleported back to L08, your starting position-";
	}
	@Override
	public void doAction(HumanPlayer player){
		player.teleport();
	}
}
