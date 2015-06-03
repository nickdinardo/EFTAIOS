package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LightsHandler implements MouseListener {

	private boolean wait = false;
	
	public void mouseClicked(MouseEvent e) {

		Point click = e.getPoint();
		coordsForLights  = boxes.getMinimumDistance(click);	
		this.wait = true;
		
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

	public void setWait(boolean flag){
		this.wait = flag;
	}
	
	public boolean getWait(){
		return this.wait;
	}
}
