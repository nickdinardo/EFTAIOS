package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.util.List;

import javax.swing.JToolTip;

public class ToolTipCard {

	public JToolTip[] setToolTip(List<String> objects){
		switch(objects.size()){
		case 1:
			if(objects.get(0) == ""){
				JToolTip[] tips = new JToolTip[0];
				return tips;
			}
			else{
				JToolTip[] tips = new JToolTip[1];
				tips[0] = specifiedTip(objects.get(0));
				return tips;
			}
		case 2:
			
			JToolTip[] tips = new JToolTip[2];
			tips[0] = specifiedTip(objects.get(0));
			tips[1] = specifiedTip(objects.get(1));
			return tips;
			
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
	
	public JToolTip specifiedTip(String object){
		String attackCard = " This card allows you to attack, using the same rules as the Aliens.";
		String defenseCard = " Play this card immediately when an Alien attacks you. You are not affected by the attack.";
		String adrenalineCard = "This card allows you to move two Sectors this turn.";
		String sedativesCard = ": If you play this card you do not draw a Dangerous Sector Card this turn, even if you move into a Dangerous Sector.";
		String teleportCard = " This card allows you to move directly to the Human Sector from any part of the ship. This is in addition to your normal movement which can happen before or after you use the item.";
		String lightsCard = " When you play this card, name any Sector. Any players (including you) that are in the named Sector or any of the six adjacent Sectors must immediately announce their exact location Coordinates. This card affects both Humans and Aliens.";
		if(object.equals("Attack Card")){
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(attackCard);
			return tooltip;
		}
		if(object.equals("Defense Card")){
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(defenseCard);
			return tooltip;
		}
		if(object.equals("Adrenaline Card")){
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(adrenalineCard);
			return tooltip;
		}
		if(object.equals("Sedatives Card")){
			JToolTip tooltip = new JToolTip();
			tooltip.setTipText(sedativesCard);
			return tooltip;
		}
		if(object.equals("Teleport Card")){
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
