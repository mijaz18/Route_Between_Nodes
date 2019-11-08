import java.util.ArrayList;

public class DataStruct {

	//Sate = Turn of player
	int Player=0; //0=player a and 1 for player b ||| or boolean state=true
	
	String Action; //Since Action is just move to a specific box like A1, B3, C2
	
	//Returns valid Actions in the current state
	public static ArrayList Actions(int state) {
		return null;
		
	}
	
	//Result outputs the state after you perform the action from state s
	public static State Result (State s, String Action) {
		return null;
		
	}
	
	//Returns the Utility of a player by searching the terminal states 
	public static int Utility(State s, int Player) {
		return 0;
	}
	
	//Returns true if the argument is a terminal state and false otherwise
	public static boolean Terminaltest(State s) {
		return false;
	}
	
}
