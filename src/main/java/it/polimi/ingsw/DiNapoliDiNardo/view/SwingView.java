package it.polimi.ingsw.DiNapoliDiNardo.view;


import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class SwingView extends View{
	
	private Information info;
	private TurnFrame turnFrame;
	private ButtonHandler button = new ButtonHandler();
	private boolean coordinates = false;
	private static final Coordinates WALLCOORD = new Coordinates (12,7);
	
	
	public String askName(boolean reask){
				
		RegistrationFrame registration = new RegistrationFrame(new JFrame("Registration to the match"), reask);
		return registration.getName();
	}


	public void showBeingHuman(String name){
		
		
		HumanDescriptionFrame showHuman = new HumanDescriptionFrame(new JFrame("Description"), name);
		showHuman.getNext();
		info = new Information(1);
		info.setPlayerName(name); //TODO da controllare quando completo
		turnFrame = new DefinitiveHumanTurnFrame(info.getPlayerName(), info.getActualPosition(), info.getTurn(), info.getItem() );
	}
	
	
	public void showBeingAlien(String name){
		
		
		AlienDescriptionFrame showAlien = new AlienDescriptionFrame(new JFrame("Description"), name);
		showAlien.getNext();
		info = new Information(2);
		info.setPlayerName(name); //TODO da controllare quando completo
		//turnFrame = new AlienTurnFrame(info.getPlayerName(), info.getActualPosition(), info.getTurn(), info.getItem() );
		turnFrame = new DefinitiveAlienTurnFrame(info.getPlayerName(), info.getActualPosition(), info.getTurn(), info.getItem());
	}
	
	
	public void showActualSituation (String name, String position, String objects, String turn){
		info.setPlayerName(name);
		info.setActualPosition(position);
		if (position.length() == 2)
			info.setActualPosition(position.substring(0, 1) + "0" + position.substring(1, position.length()));
		info.setTurn(turn);
		if (!"no".equals(objects)){
			info.setItem(Arrays.asList(objects.split(" ")));
		}
		else
			info.addToItem(0, "");
		button.setWaitAttack(false);
		button.setWaitCoordinates(false);
		button.setWaitItems(false);
		
		
		turnFrame.update(info.getPlayerName(), info.getActualPosition(), info.getTurn(), info.getItem() );
	}
	
	
	public int askItemUse(String objects, boolean discardCall){
		turnFrame.appendToTextArea("If you want to use a card press on the corresponding image\n");
		
		if (!"no".equals(objects)){
			if(discardCall){
				print("Please, select the item you want to use to get free the slot");
				info.setItem(Arrays.asList(objects.split(" ;")));
			}else{
				info.setItem(Arrays.asList(objects.split(" ;")));
			}
			
		}
		else
			info.addToItem(0, "");
		
		CardHandler cardHandler = new CardHandler();
		cardHandler.setCards(turnFrame.setCardHandler(info.getItem()));
		BoxHandler boxClick = new BoxHandler();
		boxClick.startListen(turnFrame.getBackgroundImage());
		
		while(boxClick.getWait() == false && cardHandler.getWaitForItem() == false  && button.getWaitItems() == false){
			Thread.currentThread();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//do nothing
			}
		}
		if (boxClick.getWait() == true){
			coordinates = true;
			info.setMoveCoord(boxClick.getCoordinates());
			return info.getSelectedItem();
		}else{
			info.setSelectedItem(cardHandler.getIndexCard());
			return info.getSelectedItem();
		}
		
	}
	
	public Coordinates askForLights(boolean reask){
		LightsFrame lightsframe = new LightsFrame(reask);
		LightsHandler lightsHandler = new LightsHandler();
		lightsHandler.startListen(lightsframe.getBackground());
		
		while(lightsHandler.getWait() == false){
			Thread.currentThread();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//do nothing
			}
		}
		info.setLightsCoord(lightsHandler.getCoordinates());
		lightsframe.getFrame().dispose();
		return info.getLightsCoord();
	}
	
	public String askForAttack(){
		
		turnFrame.appendToTextArea("Filthy alien, do you want to attack this position?\n");
		turnFrame.appendToTextArea("Press Attack if yes, Next if no\n");
		button.setWaitAttack(false);
		ActionListener attackB = button.startAttackListen(((DefinitiveAlienTurnFrame)turnFrame).getAttackButton());
		ActionListener nextB = button.startNextListen(turnFrame.getNextButton(), 2);
		
		while(button.getWaitAttack() == false){
			Thread.currentThread();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//do nothing
			}
		}
		info.setAttackAnswer(button.getAnswer());
		DefinitiveAlienTurnFrame alienframe = (DefinitiveAlienTurnFrame)turnFrame;
		alienframe.getAttackButton().removeActionListener(attackB);
		alienframe.getNextButton().removeActionListener(nextB);
		return info.getAttackAnswer();		
	}
	
	public Coordinates askMovement(boolean reask){
		if(reask)
			turnFrame.appendToTextArea("The movement you selected is not valid. Please select another box\n");
		//ActionListener nextB = button.startNextListen(turnFrame.getNextButton(), 1);
		
		if(coordinates == true){
			coordinates = false;
			
			return info.getMoveCoord();
		}
		else{
			BoxHandler boxClick = new BoxHandler(button);
			boxClick.startListen(turnFrame.getBackgroundImage());
			turnFrame.appendToTextArea("Where do you want to move? Click on the box in the map\n");
			
			//|| button.getWaitCoordinates() == false
			while (boxClick.getWait() == false ){
				Thread.currentThread();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//do nothing
				}
			}
			info.setMoveCoord(boxClick.getCoordinates());
			//turnFrame.getNextButton().removeActionListener(nextB);
			if (info.getMoveCoord().getCoordX() != 0 && info.getMoveCoord().getCoordY() != 0)
				return info.getMoveCoord();
			else
				return WALLCOORD;
		}
	}
	
	
	public String askForNoise(){
		turnFrame.appendToTextArea("In which sector of the map do you want to declare there's noise?\n");
		BoxHandler noiseClick = new BoxHandler();
		noiseClick.startListen(turnFrame.getBackgroundImage());
		
		while(noiseClick.getWait() == false){
			Thread.currentThread();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//do nothing
			}
		}
		Coordinates coordinates = noiseClick.getCoordinates();
		String noise = ""+(char)(coordinates.getCoordX()+64);
		String number = ""+ coordinates.getCoordY();
		if (number.length() == 1)
			number = "0"+ coordinates.getCoordY();
		noise += number;
		return noise;
	}
	
	
	
	
	public int askHumanItemDiscard(String objects){
		DiscardFrame frame = new HumanDiscardFrame(info.getItem());
		CardHandler cardHandler = new CardHandler();
		cardHandler.setCards(frame.setCardHandler(info.getItem()));
		cardHandler.startListenNoButton(frame.getButtonNo());
		cardHandler.startListenUseButton(((HumanDiscardFrame)frame).getUseButton());
		
		while(cardHandler.getWaitForItem() == false){
			Thread.currentThread();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//do nothing
			}
		}
		info.setItemToRemove(cardHandler.getIndexCard());
		frame.getFrame().dispose();
		return info.getItemToRemove() + 3;
	}
	
	public int askAlienItemDiscard(String objects){
		
		DiscardFrame frame = new AlienDiscardFrame(info.getItem());
		CardHandler cardHandler = new CardHandler();
		cardHandler.setCards(frame.setCardHandler(info.getItem()));
		cardHandler.startListenNoButton(frame.getButtonNo());
		
		while(cardHandler.getWaitForItem() == false){
			Thread.currentThread();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//do nothing
			}
		}
		info.setItemToRemove(cardHandler.getIndexCard());
		frame.getFrame().dispose();
		return info.getItemToRemove() + 3;
	}
	
	public void notifyEscape(boolean escaped, String name, String shipnumber) {
		print(name + " has REACHED THE LIFEBOAT SHIP " + shipnumber + " .....");
		if(escaped){
			print("The lifeboat ship " + shipnumber + " is incredibly still working!!!");
			print(name + " is now safe and far from here. The lifeboat ship " + shipnumber + " won't be accessible anymore");
		}
		else{
			print("The lifeboat ship " + shipnumber + " does not answer to commands! " + name + " still couldn't escape!");
			print("You'd better remember lifeboat " + shipnumber + " is not working...");
		}
	}
	
	public void showFinalResults(boolean iWon, String name, String humanlosers,	String humanwinners, String alienwinners, String alienlosers){
		FinalResultFrame frame = new FinalResultFrame(iWon, name);
		if (!humanwinners.isEmpty())
			print("The HUMANS that managed to escape and WON the game are: " + humanwinners);
		if (!humanlosers.isEmpty())
			print("The HUMANS that has been killed by the aliens and LOST the game are: " + humanlosers);
		if (!alienwinners.isEmpty())
			print("The aliens managed to avoid the escape of all the humans, so the ALIENS that WON are: " + alienwinners);
		else 
			print("The aliens have remained alone on the ship while the last human reached the lifeboat ship, so the ALIENS that LOST are: " + alienlosers);
		print("THE END");
	}
	
	public void print (String message){
		turnFrame.appendToTextArea(message+"\n");
		turnFrame.setJsbValue(turnFrame.getJsbMaximum());
	}
	
}
	



