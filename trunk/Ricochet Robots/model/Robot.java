package model;


import java.awt.Color;


public class Robot implements Movable{
	
	private static int nextId = 0; 
	
	private Color color;
	private int id;
	
	public Robot(Color color){
		this.color = color;
		id = nextId++;
	}
		
	public void moveNorth(Environment env){
		Cell c = env.modify(this,Movement.NORTH);
		if(c.north)moveNorth(env);
	}
	
	public void moveEast(Environment env){
		Cell c = env.modify(this,Movement.EAST);
		if(c.east)moveEast(env);
	}
	
	public void moveSouth(Environment env){
		Cell c = env.modify(this,Movement.SOUTH);
		if(c != null && c.south)moveSouth(env);
	}
	
	public void moveWest(Environment env){
		Cell c = env.modify(this,Movement.WEST);
		if(c.west)moveWest(env);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
