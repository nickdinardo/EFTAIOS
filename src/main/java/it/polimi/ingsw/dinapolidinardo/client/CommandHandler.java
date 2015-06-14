package it.polimi.ingsw.dinapolidinardo.client;



import java.io.IOException;
import java.util.Map;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.view.*;


/**
 * Class that receives a map of commands from the socket interface and communicate with the view 
 * asking the needed inputs for every request
 */
public class CommandHandler {
	
	private View view;
	private ClientSocketInterface csi;
	
	/**
	 * Constructor, receives the instance of the socket interface and of the view since communicates with both. 
	 *
	 *@param csi The ClientSocketInterface instance
	 *@param v The View instance
	 */
	public CommandHandler(ClientSocketInterface csi, View v){
		this.csi = csi;
		this.view = v;
	}
	
	/**
	 * handle the request from the socket interface addressing it to the correct view method
	 * 
	 * @param param the map composed of two fields where the two "keys" are "object" and "action". 
	 * 	Depending on the values of these two entries calls different view methods
	 * @throws IOException on streams errors
	 */
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
			String[] parts = action.split(";");
			view.showActualSituation(parts[0], parts[1], parts[2], parts[3]);
		    }
		if ("update".equals(object)){
			String[] parts = action.split(";");
			view.update(parts[0], parts[1], parts[2]);
		    }
		if ("results".equals(object)){
			String[] parts = action.split(";");
			if ("y".equals(parts[5]))
				view.showFinalResults(true, parts[0], parts[1], parts[2], parts[3], parts[4]);
			else 
				view.showFinalResults(false, parts[0], parts[1], parts[2], parts[3], parts[4]);
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
