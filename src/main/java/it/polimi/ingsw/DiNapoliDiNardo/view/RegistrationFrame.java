package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RegistrationFrame {
	
	private JFrame frame;
	private String name;
	CountDownLatch latch = new CountDownLatch(1);
	
	public RegistrationFrame(JFrame frameRegistration, boolean flag){
		
		frame = frameRegistration;
		ImageIcon icon = new ImageIcon("externalresources\\icon2.jpg");
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon("externalresources\\mqdefault.jpg"));
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
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.setBackground(Color.black);
		if (!flag){
			JLabel label2 = new JLabel("What's your name?");
			label2.setForeground(Color.green);
			label2.setFont(f);
			p2.add(label2, BorderLayout.NORTH);
		}else{
			JLabel label2 = new JLabel("The name you chose is already taken for this game. Please insert another name");
			label2.setForeground(Color.green);
			label2.setFont(f);
			p2.add(label2, BorderLayout.NORTH);
		}
		JTextField textField = new JTextField(20);
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
		
		
	}
	
	
		
	private class AskNameHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event){
			
			setName(event.getActionCommand());
			if("".equals(name))
				JOptionPane.showMessageDialog(null, "Empty field");
			latch.countDown();
						
		}
	}
	
		
	public String getName(){
		try {
			latch.await();
		} catch (InterruptedException e) {
			//do nothing
		}
		frame.dispose();
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
