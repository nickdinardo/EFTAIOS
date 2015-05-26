package it.polimi.ingsw.DiNapoliDiNardo.Server;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.Server.Socket.SocketHandler;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteNotifier;
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
import java.util.HashMap;
import java.util.Iterator;





public class GameServer {
	int totalplayers;
	HashMap<String, String> playersconnected; 
	HashMap<String, RemoteNotifier> notifiers; 
	HashMap<String, SocketHandler> sockethandlers; 
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
			Iterator<HashMap.Entry<String, String>> it = playersconnected.entrySet().iterator();
		    while (it.hasNext()) {
		    
		    	HashMap.Entry<String, String> entry = it.next();
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
		    Iterator<HashMap.Entry<String, String>> remover = playersconnected.entrySet().iterator();
		    while (remover.hasNext()) {
		    	
		    	HashMap.Entry<String, String> entry = remover.next();
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
		
		for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
			Player player;
			player = gamestate.givemePlayerByName(entry.getKey());
			ArrayList<Card> itemdeck = player.getPersonalDeck();
			String position = ""+(char)(player.getPosition().getCoordX()+64)+player.getPosition().getCoordY();	
			String objects = "";
			for (int i=0; i<itemdeck.size(); i++)
				objects += itemdeck.get(i).getName()+" ";		
			if (objects.length()<2)
				objects = "no";
			if(entry.getValue().equals("RMI"))
				givemeNotifierByName(entry.getKey()).showActualSituation(entry.getKey(), position, objects);
			else{
				givemeSocketHandlerByName(entry.getKey()).showActualSituation(entry.getKey(), position, objects);
				
			}
				
		}
	}
	
	
	
	
	private void askForHumanTurn(String playername, String connection) throws ClassNotFoundException, IOException{
		
		HumanPlayer player = (HumanPlayer)gamestate.givemePlayerByName(playername);
		ArrayList<Card> itemdeck = gamestate.givemePlayerByName(playername).getPersonalDeck();
		int index;
		
		if (gamestate.givemePlayerByName(playername).getPersonalDeck().size()>0){
			String objects = personalDeckListify(itemdeck);
			if(connection.equals("RMI"))
				index = givemeNotifierByName(playername).askForItem(objects);
			else
				index = givemeSocketHandlerByName(playername).askForItem(objects);
				if (index != 8)
					gamestate.itemUsageManagement(playername, index-1);
		}
		
		askForMovement(playername, connection);
		
		if (player.getPosition() instanceof DangerousBox && !player.isSedated())
			drawSectorCard(playername, connection, player);
		
		if (gamestate.givemePlayerByName(playername).getPersonalDeck().size()>0){
			String objects = personalDeckListify(itemdeck);
			if(connection.equals("RMI"))
				index = givemeNotifierByName(playername).askForItem(objects);
			else
				index = givemeSocketHandlerByName(playername).askForItem(objects);
				if (index != 8)
					gamestate.itemUsageManagement(playername, index-1);
		}
	}
	
		
	
	
	private void askForAlienTurn(String playername, String connection) throws ClassNotFoundException, IOException{
		
		AlienPlayer player = (AlienPlayer)gamestate.givemePlayerByName(playername);
		
		askForMovement(playername, connection);
		boolean attack = false;
		if(connection.equals("RMI"))
			attack = givemeNotifierByName(playername).askForAttack();
		else
			attack = givemeSocketHandlerByName(playername).askForAttack();
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
		if(connection.equals("RMI")){
			coordinates = givemeNotifierByName(playername).askForMovement(false);
		}
		else {
			coordinates = givemeSocketHandlerByName(playername).askForMovement(false);
		}
			
		do{
			validmove = gamestate.updatePlayerPosition(playername, coordinates);
			if(!validmove){
				if(connection.equals("RMI")){
					coordinates = givemeNotifierByName(playername).askForMovement(true);
				}
				else {
					coordinates = givemeSocketHandlerByName(playername).askForMovement(true);
				}
			}
		}while(!validmove);
	}

	
	
	public void notifyMessage(String message) throws RemoteException{
		for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
			if(entry.getValue().equals("RMI"))
				givemeNotifierByName(entry.getKey()).notifyMessage(message);
			else
				givemeSocketHandlerByName(entry.getKey()).notifyMessage(message);
		}
	}
	
	
	
	public Coordinates askForLights(String name) throws ClassNotFoundException, RemoteException, IOException{
		Coordinates coordinates;
		if (notifiers.containsKey(name))
			coordinates = notifiers.get(name).askForLights();
		else
			coordinates = sockethandlers.get(name).askForLights();
		return coordinates;
	}
	
	
	
	
	public void showLights(String name, String lightposition, String playersinbox) throws ClassNotFoundException, RemoteException, IOException{
		if (playersinbox.equals("")){
			if (notifiers.containsKey(name))
				notifiers.get(name).notifyMessage("In the position "+lightposition+" there is no one.");
			else
				sockethandlers.get(name).notifyMessage("In the position "+lightposition+" there is no one.");
		}
		else{
			if (notifiers.containsKey(name))
				notifiers.get(name).notifyMessage("In the position "+lightposition+" there are: "+playersinbox);
			else
				sockethandlers.get(name).notifyMessage("In the position "+lightposition+" there are: "+playersinbox);	
			}
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
		
		if (notifiers.containsKey(name))
			notifiers.get(name).notifyMessage(message);
		else
			sockethandlers.get(name).notifyMessage(message);
		
	}
	
	
	
	
	private void createPlayersInGame(HashMap<String, String> playersconnected) {
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
			if(connection.equals("RMI"))
				noiseIn = givemeNotifierByName(name).askForNoise();
			else
				noiseIn = givemeSocketHandlerByName(name).askForNoise();
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
			if(connection.equals("RMI")){
				if (player instanceof HumanPlayer)
					index = givemeNotifierByName(name).askHumanForItemChange(objects);
				else
					index = givemeNotifierByName(name).askAlienForItemChange(objects);
			}
			else{
				if (player instanceof HumanPlayer)
					index = givemeSocketHandlerByName(name).askHumanForItemChange(objects);
				else
					index = givemeSocketHandlerByName(name).askAlienForItemChange(objects);
			}
			//if selection has been use/discard an item call the game state method with his index 
			//then replace the card with the new card. Do nothing if index == 8 (keep actuals).
			if (index != 8){
				gamestate.itemUsageManagement(name, index-1);
				gamestate.givemePlayerByName(name).getPersonalDeck().add(itemcard);
			}
			
		}		
		
		if (itemdeck.size()<3){
			gamestate.givemePlayerByName(name).getPersonalDeck().add(itemcard);
			if(connection.equals("RMI"))
				givemeNotifierByName(name).notifyMessage(name+" you drew one "+itemcard.getName()+".");
			else
				givemeSocketHandlerByName(name).notifyMessage(name+" you drew one "+itemcard.getName()+".");
		}
		
	}
	
	
	
	
	public void sayByeToLosers(String dead, String killer) throws RemoteException{

		if (notifiers.containsKey(dead)){
			notifiers.get(dead).notifyMessage("-----------------------------------------------");
			notifiers.get(dead).notifyMessage(dead+" you've been brutally killed by "+killer);
			notifiers.get(dead).notifyMessage("Unfortunately, your game ends here");
			notifiers.get(dead).notifyMessage("-----------------------------------------------");
		}
		else{
			notifiers.get(dead).notifyMessage("-----------------------------------------------");
			sockethandlers.get(dead).notifyMessage(dead+" you've been brutally killed by "+killer);
			sockethandlers.get(dead).notifyMessage("Unfortunately, your game ends here");
			notifiers.get(dead).notifyMessage("-----------------------------------------------");
		}
		gamestate.getInGamePlayers().remove(dead);
	}
	
	
	
	
	private void giveWelcome() throws IOException{
	
		//print a welcome message and give the list of players to each player.
		String listofplayers = "";
		for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
			listofplayers += entry.getKey()+", ";
		}
		listofplayers = listofplayers.substring(0, listofplayers.length()-2);
		for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
			if(entry.getValue().equals("RMI")){
				givemeNotifierByName(entry.getKey()).notifyMessage("Welcome to the game "+ entry.getKey()+". The crew of the infected spaceship is composed by: "+listofplayers+". ");
			}
			else {
				givemeSocketHandlerByName(entry.getKey()).printWelcomeMessage(entry.getKey(), listofplayers);
			}
		}
	}

	
	
	
	private void informPlayersOfTheirNature() throws RemoteException{
		
		//inform players on the nature of their character
		for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
			String playername = entry.getKey();
			if(gamestate.givemePlayerByName(playername) instanceof AlienPlayer){
				if(entry.getValue().equals("RMI")){
					givemeNotifierByName(entry.getKey()).showBeingAlien(entry.getKey());
				}
				else {
					givemeSocketHandlerByName(entry.getKey()).showBeingAlien(entry.getKey());
				}
			}
			else if (gamestate.givemePlayerByName(playername) instanceof HumanPlayer){
				if(entry.getValue().equals("RMI")){
					givemeNotifierByName(entry.getKey()).showBeingHuman(entry.getKey());
				}
				else {
					givemeSocketHandlerByName(entry.getKey()).showBeingHuman(entry.getKey());
				}
			}
		}
	}
	
	
	
	
	private String personalDeckListify (ArrayList<Card> itemdeck){
	
		//utility for other methods, put in a string all the item cards of a personal deck
		String objects = "";
		for (int i=0; i<itemdeck.size(); i++)
			objects += itemdeck.get(i).getName()+" ;";
		return objects;
	}
	
	
		
	
	private RemoteNotifier givemeNotifierByName (String lookforname) throws RemoteException{
		
		for (RemoteNotifier rn: notifiers.values()){
			if (rn.getName().equals(lookforname)){
				return rn;
			}
		}
		return null;
	}

	
	
	private SocketHandler givemeSocketHandlerByName (String lookforname) throws RemoteException{
		
		for (SocketHandler sh: sockethandlers.values()){
			if (sh.getShName().equals(lookforname)){
				return sh;
			}
		}
		return null;
	}
	
	
	private String positionToString (Player player){
		String position = ""+(char)(player.getPosition().getCoordX()+64);
		String number = ""+ player.getPosition().getCoordY();
		if (number.length() == 1)
			number = "0"+ player.getPosition().getCoordY();
		position += number;
		return position;
	}
	
	
	public GameServer (int t, HashMap<String, String> pc, HashMap<String, RemoteNotifier> rn, HashMap<String, SocketHandler> sh){
		
		this.totalplayers = t;
		this.playersconnected = pc;
		this.notifiers = rn;
		this.sockethandlers = sh;
	}

}
