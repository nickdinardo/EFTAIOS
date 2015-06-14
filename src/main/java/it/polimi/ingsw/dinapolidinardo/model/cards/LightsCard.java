package it.polimi.ingsw.dinapolidinardo.model.cards;


/**
 * Representation of the Lights Item Card
 */
public class LightsCard extends ItemCard{
	
	@Override
	public String getName() {
		return "LightsCard";
	}
	@Override
	public String getUseMessage(){
		return "-Pointing your lights into darkness, you reveal all the alien and human being hiding into shadows-";
	}

}
