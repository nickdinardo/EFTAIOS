package it.polimi.ingsw.dinapolidinardo.view;

import java.awt.Color;

import javax.swing.JFrame;

public class YouAreAlienFrame extends YouAreFrame{
			
	    /**
	     * Constructor, sets the parameter that make the "alien theme" in the superclass components
	     */
	    public YouAreAlienFrame (JFrame descriptionFrame){
	    	frame = descriptionFrame;
	    	Color color = new Color(136, 66, 130);
	    	String being = "ALIEN";
	    	String image = "externalresources\\alieno2.jpg";
	    	String text = "The horrible alien disease that is \ninfecting the spaceships has caught you time ago.\n"
	    			+"Your body is now a brutal and deformed machine\neager to kill any human alive carrying fresh meat.\n"
	    			+"Your objective is tracking down\nthe poor humans that are trying\nto reach the lifeboat ships\nand kill'em before they do it.\n"
	    			+"All the miserable humans.\nEnjoy your meal.";
	    	
	    	initComponents(image, color, text, being);
	    }
	    		
	    		
}
