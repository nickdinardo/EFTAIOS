package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class BoxHandler {
	
	private MouseListener click;
	private ClickableBox boxes = new ClickableBox();
	private Coordinates coordinates = new Coordinates(0, 0);
	private boolean wait = false;
	

	private class ClickOnBox extends MouseAdapter {
		
		
		@Override
		public void mouseClicked(MouseEvent event) {
			
			Point clickedPoint = event.getPoint();
			setCoordinates(boxes.getMinimumDistance(clickedPoint));
			setWait(true);
		
		}

	}	
	
	public MouseListener startListen(JLabel background){
		click = new ClickOnBox();
		background.addMouseListener(click);
		return click;
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
