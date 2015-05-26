package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;

import it.polimi.ingsw.DiNapoliDiNardo.model.Coordinates;

public class LifeboatBox extends Box {
	private boolean working;
	
	public LifeboatBox(Coordinates coordinates) {
		super(coordinates);
		this.setWorking(false); 
	}
	
	public LifeboatBox(int x, int y){
		super(x, y);
		this.setWorking(false);
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}
}
