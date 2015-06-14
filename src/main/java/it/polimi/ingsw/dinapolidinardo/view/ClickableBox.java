package it.polimi.ingsw.dinapolidinardo.view;




import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



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
	
	
	
	//Initialize each center 
	public ClickableBox(){
		
		//Column A
		centers[0][0] = wallPoint;
		centers[1][0] = new Point(26, 74);
		centers[2][0] = new Point(26, 111);
		centers[3][0] = new Point(26, 149);
		centers[4][0] = new Point(26, 185);
		centers[5][0] = new Point(26, 223);
		centers[6][0] = wallPoint;
		centers[7][0] = wallPoint;
		centers[8][0] = new Point(26, 334);
		centers[9][0] = new Point(26, 371);
		centers[10][0] = new Point(26, 408);
		centers[11][0] = new Point(26, 445);
		centers[12][0] = new Point(26, 483);
		centers[13][0] = new Point(26, 520);
		
		//Column B
		centers[0][1] = new Point(58, 55);
		centers[1][1] = new Point(58, 93);
		centers[2][1] = new Point(58, 130);
		centers[3][1] = new Point(58, 167);
		centers[4][1] = new Point(58, 204);
		centers[5][1] = new Point(58, 241);
		centers[6][1] = wallPoint;
		centers[7][1] = new Point(58, 315);
		centers[8][1] = new Point(58, 353);
		centers[9][1] = new Point(58, 390);
		centers[10][1] = new Point(58, 427);
		centers[11][1] = new Point(58, 464);
		centers[12][1] = new Point(58, 502);
		centers[13][1] = new Point(58, 539);
		
		//Column C
		centers[0][2] = new Point(90, 37);
		centers[1][2] = new Point(90, 74);
		centers[2][2] = new Point(90, 112);
		centers[3][2] = new Point(90, 149);
		centers[4][2] = new Point(90, 186);
		centers[5][2] = new Point(90, 223);
		centers[6][2] = new Point(90, 261);
		centers[7][2] = new Point(90, 298);
		centers[8][2] = new Point(90, 334);
		centers[9][2] = new Point(90, 372);
		centers[10][2] = new Point(90, 409);
		centers[11][2] = new Point(90, 446);
		centers[12][2] = new Point(90, 483);
		centers[13][2] = new Point(90, 521);
		
		//Column D
		centers[0][3] = wallPoint;
		centers[1][3] = new Point(123, 93);
		centers[2][3] = new Point(123, 129);
		centers[3][3] = wallPoint;
		centers[4][3] = new Point(123, 204);
		centers[5][3] = wallPoint;
		centers[6][3] = wallPoint;
		centers[7][3] = new Point(123, 315);
		centers[8][3] = new Point(123, 353);
		centers[9][3] = new Point(123, 390);
		centers[10][3] = new Point(123, 427);
		centers[11][3] = new Point(123, 464);
		centers[12][3] = new Point(123, 501);
		centers[13][3] = new Point(123, 539);
		
		//Column E
		centers[0][4] = wallPoint;
		centers[1][4] = new Point(154, 74);
		centers[2][4] = new Point(154, 111);
		centers[3][4] = new Point(154, 149);
		centers[4][4] = new Point(154, 186);
		centers[5][4] = new Point(154, 223);
		centers[6][4] = wallPoint;
		centers[7][4] = new Point(154, 297);
		centers[8][4] = new Point(154, 334);
		centers[9][4] = new Point(154, 371);
		centers[10][4] = new Point(154, 408);
		centers[11][4] = new Point(154, 446);
		centers[12][4] = new Point(154, 483);
		centers[13][4] = wallPoint;
		
		//Column F
		centers[0][5] = new Point(186, 56);
		centers[1][5] = new Point(186, 92);
		centers[2][5] = new Point(186, 129);
		centers[3][5] = new Point(186, 167);
		centers[4][5] = new Point(186, 204);
		centers[5][5] = new Point(186, 241);
		centers[6][5] = new Point(186, 278);
		centers[7][5] = new Point(186, 315);
		centers[8][5] = new Point(186, 353);
		centers[9][5] = new Point(186, 390);
		centers[10][5] = new Point(186, 427);
		centers[11][5] = wallPoint;
		centers[12][5] = wallPoint;
		centers[13][5] = wallPoint;
		
		//Column G
		centers[0][6] = new Point(218, 37);
		centers[1][6] = new Point(218, 74);
		centers[2][6] = new Point(218, 111);
		centers[3][6] = new Point(218, 149);
		centers[4][6] = new Point(218, 185);
		centers[5][6] = new Point(218, 223);
		centers[6][6] = new Point(218, 260);
		centers[7][6] = new Point(218, 297);
		centers[8][6] = new Point(218, 334);
		centers[9][6] = new Point(218, 371);
		centers[10][6] = new Point(218, 409);
		centers[11][6] = new Point(218, 446);
		centers[12][6] = new Point(218, 483);
		centers[13][6] = new Point(218, 521);
		
		//Column H
		centers[0][7] = new Point(251, 55);
		centers[1][7] = new Point(251, 92);
		centers[2][7] = new Point(251, 130);
		centers[3][7] = new Point(251, 167);
		centers[4][7] = wallPoint;
		centers[5][7] = new Point(251, 241);
		centers[6][7] = new Point(251, 279);
		centers[7][7] = new Point(251, 315);
		centers[8][7] = new Point(251, 352);
		centers[9][7] = wallPoint;
		centers[10][7] = wallPoint;
		centers[11][7] = new Point(251, 465);
		centers[12][7] = new Point(251, 501);
		centers[13][7] = new Point(251, 539);
		
		//Column I
		centers[0][8] = new Point(282, 37);
		centers[1][8] = new Point(282, 74);
		centers[2][8] = new Point(282, 111);
		centers[3][8] = new Point(282, 148);
		centers[4][8] = new Point(282, 186);
		centers[5][8] = wallPoint;
		centers[6][8] = new Point(282, 260);
		centers[7][8] = new Point(282, 296);
		centers[8][8] = new Point(282, 334);
		centers[9][8] = new Point(282, 370);
		centers[10][8] = new Point(282, 408);
		centers[11][8] = wallPoint;
		centers[12][8] = new Point(282, 482);
		centers[13][8] = new Point(282, 520);
		
		//Column J
		centers[0][9] = new Point(313, 55);
		centers[1][9] = new Point(313, 93);
		centers[2][9] = new Point(313, 130);
		centers[3][9] = new Point(313, 167);
		centers[4][9] = new Point(313, 204);
		centers[5][9] = new Point(313, 242);
		centers[6][9] = wallPoint;
		centers[7][9] = new Point(313, 315);
		centers[8][9] = new Point(313, 353);
		centers[9][9] = new Point(313, 390);
		centers[10][9] = new Point(313, 427);
		centers[11][9] = wallPoint;
		centers[12][9] = new Point(313, 501);
		centers[13][9] = new Point(313, 539);
		
		//Column K
		centers[0][10] = new Point(346, 37);
		centers[1][10] = new Point(346, 73);
		centers[2][10] = new Point(346, 111);
		centers[3][10] = new Point(346, 148);
		centers[4][10] = new Point(346, 185);
		centers[5][10] = new Point(346, 222);
		centers[6][10] = wallPoint;
		centers[7][10] = new Point(346, 297);
		centers[8][10] = new Point(346, 334);
		centers[9][10] = new Point(346, 370);
		centers[10][10] = new Point(346, 408);
		centers[11][10] = new Point(346, 445);
		centers[12][10] = wallPoint;
		centers[13][10] = new Point(346, 520);
		
		//Column L
		centers[0][11] = new Point(378, 55);
		centers[1][11] = new Point(378, 92);
		centers[2][11] = new Point(378, 130);
		centers[3][11] = new Point(378, 167);
		centers[4][11] = new Point(378, 204);
		centers[5][11] = new Point(378, 242);
		centers[6][11] = wallPoint;
		centers[7][11] = new Point(378, 316);
		centers[8][11] = new Point(378, 352);
		centers[9][11] = new Point(378, 390);
		centers[10][11] = new Point(378, 426);
		centers[11][11] = new Point(378, 464);
		centers[12][11] = new Point(378, 501);
		centers[13][11] = new Point(378, 539);
		
		//Column M
		centers[0][12] = new Point(411, 37);
		centers[1][12] = new Point(411, 74);
		centers[2][12] = new Point(411, 111);
		centers[3][12] = new Point(411, 148);
		centers[4][12] = new Point(411, 185);
		centers[5][12] = new Point(411, 222);
		centers[6][12] = wallPoint;
		centers[7][12] = new Point(411, 297);
		centers[8][12] = new Point(411, 335);
		centers[9][12] = new Point(411, 371);
		centers[10][12] = new Point(411, 409);
		centers[11][12] = new Point(411, 445);
		centers[12][12] = new Point(411, 482);
		centers[13][12] = new Point(411, 520);
		
		//Column N
		centers[0][13] = new Point(443, 55);
		centers[1][13] = new Point(443, 92);
		centers[2][13] = new Point(443, 130);
		centers[3][13] = new Point(443, 167);
		centers[4][13] = new Point(443, 204);
		centers[5][13] = new Point(443, 241);
		centers[6][13] = wallPoint;
		centers[7][13] = new Point(443, 316);
		centers[8][13] = new Point(443, 353);
		centers[9][13] = new Point(443, 390);
		centers[10][13] = new Point(443, 427);
		centers[11][13] = new Point(443, 464);
		centers[12][13] = new Point(443, 501);
		centers[13][13] = new Point(443, 539);
		
		//Column O
		centers[0][14] = wallPoint;
		centers[1][14] = new Point(475, 74);
		centers[2][14] = wallPoint;
		centers[3][14] = wallPoint;
		centers[4][14] = new Point(475, 185);
		centers[5][14] = new Point(475, 223);
		centers[6][14] = new Point(475, 259);
		centers[7][14] = new Point(475, 297);
		centers[8][14] = new Point(475, 334);
		centers[9][14] = new Point(475, 371);
		centers[10][14] = new Point(475, 408);
		centers[11][14] = new Point(475, 445);
		centers[12][14] = new Point(475, 483);
		centers[13][14] = new Point(475, 520);
		
		//Column P
		centers[0][15] = new Point(506, 55);
		centers[1][15] = new Point(506, 92);
		centers[2][15] = new Point(506, 130);
		centers[3][15] = new Point(506, 167);
		centers[4][15] = new Point(506, 203);
		centers[5][15] = new Point(506, 241);
		centers[6][15] = new Point(506, 278);
		centers[7][15] = new Point(506, 315);
		centers[8][15] = new Point(506, 352);
		centers[9][15] = new Point(506, 390);
		centers[10][15] = new Point(506, 427);
		centers[11][15] = new Point(506, 464);
		centers[12][15] = new Point(506, 501);
		centers[13][15] = new Point(506, 538);
		
		//Column Q
		centers[0][16] = new Point(538, 37);
		centers[1][16] = new Point(538, 74);
		centers[2][16] = new Point(538, 112);
		centers[3][16] = new Point(538, 148);
		centers[4][16] = new Point(538, 186);
		centers[5][16] = new Point(538, 222);
		centers[6][16] = new Point(538, 260);
		centers[7][16] = new Point(538, 297);
		centers[8][16] = new Point(538, 334);
		centers[9][16] = new Point(538, 371);
		centers[10][16] = new Point(538, 408);
		centers[11][16] = new Point(538, 445);
		centers[12][16] = new Point(538, 482);
		centers[13][16] = new Point(538, 520);
		
		//Column R
		centers[0][17] = new Point(571, 55);
		centers[1][17] = new Point(571, 92);
		centers[2][17] = new Point(571, 130);
		centers[3][17] = new Point(571, 167);
		centers[4][17] = new Point(571, 204);
		centers[5][17] = new Point(571, 241);
		centers[6][17] = new Point(571, 278);
		centers[7][17] = new Point(571, 316);
		centers[8][17] = new Point(571, 352);
		centers[9][17] = wallPoint;
		centers[10][17] = wallPoint;
		centers[11][17] = new Point(571, 464);
		centers[12][17] = new Point(571, 501);
		centers[13][17] = wallPoint;
		
		//Column S
		centers[0][18] = wallPoint;
		centers[1][18] = new Point(602, 74);
		centers[2][18] = wallPoint;
		centers[3][18] = new Point(602, 148);
		centers[4][18] = new Point(602, 185);
		centers[5][18] = new Point(602, 223);
		centers[6][18] = new Point(602, 260);
		centers[7][18] = new Point(602, 297);
		centers[8][18] = new Point(602, 334);
		centers[9][18] = wallPoint;
		centers[10][18] = wallPoint;
		centers[11][18] = new Point(602, 446);
		centers[12][18] = new Point(602, 483);
		centers[13][18] = wallPoint;
		
		//Column T
		centers[0][19] = wallPoint;
		centers[1][19] = new Point(634, 92);
		centers[2][19] = wallPoint;
		centers[3][19] = wallPoint;
		centers[4][19] = new Point(634, 204);
		centers[5][19] = new Point(634, 241);
		centers[6][19] = new Point(634, 278);
		centers[7][19] = new Point(634, 315);
		centers[8][19] = wallPoint;
		centers[9][19] = wallPoint;
		centers[10][19] = new Point(634, 427);
		centers[11][19] = new Point(634, 464);
		centers[12][19] = new Point(634, 501);
		centers[13][19] = new Point(634, 538);
		
		//Column U
		centers[0][20] = new Point(666, 37);
		centers[1][20] = new Point(666, 74);
		centers[2][20] = new Point(666, 111);
		centers[3][20] = new Point(666, 148);
		centers[4][20] = new Point(666, 185);
		centers[5][20] = new Point(666, 222);
		centers[6][20] = new Point(666, 260);
		centers[7][20] = new Point(666, 297);
		centers[8][20] = new Point(666, 334);
		centers[9][20] = new Point(666, 371);
		centers[10][20] = new Point(666, 408);
		centers[11][20] = new Point(666, 445);
		centers[12][20] = new Point(666, 483);
		centers[13][20] = new Point(666, 520);
		
		//Column V
		centers[0][21] = new Point(699, 55);
		centers[1][21] = new Point(699, 92);
		centers[2][21] = new Point(699, 129);
		centers[3][21] = new Point(699, 167);
		centers[4][21] = new Point(699, 203);
		centers[5][21] = new Point(699, 241);
		centers[6][21] = wallPoint;
		centers[7][21] = new Point(699, 315);
		centers[8][21] = new Point(699, 352);
		centers[9][21] = new Point(699, 390);
		centers[10][21] = new Point(699, 427);
		centers[11][21] = new Point(699, 464);
		centers[12][21] = new Point(699, 501);
		centers[13][21] = new Point(699, 539);
		
		//Column W
		centers[0][22] = wallPoint;
		centers[1][22] = new Point(731, 74);
		centers[2][22] = new Point(731, 111);
		centers[3][22] = new Point(731, 148);
		centers[4][22] = new Point(731, 185);
		centers[5][22] = new Point(731, 222);
		centers[6][22] = wallPoint;
		centers[7][22] = wallPoint;
		centers[8][22] = new Point(731, 334);
		centers[9][22] = new Point(731, 371);
		centers[10][22] = new Point(731, 409);
		centers[11][22] = new Point(731, 445);
		centers[12][22] = new Point(731, 483);
		centers[13][22] = new Point(731, 519);
		
		//finally, the end!		
		
	}
	
	
	
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
