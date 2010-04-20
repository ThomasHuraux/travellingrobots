package model;


import java.util.ArrayList;
import java.util.Random;

public class Environment {
	
	private Grid grid;
	protected ArrayList<Position> positions;
	protected Position target;
	
	public Environment(){
		this.setGrid(new Grid("../RicochetRobots/grids/Standard.txt"));
		positions = new ArrayList<Position>();
	}
	
	public Environment(Grid grid,ArrayList<Robot> robots){
		this.setGrid(grid);
		for(Robot r : robots){
			while(! addRobotArbitrarly(r));
		}
	}
	
	public Cell modify(Robot r, int move){
		Position pos = getPosition(r);
		Position previous = pos;

		Cell c = getGrid().getCell(pos);
		switch(move){
			case Movement.NORTH:
				if(c.north) pos = new Position(pos.x,pos.y-1);
				break;
			case Movement.EAST:
				if(c.east) pos = new Position(pos.x+1,pos.y);
				break;
			case Movement.SOUTH:
				if(c.south) pos = new Position(pos.x,pos.y+1);
				break;
			case Movement.WEST:
				if(c.west) pos = new Position(pos.x-1,pos.y);
				break;
		}
		
		if(!getGrid().getCell(pos).isEmpty())
			return null;
		
		if(!(r instanceof Ghost)) {
			getGrid().getCell(previous).clean();
			getGrid().getCell(pos).fill(r);
		}
		
		positions.set(r.getId(),pos);
		return getGrid().getCell(pos);
	}
	
	public Position getPosition(Robot r){
		return positions.get(r.getId());
	}
	public boolean addRobotArbitrarly(Robot r){
		return addRobot(r,randomPosition());
	}
	
	public boolean addRobot(Robot r, Position p){
		Cell c = getGrid().getCell(p);
		if( c.isEmpty() || r instanceof Ghost && c.getType()!=11){
			r.setId(positions.size());
			positions.add(r.getId(),p);
			c.fill(r);
			
			////////
			System.out.println("Add new robot ("+p+")");
			
			return true;
		}else
			return false;
	}
	
	public void addTarget(Robot r, Position p){
		this.target = p;
		Cell c = getGrid().getCell(p);
		if (r != null)
			c.mark(r.getId());
	}
	
	public Position getTarget(){
		return target;
	}
	
	public Position randomPosition(){
		return new Position(random(getGrid()),random(getGrid()));
	}
	
	public static int random(Grid g){
		Random r = new Random();
		return r.nextInt(g.getSize()-1);
	}

	public ArrayList<Position> getPositions() {
		return positions;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Grid getGrid() {
		return grid;
	}

}
