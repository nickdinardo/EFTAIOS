package it.polimi.ingsw.dinapolidinardo.model.cards;


/**
 * Particular Sector Card that force user to declare noise in his sector
 * and let him draw an ItemCard
 */
public class NoiseHereCardPlusItem extends NoiseHereCard{

	@Override
	public boolean isWithItemType(){
		return true;
	}
}
