package simulation;

public class AStarTestForStats {
	
	public static void main(String[] args){
		
		//TestHeuristic(int NbTest, int HeuristicID, int maxtime, int precalcDepth, double costImportance)
		
		@SuppressWarnings("unused")
		TestHeuristic th0 = new TestHeuristic(50,Heuristic.ReachTheTargetID,60,10,0.0);
		@SuppressWarnings("unused")
		TestHeuristic th1 = new TestHeuristic(50,Heuristic.ReachTheTargetID,60,15,0.0);
		@SuppressWarnings("unused")
		TestHeuristic th2 = new TestHeuristic(50,Heuristic.ReachTheTargetID,60,20,0.0);
	}

}
