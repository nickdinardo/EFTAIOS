package it.polimi.ingsw.DiNapoliDiNardo.Client;

import it.polimi.ingsw.DiNapoliDiNardo.view.InitialWindow;


import java.io.IOException;



public class Client {
	String read = "";
	
	public static void main(String[] args) throws IOException {
		
		Client client = new Client();
		client.select();
	}	
	
	public void select() throws IOException{
		while(!"1".equals(this.read)  && !"2".equals(this.read)){
			//System.out.println("Scegli che interfaccia di rete usare:");
			//System.out.println("1 - Socket");
			//System.out.println("2 - RMI");
			//read = readLine("\n");
			InitialWindow init = new InitialWindow();
			this.read = init.connectionSelect();
			if(!"1".equals(this.read)  && !"2".equals(this.read))
				System.out.println("Comando non riconosciuto!");
		}
		
		NetworkInterface ni = NetworkInterfaceFactory.getInterface(this.read);
		boolean connection = ni.connect();
		if(connection){
			ni.startInterface();
		}
	}	
		
		
	
	/*private static String readLine(String format, Object... args) throws IOException {
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
	}*/
}
