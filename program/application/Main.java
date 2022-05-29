package application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	String debug;
    	GameLogic newGame;
    	
    	System.out.println("$$$$$$$$ Welcome to DEAL OR NO DEAL $$$$$$$$");
    	System.out.println("Please press D/d for a debugging session or press any other key to start the game: ");
    	
    	Scanner scanner = new Scanner(System.in); 
		debug = scanner.nextLine();
		
		if(debug.equalsIgnoreCase("d")) {
			newGame = new GameLogic(true);
		} else {
			newGame = new GameLogic(false);
		}
		
		
    
    	newGame.startGame();
       
    }
}
