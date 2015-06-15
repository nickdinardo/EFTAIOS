package it.polimi.ingsw.dinapolidinardo.view;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;


/**
 * Class that manages the clicks on the map image to select 
 * the single hexagon of the map that has been pressed, in the LightsCard
 * sector selection.
 * <p>
 * It stores the coordinates of the clicked map box in a variable
 * that can be accessed by a getter to be obtained
 */
public class LightsHandler {

	private boolean wait = false;
	private ClickableBox boxes; 
	private MouseListener position;
	private Coordinates coordinates;
	
	
	/**
	 * Constructor, receives an instance of the ClickableBox utility object
	 * 
	 * @param cb the ClickableBox utility object
	 * @see ClickableBox
	 */
	public LightsHandler (ClickableBox cb){
		this.boxes = cb;
	}
	
	
	/**
	 * MouseAdapter that gets the exact point clicked on the map image
	 * and converts it in a box coordinates
	 */
	private class LightsMovement extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {

			Point click = e.getPoint();
			setCoordinates(boxes.getMinimumDistance(click));	
			setWait(true);
			
		
		}

	}
	
	
	/**
	 * Creates a new MouseAdapter and assigns it at the specified label
	 * 
	 * @param background Label on which the MouseAdapter has to be activated
	 */
	public void startListen(JLabel background){
		position = new LightsMovement();
		background.addMouseListener(position);
	}

	
	
	//getters and setters
	
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
