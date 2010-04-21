package simulation;
import view.*;
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
	private static final int STEPTIME = 0;
	
	public RandomMover(int nbStep,Environment env){
		
		this.nbStep = nbStep;
		
		robots = new ArrayList<Robot>();
		
		init(4);
		
		last = new int[robots.size()];
	
		this.env = env;

		for(Robot r : robots)
			while(! env.addRobotArbitrarly(r));
	}
	
	public void init(int nbRobots) {
		if (nbRobots == 4) {
			robots.add( new Robot(Color.RED));
			robots.add( new Robot(Color.GREEN));
			robots.add( new Robot(Color.YELLOW));
			robots.add( new Robot(Color.BLUE));
		}
		else
			for (int i = 0; i < nbRobots; i++)
				robots.add(new Robot(Color.BLACK));
	}
	
	public void start(Simple view, FrequenceColor count){
		final Simple v = view;
//		final Count c = count;
		final FrequenceColor c = count;
		Thread t = new Thread(){
			public void run(){
				for(int i = 0; i<nbStep; i++){
					Robot moveOne = step();
					c.increase((env.getState(moveOne)).getPosition());
					v.update(env);
					try {
						sleep(STEPTIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();		
	}
	
	private Robot step(){
		int robot = random(0,robots.size());
		int movement = random(0,robots.size());
		while(noReturn && movement==last[robot]){
			movement = random(0,robots.size());
		}
		switch(movement){
				case Movement.NORTH:
					robots.get(robot).moveNorth(env);
					last[robot] = Movement.NORTH;
					break;
				case Movement.EAST:
					robots.get(robot).moveEast(env);
					last[robot] = Movement.EAST;
					break;
				case Movement.SOUTH:
					last[robot] = Movement.SOUTH;
					robots.get(robot).moveSouth(env);
					break;
				case Movement.WEST:
					last[robot] = Movement.WEST;
					robots.get(robot).moveWest(env);
					break;					
		}
		
		return robots.get(robot);
	}
	
	private int random(int min, int max){
		Random r = new Random();
		return min + r.nextInt(max - min);
	}
		
}
