package simulation;

import model.Environment;


public class TestReachTheTarget {

	static final int NBTESTS = 100;
	
	public static void main(String[] args) {

		TestHeuristic tests = new TestHeuristic(NBTESTS,Heuristic.ReachTheTargetID,60,15,0.0);
	}
}
