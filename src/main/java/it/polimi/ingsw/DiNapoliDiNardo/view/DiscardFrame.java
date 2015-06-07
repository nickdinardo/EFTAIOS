package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DiscardFrame {

	protected JFrame frame;
	protected JLabel card1;
	protected JLabel card2;
	protected JLabel card3;
	protected JButton button;
	
	public List<JLabel> setCardHandler(List<String> item){
		List<JLabel> cards = new ArrayList<JLabel>();
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		return cards;
		
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
	
	public JButton getButtonNo(){
		return this.button;
	}
}
