package it.polimi.ingsw.dinapolidinardo.model.cards;


/**
 * Particular Sector Card that force user to declare noise in his sector
 */
public class NoiseHereCard extends SectorCard{

	@Override
	public String giveWalkOnMessage(){
		return " declares: NOISE in sector ";
	}
	
}
