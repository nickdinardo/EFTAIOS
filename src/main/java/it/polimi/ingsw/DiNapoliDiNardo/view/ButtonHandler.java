package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonHandler{

	private boolean waitAttack = false;
	private boolean waitCoordinates = false;
	private boolean waitItems = false;
	private String answer = "N";
	
	private class AttackButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Attack"){
				setAnswer("Y");
				setWaitAttack(true);
			}
		}
	}	
	
	
	private class NextButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Next"){
				//TODO update frame for next turn, maybe
				setWaitAttack(true);
				setWaitCoordinates(true);
				setWaitItems(true);
				
			}
		}

	}
	
	public void startAttackListen(JButton button){
		ActionListener press  = new AttackButtonHandler();
		button.addActionListener(press);
		
	}
	
	public void startNextListen(JButton button){
		ActionListener press = new NextButtonHandler();
		button.addActionListener(press);
	}
	
	public void setWaitAttack(boolean flag){
		this.waitAttack = flag;
	}
	
	public boolean getWaitAttack(){
		return this.waitAttack;
	}
	
	public void setWaitCoordinates(boolean flag){
		this.waitCoordinates = flag;
	}
	
	public boolean getWaitCoordinates(){
		return this.waitCoordinates;
	}
	
	public void setWaitItems(boolean flag){
		this.waitItems = flag;
	}
	
	public boolean getWaitItems(){
		return this.waitItems;
	}
	
	
	
	public void setAnswer(String string){
		this.answer = string;
	}
	
	public String getAnswer(){
		return this.answer;
	}
}
