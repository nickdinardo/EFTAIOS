package it.polimi.ingsw.DiNapoliDiNardo.Client;



import java.rmi.RemoteException;
import java.util.Map;

import it.polimi.ingsw.DiNapoliDiNardo.view.*;

public class CommandHandler {
	//sistemare con singleton
	private TextView view = new TextView();
	
	
	
	
	public String handleCommand(Map<String, String> param) throws RemoteException{
		String object = param.get("object");
		String action = param.get("action");
		String toRet = "Command unknown";
		if(object.equals("player")){
			if(action.equals("askname")){
			
			return view.askName();
			
			
			}
		}
		if(object.equals("player")){
			if(action.equals("askmovement")){
			
			return view.askMovement(1).toString();
			}
		}
		if(object.equals("print")){
			System.out.println(action);
		}
		
		return toRet;
	}
}
