package it.polimi.ingsw.dinapolidinardo.server.socket;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.server.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;


/**
 * Server side of the Socket communication.
 * <p>
 * Create and codify the command Strings that will be decoded by the Client Command Handler,
 * that will call the appropriate view methods depending on their contents.
 * 
 * @see it.polimi.ingsw.dinapolidinardo.client.CommandHandler
 */
public class SocketHandler implements Handler, Runnable {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	ObjectOutputStream outObj; 
    ObjectInputStream inObj;
    private PrintStream console = System.out;
	private String name = "undefined357";
	

	//constructor
	public SocketHandler(Socket socket) {
		super();
		this.socket = socket;
		
	}
	
	
	/**
	 * Initialize the four socket streams
	 */
	@Override
	public void run() {
		try{
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outObj = new ObjectOutputStream(socket.getOutputStream());
		    inObj = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ex){
			console.println("Error while initializing socket");
			closeConnections();
		}
	}

	
	/**
	 * Close the four socket streams and the main socket
	 */
	@Override
	public void closeConnections() {
		
		out.close();
		try {
			in.close();
			outObj.close();
			inObj.close();
			socket.close();
		} catch (IOException e) {
			console.println("Error while closing socket");
			return;
		}
		
		
	}

		
	/**
	 * Ask user to input name and returns to server side an "invalid code" 
	 * if name is not valid
	 * 
	 * @param names the names already present in the game
	 * @param reask signals if it's the first time that ask the name
	 * @return the name String or an invalid code String
	 * @throws IOException if can't reach the server side
	 */
	public String askName(List<String> names, boolean reask) throws IOException{
		if(!reask){
			out.println("object=player&action=askname");
			this.name = in.readLine();
		}
		else{
			out.println("object=player&action=reaskname");
			this.name = in.readLine();
		}
		if (names.contains(name) || "".equals(name)){
			name = "namenotvalid1765";
		}	
		return name;
	}
	
	
	
	//methods implemented from Handler interface
	
	
	@Override
	public void showBeingAlien (String name){
		out.println("object="+name+"&action=beingAlien");
	}
	@Override
	public void showBeingHuman (String name){
		out.println("object="+name+"&action=beingHuman");
	}
	@Override
	public void showActualSituation (String name, String position, String objects, String turn){
		out.println("object=situation&action="+name+";"+position+";"+objects+";"+turn+";");
	}
	
	@Override
	public void updateView(String position, String reachables, String objects) throws IOException{
		out.println("object=update&action="+position+";"+reachables+";"+objects+";");
	}
		
	@Override
	public boolean askForAttack() throws ClassNotFoundException, IOException{
		out.println("object=player&action=askattack");
		String answer = in.readLine();
		boolean wantattack = false;
		if ("Y".equalsIgnoreCase(answer))
			wantattack = true;
		else 
			wantattack = false;
		return wantattack;
	}
	
	@Override
	public String askForNoise() throws ClassNotFoundException, IOException{
		out.println("object=player&action=asknoise");
		return in.readLine();
	}
	
	@Override
	public Coordinates askForMovement(boolean reask) throws IOException, ClassNotFoundException{
		if(!reask)
			out.println("object=player&action=askmovement");
		else
			out.println("object=player&action=reaskmovement");
		return (Coordinates)inObj.readObject();
	}
	
	@Override
	public Coordinates askForLights(boolean reask) throws IOException, ClassNotFoundException{
		if(!reask)
			out.println("object=player&action=asklights");
		else
			out.println("object=player&action=reasklights");
		return (Coordinates)inObj.readObject();
	}
	
	@Override
	public int askForItem(String objects, boolean fromDiscardCall) throws IOException{
		if (fromDiscardCall)
			out.println("object=playeritemsY&action="+objects);
		else
			out.println("object=playeritemsN&action="+objects);
		//converts to int the char numeric input
		int index = in.read()-48;
		in.readLine();
		return index;
	}
	
	@Override
	public int askHumanForItemChange(String objects) throws IOException{
		out.println("object=humanplayeritemsdiscard&action="+objects);
		//converts to int the char numeric input
		int index = in.read()-48;
		in.readLine();
		if (index == 9)
			index = askForItem(objects, true);
		return index;
	}
	
	@Override
	public int askAlienForItemChange(String objects) throws IOException{
		out.println("object=alienplayeritemsdiscard&action="+objects);
		//converts to int the char numeric input
		int index = in.read()-48;
		in.readLine();
		return index;
	}
	
	@Override
	public void notifyMessage(String message){
		out.println("object=print&action="+message);
	}
	

	@Override
	public void notifyEscape(boolean escaped, String name, String shipnumber){
		if (escaped)
			out.println("object=escape&action="+name+shipnumber);
		else
			out.println("object=escapefailed&action="+name+shipnumber);
	}
	
	@Override
	public void showFinalResults(boolean iWon, String name, String humanlosers, String humanwinners, String alienwinners, String alienlosers) throws RemoteException {
		if (iWon)
			out.println("object=results&action="+name+";"+humanlosers+";"+humanwinners+";"+alienwinners+";"+alienlosers+";"+"y;");
		else
			out.println("object=results&action="+name+";"+humanlosers+";"+humanwinners+";"+alienwinners+";"+alienlosers+";"+"n;");
	}
	
	@Override
	public void signalEndOfTurn() throws RemoteException {
		out.println("object=endturn&action=endturn");
	}


	
	//getters and setters
	@Override
	public String getName() {
		return this.name;
		
	}

		
	public Socket getSocket() {
		return socket;
	}

		

}
