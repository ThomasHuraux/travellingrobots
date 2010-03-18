package simulation;

import model.*;

import java.util.ArrayList;

public class AStar extends MotionPlanner{
	
	private ArrayList<Node> open;
	private ArrayList<Node> close;
	
	private Node current;
	
	private void init(Environment e){
		
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
	
}
