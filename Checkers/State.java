import java.util.Arrays;

public class State {

	int board[][];
	int player;
	String Statenum;
	int Utility;
	
	public void initialState(int dim1,int dim2,int player) {
		this.board=new int[dim1][dim2]; //for a 4by4 board dim1=5 and dim2=10
		this.player=player; //for playera player=1 and for playerb=2
		
	}
	
	public void CurrState() {
		System.out.println(Arrays.toString(this.board));
		System.out.println("Player "+ player +"'s turn");
		
	}
	
}

