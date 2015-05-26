package it.polimi.ingsw.DiNapoliDiNardo.Client;



import java.io.IOException;
import java.util.Map;

import it.polimi.ingsw.DiNapoliDiNardo.model.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.view.*;

public class CommandHandler {
	//sistemare con singleton
	private TextView view = new TextView();
	private ClientSocketInterface csi;
	
	public CommandHandler(ClientSocketInterface csi){
		this.csi = csi;
	}
	
	
	public void handleCommand(Map<String, String> param) throws IOException{
		String object = param.get("object");
		String action = param.get("action");
		
				
		if(object.equals("player")){
			if(action.equals("askname")){
				csi.getOut().println(view.askName());
				csi.getOut().flush();
				}
			if(action.equals("askattack")){
				csi.getOut().println(view.askForAttack());
				csi.getOut().flush();
				}
			if(action.equals("askmovement")){
				Coordinates coord = view.askMovement(false);
				csi.getOutObj().writeObject(coord);
			}
			if(action.equals("asklights")){
				Coordinates coord = view.askForLights();
				csi.getOutObj().writeObject(coord);
			}
			if(action.equals("asknoise")){
				String noise = view.askForNoise();
				csi.getOut().println(noise);
				csi.getOut().flush();
			}
			if(action.equals("reaskmovement")){
				Coordinates coord = view.askMovement(true);
				csi.getOutObj().writeObject(coord);
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
			csi.getOut().println(view.askItemUse(action)); 
			csi.getOut().flush();
		}
		if (object.equals("humanplayeritemsdiscard")){
			csi.getOut().println(view.askHumanItemDiscard(action)); 
			csi.getOut().flush();
		}
		if (object.equals("alienplayeritemsdiscard")){
			csi.getOut().println(view.askAlienItemDiscard(action)); 
			csi.getOut().flush();
		}	
		
		
	}
}
