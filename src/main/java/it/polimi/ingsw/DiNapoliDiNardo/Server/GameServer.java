package it.polimi.ingsw.DiNapoliDiNardo.Server;

import it.polimi.ingsw.DiNapoliDiNardo.Server.Socket.SocketHandler;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteNotifier;
import it.polimi.ingsw.DiNapoliDiNardo.model.AlienPlayer;
import it.polimi.ingsw.DiNapoliDiNardo.model.GameState;
import it.polimi.ingsw.DiNapoliDiNardo.model.HumanPlayer;

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
	
	public void rungame() throws IOException{
		
		
		giveWelcome();
		this.gamestate = new GameState(this);
		createPlayersInGame(playersconnected);
		
		
		
		
		
		
		
		
		
		while(!finish){
			//corpo partita
			}
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



	public void giveWelcome() throws IOException{
		//print a welcome message and give the list of players to each player.
				String listofplayers = "";
				for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
					listofplayers += entry.getKey()+", ";
				}
				listofplayers = listofplayers.substring(0, listofplayers.length()-2);
				for (HashMap.Entry<String, String> entry : playersconnected.entrySet()){
					if(entry.getValue().equals("RMI")){
						givemeNotifierByName(entry.getKey()).notifyMessage("Welcome to the game "+ entry.getKey()+". The crew of the infected spaceship is composed by: "+listofplayers+". Good luck.");
					}
					else {
						givemeSocketHandlerByName(entry.getKey()).printWelcomeMessage(entry.getKey(), listofplayers);
					}
				}
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
