package it.polimi.ingsw.dinapolidinardo.view;

import java.io.PrintStream;
import java.util.Scanner;




public class ViewFactory {
	private static final ViewFactory FACTORY = new ViewFactory();
	private static PrintStream out = System.out;
	private static Scanner in = null;
	
	//private constructor, singleton
	private ViewFactory() {
		
	}

	public static ViewFactory getViewFactory() {
	 	 return FACTORY;
	}
	
	public static View getView(){
		String userAnswer;
		out.println("To select the textual user-interface write TEXT, to select the graphical user-interface write any other key");
		in = new Scanner(System.in);
		userAnswer = in.nextLine();
		
		if("TEXT".equalsIgnoreCase(userAnswer)){
			return new TextView();
		}else{		
			return new SwingView();
		}
	}
}

