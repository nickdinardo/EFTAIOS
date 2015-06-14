package it.polimi.ingsw.dinapolidinardo.model.boxes;


/**
 * Basic and serializable object that represent 
 * an horizontal and a vertical coordinate
 * 
 */
public class Coordinates implements java.io.Serializable {
	
	
	
	private static final long serialVersionUID = 1L;
	private int coordX;
	private int coordY;
	
	/**
	 * Default constructor
	 */
	public Coordinates (){
	}
	
	/**
	 * Constructor 
	 * 
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 */
	public Coordinates (int x, int y){
		this.setCoordX(x);
		this.setCoordY(y);
	}
	
	
	//getters and setters
	
	public int getCoordX(){
		return this.coordX;
	}
	
	public int getCoordY(){
		return this.coordY;
	}
	
	public void setCoordX(int x){
		this.coordX = x;
	}
	
	public void setCoordY(int y){
		this.coordY = y;
	}
}

