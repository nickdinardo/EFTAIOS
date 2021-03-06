package it.polimi.ingsw.dinapolidinardo.model;



/**
 * Class that represent a human player in the game, providing all the information 
 * needed by Game State and Game Controller
 */
public class HumanPlayer extends Player{

	private boolean adrenalized = false;
	private boolean sedated = false;
	private boolean escaped = false;
	protected boolean losesIfKilledType = true;
	private int moverange = 1;
	
	
	/**
	 * Constructor, set the player in the appropriate game state
	 * 
	 * @param galilei the map where the game is played
	 * @param gs the game state that manages this player's game
	 * @param name the name of the player
	 */
 	public HumanPlayer(Map galilei, GameState gs, String name){
		this.gamestate = gs;
		this.name = name;
		this.setPosition(galilei.getHumanStartBox());
		position.setPlayer(this);
		
	}
	
	
	/**
	 * Removes this player from his actual position of the map and put him 
	 * in the Human Start Position, depending on the map specification
	 */
	@Override
	public void teleport(){
		this.position.unsetPlayer(this);
		this.setPosition(gamestate.getMap().getHumanStartBox());
		this.position.setPlayer(this);
		
	}
	
	
	//getters and setters of the human properties
	
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
		if (isAdrenalized())
			return this.moverange + 1 ;
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
