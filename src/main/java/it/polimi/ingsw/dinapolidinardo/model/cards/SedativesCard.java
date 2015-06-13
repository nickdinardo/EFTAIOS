package it.polimi.ingsw.dinapolidinardo.model.cards;

import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;


public class SedativesCard extends ItemCard {
		
	@Override
	public String getName() {
		return "SedativesCard";
	}
	@Override
	public String getUseMessage(){
		return "-Injecting yourself the sedatives you calm down and control your body. You'll not make noise around this turn-";
	}
	@Override
	public void doAction(HumanPlayer player){
		player.setSedated(true);
	}

}
