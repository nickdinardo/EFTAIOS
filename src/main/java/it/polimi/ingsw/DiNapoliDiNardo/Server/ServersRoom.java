package it.polimi.ingsw.DiNapoliDiNardo.Server;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class ServersRoom {

	private PrintStream out = System.out;
	private List<Server> games = new ArrayList<Server>();
	private ExecutorService executorGames;
	private int gameId;

	public static void main(String[] args){
		ServersRoom serverRoom = new ServersRoom();
		serverRoom.startServer();	
	}
	
	
	public void startServer(){
		
		gameId = 1;
		while(true){
			
			boolean gameStarted = false;
			Server server = new Server(gameId);
			out.println("ServersRoom is open: starting match "+gameId);
			executorGames = Executors.newCachedThreadPool();
			executorGames.submit(server);
			
			String dontchurn="";
			do{
				dontchurn += "avoided";
				if (dontchurn.length()>1000)
					dontchurn = "";
				gameStarted = server.isStarted();
			}while(!gameStarted);
			
			out.println("Game "+gameId+" has started, opening a new Server Thread");
			gameId++;
			games.add(server);
			
		}
	}	


}
















