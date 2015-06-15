package it.polimi.ingsw.dinapolidinardo.view;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;


/**
 * Class that manages the clicks on the map image to select 
 * the single hexagon of the map that has been pressed.
 * <p>
 * It stores the coordinates ofn the clicked map box in a variable
 * that can be accessed by a getter to obtain them
 */
public class BoxHandler {
	
	private MouseListener click;
	private ClickableBox boxes = new ClickableBox();
	private Coordinates coordinates = new Coordinates(0, 0);
	private boolean wait = false;
	

	/**
	 * MouseAdapter that gets the exact point clicked on the map image
	 * and converts it in a box coordinates
	 */
	private class ClickOnBox extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent event) {
			
			Point clickedPoint = event.getPoint();
			setCoordinates(boxes.getMinimumDistance(clickedPoint));
			setWait(true);
		
		}

	}	
	
	/**
	 * Creates a new MouseAdapter and assigns it at the specified label
	 * 
	 * @param background Label on which the MouseAdapter has to be activated
	 * @return the MouseAdapter object
	 */
	public MouseListener startListen(JLabel background){
		click = new ClickOnBox();
		background.addMouseListener(click);
		return click;
	}
	
	
	
	//getters and setters
	
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
