package simulation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import model.*;

public class RandomMover {
	
	private ArrayList<Robot> robots;
	private int[] last;
	private Environment env;
	private int nbStep;
	private static final boolean noReturn = true;
	
	public RandomMover(int nbStep,Environment env){
		
		this.nbStep = nbStep;
		
		robots = new ArrayList<Robot>();
		
		robots.add( new Robot(Color.RED));
		robots.add( new Robot(Color.GREEN));
		robots.add( new Robot(Color.YELLOW));
		robots.add( new Robot(Color.BLUE));
		
		last = new int[robots.size()];
	
		this.env = env;

		for(Robot r : robots)
			while(! env.addRobotArbitrarly(r));
	}
	
	public void start(){
		for(int i = 0; i<nbStep; i++)
			step();
	}
	
	private void step(){
		int robot = random(0,3);
		int movement = random(0,3);
		while(noReturn && movement==last[robot]){
			robot = random(0,3);
			movement = random(0,3);
		}
		switch(random(0,3)){
				case Movement.NORTH:
					robots.get(robot).moveNorth(env);
					last[robot] = Movement.NORTH;
					System.out.println("Move R"+robot+" to North");
					break;
				case Movement.EAST:
					robots.get(robot).moveEast(env);
					last[robot] = Movement.EAST;
					System.out.println("Move R"+robot+" to East");
					break;
				case Movement.SOUTH:
					System.out.println("Move R"+robot+" to South");
					last[robot] = Movement.SOUTH;
					robots.get(robot).moveSouth(env);
					break;
				case Movement.WEST:
					System.out.println("Move R"+robot+" to West");
					last[robot] = Movement.WEST;
					robots.get(robot).moveWest(env);
					break;
		}
	}
	
	private int random(int min, int max){
		Random r = new Random();
		return min + r.nextInt(max - min);
	}
		
}
