package simulation;

import java.util.ArrayList;

import model.Environment;

public interface Heuristic {
	
	public Environment best(Environment current, ArrayList<Environment> open);
	public int getNbITER();

}
