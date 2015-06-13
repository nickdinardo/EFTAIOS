package it.polimi.ingsw.dinapolidinardo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;



public class Client {
	private PrintStream out = System.out;
	String read = "";
	
	public static void main(String[] args) throws IOException {
		Client client = new Client();
		client.select();
	}	
	
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
