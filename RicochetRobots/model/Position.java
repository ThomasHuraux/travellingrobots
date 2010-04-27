package model;

public class Position {

	protected int x;
	protected int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position position) {
		this.x = position.getX();
		this.y = position.getY();
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public boolean compare(Position p){
		return (p.x == x)&&(p.y == y);
	}
	
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}
	
	
}
