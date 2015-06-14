package it.polimi.ingsw.dinapolidinardo.client;

/**
 * Implementation of the "factory" pattern that provides an instance of a RMI or Socket interface 
 * based on the user input
 */
public class NetworkInterfaceFactory {

	private NetworkInterfaceFactory(){
		
	}
	public static NetworkInterface getInterface(String param){
		if("1".equals(param)) 
			return new ClientSocketInterface();
		else return new ClientRMIInterface();
	}
}
