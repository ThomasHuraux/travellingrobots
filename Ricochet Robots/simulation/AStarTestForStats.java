package simulation;

public class AStarTestForStats {
	
	public static void main(String[] args){
		
		//TestHeuristic(int NbTest, int HeuristicID, int maxtime, int precalcDepth, double costImportance)
		
		@SuppressWarnings("unused")
		TestHeuristic th0 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,10,0.0);
		@SuppressWarnings("unused")
		TestHeuristic th1 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,15,0.0);
		@SuppressWarnings("unused")
		TestHeuristic th2 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,20,0.0);

	}

}
