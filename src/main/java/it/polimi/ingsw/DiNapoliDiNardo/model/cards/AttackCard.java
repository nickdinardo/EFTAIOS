package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;

public class AttackCard extends ItemCard{
	private String name = "AttackCard";
	private String useMessage = "-You charge, point and fire your weapon in the darkness. If someone (or something) is there he will suffer the consequences-";
			
	public String getName() {
		return name;
	}
	public String getUseMessage(){
		return useMessage;
	}
}
