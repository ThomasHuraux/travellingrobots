package model;


public interface Movable {
	
	public abstract void moveNorth(Environment env);	
	public abstract void moveEast(Environment env);	
	public abstract void moveSouth(Environment env);
	public abstract void moveWest(Environment env);

}
