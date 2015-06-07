package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class LightsHandler {

	private boolean wait = false;
	private ClickableBox boxes = new ClickableBox();
	private MouseListener position;
	private Coordinates coordinates;
	
	private class LightsMovement implements MouseListener{
		
	
		public void mouseClicked(MouseEvent e) {

			Point click = e.getPoint();
			setCoordinates(boxes.getMinimumDistance(click));	
			setWait(true);
			
		
		}

		public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		}

		public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		}

		public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		}

		public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		}
	}
	
	public void startListen(JLabel background){
		position = new LightsMovement();
		background.addMouseListener(position);
	}

	public void setWait(boolean flag){
		this.wait = flag;
	}
	
	public boolean getWait(){
		return this.wait;
	}
	
	public void setCoordinates(Coordinates coords){
		this.coordinates = coords;
	}
	
	public Coordinates getCoordinates(){
		return this.coordinates;
	}
}
