package it.polimi.ingsw.dinapolidinardo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Class that manages the initialization and display of the Frame 
 * with the Sector selection in the LightsCard use
 * 
 * @see it.polimi.ingsw.dinapolidinardo.model.cards.LightsCard
 */
public class LightsFrame{
	
	private JFrame frame;
	private JLabel background;

	
	/**
	 * Constructor, also initialize and display all the components
	 * 
	 * @param flag signals if it's the first time asking a sector or not
	 */
	public LightsFrame(boolean flag){
		
		this.frame = new JFrame("Lights");
		ImageIcon icon = new ImageIcon("externalresources\\icon2.jpg");
	    frame.setIconImage(icon.getImage());
		frame.getContentPane().setBackground(Color.black);
		frame.setUndecorated(true);
		frame.setLayout(new BorderLayout());
		background = new JLabel(new ImageIcon("externalresources\\galileiDefinitiva.png"));
		background.setLayout(new FlowLayout());
		background.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
		frame.add(background, BorderLayout.SOUTH);
		Font f = new Font("Impact", 0, 24);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.black);
		if(!flag){
			JLabel label = new JLabel("Which sector of the map do you want to enlight? Click on the map");
			label.setForeground(Color.lightGray);
			label.setBackground(Color.black);
			label.setFont(f);
			panel.add(label);
		}else{
			JLabel label = new JLabel("The sector you selected to enlight is not valid. Please select another box.");
			label.setForeground(Color.lightGray);
			label.setBackground(Color.black);
			label.setFont(f);
			panel.add(label);
		}
		frame.add(panel, BorderLayout.NORTH);
		frame.pack();
		frame.setSize(759, 630);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				((screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 )) - 20 );
		frame.setVisible(true);
		
		}
	
	
	
	// getters and setters
	
	public JLabel getBackground(){
		return this.background;
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
}
