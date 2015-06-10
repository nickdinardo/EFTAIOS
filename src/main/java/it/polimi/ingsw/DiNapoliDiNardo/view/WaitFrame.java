package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;




public class WaitFrame {
	private JFrame frame;
	private JLabel header;
	private JLabel waitingPhrase;
		
	
    public WaitFrame() {
        initComponents();
    }

    
    private void initComponents() {

    	frame = new JFrame("WaitFrame");
    	frame.setUndecorated(true);
        header = new javax.swing.JLabel();
        waitingPhrase = new javax.swing.JLabel();

        
        header.setIcon(new javax.swing.ImageIcon("externalresources\\header 2.jpg"));

        waitingPhrase.setFont(new java.awt.Font("Impact", 3, 18)); 
        waitingPhrase.setForeground(new java.awt.Color(255, 255, 255));
        waitingPhrase.setText("Waiting the other players...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(header)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGap(224, 224, 224))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(waitingPhrase)
                .addGap(180, 180, 180))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(header)
                .addGap(18, 18, 18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(waitingPhrase)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        frame.getContentPane().setBackground(new Color(35, 31, 32));
        frame.pack();
        frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				((screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 )) - 20 );
		frame.setVisible(true);  
    }


	public void dispose() {
		frame.dispose();
	}
   
}
