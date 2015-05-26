package it.polimi.ingsw.DiNapoliDiNardo.view;





import it.polimi.ingsw.DiNapoliDiNardo.model.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;


public abstract class View {

	
	public abstract String askName();
	
	public abstract Coordinates askMovement(boolean reask);
	
	public abstract String askForNoise();
	
	public abstract int askItemUse(String objects);
	
	public abstract void killPlayer(Player player);
	
	public abstract void attackNotSuccesful();
	
	public abstract String askForAttack();
	
	public abstract Coordinates askForLights();
	
	public abstract void revealingLights(Box box);
	
	public abstract void showBeingAlien (String name);
	public abstract void showBeingHuman (String name);

	}
