package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
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
	protected JLabel card1;
	protected JLabel card2;
	protected JLabel card3;
	protected JLabel nameLabel;
	protected JLabel turnLabel;
	protected JLabel timerLabel;
	protected JLabel positionLabel;
	protected JLabel map;
	List<String> item;
	
	
	public TurnFrame(String name, String actualPosition, String turn, List<String> objects) {
		initComponents();
		nameLabel.setText("   "+name);
		turnLabel.setText("Turn: " + turn); 
		positionLabel.setText("Position: " + actualPosition);
	}

	
	
	//public void commonBuildUp()
	
	
	
	public void update(String name, String position, String turn, List<String> objects, boolean startTurn) {
		if(startTurn)
			comunication.append(name + " you are now in the " + position + " position\n");
		turnLabel.setText("Turn: " + turn); 
		positionLabel.setText("Position: " + position);
		item = objects;
		if (item.size() == 1)
			if(!("".equals(item.get(0)))){
				card1.setIcon(new ImageIcon("externalresources\\" + item.get(0) + ".jpg"));
				card2.setIcon(new ImageIcon("externalresources\\BlankCard.png"));
				card3.setIcon(new ImageIcon("externalresources\\BlankCard.png"));
				//descCard1.setText(item.get(0));
			}
			else{
				card1.setIcon(new ImageIcon("externalresources\\BlankCard.png"));
				card2.setIcon(new ImageIcon("externalresources\\BlankCard.png"));
				card3.setIcon(new ImageIcon("externalresources\\BlankCard.png"));
			}
		if (item.size() == 2){
			card1.setIcon(new ImageIcon("externalresources\\" + item.get(0) + ".jpg"));
			card2.setIcon(new ImageIcon("externalresources\\" + item.get(1) + ".jpg"));
			card3.setIcon(new ImageIcon("externalresources\\BlankCard.png"));
			//descCard2.setText(item.get(1));
		}
		if (item.size() == 3){
			card1.setIcon(new ImageIcon("externalresources\\" + item.get(0) + ".jpg"));
			card2.setIcon(new ImageIcon("externalresources\\" + item.get(1) + ".jpg"));
			card3.setIcon(new ImageIcon("externalresources\\" + item.get(2) + ".jpg"));
			//descCard3.setText(item.get(2));
		}
	}
	
	
	protected abstract List<JLabel> setCardHandler(List<String> objects);
	
	protected abstract void initComponents();
	
	public void dispose(){
		this.frame.dispose();
	}
	
	
	public void appendToTextArea(String string){
		comunication.append(string);
	}
	
	
	public JButton getNextButton(){
		return this.nextButton;
	}
	
	
	public JLabel getBackgroundImage(){
		return this.map;
	}
	
	public void setMapImage(BufferedImage dimg){
		this.map.setIcon(new ImageIcon(dimg));
	}
	
	public void enlightCard(int index){
		if (index == 1 && (item.get(0) != ""))
			card1.setIcon(new ImageIcon("externalresources\\" + item.get(0) + "Inv.jpg"));
		if (index == 2 && item.size()>1)
			card2.setIcon(new ImageIcon("externalresources\\" + item.get(1) + "Inv.jpg"));
		if (index == 3 && item.size()>2)
			card3.setIcon(new ImageIcon("externalresources\\" + item.get(2) + "Inv.jpg"));
	}

	public void endarkCard(int index){
		if (index == 1 && (item.get(0) != ""))
			card1.setIcon(new ImageIcon("externalresources\\" + item.get(0) + ".jpg"));
		if (index == 2 && item.size()>1)
			card2.setIcon(new ImageIcon("externalresources\\" + item.get(1) + ".jpg"));
		if (index == 3 && item.size()>2)
			card3.setIcon(new ImageIcon("externalresources\\" + item.get(2) + ".jpg"));
	}
	
	public List<String> getItem(){
		return item;
	}
	
	
	
	
	
	
	
	
}



