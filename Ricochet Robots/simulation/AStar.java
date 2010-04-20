package simulation;

import model.*;

import java.util.ArrayList;

public class AStar extends MotionPlanner{
	
	private int NbITER = 0;
	
	private ArrayList<Node> open;
	private ArrayList<Node> close;
	
	private int[][] distToTarget;
	private int[][] dirToTarget;
	
	private Node current;
	
	public void init(Environment e){
		
		open = new ArrayList<Node>();
		close = new ArrayList<Node>();
		
		distToTarget = new int[e.getGrid().getSize()][e.getGrid().getSize()];
		dirToTarget = new int[e.getGrid().getSize()][e.getGrid().getSize()];
		
		current = new Node();
		
		current.BLUE = e.getPositions().get(0);
		current.GREEN = e.getPositions().get(1);
		current.RED = e.getPositions().get(2);
		current.YELLOW = e.getPositions().get(3);
			
		addInOpen(current);
		addInClose(current);
		addNewNodes(current);
	}

	public Sequence search(Environment e){
		init(e);
		while( !isFinal(current,e) && !open.isEmpty() ){
			current = best(open);
			addInClose(current);
			addNewNodes(current);
		}
		if( isFinal(current,e) )
	        return getSequence();
		return null;
	}

	private Sequence getSequence() {
		return null;
	}

	private void addNewNodes(Node current) {
	}

	private void addInClose(Node current) {	
		close.add(current);
		open.remove(current);
	}
	
	private void addInOpen(Node current) {
		open.add(current);
	}

	private Node best(ArrayList<Node> open) {
		return null;
	}

	private boolean isFinal(Node n, Environment e){
		return ( e.getTarget() == n.BLUE || e.getTarget() == n.RED || e.getTarget() == n.GREEN || e.getTarget() == n.YELLOW );
	}
	
	public void preCalc(Environment e){
		Position current = e.getTarget();
		Cell c = e.getGrid().getCell(current);
		if(c.north) expand(current,Movement.NORTH,1,e);
		if(c.east) expand(current,Movement.EAST,1,e);
		if(c.south) expand(current,Movement.SOUTH,1,e);
		if(c.west) expand(current,Movement.WEST,1,e);
	}
	
	private void mark(Position p, int m, int g){
		if(distToTarget[p.getX()][p.getY()]==0 || distToTarget[p.getX()][p.getY()] > g){
			distToTarget[p.getX()][p.getY()] = g;
			dirToTarget[p.getX()][p.getY()] = m;
		}		
	}
	
	private void expand(Position current,int movement, int generation,Environment e){
		
		NbITER++;
		
		if(generation > 15) return;
		
		System.out.println(current);
		
		Cell cellC = e.getGrid().getCell(current);		
		Position next;
		
		switch(movement){
			case Movement.NORTH :
				mark(current,Movement.SOUTH,generation);
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
				mark(current,Movement.WEST,generation);
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
				mark(current,Movement.NORTH,generation);
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
				mark(current,Movement.EAST,generation);
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
		
		if(c.north && movement!=Movement.SOUTH){
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
		}
		
		return pos;
	}

	public int[][] getDistToTarget() {
		return distToTarget;
	}

	public int[][] getDirToTarget() {
		return dirToTarget;
	}	
	
}
