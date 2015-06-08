package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NewAlienFrame extends TurnFrame{
	                    
    
	private static final Color aliencolor = Color.pink;
	private javax.swing.JButton attackButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel card1;
    private javax.swing.JLabel card2;
    private javax.swing.JLabel card3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel header;
    private javax.swing.JLabel turnLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea comunication;
   
    
	    public NewAlienFrame(String name, String actualPosition, String turn, List<String> objects) {
	        initComponents();
	        nameLabel.setText(name);
	        positionLabel.setText("Position: " + actualPosition);
	    }

	  
	    private void initComponents() {

	    	frame = new JFrame("GalileiMap");
	        jScrollPane1 = new javax.swing.JScrollPane();
	        comunication = new javax.swing.JTextArea();
	        card1 = new javax.swing.JLabel();
	        card2 = new javax.swing.JLabel();
	        card3 = new javax.swing.JLabel();
	        jLabel4 = new javax.swing.JLabel();
	        header = new javax.swing.JLabel();
	        attackButton = new javax.swing.JButton("Attack");
	        nextButton = new javax.swing.JButton("Next");
	        turnLabel = new javax.swing.JLabel();
	        nameLabel = new javax.swing.JLabel();
	        positionLabel = new javax.swing.JLabel();

	        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        frame.setBackground(new java.awt.Color(0, 0, 0));

	        comunication.setBackground(new java.awt.Color(102, 71, 102));
	        comunication.setColumns(20);
	        comunication.setForeground(new java.awt.Color(227, 227, 227));
	        comunication.setRows(5);
	        comunication.setAutoscrolls(true);
	        comunication.setBorder(new javax.swing.border.MatteBorder(null));
	        jScrollPane1.setViewportView(comunication);

	        card1.setIcon(new javax.swing.ImageIcon("externalresources\\BlankCard.png"));
	        card1.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
	        card1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

	        card2.setIcon(new javax.swing.ImageIcon("externalresources\\BlankCard.png")); 
	        card2.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

	        card3.setIcon(new javax.swing.ImageIcon("externalresources\\BlankCard.png")); 
	        card3.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

	        jLabel4.setIcon(new javax.swing.ImageIcon("externalresources\\galilei1.jpg"));
	        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));

	        header.setIcon(new javax.swing.ImageIcon("externalresources\\header.png")); 

	        attackButton.setText("Attack");
	        attackButton.setBackground(Color.black);
			attackButton.setForeground(aliencolor);
			attackButton.setFocusable(false);

	        nextButton.setText("Next");
	        nextButton.setBackground(Color.black);
			nextButton.setForeground(aliencolor);
			nextButton.setFocusable(false);

	        turnLabel.setText("jLabel6");

	        nameLabel.setText("jLabel7");

	        positionLabel.setText("jLabel8");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
	        frame.getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(14, 14, 14)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGap(12, 12, 12))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(12, 12, 12)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(nameLabel)
	                                    .addComponent(turnLabel)
	                                    .addComponent(positionLabel))
	                                .addGap(0, 0, Short.MAX_VALUE))))
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addGroup(layout.createSequentialGroup()
	                            .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addGap(18, 18, 18)
	                            .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addGap(18, 18, 18)
	                            .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(jLabel4))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                            .addComponent(header)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(14, 14, 14)
	                                .addComponent(turnLabel)
	                                .addGap(18, 18, 18)
	                                .addComponent(nameLabel)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(positionLabel)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                    .addComponent(attackButton)
	                                    .addComponent(nextButton))))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(13, 13, 13)
	                                .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(card3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(card2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))))
	                    .addComponent(jLabel4))
	                .addGap(20, 20, 20))
	        );

	        
	        frame.getContentPane().setBackground(Color.black);
	        frame.pack();
	        frame.setResizable(false);
			Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
					((screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 )) - 20 );
			frame.setVisible(true);
	    }


		@Override
		public void update(String name, String position, String turn, List<String> objects) {
			comunication.append(name + " you are now in the " + position + " position\n");
			turnLabel.setText("Turn: " + turn); 
			positionLabel.setText("Position: " + position);
			List<String> item = objects;
			if (item.size()>0)
				if(!("".equals(item.get(0)))){
					card1.setIcon(new ImageIcon("externalresources\\" + item.get(0) + ".png"));
					//descCard1.setText(item.get(0));
				}
			if (item.size()>1){
				card2.setIcon(new ImageIcon("externalresources\\" + item.get(1) + ".png"));
				//descCard2.setText(item.get(1));
			}
			if (item.size()>2){
				card3.setIcon(new ImageIcon("externalresources\\" + item.get(2) + ".png"));
				//descCard3.setText(item.get(2));
			}
		}
		
		public List<JLabel> setCardHandler(List<String> item){
			return null;
		}
		
		public JButton getAttackButton(){
			return this.attackButton;
		}
		
		public void appendToTextArea(String string){
			comunication.append(string);
		}
}
