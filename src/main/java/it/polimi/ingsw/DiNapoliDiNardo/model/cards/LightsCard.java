package it.polimi.ingsw.DiNapoliDiNardo.model.cards;

public class LightsCard extends ItemCard{
	private String name = "LightsCard";
	private String useMessage = "-Pointing your lights into darkness, you reveal all the alien and human being hiding into shadows-";
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getUseMessage(){
		return useMessage;
	}

}
