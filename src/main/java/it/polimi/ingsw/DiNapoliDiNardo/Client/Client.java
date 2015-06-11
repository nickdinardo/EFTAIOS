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
			
			System.out.println("Please choose the network interface:");
			InitialWindow init = new InitialWindow();
			this.read = init.getChoice();
			
			if(!"1".equals(this.read)  && !"2".equals(this.read))
				System.out.println("Invalid Command!");
		}
		
		NetworkInterface ni = NetworkInterfaceFactory.getInterface(this.read);
		boolean connection = ni.connect();
		if(connection){
			ni.startInterface();
		}
	}	
		
}
