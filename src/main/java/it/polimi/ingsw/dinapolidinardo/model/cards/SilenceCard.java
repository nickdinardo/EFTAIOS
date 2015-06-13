package it.polimi.ingsw.dinapolidinardo.model.cards;

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
