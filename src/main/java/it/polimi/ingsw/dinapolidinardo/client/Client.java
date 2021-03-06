package it.polimi.ingsw.dinapolidinardo.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


/**
 *  Main class on the client side of the game, allows to select the type of connection 
 *  between RMI and Socket
 *  
 */
public class Client {
	private static PrintStream out = System.out;
	private String read = "";
	
	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.select();
		} catch (IOException e) {
			out.println("Problems with resources intialization.");
			out.println("Closing client.");
		}
	}	
	
	/**
	 *  Select Socket or RMI connection via console input, and starts the interface.
	 *  Uses the "factory" pattern to get the selected instance
	 */
	public void select() throws IOException{
		while(!"1".equals(this.read)  && !"2".equals(this.read)){
			
			out.println("Please choose the network interface:");
			out.println("1 - Socket");
			out.println("2 - RMI");
			read = readLine("\n");
			
			if(!"1".equals(this.read)  && !"2".equals(this.read))
				out.println("Invalid Command!");
		}
		
		NetworkInterface ni = NetworkInterfaceFactory.getInterface(this.read);
		boolean connection = ni.connect();
		if(connection){
			ni.startInterface();
		}
	}	
	
	/**
	 *  @return 	a line read from the console
	 */
	private static String readLine(String format, Object... args) throws IOException {
	    if (System.console() != null) {
	        return System.console().readLine(format, args);
	    }
	    System.out.print(String.format(format, args));
	    
	    BufferedReader br = null;
	    InputStreamReader isr = null;
	    String read = null;
	    
	    isr = new InputStreamReader(System.in);
	    br = new BufferedReader(isr);
	    read = br.readLine();
	    
	    return read;
	}
}
