package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;

import java.io.PrintWriter;
import java.util.ArrayList;
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
	
	public boolean askItemUse(int i){
		System.out.println("Player " + i + " do you want to use an Item Card?");
		System.out.println("Y: yes    N: no");
		char risp = in.next().charAt(0);
		if (risp == 'Y' || risp == 'y')
			return true;
		else
			return false;
	}
	
	
	public boolean askForAttack(){
		System.out.println("Filthy alien do you want to attack this position?");
		System.out.println("Y: yes    N: no");
		char risp = in.next().charAt(0);
		if (risp == 'Y' || risp == 'y')
			return true;
		else
			return false;
	}
	
	
	
	
	public Card whichItem(ArrayList<Card> deck){
		System.out.println("Tell me the number of the Item you want to use");
		int index = in.nextInt();
		Card currentItem = deck.remove(index);
		return currentItem;
	}
	
	public void killPlayer(Player player){
		System.out.println("Player " + player +  " you are died");
	}
	
	public void attackNotSuccesful(){
		System.out.println("Attack has not been successful!");
	}
	
}
