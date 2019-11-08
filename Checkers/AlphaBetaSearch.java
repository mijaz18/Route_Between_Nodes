import java.util.ArrayList;

public class AlphaBetaSearch {
	
	static Action ac= new Action();
	static DataStruct dat=new DataStruct();
	
	public Action alphabetasearch(State s) {
		return null;
	}
	
	public int MaxValue(State s, int a, int b) {
		int v;
		ArrayList<Action> Actions=new ArrayList<Action>();
		if(dat.Terminaltest(s)) {
			return s.Utility;
		}else {
			v=Integer.MIN_VALUE;
			Actions=ac.getActions(s);
			for(Action act: Actions) {
				v=Math.max(v, MinValue(dat.Result(s, ac),a,b));
				if(v>=b) {
					return v;
				}else {
					a=Math.max(a, v);
				}
			}
			return v;
		}
	}
	
	public int MinValue(State s, int a, int b) {
		return 0;
	}

}
