package it.polimi.ingsw.dinapolidinardo.model.cards;


/**
 * Abstract class that represent a single card 
 * of the LifeBoat Deck of the game
 */
public abstract class LifeboatCard extends Card {
	
	/**
	 * @return true if the associated LifeBoat ship is working (drawn a green LifeBoat Card)
	 * false otherwise (drawn red LifeBoat Card)
	 */
	public boolean isWorking() {
		return false;
	}

}
