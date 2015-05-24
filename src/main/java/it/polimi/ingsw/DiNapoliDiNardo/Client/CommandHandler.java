package it.polimi.ingsw.DiNapoliDiNardo.Client;



import java.io.IOException;
import java.util.Map;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.view.*;

public class CommandHandler {
	//sistemare con singleton
	private TextView view = new TextView();
	private ClientSocketInterface CSI;
	
	public CommandHandler(ClientSocketInterface csi){
		this.CSI = csi;
	}
	
	
	public void handleCommand(Map<String, String> param) throws IOException{
		String object = param.get("object");
		String action = param.get("action");
		
				
		if(object.equals("player")){
			if(action.equals("askname")){
				CSI.getOut().println(view.askName());
				CSI.getOut().flush();
				}
			if(action.equals("askmovement")){
				Coordinates coord = view.askMovement(false);
				CSI.getOutObj().writeObject(coord);
			}
			if(action.equals("asknoise")){
				String noise = view.askForNoise();
				CSI.getOut().println(noise);
				CSI.getOut().flush();
			}
			if(action.equals("reaskmovement")){
				Coordinates coord = view.askMovement(true);
				CSI.getOutObj().writeObject(coord);
			}
		}
		if(object.equals("print")){
			System.out.println(action);
		}
		if(action.equals("beingAlien")){
			view.showBeingAlien(object);
		}
		if(action.equals("beingHuman")){
			view.showBeingHuman(object);
		}
		if (object.equals("situation")){
			String[] result = action.split(";");
			view.showActualSituation(result[0], result[1], result[2]);
		    }
		if (object.equals("playeritems")){
			CSI.getOut().println(view.askItemUse(action)); 
			CSI.getOut().flush();
		}
		if (object.equals("playeritemsdiscard")){
			CSI.getOut().println(view.askItemDiscard(action)); 
			CSI.getOut().flush();
		}
			
		
		
	}
}
