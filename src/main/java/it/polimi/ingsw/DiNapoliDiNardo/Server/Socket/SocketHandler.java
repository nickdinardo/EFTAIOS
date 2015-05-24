package it.polimi.ingsw.DiNapoliDiNardo.Server.Socket;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketHandler extends Thread{
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	ObjectOutputStream outObj; 
    ObjectInputStream inObj;
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
			outObj = new ObjectOutputStream(socket.getOutputStream());
		    inObj = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}

	public String askName() throws IOException{
		out.println("object=player&action=askname");
		this.name = in.readLine();
		return(name);
	}
		
	public void showBeingAlien (String name){
		out.println("object="+name+"&action=beingAlien");
	}
	public void showBeingHuman (String name){
		out.println("object="+name+"&action=beingHuman");
	}
	public void showActualSituation (String name, String position, String objects){
		out.println("object=situation&action="+name+";"+position+";"+objects+";");
	}
	
	public String askForNoise() throws ClassNotFoundException, IOException{
		out.println("object=player&action=asknoise");
		String noise = in.readLine();
		return (noise);
	}
	
	public Coordinates askForMovement(boolean reask) throws IOException, ClassNotFoundException{
		if(!reask)
			out.println("object=player&action=askmovement");
		else
			out.println("object=player&action=reaskmovement");
		Coordinates coord = (Coordinates)inObj.readObject();
		return (coord);
	}
	
	public Coordinates askForLights() throws IOException, ClassNotFoundException{
		out.println("object=player&action=asklights");
		Coordinates coord = (Coordinates)inObj.readObject();
		return (coord);
	}
	
	
	public int askForItem(String objects) throws IOException{
		out.println("object=playeritems&action="+objects);
		int index = in.read()-48;
		in.readLine();
		return index;
	}
	
	
	public int askHumanForItemChange(String objects) throws IOException{
		out.println("object=humanplayeritemsdiscard&action="+objects);
		int index = in.read()-48;
		in.readLine();
		if (index == 9)
			index = askForItem(objects);
		return index;
	}
	
	public int askAlienForItemChange(String objects) throws IOException{
		out.println("object=alienplayeritemsdiscard&action="+objects);
		int index = in.read()-48;
		in.readLine();
		return index;
	}
	
	
	
	public void printWelcomeMessage(String name, String list) throws IOException{
		out.println("object=print&action=Welcome to the game "+name+". The crew of the infected spaceship is composed by: "+list+". ");
		
	}
	
	public void notifyMessage(String message){
		out.println("object=print&action="+message);
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
