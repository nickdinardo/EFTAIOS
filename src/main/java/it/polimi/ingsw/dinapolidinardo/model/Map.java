package it.polimi.ingsw.dinapolidinardo.model;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Box;

public abstract class Map {
	protected Box[][] gameMap;
	protected Box humanStartBox;
	protected Box alienStartBox;
	
		
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

	
	public abstract List<Box> givemeAroundBoxes (Box center);

		
	public List<Box> reachableBoxes (Box position, int range){
		List<Box> reachablesWithPrevStep = new ArrayList<Box>();
		List<Box> reachables = new ArrayList<Box>();
		List<Box> checked = new ArrayList<Box>();
		
		for (int i=0; i<range; i++){
			if (!reachables.isEmpty()){
				for (Box b : reachables){
					reachablesWithPrevStep.add(b);
				}
				for (Box b : reachablesWithPrevStep){
					if (!checked.contains(b)){
						checked.add(b);
						List<Box> aroundBoxes = givemeAroundBoxes(b);
						List<Box> validBoxes = checkOneStepBoxes(aroundBoxes, b);
						for (Box valid : validBoxes)
							reachables.add(valid);
					}
				}
			}
			else{
				List<Box> aroundBoxes = givemeAroundBoxes(position);
				List<Box> validBoxes = checkOneStepBoxes(aroundBoxes, position);
				for (Box valid : validBoxes)
					reachables.add(valid);
			}
				
		}
		return reachables;
	}
		
			
		
	public List<Box> checkOneStepBoxes (List<Box> boxesToCheck, Box position){
		List<Box> validBoxes = new ArrayList<Box>();
		for (Box box: boxesToCheck){
			if (isValidSingleMovement(box, position))
					validBoxes.add(box);
			}
		return validBoxes;
	}
	
	
	
	public boolean isValidSingleMovement(Box destination, Box position){
		
		if (!destination.isCanBeCrossedType()) 
			return false;
		if (position.getCoordX()%2==0){//BASSA
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 1)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == 0)
					return true;
			if (destination.getCoordX()-position.getCoordX() == 0)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == -1)
					return true;
		}
		else{//ALTA
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 1)
				if (destination.getCoordY()-position.getCoordY() == -1 || destination.getCoordY()-position.getCoordY() == 0)
					return true;
			if (destination.getCoordX()-position.getCoordX() == 0)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == -1)
					return true;
		}
		return false;
	}
	
	
}
