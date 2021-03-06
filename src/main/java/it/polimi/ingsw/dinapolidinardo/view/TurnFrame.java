package it.polimi.ingsw.dinapolidinardo.view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolTip;
import javax.swing.text.DefaultCaret;


/**
 * Class that manages the creation and usage of the Main game Frame 
 */
public abstract class TurnFrame {
	
	protected JFrame frame;
	protected JLabel background;
	protected JTextArea comunication;
	protected JButton nextButton;
	protected JScrollBar jsb;
	protected JLabel card1;
	protected JLabel card2;
	protected JLabel card3;
	protected JLabel nameLabel;
	protected JLabel turnLabel;
	protected JLabel timerLabel;
	protected JLabel positionLabel;
	protected JLabel map;
	protected JLabel jLabel3;
	protected JLabel imageLabel;
    protected JScrollPane jScrollPane1;
    protected String defaultFont = "Impact";   
	protected List<String> item;
	protected ToolTipCard tips = new ToolTipCard();
	protected NumberFormat format;
	protected static final String PATH = "externalresources\\";
	protected static final String BLANKCARDPATH = "externalresources\\BlankCard.png";
	protected static final String NEGATIVE = "Inv.jpg";
	protected static final String FORMAT = ".jpg";
	protected Timer timer = new Timer();
	protected static final int TURNTIME = 3*60*1000;
	protected static final int MIN = 3;
	protected int remainingtime = 3*60*1000;
	
	
	/**
	 * Constructor, it initialize the Frame with the start game informations
	 * 
	 * @param name player's name
	 * @param actualPosition player's actual position
	 * @param turn game's current turn
	 */
	public TurnFrame(String name, String actualPosition, String turn) {
		initComponents();
		if (name.length()<12)
			nameLabel.setText("   "+name);
		else
			nameLabel.setText("   "+name.substring(0, 9)+"...");
		turnLabel.setText("Turn: " + turn); 
		positionLabel.setText("Position: " + actualPosition);
		timerLabel.setText("Remaining time: 03:00");
	}

	
	 /**
     * Initializes all the components of the Frame, differentiating them on the 
     * base of the parameters received to grant a different visualization for alien
     * and human players
     * 
     * @param imagePath the path of the image representing a human or an alien
     * @param commColor	the color used for buttons and text area
     */
	public void commonBuildUp(String imagePath, Color commColor){
		
		frame = new JFrame("GalileiMap");
		ImageIcon icon = new ImageIcon(PATH + "icon2.jpg");
		frame.setIconImage(icon.getImage());
        map = new JLabel();
        jScrollPane1 = new JScrollPane();
        comunication = new JTextArea();
        
        card1 = new JLabel();
        card2 = new JLabel();
        card3 = new JLabel();
        jLabel3 = new JLabel();
        imageLabel = new JLabel();
        nextButton = new JButton();
        nameLabel = new JLabel();
        turnLabel = new JLabel();
        timerLabel = new JLabel();
        positionLabel = new JLabel();
       		
        map.setIcon(new javax.swing.ImageIcon(PATH + "galileiDefinitiva.png")); 
        map.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        map.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        
        comunication.setEditable(false);
        comunication.setBackground(commColor);
        comunication.setForeground(Color.black);
        comunication.setColumns(20);
        comunication.setRows(5);
        comunication.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        DefaultCaret caret = (DefaultCaret)comunication.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jScrollPane1.setViewportView(comunication);

        card1.setIcon(new javax.swing.ImageIcon(BLANKCARDPATH));
        card1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        card2.setIcon(new javax.swing.ImageIcon(BLANKCARDPATH)); 
        card2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        card3.setIcon(new javax.swing.ImageIcon(BLANKCARDPATH)); 
        card3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        imageLabel.setIcon(new javax.swing.ImageIcon(imagePath)); 

        nameLabel.setFont(new java.awt.Font(defaultFont, 0, 36)); 
        turnLabel.setFont(new java.awt.Font(defaultFont, 0, 18)); 
        positionLabel.setFont(new java.awt.Font(defaultFont, 0, 18)); 
        timerLabel.setFont(new java.awt.Font(defaultFont, 0, 18)); 
        nameLabel.setForeground(new Color (140,140,140));
        turnLabel.setForeground(new Color (120,120,120));
        positionLabel.setForeground(new Color (120,120,120));
        timerLabel.setForeground(new Color (120,120,120));
        
        nextButton.setText("Next");
        nextButton.setBackground(Color.black);
		nextButton.setForeground(commColor);
		nextButton.setFocusable(false);
	
	}
	
	/**
	 * Update the view with to respond to in-game changes 
	 * 
	 * @param name player's name
	 * @param position player's actual position
	 * @param turn game's current turn
	 * @param objects a String list with the names of the currently owned items
	 * @param startTurn signals if the update has been received at the beginning of the turn or not
	 */
	public void update(String name, String position, String turn, List<String> objects, boolean startTurn) {
		if(startTurn)
			comunication.append(name + " you are now in the " + position + " position\n");
		turnLabel.setText("Turn: " + turn); 
		positionLabel.setText("Position: " + position);
		item = objects;
		JToolTip[] itemDescription = tips.setToolTip(item);
		if (item.size() == 1)
			if(!("".equals(item.get(0)))){
				card1.setIcon(new ImageIcon(PATH + item.get(0) + FORMAT));
				card2.setIcon(new ImageIcon(BLANKCARDPATH));
				card3.setIcon(new ImageIcon(BLANKCARDPATH));
				card1.setToolTipText(itemDescription[0].getTipText());
			}
			else{
				card1.setIcon(new ImageIcon(BLANKCARDPATH));
				card2.setIcon(new ImageIcon(BLANKCARDPATH));
				card3.setIcon(new ImageIcon(BLANKCARDPATH));
			}
		if (item.size() == 2){
			card1.setIcon(new ImageIcon(PATH + item.get(0) + FORMAT));
			card2.setIcon(new ImageIcon(PATH + item.get(1) + FORMAT));
			card3.setIcon(new ImageIcon(BLANKCARDPATH));
			card1.setToolTipText(itemDescription[0].getTipText());
			card2.setToolTipText(itemDescription[1].getTipText());
		}
		if (item.size() == 3){
			card1.setIcon(new ImageIcon(PATH + item.get(0) + FORMAT));
			card2.setIcon(new ImageIcon(PATH + item.get(1) + FORMAT));
			card3.setIcon(new ImageIcon(PATH + item.get(2) + FORMAT));
			card1.setToolTipText(itemDescription[0].getTipText());
			card2.setToolTipText(itemDescription[1].getTipText());
			card3.setToolTipText(itemDescription[2].getTipText());
		}
	}
	
	
	/**
	 * Obtains the labels on which the card image are
	 * 
	 * @return the list of JLabel representing card images
	 */
	protected abstract List<JLabel> setCardHandler();
	
	
	/**
     * Builds the frame calling the build method and then sets
     * a different Layout for each subclass of DiscardFrame
     */
	protected abstract void initComponents();
	
	
	/**
	 * Closes this frame
	 */
	public void dispose(){
		this.frame.dispose();
	}
	
	
	/**
	 * Append a message to the main text communication area of the Frame
	 * 
	 * @param string the message to append
	 */
	public void appendToTextArea(String string){
		comunication.append(string);
	}
	
	
    /**
     * Substitutes the image of a card with his negative.
     * <p>
     * Method is called by the MouseListeners on the label, granting the effect 
     * of "enlight" the card anytime mouse passes or does a particular action on this label
     * 
     * @param index the index of which card image has to be enlighted
     */
	public void enlightCard(int index){
		if (index == 1 && (item.get(0) != ""))
			card1.setIcon(new ImageIcon(PATH + item.get(0) + NEGATIVE));
		if (index == 2 && item.size()>1)
			card2.setIcon(new ImageIcon(PATH + item.get(1) + NEGATIVE));
		if (index == 3 && item.size()>2)
			card3.setIcon(new ImageIcon(PATH + item.get(2) + NEGATIVE));
	}

	
	/**
     * Substitutes the negative image of a card with his original.
     * <p>
     * Method is called by the MouseListeners on the label, granting the effect 
     * of "endark" the card anytime mouse goes away or does a particular action on this label
     * 
     * @param index the index of which card image has to be enlighted
     */
	public void endarkCard(int index){
		if (index == 1 && (item.get(0) != ""))
			card1.setIcon(new ImageIcon(PATH + item.get(0) + FORMAT));
		if (index == 2 && item.size()>1)
			card2.setIcon(new ImageIcon(PATH + item.get(1) + FORMAT));
		if (index == 3 && item.size()>2)
			card3.setIcon(new ImageIcon(PATH + item.get(2) + FORMAT));
	}
	
		
	/**
	 * Initializes a turn time countdown visualized on the main frame
	 */
	public void startTimer(){
		
		timer = new Timer();
		format = NumberFormat.getNumberInstance();
	    format.setMinimumIntegerDigits(2);
		timer.scheduleAtFixedRate(new TimerTask(){
		public void run() {
			if (remainingtime < 0) 
				return;
			int minutes = remainingtime/60000;
		    int seconds = (remainingtime-(minutes*60000))/1000;
		    timerLabel.setText("Remaining time: " + format.format(minutes) + ":" + format.format(seconds));	
		    remainingtime-=1000;
		        }
		    }, 0, 1000);
	}
	
	/**
	 * Stops the turn time countdown
	 */
	public void stopTimer(){
		timer.cancel();
		remainingtime = TURNTIME;
		timerLabel.setText("Remaining time: 03:00");
	}
	
	
	
	//getters and setters
		
	public List<String> getItem(){
		return item;
	}
	
	public JButton getNextButton(){
		return this.nextButton;
	}
	
	
	public JLabel getBackgroundImage(){
		return this.map;
	}
	
	public void setMapImage(BufferedImage dimg){
		this.map.setIcon(new ImageIcon(dimg));
	}
	
	
	
	
	
	
	
}



