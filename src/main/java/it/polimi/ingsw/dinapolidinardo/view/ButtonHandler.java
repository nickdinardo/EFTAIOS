package it.polimi.ingsw.dinapolidinardo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



/**
 * Class that manages all the Button Listeners of the main game Frame
 */
public class ButtonHandler{

	private boolean waitAttack = false;
	private boolean waitItems = false;
	private String answer = "N";
	
	
	/**
	 * Listener of the "Attack" button 
	 */
	private class AttackButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Attack"){
				setAnswer("Y");
				setWaitAttack(true);
			}
		}
	}	
	
	
	/**
	 * Listener of the "Next" button
	 */
	private class NextButtonHandler implements ActionListener{
		private int type = 0;
		
		public NextButtonHandler (int t){
			this.type = t;
		}
		
		@Override
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Next"){
				
				//human frame next button
				if (type == 1){
					setWaitItems(true);
				}
				//alien frame next button
				if (type == 2){
					setAnswer("N");
					setWaitAttack(true);
					
				}
				
				
			}
		}

	}
	
	
	/**
	 * Starts the listener on the "Attack" button
	 * 
	 * @param button the frame button on which activate the listener
	 * @return the activated ActionListener
	 */
	public ActionListener startAttackListen(JButton button){
		ActionListener press  = new AttackButtonHandler();
		button.addActionListener(press);
		return press;
		
	}
	
	
	/**
	 * Starts the listener on the "Next" button
	 * 
	 * @param button the frame button on which activate the listener
	 * @param type if 1, indicates the button belongs to a human frame, if 2, to an alien frame
	 * @return the activated ActionListener
	 */	
	public ActionListener startNextListen(JButton button, int type){
		ActionListener press = new NextButtonHandler(type);
		button.addActionListener(press);
		return press;
	}
	
	
	//getters and setters
	
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
