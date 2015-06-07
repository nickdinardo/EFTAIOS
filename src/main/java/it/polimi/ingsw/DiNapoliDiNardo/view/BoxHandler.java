package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class BoxHandler {
	
	private MouseListener click;
	private ClickableBox boxes = new ClickableBox();
	private Coordinates coordinates = new Coordinates(0, 0);
	private boolean wait = false;
	private ButtonHandler button;

	public BoxHandler(ButtonHandler b){
		this.button = b;
	}
	
	public BoxHandler(){
		
	}
	
	
	
	private class ClickOnBox implements MouseListener {
		private ButtonHandler button;
		
		public ClickOnBox(ButtonHandler b){
			this.button = b;
		}
		
		public void mouseClicked(MouseEvent event) {
			
			Point click = event.getPoint();
			setCoordinates(boxes.getMinimumDistance(click));
			if (button!= null)
				button.setWaitCoordinates(false);
			setWait(true);
		
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
	
	public void startListen(JLabel background){
		click = new ClickOnBox(button);
		background.addMouseListener(click);
	}
	
	public Coordinates getCoordinates(){
		return this.coordinates;
	}
	
	public void setCoordinates(Coordinates coord){
		this.coordinates = coord;
	}
	
	public void setWait(boolean flag){
		this.wait = flag;
	}
	
	public boolean getWait(){
		return this.wait;
	}
}
