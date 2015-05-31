package it.polimi.ingsw.DiNapoliDiNardo.Server.Socket;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.Server.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;


public class SocketHandler implements Handler, Runnable {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	ObjectOutputStream outObj; 
    ObjectInputStream inObj;
	private String name = "undefined357";


	public SocketHandler(Socket socket) {
		super();
		this.socket = socket;
		
	}

	public Socket getSocket() {
		return socket;
	}

	@Override
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
		String noise = in.readLine();
		return noise;
	}
	
	@Override
	public Coordinates askForMovement(boolean reask) throws IOException, ClassNotFoundException{
		if(!reask)
			out.println("object=player&action=askmovement");
		else
			out.println("object=player&action=reaskmovement");
		Coordinates coord = (Coordinates)inObj.readObject();
		return coord;
	}
	
	@Override
	public Coordinates askForLights(boolean reask) throws IOException, ClassNotFoundException{
		if(!reask)
			out.println("object=player&action=asklights");
		else
			out.println("object=player&action=reasklights");
		Coordinates coord = (Coordinates)inObj.readObject();
		return coord;
	}
	
	@Override
	public int askForItem(String objects, boolean fromDiscardCall) throws IOException{
		if (fromDiscardCall)
			out.println("object=playeritemsY&action="+objects);
		else
			out.println("object=playeritemsN&action="+objects);
		int index = in.read()-48;
		in.readLine();
		return index;
	}
	
	@Override
	public int askHumanForItemChange(String objects) throws IOException{
		out.println("object=humanplayeritemsdiscard&action="+objects);
		int index = in.read()-48;
		in.readLine();
		if (index == 9)
			index = askForItem(objects, true);
		return index;
	}
	
	@Override
	public int askAlienForItemChange(String objects) throws IOException{
		out.println("object=alienplayeritemsdiscard&action="+objects);
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
	public String getName() {
		return this.name;
		
	}

	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;
	}
	

	public String getShName(){
		return this.name;
	}
	
	
	public void close() throws IOException{
		
		in.close();
		out.close();
		socket.close();
	}

	


}
