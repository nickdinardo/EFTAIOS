package it.polimi.ingsw.DiNapoliDiNardo.view;


import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SwingView extends View{
	
	private Information info;
	private TurnFrame turnFrame;
	private WaitFrame waitFrame;
	private ButtonHandler button = new ButtonHandler();
	private ClickableBox boxUtility = new ClickableBox();
	private boolean inputCoordinates = false;
	private boolean hasMoved = false;
	private boolean removedWaitFrame = true;
	private static final Coordinates WALLCOORD = new Coordinates (12,7);
	
	
	
	@Override
	public String askName(boolean reask){
				
		RegistrationFrame registration = new RegistrationFrame(new JFrame("Registration to the match"), reask);
		return registration.getName();
	}

	@Override
	public void showBeingHuman(String name){
		
		YouAreHumanFrame showHuman = new YouAreHumanFrame(new JFrame("Welcome to the Ship"));
		showHuman.getNext();
		info = new Information(1);
		info.setPlayerName(name);
		turnFrame = new DefinitiveHumanTurnFrame(info.getPlayerName(), info.getActualPosition(), info.getTurn());
	}
	
	@Override
	public void showBeingAlien(String name){
		
		YouAreAlienFrame showAlien = new YouAreAlienFrame(new JFrame("Welcome to the Ship"));
		showAlien.getNext();
		info = new Information(2);
		info.setPlayerName(name); 
		turnFrame = new DefinitiveAlienTurnFrame(info.getPlayerName(), info.getActualPosition(), info.getTurn());
	}
	
	@Override
	public void showActualSituation (String name, String position, String objects, String turn){
		turnFrame.appendToTextArea("--------------------------------------------------\n");
		info.setPlayerName(name);
		info.setActualPosition(position);
		if (position.length() == 2)
			info.setActualPosition(position.substring(0, 1) + "0" + position.substring(1, position.length()));
		info.setTurn(turn);
		button.setWaitAttack(false);
		button.setWaitItems(false);
		if (!"no".equals(objects)){
			info.setItem(Arrays.asList(objects.split(" ")));
		}
		else
			info.addToItem(0, "");
		hasMoved = false;
		turnFrame.update(info.getPlayerName(), info.getActualPosition(), info.getTurn(), info.getItem(), true );
	}
	
	@Override
	public void update(String position, String reachables, String objects) {
		info.setActualPosition(position);
		if (position.length() == 2)
			info.setActualPosition(position.substring(0, 1) + "0" + position.substring(1, position.length()));
		if (!"no".equals(objects)){
			info.setItem(Arrays.asList(objects.split(" ")));
		}
		else
			info.addToItem(0, "");
	
		turnFrame.update(info.getPlayerName(), info.getActualPosition(), info.getTurn(), info.getItem(), false );
		
		BufferedImage image = null;
		BufferedImage enlightedMap = null;
		try {
		    image = ImageIO.read(new File("externalresources\\galileiDefinitiva.png"));
		    Color color1 = new Color(0,0,0);
		    Color color2 = new Color(0,0,0);
		    Color colorPos1 = new Color(0,0,0);
		    if (turnFrame instanceof DefinitiveHumanTurnFrame){
		    	color1 = new Color(40,62,119);
		    	color2 = new Color(16,30,69);
		    	colorPos1 = new Color (102, 129, 198); 
		    }
		    if (turnFrame instanceof DefinitiveAlienTurnFrame){
		    	color1 = new Color(89,43,80);
		    	color2 = new Color(56,27,50);
		    	colorPos1 = new Color (168, 81, 151);
		    }
			enlightedMap = boxUtility.colorReachablesBoxes(image, color1, color2, colorPos1, reachables, info.getActualPosition());
			turnFrame.setMapImage(enlightedMap);
		} catch (IOException e) {
			//it won't simply charge any image
		}
		
	}
	
	@Override
	public int askItemUse(String objects, boolean discardCall){
		
		if (!removedWaitFrame){
			waitFrame.dispose();
			removedWaitFrame = true;
		}
		//set the current items in the "information" class
		if (!"no".equals(objects)){
			if(discardCall){
				print("Please, select the item you want to use to get free the slot\n");
				info.setItem(Arrays.asList(objects.split(" ;")));
			}else{
				info.setItem(Arrays.asList(objects.split(" ;")));
			}
			
		}
		else
			info.addToItem(0, "");
		
		CardHandler cardHandler = new CardHandler(turnFrame);
		cardHandler.setCards(turnFrame.setCardHandler(info.getItem()));
		
		//first item usage phase, before movement
		if(!hasMoved){
			turnFrame.appendToTextArea("Press a card to activate an object, or press the map to move\n");
			BoxHandler boxClick = new BoxHandler();
			MouseListener clickListen = boxClick.startListen(turnFrame.getBackgroundImage());
			
			//wait while player click the map or a card
			while(!boxClick.getWait() && !cardHandler.getWaitForItem()){
				Thread.currentThread();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//do nothing
				}
			}
			turnFrame.getBackgroundImage().removeMouseListener(clickListen);
			
			//this if player clicked on the map deciding not to use any object
			if (boxClick.getWait()){
				inputCoordinates = true;
				info.setMoveCoord(boxClick.getCoordinates());
				cardHandler.removeListeners();
				info.setSelectedItem(8);
				return info.getSelectedItem();
			//this if player clicked a card
			}else{
				info.setSelectedItem(cardHandler.getIndexCard());
				cardHandler.removeListeners();
				return info.getSelectedItem();
			}
		}
		
		//second item usage phase, after movement
		else{
			turnFrame.appendToTextArea("Press a card to activate an object, or press Next button to pass the turn\n");
			ActionListener nextB = button.startNextListen(turnFrame.getNextButton(), 1);
			
			//wait while player click next to pass the turn or a card
			while(!cardHandler.getWaitForItem() && !button.getWaitItems()){
				Thread.currentThread();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//do nothing
				}
			}
			
			//if player clicked a card index will different from 8 (don't use), otherwise still 8
			info.setSelectedItem(cardHandler.getIndexCard());
			turnFrame.getNextButton().removeActionListener(nextB);
			cardHandler.removeListeners();
			return info.getSelectedItem();
		}
		
		
		
	}
	
	@Override
	public Coordinates askForLights(boolean reask){
		LightsFrame lightsframe = new LightsFrame(reask);
		LightsHandler lightsHandler = new LightsHandler();
		lightsHandler.startListen(lightsframe.getBackground());
		
		while(!lightsHandler.getWait()){
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
	
	@Override
	public String askForAttack(){
		
		turnFrame.appendToTextArea("Filthy alien, do you want to attack this position?\n");
		turnFrame.appendToTextArea("Press Attack if yes, Next if no\n");
		button.setWaitAttack(false);
		ActionListener attackB = button.startAttackListen(((DefinitiveAlienTurnFrame)turnFrame).getAttackButton());
		ActionListener nextB = button.startNextListen(turnFrame.getNextButton(), 2);
		
		while(!button.getWaitAttack()){
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
	
	@Override
	public Coordinates askMovement(boolean reask){
		
		if (!removedWaitFrame){
			waitFrame.dispose();
			removedWaitFrame = true;
		}
		
		if(reask)
			turnFrame.appendToTextArea("The movement you selected is not valid. Please select another box\n");
				
		if(inputCoordinates){
			inputCoordinates = false;
			hasMoved = true;
			return info.getMoveCoord();
		}
		else{
			BoxHandler boxClick = new BoxHandler();
			boxClick.startListen(turnFrame.getBackgroundImage());
			turnFrame.appendToTextArea("Where do you want to move? Click on the box in the map\n");
			
			
			while (boxClick.getWait() == false ){
				Thread.currentThread();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//do nothing
				}
			}
			info.setMoveCoord(boxClick.getCoordinates());
			hasMoved = true;
			if (info.getMoveCoord().getCoordX() != 0 && info.getMoveCoord().getCoordY() != 0)
				return info.getMoveCoord();
			else
				return WALLCOORD;
		}
	}
	
	@Override
	public String askForNoise(){
		turnFrame.appendToTextArea("In which sector of the map do you want to declare there's noise?\n");
		BoxHandler noiseClick = new BoxHandler();
		noiseClick.startListen(turnFrame.getBackgroundImage());
		
		Coordinates coords;
		do{
			while(!noiseClick.getWait()){
				Thread.currentThread();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//do nothing
				}
			}
			coords = noiseClick.getCoordinates();
			if((coords.getCoordX() == 1) && (coords.getCoordY() == 1))
				noiseClick.setWait(false);
		}while ((coords.getCoordX() == 1) && (coords.getCoordY() == 1));
		
		String noise = ""+(char)(coords.getCoordX()+64);
		String number = ""+ coords.getCoordY();
		if (number.length() == 1)
			number = "0"+ coords.getCoordY();
		noise += number;
		return noise;
	}
	
	
	@Override	
	public int askHumanItemDiscard(String objects){
		return discardFrameManager(true);
	}
	
	
	@Override
	public int askAlienItemDiscard(String objects){
		return discardFrameManager(false);
	}
	
	
	public int discardFrameManager(boolean withUse){

		DiscardFrame frame = new AlienDiscardFrame(info.getItem());
		CardHandler cardHandler = new CardHandler(turnFrame);
		cardHandler.setCards(frame.setCardHandler());
		cardHandler.startListenNoButton(frame.getButtonNo());
		if (withUse)
			cardHandler.startListenUseButton(((HumanDiscardFrame)frame).getUseButton());
		
		while(!cardHandler.getWaitForItem()){
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
	
		
	@Override
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
	
	@Override
	public void showFinalResults(boolean iWon, String name, String humanlosers,	String humanwinners, String alienwinners, String alienlosers){
		if (!removedWaitFrame){
			waitFrame.dispose();
			removedWaitFrame = true;
		}
		
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
	
	@Override
	public void print (String message){
		turnFrame.appendToTextArea(message+"\n");
	}

	@Override
	public void signalEndOfTurn() {
		removedWaitFrame = false;
		waitFrame = new WaitFrame();
	}
	
}
	



