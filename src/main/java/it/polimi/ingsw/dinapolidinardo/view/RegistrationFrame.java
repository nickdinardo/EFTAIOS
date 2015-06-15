package it.polimi.ingsw.dinapolidinardo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * Class that manages the initialization and display 
 * of the Frame with the name insertion box
 */
public class RegistrationFrame {
	
	private JFrame frame;
	private JLabel imageLabel;
	private JTextField textField;
	private JButton submitButton;
	private boolean flag;
	private boolean firstEnter = true;
	private String name;
	CountDownLatch latch = new CountDownLatch(1);
	
	
	/**
	 * Constructor
	 * 
	 * @param alreadyTaken signals if the name selected has been already taken and user has to re-input it
	 */
	public RegistrationFrame(boolean alreadyTaken){
		frame = new JFrame("E.F.T.A.I.O.S.");
		this.flag = alreadyTaken;
		initComponents();
		
		
	}
		
		
	/**
     * Initializes and displays components
     */
	private void initComponents() {

        imageLabel = new JLabel();
        submitButton = new JButton();
        textField = new JTextField();

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("externalresources\\icon2.jpg");
		frame.setIconImage(icon.getImage());

        imageLabel.setIcon(new javax.swing.ImageIcon("externalresources\\cover.png")); 

        submitButton.setText("Submit Name");
        submitButton.setBackground(Color.black);
        submitButton.setForeground(Color.pink);
        submitButton.setFocusable(false);

        if (!flag)
        	textField.setText("-Insert your name here-");
        else 
        	textField.setText("-Name already chosen-");
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(219, 219, 219))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );


        MouseOnText mouse = new MouseOnText();
        textField.addMouseListener(mouse);
		SubmitButtonListener subBut = new SubmitButtonListener();
		submitButton.addActionListener(subBut);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.getContentPane().setBackground(Color.black);
        frame.setResizable(false);
      	Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), 
				(screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );
		frame.setVisible(true);
    
	}
	
	
	/**
	 * MouseAdapter that cancel the default box test 
	 * when the box is clicked for input
	 */
	private class MouseOnText extends MouseAdapter {
				
		@Override
		public void mouseEntered(MouseEvent event) {
			if (firstEnter){
				textField.setText("");
				textField.setForeground(Color.black);
				firstEnter = false;
			}
		}

	}	
	
	
	/**
	 * Action Listener that sets the name chosen in a variable and 
	 * unblock the getName() method waiting for it
	 */
	private class SubmitButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			setName(textField.getText());
			latch.countDown();
		}
	}
	
	/**
	 * Waits for user to press "submit" button and then closes the frame
	 * and returns the name
	 * 
	 * @return the name chosen by user
	 */
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
