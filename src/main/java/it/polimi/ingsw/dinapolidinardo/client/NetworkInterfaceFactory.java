package it.polimi.ingsw.dinapolidinardo.client;

public class NetworkInterfaceFactory {

	private NetworkInterfaceFactory(){
		
	}
	public static NetworkInterface getInterface(String param){
		if("1".equals(param)) 
			return new ClientSocketInterface();
		else return new ClientRMIInterface();
	}
}
