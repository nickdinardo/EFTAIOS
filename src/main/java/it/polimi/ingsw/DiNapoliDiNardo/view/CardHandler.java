package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;

public class CardHandler  {

		private int indexCard = 8;
		private boolean waitForItem = false;
		private List<JLabel> cards;
		private MouseListener clickableCard1;
		private MouseListener clickableCard2;
		private MouseListener clickableCard3;
		
		private class CardHandler1 implements MouseListener {

			public void mouseClicked(MouseEvent event) {
				indexCard = 1;
				setWaitForItem(true);
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

		}	
		
		private class CardHandler2 implements MouseListener {

			public void mouseClicked(MouseEvent event) {
				indexCard = 2;
				setWaitForItem(true);
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

		}	
		
		private class CardHandler3 implements MouseListener {

			public void mouseClicked(MouseEvent event) {
				indexCard = 3;
				setWaitForItem(true);
			
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
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
		startListen();
	}
	
	public void startListen(){
		switch(cards.size()){
		case 1:
			if(!cards.isEmpty()){
				clickableCard1 = new CardHandler1();
				cards.get(0).addMouseListener(clickableCard1);
				break;
			}
		case 2:
			clickableCard1 = new CardHandler1();
			cards.get(0).addMouseListener(clickableCard1);
			clickableCard2 = new CardHandler2();
			cards.get(1).addMouseListener(clickableCard2);
			break;
		case 3:
			clickableCard1 = new CardHandler1();
			cards.get(0).addMouseListener(clickableCard1);
			clickableCard2 = new CardHandler2();
			cards.get(1).addMouseListener(clickableCard2);
			clickableCard3 = new CardHandler3();
			cards.get(2).addMouseListener(clickableCard3);
			
		}
	}
}
