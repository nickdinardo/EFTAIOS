package it.polimi.ingsw.DiNapoliDiNardo;
import java.util.ArrayList;
import java.util.Iterator;

import it.polimi.ingsw.DiNapoliDiNardo.model.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.*;
import it.polimi.ingsw.DiNapoliDiNardo.view.*;

public class Main {
	
	View view = new TextView();
	GalileiMap Galilei;
	SectorDeck sectordeck;
	ItemDeck itemdeck;
	LifeboatDeck lifeboatdeck;
	ArrayList< Player > inGamePlayers = new ArrayList< Player >();
	
	public static void main( String[] args){
		
		Main game = new Main(); 
		game.InitializeGame();
		System.out.println(game.Galilei.toString());
		
		//local game simulation
		boolean escaped=false;
		do{
			int i = 0;
			
			//objects and movement phase
			for (Player player: game.inGamePlayers){
				
					i++;
					System.out.println("Actual position: "+(char)(player.getPosition().getCoordX()+64)+player.getPosition().getCoordY());
					System.out.println("Items: "+player.getPersonalDeck().toString());
					
					//objects usage
					//if(player instanceof HumanPlayer && player.getPersonalDeck().size() > 0){
						//if(game.view.askItemUse(i)){
							//game.itemUsageManagement((HumanPlayer)player);
					//	}
					//}
					
					//movement
					Coordinates coordinates = game.view.askMovement(true);
					Box destination = game.Galilei.getMap()[coordinates.coordY-1][coordinates.coordX-1];
					player.movement(destination, player.getPosition());
					
					
					if (destination instanceof LifeboatBox)
						escaped = true;
			}
			
			//alien attacks phase
			for (Player alienplayer: game.inGamePlayers){
				if(alienplayer instanceof AlienPlayer){
					if(game.view.askForAttack()){
						game.attackManagement(alienplayer);
					}
				}
			}
			
			//removing players from the game who has been killed
			for (Iterator<Player> it = game.inGamePlayers.iterator(); it.hasNext(); ) {
			    Player player = it.next();
			    if (!player.isAlive()) {
			        it.remove();
			    }
			}
			
			//removing in-turn advantages
			for (Player humanplayer: game.inGamePlayers){
				if(humanplayer instanceof HumanPlayer){
					((HumanPlayer) humanplayer).setAdrenalized(false);
					((HumanPlayer) humanplayer).setSedated(false);
				}
			}
			
		}while (!escaped);
		System.out.println("Lifeboat ship reached, congratulations! You won 3 cookies.");
		
	}
	
	
	
	
	
	
	public void InitializeGame(){
		this.Galilei = new GalileiMap();
		this.sectordeck = new SectorDeck();
		this.itemdeck = new ItemDeck();
		this.lifeboatdeck = new LifeboatDeck();
		
		//sistemare per consentire differente numero di giocatori poi
		//HumanPlayer hansolo= new HumanPlayer(this.Galilei, this, "hansolo");
		//AlienPlayer chubecca= new AlienPlayer(this.Galilei, this, "chubecca");
		//inGamePlayers.add(hansolo);
		//inGamePlayers.add(chubecca);
	}
	
	public void drawSectorCard (Player player){
		Card sectorcard = sectordeck.drawCard();
		//esegui codice per silenzio o rumore, chiamando la view per rumore in ogni settore se necessario
		if (sectorcard instanceof NoiseAnywhereCardPlusItem || sectorcard instanceof NoiseHereCardPlusItem){
			//player.drawItemCard();
		}
	}
	
	/*public void itemUsageManagement(HumanPlayer player){
		Card item = this.view.whichItem(player.getPersonalDeck());
		
		if (item instanceof TeleportCard)
			player.teleport();
		
		if (item instanceof AttackCard){
			this.attackManagement(player);
		}
		
		if (item instanceof AdrenalineCard){
			player.setAdrenalized(true);
		}

		if (item instanceof SedativesCard){
			player.setSedated(true);
		}
		
		if (item instanceof LightsCard){
			Coordinates coordinates = this.view.askForLights();
			Box lightfocus = this.Galilei.getMap()[coordinates.coordY-1][coordinates.coordX-1];			
			//ask for the boxes around the lightfocus that can be reached with a single step (adiacent ones, without walls etc.)
			ArrayList<Box> toCheck = this.Galilei.givemeAroundBoxes(lightfocus);
			ArrayList<Box> enlighted = player.checkBoxes(toCheck, lightfocus);
			enlighted.add(lightfocus);
			for (Box box : enlighted){
				this.view.revealingLights(box);
			}
		}
		
		
	}*/
	
	public void attackManagement(Player player){
		ArrayList<Player> killed = player.attack(player.getPosition());
		if(killed.size() > 0){
			for(Player killedPlayer : killed){
				this.view.killPlayer(killedPlayer);
			}
			//remove all the players from box and then replace the attacker there
			player.getPosition().clearPlayersHere();
			player.getPosition().setPlayer(player);
		} 
		if(killed.size() == 0){
			this.view.attackNotSuccesful();
		}
	}
	
	
	
	//Getters of game decks
	public SectorDeck getSectordeck() {
		return sectordeck;
	}
	public ItemDeck getItemdeck() {
		return itemdeck;
	}
	public LifeboatDeck getLifeboatdeck() {
		return lifeboatdeck;
	}
	public GalileiMap getGalilei() {
		return Galilei;
	}

}
