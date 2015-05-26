package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;


public abstract class ItemCard extends Card{
	private String name = "ItemCard";
	private String useMessage = "";
	
	public String getName() {
		return name;
	}

	public String getUseMessage(){
		return useMessage;
	}
	
	public void doAction(HumanPlayer player){};
	
}
