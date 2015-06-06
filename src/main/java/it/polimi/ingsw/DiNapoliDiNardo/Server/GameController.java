package it.polimi.ingsw.DiNapoliDiNardo.Server;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.LifeboatBox;
import it.polimi.ingsw.DiNapoliDiNardo.model.AlienPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.ItemCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.SectorCard;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;


//avrà fatto sto benedetto merge?
public class GameController {
	int gameId;
	List<String> connectionsClosed = new ArrayList<String>();
	Map<String, String> playersInGame; 
	Map<String, Handler> handlers;
	boolean initError = false;
	boolean finished;
	boolean theLastHumanEscaped;
	GameState gamestate;
	int humanplayers = 0;
	String humanwinners = "";
	String humanlosers = "";
	String alienwinners = "";
	String alienlosers = "";
	private Timer turntimer;
	private static final int FINALTURN = 39;
	private static final int TURNTIME = 3*60*1000;
	
	
	
	public void rungame() throws IOException, ClassNotFoundException{
		
		//first thing check if troubles in initialization reduced the connections to less than two players
		//if so, closes the game
		if (playersInGame.size()<2){
			for (Handler h : handlers.values()){
				h.notifyMessage("Connection problems reduced the number of players under the minimum before the start.");
				h.notifyMessage("This game has been closed, please retry connection again restarting the client.");
			}
			return;
		}
		
		try{
			
			//initialize model main class and update players
			this.gamestate = new GameState(this);
			giveWelcome();
			if (initError)
				return;
			createPlayersInGame(playersInGame);
			informPlayersOfTheirNature();
			
			//manage the normal turn while game isn't finished
			while(!finished){
				iterateATurn();
				removeDeadorEscapedPlayers();
			    if (gamestate.getTurnNumber() == FINALTURN || humanplayers == 0)	
			    	finished = true;
			}
		
			communicateFinalResults();
		
		}
		catch (IOException e){
			//if the exception reaches this try/catch level without have being managed before, it means all the players disconnected
			System.out.println("Apparently we lost connection with all the players of game "+ gameId +".");
			System.out.println("Closing game "+ gameId +".");
			return;
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
			try{
				handlers.get(entry.getKey()).showActualSituation(entry.getKey(), position, objects, String.valueOf(gamestate.getTurnNumber()));
			}
			catch (IOException e){
				manageDisconnection(entry.getKey());
			}
		}
	}
	
	
	
	
	private void askForHumanTurn(String playername) throws ClassNotFoundException, IOException{
		
		Player player = gamestate.givemePlayerByName(playername);
		List<ItemCard> itemdeck = gamestate.givemePlayerByName(playername).getPersonalDeck();
		int index;
		
		//ask for card use only if player has "usable" cards, not if he has the defense card
		if (hasUsableCards(itemdeck)){
			String objects = personalDeckListify(itemdeck);
		
			index = handlers.get(playername).askForItem(objects, false);
				if (index != 8)
					gamestate.itemUsageManagement(playername, index-1);
		}
		
		askForMovement(playername);
		
		if (player.getPosition().isDrawingSectorCardHere() && !player.isSedated())
			drawSectorCard(playername, player);
		
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
		
		if (hasUsableCards(itemdeck)){
			String objects = personalDeckListify(itemdeck);
			index = handlers.get(playername).askForItem(objects, false);
				if (index != 8)
					gamestate.itemUsageManagement(playername, index-1);
		}
		
	}
	
		
	private boolean hasUsableCards(List<ItemCard> itemdeck){
		boolean hasUsables = false;
		for (ItemCard ic : itemdeck){
			if(ic.isActivable()){
				hasUsables = true;
				break;
			}
		}
		return hasUsables;
	}
	
	
	
	private void askForAlienTurn(String playername) throws ClassNotFoundException, IOException{
		
		Player player = gamestate.givemePlayerByName(playername);
		
		askForMovement(playername);
		
		boolean attack = false;
		attack = handlers.get(playername).askForAttack();
		
		if (attack){
			String position = positionToString(player);
			notifyMessageToAll(playername+" has ATTACKED sector "+position);
			gamestate.attackManagement(player);
			
		}
		
		if (player.getPosition().isDrawingSectorCardHere() && !player.isHasAttacked())
			drawSectorCard(playername, player);
		
		
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
			//notify game messages only to in-game players (not dead, not escaped, not removed due to connection problems)
			if (player.isAlive() && !connectionsClosed.contains(entry.getKey())){
				try{
					if (player instanceof HumanPlayer){
						HumanPlayer human = (HumanPlayer) player;
						if (!human.isEscaped())
							handlers.get(entry.getKey()).notifyMessage(message);
					}
					else			
						handlers.get(entry.getKey()).notifyMessage(message);
					}
				catch (IOException e){
					//this could help to identify closed connection in case player left not during his turn
					connectionsClosed.add(entry.getKey());
					manageDisconnection(entry.getKey());
				}
			}
		}
	}
	
	
	
	public void notifyOfEscape (boolean escaped, Player player) throws RemoteException{
		LifeboatBox ship = (LifeboatBox)player.getPosition();
		for (Map.Entry<String, String> entry : playersInGame.entrySet())
			try{
				handlers.get(entry.getKey()).notifyEscape(escaped, player.getName(), String.valueOf(ship.getNumber()));
			}catch (IOException e){
				manageDisconnection(entry.getKey());
			}
	}
	
	
	
	public Coordinates askForLights(String name, boolean reask) throws ClassNotFoundException, IOException{
		Coordinates coordinates;
		coordinates = handlers.get(name).askForLights(reask);
		return coordinates;
	}
	
	
	
	
	public void showLights(String name, String lightposition, String playersinbox) throws ClassNotFoundException, IOException{
		try{
			if ("".equals(playersinbox))
				handlers.get(name).notifyMessage("In the position "+lightposition+" there is no one.");
			else
				handlers.get(name).notifyMessage("In the position "+lightposition+" there are: "+playersinbox);	
		}
		catch (IOException e){
			manageDisconnection(name);
		}
		
	}
	
	
	 
	
	public void cardsMessages(String playername, String cardname, String usemessage) throws ClassNotFoundException, IOException{
		
		//cards that require special treatment 
		if ("AttackCard".equals(cardname)){
			String position = positionToString(gamestate.givemePlayerByName(playername));
			notifyMessageToAll(playername+" has ATTACKED position "+position+" using an Attack Card");
		}
		if (!"DefenseCard".equals(cardname) && !"AttackCard".equals(cardname))	
			notifyMessageToAll(playername+" has used one "+cardname);
		
		//every other card, getting their "usemessage"
		handlers.get(playername).notifyMessage(usemessage);
	}
	
	
	
	
	private void createPlayersInGame(Map<String, String> playersconnected) {
		
		List<String> keys = new ArrayList<String>(playersconnected.keySet());
		//randomly sort the players and then assign aliens and humans on turns starting from the aliens
		Collections.shuffle(keys);
		int i = 0;
		for (String name : keys){
			i++;
			if (i%2 == 1)
				gamestate.getInGamePlayers().add(new AlienPlayer(gamestate.getMap(), gamestate, name));
			else{ 
				gamestate.getInGamePlayers().add(new HumanPlayer(gamestate.getMap(), gamestate, name));
				humanplayers++;
			}
		}
	}

	
	
	
	private void drawSectorCard (String name, Player player) throws ClassNotFoundException, IOException{
		
		SectorCard sectorcard = (SectorCard)gamestate.getSectordeck().drawCard();
		//cards that need the view to be called
		if (sectorcard.requiresViewCall()){
			String noiseIn = "";
			noiseIn = handlers.get(name).askForNoise();
			notifyMessageToAll(name+sectorcard.giveWalkOnMessage()+noiseIn);
		}
		//any other card using superclass methods
		else{
			String position = positionToString(player);
			if (sectorcard.revealsPosition())
				notifyMessageToAll(name+sectorcard.giveWalkOnMessage()+position);
			else
				notifyMessageToAll(name+sectorcard.giveWalkOnMessage());
		}
		//call method to draw item cards if required by sector card
		if (sectorcard.isWithItemType())
			drawItemCard (name, player);
	}

	
	
	
	private void drawItemCard (String name, Player player) throws IOException, ClassNotFoundException{
		
		//manage the personal decks of the players when a new item card is drawn	
		ItemCard itemcard = (ItemCard)gamestate.getItemdeck().drawCard();
		List<ItemCard> itemdeck = gamestate.givemePlayerByName(name).getPersonalDeck();	 
		
		if (itemdeck.size()==3){
			int index;
			String objects = personalDeckListify(itemdeck);
			//if human, ask discard giving the possibility of use immediately a owned card, without giving it if alien
			if (player instanceof HumanPlayer)
				index = handlers.get(name).askHumanForItemChange(objects);
			else
				index = handlers.get(name).askAlienForItemChange(objects);
			//what follows only if player decided to discard or use one of his cards to make space for the new card
			if (index != 8){
				gamestate.itemUsageManagement(name, index-1);
				if (itemdeck.size()<3)
					gamestate.givemePlayerByName(name).getPersonalDeck().add(itemcard);
			}
			//discard the new card if the player doesn't want it
			else{
				gamestate.getItemdeck().getDiscards().add(itemcard);
			}
			
		}		
		
		if (itemdeck.size()<3){
			gamestate.givemePlayerByName(name).getPersonalDeck().add(itemcard);
			handlers.get(name).notifyMessage(name+" you drew one "+itemcard.getName()+".");
		}
		
	}
	
	
	
	
	public void sayByeToLosers(String dead, String killer) throws RemoteException{
		try{
			handlers.get(dead).notifyMessage("-----------------------------------------------");
			handlers.get(dead).notifyMessage(dead+" you've been brutally killed by "+killer);
			handlers.get(dead).notifyMessage("Unfortunately, your game ends here");
			handlers.get(dead).notifyMessage("-----------------------------------------------");
		}
		catch (IOException e){
			manageDisconnection(dead);
		}
		gamestate.getInGamePlayers().remove(dead);
	}
	
	
	
	
	private void giveWelcome() throws IOException{
	
		//print a welcome message and give the list of players to each player.
		String listofplayers = "";
		for (Map.Entry<String, String> entry : playersInGame.entrySet())
			listofplayers += entry.getKey()+", ";
		listofplayers = listofplayers.substring(0, listofplayers.length()-2);
		for (Map.Entry<String, String> entry : playersInGame.entrySet()){
			try{
				handlers.get(entry.getKey()).notifyMessage("Welcome to the game "+ entry.getKey()+". The crew of the infected spaceship is composed by: "+listofplayers+". ");
			}
			catch (IOException e){
				notifyMessageToAll("Players disconnected during the creation of the game. Game will be closed");
				initError = true;
				return;
			}
		}
	}

	
	
	
	private void informPlayersOfTheirNature() throws RemoteException{
		
		//inform players on the nature of their character
		for (Map.Entry<String, String> entry : playersInGame.entrySet()){
			String playername = entry.getKey();
			try{
			if(gamestate.givemePlayerByName(playername) instanceof AlienPlayer)
				handlers.get(entry.getKey()).showBeingAlien(entry.getKey());
			else if (gamestate.givemePlayerByName(playername) instanceof HumanPlayer)
				handlers.get(entry.getKey()).showBeingHuman(entry.getKey());
			}
			catch (IOException e){
				manageDisconnection(entry.getKey());
			}
		}
	}
	
	
	
	private String personalDeckListify (List<ItemCard> itemdeck){
	
		//utility for other methods, put in a string all the item cards of a personal deck
		String objects = "";
		for (int i=0; i<itemdeck.size(); i++)
			objects += itemdeck.get(i).getName()+" ;";
		return objects;
	}
	
	
	
	private void iterateATurn() throws ClassNotFoundException, RemoteException{
		gamestate.increaseTurnNumber();
		showActualSituation ();
		
		
		//turn iteration
		Iterator<Map.Entry<String, String>> it = playersInGame.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	       	Map.Entry<String, String> entry = it.next();
	        String playername = entry.getKey();
	        	        
	    	try {
	    		if (!connectionsClosed.contains(playername))
	    		
	    		
				if(gamestate.givemePlayerByName(playername).isAlive() && !connectionsClosed.contains(playername)){
					//ask player for turn activating and then stopping the timer of his max time
					startTurnTimer(TURNTIME, handlers.get(playername));			
					if(gamestate.givemePlayerByName(playername) instanceof AlienPlayer)
						askForAlienTurn(playername);
						
					else if (gamestate.givemePlayerByName(playername) instanceof HumanPlayer)
						askForHumanTurn(playername);
					stopTurnTimer();
				}
	    	} catch (IOException e) {
	    		manageDisconnection(playername);
			}
	        gamestate.removeInTurnBonus();
	    }
	    
		
	}
	
	
	public void manageDisconnection(String playername) throws RemoteException{
		connectionsClosed.add(playername);
		notifyMessageToAll("Player "+playername+" has disconnected from the game and has been removed. His character will stand still till the end of the game");
	}
	
	
	
	private void removeDeadorEscapedPlayers(){
		//removing dead players iteration
	    Iterator<Map.Entry<String, String>> remover = playersInGame.entrySet().iterator();
	    while (remover.hasNext()) {
	    	
	    	Map.Entry<String, String> entry = remover.next();
	        String playername = entry.getKey();
	        Player maybedead = gamestate.givemePlayerByName(playername);
	        		        	
	        if(!maybedead.isAlive() || maybedead.isEscaped() || connectionsClosed.contains(playername)){
		       remover.remove();
		       if (!maybedead.isAlive() || maybedead.isEscaped())
		    	   maybedead.getPosition().getPlayerHere().remove(maybedead);
		    }
	    }
	}
	
	
	
	private void communicateFinalResults() throws RemoteException{

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
			if(!connectionsClosed.contains(h.getName()))
				h.showFinalResults(iWon, h.getName(), humanlosers, humanwinners, alienwinners, alienlosers);
		}
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
	
	public void startTurnTimer (long turntime, Handler handler){
		this.turntimer = new Timer();
		turntimer.schedule(new DisconnectionHandler(handler) , turntime);
	}
	
	
	public void stopTurnTimer(){
		this.turntimer.cancel();	
		this.turntimer.purge();	
	}
	
	
	public GameController (int id, Map<String, String> pc, Map<String, Handler> hand){
		
		this.gameId = id;
		this.playersInGame = pc;
		this.handlers = hand;
	}

}
