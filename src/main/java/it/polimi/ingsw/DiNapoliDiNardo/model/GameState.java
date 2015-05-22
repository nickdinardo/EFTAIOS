package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.Server.GameServer;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AttackCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.ItemCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.NoiseAnywhereCardPlusItem;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.NoiseHereCardPlusItem;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.SedativesCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.TeleportCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.ItemDeck;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.LifeboatDeck;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.SectorDeck;
import java.util.ArrayList;

public class GameState {
	GameServer gameserver;
	GalileiMap Galilei;
	SectorDeck sectordeck;
	ItemDeck itemdeck;
	LifeboatDeck lifeboatdeck;
	ArrayList< Player > inGamePlayers = new ArrayList< Player >();
	
	//constructor
		public GameState(GameServer gs){
			this.Galilei = new GalileiMap();
			this.sectordeck = new SectorDeck();
			this.itemdeck = new ItemDeck();
			this.lifeboatdeck = new LifeboatDeck();
			this.gameserver = gs;
		}
	
	public boolean updatePlayerPosition (String name, Coordinates coord){
		Player player = givemePlayerByName(name);
		Box destination = this.Galilei.getMap()[coord.getCoordY()-1][coord.getCoordX()-1];
		if(player.movement(destination, player.getPosition()))
			return true;
		else
			return false;
	}
	
	
	//sector card drawing
			//if (this.position instanceof DangerousBox && !this.isSedated()){
			//	game.drawSectorCard(this);
			//}
				
	
	
	
	
	
	
	
	
	




	
	
	
	public void drawSectorCard (Player player){
		Card sectorcard = sectordeck.drawCard();
		//esegui codice per silenzio o rumore, chiamando la view per rumore in ogni settore se necessario
		if (sectorcard instanceof NoiseAnywhereCardPlusItem || sectorcard instanceof NoiseHereCardPlusItem){
			player.drawItemCard();
		}
	}
	
	public void itemUsageManagement(HumanPlayer player, ItemCard item){
		//Card item = this.view.whichItem(player.getPersonalDeck());
		
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
		
		/*if (item instanceof LightsCard){
			Coordinates coordinates = this.view.askForLights();
			Box lightfocus = this.Galilei.getMap()[coordinates.coordY-1][coordinates.coordX-1];			
			//ask for the boxes around the lightfocus that can be reached with a single step (adiacent ones, without walls etc.)
			ArrayList<Box> toCheck = this.Galilei.givemeAroundBoxes(lightfocus);
			ArrayList<Box> enlighted = player.checkBoxes(toCheck, lightfocus);
			enlighted.add(lightfocus);
			for (Box box : enlighted){
				this.view.revealingLights(box);
			}
		}*/
		
		
	}
	
	
	
	
	public void attackManagement(Player player){
		ArrayList<Player> killed = player.attack(player.getPosition());
		if(killed.size() > 0){
			for(Player killedPlayer : killed){
				//code to notify the players killed
			}
			//remove all the players from box and then replace the attacker there
			player.getPosition().clearPlayersHere();
			player.getPosition().setPlayer(player);
		} 
		if(killed.size() == 0){
			//code to notify the player of missed attack
		}
	}
	
	
	
	public void removeDeadPlayers(){
		for (Player humanplayer: this.inGamePlayers){
			if(humanplayer instanceof HumanPlayer){
				((HumanPlayer) humanplayer).setAdrenalized(false);
				((HumanPlayer) humanplayer).setSedated(false);
			}
		}
	}
		
	
	public void removeInTurnBonus (){
		for (Player humanplayer: this.inGamePlayers){
			if(humanplayer instanceof HumanPlayer){
				((HumanPlayer) humanplayer).setAdrenalized(false);
				((HumanPlayer) humanplayer).setSedated(false);
			}
		}
	}
	
	
	//give reference of player object looking for the name you ask
	public Player givemePlayerByName (String lookforname) {
		for (Player player: inGamePlayers){
			if (player.getName().equals(lookforname)){
				return player;
			}
		}
		return null;
	}
		
	

	//Getters and setters
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

	public ArrayList<Player> getInGamePlayers() {
		return inGamePlayers;
	}

	public void setInGamePlayers(ArrayList<Player> inGamePlayers) {
		this.inGamePlayers = inGamePlayers;
	}


}
