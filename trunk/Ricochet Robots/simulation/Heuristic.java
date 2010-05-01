package simulation;

import java.util.ArrayList;

public interface Heuristic {
	
	public void preCalc(Node current);
	public Node best(Node current, ArrayList<Node> open);
	public int getNbITER();
	public void setHeuristic(Node e);

}
