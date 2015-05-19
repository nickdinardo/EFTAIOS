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

	
	public void askForMovement() throws IOException{
		out.println("object=player&action=askmovement");
		System.out.println(in.readLine());
	}
	
	
	
	
	
	public void Close() throws IOException{
		
		in.close();
		out.close();
		socket.close();
	}
	
	
}
