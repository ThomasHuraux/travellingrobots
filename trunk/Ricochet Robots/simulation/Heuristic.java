package simulation;

import java.util.ArrayList;

public interface Heuristic {
	
	public static int ReachTheTargetID = 1;
	public static int CorridorHeuristicID = 2;
	public static int BruteForceID = 3;
	
	public void preCalc(Node current);
	public Node best(Node current, ArrayList<Node> open);
	public int getNbITER();
	public void setHeuristic(Node e);

}
