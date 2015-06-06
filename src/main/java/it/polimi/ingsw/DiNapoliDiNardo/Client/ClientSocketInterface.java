package it.polimi.ingsw.DiNapoliDiNardo.Client;

import it.polimi.ingsw.DiNapoliDiNardo.view.View;
import it.polimi.ingsw.DiNapoliDiNardo.view.ViewFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ClientSocketInterface implements NetworkInterface {

	private PrintStream console = System.out;
	private Socket s;
	private static final String LOCAL = "127.0.0.1";
	private View view;
	private PrintWriter out;
	private BufferedReader in; 
	ObjectOutputStream outObj;
	ObjectInputStream inObj;
	boolean stop = false;
	private CommandHandler comhan;
	
	public ClientSocketInterface() {
		
	} 
	
	@Override
	public boolean connect() throws IOException {
		try {
			s = new Socket(LOCAL, 8888);
		} catch (UnknownHostException e) {
			console.println("At this remote address there's actually no game server.");
			return false;
		} catch (IOException e) {
			console.println("Remote initializers not more avaible. Server is not accepting further players connections.");
			return false;
		}
		
		try {
			outObj = new ObjectOutputStream(s.getOutputStream());
			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {			
			s.close();
			return false;
		}
		try {
			inObj = new ObjectInputStream(s.getInputStream());
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {			
			out.close();
			s.close();
			return false;
		}
		return true;
	}

	
	@Override
	public void startInterface() {
			
		ViewFactory.getViewFactory();
		view = ViewFactory.getView();
		this.comhan = new CommandHandler(this, view);	
		
		String input;
		try {
			while((input = in.readLine()) != null && !stop){
				String[] splitted = input.split("&");
				Map<String, String> params = new HashMap<String, String>();
				for(String str : splitted){
					params.put(str.split("=")[0],str.split("=")[1]);
				}
				this.comhan.handleCommand(params);
					
			}
		} catch (IOException e) {
			console.println("Failed to reach server. You may have disconnected due to inactivity over the maximum turn time."); 
			console.println("Please restart client if you want to play another game");
		}
	}
	
	
	public PrintWriter getOut() {
		return out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public ObjectOutputStream getOutObj() {
		return outObj;
	}

	public ObjectInputStream getInObj() {
		return inObj;
	}

	
	@Override
	public boolean close() {
		try{
			in.close();
			out.close();
			s.close();
			return true;
		} catch(IOException ex){
			return false;
		}
	}

	
}
