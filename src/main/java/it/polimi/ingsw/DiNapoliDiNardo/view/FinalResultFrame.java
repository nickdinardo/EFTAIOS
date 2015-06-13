package it.polimi.ingsw.DiNapoliDiNardo.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class FinalResultFrame {

	private JFrame frame;
	private JLabel header;
    private JLabel resultLabel;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    

	private boolean victory;
    
    
    public FinalResultFrame (boolean win){
    	this.victory = win;
    	initComponents();
    }
    
		    
    protected void initComponents() {

    	frame = new JFrame("Final Result");
		ImageIcon icon = new ImageIcon("externalresources\\icon2.jpg");
	    frame.setIconImage(icon.getImage());
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.getContentPane().setBackground(Color.black);
	          
	    header = new JLabel();
        resultLabel = new JLabel();
        scrollPane = new JScrollPane();
        textArea = new JTextArea();

        header.setIcon(new ImageIcon("externalresources\\header 2.jpg")); 

        resultLabel.setFont(new Font("Narkisim", 0, 70)); 
       
        if(victory){
        	resultLabel.setForeground(new Color(102, 255, 102));
        	resultLabel.setText("YOU WON!");
        }
        else{
        	resultLabel.setForeground(new Color(153, 0, 0));
        	resultLabel.setText("YOU LOST!");
        }

        textArea.setColumns(20);
        textArea.setFont(new Font("Nirmala UI Semilight", 0, 18)); 
        textArea.setRows(5);
        textArea.setBackground(new Color(204, 204, 208));
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(header)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(resultLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header)
                .addGap(18, 18, 18)
                .addComponent(resultLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );

        frame.getContentPane().setBackground(new Color(35, 31, 32));
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				(screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ));
		frame.setVisible(true);
    }
    
    
    public JTextArea getTextArea() {
		return textArea;
	}	
		
}

