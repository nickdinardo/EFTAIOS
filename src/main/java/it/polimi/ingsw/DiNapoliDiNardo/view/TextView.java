package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.io.PrintStream;
import java.util.Scanner;


public class TextView extends View{
	Scanner in = new Scanner(System.in);
	private PrintStream out = System.out;
	boolean isFirstTurn = true;
	private static final String INVLETTER = "Please select one of the options writing the corresponding letter and nothing more";
	private static final String SHORTLINE = "--------";
	
	
	@Override
	public String askName (boolean reask){
		if(!reask){
			out.println("Connected to the server game. What's your name?");
			String name = in.nextLine();
			out.println("Please wait for other players initialization, game is loading...");
			return name;
		}
		else{
			out.println("The name you chose is already taken for this game, or is empty. Please insert another name");
			return in.nextLine();
		}
	}
	
	@Override
	public void showActualSituation (String name, String position, String objects, String turn){
		String printposition = position;
		if (position.length() == 2)
			printposition = position.substring(0, 1) + "0" + position.substring(1, position.length());
		out.println("________________________________________________________________________________________________________________________");
		out.println("Turn number : "+turn);
		out.println(name+ " you are now in the "+printposition+" position.");
		if (!"no".equals(objects)){
			out.println("In your item deck you have the following cards: "+objects);
		}
		out.println(SHORTLINE);
	}
	
		
	@Override
	public void showBeingAlien (String name){
		if(isFirstTurn){
			printGalileiMap();
			isFirstTurn = false;
		}
		out.println(name+", the horrible alien disease that is infecting the spaceships has caught you time ago. ");
		out.println("Your body is now a brutal and deformed machine eager to kill any human that is still alive carrying fresh meat.");
		out.println("Your objective is tracking down the poor humans that are trying to reach the lifeboat ships and kill'em before they do it.");
		out.println("All the miserable humans. Enjoy your meal.");
	}
	
	@Override
	public void showBeingHuman (String name){
		if(isFirstTurn){
			printGalileiMap();
			isFirstTurn = false;
		}
		out.println(name+", you are one of the survivors on the spaceship that resisted to the spreading, horrible disease that could have infected anyone of your team mates.");
		out.println("Horrendous aliens that once were your friends are lurking in the dark to kill you and eat you, and they could be anyone and anywhere. ");
		out.println("Your objective is reaching one of the avaiable lifeboat ships avoiding to attract the attentions of the blood-thirsty monsters that surround you.");
		out.println("The mission depends on you. Your life too. Good luck.");
	}
	
	@Override
	public Coordinates askMovement(boolean reask){
		if(reask){
			out.println("The movement you selected is not valid. Please select another box.");
		}
		out.println("Where do you want to move? Insert the coordinates of the box you want to move in");
		
		return solveCoordInput();
		
	}
	
	@Override	
	public int askItemUse(String objects, boolean fromDiscardCall){
		String[] items = objects.split(";");
		char risp;
		if(!fromDiscardCall){
			out.println("Do you want to use an Item Card?");
			out.println("Y: yes    N: no");
			String input = in.nextLine();
			if (input.length()>0)
				risp = input.charAt(0);
			else risp = 'w';
		}
		else 
			risp = 'y';
		if (risp == 'Y' || risp == 'y')
			return askWhichCard (items);
		else if (risp != 'N' && risp != 'n'){
			out.println("Please insert 'Y' or 'N'");
			return askItemUse(objects, false);
		}
		return 8;	
	}
	
	@Override
	public int askAlienItemDiscard(String objects){
		String[] items = objects.split(";");
		out.println("You drew an item card but your three card slots are full. Do you want to discard a card you have to free one slot for the new card?");
		out.println("D: discard an item	 N: no, I'll keep my actual items");
		String input = in.nextLine();
		char risp;
		if (input.length()>0)
			risp = input.charAt(0);
		else risp = 'w';
		boolean validanswer = false;
		do{
			if (risp == 'D' || risp == 'd'){
				validanswer = true;
				return askWhichCard(items)+3;				
			}
			if (risp == 'N' || risp == 'n'){
				validanswer = true;
				return 8;
			}	
			out.println();
		}while(!validanswer);
		return 18;
	}
	
	@Override
	public int askHumanItemDiscard(String objects){
		String[] items = objects.split(";");
		out.println("You drew an item card but your three card slots are full. Do you want to discard or use a card you have to free one slot for the new card?");
		out.println("U: use an item    D: discard an item	N: no, I'll keep my actual items");
		String input = in.nextLine();
		char risp;
		if (input.length()>0)
			risp = input.charAt(0);
		else risp = 'w';
		boolean validanswer = false;
		do{
			if (risp == 'D' || risp == 'd'){
				validanswer = true;
				return askWhichCard(items)+3;				
			}
			if (risp == 'U' || risp == 'u'){
				validanswer = true;
				return 9;
			}
			if (risp == 'N' || risp == 'n'){
				validanswer = true;
				return 8;
			}	
			out.println(INVLETTER);
		}while(!validanswer);
		return 19;
	}
	
	
	
	public int askWhichCard (String[] items){
		int answer;
		do{
			out.println("Select the number of the item you want to select:");
			for (int i=0; i<items.length; i++){
				int j = i+1;
				out.println(j+"- "+items[i]);
			}
			String inputN = in.nextLine();
			try{
				answer = Integer.parseInt(inputN);
			}catch (NumberFormatException e){
			answer=0;
			}
			if (answer <=0 || answer>items.length)
				out.println("Please write the number of the object you want to discard and nothing more");
		}while (answer <=0 || answer>items.length);
		return answer;
	}
	
	
	
	
	@Override
	public String askForAttack(){
		out.println("Filthy alien, do you want to attack this position?");
		out.println("Y: yes    N: no");
		boolean validanswer = false;
		do{
			
			String ans = in.nextLine();
			if (ans.length()>0)
				ans = ans.substring(0, 1);
			if ("Y".equalsIgnoreCase(ans))
				return ans;
			if ("N".equalsIgnoreCase(ans))
				return ans;	
			out.println(INVLETTER);
		}while(!validanswer);
		
		return null;
	}
	
	@Override
	public Coordinates askForLights(boolean reask){
		if(reask){
			out.println("The sector you selected to enlight is not valid. Please select another box.");
		}
		else{
			out.println("Which sector of the map do you want to enlight? Insert the coordinates of the box you want to illuminate");
		}
		return solveCoordInput();
		
	}
	
	@Override
	public String askForNoise(){
		out.println("In which sector of the map do you want to declare there's noise?");
		Coordinates coordinates = solveCoordInput();
		String noise = ""+(char)(coordinates.getCoordX()+64);
		String number = ""+ coordinates.getCoordY();
		if (number.length() == 1)
			number = "0"+ coordinates.getCoordY();
		noise += number;
		return noise;
	}
	
	
	@Override
	public void notifyEscape(boolean escaped, String name, String shipnumber) {
		out.println(name+" has REACHED THE LIFEBOAT SHIP "+shipnumber+" .....");
		if(escaped){
			out.println("The lifeboat ship "+shipnumber+" is incredibly still working!!!");
			out.println(name+" is now safe and far from here. The lifeboat ship "+shipnumber+" won't be accessible anymore");
		}
		else{
			out.println("The lifeboat ship "+shipnumber+" does not answer to commands! "+name+" still couldn't escape!");
			out.println("You'd better remember lifeboat "+shipnumber+" is not working...");
		}
				
	}
	
	@Override
	public void showFinalResults(boolean iWon, String name, String humanlosers,	String humanwinners, String alienwinners, String alienlosers) {
		out.println(SHORTLINE);
		if (iWon)
			out.println(name+ " YOU WON!!!");
		else
			out.println(name+ " YOU LOST!!!");
		out.println(SHORTLINE);
		if (!humanwinners.isEmpty())
		out.println("The HUMANS that managed to escape and WON the game are: "+humanwinners);
		if (!humanlosers.isEmpty())
		out.println("The HUMANS that has been killed by the aliens and LOST the game are: "+humanlosers);
		if (!alienwinners.isEmpty())
			out.println("The aliens managed to avoid the escape of all the humans, so the ALIENS that WON are: "+alienwinners);
		else 
			out.println("The aliens have remained alone on the ship while the last human reached the lifeboat ship, so the ALIENS that LOST are: "+alienlosers);
		out.println(SHORTLINE);
		out.println("THE END");
		out.println(SHORTLINE);
	}
	
	@Override
	public void print (String message){
		out.println(message);
	}
	
	
	public void printGalileiMap(){
		String textmap = "                                                                                                                           \n";
		textmap += "   _____________________________________________________________________________________________________________________\n";
		textmap += "    /   \\     /   \\     /   \\     /   \\     /   \\     /   \\     /   \\     /   \\     /   \\     /   \\     /   \\     /   \\ \n";
		textmap += "   /     \\___/ C01 \\___/     \\___/ G01 \\___/ I01 \\___/ K01 \\___/ M01 \\___/     \\___/ Q01 \\___/     \\___/ U01 \\___/     \\ \n";
		textmap += "   \\     /   \\     /         /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\     /   \\     /   \\         \\     /   \\     /\n";
		textmap += "    \\___/ B01 \\___/      ___/ F01 \\___/ H01 \\___/ J01 \\___/ L01 \\___/ N01 \\___/ P01 \\___/ R01 \\___      \\___/ V01 \\___/ \n";
		textmap += "    /   \\  D  /   \\     /   \\     /   \\     /   \\     /   \\  D  /   \\  D  /   \\     /   \\     /   \\     /   \\     /   \\ \n";
		textmap += "   / A02 \\___/ C02 \\___/ E02 \\___/ G02 \\___/ I02 \\___/ K02 \\___/ M02 \\___/ O02 \\___/ Q02 \\___/ S02 \\___/ U02 \\___/ W02 \\\n";
		textmap += "   \\  D  /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\     /   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /\n";
		textmap += "    \\___/  1  \\___/ D02 \\___/ F02 \\___/ H02 \\___/ J02 \\___/ L02 \\___/ N02 \\___/ P02 \\___/ R02 \\___/ T02 \\___/  2  \\___/ \n";
		textmap += "    /   \\  L  /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  L  /   \\ \n";
		textmap += "   / A03 \\___/ C03 \\___/ E03 \\___/ G03 \\___/ I03 \\___/ K03 \\___/ M03 \\___/     \\___/ Q03 \\___/     \\___/ U03 \\___/ W03 \\\n";
		textmap += "   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\         \\  D  /   \\     /\n";
		textmap += "    \\___/ B03 \\___/ D03 \\___/ F03 \\___/ H03 \\___/ J03 \\___/ L03 \\___/ N03 \\   / P03 \\___/ R03 \\___      \\___/ V03 \\___/ \n";
		textmap += "    /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\     /   \\     /   \\  D  /   \\     /   \\  D  /   \\ \n";
		textmap += "   / A04 \\___/ C04 \\___/ E04 \\___/ G04 \\___/ I04 \\___/ K04 \\___/ M04 \\___/     \\___/ Q04 \\___/ S04 \\   / U04 \\___/ W04 \\\n";
		textmap += "   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\     /   \\  D  /   \\  D  /   \\     /\n";
		textmap += "    \\___/ B04 \\___/     \\___/ F04 \\___/ H04 \\___/ J04 \\___/ L04 \\___/ N04 \\___/ P04 \\___/ R04 \\___/     \\___/ V04 \\___/ \n";
		textmap += "    /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\     /   \\     /   \\     /   \\  D  /   \\ \n";
		textmap += "   / A05 \\___/ C05 \\___/ E05 \\___/ G05 \\___/ I05 \\___/ K05 \\___/ M05 \\___/ O05 \\___/ Q05 \\___/ S05 \\___/ U05 \\___/ W05 \\\n";
		textmap += "   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\     /   \\     /   \\  D  /   \\  D  /   \\     /   \\     /\n";
		textmap += "    \\___/ B05 \\___/ D05 \\___/ F05 \\___/     \\___/ J05 \\___/ L05 \\___/ N05 \\___/ P05 \\___/ R05 \\___/ T05 \\___/ V05 \\___/ \n";
		textmap += "    /   \\     /   \\  D  /   \\  D  /   \\         \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\ \n";
		textmap += "   / A06 \\___/ C06 \\___/ E06 \\___/ G06 \\___      \\___/ K06 \\___/ M06 \\___/ O06 \\___/ Q06 \\___/ S06 \\___/ U06 \\___/ W06 \\\n";
		textmap += "   \\     /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\     /\n";
		textmap += "    \\___/ B06 \\___/     \\___/ F06 \\___/ H06 \\___/ J06 \\___/  A  \\___/ N06 \\___/ P06 \\___/ R06 \\___/ T06 \\___/ V06 \\___/ \n";
		textmap += "    /   \\  D  /   \\         \\  D  /   \\  D  /   \\  D  /   \\ Box /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\ \n";
		textmap += "   /     \\___/ C07 \\         \\___/ G07 \\___/ I07 \\___/     \\___/     \\___/ O07 \\___/ Q07 \\___/ S07 \\___/ U07 \\___/     \\\n";
		textmap += "   \\         \\  D  /         /   \\     /   \\  D  /                       \\  D  /   \\  D  /   \\  D  /   \\  D  /         /\n";
		textmap += "    \\         \\___/      ___/ F07 \\___/ H07 \\___/      ___       ___      \\___/ P07 \\___/ R07 \\___/ T07 \\___/         / \n";
		textmap += "    /         /   \\     /   \\  D  /   \\     /   \\     /   \\     /   \\     /   \\  D  /   \\     /   \\     /   \\         \\ \n";
		textmap += "   /      ___/ C08 \\___/ E08 \\___/ G08 \\___/ I08 \\___/ K08 \\___/ M08 \\___/ O08 \\___/ Q08 \\___/ S08 \\___/ U08 \\___      \\\n";
		textmap += "   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\     /\n";
		textmap += "    \\___/ B08 \\___/ D08 \\___/ F08 \\___/ H08 \\___/ J08 \\___/  H  \\___/ N08 \\___/ P08 \\___/ R08 \\___/ T08 \\___/ V08 \\___/ \n";
	    textmap += "    /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\ Box /   \\  D  /   \\  D  /   \\     /   \\     /   \\     /   \\ \n";
		textmap += "   / A09 \\___/ C09 \\___/ E09 \\___/ G09 \\___/ I09 \\___/ K09 \\___/ M09 \\___/ O09 \\___/ Q09 \\___/ S09 \\___/ U09 \\___/ W09 \\\n";
		textmap += "   \\     /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\     /   \\     /   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /\n";
		textmap += "    \\___/ B09 \\___/ D09 \\___/ F09 \\___/ H09 \\___/ J09 \\___/ L09 \\___/ N09 \\___/ P09 \\___/ R09 \\___/     \\___/ V09 \\___/ \n";
		textmap += "    /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\  D  /         /   \\  D  /   \\ \n";
		textmap += "   / A10 \\___/ C10 \\___/ E10 \\___/ G10 \\___/ I10 \\___/ K10 \\___/ M10 \\___/ O10 \\___/ Q10 \\___/         / U10 \\___/ W10 \\\n";
		textmap += "   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /             \\  D  /   \\     /\n";
		textmap += "    \\___/ B10 \\___/ D10 \\___/ F10 \\___/     \\___/ J10 \\___/ L10 \\___/ N10 \\___/ P10 \\___/               \\___/ V10 \\___/ \n";
		textmap += "    /   \\     /   \\     /   \\     /   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\               /   \\  D  /   \\ \n";
		textmap += "   / A11 \\___/ C11 \\___/ E11 \\___/ G11 \\   / I11 \\___/ K11 \\___/ M11 \\___/ O11 \\___/ Q11 \\           __/ U11 \\___/ W11 \\\n";
		textmap += "   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\     /   \\  D  /   \\     /         /   \\  D  /   \\     /\n";
		textmap += "    \\___/ B11 \\___/ D11 \\___/ F11 \\___/     \\___/ J11 \\___/ L11 \\___/ N11 \\___/ P11 \\___/      ___/ T11 \\___/ V11 \\___/ \n";
		textmap += "    /   \\  D  /   \\  D  /   \\  D  /   \\         \\  D  /   \\     /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\ \n";
		textmap += "   / A12 \\___/ C12 \\___/ E12 \\___/ G12 \\___      \\___/ K12 \\___/ M12 \\___/ O12 \\___/ Q12 \\___/ S12 \\___/ U12 \\___/ W12 \\\n";
		textmap += "   \\     /   \\  D  /   \\     /   \\     /   \\         \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\     /\n";
		textmap += "    \\___/ B12 \\___/ D12 \\___/     \\___/ H12 \\___      \\___/ L12 \\___/ N12 \\___/ P12 \\___/ R12 \\___/ T12 \\___/ V12 \\___/ \n";
		textmap += "    /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\         \\  D  /   \\  D  /   \\     /   \\     /   \\  D  /   \\  D  /   \\ \n";
		textmap += "   / A13 \\___/ C13 \\___/ E13 \\   / G13 \\___/ I13 \\___      \\___/ M13 \\___/ O13 \\___/ Q13 \\___/ S13 \\___/ U13 \\___/ W13 \\\n";
		textmap += "   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\     /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /\n";
		textmap += "    \\___/  4  \\___/ D13 \\___/     \\___/ H13 \\___/ J13 \\___/ L13 \\___/ N13 \\___/ P13 \\___/ R13 \\___/ T13 \\___/  3  \\___/ \n";
		textmap += "    /   \\  L  /   \\  D  /         /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  D  /   \\  L  /   \\ \n";
		textmap += "   / A14 \\___/ C14 \\___/         / G14 \\___/ I14 \\___/ K14 \\___/ M14 \\___/ O14 \\___/ Q14 \\___/     \\___/ U14 \\___/ W14 \\\n";
		textmap += "   \\  D  /   \\     /   \\         \\     /   \\     /   \\  D  /   \\  D  /   \\     /   \\     /         /   \\  D  /   \\  D  /\n";
		textmap += "    \\___/ B14 \\___/ D14 \\___      \\___/ H14 \\___/ J14 \\___/ L14 \\___/ N14 \\___/ P14 \\___/      ___/ T14 \\___/ V14 \\___/ \n";
		textmap += "        \\  D  /   \\     /   \\     /   \\     /   \\     /   \\     /   \\     /   \\  D  /   \\     /   \\     /   \\  D  /      \n";
		textmap += "         \\___/     \\___/     \\___/     \\___/     \\___/     \\___/     \\___/     \\___/     \\___/     \\___/     \\___/       \n";
		textmap += "   _____________________________________________________________________________________________________________________\n";
		textmap += "                                                                                                                           ";
		out.println(textmap);
	}
	
	
	public Coordinates solveCoordInput (){
		int numberX = 0;
		int numberY = 0;
		boolean validanswer = false;
		do{
			String input = in.nextLine();
			if (input.length()>1 && input.length()<4){
				char letter = input.charAt(0);
				input = input.substring(1);
				//parse the ASCII code of the char and convert it to a number, starting from 'A'-->1
				numberX = (int)letter;
				if (numberX<88) 
					numberX-=64;
				else numberX-=96;
				try	{
					numberY = Integer.parseInt(input);
				}			
				catch (NumberFormatException ex){
					numberY = 0;
				}
				if (numberX>0 && numberX<24 && numberY>0 && numberY<15)
					validanswer = true;
			}
			if (!validanswer)
				out.println("Please write the coordinates in a line as they appear in one of the boxes of the map");
		}while(!validanswer);
		return new Coordinates(numberX, numberY);
	}


		
}
