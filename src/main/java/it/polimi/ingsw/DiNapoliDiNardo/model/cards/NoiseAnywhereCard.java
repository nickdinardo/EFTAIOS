package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

public class NoiseAnywhereCard extends SectorCard {

	@Override
	public String giveWalkOnMessage(){
		return " declares: NOISE in sector ";
	}
	
	@Override
	public boolean requiresViewCall(){
		return true;
	}
	
}
