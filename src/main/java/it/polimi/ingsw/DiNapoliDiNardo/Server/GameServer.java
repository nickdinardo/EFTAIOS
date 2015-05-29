package it.polimi.ingsw.DiNapoliDiNardo.Server;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.LifeboatBox;
import it.polimi.ingsw.DiNapoliDiNardo.model.AlienPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;


import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;





public class GameServer {
	int totalplayers;
	Map<String, String> playersInGame; 
	Map<String, Handler> handlers;
	boolean finished;
	boolean theLastHumanEscaped;
	GameState gamestate;
	int humanplayers = 0;
	String humanwinners = "";
	String humanlosers = "";
	String alienwinners = "";
	String alienlosers = "";
	private static final int FINALTURN = 39;
	
	
	public void rungame() throws IOException, ClassNotFoundException{
		
		
		giveWelcome();
		this.gamestate = new GameState(this);
		createPlayersInGame(playersInGame);
		informPlayersOfTheirNature();
		

		while(!finished){
			
			gamestate.increaseTurnNumber();
			showActualSituation ();
			
			//turn iteration
			Iterator<Map.Entry<String, String>> it = playersInGame.entrySet().iterator();
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
		    Iterator<Map.Entry<String, String>> remover = playersInGame.entrySet().iterator();
		    while (remover.hasNext()) {
		    	
		    	Map.Entry<String, String> entry = remover.next();
		        String playername = entry.getKey();
		        Player maybedead = gamestate.givemePlayerByName(playername);
		        		        	
		        if(!maybedead.isAlive() || maybedead.isEscaped()){
			       remover.remove();
			       maybedead.getPosition().getPlayerHere().remove(maybedead);
			    }
		   
		    }
		    
		    if (gamestate.getTurnNumber() == FINALTURN || humanplayers == 0)	
		    	finished = true;
		}
	
		
		if(gamestate.getTurnNumber() == FINALTURN){
			notifyMessageToAll("---THE FINAL TURN HAS ENDED---");
		}
		else{
			if (theLastHumanEscaped)
				notifyMessageToAll("The last human on the ship managed to escape!");
			else
				notifyMessageToAll("The last human on the ship has been killed by the aliens!");
		}
		
		fillStringsWithFinalResults();
				
		//notify to all players, even the ones removed from the game, the final results of the match
		for (Handler h : handlers.values()){
			boolean iWon = true;
			if (gamestate.getLosers().contains(h.getName()))
				iWon = false;
			if (alienwinners.isEmpty() && gamestate.givemePlayerByName(h.getName()) instanceof AlienPlayer)
				iWon = false;
			h.showFinalResults(iWon, h.getName(), humanlosers, humanwinners, alienwinners, alienlosers);
		}
		
			
	
	
	}	
	
	
	
	
	
	
	private void showActualSituation () throws RemoteException{
		
		for (Map.Entry<String, String> entry : playersInGame.entrySet()){
		
			Player player;
			player = gamestate.givemePlayerByName(entry.getKey());
			List<ItemCard> itemdeck = player.getPersonalDeck();
			String position = ""+(char)(player.getPosition().getCoordX()+64)+player.getPosition().getCoordY();	
			String objects = "";
			for (int i=0; i<itemdeck.size(); i++)
				objects += itemdeck.get(i).getName()+" ";		
			if (objects.length()<2)
				objects = "no";
			handlers.get(entry.getKey()).showActualSituation(entry.getKey(), position, objects, String.valueOf(gamestate.getTurnNumber()));
		}
	}
	
	
	
	
	private void askForHumanTurn(String playername, String connection) throws ClassNotFoundException, IOException{
		
		HumanPlayer player = (HumanPlayer)gamestate.givemePlayerByName(playername);
		List<ItemCard> itemdeck = gamestate.givemePlayerByName(playername).getPersonalDeck();
		int index;
		
		List<ItemCard> personaldeck = gamestate.givemePlayerByName(playername).getPersonalDeck();
		if (!personaldeck.isEmpty() && !(personaldeck.size() == 1 && personaldeck.get(0) instanceof DefenseCard)){
			String objects = personalDeckListify(itemdeck);
		
			index = handlers.get(playername).askForItem(objects);
				if (index != 8)
					gamestate.itemUsageManagement(playername, index-1);
		}
		
		askForMovement(playername);
		
		if (player.getPosition().isDrawingSectorCardHere() && !player.isSedated())
			drawSectorCard(playername, connection, player);
		
		//check for possible escape if on escape-box
		boolean escaped;
		if (player.getPosition().isLifeBoatShipHere()){
			escaped = gamestate.escapeManagement(player);
			notifyOfEscape(escaped, player);
			if (escaped){
				decreaseHumansAndCheck(player);
				return;
			}
				
		}
		
		
		if (!personaldeck.isEmpty() && !(personaldeck.size() == 1 && personaldeck.get(0) instanceof DefenseCard)){
			String objects = personalDeckListify(itemdeck);
			index = handlers.get(playername).askForItem(objects);
				if (index != 8)
					gamestate.itemUsageManagement(playername, index-1);
		}
	}
	
		
	
	
	private void askForAlienTurn(String playername, String connection) throws ClassNotFoundException, IOException{
		
		AlienPlayer player = (AlienPlayer)gamestate.givemePlayerByName(playername);
		
		askForMovement(playername);
		boolean attack = false;
		attack = handlers.get(playername).askForAttack();
		
		if (attack){
			String position = positionToString(player);
			notifyMessageToAll(playername+" has ATTACKED sector "+position);
			gamestate.attackManagement(player);
			
		}
		if (player.getPosition().isDrawingSectorCardHere() && !player.isHasAttacked())
			drawSectorCard(playername, connection, player);
	}
	
	
	
	
	private void askForMovement(String playername) throws ClassNotFoundException, IOException{
		
		Coordinates coordinates;
		boolean validmove = false;
		coordinates = handlers.get(playername).askForMovement(false);
		do{
			validmove = gamestate.updatePlayerPosition(playername, coordinates);
			if(!validmove)
				coordinates = handlers.get(playername).askForMovement(true);
		}while(!validmove);
	}

	
	
	public void notifyMessageToAll(String message) throws RemoteException{
		
		for (Map.Entry<String, String> entry : playersInGame.entrySet()){
			Player player = gamestate.givemePlayerByName(entry.getKey());
			if (player.isAlive()){
				if (player instanceof AlienPlayer)
					handlers.get(entry.getKey()).notifyMessage(message);
				else{
					HumanPlayer human = (HumanPlayer) player;
					if (!human.isEscaped())
						handlers.get(entry.getKey()).notifyMessage(message);
				}
			}
		}
	}
	
	
	
	public void notifyOfEscape (boolean escaped, Player player) throws RemoteException{
		LifeboatBox ship = (LifeboatBox)player.getPosition();
		for (Map.Entry<String, String> entry : playersInGame.entrySet())
			handlers.get(entry.getKey()).notifyEscape(escaped, player.getName(), String.valueOf(ship.getNumber()));
	}
	
	
	
	public Coordinates askForLights(String name) throws ClassNotFoundException, IOException{
		Coordinates coordinates;
		coordinates = handlers.get(name).askForLights();
		return coordinates;
	}
	
	
	
	
	public void showLights(String name, String lightposition, String playersinbox) throws ClassNotFoundException, IOException{
		if ("".equals(playersinbox))
			handlers.get(name).notifyMessage("In the position "+lightposition+" there is no one.");
		else
			handlers.get(name).notifyMessage("In the position "+lightposition+" there are: "+playersinbox);	
	}
	
	
	 
	
	public void cardsMessages(String playername, String cardname, String usemessage) throws ClassNotFoundException, IOException{
		
		if ("AttackCard".equals(cardname)){
			String position = positionToString(gamestate.givemePlayerByName(playername));
			notifyMessageToAll(playername+" has ATTACKED position "+position+" using an Attack Card");
		}
		if (!"DefenseCard".equals(cardname))	
			notifyMessageToAll(playername+" has used one "+cardname);
		
		handlers.get(playername).notifyMessage(usemessage);
	}
	
	
	
	
	private void createPlayersInGame(Map<String, String> playersconnected) {
		List<String> keys = new ArrayList<String>(playersconnected.keySet());
		Collections.shuffle(keys);
		int i = 0;
		for (String name : keys){
			i++;
			if (i%2 == 1)
				gamestate.getInGamePlayers().add(new AlienPlayer(gamestate.getGalilei(), gamestate, name));
			else{ 
				gamestate.getInGamePlayers().add(new HumanPlayer(gamestate.getGalilei(), gamestate, name));
				humanplayers++;
			}
		}
	}

	
	
	
	private void drawSectorCard (String name, String connection, Player player) throws ClassNotFoundException, IOException{
		
		Card sectorcard = gamestate.getSectordeck().drawCard();
		String position = positionToString(player);
				
		if (sectorcard instanceof SilenceCard){
			notifyMessageToAll(name+" declares: SILENCE.");
		}
		if (sectorcard instanceof NoiseHereCard){
			notifyMessageToAll(name+" declares: NOISE in sector "+position+".");
		}
		if (sectorcard instanceof NoiseAnywhereCard){
			String noiseIn = "";
			noiseIn = handlers.get(name).askForNoise();
			notifyMessageToAll(name+" declares: NOISE in sector "+noiseIn+".");
		}
		
		//call method to draw item cards if required by sector card
		if (sectorcard instanceof NoiseAnywhereCardPlusItem || sectorcard instanceof NoiseHereCardPlusItem)
			drawItemCard (name, connection, player);
	}

	
	
	
	private void drawItemCard (String name, String connection, Player player) throws IOException, ClassNotFoundException{
		
		//manage the personal decks of the players when a new item card is drawn	
		ItemCard itemcard = (ItemCard)gamestate.getItemdeck().drawCard();
		List<ItemCard> itemdeck = gamestate.givemePlayerByName(name).getPersonalDeck();	 
		
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
		for (Map.Entry<String, String> entry : playersInGame.entrySet())
			listofplayers += entry.getKey()+", ";
		listofplayers = listofplayers.substring(0, listofplayers.length()-2);
		for (Map.Entry<String, String> entry : playersInGame.entrySet())
			handlers.get(entry.getKey()).notifyMessage("Welcome to the game "+ entry.getKey()+". The crew of the infected spaceship is composed by: "+listofplayers+". ");
		
	}

	
	
	
	private void informPlayersOfTheirNature() throws RemoteException{
		
		//inform players on the nature of their character
		for (Map.Entry<String, String> entry : playersInGame.entrySet()){
			String playername = entry.getKey();
			if(gamestate.givemePlayerByName(playername) instanceof AlienPlayer)
				handlers.get(entry.getKey()).showBeingAlien(entry.getKey());
			else if (gamestate.givemePlayerByName(playername) instanceof HumanPlayer)
				handlers.get(entry.getKey()).showBeingHuman(entry.getKey());
		
		}
	}
	
	
	
	
	private String personalDeckListify (List<ItemCard> itemdeck){
	
		//utility for other methods, put in a string all the item cards of a personal deck
		String objects = "";
		for (int i=0; i<itemdeck.size(); i++)
			objects += itemdeck.get(i).getName()+" ;";
		return objects;
	}
	
	
	private void fillStringsWithFinalResults(){
		String hwbuild = "";
		String hlbuild = "";
		String awbuild = "";
		String albuild = "";
		
		for (String name : gamestate.getWinners())
			hwbuild += name+", ";;
		if (hwbuild.length()>2)	
			humanwinners = hwbuild.substring(0, hwbuild.length()-2);
		
		for (String name : gamestate.getLosers())
			hlbuild += name+", ";
		if (hlbuild.length()>2)	
			humanlosers = hlbuild.substring(0, hlbuild.length()-2);	
		
		if (theLastHumanEscaped){
			for (String name : handlers.keySet())
				if (gamestate.givemePlayerByName(name) instanceof AlienPlayer)
					albuild += name+", ";
			if (albuild.length()>2)
				alienlosers = albuild.substring(0, albuild.length()-2);	
		}
		else{
			for (String name : handlers.keySet())
				if (gamestate.givemePlayerByName(name) instanceof AlienPlayer)
					awbuild += name+", ";
			if (awbuild.length()>2)
				alienwinners = awbuild.substring(0, awbuild.length()-2);	
		}
	}
		
	
	
	private String positionToString (Player player){
		String position = ""+(char)(player.getPosition().getCoordX()+64);
		String number = ""+ player.getPosition().getCoordY();
		if (number.length() == 1)
			number = "0"+ player.getPosition().getCoordY();
		position += number;
		return position;
	}
	
	
	//when last human is removed from game check if is escaped or killed to see if Alien team wins
	public void decreaseHumansAndCheck (Player player){
		this.humanplayers--;
		if (humanplayers == 0){
			if (player.isAlive())
				theLastHumanEscaped = true;
			else
				theLastHumanEscaped = false;
		}
			
		
	}
	
	
	public GameServer (int t, Map<String, String> pc, Map<String, Handler> hand){
		
		this.totalplayers = t;
		this.playersInGame = pc;
		this.handlers = hand;
	}

}
