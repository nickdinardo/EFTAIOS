package it.polimi.ingsw.DiNapoliDiNardo.Client;

public class NetworkInterfaceFactory {

	private NetworkInterfaceFactory(){
		
	}
	public static NetworkInterface getInterface(String param){
		if(param.equals("1")) return new ClientSocketInterface();
		else return new ClientRMIInterface();
	}
}
