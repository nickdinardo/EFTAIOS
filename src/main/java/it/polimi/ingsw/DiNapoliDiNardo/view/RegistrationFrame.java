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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RegistrationFrame {
	
	private JFrame frame;
	private String name;
	private boolean waitForName;
	
	public RegistrationFrame(JFrame frameRegistration){
		
		frame = frameRegistration;
		ImageIcon icon = new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\icon2.jpg");
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon("C:\\Users\\Beckham\\Nick\\Documenti\\Polimi\\E.F.T.A.I.O.S\\mqdefault.jpg"));
		frame.add(background);
		background.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.black);
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.setBackground(Color.gray);
		JLabel label1 = new JLabel("Connected to the server game.");
		Font f = new Font("Arial", Font.BOLD, 12);
		label1.setFont(f);
		label1.setForeground(Color.green);
		p1.add(label1);
		JLabel label2 = new JLabel("What's your name?");
		label2.setForeground(Color.green);
		label2.setFont(f);
		JTextField textField = new JTextField(20);
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.setBackground(Color.black);
		p2.add(label2, BorderLayout.NORTH);
		p2.add(textField, BorderLayout.SOUTH);
		ActionListener handler = new AskNameHandler();
		textField.addActionListener(handler);
		background.add(p1, BorderLayout.NORTH);
		background.add(p2, BorderLayout.SOUTH);
		frame.pack();
		frame.setSize(400, 250);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				(screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );
		frame.setVisible(true);
		String str = "";
		while(!waitForName){
			str += "avoided";
			if(str.length() > 10000)
				str = "";
		}
		this.updateFrame();
	}
	
	private class AskNameHandler implements ActionListener {
		
		
		public void actionPerformed(ActionEvent event){
			
			setName(event.getActionCommand());
			if("".equals(name))
				JOptionPane.showMessageDialog(null, "Empty field");
			waitForName = true;
			frame.dispose();
		}
	}
	
	private void updateFrame(){
		waitForName = false;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
