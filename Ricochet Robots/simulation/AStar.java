package simulation;

import model.*;

import java.awt.Color;
import java.util.ArrayList;

public class AStar extends MotionPlanner{
	
	private int NbITER = 0;
	private static final int MAXPRECALC = 15;
	
	private ArrayList<Environment> open;
	private ArrayList<Environment> close;
	
	private int[][] heuristic;
	private int[][] distToTarget;
	private int[][] dirToTarget;
	
	Robot red = new Robot(Color.RED);
	Robot green = new Robot(Color.GREEN);
	Robot yellow = new Robot(Color.YELLOW);
	Robot blue = new Robot(Color.BLUE);
		
	ArrayList<Robot> robots = new ArrayList<Robot>();
	
	private Environment current;
	
	public void init(Environment e){
		
		robots.add(red);
		robots.add(green);
		robots.add(yellow);
		robots.add(blue);
		
		e.addRobot(red,new Position(14,0));
		e.addRobot(green,new Position(0,11));
		e.addRobot(yellow,new Position(5,13));
		e.addRobot(blue,new Position(5,7));
		
		//Position target = new Position(9,2);
		Position target = new Position(9,8);
		//Position target = env.randomPosition();
		e.addTarget(red, target);
		
		open = new ArrayList<Environment>();
		close = new ArrayList<Environment>();
		
		int size = e.getGrid().getSize();
		heuristic = new int[size][size];
		distToTarget = new int[size][size];
		for(int x = 0;x<size;x++)	
			for(int y = 0;y<size;y++){
				heuristic[x][y] = Integer.MAX_VALUE;
				distToTarget[x][y] = Integer.MAX_VALUE;
			}
		
		dirToTarget = new int[size][size];		
		current = e;	
		addInOpen(e);
		addInClose(e);
		addNewNodes(e);
	}

	public Sequence search(Environment e){
/*		init(e);
		while( !isFinal(e) && !open.isEmpty() ){
			current = best(open);
			addInClose(current);
			addNewNodes(current);
		}
		if( isFinal(e) )
	        return getSequence();*/
		return null;
	}

	private Sequence getSequence() {
		return null;
	}

	private void addNewNodes(Environment current){
/*		for(Robot r : robots){		
			Environment re = (Environment) current.clone();
			r.moveEast(re);
			addInOpen(re);
			
			Environment rw = current.clone();
			r.moveWest(rw);
			addInOpen(rw);
			
			Environment rn = current.clone();
			r.moveNorth(rn);
			addInOpen(rn);
			
			Environment rs = current.clone();
			r.moveSouth(rs);
			addInOpen(rs);
		}*/
	}

	private void addInClose(Environment current) {	
		close.add(current);
		open.remove(current);
	}
	
	private void addInOpen(Environment current) {
		open.add(current);
	}

	private Environment best(ArrayList<Environment> open) {
		return null;
	}

/*	private boolean isFinal(Environment e){
		return ( e.getGrid().getCell(e.getTarget())).isFillWithGoodOne();
	}
	
	public void preCalc(Environment e){
		Position current = e.getTarget();
		Cell c = e.getGrid().getCell(current);
		if(c.north && (!c.south || (c.south && !e.getGrid().getCell(new Position(current.getX(),current.getY()+1)).isEmpty())))
			expand(current,Movement.NORTH,1,e);
		if(c.east && (!c.west || (c.west && !e.getGrid().getCell(new Position(current.getX()-1,current.getY())).isEmpty())))
			expand(current,Movement.EAST,1,e);
		if(c.south && (!c.north || (c.north && !e.getGrid().getCell(new Position(current.getX(),current.getY()-1)).isEmpty())))
			expand(current,Movement.SOUTH,1,e);
		if(c.west && (!c.east || (c.east && !e.getGrid().getCell(new Position(current.getX()+1,current.getY())).isEmpty())))
			expand(current,Movement.WEST,1,e);
		System.out.println("Pre-calculation terminated ("+NbITER+" steps)");
	}*/
	
	private boolean mark(Position p, int m, int g, Environment e){
		if(distToTarget[p.getX()][p.getY()] > g){
			distToTarget[p.getX()][p.getY()] = g;
			dirToTarget[p.getX()][p.getY()] = m;
			distToWall(m,p,e);
			return true;
		}
		return false;
	}
	
	private void expand(Position current,int movement, int generation,Environment e){
		
/*		NbITER++;
		
		if(generation > MAXPRECALC) return;
		
		Cell cellC = e.getGrid().getCell(current);		
		Position next;
		
		switch(movement){
			case Movement.NORTH :
				mark(current,Movement.SOUTH,generation,e);
				next = new Position(current.getX(),current.getY()-1);
				
				if(cellC.north && e.getGrid().getCell(next).isEmpty())
					expand(next,Movement.NORTH,generation,e);
				
				if(cellC.east 
						&& (!cellC.west || (cellC.west && !e.getGrid().getCell(new Position(current.getX()-1,current.getY())).isEmpty()))
						&& e.getGrid().getCell(new Position(current.getX()+1,current.getY())).isEmpty())
					expand(new Position(current.getX()+1,current.getY()),Movement.EAST,++generation,e);
				
				if(cellC.west 
						&& (!cellC.east || (cellC.east && !e.getGrid().getCell(new Position(current.getX()+1,current.getY())).isEmpty())) 
						&& e.getGrid().getCell(new Position(current.getX()-1,current.getY())).isEmpty())
					expand(new Position(current.getX()-1,current.getY()),Movement.WEST,++generation,e);
				
				break;
			case Movement.EAST :
				mark(current,Movement.WEST,generation,e);
				next = new Position(current.getX()+1,current.getY());
				
				if(cellC.east && e.getGrid().getCell(next).isEmpty())
					expand(next,Movement.EAST,generation,e);			
				
				if(cellC.north 
						&& (!cellC.south || (cellC.south && !e.getGrid().getCell(new Position(current.getX(),current.getY()+1)).isEmpty())) 
						&& e.getGrid().getCell(new Position(current.getX(),current.getY()-1)).isEmpty())
					expand(new Position(current.getX(),current.getY()-1),Movement.NORTH,++generation,e);
				
				if(cellC.south 
						&& (!cellC.north || (cellC.north && !e.getGrid().getCell(new Position(current.getX(),current.getY()-1)).isEmpty()))  
						&& e.getGrid().getCell(new Position(current.getX(),current.getY()+1)).isEmpty())
					expand(new Position(current.getX(),current.getY()+1),Movement.SOUTH,++generation,e);
				
				break;
			case Movement.SOUTH :
				mark(current,Movement.NORTH,generation,e);
				next = new Position(current.getX(),current.getY()+1);
				
				if(cellC.south && e.getGrid().getCell(next).isEmpty())
					expand(next,Movement.SOUTH,generation,e);
				
				if(cellC.east 
						&& (!cellC.west || (cellC.west && !e.getGrid().getCell(new Position(current.getX()-1,current.getY())).isEmpty()))
						&& e.getGrid().getCell(new Position(current.getX()+1,current.getY())).isEmpty())
					expand(new Position(current.getX()+1,current.getY()),Movement.EAST,++generation,e);
				
				if(cellC.west 
						&& (!cellC.east || (cellC.east && !e.getGrid().getCell(new Position(current.getX()+1,current.getY())).isEmpty())) 
						&& e.getGrid().getCell(new Position(current.getX()-1,current.getY())).isEmpty())
					expand(new Position(current.getX()-1,current.getY()),Movement.WEST,++generation,e);
				
				break;
			case Movement.WEST :
				mark(current,Movement.EAST,generation,e);
				next = new Position(current.getX()-1,current.getY());
				
				if(cellC.west && e.getGrid().getCell(next).isEmpty())
					expand(next,Movement.WEST,generation,e);
				
				if(cellC.north 
						&& (!cellC.south || (cellC.south && !e.getGrid().getCell(new Position(current.getX(),current.getY()+1)).isEmpty())) 
						&& e.getGrid().getCell(new Position(current.getX(),current.getY()-1)).isEmpty())
					expand(new Position(current.getX(),current.getY()-1),Movement.NORTH,++generation,e);
				
				if(cellC.south 
						&& (!cellC.north || (cellC.north && !e.getGrid().getCell(new Position(current.getX(),current.getY()-1)).isEmpty()))  
						&& e.getGrid().getCell(new Position(current.getX(),current.getY()+1)).isEmpty())
					expand(new Position(current.getX(),current.getY()+1),Movement.SOUTH,++generation,e);
				
				break;
		}*/
	}
	
	private void distToWall(int move,Position pos,Environment e){
		Position current = pos;
		int h = distToTarget[pos.getX()][pos.getY()];
		heuristic[current.getX()][current.getY()] = h;
		int count;
		switch(move){
			case Movement.NORTH:
			case Movement.SOUTH:
				count = 1;
				while(e.getGrid().getCell(current).east){
					current = new Position(current.getX()+1,current.getY());
					if(heuristic[current.getX()][current.getY()] > h+count)
						heuristic[current.getX()][current.getY()] = h+(++count);
				}
				count = 1;
				current = pos;
				while(e.getGrid().getCell(current).west){
					current = new Position(current.getX()-1,current.getY());
					if(heuristic[current.getX()][current.getY()] > h+count)
						heuristic[current.getX()][current.getY()] = h+(++count);
				}
				break;
			case Movement.EAST:
			case Movement.WEST:
				count = 1;
				while(e.getGrid().getCell(current).north){
					current = new Position(current.getX(),current.getY()-1);
					if(heuristic[current.getX()][current.getY()] > h+count)
						heuristic[current.getX()][current.getY()] = h+(++count);
				}
				count = 1;
				current = pos;
				while(e.getGrid().getCell(current).south){
					current = new Position(current.getX(),current.getY()+1);
					if(heuristic[current.getX()][current.getY()] > h+count)
						heuristic[current.getX()][current.getY()] = h+(++count);
				}
				break;
		}
	}
	
	public int getNbITER() {
		return NbITER;
	}

	private void fillLine(Position pos1, Position pos2, int[][] tab, int num){
		if(pos1.getX() == pos2.getX()){
			if(pos1.getY() > pos2.getY())
				for(int i = pos2.getY();i<pos1.getY();i++)
					tab[pos1.getX()][i] = num;
			else
				for(int i = pos1.getY();i<pos2.getY();i++)
					tab[pos1.getX()][i] = num;
		}
		if(pos1.getY() == pos2.getY()){
			if(pos1.getX() > pos2.getX())
				for(int i = pos2.getX();i<pos1.getX();i++)
					tab[i][pos1.getY()] = num;
			else
				for(int i = pos1.getX();i<pos2.getX();i++)
					tab[i][pos1.getY()] = num;
		}
	}
	
	private ArrayList<Position> getAccessible(Position p, int movement, Environment e){
		Cell c = e.getGrid().getCell(p);
		ArrayList<Position> pos = new ArrayList<Position>();
		
		Position next;
		
	/*	if(c.north && movement!=Movement.SOUTH){
			next = new Position(p.getX(),p.getY()-1);
			if(e.getGrid().getCell(next).isEmpty())
				pos.add(next);
		}
		if(c.east && movement!=Movement.WEST){
			next = new Position(p.getX()+1,p.getY());
			if(e.getGrid().getCell(next).isEmpty())
				pos.add(next);
		}
		if(c.south && movement!=Movement.NORTH){
			next = new Position(p.getX(),p.getY()+1);
			if(e.getGrid().getCell(next).isEmpty())
				pos.add(next);
		}
		if(c.west && movement!=Movement.EAST){
			next = new Position(p.getX()-1,p.getY());
			if(e.getGrid().getCell(next).isEmpty())
				pos.add(next);
		}*/
		
		return pos;
	}

	public int[][] getHeuristic() {
		return heuristic;
	}

	public int[][] getDirToTarget() {
		return dirToTarget;
	}	
	
}
