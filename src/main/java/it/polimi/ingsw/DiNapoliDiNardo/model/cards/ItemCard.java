package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;


public abstract class ItemCard extends Card{
	private String name = "ItemCard";
	private String useMessage = "";
	
	@Override
	public String getName() {
		return this.name;
	}

	public String getUseMessage(){
		return this.useMessage;
	}
	
	public void doAction(HumanPlayer player){}
	
}
