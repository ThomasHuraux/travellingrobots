package model;

public class Cell {

	public boolean north;
	public boolean east;
	public boolean south;
	public boolean west;
	protected int type;
	
	public Cell(boolean noMaze){
		accessible(noMaze);
	}
	
	public Cell(boolean north, boolean east, boolean south, boolean west){
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
	
	public boolean getWall(int i) {
		switch (i) {
		case 1 : return north;
		case 2 : return east;
		case 3 : return south;
		default : return west;
		}
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
	
	public int getNbWall(){
		int nb = 0;
		if(! north) nb++;
		if(! east) nb++;
		if(! south) nb++;
		if(! west) nb++;
		return nb;
	}

	@Override
	public String toString() {
		return "Cell [east=" + east + ", north=" + north + ", south=" + south
				+ ", type=" + type + ", west=" + west + "]";
	}

}
