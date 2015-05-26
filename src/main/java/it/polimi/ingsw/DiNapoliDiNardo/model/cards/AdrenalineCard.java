package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;

public class AdrenalineCard extends ItemCard{
	private String name = "AdrenalineCard";
	private String useMessage = "-Injecting yourself adrenaline you feel your body answer more quickly. You're faster in movements this turn-";
	
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
		player.setAdrenalized(true);
	}
}
