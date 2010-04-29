package simulation;

import java.util.ArrayList;

public interface Heuristic {
	
	public Node best(Node current, ArrayList<Node> open);
	public int getNbITER();

}
