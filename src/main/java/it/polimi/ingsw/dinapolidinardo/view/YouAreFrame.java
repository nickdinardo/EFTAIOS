package it.polimi.ingsw.dinapolidinardo.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Class that manages the initialization and display of the Frame 
 * that shows if user control an Alien or a Human player,
 */
public class YouAreFrame {

	protected JLabel imageLabel;
	protected JLabel titleLabel;
	protected JScrollPane scrollpane;
    protected JTextArea textBoxLabel;
    protected JFrame frame;
    protected JButton button;
    protected CountDownLatch latch = new CountDownLatch(1);
    
    
    /**
     * Initializes all the components of the Frame, differentiating them on the 
     * base of the parameters received to grant a different visualization for alien
     * and human players
     * 
     * @param image the image that shows an human or an alien
     * @param color the theme color of various part of the frame
     * @param text the text description of the character
     * @param being "alien" or "human" title of the frame
     */
    protected void initComponents(String image, Color color, String text, String being) {

    	scrollpane = new JScrollPane();
        textBoxLabel = new JTextArea();
        imageLabel = new JLabel();
        titleLabel = new JLabel();
        button = new JButton();
        
        frame.setUndecorated(true);
               
        textBoxLabel.setEditable(false);
        textBoxLabel.setBackground(color);
        textBoxLabel.setColumns(20);
        textBoxLabel.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 18)); 
        textBoxLabel.setRows(5);
        textBoxLabel.setText(text);
        textBoxLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scrollpane.setViewportView(textBoxLabel);

        imageLabel.setIcon(new javax.swing.ImageIcon(image)); 

        titleLabel.setBackground(new java.awt.Color(0, 0, 0));
        titleLabel.setFont(new java.awt.Font("Khmer UI", 1, 70)); 
        titleLabel.setForeground(color);
        titleLabel.setText(being);

        button.setBackground(Color.black);
        button.setForeground(color);
        button.setText("Start!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(imageLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(64, 64, 64)
                        .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(imageLabel)))
                .addContainerGap())
        );
        
        frame.pack();
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.black);
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				(screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );
		frame.setVisible(true);
		ActionListener handler = new ShowCharacterHandler();
		button.addActionListener(handler);
    }  
    
    
    /**
     * Waits for user input on the "Start!" button
     */
    public void getNext(){

		try {
			latch.await();
		} catch (InterruptedException e) {
			return;
		}
	}
	
    
    /**
     * Listener that unblocks the getNext() method waiting for
     * the "Start!" button to be pressed, then closes the frame
     */
	protected class ShowCharacterHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand() == "Start!"){
				
				latch.countDown();
				frame.dispose();
				
			}
		}

	}
}
