package simulation;

import java.util.ArrayList;

import model.Cell;
import model.Environment;
import model.Movement;
import model.Position;
import model.State;

public class CorridorHeuristic implements Heuristic{
	
	public static final CorridorHeuristic DEFAULT = new CorridorHeuristic();
	
	private static final int MAXNOPRECALC = 100;
	private static final int MAXPRECALC = 15;
	
	public int NbITER = 0;
	
	int[][] heuristic;
	int[][] distToTarget;
	int[][] dirToTarget;
	
	private void init(int size){
		heuristic = new int[size][size];
		distToTarget = new int[size][size];
		for(int x = 0;x<size;x++)	
			for(int y = 0;y<size;y++){
				heuristic[x][y] = MAXNOPRECALC;
				distToTarget[x][y] = MAXNOPRECALC;
			}
		
		dirToTarget = new int[size][size];
	}

	public Node best(Node current,ArrayList<Node> open) {
		
		init(current.getEnvironment().getGrid().getSize());
		
		int idMin = 0;
		int valMin = MAXNOPRECALC;
		
		int idMinH = 0;
		int valMinH = MAXNOPRECALC;
		
		int id = 0;
		
		preCalc(current.getEnvironment());
		
		for(Node n : open){
			Environment e = n.getEnvironment();
			for(State s : e.getStates()){
				if(s.getRobot()==e.getTagged()){
					if(distToTarget[s.getPosition().getX()][s.getPosition().getY()] < valMin){
						valMin = distToTarget[s.getPosition().getX()][s.getPosition().getY()];
						idMin = id;
					}
					if(heuristic[s.getPosition().getX()][s.getPosition().getY()] < valMinH){
						valMinH = heuristic[s.getPosition().getX()][s.getPosition().getY()];
						idMinH = id;
					}
				}
			}
			id++;
		}
		
		if(valMin == MAXNOPRECALC)
			idMin = idMinH;
		
		//System.out.println("Best choice ("+open.size()+" in open) : "+idMin+" (Heuristic="+valMin+") [Current cost : "+NbITER+"]");
		
		return open.get(idMin);
	}
	
	public void preCalc(Environment e){
		NbITER++;
			
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
		
		if(generation > MAXPRECALC) return;
		
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
