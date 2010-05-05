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
		@SuppressWarnings("unused")
		TestHeuristic th3 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,15,0.05);
		@SuppressWarnings("unused")
		TestHeuristic th4 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,15,0.10);
		@SuppressWarnings("unused")
		TestHeuristic th5 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,15,0.10);
		@SuppressWarnings("unused")
		TestHeuristic th6 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,15,0.50);
		@SuppressWarnings("unused")
		TestHeuristic th7 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,10,0.15);
		@SuppressWarnings("unused")
		TestHeuristic th8 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,20,0.5);
		@SuppressWarnings("unused")
		TestHeuristic th9 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,5,0.60);
		@SuppressWarnings("unused")
		TestHeuristic th10 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,5,0.8);
		@SuppressWarnings("unused")
		TestHeuristic th11 = new TestHeuristic(50,Heuristic.CorridorHeuristicID,30,20,0.01);
	}

}
