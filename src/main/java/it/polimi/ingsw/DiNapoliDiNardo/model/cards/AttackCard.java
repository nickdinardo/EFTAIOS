package it.polimi.ingsw.DiNapoliDiNardo.model.cards;



public class AttackCard extends ItemCard{
	private String name = "AttackCard";
	private String useMessage = "-You charge, point and fire your weapon in the darkness. If someone (or something) is there he will suffer the consequences-";
		
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getUseMessage(){
		return useMessage;
	}
}
