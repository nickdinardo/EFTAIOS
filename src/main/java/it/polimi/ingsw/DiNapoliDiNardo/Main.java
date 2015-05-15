package it.polimi.ingsw.DiNapoliDiNardo;
import java.util.ArrayList;

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
		
		boolean escaped=false;
		do{
			int i = 0;
			for (Player player: game.inGamePlayers){
				i++;
				System.out.println("Actual position: "+(char)(player.getPosition().getCoordX()+64)+player.getPosition().getCoordY());
				System.out.println("Items: "+player.getPersonalDeck().toString());
				Coordinates coordinates = game.view.askMovement(i);
				Box destination = game.Galilei.getMap()[coordinates.coordY-1][coordinates.coordX-1];
				player.movement(destination);
				
				if (destination instanceof LifeboatBox)
					escaped = true;
			}
		}while (!escaped);
		System.out.println("Lifeboat ship reached, congratulations! You won 3 cookies.");
		
	}
	
	
	
	
	public void drawSectorCard (Player player){
		Card sectorcard = sectordeck.drawCard();
		//esegui codice per silenzio o rumore, chiamando la view per rumore in ogni settore se necessario
		if (sectorcard instanceof NoiseAnywhereCardPlusItem || sectorcard instanceof NoiseHereCardPlusItem){
			player.drawItemCard();
		}
	}
	
	public void InitializeGame(){
		this.Galilei = new GalileiMap();
		Galilei.buildmap();
		this.sectordeck = new SectorDeck();
		this.itemdeck = new ItemDeck();
		this.lifeboatdeck = new LifeboatDeck();
		
		//sistemare per consentire differente numero di giocatori poi
		HumanPlayer hansolo= new HumanPlayer(this.Galilei, this);
		AlienPlayer chubecca= new AlienPlayer(this.Galilei, this);
		inGamePlayers.add(hansolo);
		inGamePlayers.add(chubecca);
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

}
