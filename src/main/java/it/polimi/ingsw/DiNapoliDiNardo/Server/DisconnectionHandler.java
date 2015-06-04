package it.polimi.ingsw.DiNapoliDiNardo.Server;

import java.rmi.RemoteException;
import java.util.TimerTask;

public class DisconnectionHandler extends TimerTask {
	Handler handler;
	String playername;
	
	public DisconnectionHandler(Handler hnd, String name){
		this.handler = hnd;
		this.playername = name;
	}
	
		public void run(){
			try {
				handler.closeConnections();
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    
}
