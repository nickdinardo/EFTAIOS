package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;


public class HumanPlayer extends Player{

	private boolean adrenalized = false;
	private boolean sedated = false;
	private boolean escaped = false;
	protected boolean losesIfKilledType = true;
	
	
	//constructor
 	public HumanPlayer(GalileiMap galilei, GameState gs, String name){
		this.gamestate = gs;
		this.name = name;
		this.setPosition(galilei.getHumanStartBox());
		position.setPlayer(this);
		
	}
	
	@Override
	public boolean movement (Box destination, Box position){
		
		
		if (adrenalized){
			if(isValidDoubleMovement(destination, position)){
				this.position.unsetPlayer(this);
				this.position = destination;
				this.position.setPlayer(this);
				return true;
			}
			else{
				return false;
			}
		}
		else {
			if(isValidSingleMovement(destination, position)){
				this.position.unsetPlayer(this);
				this.position=destination;
				this.position.setPlayer(this);
				return true;	
			}
			else{
				return false;
			}
		}
	}
		
	@Override
	public void teleport(){
		this.position.unsetPlayer(this);
		this.setPosition(gamestate.getGalilei().getHumanStartBox());
		this.position.setPlayer(this);
		
	}
	
	
	//getters and setters
	public boolean isAdrenalized() {
		return adrenalized;
	}

	public void setAdrenalized(boolean adrenalized) {
		this.adrenalized = adrenalized;
	}

	public boolean isSedated() {
		return sedated;
	}

	public void setSedated(boolean sedated) {
		this.sedated = sedated;
	}
	@Override
	public boolean isEscaped() {
		return this.escaped;
	}

	public void setEscaped(boolean escaped) {
		this.escaped = escaped;
	}
	
	@Override
	public boolean isLosesIfKilledType() {
		return losesIfKilledType;
	}
	
}
