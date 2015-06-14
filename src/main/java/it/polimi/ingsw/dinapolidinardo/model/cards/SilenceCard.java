package it.polimi.ingsw.dinapolidinardo.model.cards;


/**
 * Particular Sector Card that let user declare nothing more than "silence"
 */
public class SilenceCard extends SectorCard{

	@Override
	public String giveWalkOnMessage(){
		return " declares: SILENCE.";
	}
	@Override
	public boolean revealsPosition(){
		return false;
	}
}
