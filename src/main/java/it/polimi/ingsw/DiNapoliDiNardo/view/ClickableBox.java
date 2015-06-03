package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.awt.Point;


public class ClickableBox {
	
	private static final int ROWS = 14;
	private static final int COLUMNS = 23;
	private static final double DISTANCE = 16.00;
	private Point[][] centers = new Point[ROWS][COLUMNS];
	private Point wallPoint = new Point(50, 50);
	
	//Initialize each center 
	public ClickableBox(){
		
		//Column A
		centers[0][0] = wallPoint;
		centers[1][0] = new Point(18, 76);
		centers[2][0] = new Point(18, 111);
		centers[3][0] = new Point(18, 146);
		centers[4][0] = new Point(18, 181);
		centers[5][0] = new Point(18, 216);
		centers[6][0] = wallPoint;
		centers[7][0] = wallPoint;
		centers[8][0] = new Point(18, 321);
		centers[9][0] = new Point(18, 356);
		centers[10][0] = new Point(18, 391);
		centers[11][0] = new Point(18, 426);
		centers[12][0] = new Point(18, 461);
		centers[13][0] = new Point(18, 496);
		
		//Column B
		centers[0][1] = new Point(48, 58);
		centers[1][1] = new Point(48, 93);
		centers[2][1] = new Point(48, 128);
		centers[3][1] = new Point(48, 163);
		centers[4][1] = new Point(48, 198);
		centers[5][1] = new Point(48, 233);
		centers[6][1] = wallPoint;
		centers[7][1] = new Point(48, 303);
		centers[8][1] = new Point(48, 338);
		centers[9][1] = new Point(48, 373);
		centers[10][1] = new Point(48, 408);
		centers[11][1] = new Point(48, 443);
		centers[12][1] = new Point(48, 478);
		centers[13][1] = new Point(48, 513);
		
		//Column C
		centers[0][2] = new Point(78, 41);
		centers[1][2] = new Point(78, 76);
		centers[2][2] = new Point(78, 111);
		centers[3][2] = new Point(78, 146);
		centers[4][2] = new Point(78, 181);
		centers[5][2] = new Point(78, 216);
		centers[6][2] = new Point(78, 251);
		centers[7][2] = new Point(78, 286);
		centers[8][2] = new Point(78, 321);
		centers[9][2] = new Point(78, 356);
		centers[10][2] = new Point(78, 391);
		centers[11][2] = new Point(78, 426);
		centers[12][2] = new Point(78, 461);
		centers[13][2] = new Point(78, 496);
		
		//Column D
		centers[0][3] = wallPoint;
		centers[1][3] = new Point(108, 94);
		centers[2][3] = new Point(108, 129);
		centers[3][3] = wallPoint;
		centers[4][3] = new Point(108, 199);
		centers[5][3] = wallPoint;
		centers[6][3] = wallPoint;
		centers[7][3] = new Point(108, 304);
		centers[8][3] = new Point(108, 339);
		centers[9][3] = new Point(108, 374);
		centers[10][3] = new Point(108, 409);
		centers[11][3] = new Point(108, 444);
		centers[12][3] = new Point(108, 479);
		centers[13][3] = new Point(108, 514);
		
		//Column E
		centers[0][4] = wallPoint;
		centers[1][4] = new Point(138, 76);
		centers[2][4] = new Point(138, 111);
		centers[3][4] = new Point(138, 146);
		centers[4][4] = new Point(138, 181);
		centers[5][4] = new Point(138, 216);
		centers[6][4] = wallPoint;
		centers[7][4] = new Point(138, 286);
		centers[8][4] = new Point(138, 321);
		centers[9][4] = new Point(138, 356);
		centers[10][4] = new Point(138, 391);
		centers[11][4] = new Point(138, 426);
		centers[12][4] = new Point(138, 461);
		centers[13][4] = wallPoint;
		
		//Column F
		centers[0][5] = new Point(168, 58);
		centers[1][5] = new Point(168, 93);
		centers[2][5] = new Point(168, 128);
		centers[3][5] = new Point(168, 163);
		centers[4][5] = new Point(168, 198);
		centers[5][5] = new Point(168, 233);
		centers[6][5] = new Point(168, 268);
		centers[7][5] = new Point(168, 303);
		centers[8][5] = new Point(168, 338);
		centers[9][5] = new Point(168, 373);
		centers[10][5] = new Point(168, 408);
		centers[11][5] = wallPoint;
		centers[12][5] = wallPoint;
		centers[13][5] = wallPoint;
		
		//Column G
		centers[0][6] = new Point(198, 41);
		centers[1][6] = new Point(198, 76);
		centers[2][6] = new Point(198, 111);
		centers[3][6] = new Point(198, 146);
		centers[4][6] = new Point(198, 181);
		centers[5][6] = new Point(198, 216);
		centers[6][6] = new Point(198, 251);
		centers[7][6] = new Point(198, 286);
		centers[8][6] = new Point(198, 321);
		centers[9][6] = new Point(198, 356);
		centers[10][6] = new Point(198, 391);
		centers[11][6] = new Point(198, 426);
		centers[12][6] = new Point(198, 461);
		centers[13][6] = new Point(198, 496);
		
		//Column H
		centers[0][7] = new Point(228, 58);
		centers[1][7] = new Point(228, 93);
		centers[2][7] = new Point(228, 128);
		centers[3][7] = new Point(228, 163);
		centers[4][7] = wallPoint;
		centers[5][7] = new Point(228, 233);
		centers[6][7] = new Point(228, 268);
		centers[7][7] = new Point(228, 303);
		centers[8][7] = new Point(228, 338);
		centers[9][7] = wallPoint;
		centers[10][7] = wallPoint;
		centers[11][7] = new Point(228, 443);
		centers[12][7] = new Point(228, 478);
		centers[13][7] = new Point(228, 513);
		
		//Column I
		centers[0][8] = new Point(258, 41);
		centers[1][8] = new Point(258, 76);
		centers[2][8] = new Point(258, 111);
		centers[3][8] = new Point(258, 146);
		centers[4][8] = new Point(258, 181);
		centers[5][8] = wallPoint;
		centers[6][8] = new Point(258, 251);
		centers[7][8] = new Point(258, 286);
		centers[8][8] = new Point(258, 321);
		centers[9][8] = new Point(258, 356);
		centers[10][8] = new Point(258, 391);
		centers[11][8] = wallPoint;
		centers[12][8] = new Point(258, 461);
		centers[13][8] = new Point(258, 496);
		
		//Column J
		centers[0][9] = new Point(288, 58);
		centers[1][9] = new Point(288, 93);
		centers[2][9] = new Point(288, 128);
		centers[3][9] = new Point(288, 163);
		centers[4][9] = new Point(288, 198);
		centers[5][9] = new Point(288, 233);
		centers[6][9] = wallPoint;
		centers[7][9] = new Point(288, 303);
		centers[8][9] = new Point(288, 338);
		centers[9][9] = new Point(288, 373);
		centers[10][9] = new Point(288, 408);
		centers[11][9] = wallPoint;
		centers[12][9] = new Point(288, 478);
		centers[13][9] = new Point(288, 513);
		
		//Column K
		centers[0][10] = new Point(318, 41);
		centers[1][10] = new Point(318, 76);
		centers[2][10] = new Point(318, 111);
		centers[3][10] = new Point(318, 146);
		centers[4][10] = new Point(318, 181);
		centers[5][10] = new Point(318, 216);
		centers[6][10] = wallPoint;
		centers[7][10] = new Point(318, 286);
		centers[8][10] = new Point(318, 321);
		centers[9][10] = new Point(318, 356);
		centers[10][10] = new Point(318, 391);
		centers[11][10] = new Point(318, 426);
		centers[12][10] = wallPoint;
		centers[13][10] = new Point(318, 496);
		
		//Column L
		centers[0][11] = new Point(348, 58);
		centers[1][11] = new Point(348, 93);
		centers[2][11] = new Point(348, 128);
		centers[3][11] = new Point(348, 163);
		centers[4][11] = new Point(348, 198);
		centers[5][11] = wallPoint;
		centers[6][11] = wallPoint;
		centers[7][11] = wallPoint;
		centers[8][11] = new Point(348, 338);
		centers[9][11] = new Point(348, 373);
		centers[10][11] = new Point(348, 408);
		centers[11][11] = new Point(348, 443);
		centers[12][11] = new Point(348, 478);
		centers[13][11] = new Point(348, 513);
		
		//Column M
		centers[0][12] = new Point(378, 41);
		centers[1][12] = new Point(378, 76);
		centers[2][12] = new Point(378, 111);
		centers[3][12] = new Point(378, 146);
		centers[4][12] = new Point(378, 181);
		centers[5][12] = new Point(378, 216);
		centers[6][12] = wallPoint;
		centers[7][12] = new Point(378, 286);
		centers[8][12] = new Point(378, 321);
		centers[9][12] = new Point(378, 356);
		centers[10][12] = new Point(378, 391);
		centers[11][12] = new Point(378, 426);
		centers[12][12] = new Point(378, 461);
		centers[13][12] = new Point(378, 496);
		
		//Column N
		centers[0][13] = new Point(408, 58);
		centers[1][13] = new Point(408, 93);
		centers[2][13] = new Point(408, 128);
		centers[3][13] = new Point(408, 163);
		centers[4][13] = new Point(408, 198);
		centers[5][13] = new Point(408, 233);
		centers[6][13] = wallPoint;
		centers[7][13] = new Point(408, 303);
		centers[8][13] = new Point(408, 338);
		centers[9][13] = new Point(408, 373);
		centers[10][13] = new Point(408, 408);
		centers[11][13] = new Point(408, 443);
		centers[12][13] = new Point(408, 478);
		centers[13][13] = new Point(408, 513);
		
		//Column O
		centers[0][14] = wallPoint;
		centers[1][14] = new Point(438, 76);
		centers[2][14] = wallPoint;
		centers[3][14] = wallPoint;
		centers[4][14] = new Point(438, 181);
		centers[5][14] = new Point(438, 216);
		centers[6][14] = new Point(438, 251);
		centers[7][14] = new Point(438, 286);
		centers[8][14] = new Point(438, 321);
		centers[9][14] = new Point(438, 356);
		centers[10][14] = new Point(438, 391);
		centers[11][14] = new Point(438, 426);
		centers[12][14] = new Point(438, 461);
		centers[13][14] = new Point(438, 496);
		
		//Column P
		centers[0][15] = new Point(468, 58);
		centers[1][15] = new Point(468, 93);
		centers[2][15] = new Point(468, 128);
		centers[3][15] = new Point(468, 163);
		centers[4][15] = new Point(468, 198);
		centers[5][15] = new Point(468, 233);
		centers[6][15] = new Point(468, 268);
		centers[7][15] = new Point(468, 303);
		centers[8][15] = new Point(468, 338);
		centers[9][15] = new Point(468, 373);
		centers[10][15] = new Point(468, 408);
		centers[11][15] = new Point(468, 443);
		centers[12][15] = new Point(468, 478);
		centers[13][15] = new Point(468, 513);
		
		//Column Q
		centers[0][16] = new Point(498, 41);
		centers[1][16] = new Point(498, 76);
		centers[2][16] = new Point(498, 111);
		centers[3][16] = new Point(498, 146);
		centers[4][16] = new Point(498, 181);
		centers[5][16] = new Point(498, 216);
		centers[6][16] = new Point(498, 251);
		centers[7][16] = new Point(498, 286);
		centers[8][16] = new Point(498, 321);
		centers[9][16] = new Point(498, 356);
		centers[10][16] = new Point(498, 391);
		centers[11][16] = new Point(498, 426);
		centers[12][16] = new Point(498, 461);
		centers[13][16] = new Point(498, 496);
		
		//Column R
		centers[0][17] = new Point(528, 58);
		centers[1][17] = new Point(528, 93);
		centers[2][17] = new Point(528, 128);
		centers[3][17] = new Point(528, 163);
		centers[4][17] = new Point(528, 198);
		centers[5][17] = new Point(528, 233);
		centers[6][17] = new Point(528, 268);
		centers[7][17] = new Point(528, 303);
		centers[8][17] = new Point(528, 338);
		centers[9][17] = wallPoint;
		centers[10][17] = wallPoint;
		centers[11][17] = new Point(528, 443);
		centers[12][17] = new Point(528, 478);
		centers[13][17] = wallPoint;
		
		//Column S
		centers[0][18] = wallPoint;
		centers[1][18] = new Point(558, 76);
		centers[2][18] = wallPoint;
		centers[3][18] = new Point(558, 146);
		centers[4][18] = new Point(558, 181);
		centers[5][18] = new Point(558, 216);
		centers[6][18] = new Point(558, 251);
		centers[7][18] = new Point(558, 286);
		centers[8][18] = new Point(558, 321);
		centers[9][18] = wallPoint;
		centers[10][18] = wallPoint;
		centers[11][18] = new Point(558, 426);
		centers[12][18] = new Point(558, 461);
		centers[13][18] = wallPoint;
		
		//Column T
		centers[0][19] = wallPoint;
		centers[1][19] = new Point(588, 93);
		centers[2][19] = wallPoint;
		centers[3][19] = wallPoint;
		centers[4][19] = new Point(588, 198);
		centers[5][19] = new Point(588, 233);
		centers[6][19] = new Point(588, 268);
		centers[7][19] = new Point(588, 303);
		centers[8][19] = new Point(588, 338);
		centers[9][19] = wallPoint;
		centers[10][19] = wallPoint;
		centers[11][19] = new Point(588, 443);
		centers[12][19] = new Point(588, 478);
		centers[13][19] = new Point(588, 513);
		
		//Column U
		centers[0][20] = new Point(618, 41);
		centers[1][20] = new Point(618, 76);
		centers[2][20] = new Point(618, 111);
		centers[3][20] = new Point(618, 146);
		centers[4][20] = new Point(618, 181);
		centers[5][20] = new Point(618, 216);
		centers[6][20] = new Point(618, 251);
		centers[7][20] = new Point(618, 286);
		centers[8][20] = new Point(618, 321);
		centers[9][20] = new Point(618, 356);
		centers[10][20] = new Point(618, 391);
		centers[11][20] = new Point(618, 426);
		centers[12][20] = new Point(618, 461);
		centers[13][20] = new Point(618, 496);
		
		//Column V
		centers[0][21] = new Point(648, 58);
		centers[1][21] = new Point(648, 93);
		centers[2][21] = new Point(648, 128);
		centers[3][21] = new Point(648, 163);
		centers[4][21] = new Point(648, 198);
		centers[5][21] = new Point(648, 233);
		centers[6][21] = wallPoint;
		centers[7][21] = new Point(648, 303);
		centers[8][21] = new Point(648, 338);
		centers[9][21] = new Point(648, 373);
		centers[10][21] = new Point(648, 408);
		centers[11][21] = new Point(648, 443);
		centers[12][21] = new Point(648, 478);
		centers[13][21] = new Point(648, 513);
		
		//Column W
		centers[0][22] = new Point(678, 41);
		centers[1][22] = new Point(678, 76);
		centers[2][22] = new Point(678, 111);
		centers[3][22] = new Point(678, 146);
		centers[4][22] = new Point(678, 181);
		centers[5][22] = new Point(678, 216);
		centers[6][22] = new Point(678, 251);
		centers[7][22] = new Point(678, 286);
		centers[8][22] = new Point(678, 321);
		centers[9][22] = new Point(678, 356);
		centers[10][22] = new Point(678, 391);
		centers[11][22] = new Point(678, 426);
		centers[12][22] = new Point(678, 461);
		centers[13][22] = new Point(678, 496);
		
		//finally, the end!		
		
	}
	
	public Coordinates getMinimumDistance(Point clickedPoint){
		
		int x = 0, y = 0;
		boolean flag = false;
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				if(centers[i][j] != wallPoint){
					System.out.println(centers[i][j].distance(clickedPoint));
					if(centers[i][j].distance(clickedPoint) <= DISTANCE){
						x = i + 1;
						y = j + 1;
						flag = true;
						break;
					}
				}
			}
			if(flag == true)
				break;
		}
		Coordinates coord = new Coordinates(y, x);  //da controllare
		return coord;
	}		
	
}
	

