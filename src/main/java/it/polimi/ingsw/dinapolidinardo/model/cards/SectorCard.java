package it.polimi.ingsw.dinapolidinardo.model.cards;


/**
 * Abstract class that represent a single card 
 * of the Sector Deck of the game
 */
public abstract class SectorCard extends Card{
	
	public abstract String giveWalkOnMessage();
	
	/**
	 * @return true if you have to draw a card from ItemCard walking on this sector
	 */
	public boolean isWithItemType(){
		return false;
	}
	
	/**
	 * @return true if sector behavior requires user to input something via view 
	 */
	public boolean requiresViewCall(){
		return false;
	}
	
	/**
	 * @return true if sector reveals the actual and real position of who walks in
	 */
	public boolean revealsPosition(){
		return true;
	}
}
