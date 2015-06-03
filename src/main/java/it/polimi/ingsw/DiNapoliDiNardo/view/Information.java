package it.polimi.ingsw.DiNapoliDiNardo.view;

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
	private int selectedItem;
	
	
	public Information(int kindOfPlayer){
		this.turn = firstTurn;
		this.item.add("");
		if(kindOfPlayer == 1)  //Human palyer
			this.actualPosition = humanStartPosition;
		else
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
	
	
}
