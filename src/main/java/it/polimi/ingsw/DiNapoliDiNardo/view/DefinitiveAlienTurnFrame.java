package it.polimi.ingsw.DiNapoliDiNardo.view;


import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class DefinitiveAlienTurnFrame extends TurnFrame{
	
	
    private JButton attackButton;
    private JLabel jLabel3;
	private JLabel alienImageLabel;
    private JScrollPane jScrollPane1;
    private static final Color aliencolor = Color.pink;
	
    
	    
	public DefinitiveAlienTurnFrame(String name, String actualPosition, String turn, List<String> objects) {
		initComponents();
		nameLabel.setText("   "+name);
		turnLabel.setText("Turn: " + turn); 
		positionLabel.setText("Position: " + actualPosition);
	}

	   
	                           
	private void initComponents() {

		frame = new JFrame("GalileiMap");
        map = new JLabel();
        jScrollPane1 = new JScrollPane();
        comunication = new JTextArea();
        
        card1 = new JLabel();
        card2 = new JLabel();
        card3 = new JLabel();
        jLabel3 = new JLabel();
        alienImageLabel = new JLabel();
        nextButton = new JButton();
        attackButton = new JButton();
        nameLabel = new JLabel();
        turnLabel = new JLabel();
        timerLabel = new JLabel();
        positionLabel = new JLabel();
       

       

        map.setIcon(new javax.swing.ImageIcon("C:\\Users\\679405\\workspace\\DiNapoliDiNardo\\externalresources\\galileibella.jpg")); // NOI18N
        map.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        map.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        
        comunication.setEditable(false);
        comunication.setBackground(new java.awt.Color(138, 66, 130));
        comunication.setColumns(20);
        comunication.setRows(5);
        comunication.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        DefaultCaret caret = (DefaultCaret)comunication.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jScrollPane1.setViewportView(comunication);

        card1.setIcon(new javax.swing.ImageIcon("C:\\Users\\679405\\workspace\\DiNapoliDiNardo\\externalresources\\BlankCard.png")); 
        card1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        card2.setIcon(new javax.swing.ImageIcon("C:\\Users\\679405\\workspace\\DiNapoliDiNardo\\externalresources\\BlankCard.png")); 
        card2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        card3.setIcon(new javax.swing.ImageIcon("C:\\Users\\679405\\workspace\\DiNapoliDiNardo\\externalresources\\BlankCard.png")); 
        card3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        alienImageLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\679405\\workspace\\DiNapoliDiNardo\\externalresources\\alieno3.jpg")); 

        attackButton.setText("Attack");
        attackButton.setBackground(Color.black);
		attackButton.setForeground(aliencolor);
		attackButton.setFocusable(false);

        nextButton.setText("Next");
        nextButton.setBackground(Color.black);
		nextButton.setForeground(aliencolor);
		nextButton.setFocusable(false);

		nameLabel.setFont(new java.awt.Font("Impact", 0, 36)); 
        turnLabel.setFont(new java.awt.Font("Impact", 0, 16)); 
        positionLabel.setFont(new java.awt.Font("Impact", 0, 16)); 
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(alienImageLabel)
                                
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nameLabel)
                                        .addGap(72, 72, 72))//72 72 72
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(positionLabel)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(turnLabel)
                                            .addComponent(timerLabel))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(map, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                          		.addComponent(nameLabel)
                                .addGap(78, 78, 78)
                                .addComponent(turnLabel)
                                .addGap(18, 18, 18)//40 40 40
                                .addComponent(positionLabel)
                                .addGap(18, 18, 18)//39 39 39
                                .addComponent(timerLabel)
                                .addGap(55, 55, 55)//35 35 35
                                .addGap(38, 38, 38))
                                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                               
                            .addComponent(alienImageLabel))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(map))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setBackground(new java.awt.Color(0, 0, 0));
        frame.getContentPane().setBackground(Color.black);
        frame.pack();
        frame.setResizable(false);
		//Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		//frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
		//		((screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 )) - 20 );
		frame.setVisible(true);  
    }   
	
		
	@Override
	public List<JLabel> setCardHandler(List<String> item){
		return null;
	}
	
	public JButton getAttackButton(){
		return attackButton;
	}
	
		
}


