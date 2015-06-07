package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public abstract class TurnFrame {
	
	protected JFrame frame;
	protected JLabel background;
	protected JTextArea comunication;
	protected JButton nextButton;
	protected JScrollBar jsb;
	
	public abstract TurnFrame update(String name, String position, String turn, List<String> objects);
	public abstract List<JLabel> setCardHandler(List<String> objects);
	
	public void dispose(){
		this.frame.dispose();
	}
	
	public void appendToTextArea(String string){
		comunication.append(string + "\n");
	}
	
	public JLabel getBackground(){
		return this.background;
	}
	
	public JButton getNextButton(){
		return this.nextButton;
	}
	
	public void setJsbValue(int value){
		jsb.setValue(value);
	}
	
	public int getJsbMaximum(){
		return jsb.getMaximum();
	}


}
