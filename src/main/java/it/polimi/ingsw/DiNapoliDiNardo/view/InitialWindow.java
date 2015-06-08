package it.polimi.ingsw.DiNapoliDiNardo.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;


public class InitialWindow {
	String selection = "";
	JFrame frame = new JFrame("E.F.T.A.I.O.S.");
	CountDownLatch latch = new CountDownLatch(1);
	
	
	public InitialWindow(){
		
		
		frame.setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("externalresources\\cover.png"));
		frame.add(background);
		background.setLayout(new FlowLayout());
		JLabel l1=new JLabel("Choose Connection: ");
		background.add(l1);
		
		InitialWindowListener listener = new InitialWindowListener();
		
		JButton b1=new JButton("RMI");
		b1.setBackground(Color.black);
		b1.setForeground(Color.pink);
		b1.setFocusable(false);
		background.add(b1);
		b1.addActionListener(listener);
		
		JButton b2=new JButton("Sockets");
		b2.setBackground(Color.black);
		b2.setForeground(Color.pink);
		b2.setFocusable(false);
		background.add(b2);
		b2.addActionListener(listener);
		
		
		frame.setVisible(true);
		frame.setSize(800,442);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		
	}
	
	
	public String getChoice(){
		try {
			latch.await();
		} catch (InterruptedException e) {
			return "";
		}
		frame.dispose();
		return selection;
	}
	

	private class InitialWindowListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="RMI")
				selection = "2";
			if(e.getActionCommand()=="Sockets"){
				selection = "1";
			}
			latch.countDown();
			
		}
	}
	
}	

