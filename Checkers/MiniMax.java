import java.util.ArrayList;

public class MiniMax {
	
	static Action a= new Action();
	static DataStruct dat=new DataStruct();

	public static Action MiniMaxDec(State s) {
		int max=Integer.MIN_VALUE;
		Action MaxAction=null;
		ArrayList<Action> Actions=new ArrayList<Action>();
		Actions=a.getActions(s);
		for(Action act: Actions) {
			if(MinValue(dat.Result(s, act))>=max) {
				max=MinValue(dat.Result(s, act));
				MaxAction=act;
			}
			
		}
		
		return MaxAction;
		
	}
	
	public static int MaxValue(State s) {
		int v;
		ArrayList<Action> Actions=new ArrayList<Action>();
		if(DataStruct.Terminaltest(s)==true) {
			return s.Utility;
		}else {
			v=Integer.MIN_VALUE;
			Actions=a.getActions(s);
			for(Action act: Actions) {
				v=Math.max(v, MinValue(dat.Result(s, act)));
			}
		}
		return v;
	}
	
	public static int MinValue(State s) {
		int v;
		ArrayList<Action> Actions=new ArrayList<Action>();
		if(dat.Terminaltest(s)==true) {
			return s.Utility;
		}else {
			v=Integer.MAX_VALUE;
			Actions=a.getActions(s);
			for(Action act: Actions) {
				v=Math.min(v, MaxValue(dat.Result(s, act)));
			}
		}
		return v;
	}
	
}
