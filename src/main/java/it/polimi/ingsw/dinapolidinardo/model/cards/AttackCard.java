package it.polimi.ingsw.dinapolidinardo.model.cards;


/**
 * Representation of the Attack Item Card
 */
public class AttackCard extends ItemCard{
				
	@Override
	public String getName() {
		return "AttackCard";
	}
	@Override
	public String getUseMessage(){
		return "-You charge, point and fire your weapon in the darkness. If someone (or something) is there he will suffer the consequences-";
	}
}
