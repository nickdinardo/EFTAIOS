package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

public class DefenseCard extends ItemCard{
	private String name = "DefenseCard";
	private String useMessage = "You can't use a Defense Card, it will activate by itself when you'll be attacked";
	
	public String getName() {
		return name;
	}

}
