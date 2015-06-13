package it.polimi.ingsw.dinapolidinardo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonHandler{

	private boolean waitAttack = false;
	private boolean waitItems = false;
	private String answer = "N";
	
	private class AttackButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Attack"){
				setAnswer("Y");
				setWaitAttack(true);
			}
		}
	}	
	
	
	private class NextButtonHandler implements ActionListener{
		private int type = 0;
		
		public NextButtonHandler (int t){
			this.type = t;
		}
		
		@Override
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Next"){
				
				if (type == 1){
					setWaitItems(true);
				}
				if (type == 2){
					setAnswer("N");
					setWaitAttack(true);
					
				}
				
				
			}
		}

	}
	
	public ActionListener startAttackListen(JButton button){
		ActionListener press  = new AttackButtonHandler();
		button.addActionListener(press);
		return press;
		
	}
		
	public ActionListener startNextListen(JButton button, int type){
		ActionListener press = new NextButtonHandler(type);
		button.addActionListener(press);
		return press;
	}
	
	public void setWaitAttack(boolean flag){
		this.waitAttack = flag;
	}
	
	public boolean getWaitAttack(){
		return this.waitAttack;
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
