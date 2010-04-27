package model;


import java.util.ArrayList;
import java.util.Random;

public class Environment{
	
	protected static Grid grid;
	protected Position target;
	
	private Robot tagged;
	protected ArrayList<State> states;
	
	public Environment(){
		if(grid == null)
			this.setGrid(new Grid("../RicochetRobots/grids/Standard.txt"));
		states = new ArrayList<State>();
	}
	
	public Environment(Grid grid,ArrayList<Robot> robots){
		if(grid == null)
			this.setGrid(grid);
		for(Robot r : robots){
			while(! addRobotArbitrarly(r));
		}
	}
	
	public Environment clone() {
		Environment e = new Environment();
		e.addTarget(tagged,new Position(target.getX(),target.getY()));
		ArrayList<State> listes = new ArrayList<State>();
		for(State s : states)
			listes.add(new State(s.robot,new Position(s.position.getX(),s.position.getY())));
		e.setStates(listes);
		return e;
	}
	
	public Cell modify(Robot r, int move){
		State state = getState(r);
		Cell c = getGrid().getCell(state.position);
		Position pos = null;
		switch(move){
			case Movement.NORTH:
				if(c.north) pos = new Position(state.position.x,state.position.y-1);
				break;
			case Movement.EAST:
				if(c.east) pos = new Position(state.position.x+1,state.position.y);
				break;
			case Movement.SOUTH:
				if(c.south) pos = new Position(state.position.x,state.position.y+1);
				break;
			case Movement.WEST:
				if(c.west) pos = new Position(state.position.x-1,state.position.y);
				break;
		}
		if(pos==null || !isEmpty(pos))
			return null;
		
		state.position = pos;
		return getGrid().getCell(state.position);
	}
	
	public boolean addRobot(Robot r, Position p){			
		if( isEmpty(p)&& grid.getCell(p).getType()!=11){
			states.add(new State(r,p));			
			return true;
		}else
			return false;
	}
	
	public boolean addRobotArbitrarly(Robot r){
		return addRobot(r,randomPosition());
	}
	
	public boolean isEmpty(Position p){
		for(State s : states)
			if(s.position.compare(p) && !(s.robot instanceof Ghost))
					return false;
		return true;
	}
	
	public Robot getTaggedRobot() {
		return this.tagged;
	}
	
	public void setTaggedRobot(Robot tagged) {
		this.tagged = tagged;
	}
	
	public void addTarget(Robot r, Position p){
		this.target = p;
		this.tagged = r;
	}

	public Position randomPosition(){
		return new Position(random(getGrid()),random(getGrid()));
	}
	
	public static int random(Grid g){
		Random r = new Random();
		return r.nextInt(g.getSize()-1);
	}

	public Grid getGrid() {
		return grid;
	}
	
	@SuppressWarnings("static-access")
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Robot getTagged() {
		return tagged;
	}

	public void setTagged(Robot tagged) {
		this.tagged = tagged;
	}

	public State getState(Robot r){
		for(State s : states)
			if(s.robot.equals(r))
				return s;
		return null;
	}
	
	public State getState(Position p){
		for(State s : states)
			if(s.position.compare(p))
				return s;
		return null;
	}
	
	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> etats) {
		this.states = etats;
	}

	public Position getTarget(){
		return target;
	}
	
	public void setTarget(Position target) {
		this.target = target;
	}

}
