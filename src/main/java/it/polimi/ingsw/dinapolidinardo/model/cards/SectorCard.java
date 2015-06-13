package it.polimi.ingsw.dinapolidinardo.model.cards;

public abstract class SectorCard extends Card{
	
	public abstract String giveWalkOnMessage();
	
	public boolean isWithItemType(){
		return false;
	}
	
	public boolean requiresViewCall(){
		return false;
	}
	
	public boolean revealsPosition(){
		return true;
	}
}