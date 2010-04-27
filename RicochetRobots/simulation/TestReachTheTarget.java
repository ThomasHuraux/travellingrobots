package simulation;

import java.awt.Color;

import model.Environment;
import model.Position;
import model.Robot;

public class TestReachTheTarget {

	public static void main(String[] args) {
		Environment env = new Environment();

		Position target = new Position(9,2);
		env.addTarget(null, target);
		
		Robot red = new Robot(Color.red);
		Robot blue = new Robot(Color.BLUE);
		Robot green = new Robot(Color.GREEN);
		Robot yellow = new Robot(Color.YELLOW);
		
		env.addRobotArbitrarly(red);
		env.addRobotArbitrarly(blue);
		env.addRobotArbitrarly(green);
		env.addRobotArbitrarly(yellow);
		env.setTaggedRobot(red);
		
		ReachTheTarget algo = new ReachTheTarget();
		
		Sequence result = algo.search(env);
		
		//Affichage de la trace du chemin
	}

}
