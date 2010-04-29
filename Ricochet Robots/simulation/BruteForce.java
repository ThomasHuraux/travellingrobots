package simulation;

import java.util.ArrayList;


public class BruteForce implements Heuristic{
	
	public static final BruteForce DEFAULT = new BruteForce();

	@Override
	public Node best(Node current, ArrayList<Node> open) {
		return open.get(0);
	}

	@Override
	public int getNbITER() {
		// TODO Auto-generated method stub
		return 0;
	}

}
