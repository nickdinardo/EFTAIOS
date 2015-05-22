package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class TextView extends View{
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	
	
	public String askName (){
		System.out.println("Connected to the server game. Which is your name?");
		return in.nextLine();
	}
	public void showBeingAlien (String name){
		System.out.println(name+", the horrible alien disease that is infecting the spaceships has caught you time ago. ");
		System.out.println("Your body is now a brutal and deformed machine eager to kill any human that is still alive carrying fresh meat.");
		System.out.println("Your objective is tracking down the poor humans that are trying to reach the lifeboat ships and kill'em before they do it.");
		System.out.println("All the miserable humans. Enjoy your meal.");
	}
		
	public void showBeingHuman (String name){
		System.out.println(name+", you are one of the survivors on the spaceship that resisted to the spreading, horrible disease that could have infected anyone of your team mates.");
		System.out.println("Horrendous aliens that once were your friends are lurking in the dark to kill you and eat you, and they could be anyone and anywhere. ");
		System.out.println("Your objective is reaching one of the avaiable lifeboat ships avoiding to attract the attentions of the blood-thirsty monsters that surround you.");
		System.out.println("The mission depends on you. Your life too. Good luck.");
	}
	
	public Coordinates askMovement(boolean reask){
		if(reask){
			System.out.println("The movement you selected is not valid. Please select another box.");
		}
		System.out.println("Where do you want to move? Insert the coordinates of the box you want to move in. Insert the letter, press enter, then the number of the box, then press enter again.");
		
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
		System.out.println("Filthy alien, do you want to attack this position?");
		System.out.println("Y: yes    N: no");
		char risp = in.next().charAt(0);
		if (risp == 'Y' || risp == 'y')
			return true;
		else
			return false;
	}
	
	public Coordinates askForLights(){
		System.out.println("Which sector of the map do you want to enlight? Insert the coordinates of the box you want to move in. Insert the letter, press enter, then the number of the box, then press enter again.");
		
		char letter = in.next().charAt(0);
		//parse the ASCII code of the char and convert it to a number, starting from 'A'-->1
		int numberX = (int)letter;
		if (numberX<88) numberX-=64;
		else numberX-=96;
		
		int numberY = in.nextInt();
		
		Coordinates coordinates = new Coordinates(numberX, numberY);
		return coordinates;
		
	}
	
	public void revealingLights(Box box){
		System.out.println("In the position "+(char)(box.getCoordX()+64)+box.getCoordY()+" there are the following players: ");
		System.out.println(box.getPlayerHere().toString());
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
