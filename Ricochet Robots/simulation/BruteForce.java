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

	@Override
	public void preCalc(Node current) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeuristic(Node e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getHeuristicID() {
		return this.BruteForceID;
	}

}
