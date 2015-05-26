package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.Server.GameServer;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AttackCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.DefenseCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.ItemCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.LightsCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.SedativesCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.TeleportCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.ItemDeck;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.LifeboatDeck;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.SectorDeck;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GameState {
	GameServer gameserver;
	GalileiMap Galilei;
	SectorDeck sectordeck;
	ItemDeck itemdeck;
	LifeboatDeck lifeboatdeck;
	List< Player > inGamePlayers = new ArrayList< Player >();
	
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
		
	
	
	public void itemUsageManagement(String name, int index) throws ClassNotFoundException, RemoteException, IOException{
		
		
		if (index > -1 && index < 3){
			HumanPlayer player = (HumanPlayer)givemePlayerByName(name);
			ItemCard item = player.getPersonalDeck().get(index);
			gameserver.cardsMessages(name, item.getName(), item.getUseMessage());
			
			if (item instanceof LightsCard)
				lightsManagement(player);
			else if (item instanceof AttackCard)
				attackManagement(player);
			else 
				item.doAction(player);
			
			player.getPersonalDeck().remove(index);
		
		}
		else if (index > 2 && index < 6){
			//remove the card user selected to discard passing index+10
			Player player = givemePlayerByName(name);
			player.getPersonalDeck().remove(index-3);
		}
	}	
	
	
	public void lightsManagement(HumanPlayer player) throws ClassNotFoundException, RemoteException, IOException{
		Coordinates coordinates = gameserver.askForLights(player.getName());
		Box lightfocus = this.Galilei.getMap()[coordinates.getCoordY()-1][coordinates.getCoordX()-1];			
		//ask for the boxes around the lightfocus that can be reached with a single step (adiacent ones, without walls etc.)
		List<Box> toCheck = this.Galilei.givemeAroundBoxes(lightfocus);
		List<Box> enlighted = player.checkBoxes(toCheck, lightfocus);
		enlighted.add(lightfocus);
		
		for (Box box : enlighted){
			List<Player> peoplehere = box.getPlayerHere();
			String playersinbox = "";
			for (int i=0; i<peoplehere.size(); i++){
				playersinbox += peoplehere.get(i).getName();
				playersinbox += ", ";
			}
			if (playersinbox.length()>2)
				playersinbox = playersinbox.substring(0, playersinbox.length()-2);
			String lightposition = ""+(char)(box.getCoordX()+64);
			String number = ""+ box.getCoordY();
			if (number.length() == 1)
				number = "0"+ box.getCoordY();
			lightposition += number;
			gameserver.showLights(player.getName(), lightposition, playersinbox);
		}
	}
	
	
	public void attackManagement(Player player) throws RemoteException{
		List<Player> killed = player.attack(player.getPosition());
		if(killed.size() > 0){
			for(Player killedPlayer : killed){
				boolean hasDefense = false;
				Card toRemoveDefCard = null;
				if (killedPlayer instanceof HumanPlayer){
					for (Card card: killedPlayer.getPersonalDeck()){
						if (card instanceof DefenseCard){
							hasDefense = true;
							toRemoveDefCard = card;
						}
					}
				}
				if (!hasDefense){
					killedPlayer.kill();
					killedPlayer.setKiller(player.getName());
					gameserver.sayByeToLosers(killedPlayer.getName(), player.getName());
					gameserver.notifyMessage(killedPlayer.getName()+" has been KILLED by "+player.getName()+" and has left the game");
				}
				else{
					killedPlayer.getPersonalDeck().remove(toRemoveDefCard);
					gameserver.notifyMessage(killedPlayer.getName()+" saved himself from the attack activating his Defense Card!");
			    }
			}
		} 
		if (player instanceof AlienPlayer){
			AlienPlayer alien = (AlienPlayer) player;
			alien.setHasAttacked(true);
		}
		//to check
		player.getPosition().setPlayer(player);
		
	}
	
		
	public void removeInTurnBonus (){
		for (Player player: this.inGamePlayers){
			if(player instanceof HumanPlayer){
				((HumanPlayer) player).setAdrenalized(false);
				((HumanPlayer) player).setSedated(false);
			}
			else
				((AlienPlayer) player).setHasAttacked(false);
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

	public List<Player> getInGamePlayers() {
		return inGamePlayers;
	}

	public void setInGamePlayers(List<Player> inGamePlayers) {
		this.inGamePlayers = inGamePlayers;
	}


}
