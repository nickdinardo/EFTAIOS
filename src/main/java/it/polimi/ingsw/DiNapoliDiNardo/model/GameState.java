package it.polimi.ingsw.DiNapoliDiNardo.model;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.LifeboatBox;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Wall;
import it.polimi.ingsw.DiNapoliDiNardo.Server.GameController;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.AttackCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.Card;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.DefenseCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.ItemCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.LifeboatCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.LightsCard;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.ItemDeck;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.LifeboatDeck;
import it.polimi.ingsw.DiNapoliDiNardo.model.decks.SectorDeck;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GameState {
	GameController gamecontroller;
	Map map;
	SectorDeck sectordeck;
	ItemDeck itemdeck;
	LifeboatDeck lifeboatdeck;
	int turnNumber = 0;
	List< Player > inGamePlayers = new ArrayList< Player >();
	List< String > winners = new ArrayList< String >();
	List< String > losers = new ArrayList< String >();
	
	//constructor
	public GameState(GameController gs){
		this.map = new GalileiMap();
		this.sectordeck = new SectorDeck();
		this.itemdeck = new ItemDeck();
		this.lifeboatdeck = new LifeboatDeck();
		this.gamecontroller = gs;
	}
	
		
		
	public boolean updatePlayerPosition (String name, Coordinates coord){
		
		Box destination = this.map.getMap()[coord.getCoordY()-1][coord.getCoordX()-1];
		Player player = givemePlayerByName(name);
		if (destination instanceof LifeboatBox && player instanceof AlienPlayer)
			return false;
		
		boolean acceptable;
		
		
		List<Box> reachables = new ArrayList<Box>();
		int range = player.getMoveRange();
		if(player.isAdrenalized())
			range++;
		if(player.isHumanFed())
			range++;
		
		reachables = map.reachableBoxes(player.getPosition(), range);
		if(reachables.contains(destination)){
			player.position.unsetPlayer(player);
			player.position = destination;
			player.position.setPlayer(player);
			acceptable = true;
		}
		else
			acceptable = false;
		return acceptable;
	}
		
	
	
	public void itemUsageManagement(String name, int index) throws Exception{
		
		
		if (index > -1 && index < 3){
			HumanPlayer player = (HumanPlayer)givemePlayerByName(name);
			ItemCard item = player.getPersonalDeck().get(index);
			gamecontroller.cardsMessages(name, item.getName(), item.getUseMessage());
			//cards that require special treatment
			if (item instanceof LightsCard)
				lightsManagement(player);
			else if (item instanceof AttackCard)
				attackManagement(player);
			//all other type of cards, calling overrided superclass methods
			else 
				item.doAction(player);
			//discard in the itemdeck the card used, unless is a DefenseCard
			if (!(item instanceof DefenseCard)){
				ItemCard used = player.getPersonalDeck().remove(index);
				itemdeck.getDiscards().add(used);
			}
		
		}
		else if (index > 2 && index < 6){
			//remove the card user selected to discard passing index+10
			Player player = givemePlayerByName(name);
			ItemCard used = player.getPersonalDeck().remove(index-3);
			itemdeck.getDiscards().add(used);
		}
	
	}	
	
	
	
	public void lightsManagement(HumanPlayer player) throws Exception{
		Box lightfocus;
		boolean reask = false;
		do{
			Coordinates coordinates = gamecontroller.askForLights(player.getName(), reask);
			reask = true;
			lightfocus = this.map.getMap()[coordinates.getCoordY()-1][coordinates.getCoordX()-1];	
		}while(lightfocus instanceof Wall);
			
		//ask for the boxes around the lightfocus that can be reached with a single step (adiacent ones, without walls etc.)
		List<Box> toCheck = this.map.givemeAroundBoxes(lightfocus);
		List<Box> enlighted = this.map.checkOneStepBoxes(toCheck, lightfocus);
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
			gamecontroller.showLights(player.getName(), lightposition, playersinbox);
		}
	}
	
	
	
	public void attackManagement(Player player) throws RemoteException{
		List<Player> killed = player.attack(player.getPosition());
		if(!killed.isEmpty()){
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
					player.getPosition().unsetPlayer(killedPlayer);
					if(killedPlayer.isLosesIfKilledType()){
						gamecontroller.decreaseHumansAndCheck(killedPlayer);
						losers.add(killedPlayer.getName());
					}
					if(killedPlayer.isLosesIfKilledType() && player instanceof AlienPlayer){
						AlienPlayer alien = (AlienPlayer)player;
						alien.setHumanfed(true);
					}
					gamecontroller.sayByeToLosers(killedPlayer.getName(), player.getName());
					gamecontroller.notifyMessageToAll(killedPlayer.getName()+" has been KILLED by "+player.getName()+" and has left the game");
				}
				else{
					killedPlayer.getPersonalDeck().remove(toRemoveDefCard);
					itemdeck.getDiscards().add(toRemoveDefCard);
					gamecontroller.notifyMessageToAll(killedPlayer.getName()+" saved himself from the attack activating his Defense Card!");
			    }
			}
		} 
		if (player instanceof AlienPlayer){
			AlienPlayer alien = (AlienPlayer) player;
			alien.setHasAttacked(true);
		}
		
	}
	
		
	
	public boolean escapeManagement(Player player){
		
		boolean escaped = false;
		//check if the box is a lifeboat and a working one
		boolean shipworks = player.getPosition().isLifeBoatShipHere();
		if(shipworks){
			//in any case, disable the lifeboat for following players
			player.getPosition().setLifeBoatShipHere(false);
			//then draw the red or green card and solve it
			LifeboatCard card = (LifeboatCard)lifeboatdeck.drawCard();
			if (card.isWorking()){
				escaped = true;
				player.setEscaped(true);
				player.getPosition().unsetPlayer(player);
				winners.add(player.getName());
			}
		}
		return escaped;
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
	public Map getMap() {
		return map;
	}

	public List<Player> getInGamePlayers() {
		return inGamePlayers;
	}

	public void setInGamePlayers(List<Player> inGamePlayers) {
		this.inGamePlayers = inGamePlayers;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void increaseTurnNumber() {
		turnNumber++;
	}


	public List<String> getWinners() {
		return winners;
	}


	public List<String> getLosers() {
		return losers;
	}


}
