package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

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
