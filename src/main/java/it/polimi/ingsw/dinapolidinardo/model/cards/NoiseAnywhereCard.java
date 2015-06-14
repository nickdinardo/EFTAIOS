package it.polimi.ingsw.dinapolidinardo.model.cards;

/**
 * Particular Sector Card that allows user to declare noise in any sector
 */
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
