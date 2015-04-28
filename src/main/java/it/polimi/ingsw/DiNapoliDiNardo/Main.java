package it.polimi.ingsw.DiNapoliDiNardo;
import it.polimi.ingsw.DiNapoliDiNardo.model.*;

public class Main {
		
	public static void main( String[] args){
		GalileiMap G = new GalileiMap();
		G.buildmap();
		System.out.println(G.toString());
	}
	
}
