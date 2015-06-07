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
import javax.swing.JToolTip;

public class AlienDiscardFrame extends DiscardFrame{
	
	public AlienDiscardFrame(List<String> objects){
		
		frame = new JFrame("Discard");
		ImageIcon icon = new ImageIcon("externalresources\\icon2.jpg");
	    frame.setIconImage(icon.getImage());
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.getContentPane().setBackground(Color.black);
	    frame.setLayout(new BorderLayout());
		Font f = new Font("Arial", Font.BOLD, 12);
		JLabel background = new JLabel(new ImageIcon("externalresources\\mqdefault.jpg"));
		FlowLayout flowlayout = new FlowLayout();
		flowlayout.setHgap(80);
		flowlayout.setVgap(25);
		background.setLayout(flowlayout);
		ToolTipCard tips = new ToolTipCard();
		JToolTip[] itemDescription = tips.setToolTip(objects);
		card1 = new JLabel(new ImageIcon("externalresources\\" + objects.get(0) + ".png"));
		card1.setToolTipText(itemDescription[0].getTipText());
		JLabel descCard1 = new JLabel(objects.get(0));
		descCard1.setForeground(Color.green);
		descCard1.setFont(f);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setBackground(Color.black);
		panel1.add(card1, BorderLayout.NORTH);
		panel1.add(descCard1, BorderLayout.SOUTH);
		background.add(panel1);
		card2 = new JLabel(new ImageIcon("externalresources\\" + objects.get(1) + ".png"));
		card2.setToolTipText(itemDescription[1].getTipText());
		JLabel descCard2 = new JLabel(objects.get(1));
		descCard2.setForeground(Color.green);
		descCard2.setFont(f);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setBackground(Color.black);
		panel2.add(card2, BorderLayout.NORTH);
		panel2.add(descCard2, BorderLayout.SOUTH);
		background.add(panel2);
		card3 = new JLabel(new ImageIcon("externalresources\\" + objects.get(2) + ".png"));
		card3.setToolTipText(itemDescription[2].getTipText());
		JLabel descCard3 = new JLabel(objects.get(2));
		descCard3.setForeground(Color.green);
		descCard3.setFont(f);
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.setBackground(Color.black);
		panel3.add(card3, BorderLayout.NORTH);
		panel3.add(descCard3, BorderLayout.SOUTH);
		background.add(panel3);	
		button = new JButton("No");
		button.setBackground(Color.green);
		button.setForeground(Color.black);
		button.setFocusable(false);
		background.add(button);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.black);
		JLabel label1 = new JLabel("You drew an item card but your three card slots are full."); 
		label1.setFont(f);
		label1.setForeground(Color.green);
		JLabel label2 = new JLabel("Do you want to discard a card you have to free one slot for the new card?");
		label2.setFont(f);
		label2.setForeground(Color.green);
		panel.add(label1);
		panel.add(label2);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(background, BorderLayout.SOUTH);
		frame.pack();
		frame.setSize(500, 270);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				((screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 )) - 20 );
		frame.setVisible(true);
	}
	
}
