package model;



public class Cell {

	protected boolean north;
	protected boolean east;
	protected boolean south;
	protected boolean west;
	protected int type;

	protected Robot robot;
	protected int mark;
	
	public Cell(boolean noMaze){
		accessible(noMaze);
	}
	
	public Cell(boolean north, boolean east, boolean south, boolean west){
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
	
	public boolean isEmpty(){
		return robot==null;
	}
	
	public void fill(Robot robot){
		this.robot = robot;
	}
	
	public void mark(int mark){
		this.mark = mark;
	}
	
	public void clean(){
		this.robot = null;
	}
	
	private void accessible(boolean noMaze){
		this.north =
		this.east =
		this.south =
		this.west = noMaze;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public Robot getRobot(){
		return robot;
	}

	@Override
	public String toString() {
		return "Cell [east=" + east + ", mark=" + mark + ", north=" + north
				+ ", robot=" + robot + ", south=" + south + ", type=" + type
				+ ", west=" + west + "]";
	}
	
	
	
}
