package simulation;

import java.util.ArrayList;

import model.Cell;
import model.Environment;
import model.Movement;
import model.Position;

public class CorridorHeuristic implements Heuristic{
	
	public static final CorridorHeuristic DEFAULT = new CorridorHeuristic();

	private static final int MAXNOPRECALC = Integer.MAX_VALUE;
	public static int PRECALCDEPTH = 15;
	public static double COSTIMPORTANCE = 0.01;
	
	public int NbITER = 0;
	
	int[][] heuristic;
	int[][] distToTarget;
	int[][] dirToTarget;
	
	private void init(int size){
		
		if(heuristic == null){
			heuristic = new int[size][size];
			distToTarget = new int[size][size];
			dirToTarget = new int[size][size];
		}
		for(int x = 0;x<size;x++)	
			for(int y = 0;y<size;y++){
				heuristic[x][y] = MAXNOPRECALC;
				distToTarget[x][y] = MAXNOPRECALC;
			}

	}

	public Node best(Node current,ArrayList<Node> open) {
		
		int idMin = 0;
		int valMin = MAXNOPRECALC;
		
		int idMinH = 0;
		int valMinH = MAXNOPRECALC;
		
		for(int i = 0; i<open.size(); i++){
			
			Node n = open.get(i);
			
			int c_plus_h = (int)(n.getCost()*COSTIMPORTANCE) + n.getHeuristic();
			int c_plus_d = (int)(n.getCost()*COSTIMPORTANCE) + n.getDistToTarget();


			if(c_plus_d < valMin){
				valMin = c_plus_d ;
				idMin = i;
			}
			if(c_plus_h < valMinH){
				valMinH = c_plus_h;
				idMinH = i;
			}
		}

		Node choice;
		if(valMin != MAXNOPRECALC){
			choice = open.get(idMin);
			open.clear();
		}
		else choice = open.get(idMinH);	
	
		return choice;
	}
	
	public void preCalc(Node n){
		Environment e = n.getEnvironment();
		init(e.getGrid().getSize());
			
		Position current = e.getTarget();
		Cell c = e.getGrid().getCell(current);
		if(c.north && (!c.south || (c.south && !e.isEmpty(new Position(current.getX(),current.getY()+1)))))
			expand(current,Movement.NORTH,1,e);
		if(c.east && (!c.west || (c.west && !e.isEmpty(new Position(current.getX()-1,current.getY())))))
			expand(current,Movement.EAST,1,e);
		if(c.south && (!c.north || (c.north && !e.isEmpty(new Position(current.getX(),current.getY()-1)))))
			expand(current,Movement.SOUTH,1,e);
		if(c.west && (!c.east || (c.east && !e.isEmpty(new Position(current.getX()+1,current.getY())))))
			expand(current,Movement.WEST,1,e);
	}
	
	public void setHeuristic(Node n){
		Position posTaggedRobot = n.getEnvironment().getState(n.getEnvironment().getTagged()).getPosition();
		n.setHeuristic(heuristic[posTaggedRobot.getX()][posTaggedRobot.getY()]);
		n.setDistToTarget(distToTarget[posTaggedRobot.getX()][posTaggedRobot.getY()]);
	}
	
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
		
		if(generation > PRECALCDEPTH) return;
		
		Cell cellC = e.getGrid().getCell(current);		
		Position next;
		
		switch(movement){
			case Movement.NORTH :
				mark(current,Movement.SOUTH,generation,e);
				next = new Position(current.getX(),current.getY()-1);
				
				if(cellC.north && e.isEmpty(next))
					expand(next,Movement.NORTH,generation,e);
				
				if(cellC.east 
						&& (!cellC.west || (cellC.west && !e.isEmpty(new Position(current.getX()-1,current.getY()))))
						&& e.isEmpty(new Position(current.getX()+1,current.getY())))
					expand(new Position(current.getX()+1,current.getY()),Movement.EAST,++generation,e);
				
				if(cellC.west 
						&& (!cellC.east || (cellC.east && !e.isEmpty(new Position(current.getX()+1,current.getY())))) 
						&& e.isEmpty(new Position(current.getX()-1,current.getY())))
					expand(new Position(current.getX()-1,current.getY()),Movement.WEST,++generation,e);
				
				break;
			case Movement.EAST :
				mark(current,Movement.WEST,generation,e);
				next = new Position(current.getX()+1,current.getY());
				
				if(cellC.east && e.isEmpty(next))
					expand(next,Movement.EAST,generation,e);			
				
				if(cellC.north 
						&& (!cellC.south || (cellC.south && !e.isEmpty(new Position(current.getX(),current.getY()+1)))) 
						&& e.isEmpty(new Position(current.getX(),current.getY()-1)))
					expand(new Position(current.getX(),current.getY()-1),Movement.NORTH,++generation,e);
				
				if(cellC.south 
						&& (!cellC.north || (cellC.north && !e.isEmpty(new Position(current.getX(),current.getY()-1))))  
						&& e.isEmpty(new Position(current.getX(),current.getY()+1)))
					expand(new Position(current.getX(),current.getY()+1),Movement.SOUTH,++generation,e);
				
				break;
			case Movement.SOUTH :
				mark(current,Movement.NORTH,generation,e);
				next = new Position(current.getX(),current.getY()+1);
				
				if(cellC.south && e.isEmpty(next))
					expand(next,Movement.SOUTH,generation,e);
				
				if(cellC.east 
						&& (!cellC.west || (cellC.west && !e.isEmpty(new Position(current.getX()-1,current.getY()))))
						&& e.isEmpty(new Position(current.getX()+1,current.getY())))
					expand(new Position(current.getX()+1,current.getY()),Movement.EAST,++generation,e);
				
				if(cellC.west 
						&& (!cellC.east || (cellC.east && !e.isEmpty(new Position(current.getX()+1,current.getY())))) 
						&& e.isEmpty(new Position(current.getX()-1,current.getY())))
					expand(new Position(current.getX()-1,current.getY()),Movement.WEST,++generation,e);
				
				break;
			case Movement.WEST :
				mark(current,Movement.EAST,generation,e);
				next = new Position(current.getX()-1,current.getY());
				
				if(cellC.west && e.isEmpty(next))
					expand(next,Movement.WEST,generation,e);
				
				if(cellC.north 
						&& (!cellC.south || (cellC.south && !e.isEmpty(new Position(current.getX(),current.getY()+1)))) 
						&& e.isEmpty(new Position(current.getX(),current.getY()-1)))
					expand(new Position(current.getX(),current.getY()-1),Movement.NORTH,++generation,e);
				
				if(cellC.south 
						&& (!cellC.north || (cellC.north && !e.isEmpty(new Position(current.getX(),current.getY()-1))))  
						&& e.isEmpty(new Position(current.getX(),current.getY()+1)))
					expand(new Position(current.getX(),current.getY()+1),Movement.SOUTH,++generation,e);
				
				break;
		}
	}
	
	public int distToWallEast(Position p,Environment e){
		int count = 1;
		Position current = p;
		while(e.getGrid().getCell(current).east){
			current = new Position(current.getX()+1,current.getY());
			count++;
			if(count > 3)
				return -1;
		}
		return count;
	}
	
	public int distToWallSouth(Position p,Environment e){
		int count = 1;
		Position current = p;
		while(e.getGrid().getCell(current).south){
			current = new Position(current.getX(),current.getY()+1);
			count++;
			if(count > 3)
				return -1;
		}
		return count;
	}
	
	public int distToWallWest(Position p,Environment e){
		int count = 1;
		Position current = p;
		while(e.getGrid().getCell(current).west){
			current = new Position(current.getX()-1,current.getY());
			count++;
			if(count > 3)
				return -1;
		}
		return count;
	}
	
	public int distToWallNorth(Position p,Environment e){
		int count = 1;
		Position current = p;
		while(e.getGrid().getCell(current).north){
			current = new Position(current.getX(),current.getY()-1);
			count++;
			if(count > 3)
				return -1;
		}
		return count;
	}
	
	private void distToWall(int move,Position pos,Environment e){
		Position current = pos;
		int h = distToTarget[pos.getX()][pos.getY()];
		heuristic[current.getX()][current.getY()] = h;
		int dist;
		switch(move){
			case Movement.NORTH:
			case Movement.SOUTH:
				dist = distToWallWest(pos,e);
				if(dist != -1)
					while(e.getGrid().getCell(current).east){
						current = new Position(current.getX()+1,current.getY());
						if(heuristic[current.getX()][current.getY()] > h+dist)
							heuristic[current.getX()][current.getY()] = h+dist;
					}
				dist = distToWallEast(pos,e);
				current = pos;
				if(dist != -1)
					while(e.getGrid().getCell(current).west){
						current = new Position(current.getX()-1,current.getY());
						if(heuristic[current.getX()][current.getY()] > h+dist)
							heuristic[current.getX()][current.getY()] = h+dist;
					}
				break;
			case Movement.EAST:
			case Movement.WEST:
				dist = distToWallNorth(pos,e);
				if(dist != -1)
					while(e.getGrid().getCell(current).south){
						current = new Position(current.getX(),current.getY()+1);
						if(heuristic[current.getX()][current.getY()] > h+dist)
							heuristic[current.getX()][current.getY()] = h+dist;
					}
				dist = distToWallSouth(pos,e);
				current = pos;
				if(dist != -1)
					while(e.getGrid().getCell(current).north){
						current = new Position(current.getX(),current.getY()-1);
						if(heuristic[current.getX()][current.getY()] > h+dist)
							heuristic[current.getX()][current.getY()] = h+dist;
					}
				break;
		}
	}
	

	public int[][] getHeuristic() {
		return heuristic;
	}

	public int[][] getDirToTarget() {
		return dirToTarget;
	}
	
	public int getNbITER() {
		return NbITER;
	}

}
