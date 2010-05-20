package model;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class CountBot extends Robot implements Ghost{

	// The ghost robot who count proximity to target

	private static final int MAXPROXIMITY = Integer.MAX_VALUE;
//	private static int MAXGENERATION = 10;

	private static int[][] proximity;
	private static Set<Position> stopListe;

	private int generation;
	private int lastMove;

	public CountBot(Environment env, Position target, int[][] prox){
		super(Color.GRAY);
		stopListe = new HashSet<Position>();
		this.generation = 0;
		lastMove = -1;
		
		if (prox == null)
			init(env);
		else
			proximity = prox;
		
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

	private CountBot(int generation, Environment env, Set<Position> stops){
		super();
		this.generation = generation;
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
		for (int i = 1; i < 5; i++)
			if (c.getWall(i) && i != lastMove)
				replicateOnDirection(i,env,pos);
		
		// Marquage des changements de direction.
		if (!c.north && Movement.NORTH == lastMove || !c.east && Movement.EAST == lastMove || !c.south && Movement.SOUTH == lastMove || !c.west && Movement.WEST == lastMove)
			stopListe.add(pos);

		env.getStates().remove(env.getState(this));
	}

	private void update(Environment env){
		Position pos = env.getState(this).position;
		int v = proximity[pos.getX()][pos.getY()];
		if(v > generation)
			proximity[pos.getX()][pos.getY()] = generation;
	}

	private void replicateOnDirection(int i, Environment env, Position pos) {
		Position p = null;
		
		switch (i) {
		case 1 : p = new Position(pos.getX(),pos.getY()-1); break;
		case 2 : p = new Position(pos.getX()+1,pos.getY()); break;
		case 3 : p = new Position(pos.getX(),pos.getY()+1); break;
		default : p = new Position(pos.getX()-1,pos.getY()); break;
		}
		
		CountBot baby = createBabyBot(env,p);
		if(baby != null){
			baby.move(i, env);
			baby.setLastMove(Movement.NORTH);
			baby.proliferate(env);
		}
	}         
	
	public void move(int i, Environment env) {
		update(env);
		super.move(i, env);
	}

	private CountBot createBabyBot(Environment env,Position pos){
		if(env.isEmpty(pos) && (generation+1) < proximity[pos.getX()][pos.getY()]){
			CountBot baby = new CountBot(generation+1, env, stopListe);
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

	public Set<Position> getStopListe() {
		return stopListe;
	}

//	public static void setMAXGENERATION(int mAXGENERATION) {
//		MAXGENERATION = mAXGENERATION;
//	}

}
