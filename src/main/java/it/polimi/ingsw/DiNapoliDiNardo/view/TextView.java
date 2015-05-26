package it.polimi.ingsw.DiNapoliDiNardo.view;

import it.polimi.ingsw.DiNapoliDiNardo.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.Player;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;

import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TextView extends View{
	Scanner in = new Scanner(System.in);
	PrintWriter out = new PrintWriter(System.out);
	boolean isFirstTurn = true;
	
	
	public String askName (){
		System.out.println("Connected to the server game. What's your name?");
		return in.nextLine();
	}
	
	
	public void showActualSituation (String name, String position, String objects){
		if(isFirstTurn){
			printGalileiMap();
			isFirstTurn = false;
		}
		if (position.length() == 2)
			position = position.substring(0, 1) + "0" + position.substring(1, position.length());
		System.out.println("________________________________________________________________________________________________________________________");
		System.out.println(name+ " you are now in the "+position+" position.");
		if (!objects.equals("no")){
			System.out.println("In your item deck you have the following cards: "+objects);
		}
		System.out.println("--------");
	}
	
		
	
	public void showBeingAlien (String name){
		System.out.println(name+", the horrible alien disease that is infecting the spaceships has caught you time ago. ");
		System.out.println("Your body is now a brutal and deformed machine eager to kill any human that is still alive carrying fresh meat.");
		System.out.println("Your objective is tracking down the poor humans that are trying to reach the lifeboat ships and kill'em before they do it.");
		System.out.println("All the miserable humans. Enjoy your meal.");
	}
	
	
	public void showBeingHuman (String name){
		System.out.println(name+", you are one of the survivors on the spaceship that resisted to the spreading, horrible disease that could have infected anyone of your team mates.");
		System.out.println("Horrendous aliens that once were your friends are lurking in the dark to kill you and eat you, and they could be anyone and anywhere. ");
		System.out.println("Your objective is reaching one of the avaiable lifeboat ships avoiding to attract the attentions of the blood-thirsty monsters that surround you.");
		System.out.println("The mission depends on you. Your life too. Good luck.");
	}
	
	
	public Coordinates askMovement(boolean reask){
		if(reask){
			System.out.println("The movement you selected is not valid. Please select another box.");
		}
		System.out.println("Where do you want to move? Insert the coordinates of the box you want to move in");
		Coordinates coordinates = solveCoordInput();		
		return coordinates;
		
	}
	
		
	public int askItemUse(String objects){
		String[] Items = objects.split(";");
		System.out.println("Do you want to use an Item Card?");
		System.out.println("Y: yes    N: no");
		String input = in.nextLine();
		char risp;
		if (input.length()>0)
			risp = input.charAt(0);
		else risp = 'w';
		if (risp == 'Y' || risp == 'y'){
			int answer;
			do{
				System.out.println("Select the number of the item you want to use:");
				for (int i=0; i<Items.length && i<3; i++){
					int j = i+1;
					System.out.println(j+"- "+Items[i]);
				}
				try{
					answer = in.nextInt();
				}catch (InputMismatchException e){
					answer=0;
				}
				in.nextLine();
				if (answer <=0 || answer>Items.length)
					System.out.println("Please write the number of the object you want to use and nothing more");
			}while (answer <=0 || answer>Items.length);
			return answer;
		}
		else if (risp != 'N' && risp != 'n'){
			System.out.println("Please insert 'Y' or 'N'");
			askItemUse(objects);
		}
		return 8;	
	}
	
	
	public int askAlienItemDiscard(String objects){
		String[] Items = objects.split(";");
		System.out.println("You drew an item card but your three card slots are full. Do you want to discard a card you have to free one slot for the new card?");
		System.out.println("D: discard an item	N: no, I'll keep my actual items");
		String input = in.nextLine();
		char risp;
		if (input.length()>0)
			risp = input.charAt(0);
		else risp = 'w';
		boolean validanswer = false;
		do{
			if (risp == 'D' || risp == 'd'){
				validanswer = true;
				int answer;
				
				do{
					System.out.println("Select the number of the item you want to discard:");
					for (int i=0; i<Items.length && i<3; i++){
						int j = i+1;
						System.out.println(j+"- "+Items[i]);
					}
					try{
						answer = in.nextInt()+3;
					}catch (InputMismatchException e){
						answer=0;
					}
					in.nextLine();
					if (answer <=3 || answer>Items.length+3)
						System.out.println("Please write the number of the object you want to discard and nothing more");
				}while (answer <=3 || answer>Items.length+3);
				return answer;
			}
			if (risp == 'N' || risp == 'n'){
				validanswer = true;
				return 8;
			}	
			System.out.println("Please select one of the options writing the corresponding letter and nothing more");
		}while(!validanswer);
		return 18;
	}
	
	
	public int askHumanItemDiscard(String objects){
		String[] Items = objects.split(";");
		System.out.println("You drew an item card but your three card slots are full. Do you want to discard or use a card you have to free one slot for the new card?");
		System.out.println("U: use an item    D: discard an item	N: no, I'll keep my actual items");
		String input = in.nextLine();
		char risp;
		if (input.length()>0)
			risp = input.charAt(0);
		else risp = 'w';
		boolean validanswer = false;
		do{
			if (risp == 'D' || risp == 'd'){
				validanswer = true;
				int answer;
				
				do{
					System.out.println("Select the number of the item you want to discard:");
					for (int i=0; i<Items.length; i++){
						int j = i+1;
						System.out.println(j+"- "+Items[i]);
					}
					try{
						answer = in.nextInt()+3;
					}catch (InputMismatchException e){
						answer=0;
					}
					
					in.nextLine();
					if (answer <=3 || answer>Items.length+3)
						System.out.println("Please write the number of the object you want to discard and nothing more");
				}while (answer <=3 || answer>Items.length+3);
				return answer;
			}
			if (risp == 'U' || risp == 'u'){
				validanswer = true;
				return 9;
			}
			if (risp == 'N' || risp == 'n'){
				validanswer = true;
				return 8;
			}	
			System.out.println("Please select one of the options writing the corresponding letter and nothing more");
		}while(!validanswer);
		return 18;
	}
	
	
	public String askForAttack(){
		System.out.println("Filthy alien, do you want to attack this position?");
		System.out.println("Y: yes    N: no");
		boolean validanswer = false;
		do{
			
			String ans = in.nextLine();
			if (ans.length()>0)
				ans = ans.substring(0, 1);
			if (ans.equalsIgnoreCase("Y"))
				return ans;
			if (ans.equalsIgnoreCase("N"))
				return ans;	
			System.out.println("Please select one of the options writing the corresponding letter and nothing more");
		}while(!validanswer);
		
		return null;
	}
	
	
	public Coordinates askForLights(){
		System.out.println("Which sector of the map do you want to enlight? Insert the coordinates of the box you want to move in");
		Coordinates coordinates = solveCoordInput();
		return coordinates;
		
	}
	
	public String askForNoise(){
		System.out.println("In which sector of the map do you want to declare there's noise?");
		Coordinates coordinates = solveCoordInput();
		String noise = ""+(char)(coordinates.getCoordX()+64);
		String number = ""+ coordinates.getCoordY();
		if (number.length() == 1)
			number = "0"+ coordinates.getCoordY();
		noise += number;
		return noise;
	}
	
	
	public void revealingLights(Box box){
		System.out.println("In the position "+(char)(box.getCoordX()+64)+box.getCoordY()+" there are the following players: ");
		System.out.println(box.getPlayerHere().toString());
	}
	
	
	public void killPlayer(Player player){
		System.out.println("Player " + player +  " you are died");
	}
	
	public void attackNotSuccesful(){
		System.out.println("Attack has not been successful!");
	}
	
	
	public void printGalileiMap(){
		String textmap ="   ___       ___       ___       ___       ___       ___       ___       ___       ___       ___       ___       \n";
		textmap += "_______________________________________________________________________________________________________________________\n";
		textmap += "  /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\ \n";
		textmap += " /    \\___/ C01\\___/    \\___/ G01\\___/ I01\\___/ K01\\___/ M01\\___/    \\___/ Q01\\___/    \\___/ U01\\___/    \\ \n";
		textmap += "\\     /  \\     /         /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /  \\        \\     /  \\     /\n";
		textmap += " \\___/ B01\\___/      ___/ F01\\___/ H01\\___/ J01\\___/ L01\\___/ N01\\___/ P01\\___/ R01\\___     \\___/ V01\\___/ \n";
		textmap += "  /  \\  D  /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /  \\     /  \\     /  \\ \n";
		textmap += " / A02\\___/ C02\\___/ E02\\___/ G02\\___/ I02\\___/ K02\\___/ M02\\___/ O02\\___/ Q02\\___/ S02\\___/ U02\\___/ W02\\\n";
		textmap += "\\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /\n";
		textmap += " \\___/ B02\\___/ D02\\___/ F02\\___/ H02\\___/ J02\\___/ L02\\___/ N02\\___/ P02\\___/ R02\\___/ T02\\___/  3 \\___/ \n";
		textmap += "  /  \\  L  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  L  /  \\ \n";
		textmap += " / A03\\___/ C03\\___/ E03\\___/ G03\\___/ I03\\___/ K03\\___/ M03\\___/    \\___/ Q03\\___/    \\___/ U03\\___/ W03\\\n";
		textmap += "\\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\        \\  D  /  \\     /\n";
		textmap += " \\___/ B03\\___/ D03\\___/ F03\\___/ H03\\___/ J03\\___/ L03\\___/ N03\\   / P03\\___/ R03\\___     \\___/ V03\\___/ \n";
		textmap += "  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\     /  \\  D  /  \\ \n";
		textmap += " / A04\\___/ C04\\___/ E04\\___/ G04\\___/ I04\\___/ K04\\___/ M04\\___/    \\___/ Q04\\___/ S04\\   / U04\\___/ W04\\\n";
		textmap += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\  D  /  \\     /\n";
		textmap += " \\___/ B04\\___/    \\___/ F04\\___/ H04\\___/ J04\\___/ L04\\___/ N04\\___/ P04\\___/ R04\\___/    \\___/ V04\\___/ \n";
		textmap += "  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\     /  \\     /  \\     /  \\  D  /  \\ \n";
		textmap += " / A05\\___/ C05\\___/ E05\\___/ G05\\___/ I05\\___/ K05\\___/ M05\\___/ O05\\___/ Q05\\___/ S05\\___/ U05\\___/ W05\\\n";
		textmap += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /\n";
		textmap += " \\___/ B05\\___/ D05\\___/ F05\\___/    \\___/ J05\\___/ L05\\___/ N05\\___/ P05\\___/ R05\\___/ T05\\___/ V05\\___/ \n";
		textmap += "  /  \\     /  \\  D  /  \\  D  /  \\        \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\ \n";
		textmap += " / A06\\___/ C06\\___/ E06\\___/ G06\\___     \\___/ K06\\___/ M06\\___/ O06\\___/ Q06\\___/ S06\\___/ U06\\___/ W06\\\n";
		textmap += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /\n";
		textmap += " \\___/ B06\\___/    \\___/ F06\\___/ H06\\___/ J06\\___/  A \\___/ N06\\___/ P06\\___/ R06\\___/ T06\\___/ V06\\___/ \n";
		textmap += "  /  \\  D  /  \\        \\  D  /  \\  D  /  \\  D  /  \\ Box /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\ \n";
		textmap += " /    \\___/ C07\\        \\___/ G07\\___/ I07\\___/    \\___/    \\___/ O07\\___/ Q07\\___/ S07\\___/ U07\\___/    \\\n";
		textmap += "\\        \\  D  /         /  \\     /  \\  D  /                      \\  D  /  \\  D  /  \\  D  /  \\  D  /         /\n";
		textmap += " \\        \\___/      ___/ F07\\___/ H07\\___/      ___       ___     \\___/ P07\\___/ R07\\___/ T07\\___/         / \n";
		textmap += "  /         /  \\     /  \\  D  /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\     /  \\     /  \\        \\ \n";
		textmap += " /      ___/ C08\\___/ E08\\___/ G08\\___/ I08\\___/ K08\\___/ M08\\___/ O08\\___/ Q08\\___/ S08\\___/ U08\\___     \\\n";
		textmap += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /\n";
		textmap += " \\___/ B08\\___/ D08\\___/ F08\\___/ H08\\___/ J08\\___/  H \\___/ N08\\___/ P08\\___/ R08\\___/T08\\___/ V08\\___/ \n";
	    textmap += "  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\ Box /  \\  D  /  \\  D  /  \\     /  \\     /  \\     /  \\ \n";
		textmap += " / A09\\___/ C09\\___/ E09\\___/ G09\\___/ I09\\___/ K09\\___/ M09\\___/ O09\\___/ Q09\\___/ S09\\___/ U09\\___/ W09\\\n";
		textmap += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /\n";
		textmap += " \\___/ B09\\___/ D09\\___/ F09\\___/ H09\\___/ J09\\___/ L09\\___/ N09\\___/ P09\\___/ R09\\___/    \\___/ V09\\___/ \n";
		textmap += "  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /         /  \\  D  /  \\ \n";
		textmap += " / A10\\___/ C10\\___/ E10\\___/ G10\\___/ I10\\___/ K10\\___/ M10\\___/ O10\\___/ Q10\\___/         / U10\\___/ W10\\\n";
		textmap += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /            \\  D  /  \\     /\n";
		textmap += " \\___/ B10\\___/ D10\\___/ F10\\___/    \\___/ J10\\___/ L10\\___/ N10\\___/ P10\\___/              \\___/ V10\\___/ \n";
		textmap += "  /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\               /  \\  D  /  \\ \n";
		textmap += " / A11\\___/ C11\\___/ E11\\___/ G11\\   / I11\\___/ K11\\___/ M11\\___/ O11\\___/ Q11\\          ___/ U11\\___/ W11\\\n";
		textmap += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\     /         /  \\  D  /  \\     /\n";
		textmap += " \\___/ B11\\___/ D11\\___/ F11\\___/    \\___/ J11\\___/ L11\\___/ N11\\___/ P11\\___/      ___/ T11\\___/ V11\\___/ \n";
		textmap += "  /  \\  D  /  \\  D  /  \\  D  /  \\        \\  D  /  \\     /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\ \n";
		textmap += " / A12\\___/ C12\\___/ E12\\___/ G12\\___     \\___/ K12\\___/ M12\\___/ O12\\___/ Q12\\___/ S12\\___/ U12\\___/ W12\\\n";
		textmap += " \\    /  \\  D  /  \\     /  \\     /  \\        \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\     /\n";
		textmap += "  \\__/ B12\\___/ D12\\___/    \\___/ H12\\___     \\___/ L12\\___/ N12\\___/ P12\\___/ R12\\___/ T12\\___/ V12\\___/ \n";
		textmap += "  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\        \\  D  /  \\  D  /  \\     /  \\     /  \\  D  /  \\  D  /  \\ \n";
		textmap += " / A13\\___/ C13\\___/ E13\\   / G13\\___/ I13\\___     \\___/ M13\\___/ O13\\___/ Q13\\___/ S13\\___/ U13\\___/ W13\\\n";
		textmap += "\\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\     /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /\n";
		textmap += " \\___/ B13\\___/ D13\\___/    \\___/ H13\\___/ J13\\___/ L13\\___/ N13\\___/ P13\\___/ R13\\___/ T13\\___/  4 \\___/ \n";
		textmap += "  /  \\  L  /  \\  D  /         /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  D  /  \\  L  /  \\ \n";
		textmap += " / A14\\___/ C14\\___/         / G14\\___/ I14\\___/ K14\\___/ M14\\___/ O14\\___/ Q14\\___/    \\___/ U14\\___/ W14\\\n";
		textmap += "\\  D  /  \\     /  \\        \\     /  \\     /  \\  D  /  \\  D  /  \\     /  \\     /         /  \\  D  /  \\  D  /\n";
		textmap += " \\___/ B14\\___/ D14\\___     \\___/ H14\\___/ J14\\___/ L14\\___/ N14\\___/ P14\\___/      ___/ T14\\___/ V14\\___/ \n";
		textmap += "     \\  D  /  \\     /  \\     /  \\     /  \\     /  \\     /  \\     /  \\  D  /  \\     /  \\     /  \\  D  /     \n";
		textmap += "      \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/    \\___/      \n";
		textmap += "                                                                                                                        ";
		System.out.println(textmap);
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
				if (numberX<88) numberX-=64;
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
			if (validanswer == false)
				System.out.println("Please write the coordinates in a line as they appear in one of the boxes of the map");
		}while(!validanswer);
		return (new Coordinates(numberX, numberY));
	}
	
}
