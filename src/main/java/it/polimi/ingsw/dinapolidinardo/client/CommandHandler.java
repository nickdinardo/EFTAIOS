package it.polimi.ingsw.dinapolidinardo.client;



import java.io.IOException;
import java.util.Map;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.view.*;

public class CommandHandler {
	
	private View view;
	private ClientSocketInterface csi;
	
	public CommandHandler(ClientSocketInterface csi, View v){
		this.csi = csi;
		this.view = v;
	}
	
	
	public void handleCommand(Map<String, String> param) throws IOException{
		String object = param.get("object");
		String action = param.get("action");
		
				
		if("player".equals(object)){
			if("askname".equals(action)){
				csi.getOut().println(view.askName(false));
				csi.getOut().flush();
				}
			if("reaskname".equals(action)){
				csi.getOut().println(view.askName(true));
				csi.getOut().flush();
				}
			if("askattack".equals(action)){
				csi.getOut().println(view.askForAttack());
				csi.getOut().flush();
				}
			if("askmovement".equals(action)){
				Coordinates coord = view.askMovement(false);
				csi.getOutObj().writeObject(coord);
			}
			if("reaskmovement".equals(action)){
				Coordinates coord = view.askMovement(true);
				csi.getOutObj().writeObject(coord);
			}
			if("asklights".equals(action)){
				Coordinates coord = view.askForLights(false);
				csi.getOutObj().writeObject(coord);
			}
			if("reasklights".equals(action)){
				Coordinates coord = view.askForLights(true);
				csi.getOutObj().writeObject(coord);
			}
			if("asknoise".equals(action)){
				String noise = view.askForNoise();
				csi.getOut().println(noise);
				csi.getOut().flush();
			}
			
		}
		if("print".equals(object)){
			view.print(action);
		}
		if("beingAlien".equals(action)){
			view.showBeingAlien(object);
		}
		if("beingHuman".equals(action)){
			view.showBeingHuman(object);
		}
		if ("escape".equals(object)){
			String name = action.substring(0, action.length()-1);
			String shipnumber = action.substring(action.length()-1, action.length());
			view.notifyEscape(true, name, shipnumber);
		}
		if ("escapefailed".equals(object)){
			String name = action.substring(0, action.length()-1);
			String shipnumber = action.substring(action.length()-1, action.length());
			view.notifyEscape(false, name, shipnumber);
		}
		if ("situation".equals(object)){
			String[] result = action.split(";");
			view.showActualSituation(result[0], result[1], result[2], result[3]);
		    }
		if ("update".equals(object)){
			String[] result = action.split(";");
			view.update(result[0], result[1], result[2]);
		    }
		if ("results".equals(object)){
			String[] result = action.split(";");
			if ("y".equals(result[5]))
				view.showFinalResults(true, result[0], result[1], result[2], result[3], result[4]);
			else 
				view.showFinalResults(false, result[0], result[1], result[2], result[3], result[4]);
		    }
		if ("playeritemsY".equals(object)){
			csi.getOut().println(view.askItemUse(action, true)); 
			csi.getOut().flush();
		}
		if ("playeritemsN".equals(object)){
			csi.getOut().println(view.askItemUse(action, false)); 
			csi.getOut().flush();
		}
		if ("humanplayeritemsdiscard".equals(object)){
			csi.getOut().println(view.askHumanItemDiscard(action)); 
			csi.getOut().flush();
		}
		if ("alienplayeritemsdiscard".equals(object)){
			csi.getOut().println(view.askAlienItemDiscard(action)); 
			csi.getOut().flush();
		}
		if ("endturn".equals(object)){
			view.signalEndOfTurn(); 
		}	
		
		
	}
}
