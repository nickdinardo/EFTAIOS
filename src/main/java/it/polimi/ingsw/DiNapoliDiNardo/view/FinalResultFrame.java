package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinalResultFrame {

	private JFrame frame;
	
	public FinalResultFrame(boolean flag, String name){
		frame = new JFrame("Final Result");
		ImageIcon icon = new ImageIcon("externalresources\\icon2.jpg");
	    frame.setIconImage(icon.getImage());
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.getContentPane().setBackground(Color.black);
	    frame.setLayout(new FlowLayout());
	    Font f = new Font("Arial", Font.BOLD, 20);
		JLabel background = new JLabel(new ImageIcon("externalresources\\mqdefault.jpg"));
		background.setLayout(new BorderLayout());
		frame.add(background);
		if(flag){
			JLabel label = new JLabel("           " + name + " YOU WON !!!");
			label.setFont(f);
			label.setBackground(Color.black);
			label.setForeground(Color.green);
			background.add(label, BorderLayout.CENTER);
		}else{
			JLabel label = new JLabel("           " + name + " YOU LOST !!!");
			label.setFont(f);
			label.setBackground(Color.black);
			label.setForeground(Color.red);
			background.add(label, BorderLayout.CENTER);
		}
	    frame.pack();
		frame.setSize(400, 210);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				((screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 )) );
		frame.setVisible(true);
	}
}
