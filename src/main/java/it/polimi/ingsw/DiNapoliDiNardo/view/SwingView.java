package it.polimi.ingsw.DiNapoliDiNardo.view;


import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class SwingView{
	
	/*private String playerName;
	private static final String alienStartPosition = "L06";
	private static final String humanStartPosition = "L08";
	private static final String firstTurn = "1";
	private String actualPosition;
	private String turn;
	private List<String> item = new ArrayList<String>();*/
	private Information info;
	private String name;
	private TurnFrame turnFrame;
	
	private ClickableBox boxes = new ClickableBox();
	private static final Coordinates defaultCoord = new Coordinates(0, 0);
	protected Coordinates coordinates = defaultCoord;
	private JFrame lightsFrame = new JFrame("Lights");
	private JScrollBar jsb;
	private Coordinates coordsForLights;
	private boolean waitForLights = false;
	private JFrame discardFrame = new JFrame("Discard");
	private Coordinates coordsForNoise;
	private boolean waitForNoise = false;
	private JLabel background;
	private boolean waitForMove = false;
	private JButton nextButton;
	private JButton attackButton;
	private String attackAnswer;
	private boolean waitForAttack = false;
	private boolean askForItem = false;
	private JTextArea comunication;
	private int itemToRemove;
	private boolean waitForDiscard = false;
	private JLabel noItems;
	private JLabel card1;
	private JLabel card2;
	private JLabel card3;
	private JLabel descCard1;
	private JLabel descCard2;
	private JLabel descCard3;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	
	
	public String askName(){
				
		RegistrationFrame registration = new RegistrationFrame(new JFrame("Registration to the match"));
		this.name = registration.getName();
		return name;
	}


	public void showBeingHuman(String name){
		
		HumanDescriptionFrame showHuman = new HumanDescriptionFrame(new JFrame("Description"), name);
		showHuman.getNext();
		info = new Information(1);
		info.setPlayerName(name); //TODO da controllare quando completo
		turnFrame = new HumanTurnFrame(info.getPlayerName(), info.getActualPosition(), info.getTurn(), info.getItem() );
		
	}
	
	
	public void showBeingAlien(String name){
		
		AlienDescriptionFrame showAlien = new AlienDescriptionFrame(new JFrame("Description"), name);
		showAlien.getNext();
		info = new Information(2);
		info.setPlayerName(name); //TODO da controllare quando completo
		turnFrame = new AlienTurnFrame(info.getPlayerName(), info.getActualPosition(), info.getTurn(), info.getItem() );
	}
	
	
	public void showActualSituation (String name, String position, String objects, String turn){
		info.setPlayerName(name);
		info.setActualPosition(position);
		if (position.length() == 2)
			info.setActualPosition(position.substring(0, 1) + "0" + position.substring(1, position.length()));
		info.setTurn(turn);
		if (!"no".equals(objects)){
			info.setItem(Arrays.asList(objects.split(";")));
		}
		else
			info.addToItem(0, "");
		turnFrame.dispose();
		turnFrame = turnFrame.update(info.getPlayerName(), info.getActualPosition(), info.getTurn(), info.getItem() );
		
	}
	
	
	public int askItemUse(String objects){
		
		if (!"no".equals(objects)){
			info.setItem(Arrays.asList(objects.split(";")));
		}
		else
			info.addToItem(0, "");
		CardHandler cardHandler = new CardHandler();
		cardHandler.setCards(turnFrame.setCardHandler(info.getItem()));
		String str = "";
		while(cardHandler.getWaitForItem() == false){
			str += "avoided";
			if(str.length() > 10000)
				str = "";
		}
		info.setSelectedItem(cardHandler.getIndexCard());
		return info.getSelectedItem();
	}
	
	//TODO
	public Coordinates askForLights(){
		LightsFrame lightsframe = new LightsFrame();
		String str = "";
		while(waitForLights == false){
			str += "avoided";
			if(str.length() > 10000)
				str = "";
		}
		return coordsForLights;
		
	}
	
	private class LightsHandler implements MouseListener {

		public void mouseClicked(MouseEvent event) {
			
			Point click = event.getPoint();
			coordsForLights  = boxes.getMinimumDistance(click);	
			waitForLights = true;
		
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

	}	
	
	
	private class NoiseHandler implements MouseListener {

		public void mouseClicked(MouseEvent event) {
			
			Point click = event.getPoint();
			coordsForNoise  = boxes.getMinimumDistance(click);	
			waitForNoise = true;
		
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

	}	
	
	private class BoxHandler implements MouseListener {

		public void mouseClicked(MouseEvent event) {
			
			Point click = event.getPoint();
			coordinates  = boxes.getMinimumDistance(click);	
			//flag = true;
		
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

	}	
	
	private class AttackButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Attack"){
				attackAnswer = "Y";
				//waitForAttack = true;
			}
		}

	}
	
	public String askForAttack(){
		attackAnswer = "N";
		ActionListener attack = new AttackButtonHandler();
		attackButton.addActionListener(attack);
		String str = "";
		while(waitForAttack == false){
			str += "avoided";
			if(str.length() > 10000)
				str = "";
		}
		return attackAnswer;
			
	}
	
	public Coordinates askMovement(boolean reask){
		if(reask)
			comunication.append("The movement you selected is not valid. Please select another box.\n");
		MouseListener boxClick = new BoxHandler();
		background.addMouseListener(boxClick);
		String str = "";
		while (coordinates.getCoordX() == 0 && coordinates.getCoordY() == 0){
			if(!waitForMove){
				str += "avoided";
				if(str.length() > 10000)
					str = "";
			}
			else{
				background.addMouseListener(boxClick);
				waitForMove = false;
			}
		}
		//Solo dopo aver inserito le coordinate è possibile passare al turno successivo
		ActionListener nextTurn = new NextButtonHandler();
		nextButton.addActionListener(nextTurn);
		return this.coordinates;
	}
	
	public String askForNoise(){
		//TODO
		System.out.println("ciao");
		comunication.append("You drew a NoiseAnywhere card\n");
		comunication.append("In which sector of the map do you want to declare there's noise?\n");
		System.out.println("ciao");
		MouseListener noiseClick = new NoiseHandler();
		background.addMouseListener(noiseClick);
		String str = "";
		while(waitForNoise == false){
			str += "avoided";
			if(str.length() > 10000)
				str = "";
		}
		String noise = ""+(char)(coordsForNoise.getCoordX()+64);
		String number = ""+ coordsForNoise.getCoordY();
		if (number.length() == 1)
			number = "0"+ coordsForNoise.getCoordY();
		noise += number;
		return noise;
		
	}
	
	
	private class NextButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Next"){
				//TODO update frame for next turn
				askForItem = true;
				waitForAttack = true;
				//update frame
			}
		}

	}
	
	public int askHumanItemDiscard(String objects){
	
		ImageIcon icon = new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\icon2.jpg");
	    discardFrame.setIconImage(icon.getImage());
	    discardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    discardFrame.getContentPane().setBackground(Color.black);
	    discardFrame.setLayout(new BorderLayout());
		Font f = new Font("Arial", Font.BOLD, 12);
		JLabel background = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\mqdefault.jpg"));
		FlowLayout flowlayout = new FlowLayout();
		flowlayout.setHgap(80);
		flowlayout.setVgap(25);
		background.setLayout(flowlayout);
		JToolTip[] itemDescription = setToolTip(item);
		card1 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(0) + ".png"));
		card1.setToolTipText(itemDescription[0].getTipText());
		MouseListener toRemove1 = new RemoveHandler1();
		card1.addMouseListener(toRemove1);
		descCard1 = new JLabel(item.get(0));
		descCard1.setForeground(Color.green);
		descCard1.setFont(f);
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setBackground(Color.black);
		panel1.add(card1, BorderLayout.NORTH);
		panel1.add(descCard1, BorderLayout.SOUTH);
		background.add(panel1);
		card2 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(1) + ".png"));
		card2.setToolTipText(itemDescription[1].getTipText());
		MouseListener toRemove2 = new RemoveHandler2();
		card2.addMouseListener(toRemove2);
		descCard2 = new JLabel(item.get(1));
		descCard2.setForeground(Color.green);
		descCard2.setFont(f);
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setBackground(Color.black);
		panel2.add(card2, BorderLayout.NORTH);
		panel2.add(descCard2, BorderLayout.SOUTH);
		background.add(panel2);
		card3 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(2) + ".png"));
		card3.setToolTipText(itemDescription[2].getTipText());
		MouseListener toRemove3 = new RemoveHandler3();
		card3.addMouseListener(toRemove3);
		descCard3 = new JLabel(item.get(2));
		descCard3.setForeground(Color.green);
		descCard3.setFont(f);
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.setBackground(Color.black);
		panel3.add(card3, BorderLayout.NORTH);
		panel3.add(descCard3, BorderLayout.SOUTH);
		background.add(panel3);	
		JButton button1 = new JButton("No");
		button1.setBackground(Color.green);
		button1.setForeground(Color.black);
		button1.setFocusable(false);
		ActionListener buttonHandler1 = new DiscardHandler1();
		button1.addActionListener(buttonHandler1);
		JButton button2 = new JButton("Use an item");
		button2.setBackground(Color.green);
		button2.setForeground(Color.black);
		button2.setFocusable(false);
		ActionListener buttonHandler2 = new DiscardHandler2();
		button2.addActionListener(buttonHandler2);
		background.add(button2);
		background.add(button1);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.black);
		JLabel label1 = new JLabel("You drew an item card but your three card slots are full."); 
		label1.setFont(f);
		label1.setForeground(Color.green);
		JLabel label2 = new JLabel("Do you want to discard a card you have to free one slot for the new card?");
		label2.setFont(f);
		label2.setForeground(Color.green);
		panel1.add(label1);
		panel1.add(label2);
		discardFrame.add(panel1, BorderLayout.CENTER);
		discardFrame.add(background, BorderLayout.SOUTH);
		discardFrame.pack();
		discardFrame.setSize(500, 270);
		discardFrame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		discardFrame.setLocation ( ( screenSize.width / 2 ) - ( discardFrame.getWidth ( ) / 2 ), 
				((screenSize.height / 2 ) - ( discardFrame.getHeight ( ) / 2 )) );
		discardFrame.setVisible(true);
		String str = "";
		while(waitForDiscard == false){
			str += "avoided";
			if(str.length() > 10000);
				str = "";
		}
		waitForDiscard = false;
		return itemToRemove;
	}
	
	public int askAlienItemDiscard(String objects){
		
		ImageIcon icon = new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\icon2.jpg");
	    discardFrame.setIconImage(icon.getImage());
	    discardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    discardFrame.getContentPane().setBackground(Color.black);
	    discardFrame.setLayout(new BorderLayout());
		Font f = new Font("Arial", Font.BOLD, 12);
		JLabel background = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\mqdefault.jpg"));
		FlowLayout flowlayout = new FlowLayout();
		flowlayout.setHgap(80);
		flowlayout.setVgap(25);
		background.setLayout(flowlayout);
		JToolTip[] itemDescription = setToolTip(item);
		card1 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(0) + ".png"));
		card1.setToolTipText(itemDescription[0].getTipText());
		MouseListener toRemove1 = new RemoveHandler1();
		card1.addMouseListener(toRemove1);
		descCard1 = new JLabel(item.get(0));
		descCard1.setForeground(Color.green);
		descCard1.setFont(f);
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setBackground(Color.black);
		panel1.add(card1, BorderLayout.NORTH);
		panel1.add(descCard1, BorderLayout.SOUTH);
		background.add(panel1);
		card2 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(1) + ".png"));
		card2.setToolTipText(itemDescription[1].getTipText());
		MouseListener toRemove2 = new RemoveHandler2();
		card2.addMouseListener(toRemove2);
		descCard2 = new JLabel(item.get(1));
		descCard2.setForeground(Color.green);
		descCard2.setFont(f);
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setBackground(Color.black);
		panel2.add(card2, BorderLayout.NORTH);
		panel2.add(descCard2, BorderLayout.SOUTH);
		background.add(panel2);
		card3 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(2) + ".png"));
		card3.setToolTipText(itemDescription[2].getTipText());
		MouseListener toRemove3 = new RemoveHandler3();
		card3.addMouseListener(toRemove3);
		descCard3 = new JLabel(item.get(2));
		descCard3.setForeground(Color.green);
		descCard3.setFont(f);
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.setBackground(Color.black);
		panel3.add(card3, BorderLayout.NORTH);
		panel3.add(descCard3, BorderLayout.SOUTH);
		background.add(panel3);	
		JButton button1 = new JButton("No");
		button1.setBackground(Color.green);
		button1.setForeground(Color.black);
		button1.setFocusable(false);
		ActionListener buttonHandler1 = new DiscardHandler1();
		button1.addActionListener(buttonHandler1);
		background.add(button1);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.black);
		JLabel label1 = new JLabel("You drew an item card but your three card slots are full."); 
		label1.setFont(f);
		label1.setForeground(Color.green);
		JLabel label2 = new JLabel("Do you want to discard a card you have to free one slot for the new card?");
		label2.setFont(f);
		label2.setForeground(Color.green);
		panel1.add(label1);
		panel1.add(label2);
		discardFrame.add(panel1, BorderLayout.CENTER);
		discardFrame.add(background, BorderLayout.SOUTH);
		discardFrame.pack();
		discardFrame.setSize(500, 270);
		discardFrame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		discardFrame.setLocation ( ( screenSize.width / 2 ) - ( discardFrame.getWidth ( ) / 2 ), 
				((screenSize.height / 2 ) - ( discardFrame.getHeight ( ) / 2 )) - 20 );
		discardFrame.setVisible(true);
		String str = "";
		while(waitForDiscard == false){
			str += "avoided";
			if(str.length() > 10000);
				str = "";
		}
		waitForDiscard = false;
		return itemToRemove;
	}
	
	private class RemoveHandler1 implements MouseListener {

		public void mouseClicked(MouseEvent event) {
			itemToRemove = 0 + 3;
			waitForDiscard = true;
			discardFrame.dispose();
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

	}	
	
	private class RemoveHandler2 implements MouseListener {

		public void mouseClicked(MouseEvent event) {
			itemToRemove = 1 + 3;
			waitForDiscard = true;
			discardFrame.dispose();
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

	}	
	
	private class RemoveHandler3 implements MouseListener {

		public void mouseClicked(MouseEvent event) {
			itemToRemove = 2 + 3;
			waitForDiscard = true;
			discardFrame.dispose();
		
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

	}	
	
	private class DiscardHandler1 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			itemToRemove = 8;
			waitForDiscard = true;
			discardFrame.dispose();
		}

	}
	
	private class DiscardHandler2 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			itemToRemove = 18;
			waitForDiscard = true;
			discardFrame.dispose();
		}

	}
	
	public void print (String message){
		comunication.append(message + "\n");
	}

	

	
	//Setter and getter
		public void setCoordinates(Coordinates coord){
			this.coordinates = coord;
		}

}
	



