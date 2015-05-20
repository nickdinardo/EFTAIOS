package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.util.ArrayList;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;

public abstract class View {

	
	public abstract String askName();
	
	public abstract Coordinates askMovement(int i);
	
	public abstract boolean askItemUse(int i);
	
	public abstract Card whichItem(ArrayList<Card> deck);
	
	public abstract void killPlayer(Player player);
	
	public abstract void attackNotSuccesful();
	
	public abstract boolean askForAttack();
	
	public abstract Coordinates askForLights();
	
	public abstract void revealingLights(Box box);

	}
