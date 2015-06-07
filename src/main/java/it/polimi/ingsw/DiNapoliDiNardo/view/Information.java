package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Information {
	
	private String playerName;
	private static final String alienStartPosition = "L06";
	private static final String humanStartPosition = "L08";
	private static final String firstTurn = "1";
	private String actualPosition;
	private String turn;
	private List<String> item = new ArrayList<String>();
	private int selectedItem = 8;
	private int itemToRemove;
	private Coordinates moveCoord = new Coordinates();
	private Coordinates lightsCoord = new Coordinates();
	private String attackAnswer;
	
	
	public Information(int kindOfPlayer){
		this.turn = firstTurn;
		this.item.add("");
		if(kindOfPlayer == 1)  //Human palyer
			this.actualPosition = humanStartPosition;
		else     //Alien palyer
			this.actualPosition = alienStartPosition;
	}
	
	//Setters and getters
	public void setPlayerName(String name){
		this.playerName = name;
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public void setActualPosition(String position){
		this.actualPosition = position;
	}
	
	public String getActualPosition(){
		return this.actualPosition;
	}
	
	public void setTurn(String turn){
		this.turn = turn;
	}
	
	public String getTurn(){
		return this.turn;
	}
	
	public List<String> getItem(){
		return this.item;
	}
	
	public void setItem(List<String> objects){
		this.item = objects;
	}
	
	public void addToItem(int index, String object){
		this.item.add(index, object);
	}
	
	public void setSelectedItem(int i){
		this.selectedItem = i;
	}
	
	public int getSelectedItem(){
		return this.selectedItem;
	}
	
	public void setLightsCoord(Coordinates coord){
		this.lightsCoord = coord;
	}
	
	public Coordinates getLightsCoord(){
		return this.lightsCoord;
	}
	
	public Coordinates getMoveCoord(){
		return this.moveCoord;
	}
	
	public void setMoveCoord(Coordinates coord){
		this.moveCoord = coord;
	}
	
	public void setAttackAnswer(String str){
		this.attackAnswer = str;
	}
	
	public String getAttackAnswer(){
		return this.attackAnswer;
	}
	
	public void setItemToRemove(int i){
		this.itemToRemove = i;
	}
	
	public int getItemToRemove(){
		return this.itemToRemove;
	}
	
}
