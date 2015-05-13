package it.polimi.ingsw.DiNapoliDiNardo;
import it.polimi.ingsw.DiNapoliDiNardo.model.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.*;

public class Main {
		
	public static void main( String[] args){
		GalileiMap Galilei = new GalileiMap();
		Galilei.buildmap();
		System.out.println(Galilei.toString());
		

		
	}
	
}
