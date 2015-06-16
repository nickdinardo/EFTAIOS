package it.polimi.ingsw.dinapolidinardo.view;




import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Class the keep track of the pixel position of any center of the map and offer
 * some graphical functionalities such as the conversion to game coordinates of a click
 * or the coloring of only determinate parts of the image
 */
public class ClickableBox {
	
	private static final int ROWS = 14;
	private static final int COLUMNS = 23;
	private static final double DISTANCE = 18.50;
	private Point[][] centers = new Point[ROWS][COLUMNS];
	private Point wallPoint = new Point(50, 50);
	//max vertical distance from center in hexagon 
	private static final int DY = 18;
	//max horizontal distance from center in hexagon 
	private static final int DX = 20;
	
	
	
	/**
	 * Constructor, it initialize every hexagon pixel coordinates center 
	 * in a matrix reading from text file
	 * 
	 * @throws IOException if can't load the Pixels list file
	 */
	public ClickableBox() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("externalresources\\Maps\\GalileiMapPixels.txt"));
		String sCurrentLine;
		for (int j=0; j<23; j++){
			for (int i=0; i<14; i++){
				sCurrentLine = br.readLine();
				List<String> parts = Arrays.asList(sCurrentLine.split(";"));
				if ("0".equals(parts.get(0))){
					centers[i][j] = wallPoint;
				}
				else{
					centers[i][j] = new Point(Integer.parseInt(parts.get(0)), Integer.parseInt(parts.get(1)));
				}
			}	
		}
		br.close();
		
	}
	
	
	/**
	 * Converts a click's coordinates expressed in pixels in the coordinates of the 
	 * hexagon in which the click happened.
	 * <p>
	 * Works with an algorithm that measures the distance between the click 
	 * and any hexagon center: when finds a distance that indicates that the click is inside
	 * an hexagon, returns coordinates of that hexagon
	 * 
	 * @param clickedPoint the Point object of the click
	 * @return a Coordinates object representing the in-game coordinates of a box
	 */
	public Coordinates getMinimumDistance(Point clickedPoint){
		
		int x = 0, y = 0;
		boolean flag = false;
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				if(centers[i][j] != wallPoint && centers[i][j].distance(clickedPoint) <= DISTANCE){
					x = i + 1;
					y = j + 1;
					flag = true;
					break;
				}
			}
			if(flag)
				break;
		}
		if (x == 0)
			x++;
		if (y == 0)
			y++;
		return new Coordinates(y, x); 
	}		

	
	/**
	 * Color only the parts of a map image that represent the actual position and the boxes
	 * reachable with a single movement of the player
	 * <p>
	 * The algorithm is capable of individuate for every hexagon only the background parts 
	 * and color only those, leaving untouched the irregular borders and the inside tag
	 * 
	 * @param image the basic map image that will be modified
	 * @param color1 the color of the reachable Dangerous Sectors
	 * @param color2 the color of the reachable Safe Sectors
	 * @param colorPos1  the color of the actual position Sector
	 * @param listofboxes the list of reachable boxes coded as a String
	 * @param position the actual position box coded as a String
	 * @return a Buffered Image identical to the basic Image but with the selected zones colored
	 */
	public BufferedImage colorReachablesBoxes(BufferedImage image, Color color1, Color color2, Color colorPos1, String listofboxes, String position){
		
		//load and draw a new image 
		BufferedImage dimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D gph = dimg.createGraphics();  
	    gph.setComposite(AlphaComposite.Src);  
	    gph.drawImage(image, null, 0, 0);  
	    gph.dispose();  
		
		List<String> boxesInStringForm = Arrays.asList(listofboxes.split("!"));
		List<Point> hexagonsCenters = new ArrayList<Point>();
		Point actualPosition = new Point (0,0);
		int indexI;
		int indexJ;
		
		//transform the string containing the coordinates in actual Points (their centers)
		for (String s : boxesInStringForm){
			char letter = s.charAt(0);
			s = s.substring(1);
			//parse the ASCII code of the char and convert it to a number, starting from 'A'-->0
			indexJ = ((int)letter)-65;
			try	{
				indexI = (Integer.parseInt(s))-1;
				hexagonsCenters.add(centers[indexI][indexJ]);
			}			
			catch (NumberFormatException ex){
				//skip adding this box if can't convert to int the string
			}
			
		}
		
		char letter = position.charAt(0);
		String secondPart = position.substring(1);
		//parse the ASCII code of the char and convert it to a number, starting from 'A'-->0
		indexJ = ((int)letter)-65;
		try	{
			indexI = (Integer.parseInt(secondPart))-1;
			actualPosition = centers[indexI][indexJ];
		}			
		catch (NumberFormatException ex){
			//skip this box if can't convert to int the string
		}
		
		//color the reachable boxes, differently if they are dangerous or not			
		for (Point p : hexagonsCenters) {
			//color hexagons changing his pixels color in the main image
			int cx = (int)p.getX();
			int cy = (int)p.getY();
			//once set the x and y of the center, explore all the pixels that form the hex
			for(int i = cy-DY; i < cy+DY; i++) {  
				int addToX = (int) (Math.abs(i-cy)/Math.sqrt(3));
				for(int j = cx-DX+addToX; j < cx+DX-addToX; j++) {  
					int rgb = dimg.getRGB(j, i);
					int r = (rgb >> 16) & 0xFF;
					int g = (rgb >> 8) & 0xFF;
					int b = (rgb >> 0) & 0xFF;
		   
					if(!((int)p.getX() == (int)actualPosition.getX() && (int)p.getY() == (int)actualPosition.getY())){
						//color Dangerous Boxes
						if(Math.abs(r-75)<3 && Math.abs(g-75)<3 && Math.abs(b-75)<3) {  
							Color newcolor = color1;
							dimg.setRGB(j, i, newcolor.getRGB());  
						} 
						//color Normal Boxes
						if( r<3 && g<3 && b<3) {  
							Color newcolor2 = color2; //17 38 96
							dimg.setRGB(j, i, newcolor2.getRGB());  
	
						}
					}
				}  
			}  
		}

		//color actual position, differently if it's dangerous or not	
		int cx = (int)actualPosition.getX();
		int cy = (int)actualPosition.getY();
		//once set the x and y of the center, explore all the pixels that form the hex
		for(int i = cy-DY; i < cy+DY; i++) {  
			int addToX = (int) (Math.abs(i-cy)/Math.sqrt(3));
			for(int j = cx-DX+addToX; j < cx+DX-addToX; j++) {  
				int rgb = dimg.getRGB(j, i);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb >> 0) & 0xFF;
	   
				Color newcolor = colorPos1;
				
				//color Dangerous Boxes
				if(Math.abs(r-75)<3 && Math.abs(g-75)<3 && Math.abs(b-75)<3){  
					dimg.setRGB(j, i, newcolor.getRGB());  
				} 
				//color Normal Boxes
				if( r<3 && g<3 && b<3) {  
					dimg.setRGB(j, i, newcolor.getRGB());  

				}
			}  
		}  
		
		
		
		return dimg;
	}

	
	

}
