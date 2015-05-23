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
		//askForMovement();

		while(!finish){
			//non va bene qui show
			showActualSituation ();
			for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
				String playername = entry.getKey();
				
				if(gamestate.givemePlayerByName(playername) instanceof AlienPlayer){
					askForAlienTurn(playername, entry.getValue());
				}
				else if (gamestate.givemePlayerByName(playername) instanceof HumanPlayer){
					askForHumanTurn(playername, entry.getValue());
				}
			}
		}
		
	
	
	
	}
	
	
	
	
	public void showActualSituation () throws RemoteException{
		for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
			Player player;
			player = gamestate.givemePlayerByName(entry.getKey());
			String position = ""+(char)(player.getPosition().getCoordX()+64)+player.getPosition().getCoordY();	
			String objects = personalDeckListify(player);			
			if(entry.getValue().equals("RMI"))
				givemeNotifierByName(entry.getKey()).showActualSituation(entry.getKey(), position, objects);
			else{
				givemeSocketHandlerByName(entry.getKey()).showActualSituation(entry.getKey(), position, objects);
				
			}
				
		}
	}
	
	
	//usare index in gamestate per gli items e fare i metodi item usage RMI
	public void askForHumanTurn(String playername, String connection) throws ClassNotFoundException, IOException{
		HumanPlayer player = (HumanPlayer)gamestate.givemePlayerByName(playername);
		//item usage phase 1
		if (gamestate.givemePlayerByName(playername).getPersonalDeck().size()>0){
			String objects = personalDeckListify(gamestate.givemePlayerByName(playername))+",";
			//if(connection.equals("RMI"))
				//givemeNotifierByName(playername).askForItem();
			//else
				int index = givemeSocketHandlerByName(playername).askForItem(objects);
		}
		//movement
		askForMovement(playername, connection);
		//draw a sector card in dangerous box and manage the consequences
		if (player.getPosition() instanceof DangerousBox && !player.isSedated())
			drawSectorCard(playername, connection, player);
		//item usage phase 2
		if (gamestate.givemePlayerByName(playername).getPersonalDeck().size()>0){
			String objects = personalDeckListify(gamestate.givemePlayerByName(playername))+",";
			//if(connection.equals("RMI"))
				//givemeNotifierByName(playername).askForItem();
			//else
				int index = givemeSocketHandlerByName(playername).askForItem(objects);
		}
	}
	
		
		
	public void askForAlienTurn(String playername, String connection) throws ClassNotFoundException, IOException{
		AlienPlayer player = (AlienPlayer)gamestate.givemePlayerByName(playername);
		//movement
		askForMovement(playername, connection);
		//draw a sector card in dangerous box and manage the consequences
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

	//da inserire poi la pesca degli oggetti
	private void drawSectorCard (String name, String connection, Player player) throws ClassNotFoundException, IOException{
		
		Card sectorcard;
		//metti lo zero davanti se è <10
		String position = ""+(char)(player.getPosition().getCoordX()+64)+player.getPosition().getCoordY();	
		sectorcard = gamestate.getSectordeck().drawCard();
		if (sectorcard instanceof SilenceCard){
			for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
				if(entry.getValue().equals("RMI"))
					givemeNotifierByName(entry.getKey()).notifyMessage(name+" declares: SILENCE.");
				else
					givemeSocketHandlerByName(entry.getKey()).notifyMessage(name+" declares: SILENCE.");
			}
		}
		if (sectorcard instanceof NoiseHereCard){
			for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
				if(entry.getValue().equals("RMI"))
					givemeNotifierByName(entry.getKey()).notifyMessage(name+" declares: NOISE in sector "+position+".");
				else
					givemeSocketHandlerByName(entry.getKey()).notifyMessage(name+" declares: NOISE in sector "+position+".");
			}
		}
		if (sectorcard instanceof NoiseAnywhereCard){
			String noiseIn = "";
			if(connection.equals("RMI"))
				noiseIn = givemeNotifierByName(name).askForNoise();
			else
				noiseIn = givemeSocketHandlerByName(name).askForNoise();
			for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
				if(entry.getValue().equals("RMI"))
					givemeNotifierByName(entry.getKey()).notifyMessage(name+" declares: NOISE in sector "+noiseIn+".");
				else
					givemeSocketHandlerByName(entry.getKey()).notifyMessage(name+" declares: NOISE in sector "+noiseIn+".");
			}
		}
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
		//Inform players on the nature of their character
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
	
	
	public String personalDeckListify (Player player){
		String objects = "";
		for (Card item : player.getPersonalDeck()){
			if (item instanceof TeleportCard)
				objects += "TeleportCard, ";
			if (item instanceof AttackCard)
				objects += "AttackCard, ";
			if (item instanceof AdrenalineCard)
				objects += "AdrenalineCard, ";
			if (item instanceof SedativesCard)
				objects += "SedativesCard, ";
			if (item instanceof LightsCard)
				objects += "LightsCard, ";
			objects = objects.substring(0, objects.length()-2);
		}
		if (objects.length()<2)
			objects = "no";
		return objects;
	}
	
	
	public RemoteNotifier givemeNotifierByName (String lookforname) throws RemoteException{
		for (RemoteNotifier rn: notifiers.values()){
			if (rn.getName().equals(lookforname)){
				return rn;
			}
		}
		return null;
	}

	public SocketHandler givemeSocketHandlerByName (String lookforname) throws RemoteException{
		for (SocketHandler sh: sockethandlers.values()){
			if (sh.getShName().equals(lookforname)){
				return sh;
			}
		}
		return null;
	}
	
		
	public GameServer (int t, HashMap<String, String> pc, HashMap<String, RemoteNotifier> rn, HashMap<String, SocketHandler> sh){
		this.totalplayers = t;
		this.playersconnected = pc;
		this.notifiers = rn;
		this.sockethandlers = sh;
	}

}
