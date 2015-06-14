package it.polimi.ingsw.dinapolidinardo.model;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Box;


/**
 * Matrix abstract representation of an hexagonal map, 
 * with specified starting position of the different players.
 * <p>
 * Provides also methods to select boxes on the base of the map configuration.
 */
public abstract class Map {
	protected Box[][] gameMap;
	protected Box humanStartBox;
	protected Box alienStartBox;
	
	
	/**
	 * 
	 * @param center the Box for which we need the neighbors 
	 * @return the list of the selected Box neighbors in the matrix representation
	 */
	public abstract List<Box> givemeAroundBoxes (Box center);

	
	/**
	 * Given a start position and a move range, returns all the reachable boxes inside the range,
	 * discarding all the unfeasable paths
	 * 
	 * @param position the starting position of the movement
	 * @param range the range in steps (every step a box) 
	 * @return all the reachable boxes
	 */
	public List<Box> reachableBoxes (Box position, int range){
		List<Box> reachablesWithPrevStep = new ArrayList<Box>();
		List<Box> reachables = new ArrayList<Box>();
		List<Box> checked = new ArrayList<Box>();
		
		for (int i=0; i<range; i++){
			
			//if reachbles is empty (starting step) we have no need to do the two arrays procedure
			if (!reachables.isEmpty()){
				
				//fill the "previous step" list with the actual before update the actual one
				for (Box b : reachables){
					reachablesWithPrevStep.add(b);
				}
				for (Box b : reachablesWithPrevStep){
					
					//condition that checks if a box has already been checked to optimize the 
					//algorithm avoiding to check different times the same boxes
					if (!checked.contains(b)){
						checked.add(b);
						List<Box> aroundBoxes = givemeAroundBoxes(b);
						List<Box> validBoxes = checkOneStepBoxes(aroundBoxes, b);
						
						//add all the reachable boxes with a step in the overall reachable list
						for (Box valid : validBoxes)
							reachables.add(valid);
					}
				}
			}
			//starting step
			else{
				List<Box> aroundBoxes = givemeAroundBoxes(position);
				List<Box> validBoxes = checkOneStepBoxes(aroundBoxes, position);
				for (Box valid : validBoxes)
					reachables.add(valid);
			}
				
		}
		return reachables;
	}
		
			
	/**
	 * Given all the matrix neighbors of a box, return the ones of them reachable with a single step in the map
	 * 	
	 * @param boxesToCheck all the matrix neighbors (since the map is hexagonal, being a matrix neighbor doesn't grant being neighbor on the map)
	 * @param position starting Box
	 * @return all the one-step-reachable boxes
	 */
	public List<Box> checkOneStepBoxes (List<Box> boxesToCheck, Box position){
		List<Box> validBoxes = new ArrayList<Box>();
		for (Box box: boxesToCheck){
			if (isValidSingleMovement(box, position))
					validBoxes.add(box);
			}
		return validBoxes;
	}
	
	
	/**
	 * Check if a box is reachable with a single (range = 1) movement
 	 * 
	 * @param destination the Box to reach
	 * @param position the starting Box
	 * @return true if destination can be reached, false otherwise
	 */
	public boolean isValidSingleMovement(Box destination, Box position){
		
		//if the destination is a box that can't be crossed returns false immediately
		if (!destination.isCanBeCrossedType()) 
			return false;
		//distinguish between "high" and "low" boxes in a row of hexagons 
		//needed given the matrix representation of the map
		if (position.getCoordX()%2==0){//low ones
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 1)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == 0)
					return true;
			if (destination.getCoordX()-position.getCoordX() == 0)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == -1)
					return true;
		}
		else{//high ones
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 1)
				if (destination.getCoordY()-position.getCoordY() == -1 || destination.getCoordY()-position.getCoordY() == 0)
					return true;
			if (destination.getCoordX()-position.getCoordX() == 0)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == -1)
					return true;
		}
		return false;
	}
	
	
	
	//getters and setters
	
	public Box[][] getMap() {
		return gameMap;
	}

	public void setMap(Box[][] map) {
		this.gameMap = map;
	}
	
	public Box getHumanStartBox() {
		return humanStartBox;
	}

	public Box getAlienStartBox() {
		return alienStartBox;
	}

}
