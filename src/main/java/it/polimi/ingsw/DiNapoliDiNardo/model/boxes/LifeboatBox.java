package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

public class LifeboatBox extends Box {
	private boolean LifeBoatShipHere = true;
	private int number;
	
	
	public LifeboatBox(Coordinates coordinates, int number) {
		super(coordinates);
		this.number = number;
		
	}
	
	public LifeboatBox(int x, int y, int number){
		super(x, y);
		this.number = number;
	}
	
	@Override
	public boolean isLifeBoatShipHere() {
		return this.LifeBoatShipHere;
	}
	@Override
	public void setLifeBoatShipHere(boolean lifeboat) {
		this.LifeBoatShipHere = lifeboat;
	}
	
	public int getNumber() {
		return number;
	}
}
