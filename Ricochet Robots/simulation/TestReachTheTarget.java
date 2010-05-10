package simulation;


public class TestReachTheTarget {

	static final int NBTESTS = 10;
	
	public static void main(String[] args) {

		TestHeuristic tests = new TestHeuristic(NBTESTS,Heuristic.ReachTheTargetID,30,10,0.0);
	}
}
