package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;

public class TeleportCard extends ItemCard{
	private String name = "TeleportCard";
	private String useMessage = "-BZZZ...You successfully teleported back to L08, your starting position-";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getUseMessage(){
		return useMessage;
	}
	@Override
	public void doAction(HumanPlayer player){
		player.teleport();
	}
}
