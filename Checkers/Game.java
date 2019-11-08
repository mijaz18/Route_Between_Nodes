import java.util.Scanner;

public class Game { //class for running the actual logic of the game 

	public static void runGame() {
		
		Scanner in = new Scanner(System.in);
		String Player;
		
		String[][] board = BoardSetup.PromptBoard();
		int algoChosen = BoardSetup.promptAlgorithm();	
		
		System.out.println("Do you want to play BLACK (b) or WHITE (w)?");
		String color= in.next();
		if(color.equalsIgnoreCase("w")) {
			Player = "w";
		}
		else { //might need to come back and add error handling to this
			Player = "b";
		}
		
		
	}
	
	public static void main(String[] args) {
		runGame();
	}
}
