package it.polimi.ingsw.dinapolidinardo.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.polimi.ingsw.dinapolidinardo.model.boxes.*;

/**
 * Specific Map Class that provides the implementation of the Galilei Map
 */
public class GalileiMap extends Map{
	
	/**
	 * Constructor, it initializes the matrix representation respecting the design
	 * of the GalileiMap 
	 * @throws IOException 
	 */
	public GalileiMap() throws IOException{
		this.setMap(new Box[14][23]);
		for (int i=0; i<14; i++){
			for (int j=0; j<23; j++){
				gameMap[i][j] = new Wall(j+1,i+1);
			}
		}
		
		BufferedReader br = new BufferedReader(new FileReader("externalresources\\Maps\\GalileiMap.txt"));
		String sCurrentLine;
		for (int j=0; j<23; j++){
			for (int i=0; i<14; i++){
					
				sCurrentLine = br.readLine();
				List<String> parts = Arrays.asList(sCurrentLine.split(";"));
				if ("w".equals(parts.get(0))){
					this.gameMap[i][j] = new Wall(Integer.parseInt(parts.get(1)), Integer.parseInt(parts.get(2)));
				}
				if ("d".equals(parts.get(0))){
					this.gameMap[i][j] = new DangerousBox(Integer.parseInt(parts.get(1)), Integer.parseInt(parts.get(2)));
				}
				if ("b".equals(parts.get(0))){
					this.gameMap[i][j] = new Box(Integer.parseInt(parts.get(1)), Integer.parseInt(parts.get(2)));
				}
				if ("l".equals(parts.get(0))){
					this.gameMap[i][j] = new LifeboatBox(Integer.parseInt(parts.get(1)), Integer.parseInt(parts.get(2)), Integer.parseInt(parts.get(3)));
				}
				if ("a".equals(parts.get(0))){
					this.gameMap[i][j] = new AlienBox(Integer.parseInt(parts.get(1)), Integer.parseInt(parts.get(2)));
					alienStartBox = gameMap[i][j];
				}
				if ("h".equals(parts.get(0))){
					this.gameMap[i][j] = new HumanBox(Integer.parseInt(parts.get(1)), Integer.parseInt(parts.get(2)));
					humanStartBox = gameMap[i][j];
				}
			}
		}
		br.close();
 		
	}
	
		
	/**
	 * Provides all the matrix neighbors with a particular attention for side and corner boxes,
	 * in order to avoid out of bound indexes
	 * 
	 * @param center the Box for which we need the neighbors 
	 * @return the list of the selected Box neighbors in the matrix representation
	 */
	@Override
	public List<Box> givemeAroundBoxes (Box center){
		List<Box> aroundBoxes = new ArrayList<Box>();
		//corners box, to be treated differently in order to avoid IndexOutofBorder
		if (center.getCoordX()==1 && center.getCoordY()==1){
			aroundBoxes.add(gameMap[1][0]);
			aroundBoxes.add(gameMap[0][1]);
			aroundBoxes.add(gameMap[1][1]);
			return aroundBoxes;
		}
		if (center.getCoordX()==1 && center.getCoordY()==14){
			aroundBoxes.add(gameMap[12][0]);
			aroundBoxes.add(gameMap[12][1]);
			aroundBoxes.add(gameMap[13][1]);
			return aroundBoxes;
		}
		if (center.getCoordX()==23 && center.getCoordY()==1){
			aroundBoxes.add(gameMap[0][21]);
			aroundBoxes.add(gameMap[1][21]);
			aroundBoxes.add(gameMap[1][22]);
			return aroundBoxes;
		}
		if (center.getCoordX()==23 && center.getCoordY()==14){
			aroundBoxes.add(gameMap[12][21]);
			aroundBoxes.add(gameMap[13][21]);
			aroundBoxes.add(gameMap[12][22]);
			return aroundBoxes;
		}
		//side boxes
		if (center.getCoordX()==1){
			for (int i=center.getCoordX(); i<=center.getCoordX()+1; i++)
				for (int j=center.getCoordY()-1; j<=center.getCoordY()+1; j++)
					aroundBoxes.add(gameMap[j-1][i-1]);
			return aroundBoxes;
		}
		if (center.getCoordY()==14){
			for (int i=center.getCoordX()-1; i<=center.getCoordX()+1; i++)
				for (int j=center.getCoordY()-1; j<=center.getCoordY(); j++)
					aroundBoxes.add(gameMap[j-1][i-1]);
			return aroundBoxes;
		}
		if (center.getCoordY()==1){
			for (int i=center.getCoordX()-1; i<=center.getCoordX()+1; i++)
				for (int j=center.getCoordY(); j<=center.getCoordY()+1; j++)
					aroundBoxes.add(gameMap[j-1][i-1]);
			return aroundBoxes;
			
		}
		if (center.getCoordX()==23){
			for (int i=center.getCoordX()-1; i<=center.getCoordX(); i++)
				for (int j=center.getCoordY()-1; j<=center.getCoordY()+1; j++)
					aroundBoxes.add(gameMap[j-1][i-1]);
			return aroundBoxes;		
		}
		
		for (int i=center.getCoordX()-1; i<=center.getCoordX()+1; i++)
			for (int j=center.getCoordY()-1; j<=center.getCoordY()+1; j++)
				aroundBoxes.add(gameMap[j-1][i-1]);
		return aroundBoxes;		
		
	}
	
	
	
}	
	
	