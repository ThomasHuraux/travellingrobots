package model;

public class Position {

	protected int x;
	protected int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
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
	public boolean equals(Object p) {
		Position pos = (Position) p;
		return this.compare(pos);
	}
	
	@Override
	public int hashCode() {
		return 2*x-3*y;
	}
	
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
	
	
}
