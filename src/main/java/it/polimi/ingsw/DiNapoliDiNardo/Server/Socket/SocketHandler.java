package it.polimi.ingsw.DiNapoliDiNardo.Server.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketHandler extends Thread{
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private String name = "";


	public SocketHandler(Socket socket) {
		super();
		this.socket = socket;
		
	}

	public Socket getSocket() {
		return socket;
	}

	public void run() {
		try{
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}

	public String askName() throws IOException{
		out.println("object=player&action=askname");
		this.name = in.readLine();
		return(name);
	}
	
	
	
	
	public void askForMovement() throws IOException{
		out.println("object=player&action=askmovement");
		System.out.println(in.readLine());
	}
	
	public void printWelcomeMessage(String name, String list) throws IOException{
		out.println("object=print&action=Welcome to the game "+name+". The crew of the infected spaceship is composed by: "+list+". Good luck.");
		System.out.println(in.readLine());
	}
	
	
	public String getShName(){
		return this.name;
	}
	
	public void Close() throws IOException{
		
		in.close();
		out.close();
		socket.close();
	}
	
	
}
