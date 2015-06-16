package it.polimi.ingsw.dinapolidinardo.view;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


/**
 *  Return to the caller a single instance of swing or text view,
 *  deploying the "factory" and "singleton" patterns
 */
public class ViewFactory {
	private static final ViewFactory FACTORY = new ViewFactory();
	private static PrintStream out = System.out;
	private static Scanner in = null;
	
	
	/**
	 *  The private constructor grant a single instantiation:
	 *  "singleton" pattern
	 */	
	private ViewFactory() {
		
	}

	
	/**
	 *  @return the only instance of View Factory
	 */
	public static ViewFactory getViewFactory() {
	 	 return FACTORY;
	}
	
	
	/**
	 * allows user selection between CLI and swing GUI, and returns to the caller the selected type of view
	 * 
	 * @return an object either TextView or SwingView
	 * @throws IOException if couldn't load the GUI pixels text file
	 */
	public static View getView() throws IOException{
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


