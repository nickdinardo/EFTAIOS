package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;


public class SedativesCard extends ItemCard {
	private String name = "SedativesCard";
	private String useMessage = "-Injecting yourself the sedatives you calm down and control your body. You'll not make noise around this turn-";
	
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
		player.setSedated(true);
	}

}
