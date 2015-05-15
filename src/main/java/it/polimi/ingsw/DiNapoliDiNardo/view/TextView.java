package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.*;
import java.io.PrintWriter;
import java.util.Scanner;


public class TextView extends View{
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	
	
	public Coordinates askMovement(int i){
		System.out.println("Player "+i+" where do you want to move? Insert the coordinates of the box you want to move in. Insert the letter, press enter, then the number of the box, then press enter again.");
		
		char letter = in.next().charAt(0);
		//parse the ASCII code of the char and convert it to a number, starting from 'A'-->1
		int numberX = (int)letter;
		if (numberX<88) numberX-=64;
		else numberX-=96;
		
		int numberY = in.nextInt();
		
		Coordinates coordinates = new Coordinates(numberX, numberY);
		return coordinates;
		
	}
	
	
	
	
}
