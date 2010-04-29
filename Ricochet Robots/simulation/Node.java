package simulation;

import model.Environment;
import model.Robot;

public class Node {
	
	protected Environment environment;
	protected int cost;
	protected Robot moveRobot;
	protected int moveType;
	
	public Node(Environment environment){
		this.environment = environment;
		this.cost = 0;
		this.moveRobot = null;
		this.moveType = -1;
	}
	
	public Node(Node n, Robot moveRobot, int moveType) {
		this.environment = n.getEnvironment().clone();
		this.cost = n.getCost()+1;
		this.moveRobot = moveRobot;
		this.moveType = moveType;
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

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Robot getMoveRobot() {
		return moveRobot;
	}

	public void setMoveRobot(Robot moveRobot) {
		this.moveRobot = moveRobot;
	}

	public int getMoveType() {
		return moveType;
	}

	public void setMoveType(int moveType) {
		this.moveType = moveType;
	}
	
}
