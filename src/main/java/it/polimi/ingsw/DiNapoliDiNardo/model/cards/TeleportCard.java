package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;

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
