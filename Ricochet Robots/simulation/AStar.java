package simulation;

import model.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class AStar extends MotionPlanner{
	
	public static final float MAXTIME = 30;
	
	private ArrayList<Node> open;
	private ArrayList<Node> close;
	private ArrayList<String> log;
	
	Robot red = new Robot(Color.RED);
	Robot green = new Robot(Color.GREEN);
	Robot yellow = new Robot(Color.YELLOW);
	Robot blue = new Robot(Color.BLUE);
		
	ArrayList<Robot> robots = new ArrayList<Robot>();
	
	protected Node current;
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
		
		open = new ArrayList<Node>();
		close = new ArrayList<Node>();
		
		log = new ArrayList<String>();
				
		current = new Node(e);	
		addInClose(current);
		addNewNodes(current);
	}

	public Sequence search(Environment e){
		init(e);
		System.out.println("Search ...");
		long begin = System.currentTimeMillis();
		
		try{
			while( !isFinal(current.getEnvironment()) && !open.isEmpty() ){
				current = heuristic.best(current,open);
				addInClose(current);
				addNewNodes(current);
				
				timelength = ((float) (System.currentTimeMillis()-begin)) / 1000f;
				if(timelength >= MAXTIME){
					System.out.println("Too long !");
					return null;
				}
			}
		}catch(Exception exception){
			System.out.println("ERROR : ");
			exception.printStackTrace();
		}
		
		if( isFinal(current.getEnvironment()) ){
			Sequence result = new Sequence();
			result.addAll(close);
			
			System.out.println("Target reached with "+heuristic.getClass().toString()+" [ timelength="+timelength+" ; movesNb="+current.getCost()+" ]");
			
			return result.organize();
		}else return null;
	
	}

	private boolean isFinal(Environment e) {
		return e.getState(e.getTagged()).getPosition().compare(e.getTarget());
	}

	private void addNewNodes(Node e){	
		
		heuristic.preCalc(e);
		
		for(Robot r : robots){		
			//System.out.println("ADD NEW NODES FOR "+r.getColor());
			
			Node re = new Node(e);
			if(re.getEnvironment().modify(r,Movement.EAST) != null){
				r.moveEast(re.getEnvironment());
				heuristic.setHeuristic(re);
				addInOpen(re);
				//System.out.println("\tEAST");
			}
			
			Node rw = new Node(e);
			if(rw.getEnvironment().modify(r,Movement.WEST) != null){
				r.moveWest(rw.getEnvironment());
				heuristic.setHeuristic(rw);
				addInOpen(rw);
				//System.out.println("\tWEST");
			}
			
			Node rn = new Node(e);
			if(rn.getEnvironment().modify(r,Movement.NORTH) != null){
				r.moveNorth(rn.getEnvironment());
				heuristic.setHeuristic(rn);
				addInOpen(rn);
				//System.out.println("\tNORTH");
			}
			
			Node rs = new Node(e);
			if(rs.getEnvironment().modify(r,Movement.SOUTH) != null){
				r.moveSouth(rs.getEnvironment());
				heuristic.setHeuristic(rs);
				addInOpen(rs);
				//System.out.println("\tSOUTH");
			}			
		}
	}

	private void addInClose(Node current) {	
		close.add(current);
		open.remove(current);
	}
	
	private void addInOpen(Node node) {
		String s = getStringRepresentation(node.getEnvironment());
		if(! log.contains(s)){
			open.add(node);
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
