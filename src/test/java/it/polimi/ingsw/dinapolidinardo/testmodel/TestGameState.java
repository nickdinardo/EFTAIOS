package it.polimi.ingsw.dinapolidinardo.testmodel;

import static org.junit.Assert.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.dinapolidinardo.model.AlienPlayer;
import it.polimi.ingsw.dinapolidinardo.model.GameState;
import it.polimi.ingsw.dinapolidinardo.model.HumanPlayer;
import it.polimi.ingsw.dinapolidinardo.model.Player;
import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.model.boxes.LifeboatBox;
import it.polimi.ingsw.dinapolidinardo.model.cards.AdrenalineCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.AttackCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.DefenseCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.ItemCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.LifeboatCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.SectorCard;
import it.polimi.ingsw.dinapolidinardo.model.cards.SedativesCard;
import it.polimi.ingsw.dinapolidinardo.server.GameController;


public class TestGameState {
	
	private GameState gamestate;
	HumanPlayer human1;
	AlienPlayer alien1;
	HumanPlayer human2;
	AlienPlayer alien2;
	HumanPlayer human3;
	HumanPlayer human4;
	HumanPlayer human5;
	HumanPlayer human6;
	HumanPlayer human7;
	
	@Before 
	public void setUp(){
		
		gamestate = new GameState(new GameController (1, null, null));
		gamestate.getGameController().setGameState(gamestate);
		human1 = new HumanPlayer(gamestate.getMap(), gamestate, "testhuman1");
		alien1 = new AlienPlayer(gamestate.getMap(), gamestate, "testalien1");
		human2 = new HumanPlayer(gamestate.getMap(), gamestate, "testhuman2");
		alien2 = new AlienPlayer(gamestate.getMap(), gamestate, "testalien2");
		human3 = new HumanPlayer(gamestate.getMap(), gamestate, "testhuman3");
		human4 = new HumanPlayer(gamestate.getMap(), gamestate, "testhuman4");
		human5 = new HumanPlayer(gamestate.getMap(), gamestate, "testhuman5");
		human6 = new HumanPlayer(gamestate.getMap(), gamestate, "testhuman6");
		human7 = new HumanPlayer(gamestate.getMap(), gamestate, "testhuman7");
		List<Player> inTest = new ArrayList<Player>();
		inTest.add(human1);
		inTest.add(alien1);
		inTest.add(human2);
		inTest.add(alien2);
		inTest.add(human3);
		inTest.add(human4);
		inTest.add(human5);
		inTest.add(human6);
		inTest.add(human7);
		gamestate.setInGamePlayers(inTest);
		
	}
	
	@Test
	public void	turnNumberTest(){
		for (int i=0; i<5; i++)
			gamestate.increaseTurnNumber();
		assertEquals(gamestate.getTurnNumber(), 5);
	
	}
	
	@Test
	public void gettersTest(){
		assertTrue(gamestate.getSectordeck().drawCard() instanceof SectorCard);
		assertTrue(gamestate.getItemdeck().drawCard() instanceof ItemCard);
		assertTrue(gamestate.getLifeboatdeck().drawCard() instanceof LifeboatCard);
		assertTrue(gamestate.getInGamePlayers().contains(human1));
		assertTrue(gamestate.getInGamePlayers().contains(alien1));
		assertEquals(gamestate.givemePlayerByName("testhuman1"), human1);
		
	}
	
	@Test 
	public void removeBonusesTest(){
		human1.setAdrenalized(true);
		alien1.setHasAttacked(true);
		human2.setSedated(true);
		alien2.setHumanfed(true);
		gamestate.removeInTurnBonus();
		//in turn bonus that should have been removed
		assertTrue(!human1.isAdrenalized());
		assertTrue(!alien1.isHasAttacked());
		assertTrue(!human2.isAdrenalized());
		//this is not a in-turn bonus
		assertTrue(alien2.isHumanFed());
	}

	@Test 
	public void updatePositionTest(){
		//limit case
		assertTrue(!gamestate.updatePlayerPosition("testhuman1", new Coordinates(0,0)));
		assertTrue(!gamestate.updatePlayerPosition("testhuman1", new Coordinates(1000,1000)));
		//humanplayer can move in position 12,9 from start
		assertTrue(gamestate.updatePlayerPosition("testhuman1", new Coordinates(12,9)));
		//but can't in 12,11
		assertTrue(!gamestate.updatePlayerPosition("testhuman1", new Coordinates(12,11)));
		//check double alien move
		assertTrue(gamestate.updatePlayerPosition("testalien1", new Coordinates(13,5)));
		//check triple alien move
		alien1.setHumanfed(true);
		assertTrue(gamestate.updatePlayerPosition("testalien1", new Coordinates(14,4)));
		//set alien close to lifeboat and check he can't end his movement in the lifeboat
		alien1.setPosition(gamestate.getMap().getMap()[2][1]);
		assertTrue(!gamestate.updatePlayerPosition("testalien1", new Coordinates(2,2)));
		//but human can 
		human1.setPosition(gamestate.getMap().getMap()[2][1]);
		assertTrue(gamestate.updatePlayerPosition("testhuman1", new Coordinates(2,2)));
	}
	
	@Test
	public void itemDiscardTest() throws ClassNotFoundException, IOException{
		
		human1.getPersonalDeck().add(new AdrenalineCard());
		human1.getPersonalDeck().add(new SedativesCard());
		
		//index that make game state discard those cards
		gamestate.itemUsageManagement("testhuman1", 3);
		gamestate.itemUsageManagement("testhuman1", 3);
		//this is the index for "do nothing"
		gamestate.itemUsageManagement("testhuman1", 8);
		//this is a wrong index
		gamestate.itemUsageManagement("testhuman1", -3);
		assertTrue(human1.getPersonalDeck().isEmpty());
	}
	
	@Test
	public void itemUsageTest1() throws ClassNotFoundException, IOException{
		//correct selection and usage of the card
		human1.getPersonalDeck().add(new AdrenalineCard());
		try{
			gamestate.itemUsageManagement("testhuman1", 0);
		}
		catch (NullPointerException e){
			//exception generated by game controller who doesn't have any handler to which communicate
			//is not necessary manage it since communication happens after game state
			//has solved all the model changes due to the cards usage
		}
		assertTrue(human1.isAdrenalized());
	}
	
	@Test
	public void itemUsageTest2() throws ClassNotFoundException, IOException{
		human1.getPersonalDeck().add(new AdrenalineCard());
		human1.getPersonalDeck().add(new DefenseCard());
		human1.getPersonalDeck().add(new SedativesCard());
		try{
			gamestate.itemUsageManagement("testhuman1", 2);
		}
		catch (NullPointerException e){
			//exception generated by game controller who doesn't have any handler to which communicate
			//is not necessary manage it since communication happens after game state
			//has solved all the model changes due to the cards usage
		}
		try{
			gamestate.itemUsageManagement("testhuman1", 2);
		}
		catch (NullPointerException e){
		}
		assertTrue(human1.isSedated());
	}
	
	
	@Test
	public void AlienAttackTest() throws RemoteException{
		human1.setPosition(gamestate.getMap().getMap()[4][5]);
		alien1.setPosition(gamestate.getMap().getMap()[4][5]);
		try{
			gamestate.attackManagement(alien1);
		}
		catch (NullPointerException e){
			//exception generated by game controller who doesn't have any handler to which communicate
			//is not necessary manage it since communication happens after game state
			//has solved all the model changes due to the cards usage
		}
		assertTrue(!human1.isAlive());
		//check if human players has been correctly put in the losers list
		assertTrue(gamestate.getLosers().contains("testhuman1"));
		//check is alien player now can move faster
		assertTrue(alien1.isHumanFed());
		assertEquals(alien1.getMoveRange(), 3);
		//check if dead player has been removed from position
		assertTrue(!alien1.getPosition().getPlayersHere().contains("testhuman1"));
		
	}
	
	@Test
	public void AlienAgainstAlienAttackTest() throws RemoteException{
		alien1.setPosition(gamestate.getMap().getMap()[4][5]);
		alien2.setPosition(gamestate.getMap().getMap()[4][5]);
		try{
			gamestate.attackManagement(alien1);
		}
		catch (NullPointerException e){
			//exception generated by game controller who doesn't have any handler to which communicate
			//is not necessary manage it since communication happens after game state
			//has solved all the model changes due to the cards usage
		}
		assertTrue(!alien2.isAlive());
		//check is alien player now can move faster (he shouldn't having not killed a human)
		assertTrue(!alien1.isHumanFed());
		assertEquals(alien1.getMoveRange(), 2);
		//check if dead player has been removed from position
		assertTrue(!alien1.getPosition().getPlayersHere().contains("testalien2"));
		
	}
	
	@Test
	public void AlienAgainstNothingAttackTest() throws RemoteException{
		alien1.setPosition(gamestate.getMap().getMap()[4][5]);
		try{
			gamestate.attackManagement(alien1);
		}
		catch (NullPointerException e){
			//exception generated by game controller who doesn't have any handler to which communicate
			//is not necessary manage it since communication happens after game state
			//has solved all the model changes due to the cards usage
		}
		assertTrue(alien1.isAlive());
		assertTrue(!alien1.isHumanFed());
		assertEquals(alien1.getMoveRange(), 2);
		assertEquals(alien1.getPosition().getPlayersHere().size(), 1);
	}
	
	@Test
	public void AttackAgainstDefenseCardTest() throws ClassNotFoundException, IOException{
		//test in which a human player activate an Attack Card to attack
		//a box in which there's another human with a Defense Card
		alien1.setPosition(gamestate.getMap().getMap()[6][6]);
		human2.setPosition(gamestate.getMap().getMap()[6][6]);
		human2.getPersonalDeck().add(new DefenseCard());
		try{
			gamestate.attackManagement(alien1);
		}
		catch (NullPointerException e){
			//exception generated by game controller who doesn't have any handler to which communicate
			//is not necessary manage it since communication happens after game state
			//has solved all the model changes tested here due to the cards usage
		}
		//player 2 should have saved himself with defense card
		assertTrue(human2.isAlive());
		//check if human players has been NOT put in the losers list
		assertTrue(!gamestate.getLosers().contains("testhuman2"));
		//check if both remained in the position after the attack
		assertTrue(gamestate.getMap().getMap()[6][6].getPlayersHere().contains(alien1));
		assertTrue(gamestate.getMap().getMap()[6][6].getPlayersHere().contains(human2));
		//than another human attack the position
		human1.setPosition(gamestate.getMap().getMap()[6][6]);
		try{
			gamestate.attackManagement(human1);
		}
		catch (NullPointerException e){
			//exception generated by game controller who doesn't have any handler to which communicate
			//is not necessary manage it since communication happens after game state
			//has solved all the model changes tested here due to the cards usage
		}
		//check who remained in the position after the attack
		assertTrue(!gamestate.getMap().getMap()[6][6].getPlayersHere().contains(alien1));
		assertTrue(gamestate.getMap().getMap()[6][6].getPlayersHere().contains(human1));
	}
	
	@Test
	public void EscapeTest(){
		human1.setPosition(gamestate.getMap().getMap()[12][1]);
		assertTrue(human1.getPosition().isLifeBoatShipHere());
		gamestate.escapeManagement(human1);
		//if a player activate this method, the lifeboat must be unusable for all the following players
		assertTrue(!human1.getPosition().isLifeBoatShipHere());
		
	}
	
	@Test
	public void EscapeInNormalBoxTest(){
		human1.setPosition(gamestate.getMap().getMap()[1][2]);
		assertTrue(!human1.getPosition().isLifeBoatShipHere());
		gamestate.escapeManagement(human1);
		//can't escape froma box that isn't a lifeboat box
		assertTrue(!human1.isEscaped());
		
	}
	
	@Test
	public void MultipleEscapeTest(){
		//if I put four players in the four lifeboat of the map, at least one always escape,
		//because there are only three red lifeboat cards in the deck
		human1.setPosition(gamestate.getMap().getMap()[1][1]);
		human2.setPosition(gamestate.getMap().getMap()[12][1]);
		human3 = new HumanPlayer(gamestate.getMap(), gamestate, "testhuman3");
		human4 = new HumanPlayer(gamestate.getMap(), gamestate, "testhuman4");
		human3.setPosition(gamestate.getMap().getMap()[1][21]);
		human4.setPosition(gamestate.getMap().getMap()[12][21]);
		gamestate.escapeManagement(human1);
		gamestate.escapeManagement(human2);
		gamestate.escapeManagement(human3);
		gamestate.escapeManagement(human4);
		assertTrue(!gamestate.getWinners().isEmpty());
	}
	
	@Test
	public void AllLifeBoatShutDownTest(){
		//if I put six players in six lifeboat, all the green cards will be drawn 
		//and the game state has to eliminate all the players that are still in the game
		//because they can't escape and thus are considered dead
		human1.setPosition(gamestate.getMap().getMap()[1][1]);
		human2.setPosition(gamestate.getMap().getMap()[12][1]);
		human3.setPosition(gamestate.getMap().getMap()[1][21]);
		human4.setPosition(gamestate.getMap().getMap()[12][21]);
		LifeboatBox shuttle5 = new LifeboatBox(55,55, 5);
		LifeboatBox shuttle6 = new LifeboatBox(77,77, 6);
		human5.setPosition(shuttle5);
		human6.setPosition(shuttle6);
				
		gamestate.escapeManagement(human1);
		gamestate.escapeManagement(human2);
		gamestate.escapeManagement(human3);
		gamestate.escapeManagement(human4);
		gamestate.escapeManagement(human5);
		gamestate.escapeManagement(human6);
		
		//three must have escaped
		assertEquals(gamestate.getWinners().size(), 3);
		//the seventh must have been eliminated from the game
		assertTrue(!human7.isAlive());
		
		
	}
	
	
}
