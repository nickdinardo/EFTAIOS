package it.polimi.ingsw.dinapolidinardo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;


public class HumanDiscardFrame extends DiscardFrame {
	
	private JButton useButton;
	
	
	public HumanDiscardFrame(List<String> objects){
		super(objects);
	}
		
	@Override
	public void initComponents(List<String> objects){
		
		commonBuildUp(objects, new Color(88, 101, 146));
		useButton = new JButton("Use one of them");
        useButton.setBackground(Color.black);
        useButton.setForeground(new Color(88, 101, 146));
        useButton.setFocusable(false);
		
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(header)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(card1)
                                .addGap(18, 18, 18)
                                .addComponent(card2)
                                .addGap(18, 18, 18)
                                .addComponent(card3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(button)
                                .addGap(27, 27, 27)
                                .addComponent(useButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(textLabel)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header)
                .addGap(18, 18, 18)
                .addComponent(textLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card2)
                    .addComponent(card1)
                    .addComponent(card3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button)
                    .addComponent(useButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	
	    frame.setUndecorated(true);
	    frame.pack();
	    frame.setResizable(false);
	    frame.getContentPane().setBackground(new Color(35, 31, 32));
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				((screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 )) - 20 );
		frame.setVisible(true);
		
	}
	


	public JButton getUseButton(){
		return this.useButton;
	}
}
