package it.polimi.ingsw.dinapolidinardo.model.cards;


/**
 * Particular Sector Card that allows user to declare noise in any sector
 * and let him draw an ItemCard
 */
public class NoiseAnywhereCardPlusItem extends NoiseAnywhereCard{
	
	@Override
	public boolean isWithItemType(){
		return true;
	}
}
