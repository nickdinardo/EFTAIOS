package it.polimi.ingsw.DiNapoliDiNardo.model.boxes;

public class LifeboatBox extends Box {
	private boolean working;
	
	public LifeboatBox(int x, int y) {
		super(x,y);
		this.setWorking(false); 
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}
}
