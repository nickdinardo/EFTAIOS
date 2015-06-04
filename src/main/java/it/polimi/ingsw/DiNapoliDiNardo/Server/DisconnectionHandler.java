package it.polimi.ingsw.DiNapoliDiNardo.Server;

import java.rmi.RemoteException;
import java.util.TimerTask;

public class DisconnectionHandler extends TimerTask {
	GameServer gameserver;
	String playername;
	
	public DisconnectionHandler(GameServer gs, String name){
		this.gameserver = gs;
		this.playername = name;
	}
	
		public void run(){
			try {
				gameserver.manageDisconnection(playername);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    
}
