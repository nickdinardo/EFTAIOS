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

public class SocketHandler implements Handler, Runnable {
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

	
	public String askName() throws IOException{
		out.println("object=player&action=askname");
		this.name = in.readLine();
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
	public Coordinates askForLights() throws IOException, ClassNotFoundException{
		out.println("object=player&action=asklights");
		Coordinates coord = (Coordinates)inObj.readObject();
		return coord;
	}
	
	@Override
	public int askForItem(String objects) throws IOException{
		out.println("object=playeritems&action="+objects);
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
			index = askForItem(objects);
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
