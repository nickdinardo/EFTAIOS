package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LightsFrame{
	
	private JFrame frame;
	private JLabel background;

	public LightsFrame(){
		ImageIcon icon = new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\icon2.jpg");
	    frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.black);
		frame.setLayout(new BorderLayout());
		background = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\galilei1.jpg"));
		background.setLayout(new FlowLayout());
		MouseListener lightsClick = new LightsHandler();
		background.addMouseListener(lightsClick);
		frame.add(background, BorderLayout.SOUTH);
		Font f = new Font("Arial", Font.BOLD, 18);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.black);
		JLabel label = new JLabel("Which sector of the map do you want to enlight? Click on the map");
		label.setForeground(Color.green);
		label.setBackground(Color.black);
		label.setFont(f);
		panel.add(label);
		frame.add(panel, BorderLayout.NORTH);
		frame.pack();
		frame.setSize(740, 620);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				((screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 )) - 20 );
		frame.setVisible(true);
		
		}
}