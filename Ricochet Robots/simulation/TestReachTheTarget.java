package simulation;

import java.awt.Color;

import model.Environment;
import model.Position;
import model.Robot;

public class TestReachTheTarget {

	static Environment current = new Environment();
	
	public static void main(String[] args) {
		AStar algo = new AStar(ReachTheTarget.DEFAULT);
		
		algo.search(current);
	}

}
