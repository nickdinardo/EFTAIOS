package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;

public class AdrenalineCard extends ItemCard{
	
	@Override
	public String getName() {
		return "AdrenalineCard";
	}
	@Override
	public String getUseMessage(){
		return "-Injecting yourself adrenaline you feel your body answer more quickly. You're faster in movements this turn-";
	}
	@Override
	public void doAction(HumanPlayer player){
		player.setAdrenalized(true);
	}
}
