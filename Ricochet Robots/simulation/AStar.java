package simulation;

import model.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class AStar extends MotionPlanner{
	
	private ArrayList<Environment> open;
	private ArrayList<Environment> close;
	private ArrayList<String> log;
	
	Robot red = new Robot(Color.RED);
	Robot green = new Robot(Color.GREEN);
	Robot yellow = new Robot(Color.YELLOW);
	Robot blue = new Robot(Color.BLUE);
		
	ArrayList<Robot> robots = new ArrayList<Robot>();
	
	private Environment current;
	private Heuristic heuristic;
	
	private float timelength;
	
	public AStar(Heuristic h){
		heuristic = h;
	}
	
	public ArrayList<Position> getAcceptedTarget(){
		ArrayList<Position> listes = new ArrayList<Position>();
		
		listes.add(new Position(6,1));
		listes.add(new Position(13,1));
		listes.add(new Position(9,2));
		listes.add(new Position(1,3));
		listes.add(new Position(2,5));
		listes.add(new Position(7,5));
		listes.add(new Position(14,5));
		listes.add(new Position(11,6));
		listes.add(new Position(5,8));
		listes.add(new Position(2,9));
		listes.add(new Position(12,9));
		listes.add(new Position(9,10));
		listes.add(new Position(4,13));
		listes.add(new Position(14,13));
		listes.add(new Position(1,14));
		listes.add(new Position(9,14));
		
		return listes;
	}
	
	public void initDirectCase(Environment e){
		e.addRobot(red,new Position(14,0));
		e.addRobot(green,new Position(0,11));
		e.addRobot(yellow,new Position(5,13));
		e.addRobot(blue,new Position(5,7));
		
		Position target = new Position(9,2);
		e.addTarget(red, target);
	}
	
	public void initRandomCase(Environment e){
		
		while(!e.addRobotArbitrarly(red));
		while(!e.addRobotArbitrarly(green));
		while(!e.addRobotArbitrarly(yellow));
		while(!e.addRobotArbitrarly(blue));
		
		Random r = new Random();
		int idTarget =  r.nextInt(getAcceptedTarget().size()-1);
		e.addTarget(red,getAcceptedTarget().get(idTarget));
	}
	
	public void init(Environment e){
		
		robots.add(red);
		robots.add(green);
		robots.add(yellow);
		robots.add(blue);
		
		//initDirectCase(e);
		initRandomCase(e);
		
		open = new ArrayList<Environment>();
		close = new ArrayList<Environment>();
		
		log = new ArrayList<String>();
				
		current = e;	
		addInOpen(e);
		addInClose(e);
		addNewNodes(e);
	}

	public Sequence search(Environment e){
		init(e);
		System.out.println("Search ...");
		long begin = System.currentTimeMillis();
		while( !isFinal(current) && !open.isEmpty() ){
			current = heuristic.best(current,open);
			addInClose(current);
			addNewNodes(current);
		}
		if( isFinal(current) ){
			Sequence result = new Sequence();
			result.addAll(close);
			
			timelength = ((float) (System.currentTimeMillis()-begin)) / 1000f;
			System.out.println("Target reached ("+timelength+"ms) ["+close.size()+" nodes in 'close' list]");
			return result;
		}else return null;
	
	}

	private boolean isFinal(Environment e) {
		for(State s : e.getStates())
			if(s.getRobot()==e.getTagged() && s.getPosition().compare(e.getTarget()))
				return true;
		return false;
	}

	private void addNewNodes(Environment e){	
		
		for(Robot r : robots){		
			//System.out.println("ADD NEW NODES FOR "+r.getColor());
			
			Environment re = e.clone();
			if(re.modify(r,Movement.EAST) != null){
				r.moveEast(re);			
				addInOpen(re);
				//System.out.println("\tEAST");
			}
			
			Environment rw = e.clone();
			if(rw.modify(r,Movement.WEST) != null){
				r.moveWest(rw);
				addInOpen(rw);
				//System.out.println("\tWEST");
			}
			
			Environment rn = e.clone();
			if(rn.modify(r,Movement.NORTH) != null){
				r.moveNorth(rn);
				addInOpen(rn);
				//System.out.println("\tNORTH");
			}
			
			Environment rs = e.clone();
			if(rs.modify(r,Movement.SOUTH) != null){
				r.moveSouth(rs);
				addInOpen(rs);
				//System.out.println("\tSOUTH");
			}			
		}
	}

	private void addInClose(Environment current) {	
		close.add(current);
		open.remove(current);
	}
	
	private void addInOpen(Environment current) {
		String s = getStringRepresentation(current);
		if(! log.contains(s)){
			open.add(current);
			log.add(s);
		}
	}
	
	public static String getStringRepresentation(Environment e){
		String r = new String();
		for(State s : e.getStates())
			r += s.getPosition().getX()+"."+s.getPosition().getY()+"_";
		return r;
	}

	public float getTimelength() {
		return timelength;
	}
	
}
