package it.polimi.ingsw.DiNapoliDiNardo.model;




public class HumanPlayer extends Player{

	private boolean adrenalized = false;
	private boolean sedated = false;
	private boolean escaped = false;
	protected boolean losesIfKilledType = true;
	private int moverange = 1;
	
	
	//constructor
 	public HumanPlayer(Map galilei, GameState gs, String name){
		this.gamestate = gs;
		this.name = name;
		this.setPosition(galilei.getHumanStartBox());
		position.setPlayer(this);
		
	}
	
	
	
	@Override
	public void teleport(){
		this.position.unsetPlayer(this);
		this.setPosition(gamestate.getMap().getHumanStartBox());
		this.position.setPlayer(this);
		
	}
	
	
	//getters and setters
	
	@Override
	public boolean isAdrenalized() {
		return adrenalized;
	}
	
	@Override
	public boolean isEscaped() {
		return this.escaped;
	}
	
	@Override
	public boolean isSedated() {
		return this.sedated;
	}
	
	@Override
	public boolean isLosesIfKilledType() {
		return losesIfKilledType;
	}
	
	@Override
	public int getMoveRange() {
		return this.moverange;
	}
	
	public void setAdrenalized(boolean adrenalized) {
		this.adrenalized = adrenalized;
	}

	public void setSedated(boolean sedated) {
		this.sedated = sedated;
	}
	
	@Override
	public void setEscaped(boolean escaped) {
		this.escaped = escaped;
	}
	
	
	
}
