package application;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameLogic {
	
	private List<Case> cases;
	public static Scanner scanner = new Scanner(System.in);
	
	public GameLogic(boolean isDebug) {
		double caseValue = 0;
		cases = new ArrayList<>();
		
		List<Double> values = Arrays.asList
				(0.01, 1.0, 5.0, 10.0, 25.0, 50.0, 75.0, 100.0, 200.0, 300.0, 400.0,
						500.0, 750.0, 1000.0, 5000.0, 10000.0, 25000.0, 50000.0, 75000.0,
						100000.0, 200000.0, 300000.0, 400000.0, 500000.0, 750000.0, 1000000.0);
		
		if(!isDebug) {
			Collections.shuffle(values);
		}
		
		for(int i = 0; i < values.size(); i++) {
			caseValue = values.get(i);
			cases.add(new Case(i+1,caseValue));
		}
		
	}
	
	
	public void showCases() {
		for(int i = 0; i < cases.size(); i++) {
			if(!cases.get(i).isRemoved()) {
				System.out.print("[" + (cases.get(i).getCaseNumber()) + "]");				
			}
		}
		System.out.println();
	}
	
	public int getPlayerCase() {
		int caseNumber = -1;
		String possibleCaseNumber;
		showCases();
		
		
		do {
			System.out.println("Please choose your suitcase 1-26 with your price: ");
			possibleCaseNumber = scanner.nextLine();
			caseNumber = getCaseNumber(possibleCaseNumber);
		} while (caseNumber == -1);
		
		return caseNumber;
	}
	
	public int getCaseNumber(String checkCaseNumber) {
		int caseNumber = -1;

		try {
			caseNumber = Integer.parseInt(checkCaseNumber)-1;
			
			if(caseNumber < 0 || caseNumber > cases.size() ) {
				System.out.println("This number is out of range, please try again.");
			} else {
				if(cases.get(caseNumber).isRemoved()) {
					System.out.println("Sorry, this suitcase is already taken, please try again.");
				} else {
					return caseNumber;
				}
			}
			
			return -1;
			
		} catch(NumberFormatException e) {
			System.out.println("Please type in a number!");
			return -1;
		}
		
		
	}
	
	public void eliminateCase(int eliminateRounds) {
		String possibleCaseNumber;
		for(int i = 0; i < eliminateRounds; i++) {
			int eliminateCaseNumber = 0;
			do {
				System.out.println("Pick a suitcase to eliminate form the game:");
				possibleCaseNumber = scanner.nextLine();
				eliminateCaseNumber = getCaseNumber(possibleCaseNumber);
			} while (eliminateCaseNumber == -1);
			System.out.println("Case " + (eliminateCaseNumber+1) + " was eliminated.");
			System.out.println("It contains " + (int)cases.get(eliminateCaseNumber).getValue() + "€");
			System.out.println();
			cases.get(eliminateCaseNumber).setIsRemoved();
		}
	}
	
	public void endGame (double wonValue) {
		System.out.println("You decide to end the game.");
		System.out.println("Congratulations, you won " + (int)wonValue + "€");
		System.out.println("$$$$$$$$ Thank you for playing DEAL OR NO DEAL $$$$$$$$");
		System.exit(0);
	}
	
	public int getLastCaseNumber() {
		for(int i = 0; i < cases.size(); i++) {
			if(!cases.get(i).isRemoved()) {
				return cases.get(i).getCaseNumber();
			}
		}
		
		return -1;
	}
	
	
	
	public void startGame() {
		int[] round = {6, 5, 4, 3, 2, 1, 1, 1, 1};
		Banker banker = new Banker();
		int bankerOffer = 0;
		String chooseBankersOffer;
		
		Player player = new Player("Oliver", getPlayerCase());
		cases.get(player.getChoosenCaseNumeber()).setIsRemoved();
		
		for(int i = 0; i < round.length; i++) {
			showCases();
			eliminateCase(round[i]);
			bankerOffer = banker.getOffer(cases, cases.get(player.getChoosenCaseNumeber()).getValue(), i+1);
			System.out.println("The bank offers you " + bankerOffer + "€");
			
			System.out.println("Do you accept the offer?");
			System.out.println("Please press Y/y for YES or any other key for NO");
			chooseBankersOffer = scanner.nextLine();
			if(chooseBankersOffer.equalsIgnoreCase("y")) {
				endGame(bankerOffer);
			}
			
			if(i == round.length-1) {
				int lastCaseNumber = getLastCaseNumber();
				System.out.println("The suitcase with your price was number " + (player.getChoosenCaseNumeber()+1) + ".");
				System.out.println("Suitcase number " + lastCaseNumber + " is the last one remaining.");
				System.out.println("Now we give you a chance to switch to this suitcase.");
				System.out.println("Do you want to switch suitcases?");
				System.out.println("Please press Y/y for YES or any other key for NO");
				chooseBankersOffer = scanner.nextLine();
				if(chooseBankersOffer.equalsIgnoreCase("y")) {
					endGame(cases.get(lastCaseNumber).getValue());
				} else {
					endGame(cases.get(player.getChoosenCaseNumeber()).getValue());
				}
				
			}
		}
		

	}
	
	
	
	
	

}
