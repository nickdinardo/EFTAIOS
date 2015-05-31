package it.polimi.ingsw.DiNapoliDiNardo.Client;

public class NetworkInterfaceFactory {

	private NetworkInterfaceFactory(){
		
	}
	public static NetworkInterface getInterface(String param){
		if("1".equals(param)) 
			return new ClientSocketInterface();
		else return new ClientRMIInterface();
	}
}
