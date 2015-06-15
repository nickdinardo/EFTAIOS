package it.polimi.ingsw.dinapolidinardo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;


/**
 * Class that manages all the MouseListeners on the card Images of different frames
 */
public class CardHandler  {

	private int indexCard = 8;
	private boolean waitForItem = false;
	private List<JLabel> cards;
	private MouseListener clickableCard1;
	private MouseListener clickableCard2;
	private MouseListener clickableCard3;
	private TurnFrame turnframe;
	private DiscardFrame discardframe;
	
	/**
	 * Constructor, gets a reference of all the possible frames that can use
	 * this class.
	 * <p>
	 * Reference is instantiated to null if a frame is not visible at that moment
	 * 
	 * @param tf the TurnFrame reference
	 * @param df the DiscardFrame reference
	 * @see TurnFrame
	 * @see DiscardFrame
	 */
	public CardHandler(TurnFrame tf, DiscardFrame df){
		this.turnframe = tf;
		this.discardframe = df;
	}
	
	
	/**
	 * Superclass of the various card listeners. Receives an index indicating
	 * on which card manage the events and define all the possible actions
	 */
	private abstract class CardHandlerArch implements MouseListener {
		private TurnFrame turnframe;
		private DiscardFrame discardframe;
		
		/**
		 * Constructor, gets a reference of all the possible frames that can use
		 * this class.
		 * <p>
		 * Reference is instantiated to null if a frame is not visible at that moment
		 * 
		 * @param tf the TurnFrame reference
		 * @param df the DiscardFrame reference
		 * @see TurnFrame
		 * @see DiscardFrame
		 */			
		public CardHandlerArch(TurnFrame tf, DiscardFrame df){
			this.turnframe = tf;
			this.discardframe = df;
		}
		
		/**
		 * Saves in a variable the index of the clicked card
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			if (turnframe.getItem().get(0) != ""){
				setIndexCard(getIndex());
				setWaitForItem(true);
			}
		}
		
		/**
		 * Creates an "darkening" effect on the pressed image
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			if (discardframe == null)
				turnframe.endarkCard(getIndex());
			else
				discardframe.endarkCard(getIndex());
		}
		
		/**
		 * Creates an "enlightening" effect on the pressed image
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			if (discardframe == null)
				turnframe.enlightCard(getIndex());
			else
				discardframe.enlightCard(getIndex());
		}
	
		/**
		 * Creates an "enlightening" effect on the image where mouse entered
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			if (discardframe == null)
				turnframe.enlightCard(getIndex());
			else
				discardframe.enlightCard(getIndex());
		}
		
		/**
		 * Creates an "darkening" effect on the image from where mouse exited
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			if (discardframe == null)
				turnframe.endarkCard(getIndex());
			else
				discardframe.endarkCard(getIndex());
		}
	
		public abstract int getIndex();
		
	}	

	/**
	 * MouseListener of the first card image
	 */
	private class CardHandler1 extends CardHandlerArch implements MouseListener {
				
		public CardHandler1(TurnFrame tf, DiscardFrame df){
			super (tf, df);
		}
		
		@Override
		public int getIndex() {
			return 1;
		}

	}	
	
	/**
	 * MouseListener of the second card image
	 */
	private class CardHandler2 extends CardHandlerArch implements MouseListener {
		
		public CardHandler2(TurnFrame tf, DiscardFrame df){
			super (tf, df);
		}
		
		@Override
		public int getIndex() {
			return 2;
		}

	}	
	
	/**
	 * MouseListener of the third card image
	 */
	private class CardHandler3 extends CardHandlerArch implements MouseListener {
		
		public CardHandler3(TurnFrame tf, DiscardFrame df){
			super (tf, df);
		}
		
		@Override
		public int getIndex() {
			return 3;
		}

	}	
	
	/**
	 * ActionListener of the "use a card" button
	 */
	private class UseButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			setIndexCard(6);
			setWaitForItem(true);
		}
	}
	
	/**
	 * ActionListener of the "don't discard" button
	 */
	private class NoButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			setIndexCard(5);
			setWaitForItem(true);
		}
	}

	
	/**
	 * Activates the listeners on the various card images	
	 */
	public void startListenCard(){
		
		clickableCard1 = new CardHandler1(turnframe, discardframe);
		cards.get(0).addMouseListener(clickableCard1);
		clickableCard2 = new CardHandler2(turnframe, discardframe);
		cards.get(1).addMouseListener(clickableCard2);
		clickableCard3 = new CardHandler3(turnframe, discardframe);
		cards.get(2).addMouseListener(clickableCard3);
		
	}
	
	
	/**
	 * Removes the listeners on the various card images 
	 * and sets the selection index on "don't use" (8)	
	 */
	public void removeListeners(){
		
		indexCard = 8;
		cards.get(0).removeMouseListener(clickableCard1);
		cards.get(1).removeMouseListener(clickableCard2);
		cards.get(2).removeMouseListener(clickableCard3);
			
	}
		
	/**
	 * Activate the listener on the "don't discard" button
	 * @param buttonNo the button on which activate the listener
	 */
	public void startListenNoButton(JButton buttonNo){
		ActionListener no = new NoButton();
		buttonNo.addActionListener(no);
		
	}
	
	/**
	 * Activate the listener on the "use a card" button
	 * @param useButton the button on which activate the listener
	 */
	public void startListenUseButton(JButton useButton){
		ActionListener use = new UseButton();
		useButton.addActionListener(use);
		
	}	
	
	
	
		
	//getters and setters	
			
	public void setIndexCard(int i){
		this.indexCard = i;
	}

	public int getIndexCard(){
		return this.indexCard;
	}
	
	public void setWaitForItem(boolean flag){
		this.waitForItem = flag;
	}
	
	public boolean getWaitForItem(){
		return this.waitForItem;
	}
	
	public void setCards(List<JLabel> listOfCard){
		this.cards = listOfCard;
		startListenCard();
	}
	
	
	
}
