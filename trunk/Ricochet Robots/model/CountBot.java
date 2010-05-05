package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CountBot extends Robot implements Ghost{
	
	// The ghost robot who count proximity to target
	
	private static final int MAXPROXIMITY = Integer.MAX_VALUE;
	private static final int MAXGENERATION = 10;
	
	private static int[][] proximity;
	private List<Position> stopListe;
	
	private int generation;
	private int lastMove;
	
	public CountBot(Environment env, Position target){
		super(Color.GRAY);
		this.stopListe = new ArrayList<Position>();
		this.generation = 0;
		lastMove = -1;
		init(env);
		State s = null;
		if(! env.isEmpty(target)){
			s = env.getState(target);
			env.getStates().remove(s);
		}
		env.getStates().add(new State(this,target));
		proliferate(env);
		if(s != null)
			env.getStates().add(s);
	}
	
	private CountBot(int generation, Environment env){
		super();
		this.generation = generation;
		
		Position pos = env.getState(this).getPosition();
		stopListe.add(pos);
	}

	private void init(Environment env){
		int size = env.getGrid().getSize();
		proximity = new int[size][size];
		for(int x = 0;x<size;x++)	
			for(int y = 0;y<size;y++) {
				proximity[x][y] = MAXPROXIMITY;
			}
	}
	
	private void proliferate(Environment env){
		
		Position pos = env.getState(this).position;
		Cell c = env.getGrid().getCell(pos);
		
		update(env);
		
		// Lancement des Countbot.
		if(c.north && Movement.SOUTH != lastMove)
			replicateOnNorth(env,pos);
		if(c.east && Movement.WEST != lastMove)
			replicateOnEast(env,pos);
		if(c.south && Movement.NORTH != lastMove)
			replicateOnSouth(env,pos);
		if(c.west && Movement.EAST != lastMove)
			replicateOnWest(env,pos);
		
		// Marquage des changements de direction.
		if (!c.north && Movement.SOUTH == lastMove || !c.east && Movement.WEST == lastMove || !c.south && Movement.NORTH == lastMove || !c.west && Movement.EAST == lastMove)
			stopListe.add(new Position(pos.getX(), pos.getY()));
		
		env.getStates().remove(env.getState(this));
	}
	
	private void update(Environment env){
		Position pos = env.getState(this).position;
		int v = proximity[pos.getX()][pos.getY()];
		if(v > generation)
			proximity[pos.getX()][pos.getY()] = generation;
	}

	private void replicateOnNorth(Environment env, Position pos){
		CountBot baby = createBabyBot(env,new Position(pos.getX(),pos.getY()-1));
		if(baby != null){
			baby.moveNorth(env);
			baby.setLastMove(Movement.NORTH);
			if(generation < MAXGENERATION)
				baby.proliferate(env);
		}
	}
	
	private void replicateOnEast(Environment env, Position pos){
		CountBot baby = createBabyBot(env,new Position(pos.getX()+1,pos.getY()));
		if(baby != null){
			baby.moveEast(env);
			baby.setLastMove(Movement.EAST);
			if(generation < MAXGENERATION)
				baby.proliferate(env);
		}
	}
	
	private void replicateOnSouth(Environment env, Position pos){
		CountBot baby = createBabyBot(env,new Position(pos.getX(),pos.getY()+1));
		if(baby != null){
			baby.moveSouth(env);
			baby.setLastMove(Movement.SOUTH);
			if(generation < MAXGENERATION)
				baby.proliferate(env);
		}
	}
	
	private void replicateOnWest(Environment env, Position pos){
		CountBot baby = createBabyBot(env,new Position(pos.getX()-1,pos.getY()));
		if(baby != null){
			baby.moveWest(env);
			baby.setLastMove(Movement.WEST);
			if(generation < MAXGENERATION)
				baby.proliferate(env);
		}
	}		
	
	public void moveNorth(Environment env){
		update(env);
		super.moveNorth(env);
	}
	
	public void moveEast(Environment env){
		update(env);
		super.moveEast(env);
	}
	
	public void moveSouth(Environment env){
		update(env);
		super.moveSouth(env);
	}
	
	public void moveWest(Environment env){
		update(env);
		super.moveWest(env);	
	}
	
	private CountBot createBabyBot(Environment env,Position pos){
		if(env.isEmpty(pos)){
			CountBot baby = new CountBot(generation+1, env);
			env.addRobot(baby,pos);
			return baby; 
		}
		return null;		
	}
	
	public int getLastMove() {
		return lastMove;
	}

	public void setLastMove(int lastMove) {
		this.lastMove = lastMove;
	}

	public int[][] getProximity(){
		return proximity;
	}

	public List<Position> getStopListe() {
		return stopListe;
	}
}
