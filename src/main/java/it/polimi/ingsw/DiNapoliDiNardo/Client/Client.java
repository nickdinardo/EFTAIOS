package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

	public static void main(String[] args) throws IOException {
		String read = "";
		while(!read.equals("1")  && !read.equals("2")){
			System.out.println("Scegli che interfaccia di rete usare:");
			System.out.println("1 - Socket");
			System.out.println("2 - RMI");
			read = readLine("\n");
			if(!read.equals("1")  && !read.equals("2"))
				System.out.println("Comando non riconosciuto!");
		}
		
		NetworkInterface ni = NetworkInterfaceFactory.getInterface(read);
		ni.connect();
		ni.startInterface();
		
		
		
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
