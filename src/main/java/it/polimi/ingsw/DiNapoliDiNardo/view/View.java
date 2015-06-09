package it.polimi.ingsw.DiNapoliDiNardo.view;





import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;



public abstract class View {

	
	public abstract String askName(boolean reask);
	
	public abstract Coordinates askMovement(boolean reask);
	
	public abstract String askForNoise();
	
	public abstract int askItemUse(String objects, boolean fromDiscardCall);
	
	public abstract int askHumanItemDiscard(String objects);
	
	public abstract int askAlienItemDiscard(String objects);
			
	public abstract String askForAttack();
	
	public abstract Coordinates askForLights(boolean reask);
	
	public abstract void print (String message);
	
	public abstract void showBeingAlien (String name);
	public abstract void showBeingHuman (String name);

	public abstract void showActualSituation (String name, String position, String objects, String turn);
	
	public abstract void notifyEscape (boolean escaped, String name, String shipnumber);
	
	public abstract void showFinalResults(boolean iWon, String name, String humanlosers, String humanwinners, String alienwinners, String alienlosers);

	public void update(String position, String reachables, String objects) {
		
	}
	
}
