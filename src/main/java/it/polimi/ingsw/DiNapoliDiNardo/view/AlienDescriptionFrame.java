package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AlienDescriptionFrame {

	private JFrame frame;
	private boolean waitNext = false;
	
	public AlienDescriptionFrame(JFrame descriptionFrame, String name){
		
		frame = descriptionFrame;
		ImageIcon icon = new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\icon2.jpg");
		frame.setIconImage(icon.getImage());
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.black);
		JLabel background = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\giuliaghigini-artwork5-eftaios.jpg"));
		background.setLayout(new FlowLayout());
		frame.add(background, BorderLayout.NORTH);
		Font f1 = new Font("Arial", Font.BOLD, 18);
		Font f2 = new Font("Arial", Font.BOLD, 12);
		JLabel label1 = new JLabel("                                 YOU WILL BE ALIEN                                    ");
		JLabel label2 = new JLabel(name + ", the horrible alien disease that is infecting the spaceships ");
		JLabel label3 = new JLabel("has caught you time ago. Your body is now a brutal and deformed machine eager "); 
		JLabel label4 = new JLabel("to kill any human that is still alive carrying fresh meat. ");
		JLabel label5 = new JLabel("Your objective is tracking down the poor humans that are trying to reach the ");
		JLabel label6 = new JLabel("lifeboat ships and kill'em before they do it. ");
		JLabel label7 = new JLabel("All the miserable humans. Enjoy your meal. ");
		label1.setFont(f1);
		label1.setForeground(Color.green);
		background.add(label1);
		label2.setFont(f2);
		label2.setForeground(Color.green);
		label3.setFont(f2);
		label3.setForeground(Color.green);
		label4.setFont(f2);
		label4.setForeground(Color.green);
		label5.setFont(f2);
		label5.setForeground(Color.green);
		label6.setFont(f2);
		label6.setForeground(Color.green);
		label7.setFont(f2);
		label7.setForeground(Color.green);
		background.add(label2);
		background.add(label3);
		background.add(label4);
		background.add(label5);
		background.add(label6);
		background.add(label7);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.black);
		JButton button = new JButton("Next");
		panel.add(button);
		frame.add(panel, BorderLayout.SOUTH);
		frame.pack();
		frame.setSize(600, 600);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				(screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );
		frame.setVisible(true);
		ActionListener handler = new ShowAlienHandler();
		button.addActionListener(handler);
	}
	
	public void getNext(){
		waitNext = false;
		String str = "";
		while(waitNext  == false){
			str += "avoided";
			if(str.length() > 10000)
				str = "";
		}
	}
	
	private class ShowAlienHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Next"){
				
				waitNext = true;
				frame.dispose();
			}
		}

	}
}
