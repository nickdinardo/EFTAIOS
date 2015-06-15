package it.polimi.ingsw.dinapolidinardo.view;

import java.util.List;

import javax.swing.JToolTip;



public class ToolTipCard {

	/**
	 * Sets tool tips depending on the number of item cards owned
	 * 
	 * @param objects list with the name of all the cards owned
	 * @return an array of the correct tool tips
	 */
	public JToolTip[] setToolTip(List<String> objects){
		switch(objects.size()){
		case 1:
			if(objects.get(0) == "")
				return new JToolTip[0];
			JToolTip[] tips = new JToolTip[1];
			tips[0] = specifiedTip(objects.get(0));
			return tips;
		case 2:			
			JToolTip[] tipz = new JToolTip[2];
			tipz[0] = specifiedTip(objects.get(0));
			tipz[1] = specifiedTip(objects.get(1));
			return tipz;
		case 3:
			JToolTip[] tip = new JToolTip[3];
			tip[0] = specifiedTip(objects.get(0));
			tip[1] = specifiedTip(objects.get(1));
			tip[2] = specifiedTip(objects.get(2));
			return tip;
		default:
			JToolTip[] tipDefault = new JToolTip[0];
			return tipDefault;
		}
	}
	
	/**
	 * Initializes a text message that will be displayed when mouse stops 
	 * on a card image, depending on the card selected
	 * 
	 * @param object the name of the card where the mouse stops
	 * @return the tool tip text
	 */
	public JToolTip specifiedTip(String object){
		String attackCard = "This card allows you to attack, using the same rules as the Aliens.";
		String defenseCard = "This card will activate immediately when an Alien attacks you.\n You won't be affected by the attack.";
		String adrenalineCard = "This card allows you to move two Sectors this turn.";
		String sedativesCard = "If you play this card you do not draw a Dangerous Sector Card this turn, even if you move into a Dangerous Sector.";
		String teleportCard = "This card allows you to move directly to the Human Sector from any part of the ship.\n This is in addition to your normal movement which can happen before or after you use the item.";
		String lightsCard = "When you play this card, name any Sector.\n The position of any players (including you) that are in the named Sector or any of the six adjacent Sectors will be revealed\n. This card affects both Humans and Aliens.";
		if("AttackCard".equals(object)){
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(attackCard);
			return tooltip;
		}
		if("DefenseCard".equals(object)){
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(defenseCard);
			return tooltip;
		}
		if("AdrenalineCard".equals(object)){
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(adrenalineCard);
			return tooltip;
		}
		if("SedativesCard".equals(object)){
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(sedativesCard);
			return tooltip;
		}
		if("TeleportCard".equals(object)){
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(teleportCard);
			return tooltip;
		}
		else{
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(lightsCard);
			return tooltip;
		}
	}
}
