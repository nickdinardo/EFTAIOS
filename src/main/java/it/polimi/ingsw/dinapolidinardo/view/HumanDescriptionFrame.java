package it.polimi.ingsw.dinapolidinardo.view;

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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HumanDescriptionFrame {
	
	private JFrame frame;
	private String name;
	CountDownLatch latch = new CountDownLatch(1);
	
	public HumanDescriptionFrame(JFrame descriptionFrame, String name){
		
		this.frame = descriptionFrame;
		this.name = name;
		ImageIcon icon = new ImageIcon("externalresources\\icon2.jpg");
		frame.setIconImage(icon.getImage());
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.black);
		JLabel background = new JLabel(new ImageIcon("externalresources\\5d92d6be86fedb54c933f3c248f1ad22.jpg"));
		background.setLayout(new FlowLayout());
		frame.add(background, BorderLayout.NORTH);
		Font f1 = new Font("Arial", Font.BOLD, 18);
		Font f2 = new Font("Arial", Font.BOLD, 12);
		JLabel label1 = new JLabel("                                 YOU WILL BE HUMAN                                    ");
		JLabel label2 = new JLabel(this.name + ", you are one of the survivors on the spaceship");
		JLabel label3 = new JLabel("that resisted to the spreading, horrible disease that could have"); 
		JLabel label4 = new JLabel("infected anyone of your team mates. ");
		JLabel label5 = new JLabel("Horrendous aliens that once were your friends are lurking in the");
		JLabel label6 = new JLabel("dark to kill you and eat you, and they could be anyone and anywhere. ");
		JLabel label7 = new JLabel("Your objective is reaching one of the avaiable lifeboat ships avoiding");
		JLabel label8 = new JLabel("to attract the attentions of the blood-thirsty monsters that surround you.");
		JLabel label9 = new JLabel("The mission depends on you. Your life too. Good luck.");
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
		label8.setFont(f2);
		label8.setForeground(Color.green);
		label9.setFont(f2);
		label9.setForeground(Color.green);
		background.add(label2);
		background.add(label3);
		background.add(label4);
		background.add(label5);
		background.add(label6);
		background.add(label7);
		background.add(label8);
		background.add(label9);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.black);
		JButton button = new JButton("Next");
		panel.add(button);
		frame.add(panel, BorderLayout.SOUTH);
		frame.pack();
		frame.setSize(500, 500);
		
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				(screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );
		frame.setVisible(true);
		ActionListener handler = new ShowHumanHandler();
		button.addActionListener(handler);
	}
	
	public void getNext(){

		try {
			latch.await();
		} catch (InterruptedException e) {
			return;
		}
	}
	
	private class ShowHumanHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Next"){
				
				latch.countDown();
				frame.dispose();
				
			}
		}

	}
}
