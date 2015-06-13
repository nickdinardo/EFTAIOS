package it.polimi.ingsw.dinapolidinardo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class CardHandler  {

		private int indexCard = 8;
		private boolean waitForItem = false;
		private List<JLabel> cards;
		private MouseListener clickableCard1;
		private MouseListener clickableCard2;
		private MouseListener clickableCard3;
		private TurnFrame turnframe;
		private DiscardFrame discardframe;
		
		public CardHandler(TurnFrame tf, DiscardFrame df){
			this.turnframe = tf;
			this.discardframe = df;
		}
		
		private class CardHandler1 implements MouseListener {
			private TurnFrame turnframe;
			private DiscardFrame discardframe;
			
			public CardHandler1(TurnFrame tf, DiscardFrame df){
				this.turnframe = tf;
				this.discardframe = df;
			}
			@Override
			public void mouseClicked(MouseEvent event) {
				if (turnframe.getItem().get(0) != ""){
					setIndexCard(1);
					setWaitForItem(true);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if (discardframe == null)
					turnframe.endarkCard(1);
				else
					discardframe.endarkCard(1);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (discardframe == null)
					turnframe.enlightCard(1);
				else
					discardframe.enlightCard(1);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (discardframe == null)
					turnframe.enlightCard(1);
				else
					discardframe.enlightCard(1);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (discardframe == null)
					turnframe.endarkCard(1);
				else
					discardframe.endarkCard(1);
			}

		}	
		
		private class CardHandler2 implements MouseListener {
			private TurnFrame turnframe;
			private DiscardFrame discardframe;
			
			public CardHandler2(TurnFrame tf, DiscardFrame df){
				this.turnframe = tf;
				this.discardframe = df;
			}
			@Override
			public void mouseClicked(MouseEvent event) {
				if (turnframe.getItem().size()>1){
					setIndexCard(2);
					setWaitForItem(true);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if (discardframe == null)
					turnframe.endarkCard(2);
				else
					discardframe.endarkCard(2);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (discardframe == null)
					turnframe.enlightCard(2);
				else
					discardframe.enlightCard(2);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (discardframe == null)
					turnframe.enlightCard(2);
				else
					discardframe.enlightCard(2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (discardframe == null)
					turnframe.endarkCard(2);
				else
					discardframe.endarkCard(2);
			}

		}	
		
		private class CardHandler3 implements MouseListener {
			private TurnFrame turnframe;
			private DiscardFrame discardframe;
			
			public CardHandler3(TurnFrame tf, DiscardFrame df){
				this.turnframe = tf;
				this.discardframe = df;
			}
			
			@Override
			public void mouseClicked(MouseEvent event) {
				if (turnframe.getItem().size()>2){
					setIndexCard(3);
					setWaitForItem(true);
				}
			
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if (discardframe == null)
					turnframe.endarkCard(3);
				else
					discardframe.endarkCard(3);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (discardframe == null)
					turnframe.enlightCard(3);
				else
					discardframe.enlightCard(3);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (discardframe == null)
					turnframe.enlightCard(3);
				else
					discardframe.enlightCard(3);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (discardframe == null)
					turnframe.endarkCard(3);
				else
					discardframe.endarkCard(3);
			}

		}	
		
		private class UseButton implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event){
				setIndexCard(6);
				setWaitForItem(true);
			}
		}
		
		private class NoButton implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event){
				setIndexCard(5);
				setWaitForItem(true);
			}
		}
	
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
	
	
	public void startListenCard(){
	
		clickableCard1 = new CardHandler1(turnframe, discardframe);
		cards.get(0).addMouseListener(clickableCard1);
		clickableCard2 = new CardHandler2(turnframe, discardframe);
		cards.get(1).addMouseListener(clickableCard2);
		clickableCard3 = new CardHandler3(turnframe, discardframe);
		cards.get(2).addMouseListener(clickableCard3);
		
	}
	
	
	public void removeListeners(){
		
		indexCard = 8;
		cards.get(0).removeMouseListener(clickableCard1);
		cards.get(1).removeMouseListener(clickableCard2);
		cards.get(2).removeMouseListener(clickableCard3);
			
	}
		
	
	public void startListenNoButton(JButton buttonNo){
		ActionListener no = new NoButton();
		buttonNo.addActionListener(no);
		
	}
	
	
	public void startListenUseButton(JButton useButton){
		ActionListener use = new UseButton();
		useButton.addActionListener(use);
		
	}
}
