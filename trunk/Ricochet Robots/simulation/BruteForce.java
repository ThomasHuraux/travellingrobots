package simulation;

import java.util.ArrayList;

import model.Environment;

public class BruteForce implements Heuristic{
	
	public static final BruteForce DEFAULT = new BruteForce();

	@Override
	public Environment best(Environment current, ArrayList<Environment> open) {
		return open.get(0);
	}

	@Override
	public int getNbITER() {
		// TODO Auto-generated method stub
		return 0;
	}

}
