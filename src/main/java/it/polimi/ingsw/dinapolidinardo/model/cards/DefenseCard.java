package it.polimi.ingsw.dinapolidinardo.model.cards;


/**
 * Representation of the Defense Item Card
 */
public class DefenseCard extends ItemCard{
	
	@Override
	public String getName() {
		return "DefenseCard";
	}
	@Override
	public String getUseMessage(){
		return "You can't use a Defense Card, it will activate by itself when you'll be attacked";
	}
	
	@Override
	public boolean isActivable(){
		return false;
	}
	
}
