package simulation;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.*;
import view.*;


public class TestAStar {

	public static void main(String[] args){
		
		Environment env = new Environment();
		
		Robot red = new Robot(Color.RED);
		Robot green = new Robot(Color.GREEN);
		Robot yellow = new Robot(Color.YELLOW);
		Robot blue = new Robot(Color.BLUE);
		
		env.addRobot(red,new Position(14,0));
		env.addRobot(green,new Position(0,11));
		env.addRobot(yellow,new Position(5,13));
		env.addRobot(blue,new Position(5,7));
		
		Position target = new Position(9,2);
		env.addTarget(red, target);
		
		//***********************************************
		
		
		AStar algo = new AStar();
		algo.init(env);
		algo.preCalc(env);
		
		int[][] distToTarget = algo.getDistToTarget();
		
		
		//***********************************************
		
		
		Simple view = new Simple(env);
		Count count1 = new Count(env,distToTarget);
		
		JPanel all = new JPanel();
		all.add(view);
		all.add(count1);
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(all);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
	}
	
}
