package simulation;

import model.Environment;

public class Node {
	
	protected int cost;
	protected int heuristic;
	protected int distToTarget;
	
	protected Node previous;
	
	protected Environment environment;

	
	public Node(Environment environment){
		this.environment = environment;
		this.cost = 0;
		this.heuristic = 0;
		this.previous = null;
		this.distToTarget = -1;
	}
	
	public Node(Node n) {
		this.environment = n.getEnvironment().clone();
		this.cost = n.getCost()+1;
		this.distToTarget = -1;
		this.previous = n;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public int getCost() {
		return cost;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public Node getPrevious() {
		return previous;
	}

	public int getDistToTarget() {
		return distToTarget;
	}

	public void setDistToTarget(int distToTarget) {
		this.distToTarget = distToTarget;
	}

	@Override
	public String toString() {
		return "Node [cost=" + cost + ", distToTarget=" + distToTarget
				+ ", heuristic=" + heuristic + "]";
	}

	
}
