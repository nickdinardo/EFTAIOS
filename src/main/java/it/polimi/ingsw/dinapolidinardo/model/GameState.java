package it.polimi.ingsw.dinapolidinardo.model;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Box;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.model.boxes.LifeboatBox;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Wall;
import it.polimi.ingsw.dinapolidinardo.model.cards.AttackCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.Card;
import it.polimi.ingsw.dinapolidinardo.model.cards.DefenseCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.ItemCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.LifeboatCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.LightsCard;
import it.polimi.ingsw.dinapolidinardo.model.decks.ItemDeck;
import it.polimi.ingsw.dinapolidinardo.model.decks.LifeboatDeck;
import it.polimi.ingsw.dinapolidinardo.model.decks.SectorDeck;
import it.polimi.ingsw.dinapolidinardo.server.GameController;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;



/**
 *  The GameState class is the key class of the "model" of the application, designed accordingly to the MVC pattern.
 *  It dialogs with the Game Controller and manages all the model changes and update, including players and map modifications,
 *  but is always the Game Controller to call its methods if and when it needs them 
 */
public class GameState {
	private GameController gamecontroller;
	private Map map;
	private SectorDeck sectordeck;
	private ItemDeck itemdeck;
	private LifeboatDeck lifeboatdeck;
	private int turnNumber = 0;
	private List< Player > inGamePlayers = new ArrayList< Player >();
	private List< String > winners = new ArrayList< String >();
	private List< String > losers = new ArrayList< String >();
	
	/**
	 * Constructor, initialize the map and the decks of the game
	 * 
	 * @param gs reference to the game controller, that manages the connections
	 */
	public GameState(GameController gs){
		this.map = new GalileiMap();
		this.sectordeck = new SectorDeck();
		this.itemdeck = new ItemDeck();
		this.lifeboatdeck = new LifeboatDeck();
		this.gamecontroller = gs;
	}
	
		
	/**
	 * Change the position of a given player, chosen by his name
	 * 	
	 * @param name the player's moving name
	 * @param coord the selected destination coordinates
	 * @return true if the coordinates are reachables, false otherwise
	 */
	public boolean updatePlayerPosition (String name, Coordinates coord){
		
		Box destination;
		try{
			destination = this.map.getMap()[coord.getCoordY()-1][coord.getCoordX()-1];
		}
		catch (IndexOutOfBoundsException e){
			//if outside the map, return invalid movement
			return false;
		}
		Player player = givemePlayerByName(name);
		
		//if an alien player wants to move in a lifeboat, return that he can't
		if (destination instanceof LifeboatBox && player instanceof AlienPlayer)
			return false;
		
		boolean acceptable;
				
		List<Box> reachables = new ArrayList<Box>();
		int range = player.getMoveRange();
		
		//receives from map the list of the reachable boxes and check 
		//if the selected coordinates represent one of those boxes
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
		
	
	/**
	 * Manage the players personal deck modifications, using or discarding the selected cards
	 * 
	 * @param name name of the player
	 * @param index index in the personal deck of the selected card 
	 * @throws ClassNotFoundException 
	 * @throws IOException
	 */
	public void itemUsageManagement(String name, int index) throws ClassNotFoundException, IOException{
		
		
		if (index > -1 && index < 3){
			HumanPlayer player = (HumanPlayer)givemePlayerByName(name);
			ItemCard item = player.getPersonalDeck().get(index);
			
			//all other type of cards, calling overrided superclass methods
			if (!(item instanceof LightsCard) || !(item instanceof AttackCard))
				item.doAction(player);
			gamecontroller.cardsMessages(name, item.getName(), item.getUseMessage());
			//cards that require special treatment
			if (item instanceof LightsCard)
				lightsManagement(player);
			if (item instanceof AttackCard)
				attackManagement(player);
			
			//discard in the itemdeck discard pile the card used, 
			//unless is a DefenseCard (that can't be used directly)
			if (!(item instanceof DefenseCard)){
				ItemCard used = player.getPersonalDeck().remove(index);
				itemdeck.getDiscards().add(used);
			}
			
		
		}
		else if (index > 2 && index < 6){
			//remove the card user selected to discard passing index+3
			Player player = givemePlayerByName(name);
			ItemCard used = player.getPersonalDeck().remove(index-3);
			//add the card to the discard pile of the itemdeck
			itemdeck.getDiscards().add(used);
		}
	
	}	
	
	
	/**
	 * Recover all the model information needed to show a player all the players inside
	 * the positions enlighted by his LightsCard selection
	 * 
	 * @param player the player that uses the LightsCard
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void lightsManagement(HumanPlayer player) throws ClassNotFoundException, IOException{
		Box lightfocus;
		boolean reask = false;
		
		//repeatedly ask user inputs until they're valid coordinates for the lights
		do{
			Coordinates coordinates = gamecontroller.askForLights(player.getName(), reask);
			reask = true;
			lightfocus = this.map.getMap()[coordinates.getCoordY()-1][coordinates.getCoordX()-1];	
		}while(lightfocus instanceof Wall);
			
		//ask the map for the boxes around the lightfocus that can be reached with a single step (adiacent ones, without walls etc.)
		List<Box> toCheck = this.map.givemeAroundBoxes(lightfocus);
		List<Box> enlighted = this.map.checkOneStepBoxes(toCheck, lightfocus);
		enlighted.add(lightfocus);
		
		//create strings with the name of the players inside for each enlighted box
		//and passes them to game controller to be shown
		for (Box box : enlighted){
			List<Player> peoplehere = box.getPlayersHere();
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
	
	
	/**
	 * Manage the model modifications that follow an attack, checking for defense cards, 
	 * assigning the attack bonus to aliens, and setting to "dead" the killed players
	 * 
	 * @param player attacking player
	 * @throws RemoteException
	 */
	public void attackManagement(Player player) throws RemoteException{
		List<Player> killed = player.attack(player.getPosition());
		if(!killed.isEmpty()){
			for(Player killedPlayer : killed){
				
				//for every player involved check if can be saved by a Defense Card
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
				
				//if hasn't Defense Cards, manage his in-game "death", 
				//and notify it to all the players
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
				
				//otherwise remove the card and notify to everybody 
				//he saved himself from the attack
				else{
					killedPlayer.getPersonalDeck().remove(toRemoveDefCard);
					itemdeck.getDiscards().add(toRemoveDefCard);
					gamecontroller.notifyMessageToAll(killedPlayer.getName()+" saved himself from the attack activating his Defense Card!");
			    }
			}
		} 
		
		//this set is required to avoid alien players draw sector cards after the attack:
		//following the rules, they can't
		if (player instanceof AlienPlayer){
			AlienPlayer alien = (AlienPlayer) player;
			alien.setHasAttacked(true);
		}
		
	}
	
		
	/**
	 * Check if a player reached a Lifeboat ship, uses the Lifeboat Cards
	 * and manage his eventual escape and the closure of the used Lifeboat ship
	 * 
	 * @param player the player that reached the position
	 * @return true if player managed to escape, false otherwise
	 */
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
		
		//this branch is needed when all the green lifeboat cards has been drawn:
		//all the other human players that can't escape are considered dead (from game rules)
		if (winners.size() == 3){
			for (Player p : inGamePlayers)
				if (p.isLosesIfKilledType())
					p.kill();
		}
		return escaped;
	}
	
	
	/**
	 * Method called by Game Controller at the end of every turn to remove
	 * from model data all the bonuses that lasts only one turn
	 */
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
	
	
	
	/**
	 * Method that returns the instance of a player connected to a username
	 * 
	 * @param lookforname the name of the user and player
	 * @return the chosen player object
	 */
	public Player givemePlayerByName (String lookforname) {
		for (Player player: inGamePlayers){
			if (player.getName().equals(lookforname)){
				return player;
			}
		}
		return null;
	}
		
	

	//getters and setters
		
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

	public GameController getGameController(){
		return this.gamecontroller;
	}

	public List<String> getWinners() {
		return winners;
	}


	public List<String> getLosers() {
		return losers;
	}


}
