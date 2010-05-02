package simulation;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.*;
import view.*;


public class AStarOneTest {
	
	static Sequence steps = new Sequence();
	static Environment current = new Environment();

	static JPanel all = new JPanel();

	public static void main(String[] args){
		
		CorridorHeuristic h =  CorridorHeuristic.DEFAULT;
		AStar algo = new AStar(h);
		
		steps = algo.search(current);

		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(new SolutionView(current,steps));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
	}
	
}
