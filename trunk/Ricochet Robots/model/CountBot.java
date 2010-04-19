package model;

import java.awt.Color;

public class CountBot extends Robot implements Ghost{
	
	// The ghost robot who count proximity to target
	
	private static final int MAXPROXIMITY = Integer.MAX_VALUE;
	private static final int MAXGENERATION = 10;
	
	private static int[][] proximity;
	
	private int generation;
	private int lastMove;
	
	public CountBot(Environment env, Position target){
		super(Color.GRAY);
		this.generation = 0;
		lastMove = -1;
		init(env);
		Robot r = null;
		if(! env.getGrid().getCell(target).isEmpty()){
			r = env.getGrid().getCell(target).getRobot();
			env.getGrid().getCell(target).clean();
		}
		env.addRobot(this,target);
		proliferate(env);
		if(r != null)
			env.getGrid().getCell(target).fill(r);		
	}
	
	private CountBot(int generation){
		super();
		this.generation = generation;
	}

	private void init(Environment env){
		int size = env.getGrid().getSize();
		proximity = new int[size][size];
		for(int x = 0;x<size;x++)	
			for(int y = 0;y<size;y++)
				proximity[x][y] = MAXPROXIMITY;
	}
	
	private void proliferate(Environment env){
		
		Position pos = env.getPosition(this);
		Cell c = env.getGrid().getCell(pos);
		
		update(env);
		
		if(c.north && Movement.SOUTH != lastMove)
			replicateOnNorth(env,pos);
		if(c.east && Movement.WEST != lastMove)
			replicateOnEast(env,pos);
		if(c.south && Movement.NORTH != lastMove)
			replicateOnSouth(env,pos);
		if(c.west && Movement.EAST != lastMove)
			replicateOnWest(env,pos);
		
	}
	
	private void update(Environment env){
		Position pos = env.getPosition(this);
		int v = proximity[pos.getX()][pos.getY()];
		if(v > generation)			
			proximity[pos.getX()][pos.getY()] = generation;
	}
	
	public void replicateOnNorth(Environment env, Position pos){
		CountBot baby = createBabyBot(env,new Position(pos.getX(),pos.getY()-1));
		if(baby != null){
			baby.moveNorth(env);
			baby.setLastMove(Movement.NORTH);
			if(generation < MAXGENERATION)
				baby.proliferate(env);
		}
	}
	
	public void replicateOnEast(Environment env, Position pos){
		CountBot baby = createBabyBot(env,new Position(pos.getX()+1,pos.getY()));
		if(baby != null){
			baby.moveEast(env);
			baby.setLastMove(Movement.EAST);
			if(generation < MAXGENERATION)
				baby.proliferate(env);
		}
	}
	
	public void replicateOnSouth(Environment env, Position pos){
		CountBot baby = createBabyBot(env,new Position(pos.getX(),pos.getY()+1));
		if(baby != null){
			baby.moveSouth(env);
			baby.setLastMove(Movement.SOUTH);
			if(generation < MAXGENERATION)
				baby.proliferate(env);
		}
	}
	
	public void replicateOnWest(Environment env, Position pos){
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
		if(env.getGrid().getCell(pos).isEmpty()){
			CountBot baby = new CountBot(generation+1);
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
}
