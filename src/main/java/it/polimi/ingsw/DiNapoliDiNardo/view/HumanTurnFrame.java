package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolTip;

public class HumanTurnFrame extends TurnFrame{

	private	JLabel card1;
	private JLabel card2;
	private JLabel card3;
		
	public HumanTurnFrame(String name, String actualPosition, String turn, List<String> objects){
		
		frame = new JFrame("Galilei Map");
		ImageIcon icon = new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\icon2.jpg");
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.black);
		frame.setLayout(new BorderLayout());
		background = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\galilei1.jpg"));
		background.setLayout(new FlowLayout());
		frame.add(background, BorderLayout.SOUTH);
		comunication = new JTextArea();
		comunication.setBackground(Color.gray);
		comunication.setForeground(Color.green);
		Font f1 = new Font("Arial", Font.BOLD, 12);
		Font f2 = new Font("Arial", Font.BOLD, 18);
		comunication.setFont(f1);
		comunication.setAutoscrolls(true);
		comunication.setText("Server Comunications:\n");
		comunication.append(name + " you are now in the " + actualPosition + " position\n");
		comunication.setColumns(60);
		comunication.setRows(3);
		comunication.setEditable(false);
		JPanel panel = new JPanel();
		FlowLayout flowlayout1 = new FlowLayout();
		flowlayout1.setHgap(60);
		flowlayout1.setVgap(7);
		FlowLayout flowlayout2 = new FlowLayout();
		flowlayout2.setHgap(5);
		flowlayout2.setVgap(7);
		panel.setLayout(flowlayout1);
		panel.setBackground(Color.black);
		JPanel panel6 = new JPanel();
		panel6.setBackground(Color.black);
		panel6.setLayout(flowlayout2);
		JLabel positionLabel = new JLabel("Position: " + actualPosition);
		positionLabel.setFont(f1);
		positionLabel.setForeground(Color.green);
		JLabel nameLabel = new JLabel(name);
		nameLabel.setFont(f2);
		nameLabel.setForeground(Color.green);
		JLabel turnLabel = new JLabel("Turn: " + turn);
		turnLabel.setFont(f1);
		turnLabel.setForeground(Color.green);
		panel6.add(nameLabel);
		panel6.add(turnLabel);
		panel6.add(positionLabel);
		Dimension preferredSize = new Dimension(80, 80);
		panel6.setPreferredSize(preferredSize);
		panel.add(panel6);
		List<String> item = objects;
		ToolTipCard tips = new ToolTipCard();
		JToolTip[] itemDescription = tips.setToolTip(item);
		switch(item.size()){
			case 1:
				if(item.get(0) == ""){
					JLabel noItems = new JLabel("You have no objects!");
					noItems.setFont(f1);
					noItems.setBackground(Color.black);
					noItems.setForeground(Color.green);
					JPanel panel1 = new JPanel();
					panel1.setLayout(new BorderLayout());
					panel1.setBackground(Color.black);
					panel1.add(noItems, BorderLayout.CENTER);
					panel.add(panel1);
					break;
				}
				else{
					card1 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(0) + ".png"));
					card1.setToolTipText(itemDescription[0].getTipText());
					JLabel descCard1 = new JLabel(item.get(0));
					descCard1.setForeground(Color.green);
					descCard1.setFont(f1);
					JPanel panel1 = new JPanel();
					panel1.setLayout(new BorderLayout());
					panel1.setBackground(Color.black);
					panel1.add(card1, BorderLayout.NORTH);
					panel1.add(descCard1, BorderLayout.SOUTH);
					panel.add(panel1);
					break;
				}
			case 2:
				card1 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(0) + ".png"));
				card1.setToolTipText(itemDescription[0].getTipText());
				JLabel descCard1 = new JLabel(item.get(0));
				descCard1.setForeground(Color.green);
				descCard1.setFont(f1);
				JPanel panel1 = new JPanel();
				panel1.setLayout(new BorderLayout());
				panel1.setBackground(Color.black);
				panel1.add(card1, BorderLayout.NORTH);
				panel1.add(descCard1, BorderLayout.SOUTH);
				panel.add(panel1);
				card2 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(1) + ".png"));
				card2.setToolTipText(itemDescription[1].getTipText());
				JLabel descCard2 = new JLabel(item.get(1));
				descCard2.setForeground(Color.green);
				descCard2.setFont(f1);
				JPanel panel2 = new JPanel();
				panel2.setLayout(new BorderLayout());
				panel2.setBackground(Color.black);
				panel2.add(card2, BorderLayout.NORTH);
				panel2.add(descCard2, BorderLayout.SOUTH);
				panel.add(panel2);
				break;
			case 3:
				card1 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(0) + ".png"));
				card1.setToolTipText(itemDescription[0].getTipText());
				descCard1 = new JLabel(item.get(0));
				descCard1.setForeground(Color.green);
				descCard1.setFont(f1);
				panel1 = new JPanel();
				panel1.setLayout(new BorderLayout());
				panel1.setBackground(Color.black);
				panel1.add(card1, BorderLayout.NORTH);
				panel1.add(descCard1, BorderLayout.SOUTH);
				panel.add(panel1);
				card2 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(1) + ".png"));
				card2.setToolTipText(itemDescription[1].getTipText());
				descCard2 = new JLabel(item.get(1));
				descCard2.setForeground(Color.green);
				descCard2.setFont(f1);
				panel2 = new JPanel();
				panel2.setLayout(new BorderLayout());
				panel2.setBackground(Color.black);
				panel2.add(card2, BorderLayout.NORTH);
				panel2.add(descCard2, BorderLayout.SOUTH);
				panel.add(panel2);
				card3 = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\ItemCard\\" + item.get(2) + ".png"));
				card3.setToolTipText(itemDescription[2].getTipText());
				JLabel descCard3 = new JLabel(item.get(2));
				descCard3.setForeground(Color.green);
				descCard3.setFont(f1);
				JPanel panel3 = new JPanel();
				panel3.setLayout(new BorderLayout());
				panel3.setBackground(Color.black);
				panel3.add(card3, BorderLayout.NORTH);
				panel3.add(descCard3, BorderLayout.SOUTH);
				panel.add(panel3);
				break;
				
			}
			
			JScrollPane panel4 = new JScrollPane(comunication);
			panel4.setBackground(Color.black);
			panel4.setPreferredSize(new Dimension(700, 52));
			panel4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panel4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jsb = panel4.getVerticalScrollBar();
			jsb.setValue(jsb.getMaximum());
			nextButton = new JButton("Next");
			nextButton.setBackground(Color.lightGray);
			nextButton.setForeground(Color.black);
			nextButton.setFocusable(false);
			panel.add(nextButton);
			panel.add(panel4);
			frame.add(panel,  BorderLayout.CENTER);
			frame.pack();
			frame.setSize(740, 720);
			frame.setResizable(false);
			Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
					((screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 )) - 20 );
			frame.setVisible(true);
		
	}
	
	@Override
	public TurnFrame update(String name, String position, String turn, List<String> objects) {
		TurnFrame frame = new HumanTurnFrame(name, position, turn, objects);
		return frame;
	}
	
	/*public void callCardListeners(List<String> item){
		switch(item.size()){
		case 1:
			if(!item.isEmpty()){
				card1.addMouseListener(clickableCard1);
				break;
			}
		case 2:
			card1.addMouseListener(clickableCard1);
			card2.addMouseListener(clickableCard2);
			break;
		case 3:
			card1.addMouseListener(clickableCard1);
			card2.addMouseListener(clickableCard2);
			card3.addMouseListener(clickableCard3);
			break;
		}
	}*/
	
	public List<JLabel> setCardHandler(List<String> item){
		
		List<JLabel> cards = new ArrayList<JLabel>();
		switch(item.size()){
			case 1:
				if(item.get(0) != "")
					cards.add(card1);
					break;
			case 2:
				cards.add(card1);
				cards.add(card2);
				break;
			case 3:
				cards.add(card1);
				cards.add(card2);
				cards.add(card3);
			
		}
		
		return cards;
	
	}
	
	
}
