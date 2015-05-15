package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.*;
import java.io.PrintWriter;
import java.util.Scanner;


public class View {
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	
	
	public Coordinates askMovement(int i){
		out.println("Player "+i+" where do you want to move? Insert the coordinates of the box you want to move in.");
		char letter = in.next().charAt(0);
		int numberX = (int)letter;
		if (numberX<88) numberX-=64;
		else numberX-=96;
		int numberY = in.nextInt();
		
		Coordinates coordinates = new Coordinates(numberX, numberY);
		return coordinates;
		
	}
	
	
	
	
}
