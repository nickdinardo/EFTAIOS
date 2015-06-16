package it.polimi.ingsw.dinapolidinardo.client;

import it.polimi.ingsw.dinapolidinardo.view.View;
import it.polimi.ingsw.dinapolidinardo.view.ViewFactory;

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

/**
 *  Socket version of the connection interface 
 *  <p>
 *  Create two channels for the socket output and two for input, respectively for Strings and Objects
 */
public class ClientSocketInterface implements NetworkInterface {

	private PrintStream console = System.out;
	private Socket s;
	private static final String LOCAL = "127.0.0.1";
	private View view;
	private PrintWriter out;
	private BufferedReader in; 
	private ObjectOutputStream outObj;
	private ObjectInputStream inObj;
	private boolean stop = false;
	private CommandHandler comhan;
	
	public ClientSocketInterface() {
		
	} 
	
	
	/**
	 *  Initialize the four socket streams
	 *  
	 *  @return true if initialization has been successful, false otherwise
	 *  @throws IOException if can't manage to close the socket when the streams initialization failed
	 */
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

	
	/**
	 * Get a view instance via ViewFactory and initialize a Command Handler to where redirect all the stream inputs 
	 * after having codified them.
	 * @throws IOException 
	 * 
	 * @see ViewFactory
	 * @see CommandHandler
	 */
	@Override
	public void startInterface() throws IOException {
			
		ViewFactory.getViewFactory();
		view = ViewFactory.getView();
		this.comhan = new CommandHandler(this, view);	
		
		String input;
		try {
			while((input = in.readLine()) != null && !stop){
				//split the String in four parts linked two-two in a HashMap passed
				//to the Command Handler
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
	
	
	//getters
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

	
	/**
	 * Closes all socket streams
	 */
	@Override
	public boolean close() {
		try{
			in.close();
			out.close();
			inObj.close();
			outObj.close();
			s.close();
			return true;
		} catch(IOException ex){
			return false;
		}
	}

	
}
