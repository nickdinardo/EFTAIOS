package it.polimi.ingsw.DiNapoliDiNardo.Server;

import java.io.IOException;
import java.util.TimerTask;

public class DisconnectionHandler extends TimerTask {
	Handler handler;
	
	
	public DisconnectionHandler(Handler hnd){
		this.handler = hnd;
	}
	
		public void run(){
			try {
				handler.closeConnections();
				
			} catch (IOException e) {
				//exception of this handler is already managed in the main GameServer Thread
				//so there's no need to manage the return exception in this Timer Thread, that will be closed immediately
			}
		}
    
}
