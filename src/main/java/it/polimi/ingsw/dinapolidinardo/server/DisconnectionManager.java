package it.polimi.ingsw.dinapolidinardo.server;

import java.io.IOException;
import java.util.TimerTask;


/**
 * Class scheduled by the turntimer of Game Controller, an object
 * is constructed when the timer elapses, and closes the connections
 * with the unanswering client
 */
public class DisconnectionManager extends TimerTask {
	private Handler handler;
	
	/**
	 * @param hnd the handler of the user whose time elapsed
	 */
	public DisconnectionManager(Handler hnd){
		this.handler = hnd;
	}
	
	
		/**
		 * Closes the connections with client
		 */
		@Override
		public void run(){
			try {
				handler.closeConnections();
				
			} catch (IOException e) {
				//exception of this handler is already managed in the main GameServer Thread
				//so there's no need to manage the return exception in this Timer Thread, that will be closed immediately
			}
		}
    
}
