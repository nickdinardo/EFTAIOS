package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolTip;


public class AlienTurnFrame extends TurnFrame {
	
	private JButton attackButton;
	
	private	JLabel card1;
	private	JLabel descCard1;
	private JPanel panel1;
	private JLabel card2;
	private	JLabel descCard2;
	private JPanel panel2;
	private JLabel card3;
	private	JLabel descCard3;
	private JPanel panel3;
	
	private JTextArea comunication;
	private JLabel turnLabel;
	private JLabel nameLabel;
	private JLabel positionLabel;
	Font f1 = new Font("Arial", Font.BOLD, 12);
	Font f2 = new Font("Arial", Font.BOLD, 18);
	private static final Color aliencolor = Color.pink;

	
	
	public AlienTurnFrame(String name, String actualPosition, String turn, List<String> objects){
		
		frame = new JFrame("GalileiMap");
		ImageIcon icon = new ImageIcon("externalresources\\icon2.jpg");
	    frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.black);
		frame.setLayout(new BorderLayout());
		background = new JLabel(new ImageIcon("externalresources\\galilei1.jpg"));
		background.setLayout(new FlowLayout());
		frame.add(background, BorderLayout.SOUTH);
		comunication = new JTextArea();
		comunication.setBackground(Color.darkGray);
		comunication.setForeground(aliencolor);
		Font f1 = new Font("Arial", Font.BOLD, 12);
		Font f2 = new Font("Arial", Font.BOLD, 18);
		comunication.setFont(f1);
		comunication.setAutoscrolls(true);
		comunication.setText("Server Comunications:\n");
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
		BorderLayout borderlayout = new BorderLayout();
		borderlayout.setVgap(5);
		panel.setLayout(flowlayout1);
		panel.setBackground(Color.black);
		JPanel panel6 = new JPanel();
		panel6.setLayout(flowlayout2);
		panel6.setBackground(Color.black);
		positionLabel = new JLabel("Position: " + actualPosition);
		positionLabel.setFont(f1);
		positionLabel.setForeground(aliencolor);
		nameLabel = new JLabel(name);
		nameLabel.setFont(f2);
		nameLabel.setForeground(aliencolor);
		turnLabel = new JLabel("Turn: " + turn);
		turnLabel.setFont(f1);
		turnLabel.setForeground(aliencolor);
		panel6.add(nameLabel);
		panel6.add(turnLabel);
		panel6.add(positionLabel);
		Dimension preferredSize = new Dimension(80, 80);
		panel6.setPreferredSize(preferredSize);
		panel.add(panel6);
		
		//ToolTipCard tips = new ToolTipCard();
		//JToolTip[] itemDescription = tips.setToolTip(item);
		
		card1 = new JLabel(new ImageIcon("externalresources\\BlankCard.png"));
		//card1.setToolTipText(itemDescription[0].getTipText());
		descCard1 = new JLabel("EmptyCardSlot");
		descCard1.setForeground(aliencolor);
		descCard1.setFont(f1);
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setBackground(Color.black);
		panel1.add(card1, BorderLayout.NORTH);
		panel1.add(descCard1, BorderLayout.SOUTH);
		panel.add(panel1);
		card2 = new JLabel(new ImageIcon("externalresources\\BlankCard.png"));
		//card2.setToolTipText(itemDescription[1].getTipText());
		descCard2 = new JLabel("EmptyCardSlot");
		descCard2.setForeground(aliencolor);
		descCard2.setFont(f1);
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setBackground(Color.black);
		panel2.add(card2, BorderLayout.NORTH);
		panel2.add(descCard2, BorderLayout.SOUTH);
		panel.add(panel2);
		card3 = new JLabel(new ImageIcon("externalresources\\BlankCard.png"));
		//card3.setToolTipText(itemDescription[2].getTipText());
		descCard3 = new JLabel("EmptyCardSlot");
		descCard3.setForeground(aliencolor);
		descCard3.setFont(f1);
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.setBackground(Color.black);
		panel3.add(card3, BorderLayout.NORTH);
		panel3.add(descCard3, BorderLayout.SOUTH);
		panel.add(panel3);
		
		JScrollPane panel4 = new JScrollPane(comunication);
		panel4.setBackground(Color.black);
		panel4.setPreferredSize(new Dimension(700, 52));
		panel4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsb = panel4.getVerticalScrollBar();
		setJsbValue(jsb.getMaximum());
		JPanel panel5 = new JPanel();
		panel5.setBackground(Color.black);
		panel5.setLayout(borderlayout);
		nextButton = new JButton("Next");
		nextButton.setBackground(Color.black);
		nextButton.setForeground(aliencolor);
		nextButton.setFocusable(false);
		attackButton = new JButton("Attack");
		attackButton.setBackground(Color.black);
		attackButton.setForeground(aliencolor);
		attackButton.setFocusable(false);
		panel5.add(attackButton, BorderLayout.NORTH);
		panel5.add(nextButton, BorderLayout.SOUTH);
		panel.add(panel5);
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
	public void update(String name, String position, String turn, List<String> objects) {
		comunication.append(name + " you are now in the " + position + " position\n");
		turnLabel.setText("Turn: " + turn); 
		positionLabel.setText("Position: " + position);
		List<String> item = objects;
		if (item.size()>0)
			if(!("".equals(item.get(0)))){
				card1.setIcon(new ImageIcon("externalresources\\" + item.get(0) + ".png"));
				descCard1.setText(item.get(0));
			}
		if (item.size()>1){
			card2.setIcon(new ImageIcon("externalresources\\" + item.get(1) + ".png"));
			descCard2.setText(item.get(1));
		}
		if (item.size()>2){
			card3.setIcon(new ImageIcon("externalresources\\" + item.get(2) + ".png"));
			descCard3.setText(item.get(2));
		}
	}
	
	public List<JLabel> setCardHandler(List<String> item){
		return null;
	}
	
	public JButton getAttackButton(){
		return this.attackButton;
	}
	
	public void appendToTextArea(String string){
		comunication.append(string);
	}
}

