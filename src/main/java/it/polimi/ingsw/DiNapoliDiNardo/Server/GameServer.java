package it.polimi.ingsw.DiNapoliDiNardo.Server;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.model.AlienPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.DangerousBox;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;





public class GameServer {
	int totalplayers;
	Map<String, String> playersconnected; 
	Map<String, Handler> handlers;
	boolean finish;
	GameState gamestate;
	
	
	public void rungame() throws IOException, ClassNotFoundException{
		
		
		giveWelcome();
		this.gamestate = new GameState(this);
		createPlayersInGame(playersconnected);
		informPlayersOfTheirNature();
		

		while(!finish){
			
			showActualSituation ();
			
			//turn iteration
			Iterator<Map.Entry<String, String>> it = playersconnected.entrySet().iterator();
		    while (it.hasNext()) {
		    
		    	Map.Entry<String, String> entry = it.next();
		        String playername = entry.getKey();
				
		        if(gamestate.givemePlayerByName(playername).isAlive()){
					if(gamestate.givemePlayerByName(playername) instanceof AlienPlayer)
						askForAlienTurn(playername, entry.getValue());
						
					else if (gamestate.givemePlayerByName(playername) instanceof HumanPlayer)
						askForHumanTurn(playername, entry.getValue());
				}
		        
				gamestate.removeInTurnBonus();
		    }	
		
		    //removing dead players iteration
		    Iterator<Map.Entry<String, String>> remover = playersconnected.entrySet().iterator();
		    while (remover.hasNext()) {
		    	
		    	Map.Entry<String, String> entry = remover.next();
		        String playername = entry.getKey();
		        System.out.println(positionToString(gamestate.givemePlayerByName(playername)));
		        
		        
		        Player maybedead = gamestate.givemePlayerByName(playername);
		        		        	
		        if(!maybedead.isAlive()){
			       remover.remove();
			       maybedead.getPosition().getPlayerHere().remove(maybedead);
			    }
		   
		    }
		   
		    //check for winning conditions, code still to make
		}
	
	
	
	}	
	
	
	
	
	
	
	private void showActualSituation () throws RemoteException{
		
		for (Map.Entry<String, String> entry : playersconnected.entrySet()){
		
			Player player;
			player = gamestate.givemePlayerByName(entry.getKey());
			ArrayList<Card> itemdeck = player.getPersonalDeck();
			String position = ""+(char)(player.getPosition().getCoordX()+64)+player.getPosition().getCoordY();	
			String objects = "";
			for (int i=0; i<itemdeck.size(); i++)
				objects += itemdeck.get(i).getName()+" ";		
			if (objects.length()<2)
				objects = "no";
			handlers.get(entry.getKey()).showActualSituation(entry.getKey(), position, objects);
		}
	}
	
	
	
	
	private void askForHumanTurn(String playername, String connection) throws ClassNotFoundException, IOException{
		
		HumanPlayer player = (HumanPlayer)gamestate.givemePlayerByName(playername);
		ArrayList<Card> itemdeck = gamestate.givemePlayerByName(playername).getPersonalDeck();
		int index;
		
		if (gamestate.givemePlayerByName(playername).getPersonalDeck().size()>0){
			String objects = personalDeckListify(itemdeck);
		
			index = handlers.get(playername).askForItem(objects);
				if (index != 8)
					gamestate.itemUsageManagement(playername, index-1);
		}
		
		askForMovement(playername, connection);
		
		if (player.getPosition() instanceof DangerousBox && !player.isSedated())
			drawSectorCard(playername, connection, player);
		
		if (gamestate.givemePlayerByName(playername).getPersonalDeck().size()>0){
			String objects = personalDeckListify(itemdeck);
			index = handlers.get(playername).askForItem(objects);
				if (index != 8)
					gamestate.itemUsageManagement(playername, index-1);
		}
	}
	
		
	
	
	private void askForAlienTurn(String playername, String connection) throws ClassNotFoundException, IOException{
		
		AlienPlayer player = (AlienPlayer)gamestate.givemePlayerByName(playername);
		
		askForMovement(playername, connection);
		boolean attack = false;
		attack = handlers.get(playername).askForAttack();
		
		if (attack){
			String position = positionToString(player);
			notifyMessage(playername+" has ATTACKED sector "+position);
			gamestate.attackManagement(player);
			
		}
		if (player.getPosition() instanceof DangerousBox && !player.isHasAttacked())
			drawSectorCard(playername, connection, player);
	}
	
	
	
	
	private void askForMovement(String playername, String connection) throws ClassNotFoundException, IOException{
		
		Coordinates coordinates;
		boolean validmove = false;
		coordinates = handlers.get(playername).askForMovement(false);
		do{
			validmove = gamestate.updatePlayerPosition(playername, coordinates);
			if(!validmove)
				coordinates = handlers.get(playername).askForMovement(true);
		}while(!validmove);
	}

	
	
	public void notifyMessage(String message) throws RemoteException{
		
		for (Map.Entry<String, String> entry : playersconnected.entrySet())
			handlers.get(entry.getKey()).notifyMessage(message);
	}
	
	
	
	public Coordinates askForLights(String name) throws ClassNotFoundException, RemoteException, IOException{
		Coordinates coordinates;
		coordinates = handlers.get(name).askForLights();
		return coordinates;
	}
	
	
	
	
	public void showLights(String name, String lightposition, String playersinbox) throws ClassNotFoundException, RemoteException, IOException{
		if (playersinbox.equals(""))
			handlers.get(name).notifyMessage("In the position "+lightposition+" there is no one.");
		else
			handlers.get(name).notifyMessage("In the position "+lightposition+" there are: "+playersinbox);	
	}
	
	
	 
	
	public void cardsMessages(String name, String type) throws ClassNotFoundException, RemoteException, IOException{
		String message = "";
		if (type.equals("defense"))
			message = ("You can't use a Defense Card, it will activate by itself when you'll be attacked");
		if (type.equals("teleport")){
			message = ("-BZZZ...You successfully teleported back to L08, your starting position-");
			notifyMessage(name+" has used a Teleport Card");
		}
		if (type.equals("sedative")){
			message = ("-Injecting yourself the sedatives you calm down and control your body. You'll not make noise around this turn-");
			notifyMessage(name+" has used a Sedative Card");
		}
			
		if (type.equals("adrenaline")){
			message = ("-Injecting yourself adrenaline you feel your body answer more quickly. You're faster in movements this turn-");
			notifyMessage(name+" has used an Adrenaline Card");
		}
		if (type.equals("attack")){
			message = ("-You charge, point and fire your weapon in the darkness. If someone (or something) is there he will suffer the consequences-");
			String position = positionToString(gamestate.givemePlayerByName(name));
			notifyMessage(name+" has ATTACKED position "+position+" using an Attack Card");
		}
		
		handlers.get(name).notifyMessage(message);
	}
	
	
	
	
	private void createPlayersInGame(Map<String, String> playersconnected) {
		ArrayList<String> keys = new ArrayList<String>(playersconnected.keySet());
		Collections.shuffle(keys);
		int i = 0;
		for (String name : keys){
			i++;
			if (i%2 == 1)
				gamestate.getInGamePlayers().add(new AlienPlayer(gamestate.getGalilei(), gamestate, name));
			else 
				gamestate.getInGamePlayers().add(new HumanPlayer(gamestate.getGalilei(), gamestate, name));
		}
	}

	
	
	
	private void drawSectorCard (String name, String connection, Player player) throws ClassNotFoundException, IOException{
		
		Card sectorcard = gamestate.getSectordeck().drawCard();
		String position = positionToString(player);
				
		if (sectorcard instanceof SilenceCard){
			notifyMessage(name+" declares: SILENCE.");
		}
		if (sectorcard instanceof NoiseHereCard){
			notifyMessage(name+" declares: NOISE in sector "+position+".");
		}
		if (sectorcard instanceof NoiseAnywhereCard){
			String noiseIn = "";
			noiseIn = handlers.get(name).askForNoise();
			notifyMessage(name+" declares: NOISE in sector "+noiseIn+".");
		}
		
		//call method to draw item cards if required by sector card
		if (sectorcard instanceof NoiseAnywhereCardPlusItem || sectorcard instanceof NoiseHereCardPlusItem)
			drawItemCard (name, connection, player);
	}

	
	
	
	private void drawItemCard (String name, String connection, Player player) throws RemoteException, IOException, ClassNotFoundException{
		
		//manage the personal decks of the players when a new item card is drawn	
		ItemCard itemcard = (ItemCard)gamestate.getItemdeck().drawCard();
		ArrayList<Card> itemdeck = gamestate.givemePlayerByName(name).getPersonalDeck();	 
		
		if (itemdeck.size()==3){
			int index;
			String objects = personalDeckListify(itemdeck);
			
			if (player instanceof HumanPlayer)
				index = handlers.get(name).askHumanForItemChange(objects);
			else
				index = handlers.get(name).askAlienForItemChange(objects);
			if (index != 8){
				gamestate.itemUsageManagement(name, index-1);
				gamestate.givemePlayerByName(name).getPersonalDeck().add(itemcard);
			}
			
		}		
		
		if (itemdeck.size()<3){
			gamestate.givemePlayerByName(name).getPersonalDeck().add(itemcard);
			handlers.get(name).notifyMessage(name+" you drew one "+itemcard.getName()+".");
		}
		
	}
	
	
	
	
	public void sayByeToLosers(String dead, String killer) throws RemoteException{

		handlers.get(dead).notifyMessage("-----------------------------------------------");
		handlers.get(dead).notifyMessage(dead+" you've been brutally killed by "+killer);
		handlers.get(dead).notifyMessage("Unfortunately, your game ends here");
		handlers.get(dead).notifyMessage("-----------------------------------------------");
		
		gamestate.getInGamePlayers().remove(dead);
	}
	
	
	
	
	private void giveWelcome() throws IOException{
	
		//print a welcome message and give the list of players to each player.
		String listofplayers = "";
		for (Map.Entry<String, String> entry : playersconnected.entrySet())
			listofplayers += entry.getKey()+", ";
		listofplayers = listofplayers.substring(0, listofplayers.length()-2);
		for (Map.Entry<String, String> entry : playersconnected.entrySet())
			handlers.get(entry.getKey()).notifyMessage("Welcome to the game "+ entry.getKey()+". The crew of the infected spaceship is composed by: "+listofplayers+". ");
		
	}

	
	
	
	private void informPlayersOfTheirNature() throws RemoteException{
		
		//inform players on the nature of their character
		for (Map.Entry<String, String> entry : playersconnected.entrySet()){
			String playername = entry.getKey();
			if(gamestate.givemePlayerByName(playername) instanceof AlienPlayer)
				handlers.get(entry.getKey()).showBeingAlien(entry.getKey());
			else if (gamestate.givemePlayerByName(playername) instanceof HumanPlayer)
				handlers.get(entry.getKey()).showBeingHuman(entry.getKey());
		
		}
	}
	
	
	
	
	private String personalDeckListify (ArrayList<Card> itemdeck){
	
		//utility for other methods, put in a string all the item cards of a personal deck
		String objects = "";
		for (int i=0; i<itemdeck.size(); i++)
			objects += itemdeck.get(i).getName()+" ;";
		return objects;
	}
	
	
			
	private String positionToString (Player player){
		String position = ""+(char)(player.getPosition().getCoordX()+64);
		String number = ""+ player.getPosition().getCoordY();
		if (number.length() == 1)
			number = "0"+ player.getPosition().getCoordY();
		position += number;
		return position;
	}
	
	
	public GameServer (int t, Map<String, String> pc, Map<String, Handler> hand){
		
		this.totalplayers = t;
		this.playersconnected = pc;
		this.handlers = hand;
	}

}
