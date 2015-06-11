package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.Color;

import javax.swing.JFrame;


public class YouAreHumanFrame extends YouAreFrame{
	
	
	
    
    public YouAreHumanFrame (JFrame descriptionFrame){
    	frame = descriptionFrame;
    	Color color = new Color(120, 136, 182);
    	String being = "HUMAN";
    	String image = "externalresources\\Human.jpg";
    	String text = "You are one of the survivors on the spaceship that\nresisted to the spreading, horrible disease \nthat could have infected anyone of your team mates. \n"
    			+ "Horrendous aliens that once were your friends \nare lurking in the dark to kill you and eat you, \nand they could be anyone and anywhere.\n"
    			+ "Your objective is reaching one of the avaiable\nlifeboat ships avoidingto attract the attentions\nof the blood-thirsty monsters that surround you.\n"
    			+ "The mission depends on you. \nYour life too. \nGood luck.";
    	
    	initComponents(image, color, text, being);
    }
    		
    		
    
}
