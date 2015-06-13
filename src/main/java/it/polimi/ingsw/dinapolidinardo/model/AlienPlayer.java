package it.polimi.ingsw.dinapolidinardo.model;



public class AlienPlayer extends Player {
	
	private boolean humanfed = false;
	private boolean hasAttacked = false;
	private int moverange = 2;
	
	

	//constructor
	 	public AlienPlayer(Map galilei, GameState gs, String name){
			this.gamestate = gs;
			this.name = name;
			this.setPosition(galilei.getAlienStartBox());
			position.setPlayer(this);
			
		}
	
	
	
	
	@Override
	public boolean isHumanFed() {
		return this.humanfed;
	}	
	
	@Override
	public int getMoveRange() {
		if (isHumanFed())
			return this.moverange + 1;
		return this.moverange;
	}
	
	@Override
	public boolean isHasAttacked() {
		return hasAttacked;
	}

	public void setHumanfed(boolean humanfed) {
		this.humanfed = humanfed;
	}
	
	public void setHasAttacked(boolean humanfed) {
		this.hasAttacked = humanfed;
	}
}
