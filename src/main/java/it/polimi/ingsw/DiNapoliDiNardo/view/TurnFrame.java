package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

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
	protected JLabel jLabel3;
	protected JLabel imageLabel;
    protected JScrollPane jScrollPane1;
    protected String defaultFont = "Impact";   
	protected List<String> item;
	
	
	public TurnFrame(String name, String actualPosition, String turn) {
		initComponents();
		nameLabel.setText("   "+name);
		turnLabel.setText("Turn: " + turn); 
		positionLabel.setText("Position: " + actualPosition);
	}

	
	
	public void commonBuildUp(String imagePath, Color commColor){
		
		frame = new JFrame("GalileiMap");
        map = new JLabel();
        jScrollPane1 = new JScrollPane();
        comunication = new JTextArea();
        
        card1 = new JLabel();
        card2 = new JLabel();
        card3 = new JLabel();
        jLabel3 = new JLabel();
        imageLabel = new JLabel();
        nextButton = new JButton();
        nameLabel = new JLabel();
        turnLabel = new JLabel();
        timerLabel = new JLabel();
        positionLabel = new JLabel();
       		
        map.setIcon(new javax.swing.ImageIcon("externalresources\\galileiDefinitiva.png")); 
        map.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        map.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        
        comunication.setEditable(false);
        comunication.setBackground(commColor);
        comunication.setColumns(20);
        comunication.setRows(5);
        comunication.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        DefaultCaret caret = (DefaultCaret)comunication.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jScrollPane1.setViewportView(comunication);

        card1.setIcon(new javax.swing.ImageIcon("externalresources\\BlankCard.png"));
        card1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        card2.setIcon(new javax.swing.ImageIcon("externalresources\\BlankCard.png")); 
        card2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        card3.setIcon(new javax.swing.ImageIcon("externalresources\\BlankCard.png")); 
        card3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        imageLabel.setIcon(new javax.swing.ImageIcon(imagePath)); 

        nameLabel.setFont(new java.awt.Font(defaultFont, 0, 36)); 
        turnLabel.setFont(new java.awt.Font(defaultFont, 0, 18)); 
        positionLabel.setFont(new java.awt.Font(defaultFont, 0, 18)); 
        
        nextButton.setText("Next");
        nextButton.setBackground(Color.black);
		nextButton.setForeground(commColor);
		nextButton.setFocusable(false);
	
	}
	
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



