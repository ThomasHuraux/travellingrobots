package model;

public class State {

	protected Robot robot;
	protected Position position;
	
	public State(Robot robot, Position position) {
		super();
		this.robot = robot;
		this.position = position;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}	
}
