package it.polimi.ingsw.dinapolidinardo.model;


/**
 * Class that represent an alien player in the game, providing all the information 
 * needed by Game State and Game Controller
 */
public class AlienPlayer extends Player {
	
	private boolean humanfed = false;
	private boolean hasAttacked = false;
	private int moverange = 2;
	
	

	/**
	 * Constructor, set the player in the appropriate game state
	 * 
	 * @param galilei the map where the game is played
	 * @param gs the game state that manages this player's game
	 * @param name the name of the player
	 */
 	public AlienPlayer(Map galilei, GameState gs, String name){
		this.gamestate = gs;
		this.name = name;
		this.setPosition(galilei.getAlienStartBox());
		position.setPlayer(this);
		
	}


	//getters and setters of the alien properties
	
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
