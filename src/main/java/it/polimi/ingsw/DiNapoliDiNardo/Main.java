package it.polimi.ingsw.DiNapoliDiNardo;
import it.polimi.ingsw.DiNapoliDiNardo.model.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.*;

public class Main {
		
	public static void main( String[] args){
		GalileiMap Galilei = new GalileiMap();
		Galilei.buildmap();
		System.out.println(Galilei.toString());
		
		HumanPlayer marco= new HumanPlayer(Galilei);
		
		marco.setPosition(Galilei.getMap()[1][0]);
		
		marco.singleMovement(Galilei.getMap()[0][0]);
		
		System.out.println(marco.getPosition().getCoordX()+","+marco.getPosition().getCoordY());
	}
	
}
